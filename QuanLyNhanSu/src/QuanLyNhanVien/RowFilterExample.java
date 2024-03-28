package QuanLyNhanVien;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;

public class RowFilterExample {
    private JFrame frame;
    private JTable table;
    private JComboBox<String> filterComboBox;
    private DefaultTableModel tableModel;

    public RowFilterExample() {
        frame = new JFrame("Row Filter Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sample data for the table
        String[] columnNames = {"Name", "Age", "Gender"};
        Object[][] data = {
                {"John", 25, "Male"},
                {"Alice", 30, "Female"},
                {"Bob", 35, "Male"},
                {"Eve", 28, "Female"}
        };

        // Create table model and table
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);

        // Create ComboBox for filtering
        filterComboBox = new JComboBox<>(new String[]{"All", "Male", "Female"});
        filterComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterRows();
            }
        });

        // Add components to frame
        frame.getContentPane().add(new JScrollPane(table));
        frame.getContentPane().add(filterComboBox, "South");

        // Set frame size and visibility
        frame.pack();
        frame.setVisible(true);
    }

    private void filterRows() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        String selectedFilter = (String) filterComboBox.getSelectedItem();
        if (selectedFilter.equals("All")) {
            sorter.setRowFilter(null); // Show all rows
        } else {
            RowFilter<Object, Object> rowFilter = RowFilter.regexFilter(selectedFilter, 2); // Filter based on column index 2 (Gender column)
            sorter.setRowFilter(rowFilter);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RowFilterExample();
            }
        });
    }
}

