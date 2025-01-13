package AdminHome;

import net.miginfocom.layout.Grid;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    private Query q;
    public Home(){
        init();
        setVisible(true);
    }
    public void init(){
        setTitle("home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // ----------------- Add Menu -----------------
        /**
         this is menu is left north page use menuBar and menu to add it
         **/
        JMenuBar menuBar = new JMenuBar();
        Font fontFunction = new Font("Serif", Font.BOLD, 30);
        JMenu menu = new JMenu("☰");
        menu.setFont(fontFunction);
        menuBar.add(menu);
        setJMenuBar(menuBar); // Thêm thanh menu vào cửa sổ
        menuBar.setPreferredSize(new Dimension(menuBar.getWidth(), 50));

        ///------------ Add Panel to keep menu equals -------
        /**
         this is in menu to save Home and Manager
         **/
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1, 0, 10));
        menu.add(menuPanel);

        ///  ----------- pageHome default ----------------
        /**
         it is the default button to go to the default page
         **/
        JButton homeButton = new JButton("Trang chủ");
        homeButton.setFont(fontFunction);
        homeButton.setFocusPainted(false);
        homeButton.setPreferredSize(new Dimension(300, 50));
        menuPanel.add(homeButton);
        /// ------------ Manager -------------------------
        /**
         it is the manager button to go to the manager page
         **/
        JButton managerButton = new JButton("Quản lí học sinh");
        managerButton.setFont(fontFunction);
        managerButton.setFocusPainted(false);
        managerButton.setPreferredSize(new Dimension(300, 50));
        menuPanel.add(managerButton);
        //-------------------------------------------------------------------------------

        // -------------- Add Visual Home ------------------
        /**
         this is panelCard to save all page
         it added by Home at Center
         **/
        JPanel panelCard = new JPanel();
        panelCard.setLayout(new CardLayout());
