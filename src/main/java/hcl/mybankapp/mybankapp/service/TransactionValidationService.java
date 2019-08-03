package hcl.mybankapp.mybankapp.service;

public interface TransactionValidationService {

	public String minimumBalanceValidation(String accountNumber, Double amount);
}
