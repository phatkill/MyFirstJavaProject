package Home;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;


class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton currentButton;
    private String maSV;
    private AdminHome adminHome;

    public ButtonEditor(JCheckBox checkBox, JTable table, AdminHome adminHome, boolean isDeleteButton) {
        this.adminHome = adminHome; // Truyền adminHome từ ngoài vào

        // Xác định loại nút
        if (isDeleteButton) {
            currentButton = adminHome.createDeleteButton(""); // Placeholder để khởi tạo
        } else {
            currentButton = adminHome.createEditButton("");   // Placeholder để khởi tạo
        }

        currentButton.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        maSV = table.getValueAt(row, 0).toString(); // Lấy mã SV từ cột đầu tiên
        if (currentButton.getText().equals("x")) {
            currentButton = adminHome.createDeleteButton(maSV);
        } else {
            currentButton = adminHome.createEditButton(maSV);
        }
        return currentButton; // Hiển thị nút tương ứng
    }
    @Override
    public Object getCellEditorValue() {
        return currentButton.getText(); // Trả về nội dung nút
    }
}
