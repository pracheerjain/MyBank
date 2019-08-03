package hcl.mybankapp.mybankapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import hcl.mybankapp.mybankapp.exception.ApplicationException;
import hcl.mybankapp.mybankapp.repository.AccountRepository;
import hcl.mybankapp.mybankapp.service.TransactionValidationService;

public class TransactionValidationServiceImpl implements TransactionValidationService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public String minimumBalanceValidation(String accountNumber, Double amount) throws ApplicationException {
		if(null != accountNumber || null == amount ) {
			throw new ApplicationException("Account Number or amount is invalid");
		}
		Double minimumBalance;
		if(minimumBalance > amount)
		return null;
	}

}
