package graduationmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
    public String login(String username, String password) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT role FROM Users WHERE username = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("role"); // trả về role (teacher/student)
        }
        return null; // sai tài khoản hoặc mật khẩu
    }
}
