package QuanLyNhanVien;

import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

public class test2 {

    public static void setupTable(JTable table) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        for (int i = 0; i < table.getColumnCount(); i++) {
            JComboBox<String> comboBox = new JComboBox<>(new String[]{"Tăng dần", "Giảm dần"});
            int columnIndex = i;
            comboBox.addActionListener(e -> {
                String selectedItem = (String) comboBox.getSelectedItem();
                List<RowSorter.SortKey> sortKeys = new ArrayList<>();

                // Xác định kiểu sắp xếp dựa trên lựa chọn
                SortOrder sortOrder = selectedItem.equals("Tăng dần") ? SortOrder.ASCENDING : SortOrder.DESCENDING;
                sortKeys.add(new RowSorter.SortKey(columnIndex, sortOrder));
                sorter.setSortKeys(sortKeys);
            });

            TableColumn tableColumn = table.getColumnModel().getColumn(columnIndex);
            tableColumn.setHeaderRenderer((table1, value, isSelected, hasFocus, row, column) -> comboBox);
        }
    }

    public static void main(String[] args) {
        // Tạo frame và table giả định có dữ liệu
        JFrame frame = new JFrame();
        String[] columnNames = {"Tên", "Tuổi", "Email"};
        Object[][] data = {
                {"John", 28, "john@example.com"},
                {"Alice", 24, "alice@example.com"},
                {"Bob", 32, "bob@example.com"}
        };
        JTable table = new JTable(data, columnNames);

        // Gọi setupTable để thêm ComboBox vào headers
        setupTable(table);

        // Hiển thị table lên frame
        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

