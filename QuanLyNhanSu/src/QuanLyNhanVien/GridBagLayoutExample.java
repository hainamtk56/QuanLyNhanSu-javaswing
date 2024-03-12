package QuanLyNhanVien;

import javax.swing.*;
import java.awt.*;

public class GridBagLayoutExample extends JFrame {
    public GridBagLayoutExample() {
        setTitle("GridBagLayout Example");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel và Layout
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);

        // Label và TextField
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);

        // Button
        JButton submitButton = new JButton("Submit");

        // Đặt ràng buộc cho Label
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; // Cột đầu tiên
        constraints.gridy = 0; // Hàng đầu tiên
        constraints.anchor = GridBagConstraints.WEST; // Căn trái
        constraints.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        panel.add(nameLabel, constraints);

        // Đặt ràng buộc cho TextField
        constraints.gridx = 1; // Cột thứ hai
        constraints.fill = GridBagConstraints.HORIZONTAL; // Đầy đủ chiều ngang
        panel.add(nameField, constraints);

        // Đặt ràng buộc cho Button
        constraints.gridx = 0; // Quay lại cột đầu tiên
        constraints.gridy = 1; // Xuống hàng thứ hai
        constraints.gridwidth = 2; // Dùng hai ô
        constraints.anchor = GridBagConstraints.CENTER; // Căn giữa
        constraints.fill = GridBagConstraints.NONE; // Không căn đều
        panel.add(submitButton, constraints);

        // Thêm panel vào frame
        add(panel);

        // Hiển thị cửa sổ
        setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GridBagLayoutExample();
            }
        });
    }
}
