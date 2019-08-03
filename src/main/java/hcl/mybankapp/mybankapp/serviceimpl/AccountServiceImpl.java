package hcl.mybankapp.mybankapp.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public ResponseDTO getAccountDetails(String customerId) throws ResourceNotFoundException {
		if (null == customerId || "".equalsIgnoreCase(customerId)) {
			ResponseDTO response = new ResponseDTO();
			throw new ResourceNotFoundException("Customer Id is not valid...Please check again");

			/*
			 * Optional<Customer> customerById= customerRepository.findByCustomerId(String
			 * customerId); if(customerById.isPresent()) { Customer customer=
			 * customerById.get();
			 * 
			 * 
			 * Account account= new Account(); Optional<Account> accountOptional=
			 * accountRepository.findById(customer.getCustomerId());
			 */

		}

		else {
			throw new ResourceNotFoundException("Customer id is not valid");
		}

		return null;

	}

	@Override
	public ResponseDTO getAccountSummary(String customerId) {

		return null;
	}
}
