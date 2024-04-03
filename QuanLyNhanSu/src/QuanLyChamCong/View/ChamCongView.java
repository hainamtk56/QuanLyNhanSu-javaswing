package QuanLyChamCong.View;

import QuanLyChamCong.Controller.ChamCongController;
import QuanLyChamCong.Model.ChamCong;
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
import java.sql.Date;
import java.sql.Time;

public class ChamCongView extends JFrame implements ActionListener {
    // Filter
    private JComboBox<String> statusFilterComboBox, positionFilterComboBox, departmentFilterComboBox;
    JButton resetBtn, themBtn, suaBtn, xoaBtn, clearBtn, xuatBtn;
    JTextField nameSearchField = new JTextField(11);
    // Table
    DefaultTableModel defaultTableModel = ChamCongController.getAllRecords();
    JTable chamCongTable = new JTable(defaultTableModel);
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(defaultTableModel);
    // edit field
    // panel 1
    JTextField idChamCongField = new JTextField();
    JComboBox<Integer> idNhanVienComboBox ;
    static JTextField ngayLamViecField = new JTextField();
    JTextField gioBatDauField = new JTextField();
    JTextField gioKetThucField = new JTextField();

    public ChamCongView() {
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
        tablePanel.setSize(1150, 320);
        add(tablePanel);
        layout.putConstraint(SpringLayout.NORTH, tablePanel, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, tablePanel, 20, SpringLayout.WEST, this);

        // edit panel
        JPanel editPanel = initEditPanel();
        add(editPanel);
        layout.putConstraint(SpringLayout.NORTH, editPanel, 10, SpringLayout.SOUTH, tablePanel);
        layout.putConstraint(SpringLayout.WEST, editPanel, 23, SpringLayout.WEST, this);

        // buttons panel
        JPanel buttonsPanel = initButtonsPanel();
        add(buttonsPanel);
        layout.putConstraint(SpringLayout.NORTH, buttonsPanel, 10, SpringLayout.SOUTH, editPanel);
        layout.putConstraint(SpringLayout.WEST, buttonsPanel, 150, SpringLayout.WEST, this);


        setTitle("Quản Lý Chấm Công");
        setSize(1160, 570);
        setLocationRelativeTo(null); //đặt jframe ở giữa màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Filter
    private JPanel initFilter() {
        // Tạo FilterActionListener và gắn nó vào các comboBox
        ChamCongView.FilterActionListener filterListener = new FilterActionListener(sorter);
        statusFilterComboBox = new JComboBox<>(new String[]{"Tất cả", "Đang làm việc", "Đã nghỉ việc", "Tạm nghỉ việc"});
        statusFilterComboBox.setPreferredSize(new Dimension(120, 23));
        positionFilterComboBox = new JComboBox<>(new String[]{});
        positionFilterComboBox.setPreferredSize(new Dimension(125, 23));
        departmentFilterComboBox = new JComboBox<>(new String[]{});
        departmentFilterComboBox.setPreferredSize(new Dimension(135, 23));

        resetBtn = new JButton("Xóa bộ lọc");
        resetBtn.addActionListener(this);

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

        JPanel searchPanel = new JPanel();
        FlowLayout flowLayout6 = new FlowLayout(FlowLayout.LEFT);
        flowLayout6.setHgap(10);
        searchPanel.setLayout(flowLayout6);
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(nameSearchField);

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

            private void updateNameFilter() {
                String text = nameSearchField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text), 2)); // 1 là chỉ mục cột của họ tên
                }
            }
        });

        filterPanel.add(tinhTrangPanel);
        filterPanel.add(chucVuPanel);
        filterPanel.add(phongBanPanel);
        filterPanel.add(resetBtn);
        filterPanel.add(searchPanel);

        return filterPanel;
    }
    public class FilterActionListener implements ActionListener {
        private final HashMap<Integer, RowFilter<Object, Object>> activeFilters;
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
                sorter.setRowFilter(null); // nếu không có bộ lọc, thiết lập lại bộ lọc trên sorter
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
            } else {
                columnIndex = 5; // chỉ mục cột cho phòng ban
            }
            addOrRemoveFilter(columnIndex, selectedValue);
            updateFilters();
        }
    }

    private JPanel initTable() {
        JScrollPane scrollPaneTable = new JScrollPane(chamCongTable);
        scrollPaneTable.setPreferredSize(new Dimension(1100, 320));
        JPanel panel = new JPanel();
        panel.add(scrollPaneTable);
        // sorter
        int[] unsortableColumns = {3, 4, 5};
        for (int columnIndex : unsortableColumns) {
            sorter.setSortable(columnIndex, false);
        }
        chamCongTable.setRowSorter(sorter);

        // list selection listener
        chamCongTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Kiểm tra xem sự kiện có phải là sự kiện kết thúc chọn không
                if (!e.getValueIsAdjusting()) {
                    // Lấy hàng được chọn từ bảng
                    int selectedRow = chamCongTable.getSelectedRow();
                    // Kiểm tra xem có hàng được chọn không
                    if (selectedRow != -1) {
                        // Lấy dữ liệu từ bảng và hiển thị vào các textfield
                        int idChamCong = (int) chamCongTable.getValueAt(selectedRow, 0);
                        int idNhanVien = (int) chamCongTable.getValueAt(selectedRow, 1);
                        java.sql.Date ngayLamViecSql = (java.sql.Date) chamCongTable.getValueAt(selectedRow, 6);
                        LocalDate ngayLamViec = ngayLamViecSql.toLocalDate();

                        String gioBatDau = chamCongTable.getValueAt(selectedRow, 7).toString(); // Giữ nguyên kiểu dữ liệu là String
                        String gioKetThuc = chamCongTable.getValueAt(selectedRow, 8).toString(); // Giữ nguyên kiểu dữ liệu là String

                        idChamCongField.setText(String.valueOf(idChamCong));
                        idNhanVienComboBox.setSelectedItem(idNhanVien);
                        ngayLamViecField.setText(ngayLamViec.toString());
                        gioBatDauField.setText(gioBatDau);
                        gioKetThucField.setText(gioKetThuc);

                    }
                }
            }
        });
        return panel;
    }
    // Edit
    private JPanel initEditPanel() {
        // Tạo EditPanel
        JPanel editPanel = new JPanel();
        editPanel.setBorder(BorderFactory.createTitledBorder("Thông tin chấm công"));
        editPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setHgap(15);

        JPanel panel1 = new JPanel();
        panel1.setLayout(flowLayout);
        panel1.add(new JLabel("ID Chấm Công:"));
        idChamCongField.setPreferredSize(new Dimension(50, 23));
        idChamCongField.setEditable(false);
        panel1.add(idChamCongField);

        JPanel panel2 = new JPanel();
        panel2.setLayout(flowLayout);
        panel2.add(new JLabel("ID Nhân Viên:"));
        idNhanVienComboBox = new JComboBox<>(new Integer[]{});
        idNhanVienComboBox.setPreferredSize(new Dimension(60, 22));
        for (Integer item : NhanVienController.getAllIdNhanVienFromDatabase()) {
            idNhanVienComboBox.addItem(item);
        }
        panel2.add(idNhanVienComboBox);

        JPanel panel3 = new JPanel();
        panel3.setLayout(flowLayout);
        panel3.add(new JLabel("Ngày Làm Việc:"));
        ngayLamViecField.setPreferredSize(new Dimension(115, 23));
        panel3.add(ngayLamViecField);

        JPanel panel4 = new JPanel();
        panel4.setLayout(flowLayout);
        panel4.add(new JLabel("Giờ Bắt Đầu:"));
        gioBatDauField.setPreferredSize(new Dimension(115, 23));
        panel4.add(gioBatDauField);

        JPanel panel5 = new JPanel();
        panel5.setLayout(flowLayout);
        panel5.add(new JLabel("Giờ Kết Thúc:"));
        gioKetThucField.setPreferredSize(new Dimension(115, 23));
        panel5.add(gioKetThucField);


        editPanel.add(panel1);
        editPanel.add(panel2);
        editPanel.add(panel3);
        editPanel.add(panel4);
        editPanel.add(panel5);

        return editPanel;
    }
    private JPanel initButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setHgap(38);
        buttonsPanel.setLayout(flowLayout);

        themBtn = new JButton("Thêm");
        themBtn.setPreferredSize(new Dimension(100, 30));
        suaBtn = new JButton("Sửa");
        suaBtn.setPreferredSize(new Dimension(100, 30));
        xoaBtn = new JButton("Xóa");
        xoaBtn.setPreferredSize(new Dimension(100, 30));
        clearBtn = new JButton("Clear");
        clearBtn.setPreferredSize(new Dimension(100, 30));
        xuatBtn = new JButton("Xuất Excel");
        xuatBtn.setPreferredSize(new Dimension(150, 30));

        themBtn.addActionListener(this);
        suaBtn.addActionListener(this);
        xoaBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        xuatBtn.addActionListener(this);

        buttonsPanel.add(themBtn);
        buttonsPanel.add(suaBtn);
        buttonsPanel.add(xoaBtn);
        buttonsPanel.add(clearBtn);
        buttonsPanel.add(xuatBtn);

        return buttonsPanel;
    }

    public ChamCong getChamCong() {
        int idChamCong = idChamCongField.getText().isEmpty() ? 0 : Integer.parseInt(idChamCongField.getText());
        int idNhanVien = idNhanVienComboBox.getSelectedItem() != null ? (int) idNhanVienComboBox.getSelectedItem() : 0;
        Date ngayLamViec = !ngayLamViecField.getText().isEmpty() ? Date.valueOf(ngayLamViecField.getText()) : null;
        Time gioBatDau = !gioBatDauField.getText().isEmpty() ? Time.valueOf(gioBatDauField.getText()) : null;
        Time gioKetThuc = !gioKetThucField.getText().isEmpty() ? Time.valueOf(gioKetThucField.getText()) : null;
        return new ChamCong(idChamCong, idNhanVien, ngayLamViec, gioBatDau, gioKetThuc);
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

    // actionPerformed của lớp
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearBtn) {
            clearFields();
        }
        if (e.getSource() == resetBtn) {
            resetFiltersAndSearch();
        }
        if (e.getSource() == xuatBtn) {
            exportExcel();
        }
    }

    public void resetFiltersAndSearch() {
        statusFilterComboBox.setSelectedItem("Tất cả");
        positionFilterComboBox.setSelectedItem("Tất cả");
        departmentFilterComboBox.setSelectedItem("Tất cả");
        nameSearchField.setText("");
    }
    public void clearFields() {
        idChamCongField.setText("");
        idNhanVienComboBox.setSelectedItem(null);
        ngayLamViecField.setText("");
        gioBatDauField.setText("");
        gioKetThucField.setText("");
    }

    public void exportExcel() {
        int columnCount = defaultTableModel.getColumnCount();
        DefaultTableModel filteredModel = new DefaultTableModel();

        for (int i = 0; i < columnCount; i++) {
            filteredModel.addColumn(defaultTableModel.getColumnName(i));
        }

        int rowCount = chamCongTable.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            if (chamCongTable.convertRowIndexToModel(i) != -1) {
                Object[] rowData = new Object[columnCount];
                for (int j = 0; j < columnCount; j++) {
                    // Chuyển đổi ngày và giờ sang định dạng chuỗi
                    Object value = chamCongTable.getValueAt(i, j);
                    if (value instanceof Date) {
                        rowData[j] = ((Date) value).toString();
                    } else if (value instanceof Time) {
                        rowData[j] = ((Time) value).toString();
                    } else {
                        rowData[j] = value;
                    }
                }
                filteredModel.addRow(rowData);
            }
        }
        ExcelExporter.exportToExcel(filteredModel, "C:\\UTT\\java\\xuatExcel\\ChamCong.xlsx");
    }

    public JTable getChamCongTable() {
        return chamCongTable;
    }
}

