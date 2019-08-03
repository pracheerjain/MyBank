package hcl.mybankapp.mybankapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

	@PostMapping("/validate")
	public String validateUser() {
		return null;
		
	}
	
	
	private void validate() {
		
	}
	
}

