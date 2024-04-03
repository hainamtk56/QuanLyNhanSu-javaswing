package QuanLyNhanVien;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public MainFrame() {
        // Thiết lập cửa sổ
        setTitle("Demo Text Field");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // Tạo các text field và label
        JLabel label1 = new JLabel("Text Field 1:");
        textField1 = new JTextField();
        JLabel label2 = new JLabel("Text Field 2:");
        textField2 = new JTextField();
        JLabel label3 = new JLabel("Text Field 3:");
        textField3 = new JTextField();
        textField3.setEditable(false); // Không cho phép chỉnh sửa trực tiếp

        // Thêm các component vào frame
        add(label1);
        add(textField1);
        add(label2);
        add(textField2);
        add(label3);
        add(textField3);

        // Thêm DocumentListener cho textField1 và textField2
        textField1.getDocument().addDocumentListener(new TextFieldChangeListener());
        textField2.getDocument().addDocumentListener(new TextFieldChangeListener());
    }

    // Lớp inner để xử lý sự kiện DocumentListener
    private class TextFieldChangeListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            calculate();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            calculate();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            calculate();
        }

        private void calculate() {
            // Lấy giá trị từ textField1 và textField2
            String value1 = textField1.getText();
            String value2 = textField2.getText();

            try {
                // Tính tổng của giá trị từ hai text field và hiển thị kết quả trong textField3
                int result = Integer.parseInt(value1) + Integer.parseInt(value2);
                textField3.setText(Integer.toString(result));
            } catch (NumberFormatException ex) {
                // Xử lý ngoại lệ khi người dùng nhập không phải là số
                textField3.setText("");
            }
        }
    }

    public static void main(String[] args) {
        // Tạo và hiển thị frame
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
