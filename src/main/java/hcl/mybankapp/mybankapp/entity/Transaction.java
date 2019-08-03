package hcl.mybankapp.mybankapp.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "transaction_date")
	private LocalDate transactionDate;
	
	@Column(name = "transaction_time")
	private LocalTime transactionTime;
	
	@ManyToOne
	@JoinColumn(name = "customer_account_no")
	private Account customerAccountNo;
	
	@Column(name = "beneficiary_account_no")
	private Account beneficiaryAccountNo;
	
	@Column(name = "final_account_balance")
	private Double finalAccountBalance;
	
	@Column(name = "transation_amount")
	private Double transationAmount;
	

}
