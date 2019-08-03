package hcl.mybankapp.mybankapp.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcl.mybankapp.mybankapp.dto.ResponseDTO;
import hcl.mybankapp.mybankapp.exception.ResourceNotFoundException;
import hcl.mybankapp.mybankapp.repository.AccountRepository;
import hcl.mybankapp.mybankapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class); 
	/*
	 * @Autowired private AccountRepository AccountRepository;
	 */
	
	@Autowired
	private AccountRepository customerRepository;
	
	
	public ResponseDTO getAccountDetails(String customerId) throws ResourceNotFoundException{
		return null;
	}		
}
