// PhongBanController.java
package QuanLyPhongBan.Controller;

import QuanLyPhongBan.Model.PhongBanModel;
import QuanLyPhongBan.View.PhongBanView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class PhongBanController {
    private PhongBanModel model;
    private PhongBanView view;

    public PhongBanController(PhongBanModel model, PhongBanView view) {
        this.model = model;
        this.view = view;

        // Gắn các sự kiện cho các button trong View
        view.getAddBtn().addActionListener(new AddButtonListener());
        view.getEditBtn().addActionListener(new EditButtonListener());
        view.getDeleteBtn().addActionListener(new DeleteButtonListener());

        // Hiển thị dữ liệu ban đầu
        displayPhongBanList();
    }

    private void displayPhongBanList() {
        // Xóa dữ liệu hiện tại trên bảng
        clearTable();

        // Lấy danh sách phòng ban từ Model và hiển thị trên View
        for (int i = 0; i < model.getRowCount(); i++) {
            String id = model.getValueAt(i, 0).toString();
            String ten = model.getValueAt(i, 1).toString();
            view.getTableModel().addRow(new Object[]{id, ten});
        }
    }

    private void clearTable() {
        view.getTableModel().setRowCount(0);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Lấy thông tin từ các trường nhập liệu trong View
            String id = view.getIDField().getText();
            String ten = view.getNameField().getText();

            // Kiểm tra xem ID và tên có rỗng không
            if (id.isEmpty() || ten.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập ID và tên phòng ban.");
                return;
            }

            // Thêm phòng ban mới vào Model và hiển thị trên View
            model.addRow(id, ten);
            view.getTableModel().addRow(new Object[]{id, ten});

            // Xóa nội dung trên các trường nhập liệu
            view.getIDField().setText("");
            view.getNameField().setText("");
        }
    }

    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Lấy dòng được chọn từ bảng trong View
            int selectedRow = view.getPhongBanTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một phòng ban để sửa.");
                return;
            }

            // Lấy thông tin từ các trường nhập liệu trong View
            String id = view.getIDField().getText();
            String ten = view.getNameField().getText();

            // Kiểm tra xem ID và tên có rỗng không
            if (id.isEmpty() || ten.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập ID và tên phòng ban.");
                return;
            }

            // Sửa thông tin phòng ban trong Model và hiển thị trên View
            model.setValueAt(id, selectedRow, 0);
            model.setValueAt(ten, selectedRow, 1);

            // Cập nhật lại dữ liệu trên View
            view.getTableModel().setValueAt(id, selectedRow, 0);
            view.getTableModel().setValueAt(ten, selectedRow, 1);

            // Xóa nội dung trên các trường nhập liệu
            view.getIDField().setText("");
            view.getNameField().setText("");
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Lấy dòng được chọn từ bảng trong View
            int selectedRow = view.getPhongBanTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một phòng ban để xóa.");
                return;
            }

            // Xóa phòng ban khỏi Model và hiển thị trên View
            model.removeRow(selectedRow);
            view.getTableModel().removeRow(selectedRow);
        }
    }
}
