package ModeSelection;

import javax.swing.*;

import Login.LoginAdminView;
import Login.LoginUserView;
import Swing.*;
//import Image.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserOrAdmin extends JFrame {

    private JLabel lbImageGraduationTHPT;
    private JLabel lbSdtHotline;
    private JButton buttonUser, buttonAdmin;
    Font font;
    public UserOrAdmin() {
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
        //---------------Add image THPT----------------------
        ImageIcon graduationImageInitial = new ImageIcon(getClass().getResource("/Nón tốt nghiệp thpt.jpg"));
        Image ScalledImage = graduationImageInitial.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
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

        //--------------------Add Button-------------------------
        buttonUser = new JButton("User");
        buttonAdmin = new JButton("Admin");
        buttonUser.setBounds(280, 450, 400, 200);
        buttonUser.setFont(new Font("Tahoma", Font.BOLD, 65));
        buttonUser.setForeground(Color.WHITE);
        buttonUser.setBackground(new Color(102, 180, 50));
        buttonUser.setFocusPainted(false); // Loại bỏ hiệu ứng focus
        buttonAdmin.setBounds(880, 450, 400, 200);
        buttonAdmin.setFont(new Font("Tahoma", Font.BOLD, 65));
        buttonAdmin.setForeground(Color.WHITE);
        buttonAdmin.setBackground(new Color(102, 180, 50));
        buttonAdmin.setFocusPainted(false);
        add(buttonUser);
        add(buttonAdmin);
        buttonUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginUserView();

            }
        });
        buttonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginAdminView();
            }
        });
    }
    public static void main(String[] args) {
        new UserOrAdmin();
    }
}
