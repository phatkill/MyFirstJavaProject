package Model;

import AdminHome.Query;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private JButton buttonEdit;
    private JTable table;
    private int row;
    public ButtonEditor(JCheckBox checkBox) {
        buttonEdit = new JButton();
        buttonEdit.setText("sửa");
        buttonEdit.addActionListener(e->{
            if (table != null && row >= 0) {
                Query q = new Query();
                q.formatUpdatePupil(table, row);
                fireEditingStopped();
            }
        });
    }
    //tùy chỉnh trình chỉnh sửa ô (cell editor)
    @Override
    public Object getCellEditorValue() {
        return null;
    }
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;
        return buttonEdit;
    }
}
