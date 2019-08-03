package hcl.mybankapp.mybankapp.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import hcl.mybankapp.mybankapp.dto.AccountDetailsResponseDTO;
import hcl.mybankapp.mybankapp.dto.ResponseDTO;
import hcl.mybankapp.mybankapp.entity.Account;
import hcl.mybankapp.mybankapp.entity.Customer;
import hcl.mybankapp.mybankapp.exception.ResourceNotFoundException;
import hcl.mybankapp.mybankapp.repository.AccountRepository;
import hcl.mybankapp.mybankapp.repository.CustomerRepository;
import hcl.mybankapp.mybankapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;


	public ResponseDTO getAccountSummary(String customerId) throws ResourceNotFoundException {

		Optional<Customer> optionalCustomer = customerRepository.findByCustomerId(customerId);

		if (optionalCustomer.isPresent()) {
			// Pageable page=PageRequest.of(0, 10, Sort.by("date").descending());
			Account accountSummary = accountRepository.getAccountSummary((optionalCustomer.get().getCustomerId()));

			AccountDetailsResponseDTO responseDTO = new AccountDetailsResponseDTO();

			ResponseDTO response = new ResponseDTO();
			response.setHttpStatus(HttpStatus.OK);
			response.setMessage("Account summary");
			response.setData(responseDTO);
			return response;
		} else {
			throw new ResourceNotFoundException("Invalid customer id");
		}
	}

	@Override
	public ResponseDTO getAccountDetails(String customerId) {

		return null;
	}
}
