package QuanLyNhanVien.View;

import ConnectionManager.ConnectionManager;
import QuanLyNhanVien.Controller.NhanVienDAO;
import QuanLyNhanVien.Controller.NhanVienViewListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.TableRowSorter;

public class NhanVienView extends JFrame {
    private NhanVienDAO nhanVienDAO;
    private NhanVienViewListener listener;
    private JButton addBtn, editBtn, deleteBtn, clearBtn;
    private JScrollPane scrollPaneTable;
    private DefaultTableModel defaultTableModel = NhanVienDAO.getAllEmployees();
    private JTable nhanVienTable = new JTable(defaultTableModel);

    private JLabel idNhanVienLabel, hoTenLabel, ngaySinhLabel, gioiTinhLabel, tinhTrangLabel, ngayVaoLamLabel, ngayNghiViecLabel, diaChiLabel, soDienThoaiLabel, emailLabel, tenChucVuLabel, tenPhongBanLabel;

    private JTextField idNhanVienField, hoTenField, ngaySinhField, gioiTinhField, tinhTrangField, ngayVaoLamField, ngayNghiViecField, soDienThoaiField, emailField, tenChucVuField, tenPhongBanField;
    private JTextArea diaChiTA;

    public NhanVienView() {
        ConnectionManager.getConnection();
        nhanVienDAO = new NhanVienDAO();
        listener = new NhanVienViewListener(nhanVienDAO);
        initComponents();
    }




    private void initComponents() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(defaultTableModel);
        nhanVienTable.setRowSorter(sorter);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addBtn = new JButton("Thêm");
        editBtn = new JButton("Sửa");
        deleteBtn = new JButton("Xóa");
        clearBtn = new JButton("Clear");

        idNhanVienLabel = new JLabel("ID");
        hoTenLabel = new JLabel("Họ Tên");
        ngaySinhLabel = new JLabel("Ngày Sinh");
        gioiTinhLabel = new JLabel("Giới Tính");
        tinhTrangLabel = new JLabel("Tình Trạng");
        ngayVaoLamLabel = new JLabel("Ngày Vào Làm");
        ngayNghiViecLabel = new JLabel("Ngày Nghỉ Việc");
        diaChiLabel = new JLabel("Địa Chỉ");
        soDienThoaiLabel = new JLabel("Số Điện Thoại");
        emailLabel = new JLabel("Email");
        tenChucVuLabel = new JLabel("Chức vụ");
        tenPhongBanLabel = new JLabel("Phòng Ban");


        idNhanVienField = new JTextField(6);
        idNhanVienField.setEditable(false);
        hoTenField = new JTextField(15);
        ngaySinhField = new JTextField(10);
        gioiTinhField = new JTextField(6);
        tinhTrangField = new JTextField(15);
        ngayVaoLamField = new JTextField(15);
        ngayNghiViecField = new JTextField(15);
        diaChiTA = new JTextArea();
        diaChiTA.setColumns(15);
        diaChiTA.setRows(2);
        soDienThoaiField = new JTextField(15);
        emailField = new JTextField(15);
        tenChucVuField = new JTextField(15);
        tenPhongBanField = new JTextField(15);

        JScrollPane scrollPaneDiaChi = new JScrollPane(diaChiTA);

