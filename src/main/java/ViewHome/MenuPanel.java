package ViewHome;

import javax.swing.*;
import java.awt.*;

import custom.*;
import custom.Button;
import AdminHome.*;

public class MenuPanel {
    private Query q;
    private JMenuBar menuBar;
    private JPanel panelCard;
    private Home home;
    private Button homeButton;
    private Button managerButton;
    private Button chartButton;

    public MenuPanel(Home home) {
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("☰");
        menu.setFont(new Font("Serif", Font.BOLD, 30));
        menuBar.add(menu);
        JPanel menuPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        menu.add(menuPanel);
        // --------- home button -----------
        homeButton = new Button();
        homeButton.setText("Trang chủ");
        homeButton.setFont(new Font("Serif", Font.BOLD, 30));
        homeButton.setBackground(new Color(228, 169, 143));
        menuPanel.add(homeButton);
        // --------- manager button -----------
        managerButton = new Button();
        managerButton.setText("Quản lí học sinh");
        managerButton.setFont(new Font("Serif", Font.BOLD, 30));
        managerButton.setBackground(new Color(227, 144, 194));
        menuPanel.add(managerButton);
        // ---------- chart button follow math -----------
        chartButton = new Button();
        chartButton.setText("phân tích biểu đồ môn toán");
        chartButton.setFont(new Font("Serif", Font.BOLD, 30));
        chartButton.setBackground(new Color(99, 121, 170));
        menuPanel.add(chartButton);

        // ----------------  lật thẻ sau khi nhet xong ben home -------------------
        q = new Query();
        /// trang chủ
        homeButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panelCard.getLayout();
            cardLayout.show(panelCard, "default");
        });
        /// quản lí 
        managerButton.addActionListener(e -> {
            q.showAllPupil(ManagerPage.getPupilTable());
            CardLayout cardLayout = (CardLayout) panelCard.getLayout();
            cardLayout.show(panelCard, "manager");
        });
        /// biểu đồ
        chartButton.addActionListener(e -> {
            q.showAllPupil(ManagerPage.getPupilTable());
            CardLayout cardLayout = (CardLayout) panelCard.getLayout();
            cardLayout.show(panelCard, "chart");
        });
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setPanelCard(JPanel panelCard) {
        this.panelCard = panelCard;
    }
}
