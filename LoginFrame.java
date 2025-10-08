import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame implements ActionListener {
    JTextField cardField;
    JPasswordField pinField;
    JButton loginBtn;

    public LoginFrame() {
        setTitle("ATM Login");
        setSize(320, 200);
        setLayout(new GridLayout(3, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel("Card Number:"));
        cardField = new JTextField();
        add(cardField);

        add(new JLabel("PIN:"));
        pinField = new JPasswordField();
        add(pinField);

        loginBtn = new JButton("Login");
        loginBtn.addActionListener(this);
        add(new JLabel());
        add(loginBtn);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        String card = cardField.getText();
        String pin = new String(pinField.getPassword());
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE card_number=? AND pin=?");
            ps.setString(1, card);
            ps.setString(2, pin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new DashboardFrame(userId);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
