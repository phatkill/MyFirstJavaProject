package ViewHome;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class DefaultPage extends JPanel {
    public DefaultPage() {
        // Set layout for the panel
        setLayout(new BorderLayout());

        // Create header panel
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new FlowLayout());
        panelHeader.setPreferredSize(new Dimension(panelHeader.getWidth(), 100));
        JLabel labelHeader = new JLabel("Xin chào Admin");
        labelHeader.setFont(new Font("romantic", Font.BOLD, 50));
        panelHeader.add(labelHeader);
        add(panelHeader, BorderLayout.NORTH);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create JTextPane for displaying text
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false); // Make the text pane non-editable

        // Get the document and set up styles
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setFontSize(attr, 30); // Increase font size
        StyleConstants.setFontFamily(attr, "Arial");
        StyleConstants.setBold(attr, false);
        StyleConstants.setLineSpacing(attr, 0.5f); // Increase line spacing

        SimpleAttributeSet headingAttr = new SimpleAttributeSet(attr);
        StyleConstants.setBold(headingAttr, true);
        StyleConstants.setFontSize(headingAttr, 36); // Increase font size for heading

        try {
            // Insert heading text
            doc.insertString(doc.getLength(), "Hướng dẫn cách dùng app\n\n\n", headingAttr);
            // Insert body text
            doc.insertString(doc.getLength(),
                    "Đầu tiên khi bạn nhìn vào sẽ thấy ba nút, mỗi nút có một chức năng riêng, hãy cùng tôi điểm qua chức năng đầu tiên, Đó là chức năng mặc định:\n" +
                            "    - Chức năng này sẽ giúp bạn biết cách dùng app\n" +
                            "Tiếp theo là chức năng quản lí trang chủ:\n" +
                            "    - Giúp bạn đọc bảng và chỉnh sửa nó theo ý muốn của bạn( thêm, xóa, sửa)\n" +
                            "Cuối cùng là biểu đồ:\n" +
                            "    - Bạn sẽ được nhìn tổng quát biểu đồ để hiểu hơn về thị trường điểm số ở môn đó của nước ta", attr);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        // Center align the text
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        // Add JTextPane to JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane(textPane);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add main panel to DefaultPage
        add(mainPanel, BorderLayout.CENTER);
    }

    // Main method to run and test the interface
    public static void main(String[] args) {
        JFrame frame = new JFrame("Default Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true); // Remove window decorations
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window to full screen
        frame.add(new DefaultPage());
        frame.setVisible(true);
    }
}