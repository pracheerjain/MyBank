package hcl.mybankapp.mybankapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



import hcl.mybankapp.mybankapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByCustomerId(String customerId);

	
	Optional<Customer> findByCustomerIdAndPassword(String customerId, String password);

}
