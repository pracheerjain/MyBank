package hcl.mybankapp.mybankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hcl.mybankapp.mybankapp.service.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	
	public Account getAccountDetails(String customerId);

}
