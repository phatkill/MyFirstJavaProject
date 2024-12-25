package Login;

import Home.AdminHome;
import Swing.*;
import javax.swing.*;
//import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAdminView extends JFrame {
    private JLabel lbImageGraduationTHPT;
    private JLabel lbSdtHotline;
    private JPanel panelSelect;
    private Image ScalledImage;
    private JPanel loginView;
    private JLabel user, password;
    private JTextField userField;
    private JPasswordField passwordField;
    private Font font;
    private JButton togglePasswordButton;
    private boolean showPassword = true;
    private JButton loginButton;
    private JButton forgotPassword;
    public LoginAdminView() {
        this.init();
        this.setVisible(true);
    }
    private void init() {
        this.setTitle("User Or Admin");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLayout(null);
        getContentPane().setBackground(new Color(50, 120, 180)); // Màu xanh lam nhạt

        /// ------------------------Add Title bogiaoducva daotao--------------------------------------
        MainTitleTHPT mainTitleTHPT = new MainTitleTHPT();
        JLabel lbBoGiaoDucVaDaoTao = mainTitleTHPT.getLblTitleBoGiaoDucVaDaoTao();
        JLabel lbTHPT = mainTitleTHPT.getJblKiThiTotNghiepTHPT();
        lbBoGiaoDucVaDaoTao.setBounds(430, 10, 400, 100);
        lbTHPT.setBounds(430, 21, 400, 150);
        add(lbBoGiaoDucVaDaoTao);
        add(lbTHPT);
        /// ------------------------Add Contact Us-----------------------------------------------------
        ContactUs contactUs = new ContactUs();
        JLabel hotline = contactUs.getLbHotLine();
        JLabel sdt = contactUs.getSdt();
        hotline.setBounds(1000, 10, 700, 100);
        sdt.setBounds(1000, 21, 700, 150);
        add(hotline);
        add(sdt);


        //-------------------------- Add Panel --------------------------------
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(null);
        panelLogin.setBounds(310, 200, 900, 600);
        panelLogin.setBackground(new Color(255, 255, 0, 119));  // Màu vàng (Yellow)

//         Tạo Border bo tròn
//         panelLogin.setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20, new Color(19, 14, 14, 148)));

        add(panelLogin);

        //---------------Add image THPT----------------------
        ImageIcon graduationImageInitial = new ImageIcon(getClass().getResource("/Nón tốt nghiệp thpt.jpg"));
        ScalledImage = graduationImageInitial.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon ScalledImageIcon = new ImageIcon(ScalledImage);
        lbImageGraduationTHPT = new JLabel(ScalledImageIcon);
        lbImageGraduationTHPT.setBounds(310, 25, ScalledImageIcon.getIconWidth(), ScalledImageIcon.getIconHeight());
        add(lbImageGraduationTHPT);

        //--------------------Add HOTLINE and SDT-------------------------
        ImageIcon sdtHotline = new ImageIcon(getClass().getResource("/Số điện thoại HOTLINE.png"));
        ScalledImage = sdtHotline.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon sdtImageIcon = new ImageIcon(ScalledImage);
        lbSdtHotline = new JLabel(sdtImageIcon);
        lbSdtHotline.setBounds(900, 25, ScalledImageIcon.getIconWidth(), ScalledImageIcon.getIconHeight());
        add(lbSdtHotline);

        //--------------------------Create Form Login----------------------------
        //--------------------------Logo login-----------------------------------
        ImageIcon LoginLogo = new ImageIcon(getClass().getResource("/LoginLogo.png"));
        ScalledImage = LoginLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon LoginLogoIcon = new ImageIcon(ScalledImage);
        JLabel logo = new JLabel(LoginLogoIcon);
        logo.setBounds(380, 5, 150, 150);
        panelLogin.add(logo);

        //-------------------------Add login user and password----------------------
        user = new JLabel("User");
        password = new JLabel("Password");
        userField = new JTextField(10);
        passwordField = new JPasswordField(10);
        loginView = new JPanel();
        loginView.setLayout(null);
        user.setBounds(30, 200, 500, 50);
        password.setBounds(30, 300, 500, 50);
        userField.setBounds(170, 200, 600, 50);
        passwordField.setBounds(170, 300, 600, 50);
        user.setFont(new Font("TimeNewRoman", Font.BOLD, 32));
        password.setFont(new Font("Serif", Font.BOLD, 32));
        userField.setFont(new Font("Serif", Font.BOLD, 35));
        passwordField.setFont(new Font("Serif", Font.BOLD, 35));
        panelLogin.add(user);
        panelLogin.add(password);
        panelLogin.add(userField);
        panelLogin.add(passwordField);
//        //--------------------------Logo login-----------------------------------
//        ImageIcon LoginLogo = new ImageIcon(getClass().getResource("/LoginLogo.png"));
//        ScalledImage = LoginLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//        ImageIcon LoginLogoIcon = new ImageIcon(ScalledImage);
//        JLabel logo = new JLabel(LoginLogoIcon);
//        logo.setBounds(380, 5, 150, 150);
//        panelLogin.add(logo);
        //------------------------Button toggle eye ------------------------------
        ImageIcon eyeOpenImage = new ImageIcon(getClass().getResource("/eyeOpen.png"));
        ImageIcon eyeCloseImage = new ImageIcon(getClass().getResource("/eyeClose.png"));

        Image scaledEyeOpenImage = eyeOpenImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Image scaledEyeCloseImage = eyeCloseImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        togglePasswordButton = new JButton(new ImageIcon(scaledEyeCloseImage));
        togglePasswordButton.setBounds(770, 300, 50, 50);
        togglePasswordButton.setFocusPainted(false); // Tắt hiệu ứng khi nút được chọn (focus)
        togglePasswordButton.setBorderPainted(false); // Tắt viền
        togglePasswordButton.setContentAreaFilled(false); // Tắt nền
        togglePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPassword = !showPassword;


                if (showPassword) {
                    passwordField.setEchoChar((char) 0); // Hiển thị mật khẩu
                    togglePasswordButton.setIcon(new ImageIcon(scaledEyeCloseImage));
                } else {
                    passwordField.setEchoChar('*'); // Ẩn mật khẩu
                    togglePasswordButton.setIcon(new ImageIcon(scaledEyeOpenImage));
                }
            }
        });
        panelLogin.add(togglePasswordButton);


        //---------------------------------Add button Login Button -------------------------
        loginButton = new JButton("Login");
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
//        loginButton.setContentAreaFilled(false);
        loginButton.setFont(new Font("Serif", Font.BOLD, 32));
        loginButton.setBounds(350, 500, 200, 50);
        panelLogin.add(loginButton);

        //---------------------------------Add button forgot Password-----------------------
        forgotPassword = new JButton("Quên mật khẩu?");
        forgotPassword.setFont(new Font("Serif", Font.BOLD, 32));
        forgotPassword.setBounds(200, 400, 500, 50);
        forgotPassword.setFocusPainted(false);
//        forgotPassword.setBorderPainted(false);
        forgotPassword.setContentAreaFilled(false);
        panelLogin.add(forgotPassword);
        //------ giao diện trang chủ user ------
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminHome();
            }
        });

    }

    public static void main(String[] args) {
        new LoginUserView();
    }
}
