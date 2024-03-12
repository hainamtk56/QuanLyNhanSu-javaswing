package QuanLyNhanVien.Controller;

import ConnectionManager.ConnectionManager;
import QuanLyNhanVien.Model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
public class NhanVienDAO {
    private static final String INSERT_QUERY = "INSERT INTO nhanvien (hoTen, ngaySinh, gioiTinh, tinhTrang, ngayVaoLam, ngayNghiViec, diaChi, soDienThoai, email, idChucVu, idPhongBan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT idNhanVien, hoTen, ngaySinh, gioiTinh, tinhTrang, ngayVaoLam, ngayNghiViec, diaChi, soDienThoai, email, tenChucVu, tenPhongBan FROM nhanvien nv LEFT JOIN chucvu cv ON nv.idChucVu = cv.idChucVu LEFT JOIN phongban pb ON nv.idPhongBan=pb.idPhongBan;";
    private static final String UPDATE_QUERY = "UPDATE nhanvien SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, tinhTrang = ?, ngayVaoLam = ?, ngayNghiViec = ?, diaChi = ?, soDienThoai = ?, email = ?, idChucVu = ?, idPhongBan = ? WHERE idNhanVien = ?";
    private static final String DELETE_QUERY = "DELETE FROM nhanvien WHERE idNhanVien = ?";
    private static DefaultTableModel tableModel;

    public static boolean insert(NhanVien nhanVien) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, nhanVien.getHoTen());
            preparedStatement.setDate(2, java.sql.Date.valueOf(nhanVien.getNgaySinh()));
            preparedStatement.setString(3, nhanVien.getGioiTinh());
            preparedStatement.setString(4, nhanVien.getTinhTrang());
            preparedStatement.setDate(5, java.sql.Date.valueOf(nhanVien.getNgayVaoLam()));
            preparedStatement.setDate(6, java.sql.Date.valueOf(nhanVien.getNgayNghiViec()));
            preparedStatement.setString(7, nhanVien.getDiaChi());
            preparedStatement.setString(8, nhanVien.getSoDienThoai());
            preparedStatement.setString(9, nhanVien.getEmail());
            preparedStatement.setInt(10, nhanVien.getIdChucVu());
            preparedStatement.setInt(11, nhanVien.getIdPhongBan());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static DefaultTableModel getAllEmployees() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            tableModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(resultSet.getMetaData().getColumnName(i));
            }

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(row);
            }
            return tableModel;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel();
    }

    public static boolean update(NhanVien nhanVien) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, nhanVien.getHoTen());
            preparedStatement.setDate(2, java.sql.Date.valueOf(nhanVien.getNgaySinh()));
            preparedStatement.setString(3, nhanVien.getGioiTinh());
            preparedStatement.setString(4, nhanVien.getTinhTrang());
            preparedStatement.setDate(5, java.sql.Date.valueOf(nhanVien.getNgayVaoLam()));
            preparedStatement.setDate(6, java.sql.Date.valueOf(nhanVien.getNgayNghiViec()));
            preparedStatement.setString(7, nhanVien.getDiaChi());
            preparedStatement.setString(8, nhanVien.getSoDienThoai());
            preparedStatement.setString(9, nhanVien.getEmail());
            preparedStatement.setInt(10, nhanVien.getIdChucVu());
            preparedStatement.setInt(11, nhanVien.getIdPhongBan());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int id) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static DefaultTableModel getAllStudentsSortedByGPA() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM nhanvien ORDER BY GPA DESC");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            tableModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(resultSet.getMetaData().getColumnName(i));
            }

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(row);
            }
            return tableModel;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel();
    }

    public static DefaultTableModel getAllEmployeeSortedByName() {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM nhanvien ORDER BY Name");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            tableModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(resultSet.getMetaData().getColumnName(i));
            }

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(row);
            }
            return tableModel;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel();
    }
}
