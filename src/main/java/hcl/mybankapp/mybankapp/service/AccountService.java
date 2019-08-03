package hcl.mybankapp.mybankapp.service;

import hcl.mybankapp.mybankapp.dto.ResponseDTO;

public interface AccountService {
	
	public ResponseDTO getAccountDetails(String customerId);
	public ResponseDTO getAccountSummary(String customerId);

}
