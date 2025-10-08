import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardFrame extends JFrame implements ActionListener {
    int userId;
    JButton depositBtn, withdrawBtn, checkBalanceBtn, historyBtn, logoutBtn;

    public DashboardFrame(int userId) {
        this.userId = userId;

        setTitle("ATM Dashboard");
        setSize(400, 300);
        setLayout(new GridLayout(5, 1, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        depositBtn = new JButton("Deposit Money");
        withdrawBtn = new JButton("Withdraw Money");
        checkBalanceBtn = new JButton("Check Balance");
        historyBtn = new JButton("Transaction History");
        logoutBtn = new JButton("Logout");

        add(depositBtn);
        add(withdrawBtn);
        add(checkBalanceBtn);
        add(historyBtn);
        add(logoutBtn);

        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        checkBalanceBtn.addActionListener(this);
        historyBtn.addActionListener(this);
        logoutBtn.addActionListener(this);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositBtn) {
            new DepositFrame(userId);
        } else if (e.getSource() == withdrawBtn) {
            new WithdrawFrame(userId);
        } else if (e.getSource() == checkBalanceBtn) {
            new BalanceFrame(userId);
        } else if (e.getSource() == historyBtn) {
            new HistoryFrame(userId);
        } else if (e.getSource() == logoutBtn) {
            JOptionPane.showMessageDialog(this, "Logged out successfully!");
            dispose();
            new LoginFrame();
        }
    }
}
