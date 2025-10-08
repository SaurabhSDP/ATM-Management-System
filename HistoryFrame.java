import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class HistoryFrame extends JFrame {
    int userId;

    public HistoryFrame(int userId) {
        this.userId = userId;
        setTitle("Transaction History");
        setSize(500, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        String[] columns = {"Type", "Amount", "Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT type, amount, date FROM transactions WHERE user_id=? ORDER BY date DESC");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("type"),
                    rs.getDouble("amount"),
                    rs.getTimestamp("date")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }
}
