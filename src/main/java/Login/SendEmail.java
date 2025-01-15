package Login;

import javax.mail.*;  // Import các lớp từ thư viện javax.mail (cần thiết cho việc gửi email)
import javax.mail.internet.*;  // Import các lớp để xử lý các đối tượng email (ví dụ MimeMessage)
import java.util.Properties;  // Import lớp Properties để cấu hình các thuộc tính khi kết nối SMTP

public class SendEmail {

    public static void sendEmail(String toEmail, String subject, String body) {
        String fromEmail = "clonevclone05@gmail.com\n";  // Địa chỉ email gửi
        String password = "dgqp crtt mecq fium\n";  // Mật khẩu ứng dụng (cần phải tạo nếu dùng xác thực hai yếu tố)
        Properties properties = new Properties();  // Khởi tạo đối tượng Properties để chứa các thuộc tính kết nối SMTP
        properties.put("mail.smtp.host", "smtp.gmail.com");  // Cấu hình server SMTP của Gmail
        properties.put("mail.smtp.port", "587");  // Cổng SMTP cho giao thức TLS (587)
        properties.put("mail.smtp.auth", "true");  // Kích hoạt xác thực cho SMTP server
        properties.put("mail.smtp.starttls.enable", "true");  // Kích hoạt tính năng STARTTLS (mã hóa dữ liệu khi truyền)
        // Tạo Session và cấu hình xác thực
        Session session = Session.getInstance(properties, new Authenticator() {  // Tạo session để kết nối với server SMTP
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);  // Trả về thông tin xác thực (email và mật khẩu)
            }
        });

        try {
            // Tạo đối tượng Message để đại diện cho email gửi đi
            Message message = new MimeMessage(session);  // Tạo một đối tượng MimeMessage từ session đã cấu hình
            message.setFrom(new InternetAddress(fromEmail));  // Đặt địa chỉ người gửi
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));  // Đặt địa chỉ người nhận
            message.setSubject(subject);  // Đặt tiêu đề của email
            message.setText(body);  // Đặt nội dung (body) của email

            // Gửi email
            Transport.send(message);  // Gửi email qua giao thức SMTP
            System.out.println("Email đã được gửi thành công!");  // In ra thông báo thành công nếu email được gửi thành công
        } catch (MessagingException e) {  // Xử lý các lỗi nếu có trong quá trình gửi email
            e.printStackTrace();  // In ra thông tin lỗi
        }
    }
}
