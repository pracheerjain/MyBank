package hcl.mybankapp.mybankapp.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import hcl.mybankapp.mybankapp.entity.Account;
import hcl.mybankapp.mybankapp.exception.ApplicationException;
import hcl.mybankapp.mybankapp.repository.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class TransactionValidationServiceImplTest {

	@InjectMocks
	TransactionValidationServiceImpl transactionValidationServiceImpl;
	
	@Mock
	AccountRepository accountRepository;
	
	Optional<Account> accountDetail;
	
	Account account;
	
	@org.junit.Before
	public void setUp() {
		account = new Account();
	}
	
	@Test
	public void testTransactionValidationsIfDetailsAreCorrect() throws ApplicationException {
		String accountNo = "35673";
		account.setId(1L);
		account.setAccountMinBal(1000.0);
		account.setAccountBalance(10000.0);
		account.setTransactionLimit(8000.0);
		accountDetail = Optional.of(account);
		when(accountRepository.findByAccountNo(accountNo)).thenReturn(accountDetail);
		when(accountRepository.getTotalTransactedAmountOfDay(1L)).thenReturn(5000.0);
		assertEquals(true, transactionValidationServiceImpl.transactionValidations(accountNo, 200.0));
	}
	
	@Test(expected = ApplicationException.class)
	public void testTransactionValidationsIfTransactionAmountIsGreaterThanAvailableBalance() throws ApplicationException {
		String accountNo = "35673";
		account.setId(1L);
		account.setAccountMinBal(1000.0);
		account.setAccountBalance(1000.0);
		account.setTransactionLimit(8000.0);
		accountDetail = Optional.of(account);
		when(accountRepository.findByAccountNo(accountNo)).thenReturn(accountDetail);
		assertEquals(true, transactionValidationServiceImpl.transactionValidations(accountNo, 200.0));
	}
}
