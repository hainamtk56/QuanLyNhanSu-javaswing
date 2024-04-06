package UserThings.Controller;

import ConnectionManager.ConnectionManager;
import UserThings.Model.TaiKhoan;
import UserThings.View.ChangePasswordView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoiMatKhauController {
    private ChangePasswordView view;

    private TaiKhoan taiKhoan;

    private String hashedCurrentPass;

    public DoiMatKhauController(ChangePasswordView view, TaiKhoan taiKhoan) {
        this.view = view;
        this.taiKhoan = taiKhoan;
        this.view.addWindowListener(new ViewWindowListener());
        this.view.addActionListenerChangePassBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePassword();
            }
        });
    }

    public void fetchPassword() {
        TaiKhoan taiKhoan = null;
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String query = "SELECT matKhau FROM taikhoannguoidung WHERE idNhanVien = 1";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                taiKhoan = new TaiKhoan();
                taiKhoan.setMatKhau(rs.getString("matKhau"));
                hashedCurrentPass = taiKhoan.getMatKhau();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    class ViewWindowListener extends WindowAdapter {
        @Override
        public void windowOpened(WindowEvent e) {
            fetchPassword();
        }
    }

    public void updatePassword(int idNhanVien, String newMatKhau) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionManager.getConnection();
            String query = "UPDATE taikhoannguoidung SET matKhau = ? WHERE idNhanVien = ?";
            pst = con.prepareStatement(query);

            pst.setString(1, newMatKhau);
            pst.setInt(2, idNhanVien);

            // Execute the update query
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                view.displayMessage("Password successfully updated!");
            } else {
                System.out.println("No user found with idNhanVien: " + idNhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void ChangePassword() {
        String oldPassEnteredHashed = encryptPassword(view.getEnteredOldPass());
        if (hashedCurrentPass.equals(oldPassEnteredHashed)) {
            if (view.getNewPass().equals(view.getReTypePass())) {
                String newMatKhau = encryptPassword(view.getNewPass());
                updatePassword(1, newMatKhau);
            }
            else {
                view.displayMessage("Re-type password do not match!");
            }
        }
        else {
            view.displayMessage("Old password do not match!");
        }
    }

}
