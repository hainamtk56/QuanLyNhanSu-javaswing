package QuanLyPhongBan.View;

import QuanLyPhongBan.Controller.Controller;

import javax.swing.*;

public class View {
    private JFrame frame;
    private JTextField textField;
    private JButton button;

    public View() {
        frame = new JFrame("MVC Example");
        textField = new JTextField(20);
        button = new JButton("Click Me");

        JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(button);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public String getInputText() {
        return textField.getText();
    }

    public void setButtonText(String text) {
        button.setText(text);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public void addButtonClickListener(Controller.ButtonClickListener listener) {
        button.addActionListener(listener);
    }
}

