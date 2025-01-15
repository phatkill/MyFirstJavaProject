package AdminHome;

import Model.ButtonEditor;
import Model.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.JOptionPane;


public class Query {
    // --------------------------------------------------------Đây là của SQL -------------------------------------------------------------------

    // sort pupil information
    private String sortAllPupils = "SELECT * \n" +
            "FROM HOCSINH \n" +
            "JOIN DIEM \n" +
            "ON HOCSINH.MAHS = DIEM.MAHS \n" +
            "ORDER BY TEN ASC;";
    // Update pupil information
    private String updatePupilandScore = "UPDATE HOCSINH \n" +
            "SET HODEM = ?, TEN = ?, NGAYSINH = ?, GIOITINH = ?, NOISINHSONG = ?, DIACHI = ?, SODIENTHOAI = ?, EMAIL = ? \n" +
            "WHERE MAHS = ?;" +
            "UPDATE DIEM \n" +
            "SET DIEM_TOAN = ?, DIEM_VAN = ?, DIEM_ANH = ?, DIEM_VAT_LY_OR_LICH_SU = ?, DIEM_HOA_HOC_OR_DIA_LY = ?, DIEM_SINH_HOC_OR_GDCD = ? \n" +
            "WHERE MAHS = ?;";
    // print out pupil information
    private String selectAllPupil = "SELECT * \n" +
            "FROM HOCSINH \n" +
            "JOIN DIEM \n" +
            "ON HOCSINH.MAHS = DIEM.MAHS;";

    // add Pupil and Score
    private String insertPupil = "INSERT INTO HOCSINH (MAHS, HODEM, TEN, NGAYSINH, GIOITINH, NOISINHSONG, DIACHI, SODIENTHOAI, EMAIL)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String insertScore = "INSERT INTO DIEM (MAHS, DIEM_TOAN, DIEM_VAN, DIEM_ANH, DIEM_VAT_LY_OR_LICH_SU, DIEM_HOA_HOC_OR_DIA_LY, DIEM_SINH_HOC_OR_GDCD)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private String insertUser = "INSERT INTO TAIKHOAN (MAHS, CCCD, password) VALUES (?, ?, ?)";
    // delete all pupil information and grades based on MAHS
    private String deleteSelectPupils =
            "DELETE TAIKHOAN FROM TAIKHOAN" +
            " JOIN HOCSINH ON HOCSINH.MAHS = DIEM.MAHS" +
            "DELETE DIEM FROM DIEM " +
            "JOIN HOCSINH ON HOCSINH.MAHS = DIEM.MAHS " +
            "WHERE DIEM.MAHS = ?; " +
            "DELETE HOCSINH " +
            "FROM HOCSINH " +
            "WHERE HOCSINH.MAHS = ?;";
    // check idPupil ?
    private String checkIdPupil = "SELECT COUNT(*) FROM HOCSINH WHERE MAHS = ?";

    // check userName, password for user
    //------------------------------------ đây là các biến bình thường --------------------------------

    // ----------------------------------- search pupil ----------------------------------------------
    private String searchPupil = "SELECT * \n" +
            "FROM HOCSINH \n" +
            "JOIN DIEM \n" +
            "ON HOCSINH.MAHS = DIEM.MAHS \n" +
            "WHERE HOCSINH.TEN= ?;";

    private String MAHS, HODEM, TEN, GIOITINH, NOISINHSONG, DIACHI, SODIENTHOAI, EMAIL;
    private float DIEM_TOAN, DIEM_VAN, DIEM_ANH, DIEM_VATLI_OR_LICHSU, DIEM_HOAHOC_OR_DIALI, DIEM_SINHHOC_OR_GDCD;
    private Date NGAYSINH;

    private JLabel lblMaHS, lblHoDem, lblTen, lblNgaySinh, lblGioiTinh, lblNoiSinhSong,
            lblDiaChi, lblSoDienThoai, lblEmail, lblDiemToan, lblDiemVan, lblDiemAnh,
            lblDiemVatLyOrLichSu, lblDiemHoaHocOrDiaLy, lblDiemSinhHocOrGDCD, lblCccd, lblPassword;
    private JTextField txtMaHS, txtHoDem, txtTen, txtNoiSinhSong, txtDiaChi,
            txtSoDienThoai, txtEmail, txtDiemToan, txtDiemVan, txtDiemAnh,
            txtDiemVatLyOrLichSu, txtDiemHoaHocOrDiaLy, txtDiemSinhHocOrGDCD, txtCccd, txtPassword;
    private JComboBox<String> cboGioiTinh;
    private DateSelectorPanel dateSelectorPanel;
    private Font font = new Font("Arial", Font.PLAIN, 20);

