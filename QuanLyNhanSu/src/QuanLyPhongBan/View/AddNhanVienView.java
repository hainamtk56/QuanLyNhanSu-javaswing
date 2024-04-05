package QuanLyPhongBan.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ConnectionManager.ConnectionManager;

public class AddNhanVienView extends JFrame {
    private int idPhongBan;
    private String tenPhongBan;
    private ChiTietPhongBanView chiTietPhongBanView;
    private JTable nhanVienTable;
    private int selectedIdPhongBan;

    public AddNhanVienView(int idPhongBan, ChiTietPhongBanView chiTietPhongBanView) {
        this.idPhongBan = idPhongBan;
        this.selectedIdPhongBan = idPhongBan; // Khởi tạo selectedIdPhongBan với giá trị ban đầu của idPhongBan
        this.chiTietPhongBanView = chiTietPhongBanView;
        initComponents();
        loadNhanVienData();
    }

    public void setSelectedIdPhongBan(int selectedIdPhongBan) {
        this.selectedIdPhongBan = selectedIdPhongBan;
    }

    // Cập nhật dữ liệu nhân viên dựa trên selectedIdPhongBan thay vì giá trị cố định 7
    private void loadNhanVienData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tên Nhân Viên");

        try {
            Connection connection = ConnectionManager.getConnection();
            String sql = "SELECT hoTen FROM nhanvien WHERE idPhongBan = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 7); // Sử dụng selectedIdPhongBan
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String tenNhanVien = resultSet.getString("hoTen");
                model.addRow(new Object[]{tenNhanVien});
            }

            nhanVienTable.setModel(model);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateNhanVienIdPhongBan(String tenNhanVien, int newIdPhongBan) {
        try {
            Connection connection = ConnectionManager.getConnection();
            String sql = "UPDATE nhanvien SET idPhongBan = ? WHERE hoTen = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, newIdPhongBan);
            statement.setString(2, tenNhanVien);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                // Cập nhật idPhongBan của AddNhanVienView với giá trị mới
                this.idPhongBan = newIdPhongBan;
            } else {
                JOptionPane.showMessageDialog(null, "Không thể thêm nhân viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setChiTietPhongBanViewListener(ChiTietPhongBanView chiTietPhongBanView) {
        this.chiTietPhongBanView = chiTietPhongBanView;
    }
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thêm Nhân Viên");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JScrollPane scrollPane = new JScrollPane();
        nhanVienTable = new JTable();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        scrollPane.setViewportView(nhanVienTable);

        // Thêm nút Xác nhận
        JButton confirmButton = new JButton("Xác nhận");
        getContentPane().add(confirmButton, BorderLayout.SOUTH);

        // Thêm sự kiện cho nút Xác nhận
        confirmButton.addActionListener(e -> {
            int row = nhanVienTable.getSelectedRow();
            if (row != -1) {
                String selectedName = (String) nhanVienTable.getValueAt(row, 0);
                int option = JOptionPane.showConfirmDialog(null, "Bạn muốn thêm nhân viên " + selectedName + " vào phòng ban ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Thêm nhân viên vào ChiTietPhongBanView
                    chiTietPhongBanView.addNhanVien(selectedName);
                    // Thực hiện cập nhật idPhongBan của nhân viên trong cơ sở dữ liệu
                    updateNhanVienIdPhongBan(selectedName, selectedIdPhongBan); // Sử dụng selectedIdPhongBan thay vì 7
                    dispose(); // Đóng cửa sổ sau khi thêm thành công
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để thêm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
