package Swing;

import javax.swing.*;
import java.awt.*;
public class MainTitleTHPT extends JFrame {
    private JLabel lblTitleBoGiaoDucVaDaoTao,lblTitleKiThiTotNghiepTHPT;

    public MainTitleTHPT() {
        lblTitleBoGiaoDucVaDaoTao = new JLabel("BỘ GIÁO DỤC VÀ ĐÀO TẠO");
        lblTitleKiThiTotNghiepTHPT = new JLabel("KÌ THI TỐT NGHIỆP THPT");
        lblTitleBoGiaoDucVaDaoTao.setFont(new Font("Tahoma", Font.BOLD, 23));
        lblTitleKiThiTotNghiepTHPT.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitleKiThiTotNghiepTHPT.setForeground(Color.WHITE);
        lblTitleBoGiaoDucVaDaoTao.setForeground(Color.WHITE);
    }

    public JLabel getLblTitleBoGiaoDucVaDaoTao() {
        return lblTitleBoGiaoDucVaDaoTao;
    }

    public JLabel getJblKiThiTotNghiepTHPT() {
        return lblTitleKiThiTotNghiepTHPT;
    }
}
