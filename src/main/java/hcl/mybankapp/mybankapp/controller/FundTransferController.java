package hcl.mybankapp.mybankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hcl.mybankapp.mybankapp.dto.FundTranferDTO;
import hcl.mybankapp.mybankapp.exception.ApplicationException;
import hcl.mybankapp.mybankapp.service.FundTransferService;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class FundTransferController {
	
	
	private static final String ERR_MSG = "Mandetory element missing : ";
	
	@Autowired
	FundTransferService fundTransferService;
	
	@PostMapping("/transfer")
	public ResponseEntity<Object> fundTransfer(@RequestBody FundTranferDTO fundTranferDTO) throws ApplicationException{
		
		validateRequest(fundTranferDTO);
		return null;
	}
	
	@GetMapping("/beneficiary/{customerId}")
	public ResponseEntity<Object> getBeneficiary(@PathVariable("customerId") String customerId) throws ApplicationException{
		
		return new ResponseEntity<Object>(fundTransferService.getBeneficiaries(customerId), HttpStatus.OK);
	}
	
	private void validateRequest(FundTranferDTO fundTranferDTO) throws ApplicationException{
		
		if(StringUtils.isEmpty(fundTranferDTO.getBeneficiaryAccountNo())) {
			throw new ApplicationException(ERR_MSG + "Beneficiary Account No");
		}
		
		if(StringUtils.isEmpty(fundTranferDTO.getCustomerAccountNo())) {
			throw new ApplicationException(ERR_MSG + "Customer Account No");
		}
		
		if(null != fundTranferDTO.getTransactionAmount()) {
			throw new ApplicationException(ERR_MSG + "Transaction Amount");
		}
		
		if(fundTranferDTO.getTransactionAmount() < 0) {
			throw new ApplicationException("Transaction Amount should be greater than 0.");
		}
		
		if(fundTranferDTO.getBeneficiaryAccountNo().equals(fundTranferDTO.getCustomerAccountNo())) {
			throw new ApplicationException("Customer and beneficiary account should not be same.");
		}
		
	}

}
