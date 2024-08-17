import java.util.List;

import com.nwb.cust.controller.TransactionController;
import com.nwb.cust.repo.TransactionRepository;
import com.nwb.cust.service.TransactionService;
import com.nwb.cust.model.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TransactionRepository repository = new TransactionRepository();
        TransactionService service = new TransactionService(repository);
        TransactionController controller = new TransactionController(service);

        // Example usage
        List<Transaction> successTransactions = controller.getTransactionsByStatus("SUCCESS");
        System.out.println("Success Transactions: " + successTransactions.size());

        List<Transaction> failedTransactions = controller.getTransactionsByStatus("FAILED");
        System.out.println("Failed Transactions: " + failedTransactions.size());
        
        
        List<Transaction> pendingTransactions = controller.getTransactionsByStatus("PENDING");
        System.out.println("Pending Transactions: " + pendingTransactions.size());
	}

}
