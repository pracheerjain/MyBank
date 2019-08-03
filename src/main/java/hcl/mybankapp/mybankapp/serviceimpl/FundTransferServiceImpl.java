package hcl.mybankapp.mybankapp.serviceimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import hcl.mybankapp.mybankapp.dto.FundTranferDTO;
import hcl.mybankapp.mybankapp.dto.ResponseDTO;
import hcl.mybankapp.mybankapp.entity.Account;
import hcl.mybankapp.mybankapp.entity.Beneficiary;
import hcl.mybankapp.mybankapp.entity.Transaction;
import hcl.mybankapp.mybankapp.exception.ApplicationException;
import hcl.mybankapp.mybankapp.exception.ResourceNotFoundException;
import hcl.mybankapp.mybankapp.repository.AccountRepository;
import hcl.mybankapp.mybankapp.repository.BeneficiaryRepository;
import hcl.mybankapp.mybankapp.repository.CustomerRepository;
import hcl.mybankapp.mybankapp.repository.TransactionRepository;
import hcl.mybankapp.mybankapp.service.FundTransferService;
import hcl.mybankapp.mybankapp.service.TransactionValidationService;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	private static final Logger logger = LoggerFactory.getLogger(FundTransferServiceImpl.class);

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	@Autowired
	TransactionValidationService transactionValidationService;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;

	@Override
	@Transactional
	public ResponseDTO fundTransfer(FundTranferDTO fundTranferDTO) throws ApplicationException {
		ResponseDTO responseDTO = new ResponseDTO();

		Optional<Account> optionalAccount = accountRepository.findByAccountNo(fundTranferDTO.getCustomerAccountNo());
		Account account;
		if (!optionalAccount.isPresent()) {
			throw new ResourceNotFoundException("Account not found.");
		} else {
			account = optionalAccount.get();
		}

		Optional<Account> optionalBeneAccount = accountRepository
				.findByAccountNo(fundTranferDTO.getBeneficiaryAccountNo());
		Account beneAccout;
		if (!optionalBeneAccount.isPresent()) {
			throw new ResourceNotFoundException("Beneficiary Account not found.");
		} else {
			beneAccout = optionalBeneAccount.get();
		}
		
		transactionValidationService.transactionValidations(fundTranferDTO.getCustomerAccountNo(), fundTranferDTO.getTransactionAmount());

		Double finalbalance = account.getAccountBalance() - fundTranferDTO.getTransactionAmount();
		account.setAccountBalance(finalbalance);
		Account saveAccount = accountRepository.save(account);

		Double benefinalbalance = beneAccout.getAccountBalance() + fundTranferDTO.getTransactionAmount();
		account.setAccountBalance(benefinalbalance);
		Account saveBeneAccount = accountRepository.save(beneAccout);

		Transaction transaction = new Transaction();
		transaction.setBeneficiaryAccountNo(fundTranferDTO.getBeneficiaryAccountNo());
		transaction.setCustomerAccountNo(saveAccount);
		transaction.setFinalAccountBalance(finalbalance);
		transaction.setTransactionDate(LocalDate.now());
		transaction.setTransactionTime(LocalTime.now());
		transaction.setTransationAmount(fundTranferDTO.getTransactionAmount());
		transaction.setComments(fundTranferDTO.getComments());

		Transaction transaction1 = new Transaction();
		transaction1.setBeneficiaryAccountNo(fundTranferDTO.getBeneficiaryAccountNo());
		transaction1.setCustomerAccountNo(saveBeneAccount);
		transaction1.setFinalAccountBalance(benefinalbalance);
		transaction1.setTransactionDate(LocalDate.now());
		transaction1.setTransactionTime(LocalTime.now());
		transaction1.setTransationAmount(fundTranferDTO.getTransactionAmount());
		transaction1.setComments(fundTranferDTO.getComments());

		Transaction saveTransaction = transactionRepository.save(transaction);
		transactionRepository.save(transaction1);

		logger.info("Returning fund transfer request.");
		responseDTO.setData(saveTransaction.getId());
		responseDTO.setHttpStatus(HttpStatus.OK);
		responseDTO.setMessage("Fund transferd succesfully");

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
