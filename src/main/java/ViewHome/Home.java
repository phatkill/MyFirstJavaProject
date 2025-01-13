package ViewHome;

import AdminHome.Query;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    private Query q;
    private JPanel panelCard;

    public Home() {
        init();
        setVisible(true);
    }

    public void init() {
        setTitle("home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        // Thêm MenuPanel
        MenuPanel menuPanel = new MenuPanel(this);
        setJMenuBar(menuPanel.getMenuBar());

        // Tạo panelCard để chứa các trang
        panelCard = new JPanel(new CardLayout());
        add(panelCard, BorderLayout.CENTER);

        // Tạo các trang và thêm vào panelCard
        DefaultPage defaultPage = new DefaultPage();
        ManagerPage managerPage = new ManagerPage(this);
        ChartPage chartPanel = new ChartPage();
        panelCard.add(defaultPage, "default");
        panelCard.add(managerPage, "manager");
        panelCard.add(chartPanel, "chart");

        // Thiết lập panelCard cho menuPanel
        menuPanel.setPanelCard(panelCard);
    }

    public void setQuery(Query query) {
        this.q = query;
    }

    public Query getQuery() {
        return q;
    }

    public static void main(String[] args) {
        new Home();
    }
}