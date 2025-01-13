package Login;

import AdminHome.*;
import Model.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.JOptionPane;

public class Query {
    private String loginUser = "SELECT * FROM TAIKHOAN WHERE CCCD = ? AND password = ?";
    public void LoginUser(String CCCD, String password) {
        try {
            Connection connection = JDBCConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(loginUser);
            preparedStatement.setString(1, CCCD);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Login Success");
                new User.Home(resultSet.getString("MAHS"));
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
