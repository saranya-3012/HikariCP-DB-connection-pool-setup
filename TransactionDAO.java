package poolset;

import java.sql.*;
import javax.sql.DataSource;

public class TransactionDAO {

    private final DataSource dataSource;
    private final UserDAO userDAO;

    public TransactionDAO() {
    	
        this.dataSource = DBConfiguration.getDataSource();
        this.userDAO = new UserDAO();
    }

    // Deposit
    public void deposit(int userId, double amount) throws SQLException {
    	
        double currentBalance = userDAO.getBalance(userId);
        double newBalance = currentBalance + amount;

        try (Connection conn = dataSource.getConnection()) {
        	
        	// Sets the Connection to Auto-commit
            conn.setAutoCommit(false);

            userDAO.updateBalance(userId, newBalance);

            String sql = "INSERT INTO Transactions_Inf (user_id, type, amount) VALUES (?, 'DEPOSIT', ?)";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
            	
                ps.setInt(1, userId);
                ps.setDouble(2, amount);
                ps.executeUpdate();
            }

            conn.commit();
        }
    }

    // Withdraw
    public void withdraw(int userId, double amount) throws SQLException {
    	
        double currentBalance = userDAO.getBalance(userId);
        if (currentBalance < amount) throw new SQLException("Insufficient balance");

        double newBalance = currentBalance - amount;

        try (Connection conn = dataSource.getConnection()) {
        	
            conn.setAutoCommit(false);

            userDAO.updateBalance(userId, newBalance);

            String sql = "INSERT INTO Transactions_Inf (user_id, type, amount) VALUES (?, 'WITHDRAW', ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
            	
                ps.setInt(1, userId);
                ps.setDouble(2, amount);
                ps.executeUpdate();
            }

            conn.commit();
        }
    }
}