    public void showAllPupil(JTable table) {

        // -------------------------add edit button ----------------------
        try (Connection con = JDBCConnect.getConnection()) {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return (column == 16 || column == 17);
                }
                @Override
                public Class<?> getColumnClass(int column) {
                    return column == 16 ? Boolean.class : (column == 17 ? JButton.class : Object.class); // Cột 16 là cột checkbox
                }
            };
            model.addColumn("STT");
            model.addColumn("Mã học sinh");
            model.addColumn("Họ đệm");
            model.addColumn("Tên");
            model.addColumn("Ngày sinh");
            model.addColumn("Giới tính");
            model.addColumn("Nơi ở");
            model.addColumn("Địa chỉ");
            model.addColumn("Số điện thoại");
            model.addColumn("Email");
            model.addColumn("Điểm toán");
            model.addColumn("Điểm văn");
            model.addColumn("Điểm anh");
            model.addColumn("điểm vật lí/lịch sử");
            model.addColumn("điểm hóa/địa");
            model.addColumn("điểm môn sinh/GDCD");
            model.addColumn("Chọn");
            model.addColumn("Chỉnh Sửa");
            ButtonRenderer editPupil = new ButtonRenderer();
            ButtonEditor editPupilButton = new ButtonEditor(new JCheckBox());
            table.setModel(model);
            int stt = 1;
            ResultSet rs = con.createStatement().executeQuery(selectAllPupil);
            while (rs.next()) {
                MAHS = rs.getString("MAHS");
                HODEM = rs.getString("HODEM");
                TEN = rs.getString("TEN");
                NGAYSINH = rs.getDate("NGAYSINH");
                GIOITINH = rs.getString("GIOITINH");
                NOISINHSONG = rs.getString("NOISINHSONG");
                DIACHI = rs.getString("DIACHI");
                SODIENTHOAI = rs.getString("SODIENTHOAI");
                EMAIL = rs.getString("EMAIL");
                DIEM_TOAN = rs.getFloat("DIEM_TOAN");
                DIEM_VAN = rs.getFloat("DIEM_VAN");
                DIEM_ANH = rs.getFloat("DIEM_ANH");
                DIEM_VATLI_OR_LICHSU = rs.getFloat("DIEM_VAT_LY_OR_LICH_SU");
                DIEM_HOAHOC_OR_DIALI = rs.getFloat("DIEM_HOA_HOC_OR_DIA_LY");
                DIEM_SINHHOC_OR_GDCD = rs.getFloat("DIEM_SINH_HOC_OR_GDCD");
                model.addRow(new Object[]{stt, MAHS, HODEM, TEN, NGAYSINH, GIOITINH, NOISINHSONG, DIACHI, SODIENTHOAI, EMAIL, DIEM_TOAN, DIEM_VAN, DIEM_ANH, DIEM_VATLI_OR_LICHSU, DIEM_HOAHOC_OR_DIALI, DIEM_SINHHOC_OR_GDCD, false});
                table.getColumnModel().getColumn(17).setCellRenderer(editPupil);
                table.getColumnModel().getColumn(17).setCellEditor(editPupilButton);
                stt++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void setSortAllPupils(JTable table) {

        // -------------------------add edit button ----------------------
        try (Connection con = JDBCConnect.getConnection()) {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return (column == 16 || column == 17);
                }
                @Override
                public Class<?> getColumnClass(int column) {
                    return column == 16 ? Boolean.class : (column == 17 ? JButton.class : Object.class); // Cột 16 là cột checkbox
                }
            };
            model.addColumn("STT");
            model.addColumn("Mã học sinh");
            model.addColumn("Họ đệm");
            model.addColumn("Tên");
            model.addColumn("Ngày sinh");
            model.addColumn("Giới tính");
            model.addColumn("Nơi ở");
            model.addColumn("Địa chỉ");
            model.addColumn("Số điện thoại");
            model.addColumn("Email");
            model.addColumn("Điểm toán");
            model.addColumn("Điểm văn");
            model.addColumn("Điểm anh");
            model.addColumn("điểm vật lí/lịch sử");
            model.addColumn("điểm hóa/địa");
            model.addColumn("điểm môn sinh/GDCD");
            model.addColumn("Chọn");
            model.addColumn("Chỉnh Sửa");
            ButtonRenderer editPupil = new ButtonRenderer();
            ButtonEditor editPupilButton = new ButtonEditor(new JCheckBox());
            table.setModel(model);
            int stt = 1;
            ResultSet rs = con.createStatement().executeQuery(sortAllPupils);
            while (rs.next()) {
                MAHS = rs.getString("MAHS");
                HODEM = rs.getString("HODEM");
                TEN = rs.getString("TEN");
                NGAYSINH = rs.getDate("NGAYSINH");
                GIOITINH = rs.getString("GIOITINH");
                NOISINHSONG = rs.getString("NOISINHSONG");
                DIACHI = rs.getString("DIACHI");
                SODIENTHOAI = rs.getString("SODIENTHOAI");
                EMAIL = rs.getString("EMAIL");
                DIEM_TOAN = rs.getFloat("DIEM_TOAN");
                DIEM_VAN = rs.getFloat("DIEM_VAN");
                DIEM_ANH = rs.getFloat("DIEM_ANH");
                DIEM_VATLI_OR_LICHSU = rs.getFloat("DIEM_VAT_LY_OR_LICH_SU");
                DIEM_HOAHOC_OR_DIALI = rs.getFloat("DIEM_HOA_HOC_OR_DIA_LY");
                DIEM_SINHHOC_OR_GDCD = rs.getFloat("DIEM_SINH_HOC_OR_GDCD");
                model.addRow(new Object[]{stt, MAHS, HODEM, TEN, NGAYSINH, GIOITINH, NOISINHSONG, DIACHI, SODIENTHOAI, EMAIL, DIEM_TOAN, DIEM_VAN, DIEM_ANH, DIEM_VATLI_OR_LICHSU, DIEM_HOAHOC_OR_DIALI, DIEM_SINHHOC_OR_GDCD, false});
                table.getColumnModel().getColumn(17).setCellRenderer(editPupil);
                table.getColumnModel().getColumn(17).setCellEditor(editPupilButton);
                stt++;
            }
//            JOptionPane.showMessageDialog(n);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteSelectPupil(JTable table) {
        int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Kiểm tra", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try (Connection con = JDBCConnect.getConnection()) {
                int dem = 0;
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                for (int i = model.getRowCount() - 1; i >= 0; i--) {
                    Boolean isSelected = (Boolean) model.getValueAt(i, 16);
                    if (isSelected) {
                        String maSVNeedToDelete = (String) model.getValueAt(i, 1);
                        try (PreparedStatement pst = con.prepareStatement(deleteSelectPupils)) {
                            pst.setString(1, maSVNeedToDelete);
                            pst.setString(2, maSVNeedToDelete);
                            pst.executeUpdate();
                        }
                        model.removeRow(i);
                        dem++;
                    }
                }
                for (int i = 0; i < model.getRowCount(); i++) {
                    model.setValueAt(i + 1, i, 0);
                }
                if (dem > 0) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Bạn đã chọn ai để xóa đâu?");
                    return;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addFormatPupil(JTable table) {
        /// interface enter add
        JDialog dialog = new JDialog();
        dialog.setLayout(new GridBagLayout());
        dialog.setTitle("Thêm học sinh");
        dialog.setVisible(true);
        dialog.setSize(700, 800);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        lblMaHS = new JLabel("Mã SV");
        lblMaHS.setFont(font);
        txtMaHS = new JTextField(20);
        txtMaHS.setFont(font);
        c.gridx = 0;
        c.gridy = 0;
        dialog.add(lblMaHS, c);
        c.gridx = 1;
        c.gridy = 0;
        dialog.add(txtMaHS, c);
        lblHoDem = new JLabel("Họ đệm");
        lblHoDem.setFont(font);
        txtHoDem = new JTextField(20);
        txtHoDem.setFont(font);
        c.gridx = 0;
        c.gridy = 1;
        dialog.add(lblHoDem, c);
        c.gridx = 1;
        c.gridy = 1;
        dialog.add(txtHoDem, c);
        lblTen = new JLabel("Tên");
        lblTen.setFont(font);
        txtTen = new JTextField(20);
        txtTen.setFont(font);
        c.gridx = 0;
        c.gridy = 2;
        dialog.add(lblTen, c);
        c.gridx = 1;
        c.gridy = 2;
        dialog.add(txtTen, c);
        lblNgaySinh = new JLabel("Ngày Sinh");
        lblNgaySinh.setFont(font);
        dateSelectorPanel = new DateSelectorPanel();
        c.gridx = 0;
        c.gridy = 3;
        dialog.add(lblNgaySinh, c);
        c.gridx = 1;
        c.gridy = 3;
        dialog.add(dateSelectorPanel, c);
        lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setFont(font);
        cboGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        c.gridx = 0;
        c.gridy = 4;
        dialog.add(lblGioiTinh, c);
        c.gridx = 1;
        c.gridy = 4;
        dialog.add(cboGioiTinh, c);
        lblNoiSinhSong = new JLabel("Nơi sinh sống");
        lblNoiSinhSong.setFont(font);
        txtNoiSinhSong = new JTextField(20);
        txtNoiSinhSong.setFont(font);
        c.gridx = 0;
        c.gridy = 5;
        dialog.add(lblNoiSinhSong, c);
        c.gridx = 1;
        c.gridy = 5;
        dialog.add(txtNoiSinhSong, c);
        lblDiaChi = new JLabel("Địa chỉ");
        lblDiaChi.setFont(font);
        txtDiaChi = new JTextField(20);
        txtDiaChi.setFont(font);
        c.gridx = 0;
        c.gridy = 6;
        dialog.add(lblDiaChi, c);
        c.gridx = 1;
        c.gridy = 6;
        dialog.add(txtDiaChi, c);
        lblSoDienThoai = new JLabel("Số điện thoại");
        lblSoDienThoai.setFont(font);
        txtSoDienThoai = new JTextField(20);
        txtSoDienThoai.setFont(font);
        c.gridx = 0;
        c.gridy = 7;
        dialog.add(lblSoDienThoai, c);
        c.gridx = 1;
        c.gridy = 7;
        dialog.add(txtSoDienThoai, c);
        lblEmail = new JLabel("Email");
        lblEmail.setFont(font);
        txtEmail = new JTextField(20);
        txtEmail.setFont(font);
        c.gridx = 0;
        c.gridy = 8;
        dialog.add(lblEmail, c);
        c.gridx = 1;
        c.gridy = 8;
        dialog.add(txtEmail, c);
        lblDiemToan = new JLabel("Điểm toán");
        lblDiemToan.setFont(font);
        txtDiemToan = new JTextField(20);
        txtDiemToan.setFont(font);
        c.gridx = 0;
        c.gridy = 9;
        dialog.add(lblDiemToan, c);
        c.gridx = 1;
        c.gridy = 9;
        dialog.add(txtDiemToan, c);
        lblDiemVan = new JLabel("Điểm văn");
        lblDiemVan.setFont(font);
        txtDiemVan = new JTextField(20);
        txtDiemVan.setFont(font);
        c.gridx = 0;
        c.gridy = 10;
        dialog.add(lblDiemVan, c);
        c.gridx = 1;
        c.gridy = 10;
        dialog.add(txtDiemVan, c);
        lblDiemAnh = new JLabel("Điểm anh");
        lblDiemAnh.setFont(font);
        txtDiemAnh = new JTextField(20);
        txtDiemAnh.setFont(font);
        c.gridx = 0;
        c.gridy = 11;
        dialog.add(lblDiemAnh, c);
        c.gridx = 1;
        c.gridy = 11;
        dialog.add(txtDiemAnh, c);
        lblDiemVatLyOrLichSu = new JLabel("Điểm vật lí/ lịch sử");
        lblDiemVatLyOrLichSu.setFont(font);
        txtDiemVatLyOrLichSu = new JTextField(20);
        txtDiemVatLyOrLichSu.setFont(font);
        c.gridx = 0;
        c.gridy = 12;
        dialog.add(lblDiemVatLyOrLichSu, c);
        c.gridx = 1;
        c.gridy = 12;
        dialog.add(txtDiemVatLyOrLichSu, c);
        lblDiemHoaHocOrDiaLy = new JLabel("Điểm hóa học/ địa lí");
        lblDiemHoaHocOrDiaLy.setFont(font);
        txtDiemHoaHocOrDiaLy = new JTextField(20);
        txtDiemHoaHocOrDiaLy.setFont(font);
        c.gridx = 0;
        c.gridy = 13;
        dialog.add(lblDiemHoaHocOrDiaLy, c);
        c.gridx = 1;
        c.gridy = 13;
        dialog.add(txtDiemHoaHocOrDiaLy, c);
        lblDiemSinhHocOrGDCD = new JLabel("Điểm sinh học/ GDCD");
        lblDiemSinhHocOrGDCD.setFont(font);
        txtDiemSinhHocOrGDCD = new JTextField(20);
        txtDiemSinhHocOrGDCD.setFont(font);
        c.gridx = 0;
        c.gridy = 14;
        dialog.add(lblDiemSinhHocOrGDCD, c);
        c.gridx = 1;
        c.gridy = 14;
        dialog.add(txtDiemSinhHocOrGDCD, c);
        JButton btnSave = new JButton("Thêm");
        btnSave.setFont(font);
        c.gridx = 0;
        c.gridy = 15;
        lblCccd = new JLabel("cccd người dùng");
        lblCccd.setFont(font);
        dialog.add(lblCccd, c);
        c.gridx = 1;
        txtCccd = new JTextField(20);
        txtCccd.setFont(font);
        dialog.add(txtCccd, c);
        c.gridx = 0;
        c.gridy = 16;
        lblPassword = new JLabel("Mật khẩu");
        lblPassword.setFont(font);
        dialog.add(lblPassword, c);
        c.gridx = 1;
        txtPassword = new JTextField(20);
        txtPassword.setFont(font);
        dialog.add(txtPassword, c);
        txtCccd.setFont(font);
        c.gridx = 0;
        c.gridy = 17;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        dialog.add(btnSave, c);
        // handel
        btnSave.addActionListener(e -> {
            addPupil(table, dialog);
        });
    }

    public void formatUpdatePupil(JTable table, int row) {
        JDialog dialog = new JDialog();
        dialog.setLayout(new GridBagLayout());
        dialog.setTitle("Chỉnh sửa học sinh");
        dialog.setVisible(true);
        dialog.setSize(700, 800);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;

        lblMaHS = new JLabel("Mã SV");
        lblMaHS.setFont(font);
        txtMaHS = new JTextField(20);
        txtMaHS.setFont(font);
        txtMaHS.setText((String) table.getValueAt(row, 1));
        txtMaHS.setEditable(false);
        c.gridx = 0;
        c.gridy = 0;
        dialog.add(lblMaHS, c);
        c.gridx = 1;
        c.gridy = 0;
        dialog.add(txtMaHS, c);
        lblHoDem = new JLabel("Họ đệm");
        lblHoDem.setFont(font);
        txtHoDem = new JTextField(20);
        txtHoDem.setFont(font);
        txtHoDem.setText((String) table.getValueAt(row, 2));
        c.gridx = 0;
        c.gridy = 1;
        dialog.add(lblHoDem, c);
        c.gridx = 1;
        c.gridy = 1;
        dialog.add(txtHoDem, c);
        lblTen = new JLabel("Tên");
        lblTen.setFont(font);
        txtTen = new JTextField(20);
        txtTen.setFont(font);
        txtTen.setText((String) table.getValueAt(row, 3));
        c.gridx = 0;
        c.gridy = 2;
        dialog.add(lblTen, c);
        c.gridx = 1;
        c.gridy = 2;
        dialog.add(txtTen, c);
        lblNgaySinh = new JLabel("Ngày Sinh");
        lblNgaySinh.setFont(font);
        dateSelectorPanel = new DateSelectorPanel();
        c.gridx = 0;
        c.gridy = 3;
        dialog.add(lblNgaySinh, c);
        c.gridx = 1;
        c.gridy = 3;
        dialog.add(dateSelectorPanel, c);
        lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setFont(font);
        cboGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        c.gridx = 0;
        c.gridy = 4;
        dialog.add(lblGioiTinh, c);
        c.gridx = 1;
        c.gridy = 4;
        dialog.add(cboGioiTinh, c);
        lblNoiSinhSong = new JLabel("Nơi sinh sống");
        lblNoiSinhSong.setFont(font);
        txtNoiSinhSong = new JTextField(20);
        txtNoiSinhSong.setFont(font);
        txtNoiSinhSong.setText((String) table.getValueAt(row, 6));
        c.gridx = 0;
        c.gridy = 5;
        dialog.add(lblNoiSinhSong, c);
        c.gridx = 1;
        c.gridy = 5;
        dialog.add(txtNoiSinhSong, c);
        lblDiaChi = new JLabel("Địa chỉ");
        lblDiaChi.setFont(font);
        txtDiaChi = new JTextField(20);
        txtDiaChi.setFont(font);
        txtDiaChi.setText((String) table.getValueAt(row, 7));
        c.gridx = 0;
        c.gridy = 6;
        dialog.add(lblDiaChi, c);
        c.gridx = 1;
        c.gridy = 6;
        dialog.add(txtDiaChi, c);
        lblSoDienThoai = new JLabel("Số điện thoai");
        lblSoDienThoai.setFont(font);
        txtSoDienThoai = new JTextField(20);
        txtSoDienThoai.setFont(font);
        txtSoDienThoai.setText((String) table.getValueAt(row, 8));
        c.gridx = 0;
        c.gridy = 7;
        dialog.add(lblSoDienThoai, c);
        c.gridx = 1;
        c.gridy = 7;
        dialog.add(txtSoDienThoai, c);
        lblEmail = new JLabel("Email");
        lblEmail.setFont(font);
        txtEmail = new JTextField(20);
        txtEmail.setFont(font);
        txtEmail.setText((String) table.getValueAt(row, 9));
        c.gridx = 0;
        c.gridy = 8;
        dialog.add(lblEmail, c);
        c.gridx = 1;
        c.gridy = 8;
        dialog.add(txtEmail, c);
        c.gridx = 0;
        c.gridy = 9;
        lblDiemToan = new JLabel("Điểm toán");
        lblDiemToan.setFont(font);
        dialog.add(lblDiemToan, c);
        c.gridx = 1;
        c.gridy = 9;
        txtDiemToan = new JTextField(20);
        txtDiemToan.setText(table.getValueAt(row, 10).toString());
        txtDiemToan.setFont(font);
        dialog.add(txtDiemToan, c);
        lblDiemVan = new JLabel("Điểm văn");
        lblDiemVan.setFont(font);
        c.gridx = 0;
        c.gridy = 10;
        dialog.add(lblDiemVan, c);
        txtDiemVan = new JTextField(20);
        txtDiemVan.setFont(font);
        txtDiemVan.setText(table.getValueAt(row, 11).toString());
        c.gridx = 1;
        c.gridy = 10;
        dialog.add(txtDiemVan, c);
        lblDiemAnh = new JLabel("Điểm anh");
        lblDiemAnh.setFont(font);
        c.gridx = 0;
        c.gridy = 11;
        dialog.add(lblDiemAnh, c);
        txtDiemAnh = new JTextField(20);
        txtDiemAnh.setFont(font);
        txtDiemAnh.setText(table.getValueAt(row, 12).toString());
        c.gridx = 1;
        c.gridy = 11;
        dialog.add(txtDiemAnh, c);
        lblDiemVatLyOrLichSu = new JLabel("Điểm vật lí/ lịch sử");
        lblDiemVatLyOrLichSu.setFont(font);
        c.gridx = 0;
        c.gridy = 12;
        dialog.add(lblDiemVatLyOrLichSu, c);
        txtDiemVatLyOrLichSu = new JTextField(20);
        txtDiemVatLyOrLichSu.setFont(font);
        txtDiemVatLyOrLichSu.setText(table.getValueAt(row, 13).toString());
        c.gridx = 1;
        c.gridy = 12;
        dialog.add(txtDiemVatLyOrLichSu, c);
        lblDiemHoaHocOrDiaLy = new JLabel("Điểm hóa học/ địa lí");
        lblDiemHoaHocOrDiaLy.setFont(font);
        c.gridx = 0;
        c.gridy = 13;
        dialog.add(lblDiemHoaHocOrDiaLy, c);
        txtDiemHoaHocOrDiaLy = new JTextField(20);
        txtDiemHoaHocOrDiaLy.setFont(font);
        txtDiemHoaHocOrDiaLy.setText(table.getValueAt(row, 14).toString());
        c.gridx = 1;
        c.gridy = 13;
        dialog.add(txtDiemHoaHocOrDiaLy, c);
        lblDiemSinhHocOrGDCD = new JLabel("Điểm sinh học/ GDCD");
        lblDiemSinhHocOrGDCD.setFont(font);
        c.gridx = 0;
        c.gridy = 14;
        dialog.add(lblDiemSinhHocOrGDCD, c);
        txtDiemSinhHocOrGDCD = new JTextField(20);
        txtDiemSinhHocOrGDCD.setFont(font);
        txtDiemSinhHocOrGDCD.setText(table.getValueAt(row, 15).toString());
        c.gridx = 1;
        c.gridy = 14;
        dialog.add(txtDiemSinhHocOrGDCD, c);
        JButton btnSave = new JButton("Sửa");
        btnSave.setFont(font);
        c.gridx = 0;
        c.gridy = 15;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        dialog.add(btnSave, c);
//        btnSave.setContentAreaFilled(false);
        btnSave.setOpaque(false);
        btnSave.addActionListener(e -> {
            editPupil(table, dialog);
        });
    }

    public void addPupil(JTable table, JDialog dialog) {
        if (!checkValid(dialog)) return;
        try (Connection con = JDBCConnect.getConnection()) {
            try (PreparedStatement pst = con.prepareStatement(checkIdPupil)) {
                pst.setString(1, txtMaHS.getText());
                ResultSet rs = pst.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(dialog, "mã học sinh naỳ đã có rồi, vui lòng kiểm tra lại", "lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            try (PreparedStatement pst = con.prepareStatement(insertPupil)) {
                pst.setString(1, txtMaHS.getText());
                pst.setString(2, txtHoDem.getText());
                pst.setString(3, txtTen.getText());
                pst.setString(4, dateSelectorPanel.getDate().toString());
                pst.setString(5, cboGioiTinh.getSelectedItem().toString());
                pst.setString(6, txtNoiSinhSong.getText());
                pst.setString(7, txtDiaChi.getText());
                pst.setString(8, txtSoDienThoai.getText());
                pst.setString(9, txtEmail.getText());
                pst.executeUpdate();
            }
            try (PreparedStatement pstDiem = con.prepareStatement(insertScore)) {
//                pstDiem.setString(8,txtMaHS.getText());
                pstDiem.setString(1, txtMaHS.getText());
                pstDiem.setFloat(2, Float.parseFloat(txtDiemToan.getText()));
                pstDiem.setFloat(3, Float.parseFloat(txtDiemVan.getText()));
                pstDiem.setFloat(4, Float.parseFloat(txtDiemAnh.getText()));
                pstDiem.setFloat(5, Float.parseFloat(txtDiemVatLyOrLichSu.getText()));
                pstDiem.setFloat(6, Float.parseFloat(txtDiemHoaHocOrDiaLy.getText()));
                pstDiem.setFloat(7, Float.parseFloat(txtDiemSinhHocOrGDCD.getText()));
                pstDiem.executeUpdate();  // Thực thi câu lệnh chèn điểm
            }
            try (PreparedStatement pstTk = con.prepareStatement(insertUser)) {
                pstTk.setString(1, txtMaHS.getText());
                pstTk.setString(2, txtCccd.getText());
                pstTk.setString(3, txtPassword.getText());
                pstTk.executeUpdate();
            }
            JOptionPane.showMessageDialog(dialog, "Thêm thành công", " Chúc mừng", JOptionPane.INFORMATION_MESSAGE);
//            showAllPupil(table);
            dialog.dispose();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editPupil(JTable table, JDialog dialog) {
        if (!checkValid(dialog)) return;
        try (Connection con = JDBCConnect.getConnection()) {
            try (PreparedStatement pst = con.prepareStatement(updatePupilandScore)) {
                pst.setString(1, txtHoDem.getText());
                pst.setString(2, txtTen.getText());
                pst.setString(3, dateSelectorPanel.getDate().toString());
                pst.setString(4, cboGioiTinh.getSelectedItem().toString());
                pst.setString(5, txtNoiSinhSong.getText());
                pst.setString(6, txtDiaChi.getText());
                pst.setString(7, txtSoDienThoai.getText());
                pst.setString(8, txtEmail.getText());
                pst.setString(9, txtMaHS.getText());
                pst.setFloat(10, Float.parseFloat(txtDiemToan.getText()));
                pst.setFloat(11, Float.parseFloat(txtDiemVan.getText()));
                pst.setFloat(12, Float.parseFloat(txtDiemAnh.getText()));
                pst.setFloat(13, Float.parseFloat(txtDiemVatLyOrLichSu.getText()));
                pst.setFloat(14, Float.parseFloat(txtDiemHoaHocOrDiaLy.getText()));
                pst.setFloat(15, Float.parseFloat(txtDiemSinhHocOrGDCD.getText()));
                pst.setString(16, txtMaHS.getText());
                pst.executeUpdate();
            }
            JOptionPane.showMessageDialog(dialog, "Sửa thành công", " Chúc mừng", JOptionPane.INFORMATION_MESSAGE);
            showAllPupil(table);
            dialog.dispose();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean checkValid(JDialog dialog) {
        if (txtMaHS.getText().isEmpty() || txtHoDem.getText().isEmpty() || txtTen.getText().isEmpty() ||
                txtNoiSinhSong.getText().isEmpty() || txtDiaChi.getText().isEmpty() ||
                txtSoDienThoai.getText().isEmpty() || txtEmail.getText().isEmpty() ||
                txtDiemToan.getText().isEmpty() || txtDiemVan.getText().isEmpty() ||
                txtDiemAnh.getText().isEmpty() || txtDiemVatLyOrLichSu.getText().isEmpty() ||
                txtDiemHoaHocOrDiaLy.getText().isEmpty() || txtDiemSinhHocOrGDCD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            float diemToan = Float.parseFloat(txtDiemToan.getText().trim());
            float diemVan = Float.parseFloat(txtDiemVan.getText().trim());
            float diemVatLyOrLichSu = Float.parseFloat(txtDiemVatLyOrLichSu.getText().trim());
            float diemAnh = Float.parseFloat(txtDiemAnh.getText().trim());
            float diemHoaHocOrDiaLy = Float.parseFloat(txtDiemHoaHocOrDiaLy.getText().trim());
            float diemSinhHocOrGDCD = Float.parseFloat(txtDiemSinhHocOrGDCD.getText().trim());
            float minDiem = Math.min(
                    Math.min(Math.min(diemToan, diemVan), Math.min(diemVatLyOrLichSu, diemAnh)),
                    Math.min(diemHoaHocOrDiaLy, diemSinhHocOrGDCD)
            );
            float maxDiem = Math.max(
                    Math.max(Math.max(diemToan, diemVan), Math.max(diemVatLyOrLichSu, diemAnh)),
                    Math.max(diemHoaHocOrDiaLy, diemSinhHocOrGDCD)
            );
            if (minDiem < 0 || maxDiem > 10) {
                JOptionPane.showMessageDialog(dialog, "Điểm phải nằm trong khoảng 0 đến 10!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(dialog, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!txtSoDienThoai.getText().matches("[0-9]{10}")) {
            JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đúng 10 số ở mục số điện thoại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        LocalDate birthDate = dateSelectorPanel.getDate();
        LocalDate currentDate = LocalDate.now();
        if (Period.between(birthDate, currentDate).getYears() < 18) {
            JOptionPane.showMessageDialog(dialog, "Thí sinh này có đủ tuổi đâu mà thi?", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public void searchPupilFollowId(JTable table, String SearchMaHS) {
        try (Connection con = JDBCConnect.getConnection()) {
            try (PreparedStatement pst = con.prepareStatement(searchPupil)) {
                pst.setString(1, SearchMaHS);
                ResultSet rs = pst.executeQuery();
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("STT");
                model.addColumn("Mã SV");
                model.addColumn("Họ đệm");
                model.addColumn("Tên");
                model.addColumn("Ngày sinh");
                model.addColumn("Giới tính");
                model.addColumn("Nơi ở");
                model.addColumn("Địa chỉ");
                model.addColumn("Số điện thoại");
                model.addColumn("Email");
                model.addColumn("Điểm toán");
                model.addColumn("Điểm văn");
                model.addColumn("Điểm anh");
                model.addColumn("điểm vật lí/lịch sử");
                model.addColumn("điểm hóa/địa");
                model.addColumn("điểm môn sinh/GDCD");
                model.addColumn("Chọn");
                model.addColumn("Chỉnh Sửa");
                ButtonRenderer editPupil = new ButtonRenderer();
                ButtonEditor editPupilButton = new ButtonEditor(new JCheckBox());
                table.setModel(model);
                int stt = 1;
                while (rs.next()) {
                    MAHS = rs.getString("MAHS");
                    HODEM = rs.getString("HODEM");
                    TEN = rs.getString("TEN");
                    NGAYSINH = rs.getDate("NGAYSINH");
                    GIOITINH = rs.getString("GIOITINH");
                    NOISINHSONG = rs.getString("NOISINHSONG");
                    DIACHI = rs.getString("DIACHI");
                    SODIENTHOAI = rs.getString("SODIENTHOAI");
                    EMAIL = rs.getString("EMAIL");
                    DIEM_TOAN = rs.getFloat("DIEM_TOAN");
                    DIEM_VAN = rs.getFloat("DIEM_VAN");
                    DIEM_ANH = rs.getFloat("DIEM_ANH");
                    DIEM_VATLI_OR_LICHSU = rs.getFloat("DIEM_VAT_LY_OR_LICH_SU");
                    DIEM_HOAHOC_OR_DIALI = rs.getFloat("DIEM_HOA_HOC_OR_DIA_LY");
                    DIEM_SINHHOC_OR_GDCD = rs.getFloat("DIEM_SINH_HOC_OR_GDCD");
                    model.addRow(new Object[]{stt, MAHS, HODEM, TEN, NGAYSINH, GIOITINH, NOISINHSONG, DIACHI, SODIENTHOAI, EMAIL, DIEM_TOAN, DIEM_VAN, DIEM_ANH, DIEM_VATLI_OR_LICHSU, DIEM_HOAHOC_OR_DIALI, DIEM_SINHHOC_OR_GDCD, false});
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
