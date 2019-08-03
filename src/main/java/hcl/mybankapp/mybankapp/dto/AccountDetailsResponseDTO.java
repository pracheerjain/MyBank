package hcl.mybankapp.mybankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsResponseDTO {

	private String accountHolderName;
	private String accountType;

	private Double accountBalance;

	private String accountNumber;

}
