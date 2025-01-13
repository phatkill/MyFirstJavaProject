package Login;

import javax.swing.*;
import java.awt.*;

public class LoginPupil extends JFrame {
    public LoginPupil(){
        this.init();
        this.setVisible(true);
    }
    public void init(){
        setTitle("Login Pupil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
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
        gbc.gridx = 2;
        JButton buttonToggleVisibility = new JButton("👁️");
//        buttonToggleVisibility.setPreferredSize(new Dimension(100, 30)); // Kích thước vừa với trường
        buttonToggleVisibility.setFocusPainted(false);
        buttonToggleVisibility.setContentAreaFilled(false);
        loginPanel.add(buttonToggleVisibility, gbc);
        /// xu li con mat
        buttonToggleVisibility.addActionListener(e -> {
            if (txtPassword.getEchoChar() == 0) {
                txtPassword.setEchoChar('\u2022');
                buttonToggleVisibility.setText("🔒"); // Biểu tượng đóng
            } else {
                txtPassword.setEchoChar((char) 0);
                buttonToggleVisibility.setText("👁️"); // Biểu tượng mở
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
        /// nut bam dang nhap
        buttonLogin.addActionListener(e->{
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
