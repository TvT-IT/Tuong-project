package graduationmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class TeacherScreen extends JFrame {
    private JTable tblStudents;
    private DefaultTableModel tableModel;
    private StudentManager studentManager;

    public TeacherScreen() {
        studentManager = new StudentManager();
        initComponents();
        loadStudents();
    }

    private void initComponents() {
        setTitle("Quản lý giáo viên");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"SBD", "Họ Tên", "Toán", "Văn", "Anh"}, 0);
        tblStudents = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblStudents);

        JPanel panelButtons = new JPanel();

        JButton btnAdd = new JButton("Thêm học sinh");
        JButton btnUpdate = new JButton("Cập nhật điểm");

        btnAdd.addActionListener(this::addStudent);
        btnUpdate.addActionListener(this::updateScore);

        panelButtons.add(btnAdd);
        panelButtons.add(btnUpdate);

        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }

    private void loadStudents() {
        try {
            tableModel.setRowCount(0);
            for (Object[] student : studentManager.getAllStudents()) {
                tableModel.addRow(student);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    private void addStudent(ActionEvent e) {
        String studentId = JOptionPane.showInputDialog(this, "Nhập SBD:");
        if (studentId == null || studentId.trim().isEmpty()) {
            return;
        }
        String name = JOptionPane.showInputDialog(this, "Nhập Họ Tên:");
        if (name == null || name.trim().isEmpty()) {
            return;
        }

        try {
            studentManager.addStudent(studentId.trim(), name.trim());
            loadStudents();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi thêm học sinh: " + ex.getMessage());
        }
    }

    private void updateScore(ActionEvent e) {
        int row = tblStudents.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn học sinh để cập nhật điểm!");
            return;
        }
        String studentId = (String) tableModel.getValueAt(row, 0);

        try {
            String mathStr = JOptionPane.showInputDialog(this, "Điểm Toán:");
            String literatureStr = JOptionPane.showInputDialog(this, "Điểm Văn:");
            String englishStr = JOptionPane.showInputDialog(this, "Điểm Anh:");

            double math = Double.parseDouble(mathStr);
            double literature = Double.parseDouble(literatureStr);
            double english = Double.parseDouble(englishStr);

            studentManager.updateScore(studentId, math, literature, english);
            loadStudents();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số điểm!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật điểm: " + ex.getMessage());
        }
    }}
    
