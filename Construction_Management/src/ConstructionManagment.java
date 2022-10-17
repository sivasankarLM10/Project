import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import java.awt.*;

public class ConstructionManagment implements ActionListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JFrame f = new JFrame();// creating instance of JFrame
    JButton clientLoginBtn, AdminLoginBtn, supervisorLoginBtn;

    public static void main(String[] args) {
        UUID idOne = UUID.randomUUID();
        System.out.println("Welcome to Construction Management " + idOne);
        new ConstructionManagment();
        Config n1 = new Config();
        n1.establishDb();
    }

    public ConstructionManagment() {

        f.setSize((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
        f.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); // full size frame
        f.getContentPane().setBackground(Color.decode("#f0f0f0"));
        f.setLayout(new GridLayout(4, 0));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight() / 4);
        headerPanel.setBackground(Color.decode("#f0f0f0"));

        ImageIcon logo = new ImageIcon(ConstructionManagment.class.getResource("/Images/construction_logo.png"));
        JLabel logoLabel = new JLabel(logo, SwingConstants.LEFT);

        JLabel titleLabel = new JLabel("Albatross Builders");
        titleLabel.setHorizontalAlignment(SwingConstants.LEADING);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 48));

        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight() / 4);
        JLabel headingLabel = new JLabel(
                "<html>Welcome To <font color='#d1c4b2'> Albatross Builders <br><center>You Want It We Build It</font> </center><center><font color='#ebc38a'> Login As</font></font></center></html>");
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setLocation(0, 0);
        headingLabel.setForeground(Color.decode("#ebc38a"));
        headingLabel.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight() / 4);
        headingLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        contentPanel.add(headingLabel);

        JPanel loginThroughPanel = new JPanel(new BorderLayout());
        loginThroughPanel.setLayout(null);
        loginThroughPanel.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight() / 4);
        loginThroughPanel.setAlignmentX(SwingConstants.CENTER);
        loginThroughPanel.setAlignmentY(SwingConstants.BOTTOM);
        clientLoginBtn = new JButton("Client ");
        AdminLoginBtn = new JButton("Admin");
        supervisorLoginBtn = new JButton("Supervisor");
        clientLoginBtn.setBackground(Color.decode("#40392f"));
        clientLoginBtn.setForeground(Color.decode("#ebc38a"));
        clientLoginBtn.setFocusPainted(false);
        clientLoginBtn.addActionListener(this);
        AdminLoginBtn.setBackground(Color.decode("#40392f"));
        AdminLoginBtn.setForeground(Color.decode("#ebc38a"));
        AdminLoginBtn.setFocusPainted(false);
        AdminLoginBtn.addActionListener(this);
        supervisorLoginBtn.setBackground(Color.decode("#40392f"));
        supervisorLoginBtn.setForeground(Color.decode("#ebc38a"));
        supervisorLoginBtn.setFocusPainted(false);
        supervisorLoginBtn.addActionListener(this);

        clientLoginBtn.setBounds(30, 55, 65 + ((int) screenSize.getWidth()) / 4, 50);
        AdminLoginBtn.setBounds((int) screenSize.getWidth() / 3, 55, 7 + (int) screenSize.getWidth() / 3, 50);
        supervisorLoginBtn.setBounds(28 + (2 * (int) screenSize.getWidth() / 3), 55, ((int) screenSize.getWidth()) / 4,
                50);

        loginThroughPanel.add(clientLoginBtn);
        loginThroughPanel.add(AdminLoginBtn);
        loginThroughPanel.add(supervisorLoginBtn);

        JPanel footer = new JPanel();
        footer.setLayout(new BorderLayout());
        JLabel footerLabel = new JLabel(
                "<html>copyright © 2021 - Albatross Builders <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All Rights Reserved </html>",
                SwingConstants.CENTER);
        footerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        footerLabel.setBounds(0, (int) screenSize.getHeight(), (int) screenSize.getWidth(),
                (int) screenSize.getHeight() / 4);
        footerLabel.setForeground(Color.decode("#403f3f"));
        footer.setBackground(Color.decode("#f0f0f0"));
        footerLabel.setBounds(0, 0, (int) screenSize.getWidth(), 100);
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.add(footerLabel, BorderLayout.PAGE_END);

        f.add(headerPanel);
        f.add(contentPanel);
        f.add(loginThroughPanel);
        f.add(footer);
        f.setVisible(true);// making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// terminate program when closes frame

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == AdminLoginBtn) {
            new Admin();
        } else if (e.getSource() == supervisorLoginBtn) {
            new Supervisor();

        } else if (e.getSource() == clientLoginBtn) {
            new Client();
        }

    }

}
