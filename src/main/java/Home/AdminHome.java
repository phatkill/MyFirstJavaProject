package Home;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AdminHome extends JFrame {
    private JTable pupilTable;
    private JButton addButton;

    public AdminHome() {
        this.init();
        this.setVisible(true);
    }
    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Admin Home");
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());

        // ---------- Add function ------------
        JPanel panelFunction = new JPanel();
        panelFunction.setBackground(new Color(234, 112, 173));
        this.add(panelFunction, BorderLayout.NORTH);
        panelFunction.setPreferredSize(new Dimension(this.getWidth(), 30));

        // ----------- Add Panel Menu ----------
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(255, 255, 0, 166));
        add(panelMenu, BorderLayout.WEST);
        panelMenu.setPreferredSize(new Dimension(330, this.getHeight()));
        panelMenu.setLayout(new MigLayout("al,center center, wrap"));

        JButton homeButton = new JButton("Home");
        homeButton.setPreferredSize(new Dimension(240, 100));
        homeButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        panelMenu.add(homeButton);

        JButton buttonManagerment = new JButton("Quản lí học sinh");
        buttonManagerment.setPreferredSize(new Dimension(240, 100));
        buttonManagerment.setFont(new Font("Tahoma", Font.BOLD, 30));
        buttonManagerment.setBorder(BorderFactory.createEmptyBorder());
        panelMenu.add(buttonManagerment);

        // ----- Add visual------------
        JPanel visual = new JPanel();
        CardLayout cardLayout = new CardLayout();
        visual.setLayout(cardLayout);
        this.add(visual, BorderLayout.CENTER);

        JPanel defaultPanel = new JPanel();
        defaultPanel.setBackground(new Color(243, 237, 237, 255));
        visual.add(defaultPanel, "default");

        //---- Set up
        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(new Color(243, 237, 237, 255));
        pupilTable = new JTable();
        //        pupilTable.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pupilTable.setRowHeight(50);
        JScrollPane   pupilScroll = new JScrollPane(pupilTable);
        homePanel.add(pupilScroll,BorderLayout.CENTER);
        addButton = new JButton("Thêm học sinh");
        homePanel.add(addButton,BorderLayout.SOUTH);
