package DangNhap.Controller;

import DangNhap.Model.Login;
import DangNhap.View.LoginView;
import DangNhap.View.RegisterView;

import javax.swing.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class LoginCtrl {
    private Connection conn;
    LoginView view;
    Login model;

    public LoginCtrl() {
        String url = "jdbc:mysql://localhost:3306/quanlynhansu";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, "root", "");
            System.out.println("connect done");
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        this.view = new LoginView();
        this.view.login_listener(new Controller_action_listener());
        this.view.register_listener(new Register_action_listener());
    }

    public boolean check(String tenDangNhap, String matKhau) {
        String sql = "SELECT * FROM taikhoannguoidung WHERE tenDangNhap = ? AND matKhau = ?";

        try {
            String url = "jdbc:mysql://localhost:3306/quanlynhansu";
            Connection conn = DriverManager.getConnection(url, "root", "");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException var8) {
            var8.printStackTrace();
            return false;
        }
    }

    class Controller_action_listener implements ActionListener {
        Controller_action_listener() {
        }

        public void actionPerformed(ActionEvent e) {
            LoginCtrl.this.model = LoginCtrl.this.view.getAccount();
            String tenDangNhap = LoginCtrl.this.model.tenDangNhap.toString();
            String matKhau = LoginCtrl.this.model.matKhau.toString();
            boolean check = LoginCtrl.this.check(tenDangNhap, matKhau);
            if (check) {
                JOptionPane.showMessageDialog((Component)null, "Đăng nhập thành công");
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
//                        new MenuView();
                    }
                });
            } else {
                JOptionPane.showMessageDialog((Component)null, "Đăng nhập không thành công");
            }

        }
    }

    class Register_action_listener implements ActionListener {
        Register_action_listener() {
        }

        public void actionPerformed(ActionEvent e) {
            // Đóng cửa sổ đăng nhập
            LoginCtrl.this.view.dispose();
            // Mở cửa sổ đăng ký mới
            new RegisterCtrl();
        }
    }


    public static void main(String[] args) {
        new LoginCtrl();
    }
}
