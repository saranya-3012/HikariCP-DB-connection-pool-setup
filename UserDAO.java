package poolset;

import java.sql.*;
import javax.sql.DataSource;

public class UserDAO {

    private final DataSource dataSource;

    public UserDAO() {
        this.dataSource = DBConfiguration.getDataSource();
    }

    // Create New User
    public void createUser(String username, double initialBalance) throws SQLException {
    	
        String sql = "INSERT INTO Users (username, balance) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	
            ps.setString(1, username);
            ps.setDouble(2, initialBalance);
            ps.executeUpdate();
        }
    }

    // Check Balance 
    public double getBalance(int userId) throws SQLException {
    	
        String sql = "SELECT balance FROM Users_Inf WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("balance");
        }
        return 0.0;
    }

    // Update Balance
    public void updateBalance(int userId, double newBalance) throws SQLException {
    	
        String sql = "UPDATE Users_Inf SET balance = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	
            ps.setDouble(1, newBalance);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }
}
