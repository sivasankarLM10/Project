
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.util.UUID;

public class Client extends Login implements workDetails, ActionListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JFrame f, viewWorkFrame, createFrame, loginFrame;
    JPanel currentWorkPanel, viewWorkPanel, allWorkPanel, allPreviousWorkPanel,
            currentWorkOptions, formaterialLabel, allMaterialPanel;
    JTextField usernameField, loginUsernameField, fName, lName, mailIdField, phoneField;
    JScrollPane allWorkScrollPane, allPreviousWorkScrollPane, allMaterialScrollPane;
    JPasswordField passwordField, loginPasswordField, cnfPasswordField;
    JButton loginBtn, loginSubmitBtn, accCreateClnBtn, goTologinBtn, createClnBtn,
            reqAworkBtn, refreshBtn, logOutBtn, paymentBtn;

    work w1;
    Config connection = new Config();
    Connection con = connection.dbConnect();

    public String c_id, site_id, supervisorName, supervisorMail, supervisorPhone, name;

    public Client() {
        login();
    }

    public void accountCreate() {
        createFrame = new JFrame();
        createFrame.setLayout(new GridLayout(5, 0));
        createFrame.setSize((int) (screenSize.getWidth()), (int) (screenSize.getHeight() / 2));
        createFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

        JPanel forheaderLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forheaderLabel.setBackground(Color.decode("#40392f"));
        forheaderLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forheaderLabel.setBounds(0, 0, (int) screenSize.getWidth(), 80);

        JLabel headerLabel = new JLabel("Client Account Create");
        headerLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        headerLabel.setForeground(Color.decode("#ebc38a"));
        headerLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));

        forheaderLabel.add(headerLabel);
        JLabel userLabel = new JLabel();
        userLabel.setText("<html>Username</html>"); // set label value for textField1
        userLabel.setBackground(Color.DARK_GRAY);

        JLabel fnameLabel = new JLabel();
        fnameLabel.setText("<html>First Name</html>");
        fnameLabel.setBackground(Color.DARK_GRAY);

        JLabel mailLabel = new JLabel();
        mailLabel.setText("<html>Mail Id</html>");
        mailLabel.setBackground(Color.DARK_GRAY);

        JLabel lnameLabel = new JLabel();
        lnameLabel.setText("<html>Last Name</html>");
        lnameLabel.setBackground(Color.DARK_GRAY);
        // create text field to get username from the user

        fName = new JTextField(15); // set length of the text
        lName = new JTextField(15);
        mailIdField = new JTextField(35);
        phoneField = new JTextField(20);
        usernameField = new JTextField(25);

        // create label for password
        JLabel passLabel = new JLabel();
        passLabel.setText("<html>Password</html>"); // set label value for textField2

        JLabel phoneLabel = new JLabel();
        phoneLabel.setText("<html>Phone No: </html>");

        JLabel cnfPassLabel = new JLabel();
        cnfPassLabel.setText("<html>Confirm Password</html>");
        // create text field to get password from the user

        passwordField = new JPasswordField(15);
        cnfPasswordField = new JPasswordField(15);

        goTologinBtn = new JButton("<html>&nbsp;Already have an Accunt, Login?</html>");
        goTologinBtn.setBackground(Color.decode("#40392f"));
        goTologinBtn.setForeground(Color.decode("#ebc38a"));
        goTologinBtn.setFocusPainted(false);

        accCreateClnBtn = new JButton("<html>&nbsp;Create Account</html>");
        accCreateClnBtn.setBackground(Color.decode("#40392f"));
        accCreateClnBtn.setForeground(Color.decode("#ebc38a"));
        accCreateClnBtn.setFocusPainted(false);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(fnameLabel);
        namePanel.add(fName);
        namePanel.add(lnameLabel);
        namePanel.add(lName);

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernamePanel.add(userLabel);
        usernamePanel.add(usernameField);
        usernamePanel.add(mailLabel);
        usernamePanel.add(mailIdField);
        usernamePanel.add(phoneLabel);
        usernamePanel.add(phoneField);
        // usernamePanel.setAlignmentX(alignmentX);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(passLabel);
        passwordPanel.add(passwordField);
        passwordPanel.add(cnfPassLabel);
        passwordPanel.add(cnfPasswordField);

        JPanel submitPanel = new JPanel();
        submitPanel.setAlignmentY(SwingConstants.BOTTOM);
        submitPanel.add(accCreateClnBtn);
        submitPanel.add(goTologinBtn);

        createFrame.add(forheaderLabel);
        createFrame.add(namePanel);

        createFrame.add(usernamePanel);
        createFrame.add(passwordPanel);
        createFrame.add(submitPanel);
        createFrame.setVisible(true);

        goTologinBtn.addActionListener(this);
        accCreateClnBtn.addActionListener(this);
    }

    @Override
    public void login() {

        System.out.print("Login as Client");

        loginFrame = new JFrame();
        loginFrame.setLayout(new GridLayout(4, 0));
        loginFrame.setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() + 250) / 4);
        loginFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

        JPanel titlePanel = new JPanel();
        JLabel supervisorLabel = new JLabel("Client Login");
        supervisorLabel.setFont(new Font("SansSerif", Font.PLAIN, 38));
        titlePanel.add(supervisorLabel);
        JLabel userLabel = new JLabel();
        userLabel.setText("<html>&nbsp;&nbsp;&nbsp;Username</html>");
        userLabel.setBackground(Color.DARK_GRAY);

        loginUsernameField = new JTextField(30);

        JLabel passLabel = new JLabel();
        passLabel.setText("<html>&nbsp;&nbsp;&nbsp;Password</html>");

        loginPasswordField = new JPasswordField(30);

        loginSubmitBtn = new JButton("<html>&nbsp;Submit</html>");
        loginSubmitBtn.setBackground(Color.decode("#40392f"));
        loginSubmitBtn.setForeground(Color.decode("#ebc38a"));
        loginSubmitBtn.setFocusPainted(false);

        createClnBtn = new JButton("<html>&nbsp;Dont have Account?</html>");
        createClnBtn.setBackground(Color.decode("#40392f"));
        createClnBtn.setForeground(Color.decode("#ebc38a"));
        createClnBtn.setFocusPainted(false);

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(userLabel);
        usernamePanel.add(loginUsernameField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passLabel);
        passwordPanel.add(loginPasswordField);

        JPanel submitPanel = new JPanel();
        submitPanel.setAlignmentY(SwingConstants.BOTTOM);
        submitPanel.add(loginSubmitBtn);
        submitPanel.add(createClnBtn);

        loginFrame.add(titlePanel);
        loginFrame.add(usernamePanel);
        loginFrame.add(passwordPanel);
        loginFrame.add(submitPanel);
        loginFrame.setVisible(true);

        loginSubmitBtn.addActionListener(this);
        createClnBtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginSubmitBtn) {
            String sql = "select * from logindetails where username=? and password =?";
            try {
                PreparedStatement prepareStatement = con.prepareStatement(sql);
                prepareStatement.setString(1, loginUsernameField.getText());
                prepareStatement.setString(2, new String(loginPasswordField.getPassword()));

                ResultSet rs = prepareStatement.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getObject(1) + " " + rs.getString(2));
                    this.c_id = rs.getString(1);
                    loadClientPanel(c_id);

                    loginFrame.dispose();

                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } else if (e.getSource() == createClnBtn) {
            System.out.println("create an client account");
            loginFrame.dispose();
            accountCreate();
        } else if (e.getSource() == accCreateClnBtn) {
            System.out.println("Please create an account for client");
            createFrame.dispose();
            con = connection.dbConnect();

            String sql = "insert into client values(?,?,?); insert into logindetails(id, username, password) values (?,?,?); insert into contact(id, mailid, phone ) values(?, ?, ?)";
            try {
                UUID idOne = UUID.randomUUID();

                PreparedStatement prepareStatement = con.prepareStatement(sql);
                prepareStatement.setObject(1, idOne);
                prepareStatement.setString(2, fName.getText());
                prepareStatement.setString(3, lName.getText());

                prepareStatement.setObject(4, idOne);
                prepareStatement.setString(5, usernameField.getText());
                prepareStatement.setString(6, new String(passwordField.getPassword()));

                prepareStatement.setObject(7, idOne);
                prepareStatement.setString(8, mailIdField.getText());
                prepareStatement.setString(9, phoneField.getText());

                int updatedCount = prepareStatement.executeUpdate();
                System.out.println(updatedCount + "  updated ");

            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        } else if (e.getSource() == goTologinBtn) {
            createFrame.dispose();
            login();
        } else if (e.getSource() == reqAworkBtn) {
            System.out.println("Req a work ");
            w1 = new work(c_id);
        } else if (e.getSource() == refreshBtn) {
            System.out.println("Referesh");
            this.f.dispose();
            loadClientPanel(this.c_id);

        } else if (e.getSource() == logOutBtn) {
            System.out.println("Log Out Man");
            logout();
        } else {
            JButton button = (JButton) e.getSource();
            String code = button.getName().substring(0, 3);
            String idneed = button.getName().substring(3);
            if (code.equals("ACW")) {

            } else if (code.equals("UMT")) {
                new Materials(idneed);

            } else if (code.equals("GTR")) {
                System.out.println("here  ...");
                viewWork(idneed);
            } else if (code.equals("PAY")) {
                new Payment(idneed);
            } else if (code.equals("CTS")) {
                contactSupervisor(idneed);
            }
        }

    }

    public void contactSupervisor(String officeStaff_id) {
        String sql = " select fname, lname from officestaff where id=? ";
        String sql1 = " select phone, mailid from contact where oid=?";
        ResultSet rs, rs1;
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setObject(1, UUID.fromString(officeStaff_id));
            rs = prepareStatement.executeQuery();

            PreparedStatement prepareStatement1 = con.prepareStatement(sql1);
            prepareStatement1.setObject(1, UUID.fromString(officeStaff_id));
            rs1 = prepareStatement1.executeQuery();

            while (rs.next()) {
                this.supervisorName = rs.getString(1) + rs.getString(2);

            }
            while (rs1.next()) {
                this.supervisorMail = rs1.getString(2);
                this.supervisorPhone = rs1.getString(1);
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

        JLabel headerLabel = new JLabel("Supervisor Contact Details");
        headerLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        headerLabel.setForeground(Color.decode("#ebc38a"));
        headerLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));

        forheaderLabel.add(headerLabel);

        JLabel Sname = new JLabel("Supervisor Name:         " + this.supervisorName);
        Sname.setForeground(Color.decode("#523b2b"));
        Sname.setFont(new Font("Serif", Font.BOLD, 15));

        JLabel email = new JLabel("Supervisor Email:          " + this.supervisorMail);
        email.setForeground(Color.decode("#523b2b"));
        email.setFont(new Font("Serif", Font.BOLD, 15));

        JLabel phNum = new JLabel("Supervisor Number:      " + this.supervisorPhone);
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

    public void loadClientPanel(String c_id) {

        String sql = " select * from client where c_id=? ";
        ResultSet rs;

        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setObject(1, UUID.fromString(c_id));
            rs = prepareStatement.executeQuery();
            while (rs.next()) {
                this.name = rs.getString(2) + rs.getString(3);
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

        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
        welcomePanel.setBackground(Color.decode("#f0f0f0"));
        welcomePanel.setBounds(0, 60, (int) screenSize.getWidth(), 80);

        reqAworkBtn = new JButton("Request a Work");
        reqAworkBtn.setBackground(Color.decode("#a39887"));
        reqAworkBtn.setAlignmentX(100);
        reqAworkBtn.setFocusPainted(false);
        reqAworkBtn.addActionListener(this);

        refreshBtn = new JButton("Refresh");
        refreshBtn.setBackground(Color.decode("#a39887"));
        refreshBtn.setFocusPainted(false);
        refreshBtn.addActionListener(this);

        logOutBtn = new JButton("Log Out");
        logOutBtn.setBackground(Color.decode("#a39887"));
        logOutBtn.setFocusPainted(false);
        logOutBtn.addActionListener(this);

        JPanel welcomBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 50, 20));

        welcomBtnPanel.add(refreshBtn);
        welcomBtnPanel.add(reqAworkBtn);
        welcomBtnPanel.add(logOutBtn);

        JLabel welcomeLabel = new JLabel("<html> Welcome <font color='#ebc38a'>" + " " + this.name + " </font></html>");
        welcomeLabel.setHorizontalAlignment(JLabel.LEFT);
        welcomeLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));

        JPanel forcurrentWorkLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forcurrentWorkLabel.setBackground(Color.decode("#40392f"));
        forcurrentWorkLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forcurrentWorkLabel.setBounds(0, 140, (int) screenSize.getWidth(), 60);

        JLabel currentWorkLabel = new JLabel("Your Current Work");
        currentWorkLabel.setBorder(new EmptyBorder(20, 20, 10, 10));
        currentWorkLabel.setHorizontalAlignment(JLabel.LEFT);
        currentWorkLabel.setForeground(Color.decode("#ebc38a"));
        currentWorkLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));

        forcurrentWorkLabel.add(currentWorkLabel);

        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(welcomBtnPanel);

        JPanel forPreviousWorkLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forPreviousWorkLabel.setBackground(Color.decode("#40392f"));
        forPreviousWorkLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forPreviousWorkLabel.setBounds(0, 400, (int) screenSize.getWidth(), 60);

        JLabel previousWorkLabel = new JLabel("<html>Your Previous Work</html>");
        previousWorkLabel.setHorizontalAlignment(JLabel.LEFT);
        previousWorkLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        previousWorkLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        previousWorkLabel.setForeground(Color.decode("#ebc38a"));
        forPreviousWorkLabel.add(previousWorkLabel);

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

        allWorkPanel = new JPanel();
        allWorkScrollPane = new JScrollPane(allWorkPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        allWorkPanel.setLayout(new BoxLayout(allWorkPanel, BoxLayout.Y_AXIS));
        allWorkScrollPane.setBounds(0, 200, (int) screenSize.getWidth() - 60, 200);

        f.add(headerPanel);
        f.add(welcomePanel);
        f.add(forcurrentWorkLabel);
        viewOngoingWork();
        f.add(forPreviousWorkLabel);
        viewPreviousWork();
        f.add(footer);
        f.setVisible(true);// making the frame visible

    }

    public void viewOngoingWork() {
        String sql2 = "select w_id, s_day, s_month, s_year, e_day, e_month, e_year, siteloc,fname, totalarea, totalestimate, paymentstatus, s_id, id from officestaff natural join work natural join site where work.c_id=? and officestaff.id=work.o_id";
        ResultSet workDetails;
        try {
            PreparedStatement prepareStatement1 = con.prepareStatement(sql2);
            prepareStatement1.setObject(1, UUID.fromString(c_id));
            workDetails = prepareStatement1.executeQuery();

            while (workDetails.next()) {
                JLabel currentWID = new JLabel("Work Id : " + workDetails.getString(1));
                currentWID.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWID.setBackground(Color.decode("#f29105"));

                JLabel currentWStart = new JLabel("Work Started : " + workDetails.getString(2) + " / "
                        + workDetails.getString(3) + "/" + workDetails.getString(4));
                currentWStart.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentWStart.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentWStart.setBackground(Color.decode("#f29105"));

                JLabel currentClientName = new JLabel("Supervisor Name : " + workDetails.getString(9));
                currentClientName.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentClientName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentClientName.setBackground(Color.decode("#f29105"));

                JLabel currentLocation = new JLabel("Location : " + workDetails.getString(8));
                currentLocation.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentLocation.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentLocation.setBackground(Color.decode("#f29105"));

                JLabel currentArea = new JLabel("Area (Sq): " + workDetails.getString(10));
                currentArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                currentArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                currentArea.setBackground(Color.decode("#f29105"));

                JLabel estimateArea = new JLabel("Estimate : " + workDetails.getString(11));
                estimateArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                estimateArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                estimateArea.setBackground(Color.decode("#f29105"));

                JLabel PaymentStatusArea = new JLabel("Paymenet Status: " + workDetails.getString(12));
                PaymentStatusArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                PaymentStatusArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                PaymentStatusArea.setBackground(Color.decode("#f29105"));

                currentWorkOptions = new JPanel(new GridLayout(2, 0, 5, 5));
                currentWorkOptions.setBackground(Color.decode("#d1c4b2"));

                JButton materialBtn = new JButton("Used Materials");
                materialBtn.setBackground(Color.decode("#a39887"));
                materialBtn.setFocusPainted(false);
                materialBtn.setName("UMT" + workDetails.getString(13));
                materialBtn.addActionListener(this);

                JButton paymentBtn = new JButton("Payment");
                paymentBtn.setBackground(Color.decode("#a39887"));
                paymentBtn.setName("PAY" + workDetails.getString(1));
                paymentBtn.addActionListener(this);
                paymentBtn.setFocusPainted(false);

                JButton contactClient = new JButton("Contact Supervisor");
                contactClient.setBackground(Color.decode("#a39887"));
                contactClient.setFocusPainted(false);
                contactClient.setName("CTS" + workDetails.getString(14));
                contactClient.addActionListener(this);

                currentWorkOptions.add(materialBtn);
                currentWorkOptions.add(paymentBtn);
                currentWorkOptions.add(contactClient);
                currentWorkPanel = new JPanel(new GridLayout(3, 0));
                currentWorkPanel.setBackground(Color.decode("#d1c4b2"));
                currentWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
                currentWorkPanel.add(currentWID);
                currentWorkPanel.add(currentWStart);
                currentWorkPanel.add(currentClientName);
                currentWorkPanel.add(currentLocation);
                currentWorkPanel.add(currentArea);
                currentWorkPanel.add(estimateArea);
                currentWorkPanel.add(PaymentStatusArea);

                currentWorkPanel.add(currentWorkOptions);
                allWorkPanel.add(currentWorkPanel);
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        f.add(allWorkScrollPane);
    }

    public void viewPreviousWork() {
        allPreviousWorkPanel = new JPanel(new GridLayout(3, 0));
        allPreviousWorkScrollPane = new JScrollPane(allPreviousWorkPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        allPreviousWorkPanel.setLayout(new BoxLayout(allPreviousWorkPanel, BoxLayout.Y_AXIS));
        allPreviousWorkScrollPane.setBounds(0, 460, (int) screenSize.getWidth() - 60, 200);

        String sql3 = "select w_id, s_day, s_month, s_year, e_day, e_month, e_year, siteloc,fname, totalarea, totalestimate, paymentstatus from officestaff natural join work natural join site where work.c_id=? and e_day is not null  and officestaff.id=work.o_id";
        ResultSet previousWorkDetails;
        try {
            PreparedStatement prepareStatement1 = con.prepareStatement(sql3);
            prepareStatement1.setObject(1, UUID.fromString(c_id));
            previousWorkDetails = prepareStatement1.executeQuery();

            while (previousWorkDetails.next()) {
                JPanel previousWorkPanel = new JPanel(new GridLayout(3, 0));
                previousWorkPanel.setBackground(Color.decode("#d1c4b2"));
                previousWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

                JLabel previousWID = new JLabel("Work Id : " + previousWorkDetails.getString(1));
                previousWID.setBorder(new EmptyBorder(20, 20, 20, 20));
                previousWID.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                previousWID.setBackground(Color.decode("#f29105"));

                JLabel previousWStart = new JLabel("Work Started on : " + previousWorkDetails.getString(2) + " / "
                        + previousWorkDetails.getString(3) + "/" + previousWorkDetails.getString(4));
                previousWStart.setBorder(new EmptyBorder(20, 20, 20, 20));
                previousWStart.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                previousWStart.setBackground(Color.decode("#f29105"));

                JLabel previousWEnd = new JLabel("Work Ended on  : " + previousWorkDetails.getString(5) + " / "
                        + previousWorkDetails.getString(6) + "/" + previousWorkDetails.getString(7));
                previousWEnd.setBorder(new EmptyBorder(20, 20, 20, 20));
                previousWEnd.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                previousWEnd.setBackground(Color.decode("#f29105"));

                JLabel previousClientName = new JLabel("Supervisor Name : " + previousWorkDetails.getString(9));
                previousClientName.setBorder(new EmptyBorder(20, 20, 20, 20));
                previousClientName.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                previousClientName.setBackground(Color.decode("#f29105"));

                JLabel previousLocation = new JLabel("Location :" + previousWorkDetails.getString(8));
                previousLocation.setBorder(new EmptyBorder(20, 20, 20, 20));
                previousLocation.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                previousLocation.setBackground(Color.decode("#f29105"));

                JLabel previousArea = new JLabel("Area (sq) :" + previousWorkDetails.getString(10));
                previousArea.setBorder(new EmptyBorder(20, 20, 20, 20));
                previousArea.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                previousArea.setBackground(Color.decode("#f29105"));

                JLabel presviousEstimate = new JLabel("Estimate :" + previousWorkDetails.getString(11));
                presviousEstimate.setBorder(new EmptyBorder(20, 20, 20, 20));
                presviousEstimate.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                presviousEstimate.setBackground(Color.decode("#f29105"));

                JLabel paymentStatus = new JLabel("Payment Status :" + previousWorkDetails.getString(12));
                paymentStatus.setBorder(new EmptyBorder(20, 20, 20, 20));
                paymentStatus.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
                paymentStatus.setBackground(Color.decode("#f29105"));

                JPanel previousWorkOptions = new JPanel(new GridLayout(2, 0, 5, 5));
                previousWorkOptions.setBackground(Color.decode("#d1c4b2"));
                previousWorkOptions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                previousWorkOptions.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));

                JButton getReportBtn = new JButton("Get Report");
                getReportBtn.setBackground(Color.decode("#a39887"));
                getReportBtn.setName("GTR" + previousWorkDetails.getString(1));
                getReportBtn.setFocusPainted(false);
                getReportBtn.addActionListener(this);

                previousWorkOptions.add(getReportBtn);

                previousWorkPanel.add(previousWID);
                previousWorkPanel.add(previousWStart);
                previousWorkPanel.add(previousWEnd);
                previousWorkPanel.add(previousWID);
                previousWorkPanel.add(previousClientName);
                previousWorkPanel.add(previousLocation);
                previousWorkPanel.add(previousArea);
                previousWorkPanel.add(presviousEstimate);
                previousWorkPanel.add(paymentStatus);

                allPreviousWorkPanel.add(previousWorkPanel);
                allPreviousWorkPanel.add(previousWorkOptions);

            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        f.add(allPreviousWorkScrollPane);
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
                // allWorkPanel.setSize(800, 00);
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