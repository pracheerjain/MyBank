package hcl.mybankapp.mybankapp.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import hcl.mybankapp.mybankapp.dto.FundTranferDTO;
import hcl.mybankapp.mybankapp.dto.ResponseDTO;
import hcl.mybankapp.mybankapp.entity.Beneficiary;
import hcl.mybankapp.mybankapp.exception.ApplicationException;
import hcl.mybankapp.mybankapp.repository.BeneficiaryRepository;
import hcl.mybankapp.mybankapp.repository.CustomerRepository;
import hcl.mybankapp.mybankapp.service.FundTransferService;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	private static final Logger logger = LoggerFactory.getLogger(FundTransferServiceImpl.class);
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	@Override
	public ResponseDTO fundTransfer(FundTranferDTO fundTranferDTO) throws ApplicationException {
		ResponseDTO responseDTO = new ResponseDTO();

		fundTranferDTO.getCustomerAccountNo();

		logger.info("Returning fund transfer request.");
		return responseDTO;
	}

	@Override
	public ResponseDTO getBeneficiaries(String customerId) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Beneficiary> beneficiaryList = beneficiaryRepository.findByCustomerId(customerId);

		if (null != beneficiaryList && beneficiaryList.isEmpty()) {
			responseDTO.setHttpStatus(HttpStatus.OK);
			responseDTO.setMessage("No beneficiary found.");
			responseDTO.setData(beneficiaryList);

		} else {
			responseDTO.setHttpStatus(HttpStatus.OK);
			responseDTO.setMessage("List of beneficiary");
			responseDTO.setData(beneficiaryList);
		}

		return responseDTO;
	}

}
