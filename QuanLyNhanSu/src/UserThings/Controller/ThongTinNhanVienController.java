package UserThings.Controller;

import ConnectionManager.ConnectionManager;
import UserThings.Model.NhanVien;
import UserThings.Model.TaiKhoan;
import UserThings.View.ChangePasswordView;
import UserThings.View.NhanVienView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongTinNhanVienController {
    private final NhanVienView view = new NhanVienView();

    private ChangePasswordView viewPass;
    private NhanVien nhanVien;

    public ThongTinNhanVienController(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
        this.view.addWindowListener(new ViewWindowListener());
        this.view.addActionListenerToButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleClick();
            }
        });

        this.view.addActionListenertoReset(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleReset();
            }
        });

        this.view.addActionListenerToDelete(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleClear();
            }
        });

        this.view.addActionListenerToChangePassword(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPass = new ChangePasswordView();
                TaiKhoan taiKhoan = new TaiKhoan();
                DoiMatKhauController controller = new DoiMatKhauController(viewPass, taiKhoan);
            }
        });
    }

    public void fetchDataFromDatabase(int employeeId) {
        NhanVien nhanVien = null;
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String query = "SELECT n.*, c.tenChucVu, p.tenPhongBan, l.nam, l.thang, l.soGioLam, l.thuong, l.thucLanh " +
                    "FROM nhanvien n " +
                    "INNER JOIN chucvu c ON n.idChucVu = c.idChucVu " +
                    "INNER JOIN phongban p ON n.idPhongBan = p.idPhongBan " +
                    "INNER Join luong l ON n.idLuong = l.idNhanVien " +
                    "WHERE n.idNhanVien = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, employeeId);
            rs = pst.executeQuery();

            if (rs.next()) {
                nhanVien = new NhanVien();
                nhanVien.setIdNhanVien(rs.getInt("idNhanVien"));
                nhanVien.setHoTen(rs.getString("hoTen"));
                nhanVien.setDate(rs.getString("ngaySinh"));
                nhanVien.setGioiTinh(rs.getString("gioiTinh"));
                nhanVien.setDiaChi(rs.getString("diaChi"));
                nhanVien.setSoDienThoai(rs.getString("soDienThoai"));
                nhanVien.setEmail(rs.getString("email"));
                nhanVien.setTinhTrang(rs.getString("tinhTrang"));
                nhanVien.setIdChucVu(rs.getInt("idChucVu"));
                nhanVien.setIdPhongBan(rs.getInt("idPhongBan"));
                nhanVien.setNgayVaoLam(rs.getString("ngayVaoLam"));
                nhanVien.setTenChucVu(rs.getString("tenChucVu"));
                nhanVien.setTenPhongBan(rs.getString("tenPhongBan"));
                nhanVien.setNam(rs.getInt("nam"));
                nhanVien.setThang(rs.getInt("thang"));
                nhanVien.setSoGioLam(rs.getInt("soGioLam"));
                nhanVien.setThuong(rs.getInt("thuong"));
                nhanVien.setThucLanh(rs.getInt("thucLanh"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (nhanVien != null) {
            view.displayData(nhanVien);
        } else {
            view.displayMessage("Employee with ID " + employeeId + " not found.");
        }
    }

    private void updateEmployeeData(int employeeId, String newAddress, String newMobilePhone, String newEmail) {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement pst = null;

        try {
            String query = "UPDATE nhanvien SET diaChi = ?, soDienThoai = ?, email = ? WHERE idNhanVien = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, newAddress);
            pst.setString(2, newMobilePhone);
            pst.setString(3, newEmail);
            pst.setInt(4, employeeId);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                view.displayMessage("Save information successfully");
            } else {
                view.displayMessage("Failed to save information");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            view.displayMessage("Error: " + ex.getMessage());
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    private void handleClick() {
        String newAddress = view.getAdressTxt();
        String newMobilePhone = view.getHomePhoneTxt();
        String newEmail = view.getEmailTxt();
        updateEmployeeData(1, newAddress, newMobilePhone, newEmail);
    }

    private void handleReset() {
        fetchDataFromDatabase(1);
    }

    private void handleClear() {
        view.setAddressTxtField();
        view.setHomePhoneTxtField();
        view.setEmailTxtField();
    }

    class ViewWindowListener extends WindowAdapter {
        @Override
        public void windowOpened(WindowEvent e) {
            fetchDataFromDatabase(1);
        }
    }


}
