package hcl.mybankapp.mybankapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import hcl.mybankapp.mybankapp.entity.Account;
import hcl.mybankapp.mybankapp.exception.ApplicationException;
import hcl.mybankapp.mybankapp.repository.AccountRepository;
import hcl.mybankapp.mybankapp.service.TransactionValidationService;

public class TransactionValidationServiceImpl implements TransactionValidationService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public String minimumBalanceValidation(String accountNumber, Double amount) throws ApplicationException {
		if (null != accountNumber || null == amount) {
			throw new ApplicationException("Account Number or amount is invalid");
		}

		Account accountDetails = accountRepository.findByAccountNo(accountNumber).get();
		Double minimumBalance = accountDetails.getAccountMinBal();
		Double availableBalance = accountDetails.getAccountBalance();
		Double transactionLimit = accountDetails.getTransactionLimit();
		if (minimumBalance > (availableBalance - amount)) {
			throw new ApplicationException("Insufficient Balance");
		}
		Double totalTransactionAmountForToday = accountRepository.getTotalTransactedAmountOfDay(accountNumber);
		if (transactionLimit < (totalTransactionAmountForToday + amount)) {
			throw new ApplicationException("Exceeded DailyTransaction limit");
		}
		return "Transaction is valid";
	}

}
