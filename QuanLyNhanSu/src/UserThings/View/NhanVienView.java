package UserThings.View;

import UserThings.Controller.ThongTinNhanVienController;
import UserThings.Model.NhanVien;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class NhanVienView extends JFrame{

    private final JLabel quyLbl;
    private final JLabel gioLamLbl;
    private final JLabel thuongLbl;
    private final JLabel thucLanhlbl;

    private final JButton saveBtn;
    private final JButton clearBtn;
    private final JButton ResetBtn;
    private final JTextField fNameTxtField;
    private final JTextField birthDateTxtField;
    private final JTextField genderTxtField;
    private final JTextField addressTxtField;
    private final JTextField homePhoneTxtField;
    private final JTextField emailTxtField;
    private final JTextField titleTxtField;
    private final JTextField statusTxtField;
    private final JTextField departmentTxtField;
    private final JTextField hiredDateTxtField;
    private final JMenuItem item1;
    private final JMenuItem item2;
    private final JMenuItem item3;

    private ThongTinNhanVienController controller;

    private final Font customFont = new Font("Sans-serif", Font.PLAIN, 13);
    GridBagConstraints gbc = new GridBagConstraints();

    public NhanVienView() {
        setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setTitle("Thong tin ca nhan");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel MainPanel = new JPanel(new BorderLayout());
        this.add(MainPanel);

        //hamburger menu
        JPanel HamburgerPanel = new JPanel(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();
        JMenu hamburgerMenu = new JMenu();
        hamburgerMenu.setText("☰"); // Unicode for hamburger icon

        item1 = new JMenuItem("Change password");
        item2 = new JMenuItem("Check in for today");
        item3 = new JMenuItem("Sign out");

        hamburgerMenu.add(item1);
        hamburgerMenu.add(item2);
        hamburgerMenu.add(item3);

        menuBar.add(hamburgerMenu);
        HamburgerPanel.add(menuBar, BorderLayout.NORTH);
        MainPanel.add(menuBar, BorderLayout.NORTH);
        //hamburger menu

        //center Panel
        JPanel CenterPanel = new JPanel(new GridBagLayout());
        CenterPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        MainPanel.add(CenterPanel, BorderLayout.CENTER);
        //center Panel

        //control btn
        JPanel ControlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ControlPanel.setBorder(BorderFactory.createEmptyBorder(0, -10, 0, 0));
        ImageIcon originalIcon1 = new ImageIcon("QuanLyNhanSu/icons/save-file.png");
        Image originalImage1 = originalIcon1.getImage();

        int width = 20; // Desired width
        int height = 20; // Desired height
        Image scaledImage1 = originalImage1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        saveBtn = new JButton("Save Changes", scaledIcon1);
        saveBtn.setFont(customFont);


        ImageIcon originalIcon2 = new ImageIcon("QuanLyNhanSu/icons/undo.png");
        Image originalImage2 = originalIcon2.getImage();
        Image scaledImage2 = originalImage2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        clearBtn = new JButton("Clear All", scaledIcon2);
        clearBtn.setFont(customFont);

        ImageIcon originalIcon3 = new ImageIcon("QuanLyNhanSu/icons/arrow.png");
        Image originalImage3 = originalIcon3.getImage();
        Image scaledImage3 = originalImage3.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
        ResetBtn = new JButton("Reset Changes", scaledIcon3);
        ResetBtn.setFont(customFont);

        saveBtn.setOpaque(false);
        saveBtn.setContentAreaFilled(false);
        saveBtn.setBorderPainted(false);

        clearBtn.setOpaque(false);
        clearBtn.setContentAreaFilled(false);
        clearBtn.setBorderPainted(false);

        ResetBtn.setOpaque(false);
        ResetBtn.setContentAreaFilled(false);
        ResetBtn.setBorderPainted(false);

        ControlPanel.add(saveBtn);
        ControlPanel.add(clearBtn);
        ControlPanel.add(ResetBtn);

        GridBagConstraints controlGbc = new GridBagConstraints();
        controlGbc.gridx = 0;
        controlGbc.gridy = 0;
        controlGbc.gridwidth = 2;
        controlGbc.insets = new Insets(10,0,10,0);
        controlGbc.fill = GridBagConstraints.HORIZONTAL;
        controlGbc.anchor = GridBagConstraints.NORTHWEST;
        CenterPanel.add(ControlPanel, controlGbc);
        //control btn

        //top panel
        JPanel TopPanel = new JPanel(new GridBagLayout());

        JLabel fullNameLbl = new JLabel("Full Name");
        fullNameLbl.setFont(customFont);
        GridBagConstraints fNameGbc = new GridBagConstraints();
        fNameGbc.gridx = 0;
        fNameGbc.gridy = 0;
        fNameGbc.anchor = GridBagConstraints.WEST;
        fNameGbc.insets = new Insets(0, 0, 2, 35);
        TopPanel.add(fullNameLbl, fNameGbc);

        fNameTxtField = new JTextField();
        fNameTxtField.setEditable(false);
        fNameTxtField.setPreferredSize(new Dimension(400, 20));
        GridBagConstraints fNameTxtGbc = new GridBagConstraints();
        fNameTxtGbc.insets = new Insets(0,0,2,20);
        fNameTxtGbc.gridx = 1;
        fNameTxtGbc.gridy = 0;
        fNameTxtGbc.gridwidth = 5;
        fNameTxtGbc.fill = GridBagConstraints.HORIZONTAL;
        TopPanel.add(fNameTxtField, fNameTxtGbc);


        JLabel genderLbl = new JLabel("Gender");
        genderLbl.setFont(customFont);
        GridBagConstraints genderLblGbc = new GridBagConstraints();
        genderLblGbc.gridx = 0;
        genderLblGbc.gridy = 1;
        genderLblGbc.anchor = GridBagConstraints.WEST;
        genderLblGbc.insets = new Insets(0, 0, 2, 20);
        TopPanel.add(genderLbl, genderLblGbc);

        genderTxtField = new JTextField();
        genderTxtField.setEditable(false);
        genderTxtField.setPreferredSize(new Dimension(400, 20));
        GridBagConstraints genderTxtGbc = new GridBagConstraints();
        genderTxtGbc.insets = new Insets(0,0,2,20);
        genderTxtGbc.gridx = 1;
        genderTxtGbc.gridy = 1;
        genderTxtGbc.gridwidth = 5;
        genderTxtGbc.fill = GridBagConstraints.HORIZONTAL;
        TopPanel.add(genderTxtField, genderTxtGbc);

        JLabel birthDateLbl = new JLabel("Birth Date");
        birthDateLbl.setFont(customFont);
        GridBagConstraints birthDateGbc = new GridBagConstraints();
        birthDateGbc.gridx = 0;
        birthDateGbc.gridy = 2;
        birthDateGbc.anchor = GridBagConstraints.WEST;
        birthDateGbc.insets = new Insets(0, 0, 2, 20);
        TopPanel.add(birthDateLbl, birthDateGbc);

        birthDateTxtField = new JTextField();
        birthDateTxtField.setEditable(false);
        birthDateTxtField.setPreferredSize(new Dimension(400, 20));
        GridBagConstraints birthDateTxtFieldGbc = new GridBagConstraints();
        birthDateTxtFieldGbc.insets = new Insets(0,0,2,20);
        birthDateTxtFieldGbc.gridx = 1;
        birthDateTxtFieldGbc.gridy = 2;
        birthDateTxtFieldGbc.gridwidth = 5;
        birthDateTxtFieldGbc.fill = GridBagConstraints.HORIZONTAL;
        TopPanel.add(birthDateTxtField, birthDateTxtFieldGbc);

        JLabel addressLbl = new JLabel("Address");
        addressLbl.setFont(customFont);
        GridBagConstraints addressLblGbc = new GridBagConstraints();
        addressLblGbc.anchor = GridBagConstraints.WEST;
        addressLblGbc.gridx = 0;
        addressLblGbc.gridy = 3;
        addressLblGbc.insets = new Insets(0, 0, 0, 20);
        TopPanel.add(addressLbl, addressLblGbc);

        addressTxtField = new JTextField();
        addressTxtField.setPreferredSize(new Dimension(400, 20));
        GridBagConstraints addressTxtFieldGbc = new GridBagConstraints();
        addressTxtFieldGbc.insets = new Insets(0,0,0,20);
        addressTxtFieldGbc.gridx = 1;
        addressTxtFieldGbc.gridy = 3;
        addressTxtFieldGbc.gridwidth = 5;
        addressTxtFieldGbc.fill = GridBagConstraints.HORIZONTAL;
        TopPanel.add(addressTxtField, addressTxtFieldGbc);

        ImageIcon originalIcon = new ImageIcon("QuanLyNhanSu/icons/default-avatar-photo-placeholder-profile-icon-vector.jpg");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(130, 85, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);


        JLabel avatarIcon = new JLabel(scaledIcon);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        // Set the border to the label
        avatarIcon.setBorder(border);
        GridBagConstraints avatarGbc = new GridBagConstraints();
        avatarGbc.insets = new Insets(0, 10,0,0);
        avatarGbc.gridx = 6;
        avatarGbc.gridy = 0;
        avatarGbc.gridwidth = 3;
        avatarGbc.gridheight = 4;
        avatarGbc.fill = GridBagConstraints.BOTH;
        TopPanel.add(avatarIcon, avatarGbc);


        GridBagConstraints topGbc = new GridBagConstraints();
        topGbc.gridx = 0;
        topGbc.gridy = 1;
        topGbc.gridwidth = 3;
        topGbc.insets = new Insets(0,0,30,0);
        topGbc.anchor = GridBagConstraints.NORTHWEST;
        CenterPanel.add(TopPanel, topGbc);
        //top panel

        //address panel


        //contact panel
        JPanel ContactPanel = new JPanel(new GridBagLayout());

        JLabel homePhoneLbl = new JLabel("Mobile Phone");
        homePhoneLbl.setFont(customFont);
        GridBagConstraints homePhoneLblGbc = new GridBagConstraints();
        homePhoneLblGbc.gridx = 0;
        homePhoneLblGbc.gridy = 0;
        homePhoneLblGbc.anchor = GridBagConstraints.WEST;
        homePhoneLblGbc.insets = new Insets(0, 0, 2,15 );
        ContactPanel.add(homePhoneLbl, homePhoneLblGbc);

        homePhoneTxtField = new JTextField();
        homePhoneTxtField.setPreferredSize(new Dimension(560, 20));
        GridBagConstraints homePhoneTxtGbc = new GridBagConstraints();
        homePhoneTxtGbc.insets = new Insets(0,0,0,0);
        homePhoneTxtGbc.gridx = 1;
        homePhoneTxtGbc.gridy = 0;
        homePhoneTxtGbc.gridwidth = 5;
        homePhoneTxtGbc.fill = GridBagConstraints.HORIZONTAL;
        ContactPanel.add(homePhoneTxtField, homePhoneTxtGbc);

        JLabel emailLbl = new JLabel("Email");
        emailLbl.setFont(customFont);
        GridBagConstraints emailGbc = new GridBagConstraints();
        emailGbc.gridx = 0;
        emailGbc.gridy = 1;
        emailGbc.anchor = GridBagConstraints.WEST;
        emailGbc.insets = new Insets(0, 0, 0,15 );
        ContactPanel.add(emailLbl, emailGbc);

        emailTxtField = new JTextField();
        emailTxtField.setPreferredSize(new Dimension(560, 20));
        GridBagConstraints emailTxtGbc = new GridBagConstraints();
        emailTxtGbc.insets = new Insets(2,0,0,0);
        emailTxtGbc.gridx = 1;
        emailTxtGbc.gridy = 1;
        emailTxtGbc.gridwidth = 5;
        emailTxtGbc.fill = GridBagConstraints.HORIZONTAL;
        ContactPanel.add(emailTxtField, emailTxtGbc);

        GridBagConstraints contactPanelGbc = new GridBagConstraints();
        contactPanelGbc.gridx = 0;
        contactPanelGbc.gridy = 2;
        contactPanelGbc.gridwidth = 4;
        contactPanelGbc.insets = new Insets(0,0,30,0);
        contactPanelGbc.anchor = GridBagConstraints.NORTHWEST;
        CenterPanel.add(ContactPanel, contactPanelGbc);

        //contact panel

        //work panel
        JPanel WorkPanel = new JPanel(new GridBagLayout());

        JLabel titleLbl = new JLabel("Title");
        titleLbl.setFont(customFont);
        GridBagConstraints titleLblGbc = new GridBagConstraints();
        titleLblGbc.gridx = 0;
        titleLblGbc.gridy = 0;
        titleLblGbc.anchor = GridBagConstraints.WEST;
        titleLblGbc.insets = new Insets(0, 0, 2,0);
        WorkPanel.add(titleLbl, titleLblGbc);

        titleTxtField = new JTextField();
        titleTxtField.setEditable(false);
        titleTxtField.setPreferredSize(new Dimension(350, 20));
        GridBagConstraints titleTxtGbc = new GridBagConstraints();
        titleTxtGbc.insets = new Insets(0,0,2,20);
        titleTxtGbc.gridx = 1;
        titleTxtGbc.gridy = 0;
        titleTxtGbc.gridwidth = 3;
        titleTxtGbc.fill = GridBagConstraints.HORIZONTAL;
        WorkPanel.add(titleTxtField, titleTxtGbc);

        JLabel statusLbl = new JLabel("Status");
        statusLbl.setFont(customFont);
        GridBagConstraints statusLblGbc = new GridBagConstraints();
        statusLblGbc.gridx = 4;
        statusLblGbc.gridy = 0;
        statusLblGbc.insets = new Insets(0, 0, 2,10 );
        WorkPanel.add(statusLbl, statusLblGbc);

        statusTxtField = new JTextField();
        statusTxtField.setEditable(false);
        statusTxtField.setPreferredSize(new Dimension(140, 20));
        GridBagConstraints statusTxtGbc = new GridBagConstraints();
        statusTxtGbc.insets = new Insets(0,0,2,0);
        statusTxtGbc.gridx = 5;
        statusTxtGbc.gridy = 0;
        WorkPanel.add(statusTxtField, statusTxtGbc);

        JLabel departmentLbl = new JLabel("Department");
        departmentLbl.setFont(customFont);
        GridBagConstraints departmentLblGbc = new GridBagConstraints();
        departmentLblGbc.gridx = 0;
        departmentLblGbc.gridy = 1;
        departmentLblGbc.insets = new Insets(0, 0, 2,27);
        WorkPanel.add(departmentLbl, departmentLblGbc);

        departmentTxtField = new JTextField();
        departmentTxtField.setEditable(false);
        departmentTxtField.setPreferredSize(new Dimension(350, 20));
        GridBagConstraints departmentTxtGbc = new GridBagConstraints();
        departmentTxtGbc.insets = new Insets(0,0,2,0);
        departmentTxtGbc.gridx = 1;
        departmentTxtGbc.gridy = 1;
        departmentTxtGbc.gridwidth = 5;
        departmentTxtGbc.fill = GridBagConstraints.HORIZONTAL;
        WorkPanel.add(departmentTxtField, departmentTxtGbc);

        JLabel hiredDateLbl = new JLabel("Hired Date");
        hiredDateLbl.setFont(customFont);
        GridBagConstraints hiredDateLblGbc = new GridBagConstraints();
        hiredDateLblGbc.gridx = 0;
        hiredDateLblGbc.gridy = 3;
        hiredDateLblGbc.anchor = GridBagConstraints.WEST;
        hiredDateLblGbc.insets = new Insets(0, 0, 0,20);
        WorkPanel.add(hiredDateLbl, hiredDateLblGbc);

        hiredDateTxtField = new JTextField();
        hiredDateTxtField.setEditable(false);
        hiredDateTxtField.setPreferredSize(new Dimension(350, 20));
        GridBagConstraints hiredDateTxtGbc = new GridBagConstraints();
        hiredDateTxtGbc.insets = new Insets(0,0,0,0);
        hiredDateTxtGbc.gridx = 1;
        hiredDateTxtGbc.gridy = 3;
        hiredDateTxtGbc.gridwidth = 5;
        hiredDateTxtGbc.fill = GridBagConstraints.HORIZONTAL;
        WorkPanel.add(hiredDateTxtField, hiredDateTxtGbc);


        GridBagConstraints workPanelGbc = new GridBagConstraints();
        workPanelGbc.gridx = 0;
        workPanelGbc.gridy = 3;
        workPanelGbc.gridwidth = 4;
        workPanelGbc.anchor = GridBagConstraints.NORTHWEST;
        CenterPanel.add(WorkPanel, workPanelGbc);

        //work panel

        //salary Panel
        JPanel SalaryPanel = new JPanel(new GridBagLayout());
        GridBagConstraints SalaryPanelGbc = new GridBagConstraints();
        SalaryPanelGbc.insets = new Insets(80,0,0,0);
        SalaryPanelGbc.gridx = 0;
        SalaryPanelGbc.gridy = 4;
        SalaryPanelGbc.gridwidth = 5;
        SalaryPanelGbc.fill = GridBagConstraints.HORIZONTAL;
        CenterPanel.add(SalaryPanel, SalaryPanelGbc);

        quyLbl = new JLabel();
        Font quyFont = new Font("Sans-serif", Font.BOLD, 20);
        quyLbl.setFont(quyFont);
        GridBagConstraints quyLabelGbc = new GridBagConstraints();
        quyLabelGbc.gridx = 0;
        quyLabelGbc.gridy = 0;
        quyLabelGbc.gridwidth = 2;
        quyLabelGbc.fill = GridBagConstraints.HORIZONTAL;
        quyLabelGbc.anchor = GridBagConstraints.WEST;
        SalaryPanel.add(quyLbl,quyLabelGbc);

        GridBagConstraints filler0Gbc = new GridBagConstraints();
        filler0Gbc.gridx = 1;
        filler0Gbc.gridy = 0; // Adjust the row number based on your layout
        filler0Gbc.weightx = 1.0;
        filler0Gbc.fill = GridBagConstraints.VERTICAL;
        JPanel filler0Panel = new JPanel();
        SalaryPanel.add(filler0Panel, filler0Gbc);

        JPanel LuongPanel = new JPanel(new GridBagLayout());
        GridBagConstraints luongPanelGbc = new GridBagConstraints();
        luongPanelGbc.gridx = 0;
        luongPanelGbc.gridy = 1;
        luongPanelGbc.gridwidth = 2;
        luongPanelGbc.fill = GridBagConstraints.HORIZONTAL;
        luongPanelGbc.anchor = GridBagConstraints.WEST;
        luongPanelGbc.insets = new Insets(30,0,30,0);
        SalaryPanel.add(LuongPanel, luongPanelGbc);

        JLabel soGioLam = new JLabel("Number of hours: ");
        soGioLam.setFont(customFont);
        GridBagConstraints soGioLamGbc = new GridBagConstraints();
        soGioLamGbc.insets = new Insets(0,0,0,0);
        soGioLamGbc.gridx = 0;
        soGioLamGbc.gridy = 0;
        soGioLamGbc.weightx = 1;
        soGioLamGbc.anchor = GridBagConstraints.WEST;
        LuongPanel.add(soGioLam, soGioLamGbc);

        gioLamLbl = new JLabel();
        gioLamLbl.setText("Text Holder");
        gioLamLbl.setFont(customFont);
        GridBagConstraints gioLamLblGbc = new GridBagConstraints();
        gioLamLblGbc.insets = new Insets(0,0,0,80);
        gioLamLblGbc.gridx = 1;
        gioLamLblGbc.gridy = 0;
        gioLamLblGbc.anchor = GridBagConstraints.EAST;
        LuongPanel.add(gioLamLbl, gioLamLblGbc);

        JLabel thuong = new JLabel("Bonus: ");
        thuong.setFont(customFont);
        GridBagConstraints thuongGbc = new GridBagConstraints();
        thuongGbc.insets = new Insets(0,0,0,0);
        thuongGbc.gridx = 0;
        thuongGbc.gridy = 1;
        thuongGbc.weightx = 1;
        thuongGbc.anchor = GridBagConstraints.WEST;
        LuongPanel.add(thuong, thuongGbc);

        thuongLbl = new JLabel();
        thuongLbl.setText("Text Holder");
        thuongLbl.setFont(customFont);
        GridBagConstraints thuongLblGbc = new GridBagConstraints();
        thuongLblGbc.insets = new Insets(0,0,0,80);
        thuongLblGbc.gridx = 1;
        thuongLblGbc.gridy = 1;
        thuongLblGbc.anchor = GridBagConstraints.EAST;
        LuongPanel.add(thuongLbl, thuongLblGbc);

        JLabel thucLanh = new JLabel("Final Salary: ");
        Font thucLanhFont = new Font("Sans-serif", Font.BOLD, 18);
        thucLanh.setFont(thucLanhFont);
        GridBagConstraints thuclanhGbc = new GridBagConstraints();
        thuclanhGbc.insets = new Insets(10,0,0,0);
        thuclanhGbc.gridx = 0;
        thuclanhGbc.gridy = 2;
        thuclanhGbc.weightx = 1;
        thuclanhGbc.anchor = GridBagConstraints.WEST;
        LuongPanel.add(thucLanh, thuclanhGbc );

        thucLanhlbl = new JLabel();
        thucLanhlbl.setText("Text Holder");
        thucLanhlbl.setFont(thucLanhFont);
        GridBagConstraints thucLanhlblGbc = new GridBagConstraints();
        thucLanhlblGbc.insets = new Insets(10,0,0,80);
        thucLanhlblGbc.gridx = 1;
        thucLanhlblGbc.gridy = 2;
        thucLanhlblGbc.anchor = GridBagConstraints.EAST;
        LuongPanel.add(thucLanhlbl, thucLanhlblGbc);





        //salary Panel

        //salary Panel on the left
        GridBagConstraints fillerGbc = new GridBagConstraints();
        fillerGbc.gridx = 0;
        fillerGbc.gridy = 5;
        fillerGbc.weightx = 1.0;
        fillerGbc.weighty = 1.0;
        fillerGbc.fill = GridBagConstraints.VERTICAL;

        JPanel fillerPanel = new JPanel();
        CenterPanel.add(fillerPanel, fillerGbc);


        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        this.setResizable(false);
        this.setSize(760, 680);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }


    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void addActionListenerToButton(ActionListener listener) {

        saveBtn.addActionListener(listener);
    }

    public void addActionListenertoReset(ActionListener listener) {
        ResetBtn.addActionListener(listener);
    }

    public void addActionListenerToDelete(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addActionListenerToChangePassword(ActionListener listener) {
        item1.addActionListener(listener);
    }

    public void displayData(NhanVien nhanVien) {
        fNameTxtField.setText(nhanVien.getHoTen());
        genderTxtField.setText(nhanVien.getGioiTinh());
        birthDateTxtField.setText(nhanVien.getDate());
        addressTxtField.setText(nhanVien.getDiaChi());
        homePhoneTxtField.setText(nhanVien.getSoDienThoai());
        emailTxtField.setText(nhanVien.getEmail());
        statusTxtField.setText(nhanVien.getTinhTrang());
        hiredDateTxtField.setText(nhanVien.getNgayVaoLam());
        titleTxtField.setText(nhanVien.getTenChucVu());
        departmentTxtField.setText(nhanVien.getTenPhongBan());

        int thang = nhanVien.getThang();
        int nam = nhanVien.getNam();
        String text = "QUARTER " + thang + "/" + nam;
        quyLbl.setText(text);

        gioLamLbl.setText(nhanVien.getSoGioLam() + " hours");
        thuongLbl.setText(nhanVien.getThuong() + " VND");
        thucLanhlbl.setText(nhanVien.getThucLanh() + " VND");
    }

    public String getAdressTxt() {
        return addressTxtField.getText();
    }

    public String getHomePhoneTxt() {
        return homePhoneTxtField.getText();
    }

    public String getEmailTxt() {
        return emailTxtField.getText();
    }

    public void setAddressTxtField() {
        addressTxtField.setText("");
    }

    public void setEmailTxtField() {
        emailTxtField.setText("");
    }

    public void setHomePhoneTxtField() {
        homePhoneTxtField.setText("");
    }
}

