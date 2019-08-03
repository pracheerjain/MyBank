package hcl.mybankapp.mybankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hcl.mybankapp.mybankapp.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    

	public Account findByAccountNo(String accountNumber);
	
	@Query(value = "select sum(transation_amount) from mybank.transaction where customer_account_no = :accountNumber and datediff(now(),transaction_date) = 0 group by customer_account_no;", nativeQuery = true)
	public Double getTotalTransactedAmountOfDay(String accountNumber);
	
	//@Query(value="select * from Account where id = :accountId",nativeQuery = true)
	public Account findbyAccountId(Account accountId);
}
