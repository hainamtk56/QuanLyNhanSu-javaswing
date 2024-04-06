package QuanLyLuong.Controller;

import ConnectionManager.ConnectionManager;
import QuanLyLuong.Model.Luong;
import QuanLyLuong.View.LuongView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LuongController {
    private final LuongView luongView = new LuongView();

    public LuongController() {
        luongView.addThemBtnListener(new AddBtnListener());
        luongView.addSuaBtnListener(new EditBtnListener());
        luongView.addXoaBtnListener(new DeleteBtnListener());
    }

    public static DefaultTableModel getAllReCords() {
        DefaultTableModel defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

//            @Override
//            public Class<?> getColumnClass(int columnIndex) {
//                // Chỉ định kiểu dữ liệu cho từng cột
//                return switch (columnIndex) {
//                    case 0 -> Integer.class; // Cột ID là kiểu Integer
//                    case 2, 5, 6 ->
//                            LocalDate.class; // Các cột Ngày Sinh, Ngày Vào Làm, Ngày Nghỉ Việc là kiểu LocalDate
//                    default -> String.class; // Các cột còn lại là kiểu String
//                };
//            }
        };
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT luong.idLuong, nhanvien.idNhanVien, nhanvien.hoTen, nhanvien.tinhTrang, chucvu.tenChucVu, phongban.tenPhongBan, chucvu.tienCongMotGio, luong.nam, luong.thang, luong.soGioLam, luong.thuong, luong.thucLanh FROM luong LEFT JOIN nhanvien ON luong.idNhanVien = nhanvien.idNhanVien LEFT JOIN chucvu ON nhanvien.idChucVu = chucvu.idChucVu LEFT JOIN phongban ON nhanvien.idPhongBan = phongban.idPhongBan;");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            int columnCount = resultSet.getMetaData().getColumnCount();

            String[] columnNames = new String[]{"ID Lương", "ID Nhân Viên", "Họ Tên", "Tình Trạng", "Chức Vụ", "Phòng Ban", "Tiền Công Một Giờ",  "Năm", "Tháng", "Số Giờ Làm", "Thưởng", "Thực Lãnh"};
            for (int i = 1; i <= columnCount; i++) {
                defaultTableModel.addColumn(columnNames[i - 1]);
            }
//            for (int i = 1; i <= columnCount; i++) {
//                defaultTableModel.addColumn(resultSet.getMetaData().getColumnName(i));
//            }

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

    public class AddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Luong luong = luongView.getLuong();
                int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Thêm?", "Xác Nhận Thêm", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    insert(luong);
                    luongView.clearFields();
                    JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
                    updateTableData();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Thêm Thất Bại!");
                ex.printStackTrace();
            }
        }
    }

    public static void insert(Luong luong) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO luong (idNhanVien, nam, thang, soGioLam, thuong, thucLanh) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, luong.getIdNhanVien());
            preparedStatement.setInt(2, luong.getNam());
            preparedStatement.setInt(3, luong.getThang());
            preparedStatement.setInt(4, luong.getSoGioLam());
            preparedStatement.setInt(5, luong.getThuong());
            preparedStatement.setInt(6, luong.getThucLanh());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class EditBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Luong luong = luongView.getLuong();
                int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Sửa?", "Xác Nhận Sửa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    update(luong);
                    luongView.clearFields();
                    JOptionPane.showMessageDialog(null, "Sửa Thành Công!");
                    updateTableData();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Sửa Thất Bại!");
                ex.printStackTrace();
            }
        }
    }

    public static void update(Luong luong) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE luong SET idNhanVien = ?, nam = ?, thang = ?, soGioLam = ?, thuong = ?, thucLanh = ? WHERE idLuong = ?")) {
            preparedStatement.setInt(1, luong.getIdNhanVien());
            preparedStatement.setInt(2, luong.getNam());
            preparedStatement.setInt(3, luong.getThang());
            preparedStatement.setInt(4, luong.getSoGioLam());
            preparedStatement.setInt(5, luong.getThuong());
            preparedStatement.setInt(6, luong.getThucLanh());
            preparedStatement.setInt(7, luong.getIdLuong());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public class DeleteBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Luong luong = luongView.getLuong();
                int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Xóa Nhân Viên Này?", "Xác Nhận Xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    delete(luong);
                    luongView.clearFields();
                    JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
                    updateTableData();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Xóa Thất Bại!");
                ex.printStackTrace();
            }
        }
    }

    public void delete(Luong luong) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM luong WHERE idLuong = ?")) {
            preparedStatement.setInt(1, luong.getIdLuong());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getTienCongMotGio(int idNhanVien) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT tienCongMotGio FROM chucvu LEFT JOIN nhanvien ON chucvu.idChucVu = nhanvien.idChucVu WHERE idNhanVien = ?")) {
            preparedStatement.setInt(1, idNhanVien);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("tienCongMotGio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateTableData() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) luongView.getLuongTable().getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ để không trùng lặp
        DefaultTableModel luongList = getAllReCords(); // Giả sử đây là hàm lấy dữ liệu NV mới nhất
        for (int i = 0; i < luongList.getRowCount(); i++) {
            Object[] rowData = new Object[luongList.getColumnCount()];
            for (int j = 0; j < luongList.getColumnCount(); j++) {
                rowData[j] = luongList.getValueAt(i, j);
            }
            model.addRow(rowData);
        }
        // Refresh lại view
        luongView.getLuongTable().repaint();
        luongView.getLuongTable().validate();
    }
}

