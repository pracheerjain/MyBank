package hcl.mybankapp.mybankapp.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import hcl.mybankapp.mybankapp.dto.AccountDetailsResponseDTO;
import hcl.mybankapp.mybankapp.dto.ResponseDTO;
import hcl.mybankapp.mybankapp.dto.TransactionDetailsDTO;
import hcl.mybankapp.mybankapp.entity.Account;
import hcl.mybankapp.mybankapp.entity.Customer;
import hcl.mybankapp.mybankapp.entity.Transaction;
import hcl.mybankapp.mybankapp.exception.ResourceNotFoundException;
import hcl.mybankapp.mybankapp.repository.AccountRepository;
import hcl.mybankapp.mybankapp.repository.CustomerRepository;
import hcl.mybankapp.mybankapp.repository.TransactionRepository;
import hcl.mybankapp.mybankapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	/*
	 * @Autowired private AccountRepository AccountRepository;
	 */

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	public ResponseDTO getAccountSummary(String customerId) throws ResourceNotFoundException {

		Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);

		if (!optionalCustomer.isPresent()) {
			throw new ResourceNotFoundException("Invalid customer id");
		}
		// Pageable page=PageRequest.of(0, 10, Sort.by("date").descending());
		Customer customer = optionalCustomer.get();
		Account accountSummary = accountRepository.getAccountSummary(customer.getAccountId());

		AccountDetailsResponseDTO accountDetailsResponseDTO = new AccountDetailsResponseDTO();
		accountDetailsResponseDTO.setAccountBalance(accountSummary.getAccountBalance());
		accountDetailsResponseDTO.setAccountHolderName(customer.getCustomerName());
		accountDetailsResponseDTO.setAccountNumber(accountSummary.getAccountNo());
		accountDetailsResponseDTO.setAccountType(accountSummary.getAccountType());

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setHttpStatus(HttpStatus.OK);
		responseDTO.setMessage("Account summary");
		responseDTO.setData(accountDetailsResponseDTO);
		return responseDTO;

	}


		@Override
	    public ResponseDTO getAccountDetails(String customerId) {
	        
	        logger.info("Inside Service method");
	        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);
	        
	    
	        if (!optionalCustomer.isPresent()) {
	            throw new ResourceNotFoundException("Invalid customer id");
	        }
	                
	                
	        Customer customer = optionalCustomer.get();
	        
	        Pageable page=PageRequest.of(0, 10, Sort.by("transactionDate").descending());
	        
	        Account customerAccount = accountRepository.getAccountSummary(customer.getAccountId());
	        List<Transaction> accountDetails= transactionRepository.getTransactionDetails(customerAccount.getAccountNo(), page);
	        
	        TransactionDetailsDTO transactionDetailsDTO = new TransactionDetailsDTO();
	        transactionDetailsDTO.setAccountBalance(customerAccount.getAccountBalance());
	        transactionDetailsDTO.setAccountNumber(customerAccount.getAccountNo());
	        transactionDetailsDTO.setAccountCreationDate(customerAccount.getAccountCreationDate());
	        transactionDetailsDTO.setTransactionList(accountDetails);
	        
	        ResponseDTO response = new ResponseDTO();
	        response.setHttpStatus(HttpStatus.OK);
	        response.setMessage("Account details:");
	        response.setData(transactionDetailsDTO);
	        return response;
	        
	    }
	}
