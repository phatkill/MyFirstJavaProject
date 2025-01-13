package custom;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Button extends JButton {
    // Getter và Setter cho thuộc tính effectColor
    // ------------------- màu lan tỏa -------------------
    public Color getEffectColor() {
        return effectColor;
    }
    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
    }

    // Các trường để điều khiển hoạt hình và hiệu ứng khi nhấn nút
    private Animator animator;            // Đối tượng Animator để xử lý hoạt hình
    private int targetSize;               // Kích thước mục tiêu của vòng tròn hiệu ứng
    private float animatSize;             // Kích thước hiện tại của vòng tròn hiệu ứng
    private Point pressedPoint;           // Điểm nhấn chuột trên nút
    private float alpha;                  // Mức độ trong suốt của hiệu ứng
    private Color effectColor = new Color(255, 255, 255);  // Màu sắc của hiệu ứng, mặc định là trắng

    // Constructor của lớp Button, nơi khởi tạo các thuộc tính và cài đặt hiệu ứng
    public Button() {
        // Tắt việc tô màu nền mặc định của nút
        setContentAreaFilled(false);

        // Đặt viền nút với khoảng cách rỗng
        setBorder(new EmptyBorder(5, 0, 5, 0));

        // Đặt màu nền của nút là trắng
        setBackground(Color.WHITE);

        // Đặt con trỏ chuột thành hình bàn tay khi di chuột vào nút
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ----------------- HOẠT ẢNH --------------------
        // cái trên de hien thi nut nhan
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                // Tính toán kích thước mục tiêu của vòng tròn hiệu ứng (gấp đôi chiều dài hoặc chiều rộng của nút)
                targetSize = Math.max(getWidth(), getHeight()) * 2;
                animatSize = 0;  // Đặt kích thước hiệu ứng ban đầu là 0
                pressedPoint = me.getPoint();  // Lưu lại vị trí nhấn chuột
                alpha = 0.5f;  // Đặt mức độ trong suốt ban đầu là 0.5 (hiệu ứng sẽ mờ dần)

                // Dừng hoạt hình nếu nó đang chạy, và bắt đầu lại từ đầu
                if (animator.isRunning()) {
                    animator.stop();
                }
                animator.start();  // Bắt đầu hiệu ứng hoạt hình
            }
        });

        // tạo độ mờ dần
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                // Khi fraction > 0.5, giảm giá trị alpha (làm hiệu ứng mờ dần)
                if (fraction > 0.5f) {
                    alpha = 1 - fraction;  // Tạo hiệu ứng mờ dần khi hoạt hình hoàn thành
                }

                // Tính toán kích thước của vòng tròn hiệu ứng dựa trên fraction (tỷ lệ hoàn thành hoạt hình)
                animatSize = fraction * targetSize;

                // Vẽ lại nút với hiệu ứng mới
                repaint();
            }
        };

        // Tạo đối tượng Animator để điều khiển thời gian hoạt hình (400ms)
        animator = new Animator(400, target);
        // Cài đặt độ phân giải của hoạt hình
        animator.setResolution(0);

        // Cài đặt tốc độ tăng dần và giảm dần của hoạt hình (tạo hiệu ứng mượt mà)
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
    }

    // Phương thức vẽ lại nút (với hiệu ứng hoạt hình) mỗi khi có sự thay đổi
    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();  // Lấy chiều rộng của nút
        int height = getHeight();  // Lấy chiều cao của nút

        // Tạo một BufferedImage để vẽ lên giao diện
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Lấy đối tượng Graphics2D để vẽ
        Graphics2D g2 = img.createGraphics();

        // Thiết lập các tham số để vẽ mượt mà
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền nút với màu nền đã cài đặt (màu trắng)
        g2.setColor(getBackground());// xét màu
        g2.fillRoundRect(0, 0, width, height, 5, 5);  // Vẽ hình chữ nhật bo góc (2 tham so cuoi la bo tron)
        // Nếu người dùng đã nhấn chuột, vẽ hiệu ứng vòng tròn tại vị trí nhấn
        if (pressedPoint != null) {
            g2.setColor(effectColor);  // Đặt màu cho vòng tròn hiệu ứng
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));  // Cài đặt độ trong suốt của hiệu ứng
            // Vẽ vòng tròn tại vị trí nhấn chuột, với kích thước thay đổi theo thời gian
            g2.fillOval((int) (pressedPoint.x - animatSize / 2), (int) (pressedPoint.y - animatSize / 2),
                    (int) animatSize, (int) animatSize);
        }
        // Giải phóng tài nguyên sau khi vẽ
        g2.dispose();

        // Vẽ hình ảnh đã tạo lên giao diện
        grphcs.drawImage(img, 0, 0, null);

        // Vẽ lại các thành phần của JButton (nếu có)
        super.paintComponent(grphcs);// add chu
    }
}
