package QuanLyLuong.View;

import QuanLyLuong.Controller.LuongController;
import QuanLyLuong.Model.Luong;
import QuanLyNhanVien.Controller.NhanVienController;
import XuatExcel.ExcelExporter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

// sau khi ấn nút thêm hiện ra nhân viên view chỉ có bảng để fetch dữ liệu ra edit panel

public class LuongView extends JFrame implements ActionListener {
    // Filter
    private JComboBox<String> statusFilterComboBox, positionFilterComboBox, departmentFilterComboBox, yearFilterComboBox, monthFilterComboBox;
    JButton resetBtn, themBtn, suaBtn, xoaBtn, clearBtn, xuatBtn;
    JTextField nameSearchField = new JTextField(11);
    // Table
    DefaultTableModel defaultTableModel = LuongController.getAllReCords();
    JTable luongTable = new JTable(defaultTableModel);
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(defaultTableModel);
    // edit field
    // panel 1
    static JTextField idLuongField = new JTextField();
    static JComboBox<Integer> idNhanVienComboBox = new JComboBox<>();
    static JComboBox<Integer> yearComboBox;
    static JComboBox<Integer> monthComboBox;
    static JTextField soGioLamField = new JTextField();
    //panel 4
    static JTextField thuongField = new JTextField();
    static JTextField thucLanhField = new JTextField();

    public LuongView() {
        initComponents();
    }

