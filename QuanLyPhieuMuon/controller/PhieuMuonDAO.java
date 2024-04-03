package controller;

import model.PhieuMuon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;


public class PhieuMuonDAO {
    private static final String INSERT_QUERY = "INSERT INTO phieumuon (idBanDoc, idSach, soLuong, ngayMuon) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM phieumuon";
    private static final String UPDATE_QUERY = "UPDATE phieumuon SET idBanDoc = ?, idSach = ?, soLuong = ?, ngayMuon = ? WHERE idPhieuMuon = ?";
    private static final String DELETE_QUERY = "DELETE FROM phieumuon WHERE idPhieuMuon = ?";
    private static DefaultTableModel tableModel;
    
    public static boolean insert(PhieuMuon phieuMuon) {
        try (Connection connection = DBConnection.Connect();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setInt(1, phieuMuon.getIdBanDoc());
            preparedStatement.setInt(2, phieuMuon.getIdSach());
            preparedStatement.setInt(3, phieuMuon.getSoLuong());
            preparedStatement.setDate(4, java.sql.Date.valueOf(phieuMuon.getNgayMuon()));

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static DefaultTableModel getAllPhieuMuon() {
        try (Connection connection = DBConnection.Connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            tableModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            int columnCount = resultSet.getMetaData().getColumnCount();
            
            String[] columnNames = new String[]{"ID", "ID Bạn Đọc", "ID sách", "Số Lượng", "Ngày Mượn"};
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(columnNames[i - 1]);
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

    public static boolean update(PhieuMuon phieuMuon) {
        try (Connection connection = DBConnection.Connect();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setInt(1, phieuMuon.getIdBanDoc());
            preparedStatement.setInt(2, phieuMuon.getIdSach());
            preparedStatement.setInt(3, phieuMuon.getSoLuong());
            preparedStatement.setDate(4, java.sql.Date.valueOf(phieuMuon.getNgayMuon()));
            preparedStatement.setInt(5, phieuMuon.getIdPhieuMuon());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int id) {
        try (Connection connection = DBConnection.Connect();
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
        try (Connection connection = DBConnection.Connect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM phieumuon ORDER BY GPA DESC");
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

    public static DefaultTableModel getAllStudentsSortedByName() {
        try (Connection connection = DBConnection.Connect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM phieumuon ORDER BY Name");
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
