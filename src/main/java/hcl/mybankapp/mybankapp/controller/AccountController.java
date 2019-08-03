package hcl.mybankapp.mybankapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hcl.mybankapp.mybankapp.dto.ResponseDTO;
import hcl.mybankapp.mybankapp.exception.ApplicationException;
import hcl.mybankapp.mybankapp.service.AccountService;

@CrossOrigin
@RestController
public class AccountController {
	
private static final Logger logger = LoggerFactory.getLogger(AccountController.class); 
	
	@Autowired
	private AccountService AccountService;
	
	
	@GetMapping("/summary/{customerId}")
	public ResponseEntity<ResponseDTO> summary(@PathVariable String customerId) throws ApplicationException{
		
		logger.info("Received customer id");
		
		if (null == customerId || "".equalsIgnoreCase(customerId)) {
			throw new ApplicationException("Please enter valid Customer Id...");
		}
		else {
			logger.debug("Customer Id received is "+ customerId);
			ResponseDTO transactions= AccountService.getAccountDetails(customerId);
			logger.debug("Summary details for the customer are " + transactions );
			return new ResponseEntity<ResponseDTO>(transactions,HttpStatus.OK);
		}
	}

}
