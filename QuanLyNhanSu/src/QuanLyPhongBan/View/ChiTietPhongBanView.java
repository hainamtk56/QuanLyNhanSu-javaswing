package QuanLyPhongBan.View;

import ConnectionManager.ConnectionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietPhongBanView extends JFrame {
    private static int idPhongBan;
    private JTable nhanVienTable;
    private JButton addBtnChiTiet, deleteBtnChiTiet;
    private String tenPhongBan;

    public ChiTietPhongBanView(int idPhongBan, String tenPhongBan) {
        this.idPhongBan = idPhongBan;
        this.tenPhongBan = tenPhongBan;
        initComponents();
        loadNhanVienData();
    }

    void loadNhanVienData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tên Nhân Viên");

        try {
            Connection connection = ConnectionManager.getConnection();
            String sql = "SELECT hoTen FROM nhanvien WHERE idPhongBan = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idPhongBan);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String tenNhanVien = resultSet.getString("hoTen");
                model.addRow(new Object[]{tenNhanVien});
            }

            nhanVienTable.setModel(model);

            // Đóng kết nối
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        int idPhongBan = Integer.parseInt(args[0]);
        String tenPhongBan = args[1];

        SwingUtilities.invokeLater(() -> {
            ChiTietPhongBanView chiTietPhongBanView = new ChiTietPhongBanView(idPhongBan, tenPhongBan);
            chiTietPhongBanView.setVisible(true);
        });
    }


    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chi Tiết Phòng Ban");
        setSize(600, 400);
        setLocationRelativeTo(null);

// Create components
        JScrollPane scrollPane = new JScrollPane();
        nhanVienTable = new JTable();
        addBtnChiTiet = new JButton("Thêm");
        deleteBtnChiTiet = new JButton("Xóa");

// Tạo panel chứa thanh tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField searchField = new JTextField(20); // 20 là độ rộng của textField
        JButton searchBtn = new JButton("Tìm kiếm");
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

// Layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

// Tạo panel chứa nút "Thêm", "Xóa" và thanh tìm kiếm
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addBtnChiTiet);
        buttonPanel.add(deleteBtnChiTiet);
        buttonPanel.add(searchPanel);

        getContentPane().add(buttonPanel, BorderLayout.PAGE_END); // Đặt panel chứa nút vào phía dưới của cửa sổ

// Add components
        scrollPane.setViewportView(nhanVienTable);

        searchBtn.addActionListener(e -> {
            String searchQuery = searchField.getText().trim();
            if (!searchQuery.isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) nhanVienTable.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    String tenNhanVien = (String) model.getValueAt(i, 0);
                    if (tenNhanVien.toLowerCase().contains(searchQuery.toLowerCase())) {
                        nhanVienTable.setRowSelectionInterval(i, i);
                        nhanVienTable.scrollRectToVisible(nhanVienTable.getCellRect(i, 0, true));
                        JOptionPane.showMessageDialog(null, "Đã tìm thấy nhân viên: " + tenNhanVien, "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên nào.", "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khóa tìm kiếm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Thêm sự kiện cho nút "Thêm nhân viên"
        addBtnChiTiet.addActionListener(e -> {
            AddNhanVienView addNhanVienView = new AddNhanVienView(idPhongBan, this);
            addNhanVienView.setVisible(true);
        });



        // Thêm sự kiện cho nút "Xóa nhân viên"
        deleteBtnChiTiet.addActionListener(e -> {
            int selectedRow = nhanVienTable.getSelectedRow();
            if (selectedRow != -1) {
                // Lấy tên nhân viên từ hàng được chọn
                String tenNhanVien = (String) nhanVienTable.getValueAt(selectedRow, 0);
                // Hiển thị hộp thoại xác nhận
                int option = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa nhân viên " + tenNhanVien + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Gọi phương thức chuyển idPhongBan của nhân viên
                   xoaNhanVien(idPhongBan, tenNhanVien);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Phương thức để chuyển idPhongBan của nhân viên
    // Phương thức để xóa nhân viên và cập nhật idPhongBan thành 7
    private void xoaNhanVien(int idPhongBan, String tenNhanVien) {
        try {
            // Kết nối đến cơ sở dữ liệu sử dụng ConnectionManager
            Connection connection = ConnectionManager.getConnection();
            // Cập nhật idPhongBan của nhân viên thành 7
            String sql = "UPDATE nhanvien SET idPhongBan = ? WHERE hoTen = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 7); // Cập nhật idPhongBan thành 7
            statement.setString(2, tenNhanVien);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                // Load lại dữ liệu nhân viên trong ChiTietPhongBanView
                loadNhanVienData();
            } else {
                JOptionPane.showMessageDialog(null, "Không thể xóa nhân viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            // Đóng kết nối
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Phương thức để thêm nhân viên vào bảng nhân viên
    public void addNhanVien(String tenNhanVien) {
        DefaultTableModel model = (DefaultTableModel) nhanVienTable.getModel();
        model.addRow(new Object[]{tenNhanVien});
    }
}
