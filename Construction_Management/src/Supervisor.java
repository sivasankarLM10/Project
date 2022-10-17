import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

public class Supervisor extends Login implements workDetails, ActionListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JTextField usernameField, estimateArea;
    JPasswordField passwordField;
    JFrame loginFrame, f, viewWorkFrame;
    JButton submitBtn, updateWorkBtn, refreshBtn, logOutBtn;
    JLabel workIdArea;
    JComboBox<String> paymentStatusComboBox, workStatusComboBox;
    JPanel allAcceptWorkPanel, allAvailableWorkPanel, viewWorkPanel, formaterialLabel, allMaterialPanel;
    JScrollPane allAvailableWorkScrollPane, allAcceptedWorkPane, allMaterialScrollPane;

    Config connection = new Config();
    Connection con = connection.dbConnect();
    work w1 = new work(null);

    public String s_id, site_id, clientName, clientMail, clientPhone, name;

    public Supervisor() {
        login();

    }

    public void loadSupervisorPanel(String s_id) {

        this.s_id = s_id;
        String sql = " select * from officestaff where id=? ";
        ResultSet rs;
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setObject(1, UUID.fromString(s_id));
            rs = prepareStatement.executeQuery();
            while (rs.next()) {
                this.name = rs.getString(2) + " " + rs.getString(3);
            }

        } catch (SQLException e1) {

            e1.printStackTrace();
        }

        f = new JFrame();
        f.getContentPane().invalidate();
        f.setSize((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
        f.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); // full size frame
        f.setLayout(null);

        f.getContentPane().setBackground(Color.decode("#f0f0f0"));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBounds(0, 0, (int) screenSize.getWidth(), 60);
        headerPanel.setBackground(Color.decode("#f0f0f0"));

        ImageIcon logo = new ImageIcon(ConstructionManagment.class.getResource("/Images/construction_logo.png"));
        JLabel logoLabel = new JLabel(logo, SwingConstants.LEFT);

        JLabel titleLabel = new JLabel("<html>Albatross Builders</html>");
        titleLabel.setHorizontalAlignment(SwingConstants.LEADING);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 48));
        JButton loginBtn = new JButton("Login");
        loginBtn.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
        welcomePanel.setBackground(Color.decode("#f0f0f0"));
        welcomePanel.setBounds(0, 60, (int) screenSize.getWidth(), 80);

        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);

        JLabel welcomeLabel = new JLabel("<html> Welcome <font color='#ebc38a'>" + this.name + " </font></html>");
        welcomeLabel.setHorizontalAlignment(JLabel.LEFT);
        welcomeLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));

        refreshBtn = new JButton("Refresh");
        refreshBtn.setBackground(Color.decode("#a39887"));
        refreshBtn.setFocusPainted(false);
        refreshBtn.addActionListener(this);

        logOutBtn = new JButton("Log Out");
        logOutBtn.setBackground(Color.decode("#a39887"));
        logOutBtn.setFocusPainted(false);
        logOutBtn.addActionListener(this);

        JPanel forcurrentWorkLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forcurrentWorkLabel.setBackground(Color.decode("#40392f"));
        forcurrentWorkLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forcurrentWorkLabel.setBounds(0, 140, (int) screenSize.getWidth(), 60);

        JLabel currentWorkLabel = new JLabel("Your Current Work");
        currentWorkLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        currentWorkLabel.setHorizontalAlignment(JLabel.LEFT);
        currentWorkLabel.setForeground(Color.decode("#ebc38a"));

        currentWorkLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));

        forcurrentWorkLabel.add(currentWorkLabel);

        JPanel forPreviousWorkLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forPreviousWorkLabel.setBackground(Color.decode("#40392f"));
        forPreviousWorkLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forPreviousWorkLabel.setBounds(0, 400, (int) screenSize.getWidth(), 60);

        JLabel availableWork = new JLabel("<html>Available  Work</html>");
        availableWork.setHorizontalAlignment(JLabel.LEFT);
        availableWork.setBorder(new EmptyBorder(20, 20, 20, 20));
        availableWork.setFont(new Font("SansSerif", Font.PLAIN, 20));
        availableWork.setForeground(Color.decode("#ebc38a"));
        forPreviousWorkLabel.add(availableWork);

        JPanel dummy = new JPanel(new FlowLayout());
        dummy.setBackground(Color.decode("#f0f0f0"));

        JPanel footer = new JPanel();
        footer.setLayout(new BorderLayout());
        JLabel footerLabel = new JLabel(
                "<html>copyright © 2021 - Albatross Builders <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All Rights Reserved </html>",
                SwingConstants.CENTER);
        footerLabel.setVerticalAlignment(SwingConstants.TOP);
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setForeground(Color.decode("#403f3f"));
        footer.setBackground(Color.decode("#f0f0f0"));
        footer.setBounds(0, 660, (int) screenSize.getWidth(), 40);
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.add(footerLabel, BorderLayout.PAGE_END);

        allAcceptWorkPanel = new JPanel();
        allAcceptWorkPanel.setSize(800, 00);
        allAcceptedWorkPane = new JScrollPane(allAcceptWorkPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        allAcceptWorkPanel.setLayout(new BoxLayout(allAcceptWorkPanel, BoxLayout.Y_AXIS));
        allAcceptedWorkPane.setBounds(0, 200, (int) screenSize.getWidth() - 60, 200);

        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(refreshBtn);
        welcomePanel.add(logOutBtn);

        f.add(headerPanel);
        f.add(welcomePanel);
        f.add(forcurrentWorkLabel);
        viewCurrentWork();
        f.add(forPreviousWorkLabel);
        viewAvailableWork();
        f.add(footer);
        f.setVisible(true);

    }

    public void viewAvailableWork() {
        allAvailableWorkPanel = new JPanel();
        allAvailableWorkPanel.setSize((int) screenSize.getWidth(), 200);
        allAvailableWorkScrollPane = new JScrollPane(allAvailableWorkPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        allAvailableWorkPanel.setLayout(new BoxLayout(allAvailableWorkPanel, BoxLayout.Y_AXIS));
        allAvailableWorkScrollPane.setBounds(0, 460, (int) screenSize.getWidth() - 60, 200);

        String sql3 = "select w_id, s_day, s_month, s_year, e_day, e_month, e_year,fname||' '||lname, siteloc,totalarea, totalestimate, paymentstatus from client natural join work natural join site  where work.o_id is null";
        ResultSet availableWorkDetails;

        try {
            PreparedStatement prepareStatement1 = con.prepareStatement(sql3);
            availableWorkDetails = prepareStatement1.executeQuery();

            while (availableWorkDetails.next()) {

                JPanel currentWorkPanel = new JPanel(new GridLayout(3, 0));

                currentWorkPanel.setBackground(Color.decode("#d1c4b2"));
                currentWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

                JLabel currentWID = new JLabel("Work Id : " + availableWorkDetails.getString(1));
                currentWID.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWID.setBackground(Color.decode("#f29105"));

                JLabel currentWStart = new JLabel("Work Started : " + availableWorkDetails.getString(2) + " / "
                        + availableWorkDetails.getString(3) + "/" + availableWorkDetails.getString(4));
                currentWStart.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWStart.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWStart.setBackground(Color.decode("#f29105"));

                JLabel currentClientName = new JLabel("Client Name : " + availableWorkDetails.getString(8));
                currentClientName.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentClientName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentClientName.setBackground(Color.decode("#f29105"));

                JLabel currentLocation = new JLabel("Location : " + availableWorkDetails.getString(9));
                currentLocation.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentLocation.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentLocation.setBackground(Color.decode("#f29105"));

                JLabel currentArea = new JLabel("Area (Sq): " + availableWorkDetails.getString(10));
                currentArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentArea.setBackground(Color.decode("#f29105"));

                JLabel estimateArea = new JLabel("Estimate :" + availableWorkDetails.getString(11));
                estimateArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                estimateArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                estimateArea.setBackground(Color.decode("#f29105"));

                JLabel paymentstatus = new JLabel("Payment Status: " + availableWorkDetails.getString(12));
                paymentstatus.setBorder(new EmptyBorder(20, 20, 20, 20));
                paymentstatus.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                paymentstatus.setBackground(Color.decode("#f29105"));

                JPanel currentWorkOptions = new JPanel(new GridLayout(2, 0, 5, 5));
                currentWorkOptions.setBackground(Color.decode("#d1c4b2"));
                currentWorkOptions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                currentWorkOptions.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

                JButton acceptWorkBtn = new JButton("Accept");
                acceptWorkBtn.setBackground(Color.decode("#a39887"));
                acceptWorkBtn.setFocusPainted(false);
                acceptWorkBtn.setName("ACW" + availableWorkDetails.getString(1));
                acceptWorkBtn.addActionListener(this);

                currentWorkOptions.add(acceptWorkBtn);

                currentWorkPanel.add(currentWID);
                currentWorkPanel.add(currentWStart);
                currentWorkPanel.add(currentClientName);
                currentWorkPanel.add(currentLocation);
                currentWorkPanel.add(currentArea);
                currentWorkPanel.add(estimateArea);
                currentWorkPanel.add(paymentstatus);

                allAvailableWorkPanel.add(currentWorkPanel);
                allAvailableWorkPanel.add(currentWorkOptions);

            }

        } catch (SQLException e1) {

            e1.printStackTrace();
        }

        f.add(allAvailableWorkScrollPane);
    }

    public void viewCurrentWork() {
        String sql2 = "select w_id,  s_day, s_month, s_year, e_day, e_month, e_year, siteloc,fname||' '||lname, totalarea, totalestimate, paymentstatus, s_id, c_id from client natural join work natural join site where work.o_id=?  and client.c_id=work.c_id";
        ResultSet acceptWorkDetails;
        try {
            PreparedStatement prepareStatement1 = con.prepareStatement(sql2);
            prepareStatement1.setObject(1, UUID.fromString(s_id));
            acceptWorkDetails = prepareStatement1.executeQuery();

            while (acceptWorkDetails.next()) {
                JPanel currentWorkPanel = new JPanel(new GridLayout(3, 0));

                currentWorkPanel.setBackground(Color.decode("#d1c4b2"));
                currentWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

                JLabel currentWID = new JLabel("Work Id : " + acceptWorkDetails.getString(1));
                currentWID.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWID.setBackground(Color.decode("#f29105"));

                JLabel currentWStart = new JLabel("Work Started : " + acceptWorkDetails.getString(2) + " / "
                        + acceptWorkDetails.getString(3) + "/" + acceptWorkDetails.getString(4));
                currentWStart.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWStart.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWStart.setBackground(Color.decode("#f29105"));

                JLabel currentClientName = new JLabel("Client Name : " + acceptWorkDetails.getString(9));
                currentClientName.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentClientName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentClientName.setBackground(Color.decode("#f29105"));

                JLabel currentLocation = new JLabel("Location : " + acceptWorkDetails.getString(8));
                currentLocation.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentLocation.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentLocation.setBackground(Color.decode("#f29105"));

                JLabel currentArea = new JLabel("Area (Sq): " + acceptWorkDetails.getString(10));
                currentArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentArea.setBackground(Color.decode("#f29105"));

                JLabel estimateArea = new JLabel("Estimate :" + acceptWorkDetails.getString(11));
                estimateArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                estimateArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                estimateArea.setBackground(Color.decode("#f29105"));

                JLabel paymentstatus = new JLabel("Payment Status: " + acceptWorkDetails.getString(12));
                paymentstatus.setBorder(new EmptyBorder(20, 20, 20, 20));
                paymentstatus.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                paymentstatus.setBackground(Color.decode("#f29105"));

                JPanel currentWorkOptions = new JPanel(new GridLayout(2, 0, 5, 5));
                currentWorkOptions.setBackground(Color.decode("#d1c4b2"));
                currentWorkOptions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                currentWorkOptions.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

                JButton UpdateBtn = new JButton("Update Work Progress");
                UpdateBtn.setBackground(Color.decode("#a39887"));
                UpdateBtn.setFocusPainted(false);
                UpdateBtn.setName("UWP" + acceptWorkDetails.getString(1));
                UpdateBtn.addActionListener(this);

                JButton materialBtn = new JButton("Add Materials");
                materialBtn.setBackground(Color.decode("#a39887"));
                materialBtn.setFocusPainted(false);
                materialBtn.setName("UMT" + acceptWorkDetails.getString(1) + acceptWorkDetails.getString(13));
                materialBtn.addActionListener(this);

                JButton getReportBtn = new JButton("Get Report");
                getReportBtn.setBackground(Color.decode("#a39887"));
                getReportBtn.setFocusPainted(false);
                getReportBtn.setName(acceptWorkDetails.getString(1));
                getReportBtn.setName("GTR" + acceptWorkDetails.getString(1));
                getReportBtn.addActionListener(this);

                JButton contactClientBtn = new JButton("Contact Client");
                contactClientBtn.setBackground(Color.decode("#a39887"));
                contactClientBtn.setFocusPainted(false);
                contactClientBtn.setName("CTC" + acceptWorkDetails.getString(14));
                contactClientBtn.addActionListener(this);

                currentWorkOptions.add(UpdateBtn);
                currentWorkOptions.add(materialBtn);
                currentWorkOptions.add(contactClientBtn);
                currentWorkOptions.add(getReportBtn);

                currentWorkPanel.add(currentWID);
                currentWorkPanel.add(currentWStart);
                currentWorkPanel.add(currentClientName);
                currentWorkPanel.add(currentLocation);
                currentWorkPanel.add(currentArea);
                currentWorkPanel.add(estimateArea);
                currentWorkPanel.add(paymentstatus);

                allAcceptWorkPanel.add(currentWorkPanel);
                allAcceptWorkPanel.add(currentWorkOptions);

            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        f.add(allAcceptedWorkPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitBtn) {
            String sql = "select * from logindetails where username=? and password =?";
            try {
                PreparedStatement prepareStatement = con.prepareStatement(sql);
                prepareStatement.setString(1, usernameField.getText());
                prepareStatement.setString(2, new String(passwordField.getPassword()));

                ResultSet rs = prepareStatement.executeQuery();
                while (rs.next()) {

                    this.s_id = rs.getString(2);
                    loadSupervisorPanel(this.s_id);
                    loginFrame.dispose();
                }

            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        } else if (e.getSource() == logOutBtn) {
            logout();
        } else if (e.getSource() == refreshBtn) {
            this.f.dispose();
            loadSupervisorPanel(this.s_id);

        }

        else {
            JButton button = (JButton) e.getSource();
            String code = button.getName().substring(0, 3);
            String idneed = button.getName().substring(3);
            if (code.equals("ACW")) {
                System.out.println(code + " " + idneed);
                String sql3 = "update work set o_id=? where w_id=?";
                try {
                    PreparedStatement prepareStatement = con.prepareStatement(sql3);
                    prepareStatement.setObject(1, UUID.fromString(s_id));
                    prepareStatement.setObject(2, UUID.fromString(idneed));
                    int count = prepareStatement.executeUpdate();
                    System.out.println(count + " updated");
                    this.f.dispose();
                    loadSupervisorPanel(s_id);

                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
            } else if (code.equals("UWP")) {
                System.out.println("upadte work progress ");

                w1.updateWorkProgess(idneed);

            } else if (code.equals("UMT")) {
                System.out.println("used Materials  ");

                new Materials(idneed, idneed.substring(36));

            } else if (code.equals("CTC")) {
                System.out.println("Contact client");
                contactClient(idneed);

            } else if (code.equals("GTR")) {
                System.out.println("GET report ");
                viewWork(idneed);

            }

        }

    }

    public void contactClient(String clientID) {
        String sql = " select fname, lname from client where c_id=? ";
        String sql1 = " select phone, mailid from contact where id=?";
        ResultSet rs, rs1;
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setObject(1, UUID.fromString(clientID));
            rs = prepareStatement.executeQuery();

            PreparedStatement prepareStatement1 = con.prepareStatement(sql1);
            prepareStatement1.setObject(1, UUID.fromString(clientID));
            rs1 = prepareStatement1.executeQuery();

            while (rs.next()) {
                this.clientName = rs.getString(1) + rs.getString(2);

            }
            while (rs1.next()) {
                this.clientMail = rs1.getString(2);
                this.clientPhone = rs1.getString(1);
            }

        } catch (SQLException e1) {

            e1.printStackTrace();
        }
        JFrame f = new JFrame();
        f.setVisible(true);
        f.setSize(700, 260);
        f.setLayout(null);
        f.setLocation((int) screenSize.getWidth() / 3, (int) screenSize.getHeight() / 3);
        f.getContentPane().setBackground(Color.decode("#d1c4b2"));

        JPanel forheaderLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forheaderLabel.setBackground(Color.decode("#40392f"));
        forheaderLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forheaderLabel.setBounds(0, 0, 700, 80);

        JLabel headerLabel = new JLabel("Client Contact Details");
        headerLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        headerLabel.setForeground(Color.decode("#ebc38a"));
        headerLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));

        forheaderLabel.add(headerLabel);

        JLabel Sname = new JLabel("Client Name:         " + this.clientName);
        Sname.setForeground(Color.decode("#523b2b"));
        Sname.setFont(new Font("Serif", Font.BOLD, 15));

        JLabel email = new JLabel("Client Email:          " + this.clientMail);
        email.setForeground(Color.decode("#523b2b"));
        email.setFont(new Font("Serif", Font.BOLD, 15));

        JLabel phNum = new JLabel("Client Number:      " + this.clientPhone);
        phNum.setForeground(Color.decode("#523b2b"));
        phNum.setFont(new Font("Serif", Font.BOLD, 15));

        JPanel snamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        snamePanel.add(Sname);
        Sname.setBounds(30, 80, 700, 30);

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(email);
        email.setBounds(30, 120, 700, 30);

        JPanel phNumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phNumPanel.add(phNum);
        phNum.setBounds(30, 160, 700, 30);

        f.add(forheaderLabel);
        f.add(Sname);
        f.add(email);
        f.add(phNum);

    }

    @Override
    public void login() {

        System.out.print("Login to Supervisor");

        loginFrame = new JFrame();
        loginFrame.setLayout(new GridLayout(4, 0));
        loginFrame.setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() + 250) / 4);
        loginFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

        JPanel titlePanel = new JPanel();
        JLabel supervisorLabel = new JLabel("Supervisor Login");
        supervisorLabel.setFont(new Font("SansSerif", Font.PLAIN, 38));
        titlePanel.add(supervisorLabel);
        JLabel userLabel = new JLabel();
        userLabel.setText("<html>&nbsp;&nbsp;&nbsp;Username</html>");
        userLabel.setBackground(Color.DARK_GRAY);

        usernameField = new JTextField(30);

        JLabel passLabel = new JLabel();
        passLabel.setText("<html>&nbsp;&nbsp;&nbsp;Password</html>");

        passwordField = new JPasswordField(30);

        submitBtn = new JButton("<html>&nbsp;Submit</html>");
        submitBtn.setBackground(Color.decode("#40392f"));
        submitBtn.setForeground(Color.decode("#ebc38a"));
        submitBtn.setFocusPainted(false);

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(userLabel);
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passLabel);
        passwordPanel.add(passwordField);

        JPanel submitPanel = new JPanel();
        submitPanel.setAlignmentY(SwingConstants.BOTTOM);
        submitPanel.add(submitBtn);

        loginFrame.add(titlePanel);
        loginFrame.add(usernamePanel);
        loginFrame.add(passwordPanel);
        loginFrame.add(submitPanel);
        loginFrame.setVisible(true);

        submitBtn.addActionListener(this);

    }

    @Override
    public void logout() {

        f.dispose();
    }

    @Override
    public void viewWork(String w_id) {

        viewWorkFrame = new JFrame();
        viewWorkFrame.setLayout(null);
        viewWorkFrame.setSize((int) screenSize.getWidth(), 540);
        viewWorkFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.decode("#40392f"));
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        titlePanel.setBounds(0, 0, (int) screenSize.getWidth(), 60);

        JLabel viewWorkLabel = new JLabel("View Work");
        viewWorkLabel.setBorder(new EmptyBorder(20, 20, 10, 10));
        viewWorkLabel.setHorizontalAlignment(JLabel.LEFT);
        viewWorkLabel.setForeground(Color.decode("#ebc38a"));

        titlePanel.add(viewWorkLabel);

        Border blackline = BorderFactory.createLineBorder(Color.black);

        String sql3 = "select w_id, s_day, s_month, s_year, e_day, e_month, e_year,fname||' '||lname, siteloc,totalarea, totalestimate, paymentstatus,s_id from client natural join work natural join site  where work.w_id=?";
        String sql4 = "select  m_id, m_name, m_cost, m_size, m_company, m_quantity from materials natural join materialspec where materials.site_id =?  ";

        ResultSet workDetails, materialDetails;
        try {
            PreparedStatement prepareStatement1 = con.prepareStatement(sql3);
            prepareStatement1.setObject(1, UUID.fromString(w_id));
            workDetails = prepareStatement1.executeQuery();

            while (workDetails.next()) {
                this.site_id = workDetails.getString(13);
                viewWorkPanel = new JPanel(new GridLayout(3, 0));
                viewWorkPanel.setBounds(0, 60, (int) screenSize.getWidth(), 200);

                viewWorkPanel.setBackground(Color.decode("#d1c4b2"));
                viewWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

                JLabel currentWID = new JLabel("Work Id : " + workDetails.getString(1));
                currentWID.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWID.setBackground(Color.decode("#f29105"));

                JLabel currentWStart = new JLabel("Work Started : " + workDetails.getString(2) + " / "
                        + workDetails.getString(3) + "/" + workDetails.getString(4));
                currentWStart.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWStart.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWStart.setBackground(Color.decode("#f29105"));

                JLabel currentClientName = new JLabel("Client Name : " + workDetails.getString(8));
                currentClientName.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentClientName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentClientName.setBackground(Color.decode("#f29105"));

                JLabel currentLocation = new JLabel("Location : " + workDetails.getString(9));
                currentLocation.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentLocation.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentLocation.setBackground(Color.decode("#f29105"));

                JLabel currentArea = new JLabel("Area (Sq): " + workDetails.getString(10));
                currentArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentArea.setBackground(Color.decode("#f29105"));

                JLabel estimateArea = new JLabel("Estimate :" + workDetails.getString(11));
                estimateArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                estimateArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                estimateArea.setBackground(Color.decode("#f29105"));

                JLabel paymentstatus = new JLabel("Payment Status: " + workDetails.getString(12));
                paymentstatus.setBorder(new EmptyBorder(20, 20, 20, 20));
                paymentstatus.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                paymentstatus.setBackground(Color.decode("#f29105"));

                JPanel currentWorkOptions = new JPanel(new GridLayout(2, 0, 5, 5));
                currentWorkOptions.setBackground(Color.decode("#d1c4b2"));
                currentWorkOptions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                currentWorkOptions.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

                formaterialLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                formaterialLabel.setBackground(Color.decode("#40392f"));
                formaterialLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
                formaterialLabel.setBounds(0, 260, (int) screenSize.getWidth(), 60);

                JLabel usedMaterialsLabel = new JLabel("Used Materials");
                usedMaterialsLabel.setBorder(new EmptyBorder(20, 20, 10, 10));
                usedMaterialsLabel.setHorizontalAlignment(JLabel.LEFT);
                usedMaterialsLabel.setForeground(Color.decode("#ebc38a"));

                formaterialLabel.add(usedMaterialsLabel);

                allMaterialPanel = new JPanel();
                allMaterialScrollPane = new JScrollPane(allMaterialPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                allMaterialPanel.setLayout(new BoxLayout(allMaterialPanel, BoxLayout.Y_AXIS));
                allMaterialScrollPane.setBounds(0, 320, (int) screenSize.getWidth() - 60, 200);

                PreparedStatement prepareStatement = con.prepareStatement(sql4);
                prepareStatement.setObject(1, UUID.fromString(this.site_id));
                System.out.println("site ID: " + this.site_id);
                materialDetails = prepareStatement.executeQuery();
                while (materialDetails.next()) {
                    JPanel materialPanel = new JPanel(new GridLayout(2, 0));
                    materialPanel.setBackground(Color.decode("#d1c4b2"));
                    materialPanel.setBorder(blackline);
                    JLabel matID = new JLabel("Material Id : " + materialDetails.getString(1));
                    matID.setBorder(new EmptyBorder(20, 20, 20, 20));
                    matID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                    matID.setBackground(Color.decode("#f29105"));

                    JLabel mName = new JLabel("Material Name : " + materialDetails.getString(2));
                    mName.setBorder(new EmptyBorder(20, 20, 20, 20));
                    mName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                    mName.setBackground(Color.decode("#f29105"));

                    JLabel mCost = new JLabel("Cost : " + materialDetails.getString(3));
                    mCost.setBorder(new EmptyBorder(20, 20, 20, 20));
                    mCost.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                    mCost.setBackground(Color.decode("#f29105"));

                    JLabel mSize = new JLabel("Size : " + materialDetails.getString(4));
                    mSize.setBorder(new EmptyBorder(20, 20, 20, 20));
                    mSize.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                    mSize.setBackground(Color.decode("#f29105"));

                    JLabel mCompany = new JLabel("Company : " + materialDetails.getString(5));
                    mCompany.setBorder(new EmptyBorder(20, 20, 20, 20));
                    mCompany.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                    mCompany.setBackground(Color.decode("#f29105"));

                    JLabel mQty = new JLabel("Quantity : " + materialDetails.getString(6));
                    mQty.setBorder(new EmptyBorder(20, 20, 20, 20));
                    mQty.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                    mQty.setBackground(Color.decode("#f29105"));

                    materialPanel.add(matID);
                    materialPanel.add(mName);
                    materialPanel.add(mCost);
                    materialPanel.add(mSize);
                    materialPanel.add(mCompany);
                    materialPanel.add(mQty);

                    allMaterialPanel.add(materialPanel);
                }

                viewWorkPanel.add(currentWID);
                viewWorkPanel.add(currentWStart);
                viewWorkPanel.add(currentClientName);
                viewWorkPanel.add(currentLocation);
                viewWorkPanel.add(currentArea);
                viewWorkPanel.add(estimateArea);
                viewWorkPanel.add(paymentstatus);

            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        viewWorkFrame.add(titlePanel);
        viewWorkFrame.add(viewWorkPanel);
        viewWorkFrame.add(formaterialLabel);
        viewWorkFrame.add(allMaterialScrollPane);
        viewWorkFrame.setVisible(true);
    }

}