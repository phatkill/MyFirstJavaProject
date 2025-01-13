package custom;

import javax.swing.*;
import java.awt.*;

//Lớp ảnh nền
public class BackGround extends JPanel {
    private Image backgroundImage;

    public BackGround(String imagePath) {
        try {
            backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Vẽ ảnh nền lấp đầy toàn bộ JPanel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}