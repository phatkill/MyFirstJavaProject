package Login;

import ViewHome.Home;

import javax.swing.*;
import java.awt.*;
//thu vien them mail


public class LoginAdmin extends JFrame {
    public LoginAdmin() {
        this.init();
        this.setVisible(true);
    }

    public void init() {
        setTitle("Login Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        JPanel loginPanel = new JPanel();
        add(loginPanel, BorderLayout.CENTER);
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setOpaque(false);// nghệ thuật vẽ nền
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel title = new JLabel("Đăng nhập", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
//        title.setHorizontalAlignment(SwingConstants.CENTER);   // đặt ở giữa chiều ngang
        loginPanel.add(title, gbc);
        // ten dang nhap
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel lbuserName = new JLabel("Tên đăng nhập: ");
        lbuserName.setFont(new Font("Arial", Font.BOLD, 20));
        loginPanel.add(lbuserName, gbc);
        gbc.gridx = 1;
        JTextField txtuserName = new JTextField();
        loginPanel.add(txtuserName, gbc);
        txtuserName.setPreferredSize(new Dimension(200, 30));
        // mat khau
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lbPassword = new JLabel("Mật khẩu: ");
        lbPassword.setFont(new Font("Arial", Font.BOLD, 20));
        loginPanel.add(lbPassword, gbc);
        gbc.gridx = 1;
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        txtPassword.setPreferredSize(new Dimension(200, 30));
        loginPanel.add(txtPassword, gbc);

        // ------------- tao bieu do con mat ------------------------
        // Tạo biểu tượng cho nút
        ImageIcon eyeOpenIcon = new ImageIcon(new ImageIcon("path_to_eye_open_image.png").getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        ImageIcon eyeClosedIcon = new ImageIcon(new ImageIcon("path_to_eye_closed_image.png").getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

        //--------------- con mat ----------------
        gbc.gridx = 2;
        JButton buttonToggleVisibility = new JButton(eyeOpenIcon);
        buttonToggleVisibility.setFocusPainted(false);
        buttonToggleVisibility.setContentAreaFilled(false);
        buttonToggleVisibility.setBorderPainted(false); // Tắt khung viền của nút
        loginPanel.add(buttonToggleVisibility, gbc);

        // Xử lý sự kiện con mắt
        buttonToggleVisibility.addActionListener(e -> {
            if (txtPassword.getEchoChar() == 0) {
                txtPassword.setEchoChar('\u2022');
                buttonToggleVisibility.setIcon(eyeClosedIcon); // Biểu tượng đóng
            } else {
                txtPassword.setEchoChar((char) 0);
                buttonToggleVisibility.setIcon(eyeOpenIcon); // Biểu tượng mở
            }
        });

        // nut dang nhap
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton buttonLogin = new JButton("Đăng nhập");
        buttonLogin.setFocusPainted(false); // Bỏ viền khi được chọn
        loginPanel.add(buttonLogin, gbc);
        // nut quen mat khau
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton buttonForgetPassword = new JButton("Quên mật khẩu");
        buttonForgetPassword.setFocusPainted(false); // Bỏ viền khi được chọn
        buttonForgetPassword.setBorderPainted(false); // Bỏ viền nút
        buttonForgetPassword.setContentAreaFilled(false); // Bỏ nền nút
        buttonForgetPassword.setForeground(Color.BLUE); // Đặt màu chữ
        loginPanel.add(buttonForgetPassword, gbc);
        // ---------------- xu li dang nhap ----------------
        String tkAdmin = "phatkill";
        String mkAdmin = "phatofbug";
        buttonLogin.addActionListener(e -> {
            String userName = txtuserName.getText();
            String password = txtPassword.getText();
            Query query = new Query();
            if (userName.equals(tkAdmin) && password.equals(mkAdmin)) {
                JOptionPane.showMessageDialog(null, "Login Success");
                new Home();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed");
            }
        });
        // ---------------- xu li quen mat khau ----------------
        buttonForgetPassword.addActionListener(e -> {
            // Lấy tên tài khoản từ người dùng
            String userName = JOptionPane.showInputDialog(this, "Nhập tên tài khoản của bạn:");
            if (userName != null && !userName.trim().isEmpty()) {
                if (userName.equals(tkAdmin)) { // Kiểm tra nếu tài khoản là admin
                    String subject = "Mật khẩu Admin";
                    String body = "Mật khẩu hiện tại của admin là: " + mkAdmin;
                    SendEmail.sendEmail("clonevclone05@gmail.com", subject, body); // Gửi email cho bạn
                    // Thông báo người dùng
                    JOptionPane.showMessageDialog(this, "Đã gửi mật khẩu admin đến email của bạn!");
                } else {
                    JOptionPane.showMessageDialog(this, "Bạn không phải là admin!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tài khoản hợp lệ!");
            }
        });
    }
}
