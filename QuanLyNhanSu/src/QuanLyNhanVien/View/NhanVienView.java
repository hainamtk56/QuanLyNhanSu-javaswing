package QuanLyNhanVien.View;

import QuanLyNhanVien.Controller.NhanVienController;
import QuanLyNhanVien.Model.NhanVien;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.swing.table.TableRowSorter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JComboBox;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class NhanVienView extends JFrame implements ActionListener {
    private JButton themBtn, suaBtn, xoaBtn, clearBtn;
    private final DefaultTableModel defaultTableModel = NhanVienController.getAllEmployees();
    private final JTable nhanVienTable = new JTable(defaultTableModel);

    private JTextField idNhanVienField, hoTenField, ngaySinhField, ngayVaoLamField, ngayNghiViecField, soDienThoaiField, emailField;
    private JComboBox<String> gioiTinhComboBox, tinhTrangComboBox, tenChucVuComboBox, tenPhongBanComboBox;
    private JTextArea diaChiTA;
    private JComboBox<String> genderFilterComboBox;
    private JComboBox<String> statusFilterComboBox;
    private JComboBox<String> positionFilterComboBox;
    private JComboBox<String> departmentFilterComboBox;
    TableRowSorter<DefaultTableModel> sorter;
    JTextField nameSearchField = new JTextField(11);
    JButton resetButton;

    public NhanVienView() {
        initComponents();
        loadDataFromDatabase();
    }


    private void initComponents() {
        nameSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateNameFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateNameFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateNameFilter();
            }

            // Phương thức này cập nhật bộ lọc dựa trên nội dung nhập vào
            private void updateNameFilter() {
                String text = nameSearchField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    // Tạo bộ lọc và cập nhật nó trên sorter
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text), 1)); // 1 là chỉ mục cột của họ tên
                }
            }
        });
        sorter = new TableRowSorter<>(defaultTableModel);
        sorter.setSortable(0, true); // Cột ID
        sorter.setSortable(1, true); // Cột hoTen
        sorter.setSortable(2, true); // Cột ngaySinh
        sorter.setSortable(3, false); // gioi tinh
        sorter.setSortable(4, false); // tinh trang
        sorter.setSortable(5, true); // ngay vao lam
        sorter.setSortable(6, true); // ngay nghi viec
        sorter.setSortable(7, true); // dia chi
        sorter.setSortable(8, false); // sdt
        sorter.setSortable(9, false); // email
        sorter.setSortable(10, false); // ten chuc vu
        sorter.setSortable(11, false); // ten phong ban
        nhanVienTable.setRowSorter(sorter);

        // Tạo FilterActionListener và gắn nó vào các comboBox
        FilterActionListener filterListener = new FilterActionListener(sorter);
        genderFilterComboBox = new JComboBox<>(new String[]{"Tất cả", "Nam", "Nữ", "Khác"});
        genderFilterComboBox.setPreferredSize(new Dimension(85, 23));
        statusFilterComboBox = new JComboBox<>(new String[]{"Tất cả", "Đang làm việc", "Đã nghỉ việc", "Tạm nghỉ việc"});
        statusFilterComboBox.setPreferredSize(new Dimension(120, 23));
        positionFilterComboBox = new JComboBox<>(new String[]{});
        positionFilterComboBox.setPreferredSize(new Dimension(125, 23));
        departmentFilterComboBox = new JComboBox<>(new String[]{});
        departmentFilterComboBox.setPreferredSize(new Dimension(135, 23));

        positionFilterComboBox.addItem("Tất cả");
        for (String item : NhanVienController.getAllChucVuFromDatabase()) {
            positionFilterComboBox.addItem(item);
        }
        departmentFilterComboBox.addItem("Tất cả");
        for (String item : NhanVienController.getAllPhongBanFromDatabase()) {
            departmentFilterComboBox.addItem(item);
        }

        genderFilterComboBox.addActionListener(filterListener);
        statusFilterComboBox.addActionListener(filterListener);
        positionFilterComboBox.addActionListener(filterListener);
        departmentFilterComboBox.addActionListener(filterListener);

        JPanel filterPanel = new JPanel();
        FlowLayout flowLayout1 = new FlowLayout(FlowLayout.LEFT);
        flowLayout1.setHgap(12);
        filterPanel.setLayout(flowLayout1);

        JPanel gioiTinhPanel = new JPanel();
        FlowLayout flowLayout2 = new FlowLayout(FlowLayout.LEFT);
        flowLayout2.setHgap(10);
        gioiTinhPanel.setLayout(flowLayout2);
        gioiTinhPanel.add(new JLabel("Giới tính:"));
        gioiTinhPanel.add(genderFilterComboBox);

        JPanel tinhTrangPanel = new JPanel();
        FlowLayout flowLayout3 = new FlowLayout(FlowLayout.LEFT);
        flowLayout3.setHgap(10);
        tinhTrangPanel.setLayout(flowLayout3);
        tinhTrangPanel.add(new JLabel("Tình trạng:"));
        tinhTrangPanel.add(statusFilterComboBox);

        JPanel chucVuPanel = new JPanel();
        FlowLayout flowLayout4 = new FlowLayout(FlowLayout.LEFT);
        flowLayout4.setHgap(10);
        chucVuPanel.setLayout(flowLayout4);
        chucVuPanel.add(new JLabel("Chức vụ:"));
        chucVuPanel.add(positionFilterComboBox);

        JPanel phongBanPanel = new JPanel();
        FlowLayout flowLayout5 = new FlowLayout(FlowLayout.LEFT);
        flowLayout5.setHgap(10);
        phongBanPanel.setLayout(flowLayout5);
        phongBanPanel.add(new JLabel("Phòng ban:"));
        phongBanPanel.add(departmentFilterComboBox);

        JPanel searchPanel = new JPanel();
        FlowLayout flowLayout6 = new FlowLayout(FlowLayout.LEFT);
        flowLayout6.setHgap(10);
        searchPanel.setLayout(flowLayout6);
        searchPanel.add(new JLabel("Tìm kiếm theo họ tên: "));
        searchPanel.add(nameSearchField);

        filterPanel.add(gioiTinhPanel);
        filterPanel.add(tinhTrangPanel);
        filterPanel.add(chucVuPanel);
        filterPanel.add(phongBanPanel);
        resetButton = new JButton("Xóa bộ lọc");
        filterPanel.add(resetButton);
        filterPanel.add(searchPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        themBtn = new JButton("Thêm");
        suaBtn = new JButton("Sửa");
        xoaBtn = new JButton("Xóa");
        clearBtn = new JButton("Clear");


        JLabel idNhanVienLabel = new JLabel("ID");
        JLabel hoTenLabel = new JLabel("Họ Tên");
        JLabel ngaySinhLabel = new JLabel("Ngày Sinh");
        JLabel gioiTinhLabel = new JLabel("Giới Tính");
        JLabel tinhTrangLabel = new JLabel("Tình Trạng");
        JLabel ngayVaoLamLabel = new JLabel("Ngày Vào Làm");
        JLabel ngayNghiViecLabel = new JLabel("Ngày Nghỉ Việc");
        JLabel diaChiLabel = new JLabel("Địa Chỉ");
        JLabel soDienThoaiLabel = new JLabel("Số Điện Thoại");
        JLabel emailLabel = new JLabel("Email");
        JLabel tenChucVuLabel = new JLabel("Chức vụ");
        JLabel tenPhongBanLabel = new JLabel("Phòng Ban");


        idNhanVienField = new JTextField(15);
        idNhanVienField.setEditable(false);
        hoTenField = new JTextField(15);
        ngaySinhField = new JTextField(15);
        String[] gioiTinhComboBoxItems = {"Nam", "Nữ", "Khác"};
        gioiTinhComboBox = new JComboBox<>(gioiTinhComboBoxItems);
        gioiTinhComboBox.setPreferredSize(new Dimension(153, 22));
        String[] tinhTrangComboBoxItems = {"Đang làm việc", "Tạm nghỉ việc", "Đã nghỉ việc"};
        tinhTrangComboBox = new JComboBox<>(tinhTrangComboBoxItems);
        tinhTrangComboBox.setPreferredSize(new Dimension(153, 22));
        ngayVaoLamField = new JTextField(15);
        ngayNghiViecField = new JTextField(15);
        diaChiTA = new JTextArea();
        diaChiTA.setColumns(15);
        diaChiTA.setRows(2);
        soDienThoaiField = new JTextField(15);
        emailField = new JTextField(15);
        tenChucVuComboBox = new JComboBox<>();
        tenChucVuComboBox.setPreferredSize(new Dimension(153, 22));
        tenPhongBanComboBox = new JComboBox<>();
        tenPhongBanComboBox.setPreferredSize(new Dimension(153, 22));

        JScrollPane scrollPaneDiaChi = new JScrollPane(diaChiTA);

        JScrollPane scrollPaneTable = new JScrollPane(nhanVienTable);
        scrollPaneTable.setPreferredSize(new Dimension(1247, 350));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(scrollPaneTable);

        panel.add(filterPanel);

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
        panel.add(gioiTinhComboBox);
        panel.add(tinhTrangComboBox);
        panel.add(ngayVaoLamField);
        panel.add(ngayNghiViecField);
        panel.add(scrollPaneDiaChi);
        panel.add(soDienThoaiField);
        panel.add(emailField);
        panel.add(tenChucVuComboBox);
        panel.add(tenPhongBanComboBox);

        panel.add(themBtn);
        panel.add(suaBtn);
//        panel.add(xoaBtn);
        panel.add(clearBtn);

        // filter
        layout.putConstraint(SpringLayout.WEST, filterPanel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, filterPanel, 10, SpringLayout.NORTH, panel);

        // bang nhan vien
        layout.putConstraint(SpringLayout.WEST, scrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, scrollPaneTable, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, scrollPaneTable, -19, SpringLayout.EAST, panel);

        // cot 1
        layout.putConstraint(SpringLayout.WEST, idNhanVienLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idNhanVienLabel, 20, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, idNhanVienField, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idNhanVienField, 20, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, hoTenLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, hoTenLabel, 60, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, hoTenField, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, hoTenField, 60, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, ngaySinhLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngaySinhLabel, 100, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, ngaySinhField, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngaySinhField, 100, SpringLayout.SOUTH, scrollPaneTable);

        //cot 2
        layout.putConstraint(SpringLayout.WEST, gioiTinhLabel, 285, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gioiTinhLabel, 20, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, gioiTinhComboBox, 375, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gioiTinhComboBox, 20, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, tinhTrangLabel, 285, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tinhTrangLabel, 60, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, tinhTrangComboBox, 375, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tinhTrangComboBox, 60, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, ngayVaoLamLabel, 285, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngayVaoLamLabel, 100, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, ngayVaoLamField, 375, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngayVaoLamField, 100, SpringLayout.SOUTH, scrollPaneTable);

        //cot 3
        layout.putConstraint(SpringLayout.WEST, ngayNghiViecLabel, 570, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngayNghiViecLabel, 20, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, ngayNghiViecField, 670, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngayNghiViecField, 20, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, diaChiLabel, 570, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, diaChiLabel, 60, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, scrollPaneDiaChi, 670, SpringLayout.WEST, panel);
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
        layout.putConstraint(SpringLayout.WEST, tenChucVuComboBox, 940, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenChucVuComboBox, 60, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, tenPhongBanLabel, 860, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenPhongBanLabel, 100, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, tenPhongBanComboBox, 940, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenPhongBanComboBox, 100, SpringLayout.SOUTH, scrollPaneTable);

        // nut
        layout.putConstraint(SpringLayout.EAST, themBtn, -32, SpringLayout.WEST, suaBtn);
        layout.putConstraint(SpringLayout.NORTH, themBtn, 25, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.EAST, suaBtn, -19, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, suaBtn, 25, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.EAST, clearBtn, -19, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 85, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 17, SpringLayout.EAST, tenPhongBanComboBox);

        layout.putConstraint(SpringLayout.EAST, xoaBtn, -19, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, xoaBtn, 85, SpringLayout.SOUTH, scrollPaneTable);

        themBtn.addActionListener(this);
        suaBtn.addActionListener(this);
        xoaBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        resetButton.addActionListener(this);

        nhanVienTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Kiểm tra xem sự kiện có phải là sự kiện kết thúc chọn không
                if (!e.getValueIsAdjusting()) {
                    // Lấy hàng được chọn từ bảng
                    int selectedRow = nhanVienTable.getSelectedRow();
                    // Kiểm tra xem có hàng được chọn không
                    if (selectedRow != -1) {
                        // Lấy dữ liệu từ bảng và hiển thị vào các textfield
                        int idNhanVien = (int) nhanVienTable.getValueAt(selectedRow, 0);
                        String hoTen = (String) nhanVienTable.getValueAt(selectedRow, 1);
                        java.sql.Date ngaySinhSql = (java.sql.Date) nhanVienTable.getValueAt(selectedRow, 2);
                        LocalDate ngaySinh = ngaySinhSql.toLocalDate();
                        String gioiTinh = (String) nhanVienTable.getValueAt(selectedRow, 3);
                        String tinhTrang = (String) nhanVienTable.getValueAt(selectedRow, 4);
                        java.sql.Date ngayVaoLamSql = (java.sql.Date) nhanVienTable.getValueAt(selectedRow, 5);
                        LocalDate ngayVaoLam = ngayVaoLamSql.toLocalDate();
                        java.sql.Date ngayNghiViecSql = (java.sql.Date) nhanVienTable.getValueAt(selectedRow, 6);
                        LocalDate ngayNghiViec = (ngayNghiViecSql != null) ? ngayNghiViecSql.toLocalDate() : null;
                        String diaChi = (String) nhanVienTable.getValueAt(selectedRow, 7);
                        String soDienThoai = (String) nhanVienTable.getValueAt(selectedRow, 8);
                        String email = (String) nhanVienTable.getValueAt(selectedRow, 9);
                        String tenChucVu = (String) nhanVienTable.getValueAt(selectedRow, 10);
                        String tenPhongBan = (String) nhanVienTable.getValueAt(selectedRow, 11);

                        idNhanVienField.setText(String.valueOf(idNhanVien));
                        hoTenField.setText(hoTen);
                        ngaySinhField.setText(ngaySinh.toString());
                        gioiTinhComboBox.setSelectedItem(gioiTinh); // Sử dụng setSelectedItem() thay vì setText()
                        tinhTrangComboBox.setSelectedItem(tinhTrang); // Sử dụng setSelectedItem() thay vì setText()
                        ngayVaoLamField.setText(ngayVaoLam.toString());
                        ngayNghiViecField.setText((ngayNghiViec != null) ? ngayNghiViec.toString() : "");
                        diaChiTA.setText(diaChi);
                        soDienThoaiField.setText(soDienThoai);
                        emailField.setText(email);
                        tenChucVuComboBox.setSelectedItem(tenChucVu); // Sử dụng setSelectedItem() thay vì setText()
                        tenPhongBanComboBox.setSelectedItem(tenPhongBan); // Sử dụng setSelectedItem() thay vì setText()
                    }
                }
            }
        });


        add(panel);
        setTitle("Quản Lý Nhân Viên");
        setSize(1300, 593);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearBtn) {
            clearFields();
        }
        if (e.getSource() == resetButton) {
            resetFiltersAndSearch();
        }
    }

    private void resetFiltersAndSearch() {
        genderFilterComboBox.setSelectedItem("Tất cả");
        statusFilterComboBox.setSelectedItem("Tất cả");
        positionFilterComboBox.setSelectedItem("Tất cả");
        departmentFilterComboBox.setSelectedItem("Tất cả");

        nameSearchField.setText("");
    }

    public void clearFields() {
        idNhanVienField.setText("");
        hoTenField.setText("");
        ngaySinhField.setText("");
        gioiTinhComboBox.setSelectedItem(null); // Xóa mục được chọn trong JComboBox
        tinhTrangComboBox.setSelectedItem(null); // Xóa mục được chọn trong JComboBox
        ngayVaoLamField.setText("");
        ngayNghiViecField.setText("");
        diaChiTA.setText("");
        soDienThoaiField.setText("");
        emailField.setText("");
        tenChucVuComboBox.setSelectedItem(null); // Xóa mục được chọn trong JComboBox
        tenPhongBanComboBox.setSelectedItem(null); // Xóa mục được chọn trong JComboBox
    }


    public void addThemBtnListener(ActionListener listener) {
        themBtn.addActionListener(listener);
    }

    public void addSuaBtnListener(ActionListener listener) {
        suaBtn.addActionListener(listener);
    }

    public void addXoaBtnListener(ActionListener listener) {
        xoaBtn.addActionListener(listener);
    }

    public JTextField getIdNhanVienField() {
        return idNhanVienField;
    }

    public JTextField getHoTenField() {
        return hoTenField;
    }

    public JTextField getNgaySinhField() {
        return ngaySinhField;
    }

    public JTextField getNgayVaoLamField() {
        return ngayVaoLamField;
    }

    public JTextField getNgayNghiViecField() {
        return ngayNghiViecField;
    }

    public JTextArea getDiaChiTA() {
        return diaChiTA;
    }

    public JTextField getSoDienThoaiField() {
        return soDienThoaiField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JComboBox<String> getGioiTinhComboBox() {
        return gioiTinhComboBox;
    }

    public JComboBox<String> getTinhTrangComboBox() {
        return tinhTrangComboBox;
    }

    public JComboBox<String> getTenChucVuComboBox() {
        return tenChucVuComboBox;
    }

    public JComboBox<String> getTenPhongBanComboBox() {
        return tenPhongBanComboBox;
    }

    public JTable getNhanVienTable() {
        return nhanVienTable;
    }

    private void loadDataFromDatabase() {
        ArrayList<String> chucVuList = NhanVienController.getAllChucVuFromDatabase();
        ArrayList<String> phongBanList = NhanVienController.getAllPhongBanFromDatabase();
        fillComboBox(tenChucVuComboBox, chucVuList);
        fillComboBox(tenPhongBanComboBox, phongBanList);
    }

    private void fillComboBox(JComboBox<String> comboBox, ArrayList<String> dataList) {
        for (String item : dataList) {
            comboBox.addItem(item);
        }
    }

    public NhanVien getNhanVien() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setIdNhanVien(Integer.parseInt(getIdNhanVienField().getText()));
        nhanVien.setHoTen(getHoTenField().getText());
        nhanVien.setNgaySinh(LocalDate.parse(getNgaySinhField().getText()));
        nhanVien.setGioiTinh((String) getGioiTinhComboBox().getSelectedItem());
        nhanVien.setTinhTrang((String) getTinhTrangComboBox().getSelectedItem());
        nhanVien.setNgayVaoLam(LocalDate.parse(getNgayVaoLamField().getText()));
        String ngayNghiViecText = getNgayNghiViecField().getText();
        if (!ngayNghiViecText.isEmpty()) {
            nhanVien.setNgayNghiViec(LocalDate.parse(ngayNghiViecText));
        }
        nhanVien.setDiaChi(getDiaChiTA().getText());
        nhanVien.setSoDienThoai(getSoDienThoaiField().getText());
        nhanVien.setEmail(getEmailField().getText());
        nhanVien.setIdChucVu(getIdChucVu());
        nhanVien.setIdPhongBan(getIdPhongBan());
        return nhanVien;
    }

    private int getIdChucVu() {
        // Lấy ID chức vụ từ tên chức vụ được chọn trong ComboBox
        String tenChucVu = (String) getTenChucVuComboBox().getSelectedItem();
        // Sử dụng NhanVienController hoặc một phương thức tương tự để lấy ID từ tên chức vụ
        return NhanVienController.getIdChucVuFromDatabase(tenChucVu);
    }

    private int getIdPhongBan() {
        // Lấy ID phòng ban từ tên phòng ban được chọn trong ComboBox
        String tenPhongBan = (String) getTenPhongBanComboBox().getSelectedItem();
        // Sử dụng NhanVienController hoặc một phương thức tương tự để lấy ID từ tên phòng ban
        return NhanVienController.getIdPhongBanFromDatabase(tenPhongBan);
    }
    public class FilterActionListener implements ActionListener {
        private final HashMap<Integer, RowFilter<Object, Object>> activeFilters; // Lưu trữ các bộ lọc hoạt động theo cột
        private final TableRowSorter<?> sorter;

        public FilterActionListener(TableRowSorter<?> sorter) {
            this.sorter = sorter;
            this.activeFilters = new HashMap<>();
        }

        public void addOrRemoveFilter(int columnIndex, String selectedValue) {
            if (Objects.equals(selectedValue, "Tất cả")) {
                // Nếu lựa chọn là "Tất cả", thì xóa bộ lọc cho cột đó
                activeFilters.remove(columnIndex);
            } else {
                RowFilter<Object, Object> filter = RowFilter.regexFilter(selectedValue, columnIndex);
                // Cập nhật bộ lọc mới vào HashMap
                activeFilters.put(columnIndex, filter);
            }
            updateFilters();
        }

        private void updateFilters() {
            // Tạo chuỗi bộ lọc từ các giá trị trong HashMap
            if (activeFilters.isEmpty()) {
                sorter.setRowFilter(null); // nếu không có bộ lọc, hãy thiết lập lại bộ lọc trên sorter
            } else {
                ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>(activeFilters.values());
                RowFilter<Object, Object> compoundRowFilter = RowFilter.andFilter(filters);
                sorter.setRowFilter(compoundRowFilter);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            String selectedValue = (String) comboBox.getSelectedItem();
            int columnIndex;
            if (comboBox == genderFilterComboBox) {
                columnIndex = 3; // chỉ mục cột cho giới tính
            } else if (comboBox == statusFilterComboBox) {
                columnIndex = 4; // chỉ mục cột cho tình trạng
            } else if (comboBox == positionFilterComboBox) {
                columnIndex = 10; // chỉ mục cột cho chức vụ
            } else {
                columnIndex = 11; // chỉ mục cột cho phòng ban
            }
            addOrRemoveFilter(columnIndex, selectedValue);
        }
    }

}
