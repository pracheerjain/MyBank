package hcl.mybankapp.mybankapp.service;

import hcl.mybankapp.mybankapp.dto.FundTranferDTO;
import hcl.mybankapp.mybankapp.dto.ResponseDTO;
import hcl.mybankapp.mybankapp.exception.ApplicationException;

public interface FundTransferService {
	
	public ResponseDTO fundTransfer(FundTranferDTO fundTranferDTO) throws ApplicationException;
	
	public ResponseDTO getBeneficiaries(String customerId);
	

}
