package ViewHome;

import AdminHome.Query;
import custom.Button;

import javax.swing.*;
import java.awt.*;

public class ManagerPage extends JPanel {
    private static JTable pupilTable;
    private Home home;
    private Query q;

    public ManagerPage(Home home) {
        this.home = home;
        setLayout(new BorderLayout());

        pupilTable = new JTable();
        JScrollPane pupilScrollPane = new JScrollPane(pupilTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(pupilScrollPane, BorderLayout.CENTER);


        // ------------- tạo các nút chức năng -------------
        JPanel managerFunction = new JPanel();
        managerFunction.setBorder(BorderFactory.createLineBorder(new Color(23, 97, 205, 255), 17)); // Đặt viền cho panel
        managerFunction.setPreferredSize(new Dimension(managerFunction.getWidth(), 400)); // Thiết lập kích thước ưu tiên
        managerFunction.setLayout(new BorderLayout());
        add(managerFunction, BorderLayout.NORTH); // Thêm managerFunction vào phía bắc của ManagerPage

        /// Tạo panel panelTitleFiled để chứa tiêu đề chức năng
        JPanel panelTitleFiled = new JPanel();
        JLabel labelTitleFiled = new JLabel("Chức năng");
        labelTitleFiled.setFont(new Font("Serif", Font.BOLD, 30)); // Thiết lập font chữ
        panelTitleFiled.add(labelTitleFiled); // Thêm label vào panel
        managerFunction.add(panelTitleFiled, BorderLayout.NORTH); // Thêm panel vào phía bắc của managerFunction

        /// Tạo panel panelFunctionAndFilter để chứa các chức năng và bộ lọc
        JPanel panelFunctionAndFilter = new JPanel();
        panelFunctionAndFilter.setLayout(new BorderLayout());
        managerFunction.add(panelFunctionAndFilter, BorderLayout.CENTER); // Thêm panelFunctionAndFilter vào trung tâm của managerFunction

        // ----------------------- thêm các panelFunction ------------------------------
        /// Tạo panel panelNorthFunction để chứa các chức năng cơ bản (thêm, xóa)
        JPanel panelNorthFunction = new JPanel();
        panelFunctionAndFilter.add(panelNorthFunction, BorderLayout.NORTH); // Thêm panelNorthFunction vào phía bắc của panelFunctionAndFilter

        /// Tạo panel panelFilterFunction để chứa chức năng tìm kiếm
        JPanel panelFilterFunction = new JPanel();
        panelFilterFunction.setLayout(new BorderLayout());
        panelFunctionAndFilter.add(panelFilterFunction, BorderLayout.CENTER); // Thêm panelFilterFunction vào trung tâm của panelFunctionAndFilte

        // ------------------ Tạo panel searchFunction để chứa các thành phần tìm kiếm -----------------------
        JPanel searchFunction = new JPanel();
        panelFilterFunction.add(searchFunction, BorderLayout.CENTER); // Thêm searchFunction vào trung tâm của panelFilterFunction
        searchFunction.setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Tìm kiếm Mã Học Sinh: ");
        Font fontFunction = new Font("Serif", Font.BOLD, 30); // Thiết lập font chữ
        searchLabel.setFont(fontFunction); // Đặt font chữ cho searchLabel
        searchFunction.add(searchLabel); // Thêm searchLabel vào searchFunction
        JTextField maSVField = new JTextField();
        maSVField.setPreferredSize(new Dimension(300, 50)); // Đặt kích thước ưu tiên cho JTextField
        searchFunction.add(maSVField); // Thêm JTextField vào searchFunction
        Button searchButton = new Button();
        searchButton.setText("Tìm kiếm");
        searchButton.setFocusPainted(false);
        searchButton.setForeground(new Color(255, 255, 255));
        searchButton.setBackground(new Color(221, 92, 25));
        searchButton.setFont(fontFunction); // Đặt font chữ cho searchButton
        searchButton.setPreferredSize(new Dimension(300, 50)); // Đặt kích thước ưu tiên cho searchButton
        searchFunction.add(searchButton); // Thêm searchButton vào searchFunction
        // thêm sk cho nút tìm  kiếm
        q = new Query();
        searchButton.addActionListener(e -> {
            q.searchPupilFollowId(pupilTable, maSVField.getText());
        });

        // ----------------- Tạo và thêm nút "Thêm học sinh" vào panelNorthFunction -----------
        Button addPupilButton = new Button();
        addPupilButton.setText("Thêm học sinh");
        addPupilButton.setBackground(new Color(0, 82, 204));
        addPupilButton.setForeground(new Color(255, 255, 255, 255));
        addPupilButton.setFont(fontFunction); // Đặt font chữ cho addPupilButton
        addPupilButton.setFocusPainted(false);
        addPupilButton.setPreferredSize(new Dimension(300, 50)); // Đặt kích thước ưu tiên cho addPupilButton
        panelNorthFunction.add(addPupilButton); // Thêm addPupilButton vào panelNorthFunction

        /// Thêm sự kiện cho nút "Thêm học sinh"
        addPupilButton.addActionListener(e -> {
            q.addFormatPupil(pupilTable);
        });


        // ------------- Tạo và thêm nút "Xóa số lượng học sinh" vào panelNorthFunction -----------------
        Button deletePupilsButton = new Button();
        deletePupilsButton.setText("Xóa số lượng học sinh");
        deletePupilsButton.setBackground(new Color(227, 144, 194, 255));
        deletePupilsButton.setForeground(new Color(255, 255, 255, 255));
        deletePupilsButton.setFont(fontFunction); // Đặt font chữ cho deletePupilsButton
        deletePupilsButton.setFocusPainted(false);

        deletePupilsButton.setPreferredSize(new Dimension(300, 50)); // Đặt kích thước ưu tiên cho deletePupilsButton
        panelNorthFunction.add(deletePupilsButton); // Thêm deletePupilsButton vào panelNorthFunction
        /// Thêm sự kiện cho nút "Xóa số lượng học sinh"
        deletePupilsButton.addActionListener(e -> {
            q.DeleteSelectPupil(pupilTable);
        });

        // ------------- Tạo thêm nút chức năng load lại ------------------------------------
        Button reloadButton = new Button();
        reloadButton.setText("tải lại bảng");
        reloadButton.setFont(fontFunction);
        reloadButton.setBackground(new Color(237, 27, 27));
        reloadButton.setForeground(new Color(255, 255, 255, 255));
        reloadButton.setPreferredSize(new Dimension(300, 50));
        reloadButton.setFocusPainted(false);

        panelNorthFunction.add(reloadButton);
        reloadButton.addActionListener(e ->
        {
            q.showAllPupil(pupilTable);
        });
    }

    // Phương thức để lấy bảng học sinh
    public static JTable getPupilTable() {
        return pupilTable;
    }
}
