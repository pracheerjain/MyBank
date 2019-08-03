package hcl.mybankapp.mybankapp.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcl.mybankapp.mybankapp.dto.CustomerDTO;
import hcl.mybankapp.mybankapp.exception.ApplicationException;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

	@PostMapping("/validate")
	public String validateUser(@RequestBody CustomerDTO inCustomer) throws ApplicationException {
		validate(inCustomer);
		return null;
		
	}
	
	
	private void validate(CustomerDTO inCustomer) throws ApplicationException{
		
		if(StringUtils.isEmpty(inCustomer.getCustomerId())) {
			throw new ApplicationException("customer id should not be empty");
		}
		
		if(StringUtils.isEmpty(inCustomer.getPassword())) {
			throw new ApplicationException("Password should not be empty");
		}
		
	}
	
}

