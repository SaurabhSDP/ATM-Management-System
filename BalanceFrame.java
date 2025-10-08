import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class BalanceFrame extends JFrame {
    int userId;

    public BalanceFrame(int userId) {
        this.userId = userId;
        setTitle("Check Balance");
        setSize(320, 120);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT balance FROM users WHERE id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                add(new JLabel("Your Current Balance is: ₹" + balance));
            } else {
                add(new JLabel("User not found."));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }
}
