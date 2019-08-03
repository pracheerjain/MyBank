package hcl.mybankapp.mybankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hcl.mybankapp.mybankapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
