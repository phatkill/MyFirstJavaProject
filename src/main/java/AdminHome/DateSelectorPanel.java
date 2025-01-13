package AdminHome;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;
public class DateSelectorPanel extends JPanel {
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    public DateSelectorPanel() {
        // Tạo các JComboBox cho ngày, tháng, năm
        dayComboBox = new JComboBox<>();
        monthComboBox = new JComboBox<>();
        yearComboBox = new JComboBox<>();
        // Gọi hàm tạo ngày, tháng, năm hợp lệ và lắng nghe sự kiện
        createDateSelectors();
        // Tạo JPanel chứa các JComboBox
        this.setLayout(new FlowLayout());
        this.add(dayComboBox);
        this.add(monthComboBox);
        this.add(yearComboBox);
    }
    // Hàm tạo JComboBox cho ngày, tháng, năm và xử lý sự kiện thay đổi
    private void createDateSelectors() {
        // Thêm tháng vào monthComboBox
        for (int i = 1; i <= 12; i++) {
            monthComboBox.addItem(i < 10 ? "0" + i : String.valueOf(i));
        }
        // Thêm năm vào yearComboBox (ví dụ từ 1920 đến 2024)
        for (int i = 1920; i <= 2024; i++) {
            yearComboBox.addItem(String.valueOf(i));
        }

        // Thêm ngày vào dayComboBox (ban đầu sẽ có tất cả ngày trong tháng 1)
        updateDays();
        // Lắng nghe sự kiện thay đổi tháng
        monthComboBox.addActionListener(e -> updateDays());
        // Lắng nghe sự kiện thay đổi năm
        yearComboBox.addActionListener(e -> updateDays());
    }
    // Hàm cập nhật ngày khi thay đổi tháng hoặc năm
    private void updateDays() {
        int month = Integer.parseInt((String) monthComboBox.getSelectedItem());
        int year = Integer.parseInt((String) yearComboBox.getSelectedItem());
        // Xóa các ngày cũ trong JComboBox
        dayComboBox.removeAllItems();
        // Lấy số ngày hợp lệ trong tháng
        int daysInMonth = getDaysInMonth(month, year);
        // Nếu đã chọn ngày 31, thì không cho phép chọn các tháng không có 31 ngày
        if (daysInMonth < 31 && dayComboBox.getSelectedItem() != null &&
                Integer.parseInt((String) dayComboBox.getSelectedItem()) == 31) {
            dayComboBox.removeAllItems(); // Xóa ngày 31 nếu không hợp lệ
        }
        // Thêm các ngày vào JComboBox
        for (int i = 1; i <= daysInMonth; i++) {
            dayComboBox.addItem(i < 10 ? "0" + i : String.valueOf(i));
        }
    }
    // Hàm kiểm tra năm nhuận
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    // Hàm lấy số ngày trong tháng
    private int getDaysInMonth(int month, int year) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28; // Tháng 2
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30; // Các tháng có 30 ngày
        }
        return 31; // Các tháng còn lại có 31 ngày
    }
    // Phương thức trả về ngày được chọn dưới dạng Date
    public LocalDate getDate() {
        int day = Integer.parseInt((String) dayComboBox.getSelectedItem());
        int month = Integer.parseInt((String) monthComboBox.getSelectedItem());  // Lưu ý: Tháng trong Java là 1-12
        int year = Integer.parseInt((String) yearComboBox.getSelectedItem());

        return LocalDate.of(year, month, day);  // Trả về đối tượng LocalDate
    }
    public LocalDate now() {
        return LocalDate.now();  // Trả về ngày hiện tại dưới dạng LocalDate
    }
    // Getter cho các JComboBox (để sử dụng nếu cần)
    public JComboBox<String> getDayComboBox() {
        return dayComboBox;
    }
    public JComboBox<String> getMonthComboBox() {
        return monthComboBox;
    }
    public JComboBox<String> getYearComboBox() {
        return yearComboBox;
    }
}
