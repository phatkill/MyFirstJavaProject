package Home;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton  implements TableCellRenderer {
//    public ButtonRenderer() {
//        setOpaque(true);///: Phương thức này đảm bảo rằng nền của nút (JButton) sẽ không trong suốt.
//    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null){
            setText("");
            setEnabled(false);
        }else{
            setText(value.toString());
            setEnabled(false);
        }
        return this;
    }
}
