package graduationmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class LoginScreen extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private final UserManager userManager;

    public LoginScreen() {
        userManager = new UserManager();
        initComponents();
    }

    private void initComponents() {
        setTitle("Login System");
        setSize(350, 200);
        setLocationRelativeTo(null); // hiển thị giữa màn hình
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitle = new JLabel("Đăng nhập hệ thống");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        add(lblTitle, gbc);

        JLabel lblUser = new JLabel("Username:");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        add(lblUser, gbc);

        txtUsername = new JTextField(15);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 5, 10);
        add(txtUsername, gbc);

        JLabel lblPass = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 5, 5);
        add(lblPass, gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 5, 10);
        add(txtPassword, gbc);

        btnLogin = new JButton("Login");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(btnLogin, gbc);

        btnLogin.addActionListener(this::login);
    }

   private void login(ActionEvent e) {
    String username = txtUsername.getText();
    String password = new String(txtPassword.getPassword());

    try {
        String role = userManager.login(username, password);
        if (role == null) {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!");
        } else {
            new MainMenu(username, role).setVisible(true);
            this.dispose();
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Lỗi kết nối DB: " + ex.getMessage());
    }
}}

