package hcl.mybankapp.mybankapp.service;

import hcl.mybankapp.mybankapp.dto.ResponseDTO;

public interface AccountService {

	ResponseDTO getAccountDetails(String customerId);

}
