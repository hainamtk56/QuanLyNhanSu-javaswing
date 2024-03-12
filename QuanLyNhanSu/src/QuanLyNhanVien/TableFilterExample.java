package QuanLyNhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TableFilterExample extends JFrame {
    private JComboBox<String> genderFilterComboBox;
    private JComboBox<String> statusFilterComboBox;
    private JComboBox<String> positionFilterComboBox;
    private JComboBox<String> departmentFilterComboBox;
    private JTable table;
    private DefaultTableModel tableModel;

    public TableFilterExample() {
        setTitle("Table Filter Example");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Data for table
        Object[][] data = {
                {"John", "Doe", "Nam", "Đang làm việc", "Quản lý", "Kế toán"},
                {"Jane", "Smith", "Nữ", "Tạm nghỉ việc", "Nhân viên", "Kế hoạch"},
                {"Bob", "Johnson", "Nam", "Đã nghỉ việc", "Quản lý", "Kế toán"},
                {"Alice", "Williams", "Nữ", "Đang làm việc", "Nhân viên", "Kế hoạch"}
        };
        String[] columns = {"Họ", "Tên", "Giới tính", "Tình trạng", "Chức vụ", "Phòng ban"};

        // Table model
        tableModel = new DefaultTableModel(data, columns);

        // Table
        table = new JTable(tableModel);

        // Filter ComboBoxes
        genderFilterComboBox = new JComboBox<>(getDistinctColumnValues(2)); // Gender column
        genderFilterComboBox.addActionListener(new FilterActionListener(2));

        statusFilterComboBox = new JComboBox<>(getDistinctColumnValues(3)); // Status column
        statusFilterComboBox.addActionListener(new FilterActionListener(3));

        positionFilterComboBox = new JComboBox<>(getDistinctColumnValues(4)); // Position column
        positionFilterComboBox.addActionListener(new FilterActionListener(4));

        departmentFilterComboBox = new JComboBox<>(getDistinctColumnValues(5)); // Department column
        departmentFilterComboBox.addActionListener(new FilterActionListener(5));

        // Layout
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout());
        filterPanel.add(new JLabel("Giới tính:"));
        filterPanel.add(genderFilterComboBox);
        filterPanel.add(new JLabel("Tình trạng:"));
        filterPanel.add(statusFilterComboBox);
        filterPanel.add(new JLabel("Chức vụ:"));
        filterPanel.add(positionFilterComboBox);
        filterPanel.add(new JLabel("Phòng ban:"));
        filterPanel.add(departmentFilterComboBox);

        setLayout(new BorderLayout());
        add(filterPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // Method to get distinct values from a column
    private String[] getDistinctColumnValues(int columnIndex) {
        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String value = (String) tableModel.getValueAt(i, columnIndex);
            if (!values.contains(value)) {
                values.add(value);
            }
        }
        return values.toArray(new String[0]);
    }

    // ActionListener for filter ComboBoxes
    private class FilterActionListener implements ActionListener {
        private int columnIndex;

        public FilterActionListener(int columnIndex) {
            this.columnIndex = columnIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            String selectedValue = (String) comboBox.getSelectedItem();
            filterTableByColumn(columnIndex, selectedValue);
        }
    }

    // Method to filter table by a column value
    private void filterTableByColumn(int columnIndex, String value) {
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);
        rowSorter.setRowFilter(RowFilter.regexFilter(value, columnIndex));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TableFilterExample().setVisible(true);
            }
        });
    }
}
