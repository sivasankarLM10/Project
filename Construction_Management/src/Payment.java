import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

/**
 * 
 */
public class Payment implements ActionListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JFrame paymentFrame;
    JLabel amountLabel;
    JButton Make_PaymentBtn;

    Config connection = new Config();
    Connection con = connection.dbConnect();

    public int estimate, count;
    public String w_id;

    public Payment(String w_id) {
        this.w_id = w_id;
        if (checkWorkFinish())
            makePayment(w_id);
        else if (alreadyPaymentDone()) {

            JFrame f = new JFrame();
            f.setVisible(true);
            f.setSize(400, 250);
            f.setLayout(new GridLayout(3, 0));
            f.setLocation((int) screenSize.getWidth() / 3, (int) screenSize.getHeight() / 3);
            f.getContentPane().setBackground(Color.decode("#f0f0f0"));
            f.add(new JLabel("Payment Already Done"));

        } else {
            workPendingAlertPanel();
        }

    }

    public Boolean alreadyPaymentDone() {
        String sql = "select worktype from work where paymentstatus='true' or paymentstatus='True' and w_id=?";
        ResultSet rs;

        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setObject(1, UUID.fromString(w_id));
            rs = prepareStatement.executeQuery();

            this.count = rs.getFetchSize();
            if (rs.next())
                this.count = 1;

        } catch (SQLException e1) {

            e1.printStackTrace();
        }
        if (this.count == 1)
            return true;
        else
            return false;
    }

    public boolean checkWorkFinish() {

        String sql = "select worktype from work where e_day is not null and w_id=?";
        ResultSet rs;

        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setObject(1, UUID.fromString(w_id));
            rs = prepareStatement.executeQuery();

            this.count = rs.getFetchSize();
            if (rs.next())
                this.count = 1;

        } catch (SQLException e1) {

            e1.printStackTrace();
        }
        if (this.count == 1)
            return true;
        else
            return false;
    }

    public void workPendingAlertPanel() {
        JFrame f = new JFrame();
        f.setVisible(true);
        f.setSize(400, 150);
        f.setLocation((int) screenSize.getWidth() / 3, (int) screenSize.getHeight() / 3);
        f.setLayout(new GridLayout(1, 0));
        f.getContentPane().setBackground(Color.decode("#f0f0f0"));
        JLabel alert = new JLabel("       Cant make Payment,Your  work is  Pending");
        f.add(alert);
        f.setVisible(true);

    }

    public void makePayment(String w_id) {
        paymentFrame = new JFrame();
        paymentFrame.setVisible(true);
        paymentFrame.setSize((int) screenSize.getWidth() / 2, 250);
        paymentFrame.setLayout(new GridLayout(3, 0));
        paymentFrame.setLocation((int) screenSize.getWidth() / 3, (int) screenSize.getHeight() / 3);
        paymentFrame.getContentPane().setBackground(Color.decode("#d1c4b2"));

        JPanel forheaderLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forheaderLabel.setBackground(Color.decode("#40392f"));
        forheaderLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forheaderLabel.setBounds(0, 0, (int) screenSize.getWidth() / 2, 80);

        JLabel headerLabel = new JLabel("Payment Details");
        headerLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        headerLabel.setForeground(Color.decode("#ebc38a"));
        headerLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));

        forheaderLabel.add(headerLabel);

        String sql = "select totalestimate from work where w_id =?";
        ResultSet rs;
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setObject(1, UUID.fromString(w_id));
            rs = prepareStatement.executeQuery();

            while (rs.next()) {
                this.estimate = rs.getInt(1);
            }

        } catch (SQLException e1) {

            e1.printStackTrace();
        }

        JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel amountLabel = new JLabel("Estimate (in Rs):" + this.estimate);
        amountLabel.setBackground(Color.DARK_GRAY);
        amountPanel.add(amountLabel);

        Make_PaymentBtn = new JButton("Pay");
        Make_PaymentBtn.setFont(new Font("Serif", Font.BOLD, 15));
        Make_PaymentBtn.setBounds(120, 80, 100, 30);
        Make_PaymentBtn.addActionListener(this);
        Make_PaymentBtn.setBackground(Color.decode("#40392f"));
        Make_PaymentBtn.setForeground(Color.decode("#ebc38a"));
        Make_PaymentBtn.setFocusPainted(false);

        JPanel submitPanel = new JPanel();
        submitPanel.setAlignmentY(SwingConstants.BOTTOM);
        submitPanel.add(Make_PaymentBtn);

        paymentFrame.add(forheaderLabel);
        paymentFrame.add(amountPanel);
        paymentFrame.add(submitPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Make_PaymentBtn) {

            String sql1 = "insert into payment values(?,?,?); update work set paymentstatus='true' ";
            try {
                UUID idOne = UUID.randomUUID();

                PreparedStatement prepareStatement = con.prepareStatement(sql1);
                prepareStatement.setObject(1, idOne);
                prepareStatement.setObject(2, UUID.fromString(this.w_id));
                prepareStatement.setInt(3, this.estimate);

                int updatedCount = prepareStatement.executeUpdate();
                System.out.println(updatedCount + "  updated ");
                if (updatedCount == 1) {
                    paymentFrame.dispose();
                    JFrame f = new JFrame();
                    f.setVisible(true);
                    f.setSize(400, 250);
                    f.setLayout(new GridLayout(3, 0));
                    f.setLocation((int) screenSize.getWidth() / 3, (int) screenSize.getHeight() / 3);
                    f.getContentPane().setBackground(Color.decode("#f0f0f0"));
                    f.add(new JLabel("Payment SuccessFull"));

                } else {
                    paymentFrame.dispose();
                    JFrame f = new JFrame();
                    f.setVisible(true);
                    f.setSize(400, 250);
                    f.setLayout(new GridLayout(3, 0));
                    f.setLocation((int) screenSize.getWidth() / 3, (int) screenSize.getHeight() / 3);
                    f.getContentPane().setBackground(Color.decode("#f0f0f0"));
                    f.add(new JLabel("Sorry, Payment UnSuccessFull"));

                }

            } catch (SQLException e1) {

                e1.printStackTrace();
            }

        }
    }

}