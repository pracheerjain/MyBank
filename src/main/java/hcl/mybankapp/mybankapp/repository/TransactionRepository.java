package hcl.mybankapp.mybankapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hcl.mybankapp.mybankapp.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	@Query(value="select * from transaction where customer_account_no =:accountNo",nativeQuery=true)
    List<Transaction> getTransactionDetails(Long accountNo, Pageable page);
	
}
