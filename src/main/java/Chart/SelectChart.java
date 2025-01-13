package Chart;

import javax.swing.*;
import java.awt.*;

public class SelectChart extends JFrame{
    public SelectChart(){
        this.init();
        this.setVisible(true);
    }
    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Chọn thứ bạn muốn hiển thị biểu đồ", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(new Color(255, 102, 0));
        add(title, BorderLayout.NORTH);
    }
    public static void main(String[] args) {
        new SelectChart();
    }
}
