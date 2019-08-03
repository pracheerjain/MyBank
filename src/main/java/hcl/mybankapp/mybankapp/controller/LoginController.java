package hcl.mybankapp.mybankapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcl.mybankapp.mybankapp.dto.CustomerDTO;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

	@PostMapping("/validate")
	public String validateUser(@RequestBody CustomerDTO inCustomer) {
		return null;
		
	}
	
	
	private void validate(CustomerDTO inCustomer) {
		
	}
	
}

