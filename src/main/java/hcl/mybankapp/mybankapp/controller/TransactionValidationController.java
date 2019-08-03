package hcl.mybankapp.mybankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hcl.mybankapp.mybankapp.exception.ApplicationException;
import hcl.mybankapp.mybankapp.service.TransactionValidationService;

@RestController
@RequestMapping("/transaction")
public class TransactionValidationController {

	@Autowired
	TransactionValidationService transactionValidationService;
	
	@GetMapping("/validate")
	public ResponseEntity<Boolean> validateTransaction(@RequestParam("accountNo") String accountNo, @RequestParam("amount") Double amount) throws ApplicationException{
		if(null == accountNo | null == amount || 0.0 == amount) {
			throw new ApplicationException("Enter valid Accouunt number or amount");
		}
		return new ResponseEntity<>(transactionValidationService.transactionValidations(accountNo, amount), HttpStatus.OK);
	}
}
