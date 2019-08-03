package hcl.mybankapp.mybankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hcl.mybankapp.mybankapp.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	public void findByAccount;
}
