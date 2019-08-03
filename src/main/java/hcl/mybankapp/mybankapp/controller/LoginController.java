package hcl.mybankapp.mybankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcl.mybankapp.mybankapp.dto.CustomerDTO;
import hcl.mybankapp.mybankapp.dto.ResponseDTO;
import hcl.mybankapp.mybankapp.exception.ApplicationException;
import hcl.mybankapp.mybankapp.exception.UserIsInactiveException;
import hcl.mybankapp.mybankapp.serviceimpl.LoginServiceImpl;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

	@Autowired
	LoginServiceImpl loginServiceImpl;
	
	@PostMapping("/validate")
	public ResponseEntity<ResponseDTO> validateUser(@RequestBody CustomerDTO inCustomer) throws ApplicationException, UserIsInactiveException {
		validate(inCustomer);
		ResponseDTO response = loginServiceImpl.validateUser(inCustomer);
		if(response.getHttpStatus().equals(HttpStatus.OK)) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else
			throw new ApplicationException(response.getMessage());
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

