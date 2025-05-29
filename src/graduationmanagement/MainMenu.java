package graduationmanagement;

import javax.swing.*;

public class MainMenu extends JFrame {
    private final String username;
    private final String role;

    public MainMenu(String username, String role) {
        this.username = username;
        this.role = role;

        initComponents();
    }

    private void initComponents() {
        setTitle("Menu Chính");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Xin chào, " + username + " (" + role + ")");
        JButton btnEnter = new JButton("Vào hệ thống");
        JButton btnExit = new JButton("Thoát");

        btnEnter.addActionListener(e -> {
            if ("teacher".equalsIgnoreCase(role)) {
                new TeacherScreen().setVisible(true);
            } else if ("student".equalsIgnoreCase(role)) {
                new StudentScreen(username).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Vai trò không xác định!");
            }
            this.dispose();
        });

        btnExit.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(btnEnter);
        panel.add(btnExit);

        add(panel);
        setVisible(true);
    }
}
