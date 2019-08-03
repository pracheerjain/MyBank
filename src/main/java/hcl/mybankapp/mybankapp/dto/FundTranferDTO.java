package hcl.mybankapp.mybankapp.dto;

import lombok.Data;

@Data
public class FundTranferDTO {
	
	private String customerAccountNo;
	
	private String beneficiaryAccountNo;
	
	private Double transactionAmount;
	
	private String comments;

}
