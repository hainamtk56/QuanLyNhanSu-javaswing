package QuanLyPhongBan.Controller;

import ConnectionManager.ConnectionManager;
import QuanLyPhongBan.Model.PhongBan;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;

public class
PhongBanDAO {
    private static final String INSERT_QUERY = "INSERT INTO phongban (tenPhongBan) VALUES (?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM phongban";
    private static final String UPDATE_QUERY = "UPDATE phongban SET tenPhongBan = ? WHERE idPhongBan = ?";
    private static final String DELETE_QUERY = "DELETE FROM phongban WHERE idPhongBan = ?";
    private static DefaultTableModel tableModel;

    public boolean insert(PhongBan phongBan) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, phongBan.getTenPhongBan());
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Thêm phòng ban mới thành công.");
                return true;
            } else {
                System.out.println("Không thể thêm phòng ban mới.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Đã xảy ra lỗi khi thêm phòng ban mới:");
            e.printStackTrace();
            return false;
        }
    }

    public static DefaultTableModel getAllDepartments() {
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

    public static boolean update(PhongBan phongBan) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, phongBan.getTenPhongBan());
            preparedStatement.setInt(2, phongBan.getIdPhongBan());

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

    public DefaultTableModel sortById() {
        // Lấy dữ liệu hiện tại từ defaultTableModel
        DefaultTableModel model = getAllDepartments();

        // Sắp xếp dữ liệu theo ID
        model.getDataVector().sort(Comparator.comparing(row -> (Integer) row.get(0)));

        // Trả về mô hình dữ liệu đã được sắp xếp
        return model;
    }

    // Phương thức sắp xếp theo tên
    public DefaultTableModel sortByName() {
        // Lấy dữ liệu hiện tại từ defaultTableModel
        DefaultTableModel model = getAllDepartments();

        // Sắp xếp dữ liệu theo tên
        model.getDataVector().sort(Comparator.comparing(row -> (String) row.get(1)));

        // Trả về mô hình dữ liệu đã được sắp xếp
        return model;
    }
}
