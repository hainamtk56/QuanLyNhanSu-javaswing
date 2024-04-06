package Menu;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuView extends JFrame {
    private JMenuItem item1, item2, item3, item4, item5, item6, item7;

    public MenuView() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 563);

        // Use BackgroundPanel as content pane
        BackgroundPanel contentPane = new BackgroundPanel("QuanLyNhanSu/icons/OIP.jpg");
        setContentPane(contentPane);

        JMenuBar menuBar = new JMenuBar();

        // Creating Menus
        JMenu managementMenu = new JMenu("Management");
        JMenu userMenu = new JMenu("User");

        // Creating Menu Items
        item1 = new JMenuItem("Project Management");
        item2 = new JMenuItem("Human Resources Management");
        item3 = new JMenuItem("Department Management");
        item4 = new JMenuItem("Attendance Management");
        item5 = new JMenuItem("User Information");
        item6 = new JMenuItem("Sign Out");
        item7 = new JMenuItem("Salary Management");

        // Adding items to Management Menu
        managementMenu.add(item1);
        managementMenu.addSeparator();
        managementMenu.add(item2);
        managementMenu.addSeparator();
        managementMenu.add(item3);
        managementMenu.addSeparator();
        managementMenu.add(item4);
        managementMenu.addSeparator();
        managementMenu.add(item7);

        // Adding items to User Menu
        userMenu.add(item5);
        userMenu.addSeparator(); // Adds a separator line
        userMenu.add(item6);

        // Adding menus to menu bar
        menuBar.add(managementMenu);
        menuBar.add(userMenu);

        // Set menu bar to frame
        setJMenuBar(menuBar);

        setVisible(true);
    }

    // Action Listeners
    public void quanLyDuAnActionListener(ActionListener listener) {
        item1.addActionListener(listener);
    }

    public void quanLyNhanVienActionListener(ActionListener listener) {
        item2.addActionListener(listener);
    }

    public void quanLyPhongBanActionListener(ActionListener listener) {
        item3.addActionListener(listener);
    }

    public void quanlyChamCongActionListener(ActionListener listener) {
        item4.addActionListener(listener);
    }

    public void quanLyLuongActionListener(ActionListener listener) {
        item7.addActionListener(listener);
    }

    public void thongTinCaNhanActionListener(ActionListener listener) {
        item5.addActionListener(listener);
    }

    public void dangXuatActionListener(ActionListener listener) {
        item6.addActionListener(listener);
    }
}
