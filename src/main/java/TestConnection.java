import java.sql.*;

import static java.lang.System.*;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://DESKTOP-07RR9PT:1433;"
                +"user=sa;"
                +"password=123;"
                +"databaseName=QLDIEM;"
                +"encrypt=true;"
                +"trustServerCertificate=true;"
//                +"serverTimezone=UTC;"
                +"loginTimeout=3;";

        try (Connection con = DriverManager.getConnection(url)) {
            out.println("Successfully connected to database");
            String sql = "SELECT * FROM SINHVIEN";
            out.printf("%-12s %-25s %-12s %-21s %-10s %-30s %-12s\n", "Mã Sv", "Họ Đệm", "Tên",
                    "Ngày Sinh", "Giới Tính", "Nơi Sinh", "Mã Lớp");
            out.println("_______________________________________________________________________" +
                    "________________________________________________________________________");

            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next())
                {
                    String maSv = rs.getString("MASV");
                    String hoDem = rs.getString("HODEM");
                    String ten = rs.getString("TEN");
                    String ngaySinh = rs.getString("NGAYSINH");
                    boolean checkGioiTinh = rs.getBoolean("GIOITINH");
                    String gioiTinh= checkGioiTinh ? "Nam" : "Nữ";
                    String noiSinh = rs.getString("NOISINH");
                    String maLop = rs.getString("MALOP");
                    out.printf("%-12s %-25s %-12s %-15s %-10s %-30s %-12s\n", maSv, hoDem, ten, ngaySinh
                            , gioiTinh, noiSinh, maLop);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
         catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}