package QuanLyPhongBan.View;

import ConnectionManager.ConnectionManager;
import QuanLyPhongBan.Controller.PhongBanDAO;
import QuanLyPhongBan.Controller.PhongBanViewListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.TableRowSorter;

public class PhongBanView extends JFrame {
    private PhongBanDAO phongBanDAO;
    private PhongBanViewListener listener;
    private JButton addBtn, editBtn, deleteBtn, clearBtn;
    private JScrollPane scrollPaneTable;
    private DefaultTableModel defaultTableModel = PhongBanDAO.getAllDepartments();
    private JTable phongBanTable = new JTable(defaultTableModel);

    private JLabel idPhongBanLabel, tenPhongBanLabel;

    private JTextField idPhongBanField, tenPhongBanField;

    public PhongBanView() {
        ConnectionManager.getConnection();
        phongBanDAO = new PhongBanDAO();
        listener = new PhongBanViewListener(phongBanDAO);
        initComponents();
    }

    private void initComponents() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(defaultTableModel);
        phongBanTable.setRowSorter(sorter);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addBtn = new JButton("Thêm");
        editBtn = new JButton("Sửa");
        deleteBtn = new JButton("Xóa");
        clearBtn = new JButton("Clear");

        idPhongBanLabel = new JLabel("ID Phòng Ban");
        tenPhongBanLabel = new JLabel("Tên Phòng Ban");

        idPhongBanField = new JTextField(6);
        idPhongBanField.setEditable(false);
        tenPhongBanField = new JTextField(15);

        scrollPaneTable = new JScrollPane(phongBanTable);
        scrollPaneTable.setPreferredSize(new Dimension(1247, 400));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1300, 800);
        panel.setLayout(layout);
        panel.add(scrollPaneTable);

        panel.add(idPhongBanLabel);
        panel.add(tenPhongBanLabel);

        panel.add(idPhongBanField);
        panel.add(tenPhongBanField);

        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(clearBtn);

        // Bảng phòng ban
        layout.putConstraint(SpringLayout.WEST, scrollPaneTable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, scrollPaneTable, 20, SpringLayout.NORTH, panel);

        // Cột 1
        layout.putConstraint(SpringLayout.WEST, idPhongBanLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idPhongBanLabel, 20, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, idPhongBanField, 110, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idPhongBanField, 20, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, tenPhongBanLabel, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenPhongBanLabel, 20, SpringLayout.SOUTH, scrollPaneTable);
        layout.putConstraint(SpringLayout.WEST, tenPhongBanField, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenPhongBanField, 20, SpringLayout.SOUTH, scrollPaneTable);

        // Nút
        layout.putConstraint(SpringLayout.WEST, addBtn, 700, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addBtn, 25, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, editBtn, 800, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, editBtn, 25, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, clearBtn, 700, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 85, SpringLayout.SOUTH, scrollPaneTable);

        layout.putConstraint(SpringLayout.WEST, deleteBtn, 800, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, deleteBtn, 85, SpringLayout.SOUTH, scrollPaneTable);

        clearBtn.addActionListener(this::clearFieldsActionPerformed);

        add(panel);
        setTitle("Quản Lý Phòng Ban");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void clearFieldsActionPerformed(ActionEvent e) {
        clearFields();
    }

    private void clearFields() {
        idPhongBanField.setText("");
        tenPhongBanField.setText("");
    }
}
