package hcl.mybankapp.mybankapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import hcl.mybankapp.mybankapp.repository.AccountRepository;
import hcl.mybankapp.mybankapp.service.TransactionValidationService;

public class TransactionValidationServiceImpl implements TransactionValidationService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public String minimumBalanceValidation(String accountNumber, Double amount) {
		if(StringUtils.isEmpty(accountNumber) || null != amount ) {
			
		}
		return null;
	}

}
