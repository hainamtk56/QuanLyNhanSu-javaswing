package QuanLyPhongBan.View;

import QuanLyPhongBan.Controller.PhongBanDAO;
import QuanLyPhongBan.Controller.PhongBanViewListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PhongBanView extends JFrame {
    private final PhongBanDAO phongBanDAO;
    private PhongBanViewListener listener;
    private JButton sortByIdBtn;
    private JButton sortByNameBtn;

    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JScrollPane scrollPaneTable;
    private final DefaultTableModel defaultTableModel;

    private JLabel idPhongBanLabel;
    private JLabel tenPhongBanLabel;
    private JTextField idPhongBanField;
    private JTextField tenPhongBanField;

    private ChiTietPhongBanView chiTietPhongBanView;
    private int selectedIdPhongBan;

    private void updateTableData(DefaultTableModel model) {
        // Clear the existing table data
        defaultTableModel.setRowCount(0);

        // Update the existing table model with the new data
        for (int i = 0; i < model.getRowCount(); i++) {
            Object[] row = new Object[model.getColumnCount()];
            for (int j = 0; j < model.getColumnCount(); j++) {
                row[j] = model.getValueAt(i, j);
            }
            defaultTableModel.addRow(row);
        }
    }

    public PhongBanView() {
        phongBanDAO = new PhongBanDAO();
        defaultTableModel = phongBanDAO.getAllDepartments();

        // Create a new JTable object
        JTable phongBanTable = new JTable(defaultTableModel);

        // Initialize listener
        listener = new PhongBanViewListener(phongBanDAO, defaultTableModel, phongBanTable);

        // Gán giá trị cho tenPhongBanField
        tenPhongBanField = new JTextField(); // Khởi tạo trường tenPhongBanField
        listener.setTenPhongBanField(tenPhongBanField);

        // Gán giá trị cho idPhongBanField
        idPhongBanField = new JTextField(); // Khởi tạo trường idPhongBanField
        listener.setIdPhongBanField(idPhongBanField);

        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addBtn = new JButton("Thêm");
        editBtn = new JButton("Sửa");
        deleteBtn = new JButton("Xóa");
        sortByIdBtn = new JButton("Sắp xếp theo ID");
        sortByNameBtn = new JButton("Sắp xếp theo tên");

        idPhongBanLabel = new JLabel("ID Phòng Ban");
        tenPhongBanLabel = new JLabel("Tên Phòng Ban");

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

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPaneTable)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(idPhongBanLabel)
                                        .addComponent(tenPhongBanLabel))
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
                        .addComponent(idPhongBanLabel)
                        .addComponent(idPhongBanField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tenPhongBanLabel)
                        .addComponent(tenPhongBanField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addBtn)
                        .addComponent(editBtn)
                        .addComponent(deleteBtn)
                        .addComponent(sortByIdBtn)
                        .addComponent(sortByNameBtn)));

        // Add listeners to buttons
        addBtn.addActionListener(e -> listener.createAddBtnListener(tenPhongBanField).actionPerformed(e));
        editBtn.addActionListener(e -> listener.createEditBtnListener().actionPerformed(e));
        deleteBtn.addActionListener(e -> listener.createDeleteBtnListener().actionPerformed(e));
        sortByIdBtn.addActionListener(e -> {
            // Gọi phương thức sắp xếp theo ID từ lớp DAO
            DefaultTableModel sortedModel = phongBanDAO.sortById();
            // Cập nhật dữ liệu trên bảng với dữ liệu đã được sắp xếp
            updateTableData(sortedModel);
        });

        sortByNameBtn.addActionListener(e -> {
            // Gọi phương thức sắp xếp theo tên từ lớp DAO
            DefaultTableModel sortedModel = phongBanDAO.sortByName();
            // Cập nhật dữ liệu trên bảng với dữ liệu đã được sắp xếp
            updateTableData(sortedModel);
        });

        // Add mouse listener to phongBanTable for double-click event
        // Add mouse listener to phongBanTable for double-click event
        // Sự kiện double click vào bảng phòng ban
        phongBanTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = phongBanTable.getSelectedRow();
                    selectedIdPhongBan = (int) phongBanTable.getValueAt(row, 0); // Lưu idPhongBan được chọn
                    String tenPhongBan = (String) phongBanTable.getValueAt(row, 1); // Lấy tên phòng ban
                    // Mở ChiTietPhongBanView và truyền idPhongBan và tenPhongBan
                    ChiTietPhongBanView chiTietPhongBanView = new ChiTietPhongBanView(selectedIdPhongBan, tenPhongBan);
                    chiTietPhongBanView.setVisible(true);
                }
            }
        });






        add(panel);
        setTitle("Quản Lý Phòng Ban");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public int getSelectedIdPhongBan() {
        return selectedIdPhongBan;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PhongBanView::new);
    }
}