// ---- Add Pupil ---------
        addButton.addActionListener(e -> {
            JFrame addFrame = new JFrame();
            addFrame.setLayout(new BorderLayout());
            addFrame.setSize(600, 800);
            addFrame.setLocationRelativeTo(null);

            JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
            addFrame.add(inputPanel, BorderLayout.CENTER);

            // Các JTextField và ComboBox
            JTextField tfMaSV = new JTextField();
            JTextField tfHoDem = new JTextField();
            JTextField tfTen = new JTextField();
            JTextField tfMaLop = new JTextField();
            JTextField tfDiaChi = new JTextField();
            JTextField tfSoDienThoai = new JTextField();
            JTextField tfEmail = new JTextField();
            JTextField tfDiemToan = new JTextField();
            JTextField tfDiemVan = new JTextField();
            JTextField tfDiemAnh = new JTextField();
            JTextField tfDiemVatLy = new JTextField();
            JTextField tfDiemHoaHoc = new JTextField();
            JTextField tfDiemSinhHoc = new JTextField();

            // ComboBox cho ngày sinh
            JComboBox<Integer> cbNgay = new JComboBox<>();
            JComboBox<Integer> cbThang = new JComboBox<>();
            JComboBox<Integer> cbNam = new JComboBox<>();

            for (int i = 1; i <= 31; i++) cbNgay.addItem(i);
            for (int i = 1; i <= 12; i++) cbThang.addItem(i);
            for (int i = 1900; i <= 2024; i++) cbNam.addItem(i);

            // ComboBox cho giới tính
            JComboBox<String> cbGioiTinh = new JComboBox<>(new String[]{"M", "F"});

            // Thêm các thành phần vào panel
            inputPanel.add(new JLabel("Mã sinh viên:"));
            inputPanel.add(tfMaSV);
            inputPanel.add(new JLabel("Họ và đệm:"));
            inputPanel.add(tfHoDem);
            inputPanel.add(new JLabel("Tên:"));
            inputPanel.add(tfTen);
            inputPanel.add(new JLabel("Ngày sinh:"));
            JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            datePanel.add(cbNgay);
            datePanel.add(cbThang);
            datePanel.add(cbNam);
            inputPanel.add(datePanel);
            inputPanel.add(new JLabel("Giới tính:"));
            inputPanel.add(cbGioiTinh);
            inputPanel.add(new JLabel("Mã lớp:"));
            inputPanel.add(tfMaLop);
            inputPanel.add(new JLabel("Địa chỉ:"));
            inputPanel.add(tfDiaChi);
            inputPanel.add(new JLabel("Số điện thoại:"));
            inputPanel.add(tfSoDienThoai);
            inputPanel.add(new JLabel("Email:"));
            inputPanel.add(tfEmail);
            inputPanel.add(new JLabel("Điểm Toán:"));
            inputPanel.add(tfDiemToan);
            inputPanel.add(new JLabel("Điểm Văn:"));
            inputPanel.add(tfDiemVan);
            inputPanel.add(new JLabel("Điểm Anh:"));
            inputPanel.add(tfDiemAnh);
            inputPanel.add(new JLabel("Điểm Vật Lý:"));
            inputPanel.add(tfDiemVatLy);
            inputPanel.add(new JLabel("Điểm Hóa Học:"));
            inputPanel.add(tfDiemHoaHoc);
            inputPanel.add(new JLabel("Điểm Sinh Học:"));
            inputPanel.add(tfDiemSinhHoc);

            // Nút lưu
            JButton saveButton = new JButton("Lưu");
            addFrame.add(saveButton, BorderLayout.SOUTH);

            addFrame.setVisible(true);

            saveButton.addActionListener(ev -> {
                // Kiểm tra xem người dùng đã chọn ngày sinh và giới tính hay chưa
                if (cbNgay.getSelectedItem() == null || cbThang.getSelectedItem() == null || cbNam.getSelectedItem() == null || cbGioiTinh.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(addFrame, "Vui lòng chọn đầy đủ thông tin ngày sinh và giới tính.", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Ghép ngày sinh từ ComboBox
                String ngaySinh = cbNam.getSelectedItem() + "-" + cbThang.getSelectedItem() + "-" + cbNgay.getSelectedItem();

                // Lưu vào cơ sở dữ liệu
                try (Connection con = DatabaseConnection.getConnection()) {
                    String addQuery = "INSERT INTO HOCSINH (MASV, HODEM, TEN, NGAYSINH, GIOITINH, MALOP, DIACHI, SODIENTHOAI, EMAIL, DIEM_TOAN, DIEM_VAN, DIEM_ANH, DIEM_VAT_LY_OR_LICH_SU, DIEM_HOA_HOC_OR_DIA_LI, DIEM_SINH_HOC_OR_GDCD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pst = con.prepareStatement(addQuery)) {
                        pst.setString(1, tfMaSV.getText().trim());
                        pst.setString(2, tfHoDem.getText().trim());
                        pst.setString(3, tfTen.getText().trim());
                        pst.setString(4, ngaySinh);
                        pst.setString(5, cbGioiTinh.getSelectedItem().toString());
                        pst.setString(6, tfMaLop.getText().trim());
                        pst.setString(7, tfDiaChi.getText().trim());
                        pst.setString(8, tfSoDienThoai.getText().trim());
                        pst.setString(9, tfEmail.getText().trim());
                        pst.setDouble(10, Double.parseDouble(tfDiemToan.getText().trim()));
                        pst.setDouble(11, Double.parseDouble(tfDiemVan.getText().trim()));
                        pst.setDouble(12, Double.parseDouble(tfDiemAnh.getText().trim()));
                        pst.setDouble(13, Double.parseDouble(tfDiemVatLy.getText().trim()));
                        pst.setDouble(14, Double.parseDouble(tfDiemHoaHoc.getText().trim()));
                        pst.setDouble(15, Double.parseDouble(tfDiemSinhHoc.getText().trim()));

                        int rowsAffected = pst.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(addFrame, "Thêm học sinh thành công!");
                        } else {
                            JOptionPane.showMessageDialog(addFrame, "Có lỗi xảy ra. Không thể thêm học sinh.");
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(addFrame, "Lỗi cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(addFrame, "Lỗi không xác định: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            });
        });



        visual.add(homePanel, "Manager");
        buttonManagerment.addActionListener(e->{
            loadPupil(pupilTable);
            cardLayout.show(visual, "Manager");
        });
        homeButton.addActionListener(e->{
            cardLayout.show(visual, "Home");
        });
    }
    // ----------- Load pupil --------------------
    private void loadPupil(JTable pupilTable){
        String query = "select * from HOCSINH";
        try (Connection con = DatabaseConnection.getConnection();   Statement st = con.createStatement()){
            DefaultTableModel model= new DefaultTableModel();
            model.addColumn("Mã SV");
            model.addColumn("Họ đệm");
            model.addColumn("Tên");
            model.addColumn("Ngày sinh");
            model.addColumn("Giới tính");
            model.addColumn("Lớp");
            model.addColumn("Địa chỉ");
            model.addColumn("Số điện thoại");
            model.addColumn("Email");
            model.addColumn("Điểm Toán");
            model.addColumn("Điểm Văn");
            model.addColumn("Điểm Anh");
            model.addColumn("Điểm Vật lý/Lịch sử");
            model.addColumn("Điểm Hóa học/Địa lý");
            model.addColumn("Điểm Sinh /GDCD");
            model.addColumn("Xóa");
            model.addColumn("Chỉnh Sửa");

            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                String maSv = rs.getString("MASV");
                String hoDem = rs.getString("HODEM");
                String ten = rs.getString("TEN");
                String ngaySinh = rs.getString("NGAYSINH");
                String gioiTinh = rs.getString("GIOITINH");
                String maLop = rs.getString("MALOP");
                String diaChi = rs.getString("DIACHI");
                String soDienThoai = rs.getString("SODIENTHOAI");
                String email = rs.getString("EMAIL");
                double diemToan = rs.getDouble("DIEM_TOAN");
                double diemVan = rs.getDouble("DIEM_VAN");
                double diemAnh = rs.getDouble("DIEM_ANH");
                double diemVatLy = rs.getDouble("DIEM_VAT_LY_OR_LICH_SU");
                double diemHoaHoc = rs.getDouble("DIEM_HOA_HOC_OR_DIA_LI");
                double diemSinhHoc = rs.getDouble("DIEM_SINH_HOC_OR_GDCD");
                model.addRow(new Object[]{maSv, hoDem, ten, ngaySinh, gioiTinh, maLop, diaChi, soDienThoai, email, diemToan, diemVan, diemAnh, diemVatLy, diemHoaHoc, diemSinhHoc});
            }
            pupilTable.setModel(model);//tran
            TableFunction.enableWordWrap(pupilTable);
            pupilTable.getColumnModel().getColumn(6).setPreferredWidth(200); // địa chỉ
            pupilTable.getColumnModel().getColumn(8).setPreferredWidth(200);//email

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            // Áp dụng canh giữa cho tất cả các cột
            for (int i = 0; i < pupilTable.getColumnCount(); i++) {
                pupilTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            pupilTable.getColumnModel().getColumn(model.getColumnCount()-2).setCellRenderer(new ButtonRenderer());
            pupilTable.getColumnModel().getColumn(model.getColumnCount()-1).setCellRenderer(new ButtonRenderer());
            // Áp dụng editor cho các cột "Xóa" và "Chỉnh Sửa"
            // Áp dụng editor cho các cột "Xóa" và "Chỉnh Sửa"
            pupilTable.getColumnModel().getColumn(model.getColumnCount() - 2).setCellEditor(new ButtonEditor(new JCheckBox("Xóa"), pupilTable, this, true));
            pupilTable.getColumnModel().getColumn(model.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JCheckBox("Sửa"), pupilTable, this, false));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // --- Delete Pupil -------
    JButton createDeleteButton(String maSV) {
        JButton deleteButton = new JButton("x");
        deleteButton.addActionListener(e->{
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc rằng bạn sẽ xóa nó?");
            if (confirm == JOptionPane.YES_OPTION) {
                deletePupil(maSV);
            }
        });
        return deleteButton;
    }
    JButton createEditButton(String maSV) {
        JButton editButton = new JButton("☑");
        editButton.addActionListener(e-> {editPupil(maSV);});
        return editButton;
    }
    public void deletePupil(String maSV) {
        String deleteQuery = "DELETE FROM HOCSINH WHERE MASV = ?";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement pst = con.prepareStatement(deleteQuery)){
            pst.setString(1, maSV);
            int affectedRows = pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Xóa thành công");
            loadPupil(pupilTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editPupil(String maSV) {
        // Tạo frame cho việc chỉnh sửa học sinh
        JFrame editFrame = new JFrame("Chỉnh sửa học sinh");
        editFrame.setLayout(new BorderLayout());
        editFrame.setSize(600, 800);
        editFrame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
        editFrame.setLayout(new BorderLayout()); // Sử dụng BorderLayout để chứa các thành phần

        // Tạo panel để chứa các trường nhập liệu và các nút
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        editFrame.add(inputPanel, BorderLayout.CENTER);
        // Các JTextField để nhập dữ liệu
        JTextField tfHoDem = new JTextField();
        JTextField tfTen = new JTextField();
        JTextField tfNgaySinh = new JTextField();
        JTextField tfGioiTinh = new JTextField();
        JTextField tfMaLop = new JTextField();
        JTextField tfDiaChi = new JTextField();
        JTextField tfSoDienThoai = new JTextField();
        JTextField tfEmail = new JTextField();
        JTextField tfDiemToan = new JTextField();
        JTextField tfDiemVan = new JTextField();
        JTextField tfDiemAnh = new JTextField();
        JTextField tfDiemVatLy = new JTextField();
        JTextField tfDiemHoaHoc = new JTextField();
        JTextField tfDiemSinhHoc = new JTextField();

        tfHoDem.setPreferredSize(new Dimension(200, 30));
        tfTen.setPreferredSize(new Dimension(200, 30));
        tfNgaySinh.setPreferredSize(new Dimension(200, 30));
        tfGioiTinh.setPreferredSize(new Dimension(200, 30));
        tfMaLop.setPreferredSize(new Dimension(200, 30));
        tfDiaChi.setPreferredSize(new Dimension(200, 30));
        tfSoDienThoai.setPreferredSize(new Dimension(200, 30));
        tfEmail.setPreferredSize(new Dimension(200, 30));
        tfDiemToan.setPreferredSize(new Dimension(200, 30));
        tfDiemVan.setPreferredSize(new Dimension(200, 30));
        tfDiemAnh.setPreferredSize(new Dimension(200, 30));
        tfDiemVatLy.setPreferredSize(new Dimension(200, 30));
        tfDiemHoaHoc.setPreferredSize(new Dimension(200, 30));
        tfDiemSinhHoc.setPreferredSize(new Dimension(200, 30));

        inputPanel.add(new JLabel("Họ và đệm:"));
        inputPanel.add(tfHoDem);
        inputPanel.add(new JLabel("Tên:"));
        inputPanel.add(tfTen);
        inputPanel.add(new JLabel("Ngày sinh:"));
        inputPanel.add(tfNgaySinh);
        inputPanel.add(new JLabel("Giới tính:"));
        inputPanel.add(tfGioiTinh);
        inputPanel.add(new JLabel("Mã lớp:"));
        inputPanel.add(tfMaLop);
        inputPanel.add(new JLabel("Địa chỉ:"));
        inputPanel.add(tfDiaChi);
        inputPanel.add(new JLabel("Số điện thoại:"));
        inputPanel.add(tfSoDienThoai);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(tfEmail);
        inputPanel.add(new JLabel("Điểm Toán:"));
        inputPanel.add(tfDiemToan);
        inputPanel.add(new JLabel("Điểm Văn:"));
        inputPanel.add(tfDiemVan);
        inputPanel.add(new JLabel("Điểm Anh:"));
        inputPanel.add(tfDiemAnh);
        inputPanel.add(new JLabel("Điểm Vật Lý:"));
        inputPanel.add(tfDiemVatLy);
        inputPanel.add(new JLabel("Điểm Hóa Học:"));
        inputPanel.add(tfDiemHoaHoc);
        inputPanel.add(new JLabel("Điểm Sinh Học:"));
        inputPanel.add(tfDiemSinhHoc);
        // Nút lưu
        JButton saveButton = new JButton("Lưu");
        editFrame.add(saveButton, BorderLayout.SOUTH);
        // Hiển thị frame
        editFrame.setVisible(true);  // Đảm bảo frame hiển thị
        try (Connection con = DatabaseConnection.getConnection()){
            PreparedStatement pst = con.prepareStatement("SELECT * FROM HOCSINH WHERE MASV = ?");
            pst.setString(1, maSV);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                tfHoDem.setText(rs.getString("HODEM"));
                tfTen.setText(rs.getString("TEN"));
                tfNgaySinh.setText(rs.getString("NGAYSINH"));
                tfGioiTinh.setText(rs.getString("GIOITINH"));
                tfMaLop.setText(rs.getString("MALOP"));
                tfDiaChi.setText(rs.getString("DIACHI"));
                tfSoDienThoai.setText(rs.getString("SODIENTHOAI"));
                tfEmail.setText(rs.getString("EMAIL"));
                tfDiemToan.setText(String.valueOf(rs.getDouble("DIEM_TOAN")));
                tfDiemVan.setText(String.valueOf(rs.getDouble("DIEM_VAN")));
                tfDiemAnh.setText(String.valueOf(rs.getDouble("DIEM_ANH")));
                tfDiemVatLy.setText(String.valueOf(rs.getDouble("DIEM_VAT_LY_OR_LICH_SU")));
                tfDiemHoaHoc.setText(String.valueOf(rs.getDouble("DIEM_HOA_HOC_OR_DIA_LI")));
                tfDiemSinhHoc.setText(String.valueOf(rs.getDouble("DIEM_SINH_HOC_OR_GDCD")));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        saveButton.addActionListener(e -> {
            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement("UPDATE HOCSINH SET HODEM=?, TEN=?, NGAYSINH=?, GIOITINH=?, MALOP=?, DIACHI=?, SODIENTHOAI=?, EMAIL=?, DIEM_TOAN=?, DIEM_VAN=?, DIEM_ANH=?, DIEM_VAT_LY_OR_LICH_SU=?, DIEM_HOA_HOC_OR_DIA_LI=?, DIEM_SINH_HOC_OR_GDCD=? WHERE MASV=?")) {

                pst.setString(1, tfHoDem.getText());
                pst.setString(2, tfTen.getText());
                pst.setString(3, tfNgaySinh.getText());
                pst.setString(4, tfGioiTinh.getText());
                pst.setString(5, tfMaLop.getText());
                pst.setString(6, tfDiaChi.getText());
                pst.setString(7, tfSoDienThoai.getText());
                pst.setString(8, tfEmail.getText());
                pst.setDouble(9, Double.parseDouble(tfDiemToan.getText()));
                pst.setDouble(10, Double.parseDouble(tfDiemVan.getText()));
                pst.setDouble(11, Double.parseDouble(tfDiemAnh.getText()));
                pst.setDouble(12, Double.parseDouble(tfDiemVatLy.getText()));
                pst.setDouble(13, Double.parseDouble(tfDiemHoaHoc.getText()));
                pst.setDouble(14, Double.parseDouble(tfDiemSinhHoc.getText()));
                pst.setString(15, maSV); // Điều kiện WHERE sử dụng mã SV

                int rowUpdated = pst.executeUpdate();
                if (rowUpdated > 0) {
                    JOptionPane.showMessageDialog(editFrame, "Cập nhật thành công");
                    editFrame.dispose();
                    loadPupil(pupilTable);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(editFrame, "Cập nhật thất bại. Vui lòng thử lại.");
            }
        });
    }

    public static void main(String[] args) {
        new AdminHome();
    }
}