    private void initComponents() {
        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        // Filter
        JPanel filterPanel = initFilter();
        add(filterPanel);
        layout.putConstraint(SpringLayout.WEST, filterPanel, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, filterPanel, 10, SpringLayout.NORTH, this);

        // Bảng
        JPanel tablePanel = initTable();
        tablePanel.setSize(1600, 320);
        add(tablePanel);
        layout.putConstraint(SpringLayout.NORTH, tablePanel, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, tablePanel, 20, SpringLayout.WEST, this);

        // edit panel
        JPanel editPanel = initEditPanel();
        add(editPanel);
        layout.putConstraint(SpringLayout.NORTH, editPanel, 0, SpringLayout.SOUTH, tablePanel);
        layout.putConstraint(SpringLayout.WEST, editPanel, 0, SpringLayout.WEST, this);

        setTitle("Quản Lý Lương");
        setSize(1460, 540);
        setLocationRelativeTo(null); //đặt jframe ở giữa màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Filter
    private JPanel initFilter() {
        // Tạo FilterActionListener và gắn nó vào các comboBox
        LuongView.FilterActionListener filterListener = new LuongView.FilterActionListener(sorter);
        statusFilterComboBox = new JComboBox<>(new String[]{"Tất cả", "Đang làm việc", "Đã nghỉ việc", "Tạm nghỉ việc"});
        statusFilterComboBox.setPreferredSize(new Dimension(120, 23));
        positionFilterComboBox = new JComboBox<>(new String[]{});
        positionFilterComboBox.setPreferredSize(new Dimension(125, 23));
        departmentFilterComboBox = new JComboBox<>(new String[]{});
        departmentFilterComboBox.setPreferredSize(new Dimension(135, 23));
        // Tạo một mảng String để chứa các năm từ năm hiện tại đến 2015
        int currentYear = LocalDate.now().getYear();
        int startYear = 2015;
        int numYears = currentYear - startYear + 2; // Tăng numYears lên 1 để chứa phần tử "Tất cả"
        String[] years = new String[numYears];
        years[0] = "Tất cả";
        for (int i = 1; i < numYears; i++) {
            years[i] = String.valueOf(currentYear - (i - 1));
        }
        yearFilterComboBox = new JComboBox<>(years);
        yearFilterComboBox.setPreferredSize(new Dimension(70, 23));
        String[] months = {"Tất cả", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        monthFilterComboBox = new JComboBox<>(months);
        monthFilterComboBox.setPreferredSize(new Dimension(70, 23));

        positionFilterComboBox.addItem("Tất cả");
        for (String item : NhanVienController.getAllChucVuFromDatabase()) {
            positionFilterComboBox.addItem(item);
        }
        departmentFilterComboBox.addItem("Tất cả");
        for (String item : NhanVienController.getAllPhongBanFromDatabase()) {
            departmentFilterComboBox.addItem(item);
        }

        statusFilterComboBox.addActionListener(filterListener);
        positionFilterComboBox.addActionListener(filterListener);
        departmentFilterComboBox.addActionListener(filterListener);
        yearFilterComboBox.addActionListener(filterListener);
        monthFilterComboBox.addActionListener(filterListener);

        // Tạo FilterPanel
        JPanel filterPanel = new JPanel();
        FlowLayout flowLayout1 = new FlowLayout(FlowLayout.LEFT);
        flowLayout1.setHgap(12);
        filterPanel.setLayout(flowLayout1);

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

        JPanel yearPanel = new JPanel();
        FlowLayout flowLayout7 = new FlowLayout(FlowLayout.LEFT);
        flowLayout7.setHgap(10);
        yearPanel.setLayout(flowLayout7);
        yearPanel.add(new JLabel("Năm:"));
        yearPanel.add(yearFilterComboBox);

        JPanel monthPanel = new JPanel();
        FlowLayout flowLayout8 = new FlowLayout(FlowLayout.LEFT);
        flowLayout8.setHgap(10);
        monthPanel.setLayout(flowLayout8);
        monthPanel.add(new JLabel("Tháng:"));
        monthPanel.add(monthFilterComboBox);

        JPanel searchPanel = new JPanel();
        FlowLayout flowLayout6 = new FlowLayout(FlowLayout.LEFT);
        flowLayout6.setHgap(10);
        searchPanel.setLayout(flowLayout6);
        searchPanel.add(new JLabel("Tìm kiếm theo họ tên: "));
        searchPanel.add(nameSearchField);

        filterPanel.add(tinhTrangPanel);
        filterPanel.add(chucVuPanel);
        filterPanel.add(phongBanPanel);
        filterPanel.add(yearPanel);
        filterPanel.add(monthPanel);
        resetBtn = new JButton("Xóa bộ lọc");
        resetBtn.addActionListener(this);
        filterPanel.add(resetBtn);
        filterPanel.add(searchPanel);

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
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text), 2)); // 1 là chỉ mục cột của họ tên
                }
            }
        });
        return filterPanel;
    }

    public Luong getLuong() {
        Luong luong = new Luong();
        String idLuongText = idLuongField.getText();
        if (idLuongText != null && !idLuongText.isEmpty()) {
            try {
                int idLuong = Integer.parseInt(idLuongText);
                luong.setIdLuong(idLuong);
            } catch (NumberFormatException ex) {
                System.out.println(":)");
            }
        } else {
            luong.setIdLuong(0);
        }
        if (idNhanVienComboBox.getSelectedItem() == null) {
            luong.setIdNhanVien(0);
        } else {
            luong.setIdNhanVien((Integer) Objects.requireNonNull(idNhanVienComboBox.getSelectedItem()));
        }
        luong.setNam((Integer) Objects.requireNonNull(yearComboBox.getSelectedItem()));
        luong.setThang((Integer) Objects.requireNonNull(monthComboBox.getSelectedItem()));
        luong.setSoGioLam(Integer.parseInt(soGioLamField.getText()));
        luong.setThuong(Integer.parseInt(thuongField.getText()));
        luong.setThucLanh(Integer.parseInt(thucLanhField.getText()));
        return luong;
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
                RowFilter<Object, Object> filter = RowFilter.regexFilter("^" + Pattern.quote(selectedValue) + "$", columnIndex);
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
            if (comboBox == statusFilterComboBox) {
                columnIndex = 3; // chỉ mục cột cho tình trạng
            } else if (comboBox == positionFilterComboBox) {
                columnIndex = 4; // chỉ mục cột cho chức vụ
            } else if (comboBox == departmentFilterComboBox) {
                columnIndex = 5; // chỉ mục cột cho phòng ban
            } else if (comboBox == yearFilterComboBox) {
                columnIndex = 7; // chỉ mục cột cho năm
            } else {
                columnIndex = 8; // chỉ mục cho tháng
            }
            addOrRemoveFilter(columnIndex, selectedValue);
        }
    }

    private void resetFiltersAndSearch() {
        statusFilterComboBox.setSelectedItem("Tất cả");
        positionFilterComboBox.setSelectedItem("Tất cả");
        departmentFilterComboBox.setSelectedItem("Tất cả");
        yearFilterComboBox.setSelectedItem("Tất cả");
        monthFilterComboBox.setSelectedItem("Tất cả");

        nameSearchField.setText("");
    }

    // Bảng
    private JPanel initTable() {
        JScrollPane scrollPaneTable = new JScrollPane(luongTable);
        scrollPaneTable.setPreferredSize(new Dimension(1400, 320));
        JPanel panel = new JPanel();
        panel.add(scrollPaneTable);
        // sorter
        int[] unsortableColumns = {3, 4, 5};
        for (int columnIndex : unsortableColumns) {
            sorter.setSortable(columnIndex, false);
        }
        luongTable.setRowSorter(sorter);

        // list selection listener
        luongTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Kiểm tra xem sự kiện có phải là sự kiện kết thúc chọn không
                if (!e.getValueIsAdjusting()) {
                    // Lấy hàng được chọn từ bảng
                    int selectedRow = luongTable.getSelectedRow();
                    // Kiểm tra xem có hàng được chọn không
                    if (selectedRow != -1) {
                        // Lấy dữ liệu từ bảng và hiển thị vào các textfield
                        int idLuong = (int) luongTable.getValueAt(selectedRow, 0);
                        int idNhanVien = (int) luongTable.getValueAt(selectedRow, 1);
                        int nam = (int) luongTable.getValueAt(selectedRow, 7);
                        int thang = (int) luongTable.getValueAt(selectedRow, 8);
                        int soGioLam = (int) luongTable.getValueAt(selectedRow, 9);
                        int thuong = (int) luongTable.getValueAt(selectedRow, 10);
                        int thucLanh = (int) luongTable.getValueAt(selectedRow, 11);

                        idLuongField.setText(String.valueOf(idLuong));
                        idNhanVienComboBox.setSelectedItem(idNhanVien);
                        yearComboBox.setSelectedItem(nam);
                        monthComboBox.setSelectedItem(thang);
                        soGioLamField.setText(String.valueOf(soGioLam));
                        thuongField.setText(String.valueOf(thuong));
                        thucLanhField.setText(String.valueOf(thucLanh));
                    }
                }
            }
        });
        return panel;
    }

    // Các Label, TextField và Button (edit fields)
    private JPanel initEditPanel() {
        JPanel panel = new JPanel();
        SpringLayout springLayout = new SpringLayout();
        panel.setLayout(springLayout);
        panel.setPreferredSize(new Dimension(1600, 110));

        JLabel idLuongLabel = new JLabel("ID Lương:");
        panel.add(idLuongLabel);
        springLayout.putConstraint(SpringLayout.NORTH, idLuongLabel, 12, SpringLayout.NORTH, panel);
        springLayout.putConstraint(SpringLayout.WEST, idLuongLabel, 135, SpringLayout.WEST, panel);
        idLuongField = new JTextField(6);
        idLuongField.setEditable(false);
        panel.add(idLuongField);
        springLayout.putConstraint(SpringLayout.NORTH, idLuongField, 0, SpringLayout.NORTH, idLuongLabel);
        springLayout.putConstraint(SpringLayout.WEST, idLuongField, 12, SpringLayout.EAST, idLuongLabel);

        JLabel idNhanVienLabel = new JLabel("ID Nhân Viên:");
        panel.add(idNhanVienLabel);
        springLayout.putConstraint(SpringLayout.NORTH, idNhanVienLabel, 0, SpringLayout.NORTH, idLuongField);
        springLayout.putConstraint(SpringLayout.WEST, idNhanVienLabel, 30, SpringLayout.EAST, idLuongField);
        idNhanVienComboBox = new JComboBox<>(new Integer[]{});
        idNhanVienComboBox.setPreferredSize(new Dimension(50, 23));
        for (Integer item : NhanVienController.getAllIdNhanVienFromDatabase()) {
            idNhanVienComboBox.addItem(item);
        }
        panel.add(idNhanVienComboBox);
        springLayout.putConstraint(SpringLayout.NORTH, idNhanVienComboBox, 0, SpringLayout.NORTH, idNhanVienLabel);
        springLayout.putConstraint(SpringLayout.WEST, idNhanVienComboBox, 15, SpringLayout.EAST, idNhanVienLabel);

        JLabel namLabel = new JLabel("Năm:");
        panel.add(namLabel);
        springLayout.putConstraint(SpringLayout.NORTH, namLabel, 0, SpringLayout.NORTH, idNhanVienComboBox);
        springLayout.putConstraint(SpringLayout.WEST, namLabel, 30, SpringLayout.EAST, idNhanVienComboBox);
        // Tạo một mảng String để chứa các năm từ năm hiện tại đến 2015
        int currentYear = LocalDate.now().getYear();
        int startYear = 2015;
        int numYears = currentYear - startYear + 1;
        Integer[] years = new Integer[numYears];
        for (int i = 0; i < numYears; i++) {
            years[i] = currentYear - i;
        }
        yearComboBox = new JComboBox<>(years);
        yearComboBox.setPreferredSize(new Dimension(70, 23));
        panel.add(yearComboBox);
        springLayout.putConstraint(SpringLayout.NORTH, yearComboBox, 0, SpringLayout.NORTH, namLabel);
        springLayout.putConstraint(SpringLayout.WEST, yearComboBox, 12, SpringLayout.EAST, namLabel);

        JLabel thangLabel = new JLabel("Tháng:");
        panel.add(thangLabel);
        springLayout.putConstraint(SpringLayout.NORTH, thangLabel, 0, SpringLayout.NORTH, yearComboBox);
        springLayout.putConstraint(SpringLayout.WEST, thangLabel, 30, SpringLayout.EAST, yearComboBox);
        monthComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        monthComboBox.setPreferredSize(new Dimension(50, 23));
        panel.add(monthComboBox);
        springLayout.putConstraint(SpringLayout.NORTH, monthComboBox, 0, SpringLayout.NORTH, thangLabel);
        springLayout.putConstraint(SpringLayout.WEST, monthComboBox, 12, SpringLayout.EAST, thangLabel);

        JLabel soGioLamLabel = new JLabel("Số giờ làm:");
        panel.add(soGioLamLabel);
        springLayout.putConstraint(SpringLayout.NORTH, soGioLamLabel, 0, SpringLayout.NORTH, monthComboBox);
        springLayout.putConstraint(SpringLayout.WEST, soGioLamLabel, 30, SpringLayout.EAST, monthComboBox);
        soGioLamField = new JTextField(5);
        panel.add(soGioLamField);
        springLayout.putConstraint(SpringLayout.NORTH, soGioLamField, 0, SpringLayout.NORTH, soGioLamLabel);
        springLayout.putConstraint(SpringLayout.WEST, soGioLamField, 12, SpringLayout.EAST, soGioLamLabel);

        JLabel thuongLabel = new JLabel("Thưởng:");
        panel.add(thuongLabel);
        springLayout.putConstraint(SpringLayout.NORTH, thuongLabel, 0, SpringLayout.NORTH, soGioLamField);
        springLayout.putConstraint(SpringLayout.WEST, thuongLabel, 30, SpringLayout.EAST, soGioLamField);
        thuongField = new JTextField(10);
        thuongField.setText("0");
        panel.add(thuongField);
        springLayout.putConstraint(SpringLayout.NORTH, thuongField, 0, SpringLayout.NORTH, thuongLabel);
        springLayout.putConstraint(SpringLayout.WEST, thuongField, 12, SpringLayout.EAST, thuongLabel);

        JLabel thucLanhLabel = new JLabel("Thực Lãnh:");
        panel.add(thucLanhLabel);
        springLayout.putConstraint(SpringLayout.NORTH, thucLanhLabel, 0, SpringLayout.NORTH, thuongField);
        springLayout.putConstraint(SpringLayout.WEST, thucLanhLabel, 30, SpringLayout.EAST, thuongField);
        thucLanhField = new JTextField(10);
        thucLanhField.setEditable(false);
        panel.add(thucLanhField);
        springLayout.putConstraint(SpringLayout.NORTH, thucLanhField, 0, SpringLayout.NORTH, thucLanhLabel);
        springLayout.putConstraint(SpringLayout.WEST, thucLanhField, 12, SpringLayout.EAST, thucLanhLabel);

        //panel 5: buttons
        themBtn = new JButton("Thêm");
        themBtn.setPreferredSize(new Dimension(90, 35));
        panel.add(themBtn);
        springLayout.putConstraint(SpringLayout.NORTH, themBtn, 35, SpringLayout.SOUTH, idLuongLabel);
        springLayout.putConstraint(SpringLayout.WEST, themBtn, 200, SpringLayout.WEST, idLuongLabel);
        suaBtn = new JButton("Sửa");
        suaBtn.setPreferredSize(new Dimension(90, 35));
        panel.add(suaBtn);
        springLayout.putConstraint(SpringLayout.NORTH, suaBtn, 35, SpringLayout.SOUTH, idLuongLabel);
        springLayout.putConstraint(SpringLayout.WEST, suaBtn, 50, SpringLayout.EAST, themBtn);
        clearBtn = new JButton("Clear");
        clearBtn.setPreferredSize(new Dimension(90, 35));
        panel.add(clearBtn);
        springLayout.putConstraint(SpringLayout.NORTH, clearBtn, 35, SpringLayout.SOUTH, idLuongLabel);
        springLayout.putConstraint(SpringLayout.WEST, clearBtn, 50, SpringLayout.EAST, suaBtn);
        xoaBtn = new JButton("Xóa");
        xoaBtn.setPreferredSize(new Dimension(90, 35));
        panel.add(xoaBtn);
        springLayout.putConstraint(SpringLayout.NORTH, xoaBtn, 35, SpringLayout.SOUTH, idLuongLabel);
        springLayout.putConstraint(SpringLayout.WEST, xoaBtn, 50, SpringLayout.EAST, clearBtn);
        xuatBtn = new JButton("Xuất Excel");
        xuatBtn.setPreferredSize(new Dimension(130, 35));
        panel.add(xuatBtn);
        springLayout.putConstraint(SpringLayout.NORTH, xuatBtn, 35, SpringLayout.SOUTH, idLuongLabel);
        springLayout.putConstraint(SpringLayout.WEST, xuatBtn, 50, SpringLayout.EAST, xoaBtn);

        soGioLamField.getDocument().addDocumentListener(new TextFieldChangeListener());
        thuongField.getDocument().addDocumentListener(new TextFieldChangeListener());
        themBtn.addActionListener(this);
        suaBtn.addActionListener(this);
        xoaBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        xuatBtn.addActionListener(this);

        return panel;
    }

    private static class TextFieldChangeListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            calculate();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            calculate();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            calculate();
        }

        private void calculate() {
            String value1 = soGioLamField.getText();
            String value2 = thuongField.getText();

            try {
                int result = Integer.parseInt(value1) * LuongController.getTienCongMotGio(Integer.parseInt(Objects.requireNonNull(idNhanVienComboBox.getSelectedItem()).toString())) + Integer.parseInt(value2);
                thucLanhField.setText(Integer.toString(result));
            } catch (NumberFormatException ex) {
                // Xử lý ngoại lệ khi người dùng nhập không phải là số
                thucLanhField.setText("");
            }
        }
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

    // ghi đè actionPerformed của lớp
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetBtn) {
            resetFiltersAndSearch();
        }
        if (e.getSource() == xuatBtn) {
            exportExcel();
        }
        if (e.getSource() == clearBtn) {
            clearFields();
        }
    }

    public void clearFields() {
        idLuongField.setText("");
        idNhanVienComboBox.setSelectedItem(null);
        yearComboBox.setSelectedItem(null);
        monthComboBox.setSelectedItem(null);
        soGioLamField.setText("");
        thuongField.setText("");
        thucLanhField.setText("");
    }

    public void exportExcel() {
        // Lấy số lượng cột từ bảng gốc
        int columnCount = defaultTableModel.getColumnCount();
        // Tạo bảng mới để chứa dữ liệu đã lọc
        DefaultTableModel filteredModel = new DefaultTableModel();
        // Thêm các cột vào bảng mới
        for (int i = 0; i < columnCount; i++) {
            filteredModel.addColumn(defaultTableModel.getColumnName(i));
        }
        // Lấy số lượng dòng đã lọc
        int rowCount = luongTable.getRowCount();
        // Lặp qua các dòng đã lọc
        for (int i = 0; i < rowCount; i++) {
            // Nếu dòng đó được hiển thị (không bị ẩn do bộ lọc)
            if (luongTable.convertRowIndexToModel(i) != -1) {
                // Lấy dòng dữ liệu từ bảng gốc
                Object[] rowData = new Object[columnCount];
                for (int j = 0; j < columnCount; j++) {
                    rowData[j] = luongTable.getValueAt(i, j);
                }
                // Thêm dòng dữ liệu vào bảng mới
                filteredModel.addRow(rowData);
            }
        }
        // Xuất Excel với dữ liệu đã lọc
        ExcelExporter.exportToExcel(filteredModel, "C:\\UTT\\java\\xuatExcel\\luong.xlsx");
    }

    public JTable getLuongTable() {
        return luongTable;
    }

    public static void setIdNhanVienComboBox(int idNhanVien) {
        idNhanVienComboBox.setSelectedItem(idNhanVien);
    }

    // set year combobox to current year
    static int currentYear = LocalDate.now().getYear();
    static int currentMonth = LocalDate.now().getMonthValue();
    public static void setYearComboBoxToCurrentYear() {
        yearComboBox.setSelectedItem(currentYear);
    }
    public static void setMonthComboBoxToCurrentMonth() {
        monthComboBox.setSelectedItem(currentMonth);
    }
}

