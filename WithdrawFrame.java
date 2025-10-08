import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class WithdrawFrame extends JFrame implements ActionListener {
    int userId;
    JTextField amountField;
    JButton withdrawBtn;

    public WithdrawFrame(int userId) {
        this.userId = userId;

        setTitle("Withdraw Money");
        setSize(320, 150);
        setLayout(new GridLayout(2, 2, 10, 10));

        add(new JLabel("Enter Amount:"));
        amountField = new JTextField();
        add(amountField);

        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.addActionListener(this);
        add(new JLabel());
        add(withdrawBtn);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double amt = Double.parseDouble(amountField.getText());
            if (amt <= 0) {
                JOptionPane.showMessageDialog(this, "Enter a positive amount.");
                return;
            }
            Connection con = DBConnection.getConnection();

            PreparedStatement psCheck = con.prepareStatement("SELECT balance FROM users WHERE id=?");
            psCheck.setInt(1, userId);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (amt > balance) {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance!");
                    return;
                }

                PreparedStatement ps1 = con.prepareStatement("UPDATE users SET balance = balance - ? WHERE id=?");
                ps1.setDouble(1, amt);
                ps1.setInt(2, userId);
                ps1.executeUpdate();

                PreparedStatement ps2 = con.prepareStatement("INSERT INTO transactions(user_id, type, amount) VALUES (?, ?, ?)");
                ps2.setInt(1, userId);
                ps2.setString(2, "Withdraw");
                ps2.setDouble(3, amt);
                ps2.executeUpdate();

                JOptionPane.showMessageDialog(this, "₹" + amt + " Withdrawn Successfully!");
                dispose();
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Enter a valid number.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
