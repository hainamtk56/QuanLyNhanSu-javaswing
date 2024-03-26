package QuanLyPhongBan.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhongBanViewListener {
    private PhongBanDAO phongBanDAO;

    public PhongBanViewListener(PhongBanDAO phongBanDAO) {
        this.phongBanDAO = phongBanDAO;
    }

    public ActionListener createAddBtnListener() {
        return new AddBtnListener();
    }

    public ActionListener createEditBtnListener() {
        return new EditBtnListener();
    }

    public ActionListener createDeleteBtnListener() {
        return new DeleteBtnListener();
    }

    public ActionListener createClearBtnListener() {
        return new ClearBtnListener();
    }

    private class AddBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to add?", "Add Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Implement adding logic here
            }
        }
    }

    private class EditBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to edit?", "Edit Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Implement editing logic here
            }
        }
    }

    private class DeleteBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Implement deleting logic here
            }
        }
    }

    private class ClearBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement clearing logic here
        }
    }
}
