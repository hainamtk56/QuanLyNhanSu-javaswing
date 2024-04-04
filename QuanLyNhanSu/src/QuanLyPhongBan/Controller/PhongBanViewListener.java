package QuanLyPhongBan.Controller;

import QuanLyPhongBan.Model.PhongBan;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;

public class PhongBanViewListener {
    private final PhongBanDAO phongBanDAO;
    private final DefaultTableModel defaultTableModel;
    private JTextField tenPhongBanField;
    private JTextField idPhongBanField;
    private final JTable phongBanTable;

    public PhongBanViewListener(PhongBanDAO phongBanDAO, DefaultTableModel defaultTableModel, JTable phongBanTable) {
        this.phongBanDAO = phongBanDAO;
        this.defaultTableModel = defaultTableModel;
        this.phongBanTable = phongBanTable;

        // Add listener for row selection in the table
        phongBanTable.getSelectionModel().addListSelectionListener(new TableSelectionListener());
    }

    public void setTenPhongBanField(JTextField tenPhongBanField) {
        this.tenPhongBanField = tenPhongBanField;
    }

    public void setIdPhongBanField(JTextField idPhongBanField) {
        this.idPhongBanField = idPhongBanField;
    }

    public ActionListener createAddBtnListener(JTextField tenPhongBanField) {
        return new AddBtnListener();
    }

    public ActionListener createEditBtnListener() {
        return new EditBtnListener();
    }

    public ActionListener createDeleteBtnListener() {
        return new DeleteBtnListener();
    }

    public ActionListener createSortByIdBtnListener() {
        return new SortByIdBtnListener();
    }

    public ActionListener createSortByNameBtnListener() {
        return new SortByNameBtnListener();
    }

    private class AddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tenPhongBan = tenPhongBanField.getText().trim();
            if (!tenPhongBan.isEmpty()) {
                boolean success = phongBanDAO.insert(new PhongBan(tenPhongBan));
                if (success) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    updateTableData();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm không thành công", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phòng ban", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class EditBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = phongBanTable.getSelectedRow();
            if (selectedRow != -1) {
                int idPhongBan = Integer.parseInt(idPhongBanField.getText());
                String tenPhongBan = tenPhongBanField.getText().trim();
                if (!tenPhongBan.isEmpty()) {
                    boolean success = phongBanDAO.update(new PhongBan(idPhongBan, tenPhongBan));
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Sửa thành công");
                        updateTableData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Sửa không thành công", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phòng ban", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DeleteBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = phongBanTable.getSelectedRow();
            if (selectedRow != -1) {
                int idPhongBan = Integer.parseInt(idPhongBanField.getText());
                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận Xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    boolean success = phongBanDAO.delete(idPhongBan);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Xóa thành công");
                        updateTableData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa không thành công", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SortByIdBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel sortedModel = phongBanDAO.sortById();
            updateTableData(sortedModel);
        }
    }

    private class SortByNameBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel sortedModel = phongBanDAO.sortByName();
            updateTableData(sortedModel);
        }
    }

    private void updateTableData() {
        // Retrieve the updated data from the database
        DefaultTableModel newModel = phongBanDAO.getAllDepartments();

        // Clear the existing table data
        defaultTableModel.setRowCount(0);

        // Update the existing table model with the new data
        for (int i = 0; i < newModel.getRowCount(); i++) {
            Object[] row = new Object[newModel.getColumnCount()];
            for (int j = 0; j < newModel.getColumnCount(); j++) {
                row[j] = newModel.getValueAt(i, j);
            }
            defaultTableModel.addRow(row);
        }
    }

    private void updateTableData(DefaultTableModel sortedModel) {
        // Clear the existing table data
        defaultTableModel.setRowCount(0);

        // Update the existing table model with the new data
        for (int i = 0; i < sortedModel.getRowCount(); i++) {
            Object[] row = new Object[sortedModel.getColumnCount()];
            for (int j = 0; j < sortedModel.getColumnCount(); j++) {
                row[j] = sortedModel.getValueAt(i, j);
            }
            defaultTableModel.addRow(row);
        }
    }


    private class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) { // Ensure that the event is not caused by user adjustment
                int selectedRow = phongBanTable.getSelectedRow();
                if (selectedRow != -1) { // If a row is selected
                    TableModel model = phongBanTable.getModel();
                    String tenPhongBan = (String) model.getValueAt(selectedRow, 1);
                    int idPhongBan = (int) model.getValueAt(selectedRow, 0);
                    idPhongBanField.setText(String.valueOf(idPhongBan));
                    tenPhongBanField.setText(tenPhongBan);
                }
            }
        }
    }
}
