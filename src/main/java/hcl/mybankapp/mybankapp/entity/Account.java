package hcl.mybankapp.mybankapp.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "account")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6478863948823155841L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/*
	 * @OneToOne()
	 * 
	 * @JoinColumn(name = "customer_id")
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Long customerId;

	@Column(name = "account_type")
	private String accountType;

	@Column(name = "account_balance")
	private Double accountBalance;

	@Column(name = "account_no")
	private String accountNo;

	@Column(name = "account_creation_date")
	private LocalDate accountCreationDate;

	@Column(name = "account_min_bal")
	private Double accountMinBal;

	@Column(name = "transaction_limit")
	private Double transactionLimit;

	@OneToMany(mappedBy = "customerAccountNo", cascade = CascadeType.ALL)
	private List<Transaction> transactionList = new ArrayList<>();

}
