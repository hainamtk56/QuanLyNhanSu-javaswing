package view;

import controller.DBConnection;
import controller.PhieuMuonDAO;
import model.PhieuMuon;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.SpringLayout;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class QuanLyPhieuMuonView extends JFrame implements ActionListener{
    private JButton addStudentBtn;
    private JButton editStudentBtn;
    private JButton deleteStudentBtn;
    private JButton clearBtn;
    private JButton sortStudentGPABtn;
    private JButton sortStudentNameBtn;
    private JScrollPane scrollPaneStudentTable;
    private DefaultTableModel defaultTableModel = PhieuMuonDAO.getAllStudents();
    private JTable studentTable = new JTable(defaultTableModel);

    private JLabel IDLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;
    private JLabel gpaLabel;

    private JTextField IDField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea addressTA;
    private JTextField gpaField;
    
    public QuanLyPhieuMuonView() {
    DBConnection.Connect();
    initComponents();
}
    
    private void refreshTable() {
        defaultTableModel = PhieuMuonDAO.getAllStudents();
        studentTable.setModel(defaultTableModel);
    }
    
     private void clearFieldsActionPerformed(ActionEvent e) {
        clearFields();
    }

    private void clearFields() {
        IDField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressTA.setText("");
        gpaField.setText("");
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addStudentBtn = new JButton("Add");
        editStudentBtn = new JButton("Edit");
        deleteStudentBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortStudentGPABtn = new JButton("Sort By GPA");
        sortStudentNameBtn = new JButton("Sort By Name");
        
        IDLabel = new JLabel("ID");
        nameLabel = new JLabel("Name");
        ageLabel = new JLabel("Age");
        addressLabel = new JLabel("Address");
        gpaLabel = new JLabel("GPA");

        IDField = new JTextField(6);
        IDField.setEditable(false);
        nameField = new JTextField(15);
        ageField = new JTextField(6);
        addressTA = new JTextArea();
        addressTA.setColumns(15);
        addressTA.setRows(5);
        JScrollPane jScrollPaneAddress = new JScrollPane(addressTA);
        gpaField = new JTextField(6);
        
//        scrollPaneStudentTable.setViewportView(studentTable);
        scrollPaneStudentTable = new JScrollPane(studentTable);
        scrollPaneStudentTable.setPreferredSize(new Dimension(480, 300));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(scrollPaneStudentTable);

        panel.add(addStudentBtn);
        panel.add(editStudentBtn);
        panel.add(deleteStudentBtn);
        panel.add(clearBtn);
        panel.add(sortStudentGPABtn);
        panel.add(sortStudentNameBtn);

        panel.add(IDLabel);
        panel.add(nameLabel);
        panel.add(ageLabel);
        panel.add(addressLabel);
        panel.add(gpaLabel);

        panel.add(IDField);
        panel.add(nameField);
        panel.add(ageField);
        panel.add(jScrollPaneAddress);
        panel.add(gpaField);

        layout.putConstraint(SpringLayout.WEST, IDLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IDLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ageLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, addressLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addressLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, gpaLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gpaLabel, 200, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, IDField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IDField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ageField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, gpaField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gpaField, 200, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, scrollPaneStudentTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, scrollPaneStudentTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addStudentBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, 270, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editStudentBtn, 60, SpringLayout.WEST, addStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, editStudentBtn, 270, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, 60, SpringLayout.WEST, editStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 270, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 77, SpringLayout.WEST, deleteStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, 270, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, sortStudentGPABtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortStudentGPABtn, 340, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortStudentNameBtn, 115, SpringLayout.WEST, sortStudentGPABtn);
        layout.putConstraint(SpringLayout.NORTH, sortStudentNameBtn, 340, SpringLayout.NORTH, panel);
        
        deleteStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    int id = Integer.parseInt(IDField.getText());
                    if (PhieuMuonDAO.delete(id)) {
                        JOptionPane.showMessageDialog(panel, "Deleted successfully!");
                        refreshTable();
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(panel, "Deletion failed. Please try again.");                
                    }
                }
            }
        });

        addStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(QuanLyPhieuMuonView.this, "Are you sure you want to add?", "Add Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String address = addressTA.getText();
                    double gpa = Double.parseDouble(gpaField.getText());
                    
                    PhieuMuon student = new PhieuMuon(1, name, age, address, gpa);
                    
                    if (PhieuMuonDAO.insert(student)) {
                        JOptionPane.showMessageDialog(panel, "Add successfully!");
                        refreshTable();
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(panel, "Addition failed. Please try again.", "Error!", JOptionPane.ERROR);                
                    }
                }
                
            }
        });
        
        editStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(QuanLyPhieuMuonView.this, "Are you sure you want to edit?", "Edition Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    int id = Integer.parseInt(IDField.getText());
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String address = addressTA.getText();
                    double gpa = Double.parseDouble(gpaField.getText());

                    PhieuMuon student = new PhieuMuon(id, name, age, address, gpa);
                    if (PhieuMuonDAO.update(student)) {
                        JOptionPane.showMessageDialog(panel, "Edit successfully!");
                        refreshTable();
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(panel, "edition failed. Please try again.", "Error!", JOptionPane.ERROR);                
                    }
                }
            }
        });
        
        clearBtn.addActionListener(this::clearFieldsActionPerformed);
        
        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Kiểm tra xem sự kiện có phải là sự kiện kết thúc chọn không
                if (!e.getValueIsAdjusting()) {
                    // Lấy hàng được chọn từ bảng
                    int selectedRow = studentTable.getSelectedRow();

                    // Kiểm tra xem có hàng được chọn không
                    if (selectedRow != -1) {
                        // Lấy dữ liệu từ bảng và hiển thị vào các textfield
                        int id = (int) studentTable.getValueAt(selectedRow, 0);
                        String name = (String) studentTable.getValueAt(selectedRow, 1);
                        int age = (int) studentTable.getValueAt(selectedRow, 2);
                        String address = (String) studentTable.getValueAt(selectedRow, 3);
                        BigDecimal gpaBigDecimal = (BigDecimal) studentTable.getValueAt(selectedRow, 4);
                        double gpa = gpaBigDecimal.doubleValue();


                        IDField.setText(String.valueOf(id));
                        nameField.setText(name);
                        ageField.setText(String.valueOf(age));
                        addressTA.setText(address);
                        gpaField.setText(String.valueOf(gpa));
                    }
                }
            }
        });
        
        sortStudentGPABtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultTableModel = PhieuMuonDAO.getAllStudentsSortedByGPA();
                studentTable.setModel(defaultTableModel);
            }
        });

        sortStudentNameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultTableModel = PhieuMuonDAO.getAllStudentsSortedByName();
                studentTable.setModel(defaultTableModel);
            }
        });
        
        add(panel);
        setTitle("Student Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearBtn) {
            clearFields();
        }
    }
}