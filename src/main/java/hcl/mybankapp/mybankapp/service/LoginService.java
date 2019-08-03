package hcl.mybankapp.mybankapp.service;

import hcl.mybankapp.mybankapp.dto.CustomerDTO;
import hcl.mybankapp.mybankapp.dto.ResponseDTO;

public interface LoginService {

	public ResponseDTO validateUser(CustomerDTO inCustomer);
	
}
