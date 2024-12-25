package Home;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TableFunction {

    public static void enableWordWrap(JTable table) {

        // Thiết lập renderer cho tất cả các ô trong bảng để hiển thị văn bản với tính năng xuống dòng
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Tạo một JLabel và bao quanh giá trị của ô trong thẻ <html> để hỗ trợ tính năng xuống dòng
                JLabel label = new JLabel("<html>" + (value == null ? "" : value.toString()) + "</html>");

                // Căn chỉnh văn bản lên phía trên cùng của ô
                label.setVerticalAlignment(SwingConstants.TOP);
                label.setFont(new Font("Tahoma", Font.PLAIN, 16)); // Đặt font chữ cho văn bản trong ô

                // Nếu ô được chọn, thay đổi màu nền và màu chữ
                if (isSelected) {
                    label.setBackground(table.getSelectionBackground());
                    label.setForeground(table.getSelectionForeground());
                    label.setOpaque(true);
                } else {
                    // Nếu không được chọn, sử dụng màu nền và màu chữ mặc định
                    label.setBackground(table.getBackground());
                    label.setForeground(table.getForeground());
                    label.setOpaque(true);
                }

                return label;  // Trả về JLabel để hiển thị trong ô
            }
        });

        // Tuỳ chỉnh chiều cao của tiêu đề bảng
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 80)); // Tăng chiều cao của tiêu đề lên 80
        // Tuỳ chỉnh renderer của tiêu đề bảng
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Lấy label mặc định của tiêu đề
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Căn giữa văn bản trong tiêu đề
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Tahoma", Font.BOLD, 14)); // Đặt font chữ cho tiêu đề
                label.setOpaque(true); // Đảm bảo rằng tiêu đề có nền
                label.setBackground(Color.PINK); // Màu nền của tiêu đề là hồng
                label.setForeground(Color.BLACK); // Màu chữ của tiêu đề là đen

                // Giữ lại đường viền của tiêu đề
                label.setBorder(UIManager.getBorder("TableHeader.cellBorder"));

                // Nếu tiêu đề có ký tự đặc biệt như "/", thay thế chúng bằng một dòng mới
                if (value != null) {
                    label.setText("<html>" + value.toString().replace("/", "/<br>") + "</html>");
                }

                return label; // Trả về label đã tuỳ chỉnh
            }
        });
    }
}
