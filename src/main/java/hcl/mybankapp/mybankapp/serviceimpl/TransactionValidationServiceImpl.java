package hcl.mybankapp.mybankapp.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import hcl.mybankapp.mybankapp.entity.Account;
import hcl.mybankapp.mybankapp.exception.ApplicationException;
import hcl.mybankapp.mybankapp.repository.AccountRepository;
import hcl.mybankapp.mybankapp.service.TransactionValidationService;

public class TransactionValidationServiceImpl implements TransactionValidationService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionValidationServiceImpl.class);
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Boolean minimumBalanceValidation(String accountNumber, Double amount) throws ApplicationException {
		logger.info("Inside minimumBalanceValidation method of TransactionValidationServiceImpl class");
		if (null == accountNumber || null == amount) {
			throw new ApplicationException("Account Number or amount is invalid");
		}

		Account accountDetails = accountRepository.findByAccountNo(accountNumber);
		Double minimumBalance = accountDetails.getAccountMinBal();
		Double availableBalance = accountDetails.getAccountBalance();
		Double transactionLimit = accountDetails.getTransactionLimit();
		if (minimumBalance > (availableBalance - amount)) {
			throw new ApplicationException("Insufficient Balance");
		}
		if (amount <= transactionLimit) {
			Double totalTransactionAmountForToday = accountRepository.getTotalTransactedAmountOfDay(accountNumber);
			if (transactionLimit < (totalTransactionAmountForToday + amount)) {
				throw new ApplicationException("Exceeded DailyTransaction limit");
			}
		}
		return true;
	}

}
