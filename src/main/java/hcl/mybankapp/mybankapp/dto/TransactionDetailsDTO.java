package hcl.mybankapp.mybankapp.dto;
import java.time.LocalDate;
import java.util.List;
import hcl.mybankapp.mybankapp.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailsDTO {
    
    private Double accountBalance;
    private String accountNumber;
    
    private LocalDate accountCreationDate;
    
    private List<Transaction> transactionList;
}