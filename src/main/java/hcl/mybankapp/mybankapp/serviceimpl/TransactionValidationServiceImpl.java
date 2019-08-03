package hcl.mybankapp.mybankapp.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcl.mybankapp.mybankapp.entity.Account;
import hcl.mybankapp.mybankapp.exception.ApplicationException;
import hcl.mybankapp.mybankapp.exception.ResourceNotFoundException;
import hcl.mybankapp.mybankapp.repository.AccountRepository;
import hcl.mybankapp.mybankapp.service.TransactionValidationService;

@Service
public class TransactionValidationServiceImpl implements TransactionValidationService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionValidationServiceImpl.class);
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Boolean transactionValidations(String accountNumber, Double amount) throws ApplicationException {
		logger.info("Inside minimumBalanceValidation method of TransactionValidationServiceImpl class");
		if (null == accountNumber || null == amount) {
			throw new ApplicationException("Account Number or amount is invalid");
		}
		
		Optional<Account> accountDetails = accountRepository.findByAccountNo(accountNumber);
		if(!accountDetails.isPresent()) {
			throw new ResourceNotFoundException("Enter valid account number");
		}
		Account accountDetail = accountDetails.get();
		Double minimumBalance = accountDetail.getAccountMinBal();
		Double availableBalance = accountDetail.getAccountBalance();
		Double transactionLimit = accountDetail.getTransactionLimit();

		if (minimumBalance > (availableBalance - amount)) {
			throw new ApplicationException("Insufficient Balance");
		}
		if (amount > transactionLimit) {
			throw new ApplicationException("Exceeded DailyTransaction limit");
		}
		Double totalTransactionAmountForToday = accountRepository.getTotalTransactedAmountOfDay(accountDetail.getId());
		if (transactionLimit < (totalTransactionAmountForToday + amount)) {
			throw new ApplicationException("Exceeded DailyTransaction limit");
		}
		return true;
	
	}

}
