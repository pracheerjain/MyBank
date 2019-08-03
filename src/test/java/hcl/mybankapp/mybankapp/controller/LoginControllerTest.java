package hcl.mybankapp.mybankapp.controller;


import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import hcl.mybankapp.mybankapp.dto.CustomerDTO;
import hcl.mybankapp.mybankapp.dto.ResponseDTO;
import hcl.mybankapp.mybankapp.exception.ApplicationException;
import hcl.mybankapp.mybankapp.exception.UserIsInactiveException;
import hcl.mybankapp.mybankapp.serviceimpl.LoginServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
	
	@InjectMocks
	LoginController loginController;
	
	@Mock
	LoginServiceImpl loginServiceImpl;
	
	CustomerDTO sampleCustomer = new CustomerDTO();
	
	ResponseDTO response = new ResponseDTO();
	
	@Before
	public void setUp() {
		
		sampleCustomer.setCustomerId("sagar");
		sampleCustomer.setPassword("Sagar7");
	}

	@Test
	public void authenticatedUser() throws UserIsInactiveException, ApplicationException {
		
		response.setHttpStatus(HttpStatus.OK);
		response.setMessage("success");
		Mockito.when(loginServiceImpl.validateUser(sampleCustomer)).thenReturn(response);
		assertNotNull(loginController.validateUser(sampleCustomer));

	
	}
	
	@Test(expected = ApplicationException.class)
	public void unauthorizedUser() throws UserIsInactiveException, ApplicationException {
		
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		response.setMessage("Invalid details");
		Mockito.when(loginServiceImpl.validateUser(sampleCustomer)).thenReturn(response);
		assertNotNull(loginController.validateUser(sampleCustomer));

	
	}
	
	/*
	 * @Test(expected = ApplicationException.class) public void customerIdIsNull()
	 * throws UserIsInactiveException, ApplicationException {
	 * sampleCustomer.setCustomerId("");
	 * 
	 * response.setHttpStatus(HttpStatus.BAD_REQUEST);
	 * response.setMessage("CustomerId should not be empty");
	 * Mockito.when(loginServiceImpl.validateUser(sampleCustomer)).thenReturn(
	 * response); assertNotNull(loginController.validateUser(sampleCustomer));
	 * 
	 * 
	 * }
	 * 
	 * @Test(expected = ApplicationException.class) public void passwordIsNull()
	 * throws UserIsInactiveException, ApplicationException {
	 * sampleCustomer.setPassword("");
	 * 
	 * response.setHttpStatus(HttpStatus.BAD_REQUEST);
	 * response.setMessage("Passeord should not be empty");
	 * Mockito.when(loginServiceImpl.validateUser(sampleCustomer)).thenReturn(
	 * response); assertNotNull(loginController.validateUser(sampleCustomer));
	 * 
	 * 
	 * }
	 */
}
