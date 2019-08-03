package hcl.mybankapp.mybankapp.service;

import hcl.mybankapp.mybankapp.exception.ApplicationException;

public interface TransactionValidationService {

	public Boolean minimumBalanceValidation(String accountNumber, Double amount) throws ApplicationException;
}
