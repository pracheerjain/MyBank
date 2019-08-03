package hcl.mybankapp.mybankapp.serviceimpl;

import java.util.List;

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

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	@Override
	public ResponseDTO FundTransfer(FundTranferDTO fundTranferDTO) throws ApplicationException {
		ResponseDTO responseDTO = new ResponseDTO();

		fundTranferDTO.getCustomerAccountNo();

		return responseDTO;
	}

	@Override
	public ResponseDTO getBeneficiaries(String customerId) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<Beneficiary> beneficiaryList = beneficiaryRepository.findByCustomerId(customerId);

		if (beneficiaryList.isEmpty()) {
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
