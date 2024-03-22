package DangNhap.View;

import DangNhap.Controller.LoginCtrl;
import DangNhap.Model.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ActionListener {
    private JLabel titleLable;
    private JLabel tenDangNhapLable;
    private JLabel matKhauLable;
    public JTextField tenDangNhapField;
    public JTextField matKhauField;
    private JButton dangNhapBtn;
    private JButton dangKyBtn; // Thêm nút đăng ký mới

    public LoginView() {
        initComponents();
    }

    public void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        titleLable = new JLabel("Đăng nhập");
        tenDangNhapLable = new JLabel("Tên ĐN: ");
        matKhauLable = new JLabel("Mật khẩu: ");
        dangNhapBtn = new JButton("Đăng nhập");
        dangKyBtn = new JButton("Đăng ký"); // Khởi tạo nút đăng ký mới
        tenDangNhapField = new JTextField(30);
        matKhauField = new JTextField(30);
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(500, 200);
        panel.setLayout(layout);
        panel.add(dangNhapBtn);
        panel.add(dangKyBtn); // Thêm nút đăng ký vào panel
        panel.add(titleLable);
        panel.add(tenDangNhapLable);
        panel.add(matKhauLable);
        panel.add(tenDangNhapField);
        panel.add(matKhauField);
        layout.putConstraint(SpringLayout.WEST, titleLable, 220, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleLable, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, tenDangNhapLable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenDangNhapLable, 50, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, matKhauLable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, matKhauLable, 80, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, tenDangNhapField, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenDangNhapField, 50, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, matKhauField, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, matKhauField, 80, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, dangNhapBtn, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dangNhapBtn, 120, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, dangKyBtn, 290, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dangKyBtn, 120, SpringLayout.NORTH, panel);

        add(panel);
        setTitle("Login");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setVisible(true);
        dangNhapBtn.addActionListener(this);
        dangKyBtn.addActionListener(this); // Đăng ký ActionListener cho nút đăng ký mới
    }

    public void actionPerformed(ActionEvent e) {
        // Xử lý sự kiện khi nút đăng nhập hoặc đăng ký được nhấn
    }

    public void login_listener(ActionListener log) {
        dangNhapBtn.addActionListener(log);
    }

    public void dangKy_listener(ActionListener dk) {
        dangKyBtn.addActionListener(dk); // Thêm phương thức để đăng ký ActionListener cho nút đăng ký mới
    }

    public Login getAccount() {
        return new Login(tenDangNhapField.getText(), matKhauField.getText());
    }

    public static void main(String[] args) {
        new LoginView();
    }

    public void register_listener(ActionListener dk) {
        dangKyBtn.addActionListener(dk); // Đăng ký ActionListener cho nút đăng ký
    }

}
