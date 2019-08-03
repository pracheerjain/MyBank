package hcl.mybankapp.mybankapp.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "transaction")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Transaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3972741260607418209L;

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
	private String beneficiaryAccountNo;
	
	@Column(name = "final_account_balance")
	private Double finalAccountBalance;
	
	@Column(name = "transation_amount")
	private Double transationAmount;
	

}
