package ViewHome;

import AdminHome.JDBCConnect;
import AdminHome.Query;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChartPage extends Panel {
    private Query q;

    public ChartPage() {
        setLayout(new BorderLayout());
        JFreeChart barChart = createChart();
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        add(chartPanel, BorderLayout.CENTER);
    }

    private JFreeChart createChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();//để lưu trữ dữ liệu dạng bảng
        try {
            Connection con = JDBCConnect.getConnection();
            Statement statement = con.createStatement();
            String query = "SELECT MAHS, DIEM_TOAN FROM DIEM";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String pupilId = rs.getString("MAHS");
                double mathScore = rs.getDouble("DIEM_TOAN");
                dataset.addValue(mathScore, "diem toan", pupilId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ChartFactory.createBarChart(
                "Biểu đồ điểm Toán",  // Biểu đồ tiêu đề
                "Mã HS",             // Trục x
                "Điểm Toán",         // Trục y
                dataset,             // Dữ liệu
                PlotOrientation.VERTICAL,//  Hướng biểu đồ: Dọc
                true, // Hiển thị chú thích
                true, // Hiển thị gợi ý (tooltips)
                false // Không sử dụng URL
        );
    }
}
