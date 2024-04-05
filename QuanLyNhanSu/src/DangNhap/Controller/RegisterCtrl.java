package DangNhap.Controller;

import DangNhap.Model.RegisterModel;
import DangNhap.View.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterCtrl {
    private Connection conn;
    private RegisterView view;

    public RegisterCtrl() {
        // Kết nối đến cơ sở dữ liệu
        String url = "jdbc:mysql://localhost:3306/quanlynhansu";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, "root", "");
            System.out.println("Connect done");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Tạo và hiển thị giao diện đăng ký
        this.view = new RegisterView();
        this.view.register_listener(new RegisterActionListener());
    }

    // Phương thức kiểm tra xem tên đăng nhập đã tồn tại trong cơ sở dữ liệu chưa
    public boolean checkExist(String tenDangNhap) {
        String sql = "SELECT * FROM taikhoannguoidung WHERE tenDangNhap = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức thực hiện đăng ký tài khoản
    public boolean register(String tenDangNhap, String matKhau) {
        String insertSql = "INSERT INTO taikhoannguoidung (tenDangNhap, matKhau, vaiTro) VALUES (?, ?, 'Người dùng')";

        try {
            PreparedStatement ps = conn.prepareStatement(insertSql);
            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ActionListener cho nút Đăng ký trong giao diện
    class RegisterActionListener implements ActionListener {
        RegisterActionListener() {}

        @Override
        public void actionPerformed(ActionEvent e) {
            String tenDangNhap = view.getTenDangNhap();
            String matKhau = view.getMatKhau();

            RegisterModel model = new RegisterModel(tenDangNhap, matKhau);

            if (checkExist(model.getTenDangNhap())) {
                JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại");
            } else {
                boolean result = register(model.getTenDangNhap(), model.getMatKhau());
                if (result) {
                    JOptionPane.showMessageDialog(null, "Đăng ký thành công");
                    // Quay lại trang đăng nhập
                    view.dispose(); // Đóng cửa sổ đăng ký
                    new LoginCtrl(); // Mở lại cửa sổ đăng nhập
                } else {
                    JOptionPane.showMessageDialog(null, "Đăng ký không thành công");
                }
            }
        }
    }
    public void register_listener(ActionListener registerListener) {
        view.register_listener(registerListener); // Sử dụng nút "Đăng ký" từ giao diện RegisterView
    }



    public static void main(String[] args) {
        new RegisterCtrl();
    }
}
