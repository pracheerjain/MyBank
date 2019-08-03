package hcl.mybankapp.mybankapp.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcl.mybankapp.mybankapp.dto.CustomerDTO;
import hcl.mybankapp.mybankapp.dto.ResponseDTO;
import hcl.mybankapp.mybankapp.entity.Customer;
import hcl.mybankapp.mybankapp.repository.CustomerRepository;
import hcl.mybankapp.mybankapp.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	CustomerRepository customerRepository;

	public ResponseDTO validateUser(CustomerDTO inCustomer) {
		
		Optional<Customer> customer = customerRepository.findByCustomerIdAndPassword(inCustomer.getCustomerId(), inCustomer.getPassword());
		
		if(customer.isPresent()) {
			
			if()
		}
		
		return null;
		// TODO Auto-generated method stub
		
	}
	
}
