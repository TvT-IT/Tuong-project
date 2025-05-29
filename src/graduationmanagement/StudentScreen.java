package graduationmanagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentScreen extends JFrame {
    public StudentScreen(String studentId) {
        setTitle("Thông Tin Học Sinh");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtInfo);
        add(scrollPane, BorderLayout.CENTER);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Students WHERE student_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, studentId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        txtInfo.setText(
                            "SBD: " + rs.getString("student_id") + "\n" +
                            "Họ Tên: " + rs.getString("name") + "\n" +
                            "Toán: " + rs.getDouble("math") + "\n" +
                            "Văn: " + rs.getDouble("literature") + "\n" +
                            "Anh: " + rs.getDouble("english")
                        );
                    } else {
                        txtInfo.setText("Không tìm thấy học sinh với SBD: " + studentId);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }

        setVisible(true);
    }
}
