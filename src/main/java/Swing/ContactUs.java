package Swing;

import javax.swing.*;
import java.awt.*;

public class ContactUs extends JFrame {
    public JLabel getLbHotLine() {
        return lbHotLine;
    }
    public JLabel getSdt() {
        return lbSdt;
    }
    private JLabel lbHotLine;
    private JLabel lbSdt;
    public ContactUs() {
        lbHotLine = new JLabel("HOTLINE:");
        lbSdt = new JLabel("0934.765.006");
        lbHotLine.setFont(new Font("Tahoma", Font.BOLD, 23));
        lbSdt.setFont(new Font("Tahoma", Font.BOLD, 28));
        lbHotLine.setForeground(Color.WHITE);
        lbSdt.setForeground(Color.WHITE);
    }
}
