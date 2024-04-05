package DangNhap.View;

import javax.swing.*;
import java.awt.*;

public class ViewUser extends JFrame {
    public ViewUser() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("User View");
        setSize(500, 250);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("This is the User View");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        getContentPane().add(label, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewUser::new);
    }
}
