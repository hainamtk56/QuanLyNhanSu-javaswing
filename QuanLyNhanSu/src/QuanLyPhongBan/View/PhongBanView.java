package QuanLyPhongBan.View;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PhongBanView extends JFrame {
    private JLabel titleLabel;
    private JLabel IDLabel;
    private JLabel nameLabel;

    private JTextField IDField;
    private JTextField nameField;

    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;

    private JScrollPane ScrollTable;
    private JTable phongBanTable;

    private DefaultTableModel tableModel;

    private final String[] columnNames = new String[]{"ID", "Tên phòng ban"};

    public PhongBanView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("QUẢN LÝ PHÒNG BAN");
        IDLabel = new JLabel("ID: ");
        nameLabel = new JLabel("Tên phòng ban: ");

        IDField = new JTextField(15);
        nameField = new JTextField(15);

        addBtn = new JButton("Thêm");
        editBtn = new JButton("Sửa");
        deleteBtn = new JButton("Xóa");

        ScrollTable = new JScrollPane();
        phongBanTable = new JTable();

        tableModel = new DefaultTableModel(columnNames, 0);
        phongBanTable.setModel(tableModel);
        ScrollTable.setViewportView(phongBanTable);
        ScrollTable.setPreferredSize(new Dimension(300, 300));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(700, 450);
        panel.setLayout(layout);
        panel.add(ScrollTable);

        panel.add(titleLabel);
        panel.add(IDLabel);
        panel.add(nameLabel);

        panel.add(IDField);
        panel.add(nameField);

        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);

        layout.putConstraint(SpringLayout.WEST, titleLabel, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, IDLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IDLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 100, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, IDField, 120, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IDField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 120, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 100, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addBtn, 150, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editBtn, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, editBtn, 150, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteBtn, 160, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, deleteBtn, 150, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, ScrollTable, 330, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ScrollTable, 20, SpringLayout.NORTH, panel);

        this.add(panel);
        this.setTitle("QUẢN LÝ PHÒNG BAN");
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JTextField getIDField() {
        return IDField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getEditBtn() {
        return editBtn;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public JTable getPhongBanTable() {
        return phongBanTable;
    }

    public static void main(String[] args) {
        PhongBanView view = new PhongBanView();
    }
}
