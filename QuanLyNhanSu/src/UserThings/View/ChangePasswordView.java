package UserThings.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ChangePasswordView extends JFrame {
    private JLabel currentPassLbl;

    private JLabel newPassLbl;

    private JLabel reTypePassLbl;

    private JTextField currentPassTxtField;

    private JTextField newPassTxtField;

    private JTextField reTypePassTxtField;

    private JButton changePassBtn;

    private final Font customFont = new Font("Sans-serif", Font.PLAIN, 13);

    public ChangePasswordView() {
        setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel PasswordPanel = new JPanel(new GridBagLayout());

        currentPassLbl = new JLabel();
        currentPassLbl.setFont(customFont);
        currentPassLbl.setText("Current password");
        GridBagConstraints currentPassLblGbc = new GridBagConstraints();
        currentPassLblGbc.gridx = 0;
        currentPassLblGbc.gridy = 0;
        currentPassLblGbc.anchor = GridBagConstraints.WEST;
        currentPassLblGbc.insets = new Insets(0,0,2,10);
        PasswordPanel.add(currentPassLbl, currentPassLblGbc);

        currentPassTxtField = new JTextField();
        currentPassTxtField.setPreferredSize(new Dimension(140,20));
        GridBagConstraints currentPassTxtFieldGbc = new GridBagConstraints();
        currentPassTxtFieldGbc.gridx = 1;
        currentPassTxtFieldGbc.gridy = 0;
        currentPassTxtFieldGbc.insets = new Insets(0,0,2,0);
        PasswordPanel.add(currentPassTxtField, currentPassTxtFieldGbc);

        newPassLbl = new JLabel();
        newPassLbl.setFont(customFont);
        newPassLbl.setText("New password");
        GridBagConstraints newPassLblGbc = new GridBagConstraints();
        newPassLblGbc.gridx = 0;
        newPassLblGbc.gridy = 1;
        newPassLblGbc.anchor = GridBagConstraints.WEST;
        newPassLblGbc.insets = new Insets(0,0,2,10);
        PasswordPanel.add(newPassLbl, newPassLblGbc);

        newPassTxtField = new JTextField();
        newPassTxtField.setPreferredSize(new Dimension(140,20));
        GridBagConstraints newPassTxtFieldGbc = new GridBagConstraints();
        newPassTxtFieldGbc.gridx = 1;
        newPassTxtFieldGbc.gridy = 1;
        newPassTxtFieldGbc.insets = new Insets(0,0,2,0);
        PasswordPanel.add(newPassTxtField, newPassTxtFieldGbc);

        reTypePassLbl = new JLabel();
        reTypePassLbl.setFont(customFont);
        reTypePassLbl.setText("Confirm password");
        GridBagConstraints reTypePassLblGbc = new GridBagConstraints();
        reTypePassLblGbc.gridx = 0;
        reTypePassLblGbc.gridy = 2;
        reTypePassLblGbc.anchor = GridBagConstraints.WEST;
        reTypePassLblGbc.insets = new Insets(0,0,2,10);
        PasswordPanel.add(reTypePassLbl, reTypePassLblGbc);

        reTypePassTxtField = new JTextField();
        reTypePassTxtField.setPreferredSize(new Dimension(140,20));
        GridBagConstraints reTypePassTxtFieldGbc = new GridBagConstraints();
        reTypePassTxtFieldGbc.gridx = 1;
        reTypePassTxtFieldGbc.gridy = 2;
        reTypePassTxtFieldGbc.insets = new Insets(0,0,2,0);
        PasswordPanel.add(reTypePassTxtField, reTypePassTxtFieldGbc);

        changePassBtn = new JButton();
        changePassBtn.setText("Confirm");
        changePassBtn.setFont(customFont);
        GridBagConstraints changePassBtnGbc = new GridBagConstraints();
        changePassBtnGbc.gridx = 0;
        changePassBtnGbc.gridy = 3;
        changePassBtnGbc.anchor = GridBagConstraints.CENTER;
        changePassBtnGbc.insets = new Insets(10,0,0,0);
        PasswordPanel.add(changePassBtn, changePassBtnGbc);

        this.add(PasswordPanel);
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
        this.setTitle("Change password");
        this.setResizable(false);
        this.setSize(400, 400);
        this.setVisible(true);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    public void addActionListenerChangePassBtn(ActionListener listener) {
        changePassBtn.addActionListener(listener);
    }

    public String getEnteredOldPass() {
        return currentPassTxtField.getText();
    }

    public String getNewPass() {
        return newPassTxtField.getText();
    }

    public String getReTypePass() {
        return reTypePassTxtField.getText();
    }
}


