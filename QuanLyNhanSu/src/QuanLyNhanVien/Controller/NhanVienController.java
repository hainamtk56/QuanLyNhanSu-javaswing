package QuanLyNhanVien.Controller;

import ConnectionManager.ConnectionManager;
import QuanLyNhanVien.Model.NhanVien;
import QuanLyNhanVien.View.NhanVienView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class NhanVienController {
    private final NhanVienView nhanVienView;
    private static final String INSERT_QUERY = "INSERT INTO nhanvien (hoTen, ngaySinh, gioiTinh, tinhTrang, ngayVaoLam, ngayNghiViec, diaChi, soDienThoai, email, idChucVu, idPhongBan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT idNhanVien, hoTen, ngaySinh, gioiTinh, tinhTrang, ngayVaoLam, ngayNghiViec, diaChi, soDienThoai, email, tenChucVu, tenPhongBan FROM nhanvien nv LEFT JOIN chucvu cv ON nv.idChucVu = cv.idChucVu LEFT JOIN phongban pb ON nv.idPhongBan=pb.idPhongBan;";
    private static final String UPDATE_QUERY = "UPDATE nhanvien SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, tinhTrang = ?, ngayVaoLam = ?, ngayNghiViec = ?, diaChi = ?, soDienThoai = ?, email = ?, idChucVu = ?, idPhongBan = ? WHERE idNhanVien = ?";
    private static final String DELETE_QUERY = "DELETE FROM nhanvien WHERE idNhanVien = ?";

    public NhanVienController() {
        ConnectionManager.getConnection();
        nhanVienView = new NhanVienView();
        nhanVienView.addThemBtnListener(new AddBtnListener());
        nhanVienView.addSuaBtnListener(new UpdateBtnListener());
        nhanVienView.addXoaBtnListener(new DeleteBtnListener());
    }

    public class AddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                NhanVien nhanVien = nhanVienView.getNhanVien();
                int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Thêm Nhân Viên Này?", "Xác Nhận Sửa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    insert(nhanVien);
                    nhanVienView.clearFields();
                    JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
                    updateTableData();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Thêm Thất Bại!");
                ex.printStackTrace();
            }
        }
    }

    public class UpdateBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                NhanVien nhanVien = nhanVienView.getNhanVien();
                int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Sửa Nhân Viên Này?", "Xác Nhận Sửa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    update(nhanVien);
                    nhanVienView.clearFields();
                    JOptionPane.showMessageDialog(null, "Sửa Thành Công!");
                    updateTableData();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Sửa Thất Bại!");
                ex.printStackTrace();
            }
        }
    }

    public class DeleteBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                NhanVien nhanVien = nhanVienView.getNhanVien();
                int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Xóa Nhân Viên Này?", "Xác Nhận Xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    delete(nhanVien);
                    nhanVienView.clearFields();
                    JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
                    updateTableData();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Xóa Thất Bại!");
                ex.printStackTrace();
            }
        }
    }

    public void updateTableData() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) nhanVienView.getNhanVienTable().getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ để không trùng lặp
        DefaultTableModel nhanVienList = getAllEmployees(); // Giả sử đây là hàm lấy dữ liệu NV mới nhất
        for (int i = 0; i < nhanVienList.getRowCount(); i++) {
            Object[] rowData = new Object[nhanVienList.getColumnCount()];
            for (int j = 0; j < nhanVienList.getColumnCount(); j++) {
                rowData[j] = nhanVienList.getValueAt(i, j);
            }
            model.addRow(rowData);
        }
        // Refresh lại view
        nhanVienView.getNhanVienTable().repaint();
        nhanVienView.getNhanVienTable().validate();
    }

    public static int getIdChucVuFromDatabase(String tenChucVu) {
        int idChucVu = 0;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT idChucVu FROM ChucVu WHERE tenChucVu = ?")) {
            preparedStatement.setString(1, tenChucVu);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idChucVu = resultSet.getInt("idChucVu");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return idChucVu;
    }

    public static int getIdPhongBanFromDatabase(String tenPhongBan) {
        int idPhongBan = 0;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT idPhongBan FROM PhongBan WHERE tenPhongBan = ?")) {
            preparedStatement.setString(1, tenPhongBan);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idPhongBan = resultSet.getInt("idPhongBan");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return idPhongBan;
    }

    public static DefaultTableModel getAllEmployees() {
        DefaultTableModel defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Chỉ định kiểu dữ liệu cho từng cột
                return switch (columnIndex) {
                    case 0 -> Integer.class; // Cột ID là kiểu Integer
                    case 2, 5, 6 ->
                            LocalDate.class; // Các cột Ngày Sinh, Ngày Vào Làm, Ngày Nghỉ Việc là kiểu LocalDate
                    default -> String.class; // Các cột còn lại là kiểu String
                };
            }
        };
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            int columnCount = resultSet.getMetaData().getColumnCount();

            String[] columnNames = new String[]{"ID", "Họ Tên", "Ngày Sinh", "Giới Tính", "Tình Trạng", "Ngày Vào Làm", "Ngày Nghỉ Việc", "Địa Chỉ", "Số Điện Thoại", "Email", "Chức Vụ", "Phòng Ban"};
            for (int i = 1; i <= columnCount; i++) {
                defaultTableModel.addColumn(columnNames[i - 1]);
            }

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                defaultTableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return defaultTableModel;
    }

    public static void insert(NhanVien nhanVien) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, nhanVien.getHoTen());
            preparedStatement.setDate(2, java.sql.Date.valueOf(nhanVien.getNgaySinh()));
            preparedStatement.setString(3, nhanVien.getGioiTinh());
            preparedStatement.setString(4, nhanVien.getTinhTrang());
            preparedStatement.setDate(5, java.sql.Date.valueOf(nhanVien.getNgayVaoLam()));
            // Kiểm tra ngày nghỉ việc có null hay không trước khi sử dụng
            LocalDate ngayNghiViec = nhanVien.getNgayNghiViec();
            if (ngayNghiViec != null) {
                preparedStatement.setDate(6, java.sql.Date.valueOf(ngayNghiViec));
            } else {
                preparedStatement.setNull(6, java.sql.Types.DATE);
            }
            preparedStatement.setString(7, nhanVien.getDiaChi());
            preparedStatement.setString(8, nhanVien.getSoDienThoai());
            preparedStatement.setString(9, nhanVien.getEmail());
            preparedStatement.setInt(10, nhanVien.getIdChucVu());
            preparedStatement.setInt(11, nhanVien.getIdPhongBan());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(NhanVien nhanVien) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, nhanVien.getHoTen());
            preparedStatement.setDate(2, java.sql.Date.valueOf(nhanVien.getNgaySinh()));
            preparedStatement.setString(3, nhanVien.getGioiTinh());
            preparedStatement.setString(4, nhanVien.getTinhTrang());
            preparedStatement.setDate(5, java.sql.Date.valueOf(nhanVien.getNgayVaoLam()));
            LocalDate ngayNghiViec = nhanVien.getNgayNghiViec();
            if (ngayNghiViec != null) {
                preparedStatement.setDate(6, java.sql.Date.valueOf(ngayNghiViec));
            } else {
                preparedStatement.setNull(6, java.sql.Types.DATE);
            }
            preparedStatement.setString(7, nhanVien.getDiaChi());
            preparedStatement.setString(8, nhanVien.getSoDienThoai());
            preparedStatement.setString(9, nhanVien.getEmail());
            preparedStatement.setInt(10, nhanVien.getIdChucVu());
            preparedStatement.setInt(11, nhanVien.getIdPhongBan());
            preparedStatement.setInt(12, nhanVien.getIdNhanVien());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(NhanVien nhanVien) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, nhanVien.getIdNhanVien());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getAllChucVuFromDatabase() {
        ArrayList<String> chucVuList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT tenChucVu FROM chucvu");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                chucVuList.add(resultSet.getString("tenChucVu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chucVuList;
    }

    public static ArrayList<String> getAllPhongBanFromDatabase() {
        ArrayList<String> phongBanList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT tenPhongBan FROM phongban");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                phongBanList.add(resultSet.getString("tenPhongBan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phongBanList;
    }
}

