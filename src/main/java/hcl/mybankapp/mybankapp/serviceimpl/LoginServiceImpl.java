package hcl.mybankapp.mybankapp.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		
		ResponseDTO response = new ResponseDTO();
		
		
		Optional<Customer> customer = customerRepository.findByCustomerIdAndPassword(inCustomer.getCustomerId(), inCustomer.getPassword());
		
		if(customer.isPresent()) {
			
			if(customer.get().getStatus().equalsIgnoreCase("y")) {
				
				response.setMessage("Success");
				response.setHttpStatus(HttpStatus.OK);
				response.setData("");
				
				return response;
				
			}
			
			else
			response.setMessage("User is Inactive");
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
			response.setData("");
			
			return response;
				
		}
		
		response.setMessage("Failed");
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		response.setData("");
		return response;
		// TODO Auto-generated method stub
		
	}
	
}
