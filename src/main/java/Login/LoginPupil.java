package Login;

import javax.swing.*;
import java.awt.*;

public class LoginPupil extends JFrame {
    public LoginPupil() {
        this.init();
        this.setVisible(true);
    }

    public void init() {
        setTitle("Login Pupil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setResizable(false);
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
        txtuserName.setFont(new Font("Arial", Font.BOLD, 20));
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

        //--------------- con mat ----------------
        // con mắt
        gbc.gridx = 2;
        // Tạo các ImageIcon từ tài nguyên
        ImageIcon eyeOpen = new ImageIcon(
                new ImageIcon(getClass().getResource("/eyeCLose.png"))
                        .getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH) // Điều chỉnh kích thước tại đây
        );
        ImageIcon eyeClose = new ImageIcon(
                new ImageIcon(getClass().getResource("/eyeOpen.png"))
                        .getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH) // Điều chỉnh kích thước tại đây
        );
        JButton buttonToggleVisibility = new JButton(eyeOpen);
        buttonToggleVisibility.setFocusPainted(false);  // Bỏ viền khi nút có tiêu điểm
        buttonToggleVisibility.setContentAreaFilled(false);  // Bỏ nền cho nút
        buttonToggleVisibility.setBorderPainted(false);  // Bỏ viền của nút
        loginPanel.add(buttonToggleVisibility, gbc);

        buttonToggleVisibility.addActionListener(e -> {
            if (txtPassword.getEchoChar() == 0) {
                txtPassword.setEchoChar('\u2022');  // Đặt lại mật khẩu có dấu chấm
                buttonToggleVisibility.setIcon(eyeOpen);  // Sử dụng eyeOpen icon
            } else {
                txtPassword.setEchoChar((char) 0);  // Mật khẩu không có dấu chấm
                buttonToggleVisibility.setIcon(eyeClose);  // Sử dụng eyeClose icon
            }
        });

        // nut dang nhap
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton buttonLogin = new JButton("Đăng nhập");
        buttonLogin.setFont(new Font("Aria", Font.BOLD, 20));
        buttonLogin.setFocusPainted(false); // Bỏ viền khi được chọn
        loginPanel.add(buttonLogin, gbc);
        // nut quen mat khau
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton buttonForgetPassword = new JButton("Quên mật khẩu");
        buttonForgetPassword.setFont(new Font("Aria", Font.BOLD, 20));
        buttonForgetPassword.setFocusPainted(false); // Bỏ viền khi được chọn
        buttonForgetPassword.setBorderPainted(false); // Bỏ viền nút
        buttonForgetPassword.setContentAreaFilled(false); // Bỏ nền nút
        buttonForgetPassword.setForeground(Color.BLUE); // Đặt màu chữ
        loginPanel.add(buttonForgetPassword, gbc);
        /// nut bam dang nhap
        buttonLogin.addActionListener(e -> {
            String userName = txtuserName.getText();
            String password = txtPassword.getText();
            Query query = new Query();
            query.LoginUser(userName, password);
        });
    }
    public static void main(String[] args) {
        new LoginPupil();
    }
}