//        panelCard.setBackground(new Color(189, 119, 160, 255));
        add(panelCard, BorderLayout.CENTER);
        ///  default page
        /**
         This is default page to show when you open the app or click home button use cardlayou
         this have panelDefaultPage to add header page and panelManagerPage to add manager page
         **/
        /// Default
        JPanel panelDefaultPage = new JPanel();
        panelDefaultPage.setBackground(new Color(189, 119, 160, 255));
        panelCard.add(panelDefaultPage,"default");

        /// Manager
        JPanel panelManagerPage = new JPanel();
        panelManagerPage.setLayout(new BorderLayout());
        panelCard.add(panelManagerPage,"manager");


        ///------------------Add Header Page ----------
        /**
         this is header page to show when you open the app or click home button
         this have PanelHeader to add labelHeader and .... (not yet)
         */
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new FlowLayout());
        panelHeader.setPreferredSize(new Dimension(panelHeader.getWidth(), 100));
        JLabel labelHeader = new JLabel("Welcome to Admin Home");
        panelHeader.add(labelHeader);
        labelHeader.setFont(new Font("romantic", Font.BOLD, 50));
        panelDefaultPage.setLayout(new BorderLayout());
        panelDefaultPage.add(panelHeader,"North");

        ///  ------------------ ADD PanelManagerPage ----------------
        /**
         this is manager page to show when you click manager button
         this (have tablePupil wrapped in jcrollpane) to add table to show all pupil and managerFunction to add function to manager and it at Center
         this have FunctionManagerHome to add function to managerFunction it at north (but i modified it to upsize(high->400))
         **/

        // ---------------------Add tablePuPil ------------------------
        JTable pupilTable = new JTable();
        JScrollPane pupilScrollPane = new JScrollPane(pupilTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelManagerPage.add(pupilScrollPane,BorderLayout.CENTER);

        //-------------------- Add Function Manager -------------------------
        /**
         this is manager Function and it at north of manager page
         this have:
         + labelTitleField to add title
         +

         **/
        JPanel managerFunction = new JPanel();
        managerFunction.setBorder(BorderFactory.createLineBorder(new Color(23, 97, 205, 255), 17));
        managerFunction.setPreferredSize(new Dimension(managerFunction.getWidth(), 400));
        managerFunction.setLayout(new BorderLayout());
        panelManagerPage.add(managerFunction, BorderLayout.NORTH);
        /// ----------------- Add Title Function----------------
        /**
          this is title of managerFunction and it at north of managerFunction
         **/
        JPanel panelTitleFiled = new JPanel();
        JLabel labelTitleFiled = new JLabel("Chức năng");
        labelTitleFiled.setFont(new Font("Serif", Font.BOLD, 30));
        panelTitleFiled.add(labelTitleFiled);
        managerFunction.add(panelTitleFiled, BorderLayout.NORTH);

        /**
          this is panelFunctionAndFilter to add function to managerFunction and it at center of managerFunction
         it have:
         + panelNorthFunction to add function simple as add, delete, sort
         + panelFilterFunction to add search function
         **/
        JPanel panelFunctionAndFilter = new JPanel();
        
        /// ----------------- Add function to panelFunctionAndFilter ----------------
        panelFunctionAndFilter.setLayout(new BorderLayout());
        managerFunction.add(panelFunctionAndFilter, BorderLayout.CENTER);
        
        /// ---------------- Add PanelNorthFunction ----------------
        JPanel panelNorthFunction = new JPanel();
        /**
         this is panelNorthFunction to add function simple as add, delete, sort
         **/
        panelFunctionAndFilter.add(panelNorthFunction, BorderLayout.NORTH);
        // ------------------Filter ----------------------------------

        /**
            this is panelFilterFunction to add search function
         **/

        JPanel panelFilterFunction = new JPanel();
        panelFilterFunction.setLayout(new BorderLayout());
        panelFunctionAndFilter.add(panelFilterFunction, BorderLayout.CENTER);

        // ------------------Add Search Function ---------------------
        /**
            this is searchFunction to add search function
            this have searchLabel to add label search
            this have maSVField to add textfield to search
         **/
        JPanel searchFunction = new JPanel();
        panelFilterFunction.add(searchFunction, BorderLayout.CENTER);
        searchFunction.setLayout(new FlowLayout());
        JLabel searchLabel = new JLabel("Tìm kiếm học sinh: ");
        searchLabel.setFont(fontFunction);
        searchFunction.add(searchLabel);
        JTextField maSVField = new JTextField();
        maSVField.setPreferredSize(new Dimension(300, 50));
        searchFunction.add(maSVField);
        JButton searchButton = new JButton("Tìm kiếm");
        searchButton.setFont(fontFunction);
        searchButton.setFocusPainted(false);
        searchButton.setPreferredSize(new Dimension(200, 50));
        searchFunction.add(searchButton);

        // ------------------Handel ALL ---------------------
        /**
            this is handel all include:  flip card, button in home, and filter
         **/

        // ---------------Handel flip card homebutton and managerbutton-----------------
        /**
         this is handel flip card homebutton and managerbutton
         when you click homebutton it will show default page by (panelCard, "default")
         when you click managerbutton it will show manager page by (panelCard, "manager")
         **/
        homeButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) panelCard.getLayout();
            cardLayout.show(panelCard, "default");
        });
        managerButton.addActionListener(e -> {
            loadPupils(pupilTable);
            CardLayout cardLayout = (CardLayout) panelCard.getLayout();
            cardLayout.show(panelCard, "manager");
        });

        // ----------------------Button add pupil ---------------------
        JButton addPupilButton = new JButton("Thêm học sinh");
        addPupilButton.setFont(fontFunction);
        addPupilButton.setFocusPainted(false);
        addPupilButton.setPreferredSize(new Dimension(400, 50));
        panelNorthFunction.add(addPupilButton);
        addPupilButton.addActionListener(e->{
            addPupil(pupilTable);
        });

        // ------------------Delete Function ----------------------------------
        JButton deletePupilsButton = new JButton("Xóa số lượng học sinh");
        deletePupilsButton.setFont(fontFunction);
        deletePupilsButton.setFocusPainted(false);
        deletePupilsButton.setPreferredSize(new Dimension(400, 50));
        panelNorthFunction.add(deletePupilsButton);
        deletePupilsButton.addActionListener(e -> {
            deletePupil(pupilTable);
        });
        //------------------Search Function ----------------------------------
        searchButton.addActionListener(e -> {
            q = new Query();
            q.searchPupilFollowId(pupilTable, maSVField.getText());
        });
    }
    private void addPupil(JTable pupilTable) {
        q = new Query();
        q.addFormatPupil(pupilTable);
    }

    //  ------------------ Load Pupil -----------------------------
    private void loadPupils(JTable table){
        q = new Query();
        q.showAllPupil(table);
    }
    private void deletePupil(JTable table){
        q = new Query();
        q.DeleteSelectPupil(table);
    }
    public static void main(String[] args) {
        new Home();
    }
}
