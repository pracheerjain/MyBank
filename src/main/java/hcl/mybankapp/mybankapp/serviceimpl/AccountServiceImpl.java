package hcl.mybankapp.mybankapp.serviceimpl;

@Service
public class AccountServiceImpl implements AccountService {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionSummaryServiceImpl.class); 
	/*
	 * @Autowired private TransactionRepository transactionRepository;
	 */
	
	@Autowired
	private AccountRepository customerRepository;
	
	
	public ResponseDTO getAccountDetails(String customerId) throws CustomerNotFoundException{
		
		logger.info("Inside Service method");
		Optional<Customer> optopnalCustomer = customerRepository.findByCustomerId(customerId);
		
	
			if (optopnalCustomer.isPresent()) {
				Pageable page=PageRequest.of(0, 10, Sort.by("date").descending());
			List<Transaction> transactions = transactionRepository.findTransactionDetails(optopnalCustomer.get().getId(),page);
			 
			 List<TransactionDTO> respList = new ArrayList<>();
			 transactions.forEach(u -> {
					respList.add(TransactionMapper.mapTransactionDTOToResponseList(u));

				});
			ResponseDTO response = new ResponseDTO();
			response.setHttpStatus(HttpStatus.OK);
			response.setMessage("Transactions summary");
			response.setData(respList);
			return response;
		}
			else {
				throw new CustomerNotFoundException("Invalid customer id");
			}
	}

}
