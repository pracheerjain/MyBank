package hcl.mybankapp.mybankapp.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import hcl.mybankapp.mybankapp.dto.CustomerDTO;
import hcl.mybankapp.mybankapp.dto.ResponseDTO;
import hcl.mybankapp.mybankapp.entity.Customer;
import hcl.mybankapp.mybankapp.exception.UserIsInactiveException;
import hcl.mybankapp.mybankapp.repository.CustomerRepository;
import hcl.mybankapp.mybankapp.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	@Autowired
	CustomerRepository customerRepository;
	public ResponseDTO validateUser(CustomerDTO inCustomer) throws UserIsInactiveException {
		
		ResponseDTO response = new ResponseDTO();
		
		
		Optional<Customer> customer = customerRepository.findByCustomerIdAndPassword(inCustomer.getCustomerId(), inCustomer.getPassword());
		
		if(customer.isPresent()) {
			
			
			if(customer.get().getStatus().equalsIgnoreCase("y")) {
				logger.debug("in correct case");
				response.setMessage("Success");
				response.setHttpStatus(HttpStatus.OK);
			}
			else {
				logger.debug("in inactive case");
				response.setMessage("User is inactive") ;
				response.setHttpStatus(HttpStatus.BAD_REQUEST);
			}
		}
		else {
			logger.debug("in authentication failed");
			response.setMessage("Authentication Failed");
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
