package hcl.mybankapp.mybankapp.service;

import hcl.mybankapp.mybankapp.dto.CustomerDTO;
import hcl.mybankapp.mybankapp.dto.ResponseDTO;
import hcl.mybankapp.mybankapp.exception.UserIsInactiveException;

public interface LoginService {

	public ResponseDTO validateUser(CustomerDTO inCustomer) throws UserIsInactiveException;
	
}
