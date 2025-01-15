package Login;

import javax.swing.*;
import java.awt.*;

public class selectUserorAdmin extends JFrame {
    public selectUserorAdmin() {
        this.init();
        this.setVisible(true);
    }

    public void init() {
        setTitle("Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel title = new JLabel("Vui lòng chọn", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10)); // Khoảng cách giữa tiêu đề và các thành phần khác
        add(title, BorderLayout.NORTH);

        JPanel panelHome = new JPanel(new GridBagLayout());
        panelHome.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 100, 20, 40); // Khoảng cách thụt vào từ các cạnh trái và phải
        gbc.fill = GridBagConstraints.HORIZONTAL; // Kéo dài nút theo chiều ngang

        // Tạo nút "người dùng"
        JButton buttonPupil = new JButton("Người dùng");
        buttonPupil.setFont(new Font("Arial", Font.BOLD, 40));
        buttonPupil.setBackground(Color.GREEN);
        buttonPupil.setPreferredSize(new Dimension(400, 300)); // Kích thước nút
        buttonPupil.setFocusPainted(false);
        buttonPupil.addActionListener(e -> {
            new LoginPupil();
            dispose();
        });
        // Tạo nút "admin"
        JButton buttonAdmin = new JButton("Admin");
        buttonAdmin.setFont(new Font("Arial", Font.BOLD, 40));
        buttonAdmin.setBackground(Color.CYAN);
        buttonAdmin.setPreferredSize(new Dimension(400, 300)); // Kích thước nút
        buttonAdmin.setFocusPainted(false);
        buttonAdmin.addActionListener(e -> {
            new LoginAdmin();
            dispose();
        });
        // Thêm các nút vào panel
        gbc.gridx = 0; // Cột 1
        gbc.gridy = 0; // Hàng 1
        panelHome.add(buttonPupil, gbc);
        gbc.gridx = 1; // Nút admin xuống hàng dưới
        panelHome.add(buttonAdmin, gbc);
        // Thêm panel vào JFrame
        add(panelHome, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new selectUserorAdmin();
    }
}
