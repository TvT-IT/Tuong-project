package graduationmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    public void addStudent(String studentId, String name) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO Students (student_id, name) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, studentId);
        stmt.setString(2, name);
        stmt.executeUpdate();
    }

    public List<Object[]> getAllStudents() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Students";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        List<Object[]> students = new ArrayList<>();
        while (rs.next()) {
            students.add(new Object[]{
                rs.getString("student_id"),
                rs.getString("name"),
                rs.getDouble("math"),
                rs.getDouble("literature"),
                rs.getDouble("english")
            });
        }
        return students;
    }

    public void updateScore(String studentId, double math, double literature, double english) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE Students SET math = ?, literature = ?, english = ? WHERE student_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setDouble(1, math);
        stmt.setDouble(2, literature);
        stmt.setDouble(3, english);
        stmt.setString(4, studentId);
        stmt.executeUpdate();
    }
}
