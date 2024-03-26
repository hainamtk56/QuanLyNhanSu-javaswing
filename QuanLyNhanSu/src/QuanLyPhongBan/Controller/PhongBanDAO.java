package QuanLyPhongBan.Controller;

import ConnectionManager.ConnectionManager;
import QuanLyPhongBan.Model.PhongBan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class PhongBanDAO {
    private static final String INSERT_QUERY = "INSERT INTO phongban (tenPhongBan) VALUES (?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM phongban";
    private static final String UPDATE_QUERY = "UPDATE phongban SET tenPhongBan = ? WHERE idPhongBan = ?";
    private static final String DELETE_QUERY = "DELETE FROM phongban WHERE idPhongBan = ?";
    private static DefaultTableModel tableModel;

    public static boolean insert(PhongBan phongBan) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, phongBan.getTenPhongBan());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
}