        scrollPaneTable = new JScrollPane(nhanVienTable);
        scrollPaneTable.setPreferredSize(new Dimension(1247, 400));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1300, 800);
        panel.setLayout(layout);
        panel.add(scrollPaneTable);

        panel.add(idNhanVienLabel);
        panel.add(hoTenLabel);
        panel.add(ngaySinhLabel);
        panel.add(gioiTinhLabel);
        panel.add(tinhTrangLabel);
        panel.add(ngayVaoLamLabel);
        panel.add(ngayNghiViecLabel);
        panel.add(diaChiLabel);
        panel.add(soDienThoaiLabel);
        panel.add(emailLabel);
        panel.add(tenChucVuLabel);
        panel.add(tenPhongBanLabel);

        panel.add(idNhanVienField);
        panel.add(hoTenField);
        panel.add(ngaySinhField);
        panel.add(gioiTinhField);
        panel.add(tinhTrangField);
        panel.add(ngayVaoLamField);
        panel.add(ngayNghiViecField);
        panel.add(scrollPaneDiaChi);
        panel.add(soDienThoaiField);
        panel.add(emailField);
        panel.add(tenChucVuField);
        panel.add(tenPhongBanField);

        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(clearBtn);

        // bang nhan vien
        layout.putConstraint(SpringLayout.WEST, scrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, scrollPaneTable, 20, SpringLayout.NORTH, panel);

        // cot 1
        layout.putConstraint(SpringLayout.WEST, idNhanVienLabel, 20, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, idNhanVienLabel, 20, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, idNhanVienField, 90, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, idNhanVienField, 20, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, hoTenLabel, 20, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, hoTenLabel, 60, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, hoTenField, 90, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, hoTenField, 60, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, ngaySinhLabel, 20, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, ngaySinhLabel, 100, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, ngaySinhField, 90, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, ngaySinhField, 100, SpringLayout.SOUTH, scrollPaneTable);

        //cot 2
        layout.putConstraint(SpringLayout.WEST, gioiTinhLabel, 285, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, gioiTinhLabel, 20, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, gioiTinhField, 375, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, gioiTinhField, 20, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, tinhTrangLabel, 285, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, tinhTrangLabel, 60, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, tinhTrangField, 375, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, tinhTrangField, 60, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, ngayVaoLamLabel, 285, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, ngayVaoLamLabel, 100, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, ngayVaoLamField, 375, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, ngayVaoLamField, 100, SpringLayout.SOUTH, scrollPaneTable);

        //cot 3
        layout.putConstraint(SpringLayout.WEST, ngayNghiViecLabel, 570, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, ngayNghiViecLabel, 20, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, ngayNghiViecField, 670, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, ngayNghiViecField, 20, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, diaChiLabel, 570, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, diaChiLabel, 60, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, scrollPaneDiaChi, 670, SpringLayout.WEST,panel );
        layout.putConstraint(SpringLayout.NORTH, scrollPaneDiaChi, 60, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, soDienThoaiLabel, 570, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, soDienThoaiLabel, 100, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, soDienThoaiField, 670, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, soDienThoaiField, 100, SpringLayout.SOUTH, scrollPaneTable);

        // cot 4
        layout.putConstraint(SpringLayout.WEST, emailLabel, 860, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, emailLabel, 20, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, emailField, 940, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, emailField, 20, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, tenChucVuLabel, 860, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenChucVuLabel, 60, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, tenChucVuField, 940, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenChucVuField, 60, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, tenPhongBanLabel, 860, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenPhongBanLabel, 100, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, tenPhongBanField, 940, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenPhongBanField, 100, SpringLayout.SOUTH, scrollPaneTable);

        // nut
        layout.putConstraint(SpringLayout.WEST, addBtn, 1115, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addBtn, 25, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, editBtn, 1210, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, editBtn, 25, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, clearBtn, 1115, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 85, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, deleteBtn, 1210, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, deleteBtn, 85, SpringLayout.SOUTH, scrollPaneTable);

//        deleteBtn.addActionListener(listener.createDeleteBtnListener());
//        addBtn.addActionListener(listener.createAddBtnListener());
//        editBtn.addActionListener(listener.createEditBtnListener());
//        clearBtn.addActionListener(listener.createClearBtnListener());

