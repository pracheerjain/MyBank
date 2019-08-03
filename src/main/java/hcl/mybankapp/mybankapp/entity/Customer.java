package hcl.mybankapp.mybankapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "customer_id")
	private String customerId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "status")
	private String status;
	
	@OneToOne
	@JoinColumn(name = "account_id" )
	private Account accountId;
}
