package QuanLyPhongBan.Controller;

import QuanLyPhongBan.Model.PhongBan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhongBanViewListener {
    private final PhongBanDAO phongBanDAO;
    private final DefaultTableModel defaultTableModel;
    private JTextField tenPhongBanField;
    private JTextField idPhongBanField;
    private final JTable phongBanTable;
    private void clearTextFields() {
        tenPhongBanField.setText("");
        idPhongBanField.setText("");
    }

    public PhongBanViewListener(PhongBanDAO phongBanDAO, DefaultTableModel defaultTableModel, JTable phongBanTable) {
        this.phongBanDAO = phongBanDAO;
        this.defaultTableModel = defaultTableModel;
        this.phongBanTable = phongBanTable;

        // Add listener for row selection in the table
        phongBanTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = phongBanTable.getSelectedRow();
                if (selectedRow != -1) {
                    TableModel model = phongBanTable.getModel();
                    String tenPhongBan = (String) model.getValueAt(selectedRow, 1);
                    int idPhongBan = (int) model.getValueAt(selectedRow, 0);
                    idPhongBanField.setText(String.valueOf(idPhongBan));
                    tenPhongBanField.setText(tenPhongBan);
                }
            }
        });
    }

    public void setTenPhongBanField(JTextField tenPhongBanField) {
        this.tenPhongBanField = tenPhongBanField;
    }

    public void setIdPhongBanField(JTextField idPhongBanField) {
        this.idPhongBanField = idPhongBanField;
    }

    public ActionListener createAddBtnListener(JTextField tenPhongBanField) {
        return new AddBtnListener(tenPhongBanField);
    }

    public ActionListener createEditBtnListener(JTextField tenPhongBanField) {
        return new EditBtnListener(tenPhongBanField);
    }

    public ActionListener createDeleteBtnListener() {
        return new DeleteBtnListener();
    }

    public ActionListener createSortByIdBtnListener() {
        return e -> updateTableData(phongBanDAO.sortById());
    }

    public ActionListener createSortByNameBtnListener() {
        return e -> updateTableData(phongBanDAO.sortByName());
    }

    private void updateTableData(DefaultTableModel model) {
        defaultTableModel.setRowCount(0);
        for (int i = 0; i < model.getRowCount(); i++) {
            Object[] row = new Object[model.getColumnCount()];
            for (int j = 0; j < model.getColumnCount(); j++) {
                row[j] = model.getValueAt(i, j);
            }
            defaultTableModel.addRow(row);
        }
    }

    private class AddBtnListener implements ActionListener {
        private final JTextField tenPhongBanField;

        public AddBtnListener(JTextField tenPhongBanField) {
            this.tenPhongBanField = tenPhongBanField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String tenPhongBan = tenPhongBanField.getText().trim();
            if (!tenPhongBan.isEmpty()) {
                boolean success = phongBanDAO.insert(new PhongBan(tenPhongBan));
                if (success) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                    updateTableData(phongBanDAO.getAllDepartments());
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm không thành công", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phòng ban", "Error", JOptionPane.ERROR_MESSAGE);
            }
            clearTextFields();
        }

    }

    private class EditBtnListener implements ActionListener {
        private final JTextField tenPhongBanField;

        public EditBtnListener(JTextField tenPhongBanField) {
            this.tenPhongBanField = tenPhongBanField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = phongBanTable.getSelectedRow();
            if (selectedRow != -1) {
                int idPhongBan = (int) phongBanTable.getValueAt(selectedRow, 0);
                String tenPhongBan = tenPhongBanField.getText().trim();
                if (!tenPhongBan.isEmpty()) {
                    boolean success = PhongBanDAO.update(new PhongBan(idPhongBan, tenPhongBan));
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Sửa thành công");
                        updateTableData(phongBanDAO.getAllDepartments());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sửa không thành công", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phòng ban", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa", "Error", JOptionPane.ERROR_MESSAGE);
            }
            idPhongBanField.setText("");
            tenPhongBanField.setText("");
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
                        updateTableData(phongBanDAO.getAllDepartments());
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa không thành công", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa", "Error", JOptionPane.ERROR_MESSAGE);
            }
            clearTextFields();
        }
    }
}
