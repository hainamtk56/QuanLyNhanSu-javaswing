package DangNhap.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame implements ActionListener {
    private JLabel titleLable;
    private JLabel tenDangNhapLable;
    private JLabel matKhauLable;
    private JLabel vaiTroLable; // Thêm nhãn vaiTroLable
    private JTextField tenDangNhapField;
    private JTextField matKhauField;
    private JTextField vaiTroField; // Thêm trường vaiTroField
    private JButton dangKyBtn;

    public RegisterView() {
        initComponents();
    }

    public void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        titleLable = new JLabel("Đăng ký");
        tenDangNhapLable = new JLabel("Tên ĐN: ");
        matKhauLable = new JLabel("Mật khẩu: ");
        vaiTroLable = new JLabel("Vai trò: "); // Khởi tạo nhãn vaiTroLable
        dangKyBtn = new JButton("Đăng ký");
        tenDangNhapField = new JTextField(30);
        matKhauField = new JTextField(30);
        vaiTroField = new JTextField(30); // Khởi tạo trường vaiTroField
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(500, 200);
        panel.setLayout(layout);
        panel.add(dangKyBtn);
        panel.add(titleLable);
        panel.add(tenDangNhapLable);
        panel.add(matKhauLable);
        panel.add(vaiTroLable); // Thêm nhãn vaiTroLable vào panel
        panel.add(tenDangNhapField);
        panel.add(matKhauField);
        panel.add(vaiTroField); // Thêm trường vaiTroField vào panel
        layout.putConstraint(SpringLayout.WEST, titleLable, 220, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleLable, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tenDangNhapLable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenDangNhapLable, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, matKhauLable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, matKhauLable, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, vaiTroLable, 20, SpringLayout.WEST, panel); // Sắp xếp nhãn vaiTroLable
        layout.putConstraint(SpringLayout.NORTH, vaiTroLable, 110, SpringLayout.NORTH, panel); // Sắp xếp nhãn vaiTroLable
        layout.putConstraint(SpringLayout.WEST, tenDangNhapField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenDangNhapField, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, matKhauField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, matKhauField, 80, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, vaiTroField, 100, SpringLayout.WEST, panel); // Sắp xếp trường vaiTroField
        layout.putConstraint(SpringLayout.NORTH, vaiTroField, 110, SpringLayout.NORTH, panel); // Sắp xếp trường vaiTroField
        layout.putConstraint(SpringLayout.WEST, dangKyBtn, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dangKyBtn, 140, SpringLayout.NORTH, panel);
        add(panel);
        setTitle("Register");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setVisible(true);
        dangKyBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        // Xử lý sự kiện khi nút đăng ký được nhấn
    }

    public String getTenDangNhap() {
        return tenDangNhapField.getText();
    }

    public String getMatKhau() {
        return matKhauField.getText();
    }

    public String getVaiTro() {
        return vaiTroField.getText(); // Trả về giá trị của trường vaiTroField
    }

    public void register_listener(ActionListener registerListener) {
        dangKyBtn.addActionListener(registerListener);
    }

    public static void main(String[] args) {
        new RegisterView();
    }
}
