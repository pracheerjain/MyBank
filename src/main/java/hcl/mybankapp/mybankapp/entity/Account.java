package hcl.mybankapp.mybankapp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "account_balance")
	private String accountBalance;
	
	@Column(name = "account_no")
	private String accountNo;
	
	@Column(name = "account_creation_date")
	private LocalDate accountCreationDate;
	
	@Column(name = "account_min_bal")
	private Double accountMinBal;
	
	@Column(name = "transaction_limit")
	private Integer transactionLimit;
	
	

}
