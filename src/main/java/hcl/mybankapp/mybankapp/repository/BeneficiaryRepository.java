package hcl.mybankapp.mybankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hcl.mybankapp.mybankapp.entity.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

	List<Beneficiary> findByCustomerId(String customerId);
	
}
