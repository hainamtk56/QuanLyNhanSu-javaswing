package QuanLyChamCong.Controller;

import ConnectionManager.ConnectionManager;
import QuanLyChamCong.Model.ChamCong;
import QuanLyChamCong.View.ChamCongView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChamCongController {
    private final ChamCongView chamCongView = new ChamCongView();

    public ChamCongController() {
        initializeListeners();
        chamCongView.addThemBtnListener(new AddBtnListener());
        chamCongView.addSuaBtnListener(new EditBtnListener());
        chamCongView.addXoaBtnListener(new DeleteBtnListener());
    }

    //linh
    private void initializeListeners() {
        chamCongView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                chamCongView.dispose();
            }
        });
    }

    public static DefaultTableModel getAllRecords() {
        DefaultTableModel defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT cc.idChamCong, cc.idNhanVien, nv.hoTen, nv.tinhTrang, cv.tenChucVu, pb.tenPhongBan, cc.ngayLamViec, cc.gioBatDau, cc.gioKetThuc\n" +
                     "FROM chamcong cc\n" +
                     "INNER JOIN nhanvien nv ON cc.idNhanVien = nv.idNhanVien\n" +
                     "INNER JOIN chucvu cv ON nv.idChucVu = cv.idChucVu\n" +
                     "INNER JOIN phongban pb ON nv.idPhongBan = pb.idPhongBan");
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

    public class AddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ChamCong chamCong = chamCongView.getChamCong();
                System.out.println(chamCong.toString());
                int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Thêm?", "Xác Nhận Thêm", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    insert(chamCong);
                    chamCongView.clearFields();
                    JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
                    updateTableData();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Thêm Thất Bại!");
                ex.printStackTrace();
            }
        }
    }

    public void insert(ChamCong chamCong) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ChamCong (idNhanVien, ngayLamViec, gioBatDau, gioKetThuc) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, chamCong.getIdNhanVien());
            preparedStatement.setDate(2, chamCong.getNgayLamViec());
            preparedStatement.setTime(3, chamCong.getGioBatDau());
            preparedStatement.setTime(4, chamCong.getGioKetThuc());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class EditBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ChamCong chamCong = chamCongView.getChamCong();
                int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Sửa?", "Xác Nhận Sửa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    update(chamCong);
                    chamCongView.clearFields();
                    JOptionPane.showMessageDialog(null, "Sửa Thành Công!");
                    updateTableData();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Sửa Thất Bại!");
                ex.printStackTrace();
            }
        }
    }

    public void update(ChamCong chamCong) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE chamcong SET idNhanVien = ?, ngayLamViec = ?, gioBatDau = ?, gioKetThuc = ? WHERE idChamCong = ?")) {
            preparedStatement.setInt(1, chamCong.getIdNhanVien());
            preparedStatement.setDate(2, chamCong.getNgayLamViec());
            preparedStatement.setTime(3, chamCong.getGioBatDau());
            preparedStatement.setTime(4, chamCong.getGioKetThuc());
            preparedStatement.setInt(5, chamCong.getIdChamCong());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class DeleteBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ChamCong chamCong = chamCongView.getChamCong();
                int option = JOptionPane.showConfirmDialog(null, "Bạn Có Chắc Muốn Xóa?", "Xác Nhận Xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    delete(chamCong);
                    chamCongView.clearFields();
                    JOptionPane.showMessageDialog(null, "Xóa Thành Công!");
                    updateTableData();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Xóa Thất Bại!");
                ex.printStackTrace();
            }
        }
    }

    public void delete(ChamCong chamCong) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM chamcong WHERE idChamCong = ?")) {
            preparedStatement.setInt(1, chamCong.getIdChamCong());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTableData() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) chamCongView.getChamCongTable().getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ để không trùng lặp
        DefaultTableModel chamCongList = getAllRecords(); // Giả sử đây là hàm lấy dữ liệu NV mới nhất
        for (int i = 0; i < chamCongList.getRowCount(); i++) {
            Object[] rowData = new Object[chamCongList.getColumnCount()];
            for (int j = 0; j < chamCongList.getColumnCount(); j++) {
                rowData[j] = chamCongList.getValueAt(i, j);
            }
            model.addRow(rowData);
        }
        // Refresh lại view
        chamCongView.getChamCongTable().repaint();
        chamCongView.getChamCongTable().validate();
    }

}

