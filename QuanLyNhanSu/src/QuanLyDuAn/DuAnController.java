package QuanLyDuAn;

import ConnectionManager.ConnectionManager;
import QuanLyDuAn.View.DuAnView;
import QuanLyDuAn.Model.DuAn;


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

public class DuAnController {
    private final DuAnView duAnView;
    private static final String INSERT_QUERY = "INSERT INTO duan (hoTen, ngaySinh, gioiTinh, tinhTrang, Duan, soDienThoai, email, idChucVu, idPhongBan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT idNhanVien, hoTen, ngaySinh, gioiTinh, tinhTrang, Duan, soDienThoai, email, tenChucVu, tenPhongBan FROM duan nv LEFT JOIN chucvu cv ON nv.idChucVu = cv.idChucVu LEFT JOIN phongban pb ON nv.idPhongBan=pb.idPhongBan;";
    private static final String UPDATE_QUERY = "UPDATE duan SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, tinhTrang = ?, Duan = ?, soDienThoai = ?, email = ?, idChucVu = ?, idPhongBan = ? WHERE idNhanVien = ?";
    private static final String DELETE_QUERY = "DELETE FROM duan WHERE idNhanVien = ?";

    public DuAnController() {
        ConnectionManager.getConnection();
        duAnView = new DuAnView();
        duAnView.addThemBtnListener(new AddBtnListener());
        duAnView.addSuaBtnListener(new UpdateBtnListener());
        duAnView.addXoaBtnListener(new DeleteBtnListener());
    }

    public class AddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DuAn duAn = duAnView.getDuAn();
                int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Thêm Nhân Viên Này?", "Xác Nhận Sửa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    insert(duAn);
                    duAnView.clearFields();
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
                DuAn duAn = duAnView.getDuAn();
                int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Sửa Nhân Viên Này?", "Xác Nhận Sửa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    update(duAn);
                    duAnView.clearFields();
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
                DuAn duAn = duAnView.getDuAn();
                int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Xóa Nhân Viên Này?", "Xác Nhận Xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    delete(duAn);
                    duAnView.clearFields();
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
        DefaultTableModel model = (DefaultTableModel) duAnView.getDuanTable().getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ để không trùng lặp
        DefaultTableModel duAnList = getAllEmployees(); // Giả sử đây là hàm lấy dữ liệu NV mới nhất
        for (int i = 0; i < duAnList.getRowCount(); i++) {
            Object[] rowData = new Object[duAnList.getColumnCount()];
            for (int j = 0; j < duAnList.getColumnCount(); j++) {
                rowData[j] = duAnList.getValueAt(i, j);
            }
            model.addRow(rowData);
        }
        // Refresh lại view
        duAnView.getDuanTable().repaint();
        duAnView.getDuanTable().validate();
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


            for (int i = 1; i <= columnCount; i++) {
                defaultTableModel.addColumn(resultSet.getMetaData().getColumnName(i));
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

    public static void insert(DuAn duAn) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, duAn.getHoTen());
            preparedStatement.setDate(2, java.sql.Date.valueOf(duAn.getNgaySinh()));
            preparedStatement.setString(3, duAn.getGioiTinh());
            preparedStatement.setString(4, duAn.getTinhTrang());
            preparedStatement.setString(5, duAn.getDuan());
            preparedStatement.setString(6, duAn.getSoDienThoai());
            preparedStatement.setString(7, duAn.getEmail());
            preparedStatement.setInt(8, duAn.getIdChucVu());
            preparedStatement.setInt(9, duAn.getIdPhongBan());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(DuAn duAn) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, duAn.getHoTen());
            preparedStatement.setDate(2, java.sql.Date.valueOf(duAn.getNgaySinh()));
            preparedStatement.setString(3, duAn.getGioiTinh());
            preparedStatement.setString(4, duAn.getTinhTrang());
            preparedStatement.setString(5, duAn.getDuan());
            preparedStatement.setString(6, duAn.getSoDienThoai());
            preparedStatement.setString(7, duAn.getEmail());
            preparedStatement.setInt(8, duAn.getIdChucVu());
            preparedStatement.setInt(9, duAn.getIdPhongBan());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(DuAn duAn) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, duAn.getIdNhanVien());
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
