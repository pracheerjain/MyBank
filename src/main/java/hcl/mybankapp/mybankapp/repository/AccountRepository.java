package hcl.mybankapp.mybankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hcl.mybankapp.mybankapp.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    

	public Account findByAccountNo(String accountNumber);
	
	@Query(value = "select sum(transaction_amount from transaction where from_account = :accountNumber and transaction_date = Now() group by from_account)", nativeQuery = true)
	public Double getTotalTransactedAmountOfDay(String accountNumber);
	
	@Query(value="select * from Account where id = :accountId",nativeQuery = true)
	public Account getAccountSummary(Account accountId);
}
