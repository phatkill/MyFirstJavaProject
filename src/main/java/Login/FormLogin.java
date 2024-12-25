package Login;

import javax.swing.*;
import java.awt.*;

public class FormLogin extends JFrame{
    private JPanel loginView;
    private JLabel user, password;
    private JTextField userField, passwordField;
    private Font font;
    public FormLogin() {
        user = new JLabel("User");
        password = new JLabel("Password");
        userField = new JTextField(10);
        passwordField = new JTextField(10);
        loginView = new JPanel();
        loginView.setLayout(null);
        user.setBounds(200, 45, 500, 100);
        password.setBounds(200, 150, 500, 100);
        userField.setBounds(600, 50, 500, 60);
        passwordField.setBounds(600, 180, 500, 60);
        user.setFont(new Font("TimeNewRoman", Font.BOLD, 60));
        password.setFont(new Font("Serif", Font.BOLD, 60));
        userField.setFont(new Font("Serif", Font.BOLD, 60));
        passwordField.setFont(new Font("Serif", Font.BOLD, 60));
        loginView.add(user);
        loginView.add(password);
        loginView.add(userField);
        loginView.add(passwordField);
        this.add(loginView);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1800, 300);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //-----------Create form login --------------------
    }

    public static void main(String[] args) {
        new FormLogin();
    }
}
