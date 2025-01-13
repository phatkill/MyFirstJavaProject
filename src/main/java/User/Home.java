package User;

import AdminHome.JDBCConnect;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;

public class Home extends JFrame {

    // Khai báo các thuộc tính để lưu trữ thông tin cá nhân
    private String MAHS, HODEM, TEN, GIOITINH, NOISINHSONG, DIACHI, SODIENTHOAI, EMAIL;
    private float DIEM_TOAN, DIEM_VAN, DIEM_ANH, DIEM_VAT_LY_OR_LICH_SU, DIEM_HOAHOC, DIEM_SINHHOC;
    private Date NGAYSINH;

    // Câu lệnh truy vấn để lấy thông tin của học sinh từ cơ sở dữ liệu
    private String selectAllPupil = "SELECT * \n" +
            "FROM HOCSINH \n" +
            "JOIN DIEM \n" +
            "ON HOCSINH.MAHS = DIEM.MAHS WHERE  HOCSINH.MAHS = ?";

    public Home(String MAHS) {
        // Cấu hình cho JFrame
        setTitle("Thông tin cá nhân");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thông tin cá nhân", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        add(titleLabel, BorderLayout.NORTH);

        // Panel chứa thông tin cá nhân
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(infoPanel, BorderLayout.CENTER);

        // Cấu hình GridBagConstraints để điều chỉnh vị trí các thành phần trong panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Lấy thông tin cá nhân từ cơ sở dữ liệu và hiển thị lên giao diện
        try {
            Connection connection = JDBCConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllPupil);
            preparedStatement.setString(1, MAHS);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Gán giá trị từ ResultSet vào các thuộc tính của đối tượng
                this.MAHS = resultSet.getString("MAHS");
                this.HODEM = resultSet.getString("HODEM");
                this.TEN = resultSet.getString("TEN");
                this.NGAYSINH = resultSet.getDate("NGAYSINH");
                this.GIOITINH = resultSet.getString("GIOITINH");
                this.NOISINHSONG = resultSet.getString("NOISINHSONG");
                this.DIACHI = resultSet.getString("DIACHI");
                this.SODIENTHOAI = resultSet.getString("SODIENTHOAI");
                this.EMAIL = resultSet.getString("EMAIL");
                this.DIEM_TOAN = resultSet.getFloat("DIEM_TOAN");
                this.DIEM_VAN = resultSet.getFloat("DIEM_VAN");
                this.DIEM_ANH = resultSet.getFloat("DIEM_ANH");
                this.DIEM_VAT_LY_OR_LICH_SU = resultSet.getFloat("DIEM_VAT_LY_OR_LICH_SU");
                this.DIEM_HOAHOC = resultSet.getFloat("DIEM_HOA_HOC_OR_DIA_LY");
                this.DIEM_SINHHOC = resultSet.getFloat("DIEM_SINH_HOC_OR_GDCD");

                // Gọi phương thức để hiển thị các thông tin lên giao diện
                addLabelAndValue(infoPanel, gbc, 0, "Mã HS:", this.MAHS);
                addLabelAndValue(infoPanel, gbc, 1, "Họ đệm:", this.HODEM);
                addLabelAndValue(infoPanel, gbc, 2, "Tên:", this.TEN);
                addLabelAndValue(infoPanel, gbc, 3, "Ngày sinh:", this.NGAYSINH.toString());
                addLabelAndValue(infoPanel, gbc, 4, "Giới tính:", this.GIOITINH);
                addLabelAndValue(infoPanel, gbc, 5, "Nơi sinh:", this.NOISINHSONG);
                addLabelAndValue(infoPanel, gbc, 6, "Địa chỉ:", this.DIACHI);
                addLabelAndValue(infoPanel, gbc, 7, "Số điện thoại:", this.SODIENTHOAI);
                addLabelAndValue(infoPanel, gbc, 8, "Email:", this.EMAIL);
                addLabelAndValue(infoPanel, gbc, 9, "Điểm Toán:", Float.toString(this.DIEM_TOAN));
                addLabelAndValue(infoPanel, gbc, 10, "Điểm Văn:", Float.toString(this.DIEM_VAN));
                addLabelAndValue(infoPanel, gbc, 11, "Điểm Anh:", Float.toString(this.DIEM_ANH));
                addLabelAndValue(infoPanel, gbc, 12, "Điểm Vật Lý:", Float.toString(this.DIEM_VAT_LY_OR_LICH_SU));
                addLabelAndValue(infoPanel, gbc, 13, "Điểm Hóa Học:", Float.toString(this.DIEM_HOAHOC));
                addLabelAndValue(infoPanel, gbc, 14, "Điểm Sinh Học:", Float.toString(this.DIEM_SINHHOC));

            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin cá nhân với mã học sinh này: " + MAHS);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Hiển thị JFrame
        setVisible(true);
    }

    // Phương thức giúp thêm nhãn và giá trị vào panel
    private void addLabelAndValue(JPanel panel, GridBagConstraints gbc, int yPos, String labelText, String valueText) {
        gbc.gridx = 0;
        gbc.gridy = yPos;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        panel.add(new JLabel(valueText), gbc);
    }

}