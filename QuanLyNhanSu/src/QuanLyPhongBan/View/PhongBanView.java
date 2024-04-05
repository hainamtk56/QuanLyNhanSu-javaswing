package QuanLyPhongBan.View;

import QuanLyPhongBan.Controller.PhongBanDAO;
import QuanLyPhongBan.Controller.PhongBanViewListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PhongBanView extends JFrame {
    private final PhongBanDAO phongBanDAO;
    private PhongBanViewListener listener;
    private JTextField tenPhongBanField;
    private JTextField idPhongBanField;
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton sortByIdBtn;
    private JButton sortByNameBtn;
    private JScrollPane scrollPaneTable;
    private final DefaultTableModel defaultTableModel;

    public PhongBanView() {
        phongBanDAO = new PhongBanDAO();
        defaultTableModel = PhongBanDAO.getAllDepartments();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addBtn = new JButton("Thêm");
        editBtn = new JButton("Sửa");
        deleteBtn = new JButton("Xóa");
        sortByIdBtn = new JButton("Sắp xếp theo ID");
        sortByNameBtn = new JButton("Sắp xếp theo tên");

        idPhongBanField = new JTextField(6);
        idPhongBanField.setEditable(false);
        tenPhongBanField = new JTextField(15);

        scrollPaneTable = new JScrollPane();
        JTable phongBanTable = new JTable(defaultTableModel);
        scrollPaneTable.setViewportView(phongBanTable);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel idLabel = new JLabel("ID Phòng Ban");
        JLabel tenLabel = new JLabel("Tên Phòng Ban");

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPaneTable)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(idLabel)
                                        .addComponent(tenLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(idPhongBanField)
                                        .addComponent(tenPhongBanField)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addBtn)
                                                .addComponent(editBtn)
                                                .addComponent(deleteBtn)
                                                .addComponent(sortByIdBtn)
                                                .addComponent(sortByNameBtn))))));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(scrollPaneTable)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(idLabel)
                        .addComponent(idPhongBanField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tenLabel)
                        .addComponent(tenPhongBanField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addBtn)
                        .addComponent(editBtn)
                        .addComponent(deleteBtn)
                        .addComponent(sortByIdBtn)
                        .addComponent(sortByNameBtn)));

        // Initialize listener
        listener = new PhongBanViewListener(phongBanDAO, defaultTableModel, phongBanTable);
        listener.setTenPhongBanField(tenPhongBanField);
        listener.setIdPhongBanField(idPhongBanField);

        // Add listeners to buttons
        addBtn.addActionListener(listener.createAddBtnListener(tenPhongBanField));
        editBtn.addActionListener(listener.createEditBtnListener(tenPhongBanField));
        deleteBtn.addActionListener(listener.createDeleteBtnListener());
        sortByIdBtn.addActionListener(listener.createSortByIdBtnListener());
        sortByNameBtn.addActionListener(listener.createSortByNameBtnListener());

        add(panel);
        setTitle("Quản Lý Phòng Ban");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(PhongBanView::new);
    }
}