//        public void addBtnListener(NhanVienDAO.addBtnListener listener) {
//            addBtn.addActionListener(listener);
//        }
//        addBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int option = JOptionPane.showConfirmDialog(NhanVienView.this, "Are you sure you want to add?", "Add Confirmation", JOptionPane.YES_NO_OPTION);
//                if (option == JOptionPane.YES_OPTION) {
//                    String name = hoTenField.getText();
//                    int age = Integer.parseInt(ageField.getText());
//                    String address = diaChiTA.getText();
//                    double gpa = Double.parseDouble(gpaField.getText());
//
//                    NhanVien nhanVien = new NhanVien(1, name, age, address, gpa);
//
//                    if (NhanVienDAO.insert(student)) {
//                        JOptionPane.showMessageDialog(panel, "Add successfully!");
//                        refreshTable();
//                        clearFields();
//                    } else {
//                        JOptionPane.showMessageDialog(panel, "Addition failed. Please try again.", "Error!", JOptionPane.ERROR);
//                    }
//                }
//
//            }
//        });
//
//        editBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int option = JOptionPane.showConfirmDialog(NhanVienView.this, "Are you sure you want to edit?", "Edition Confirmation", JOptionPane.YES_NO_OPTION);
//                if (option == JOptionPane.YES_OPTION) {
//                    int id = Integer.parseInt(idNhanVienField.getText());
//                    String name = hoTenField.getText();
//                    int age = Integer.parseInt(ageField.getText());
//                    String address = diaChiTA.getText();
//                    double gpa = Double.parseDouble(gpaField.getText());
//
//                    NhanVien student = new NhanVien(id, name, age, address, gpa);
//                    if (NhanVienDAO.update(student)) {
//                        JOptionPane.showMessageDialog(panel, "Edit successfully!");
//                        refreshTable();
//                        clearFields();
//                    } else {
//                        JOptionPane.showMessageDialog(panel, "edition failed. Please try again.", "Error!", JOptionPane.ERROR);
//                    }
//                }
//            }
//        });

//        deleteBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
//                if (option == JOptionPane.YES_OPTION) {
//                    int id = Integer.parseInt(idNhanVienField.getText());
//                    if (NhanVienDAO.delete(id)) {
//                        JOptionPane.showMessageDialog(panel, "Deleted successfully!");
//                        refreshTable();
//                        clearFields();
//                    } else {
//                        JOptionPane.showMessageDialog(panel, "Deletion failed. Please try again.");
//                    }
//                }
//            }
//        });
//
//
//        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                // Kiểm tra xem sự kiện có phải là sự kiện kết thúc chọn không
//                if (!e.getValueIsAdjusting()) {
//                    // Lấy hàng được chọn từ bảng
//                    int selectedRow = studentTable.getSelectedRow();
//
//                    // Kiểm tra xem có hàng được chọn không
//                    if (selectedRow != -1) {
//                        // Lấy dữ liệu từ bảng và hiển thị vào các textfield
//                        int id = (int) studentTable.getValueAt(selectedRow, 0);
//                        String name = (String) studentTable.getValueAt(selectedRow, 1);
//                        int age = (int) studentTable.getValueAt(selectedRow, 2);
//                        String address = (String) studentTable.getValueAt(selectedRow, 3);
//                        BigDecimal gpaBigDecimal = (BigDecimal) studentTable.getValueAt(selectedRow, 4);
//                        double gpa = gpaBigDecimal.doubleValue();
//
//
//                        idNhanVienField.setText(String.valueOf(id));
//                        hoTenField.setText(name);
//                        ageField.setText(String.valueOf(age));
//                        diaChiTA.setText(address);
//                        gpaField.setText(String.valueOf(gpa));
//                    }
//                }
//            }
//        });
//
//        sortGPABtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                defaultTableModel = NhanVienDAO.getAllsSortedByGPA();
//                studentTable.setModel(defaultTableModel);
//            }
//        });
//
//        sortNameBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                defaultTableModel = NhanVienDAO.getAllNhanViensSortedByName();
//                studentTable.setModel(defaultTableModel);
//            }
//        });
        clearBtn.addActionListener(this::clearFieldsActionPerformed);

        add(panel);
        setTitle("Quản Lý Nhân Viên");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void refreshTable() {
        defaultTableModel = NhanVienDAO.getAllEmployees();
        nhanVienTable.setModel(defaultTableModel);
    }

    private void clearFieldsActionPerformed(ActionEvent e) {
        clearFields();
    }

    private void clearFields() {
        idNhanVienField.setText("");
        hoTenField.setText("");
        ngaySinhField.setText("");
        gioiTinhField.setText("");
        tinhTrangField.setText("");
        ngayVaoLamField.setText("");
        ngayNghiViecField.setText("");
        diaChiTA.setText("");
        soDienThoaiField.setText("");
        emailField.setText("");
        tenChucVuField.setText("");
        tenPhongBanField.setText("");
    }
}
