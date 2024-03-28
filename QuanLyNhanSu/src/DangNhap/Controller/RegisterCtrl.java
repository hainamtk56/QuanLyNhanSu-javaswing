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
        String url = "jdbc:mysql://localhost:3306/quanlynhansu";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, "root", "");
            System.out.println("connect done");
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        this.view = new RegisterView();
        this.view.register_listener(new Register_action_listener());
    }

    public boolean checkExist(String tenDangNhap) {
        String sql = "SELECT * FROM taikhoannguoidung WHERE tenDangNhap = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException var6) {
            var6.printStackTrace();
            return false;
        }
    }

    public boolean register(String tenDangNhap, String matKhau, String vaiTro) {
        String insertSql = "INSERT INTO taikhoannguoidung (tenDangNhap, matKhau, vaiTro) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(insertSql);
            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            ps.setString(3, vaiTro);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException var7) {
            var7.printStackTrace();
            return false;
        }
    }

    class Register_action_listener implements ActionListener {
        Register_action_listener() {
        }

        public void actionPerformed(ActionEvent e) {
            String tenDangNhap = view.getTenDangNhap();
            String matKhau = view.getMatKhau();
            String vaiTro = view.getVaiTro();

            RegisterModel model = new RegisterModel(tenDangNhap, matKhau, vaiTro);

            if (checkExist(model.getTenDangNhap())) {
                JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại");
            } else {
                boolean result = register(model.getTenDangNhap(), model.getMatKhau(), model.getVaiTro());
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

    public static void main(String[] args) {
        new RegisterCtrl();
    }
}
