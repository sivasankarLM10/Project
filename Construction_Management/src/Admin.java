
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.util.UUID;

public class Admin extends Login implements workDetails, ActionListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JFrame addstaffFrame, f, createFrame, viewWorkFrame, loginFrame;
    JComboBox<String> rolesComboxBox;
    JTextField usernameField, loginUsernameField, mailIdField, phoneField, fName, lName, mailArea, setUsenameField;
    JButton loginSubmitBtn, viewWorkBtn, goTologinBtn, refreshBtn, addmatBtn,
            addStaffInFormBtn, addOfficeStaff, addStaffBtn, logOutBtn;
    JPanel formaterialLabel, allAvailableWorkPanel, viewWorkPanel, allMaterialPanel;
    JPasswordField JPasswordField, passwordField, loginPasswordField, cnfPasswordField, setPassField;
    JScrollPane allAvailabeWorkScrollPane, allMaterialScrollPane;

    Config connection = new Config();
    Connection con = connection.dbConnect();
    public String site_id, ad_id, adminName;
    public int totalWorks, totalClients, totalSupervisor, totalOngoingwork;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginSubmitBtn) {
            System.out.println("calling ..");

            String sql = "select * from logindetails where username=? and password =?";
            try {
                PreparedStatement prepareStatement = con.prepareStatement(sql);
                prepareStatement.setString(1, loginUsernameField.getText());
                prepareStatement.setString(2, new String(loginPasswordField.getPassword()));

                ResultSet rs = prepareStatement.executeQuery();
                while (rs.next()) {
                    System.out.println("hello ");
                    System.out.println(rs.getObject(1) + " " + rs.getString(2));
                    this.ad_id = rs.getString(2);
                    loadAdminPanel(this.ad_id);
                    loginFrame.dispose();
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } else if (e.getSource() == addmatBtn) {
            Materials m1 = new Materials();
            m1.addMaterialstoCompany();

        } else if (e.getSource() == addOfficeStaff) {
            addOfficeStaff();

        } else if (e.getSource() == addStaffInFormBtn) {
            System.out.println("hello here");
            String sql = "insert into officestaff values(?,?,?,?); insert into logindetails(oid, username, password) values (?,?,?); insert into contact(oid, phone, mailid) values(?, ?, ?)";
            try {
                UUID idOne = UUID.randomUUID();

                PreparedStatement prepareStatement = con.prepareStatement(sql);
                prepareStatement.setObject(1, idOne);
                prepareStatement.setString(2, fName.getText());
                prepareStatement.setString(3, lName.getText());
                prepareStatement.setString(4, (String) rolesComboxBox.getSelectedItem());

                prepareStatement.setObject(5, idOne);
                prepareStatement.setString(6, setUsenameField.getText());
                prepareStatement.setString(7, new String(setPassField.getPassword()));

                prepareStatement.setObject(8, idOne);
                prepareStatement.setString(9, mailArea.getText());
                prepareStatement.setString(10, phoneField.getText());

                int updatedCount = prepareStatement.executeUpdate();
                System.out.println(updatedCount + "  updated ");
                if (updatedCount == 1) {
                    addstaffFrame.dispose();
                }
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        } else if (e.getSource() == logOutBtn) {
            logout();
        }

        else {
            JButton button = (JButton) e.getSource();
            String code = button.getName().substring(0, 3);
            String idneed = button.getName().substring(3);
            if (code.equals("VIW")) {
                viewWork(idneed);
                System.out.println(code + " " + idneed);

            }
        }

    }

    public void addOfficeStaff() {
        addstaffFrame = new JFrame();
        addstaffFrame.setVisible(true);
        addstaffFrame.setSize(700, 550);
        addstaffFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);
        addstaffFrame.setLayout(null);
        addstaffFrame.getContentPane().setBackground(Color.decode("#d1c4b2"));
        ;

        JPanel forheaderLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forheaderLabel.setBackground(Color.decode("#40392f"));
        forheaderLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forheaderLabel.setBounds(0, 0, (int) screenSize.getWidth(), 80);

        JLabel headerLabel = new JLabel("Office Staff Details");
        headerLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        headerLabel.setHorizontalAlignment(JLabel.LEFT);
        headerLabel.setForeground(Color.decode("#ebc38a"));
        headerLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));

        forheaderLabel.add(headerLabel);

        JLabel fnameLabel = new JLabel("First name");
        fnameLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
        fnameLabel.setBounds(80, 80, 200, 30);

        JLabel lnameLabel = new JLabel("Last name");
        lnameLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
        lnameLabel.setBounds(80, 120, 200, 30);

        JLabel rollLabel = new JLabel("Roll");
        rollLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
        rollLabel.setBounds(80, 165, 200, 30);

        JLabel mailLabel = new JLabel("Email");
        mailLabel.setFont(new Font("SansSerif", Font.PLAIN, 25));
        mailLabel.setBounds(80, 210, 200, 30);

        JLabel pNum = new JLabel("Phone Number");
        pNum.setFont(new Font("SansSerif", Font.PLAIN, 25));
        pNum.setBounds(80, 260, 200, 30);

        JLabel setUname = new JLabel("Set Username");
        setUname.setFont(new Font("SansSerif", Font.PLAIN, 25));
        setUname.setBounds(80, 310, 200, 30);

        JLabel setPass = new JLabel("Set Password");
        setPass.setFont(new Font("SansSerif", Font.PLAIN, 25));
        setPass.setBounds(80, 360, 200, 30);

        String[] optionsToChoose = { "Admin", "Supervisor" };
        rolesComboxBox = new JComboBox<>(optionsToChoose);
        rolesComboxBox.setBackground(Color.white);

        fName = new JTextField();
        fName.setBounds(300, 80, 200, 30);

        lName = new JTextField();
        lName.setBounds(300, 120, 200, 30);

        mailArea = new JTextField();
        mailArea.setBounds(300, 220, 250, 30);

        phoneField = new JTextField();
        phoneField.setBounds(300, 270, 250, 30);

        setUsenameField = new JTextField();
        setUsenameField.setBounds(300, 320, 250, 30);

        setPassField = new JPasswordField();
        setPassField.setBounds(300, 370, 250, 30);

        addStaffInFormBtn = new JButton("ADD");
        addStaffInFormBtn.setBackground(Color.decode("#a39887"));
        addStaffInFormBtn.setFocusPainted(false);
        addStaffInFormBtn.addActionListener(this);
        addStaffInFormBtn.setBounds(300, 430, 150, 30);

        rolesComboxBox.setBounds(300, 170, 200, 30);

        addstaffFrame.add(forheaderLabel);
        addstaffFrame.add(fnameLabel);
        addstaffFrame.add(fName);

        addstaffFrame.add(lnameLabel);
        addstaffFrame.add(lName);

        addstaffFrame.add(rollLabel);
        addstaffFrame.add(rolesComboxBox);

        addstaffFrame.add(mailLabel);
        addstaffFrame.add(mailArea);

        addstaffFrame.add(pNum);
        addstaffFrame.add(phoneField);

        addstaffFrame.add(setUname);
        addstaffFrame.add(setUsenameField);

        addstaffFrame.add(setPass);
        addstaffFrame.add(setPassField);

        addstaffFrame.add(addStaffInFormBtn);
    }

    public Admin() {
        login();
    }

    @Override
    public void login() {

        loginFrame = new JFrame();
        loginFrame.setLayout(new GridLayout(4, 0));
        loginFrame.setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() + 250) / 4);
        loginFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);

        JPanel titlePanel = new JPanel();
        JLabel adminLabel = new JLabel("Admin Login");
        adminLabel.setFont(new Font("SansSerif", Font.PLAIN, 38));
        titlePanel.add(adminLabel);
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

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(userLabel);
        usernamePanel.add(loginUsernameField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passLabel);
        passwordPanel.add(loginPasswordField);

        JPanel submitPanel = new JPanel();
        submitPanel.setAlignmentY(SwingConstants.BOTTOM);
        submitPanel.add(loginSubmitBtn);

        loginFrame.add(titlePanel);
        loginFrame.add(usernamePanel);
        loginFrame.add(passwordPanel);
        loginFrame.add(submitPanel);
        loginFrame.setVisible(true);

        loginSubmitBtn.addActionListener(this);

    }

    @Override
    public void logout() {

        f.dispose();
    }

    public void loadAdminPanel(String ad_id) {

        String sql6 = " select * from officestaff where id=? ";
        ResultSet rs1;
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql6);
            prepareStatement.setObject(1, UUID.fromString(ad_id));
            rs1 = prepareStatement.executeQuery();
            while (rs1.next()) {
                this.adminName = rs1.getString(2) + " " + rs1.getString(3);

            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        f = new JFrame();// creating instance of JFrame
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

        JLabel welcomeLabel = new JLabel("<html> Welcome  <font color='#ebc38a'>" + adminName + "</font></html>");
        welcomeLabel.setHorizontalAlignment(JLabel.LEFT);
        welcomeLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 35));
        // welcomeLabel.setForeground(Color.decode("#a38f72"));
        headerPanel.add(logoLabel);
        headerPanel.add(titleLabel);

        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
        welcomePanel.setBackground(Color.decode("#f0f0f0"));
        welcomePanel.setBounds(0, 60, (int) screenSize.getWidth(), 80);

        welcomePanel.add(welcomeLabel);

        // ADDING BUTTONS
        JPanel bodyButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bodyButtons.setBounds(0, 140, (int) screenSize.getWidth(), 60);
        bodyButtons.setBackground(Color.decode("#f0f0f0"));
        f.setVisible(true);

        JButton pastWork = new JButton("SEE PAST WORKS");
        pastWork.setBackground(Color.decode("#a39887"));
        pastWork.setAlignmentX(100);
        pastWork.setFocusPainted(false);
        pastWork.addActionListener(this);

        JButton supList = new JButton("SEE ALL SUPERVISORS");
        supList.setBackground(Color.decode("#a39887"));
        supList.setAlignmentX(100);
        supList.setFocusPainted(false);
        supList.addActionListener(this);

        JButton clist = new JButton("SEE ALL CLIENTS");
        clist.setBackground(Color.decode("#a39887"));
        clist.setAlignmentX(100);
        clist.setFocusPainted(false);
        clist.addActionListener(this);

        addOfficeStaff = new JButton("Add Office Staff");
        addOfficeStaff.setBackground(Color.decode("#a39887"));
        addOfficeStaff.setAlignmentX(100);
        addOfficeStaff.setFocusPainted(false);
        addOfficeStaff.addActionListener(this);

        addmatBtn = new JButton("Add Materials");
        addmatBtn.setBackground(Color.decode("#a39887"));
        addmatBtn.setFocusPainted(false);
        addmatBtn.addActionListener(this);

        logOutBtn = new JButton("Log Out");
        logOutBtn.setBackground(Color.decode("#a39887"));
        logOutBtn.setAlignmentX(100);
        logOutBtn.setFocusPainted(false);
        logOutBtn.addActionListener(this);

        bodyButtons.add(addOfficeStaff);
        bodyButtons.add(addmatBtn);
        bodyButtons.add(logOutBtn);

        welcomePanel.add(bodyButtons);

        JPanel forOngoingtWorkLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        forOngoingtWorkLabel.setBackground(Color.decode("#40392f"));
        forOngoingtWorkLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        forOngoingtWorkLabel.setBounds(0, 140, (int) screenSize.getWidth(), 60);

        JLabel ongoingWorkLabel = new JLabel("Ongoing Work");
        ongoingWorkLabel.setBorder(new EmptyBorder(20, 20, 10, 10));
        ongoingWorkLabel.setHorizontalAlignment(JLabel.LEFT);
        ongoingWorkLabel.setForeground(Color.decode("#ebc38a"));

        ongoingWorkLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));

        forOngoingtWorkLabel.add(ongoingWorkLabel);

        allAvailableWorkPanel = new JPanel();

        allAvailabeWorkScrollPane = new JScrollPane(allAvailableWorkPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        allAvailableWorkPanel.setLayout(new BoxLayout(allAvailableWorkPanel, BoxLayout.Y_AXIS));
        allAvailabeWorkScrollPane.setBounds(0, 200, (int) screenSize.getWidth() - 60, 200);

        f.add(headerPanel);
        f.add(welcomePanel);

        f.add(forOngoingtWorkLabel);
        viewOngoingWork();

        JPanel generalInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        generalInfoPanel.setBackground(Color.decode("#40392f"));
        generalInfoPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        generalInfoPanel.setBounds(0, 460, (int) screenSize.getWidth(), 60);

        JLabel generalInfoLabel = new JLabel("General Info");
        generalInfoLabel.setBorder(new EmptyBorder(20, 20, 10, 10));
        generalInfoLabel.setHorizontalAlignment(JLabel.LEFT);
        generalInfoLabel.setForeground(Color.decode("#ebc38a"));
        generalInfoLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));

        generalInfoPanel.add(generalInfoLabel);

        f.add(generalInfoPanel);
        JPanel footer = new JPanel();
        footer.setLayout(new BorderLayout());
        JLabel footerLabel = new JLabel(
                "<html>copyright © 2021 - Albatross Builders <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All Rights Reserved </html>",
                SwingConstants.CENTER);
        footerLabel.setVerticalAlignment(SwingConstants.TOP);
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setForeground(Color.decode("#403f3f"));
        footer.setBackground(Color.decode("#f0f0f0"));
        footer.setBounds(0, 670, (int) screenSize.getWidth(), 30);
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.add(footerLabel, BorderLayout.PAGE_END);
        viewGeneralInfo();
        f.add(footer);

    }

    public void viewOngoingWork() {
        String sql2 = "select w_id, s_day, s_month, s_year, e_day, e_month, e_year, siteloc,fname, totalarea, totalestimate, paymentstatus, s_id from officestaff natural join work natural join site where e_day is null  and officestaff.id=work.o_id";
        ResultSet workDetails;
        try {
            PreparedStatement prepareStatement1 = con.prepareStatement(sql2);
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

                JPanel availabeWorkOptions = new JPanel(new GridLayout(2, 0, 5, 5));
                availabeWorkOptions.setBackground(Color.decode("#d1c4b2"));

                viewWorkBtn = new JButton("view");
                viewWorkBtn.setBackground(Color.decode("#a39887"));
                viewWorkBtn.setName("VIW" + workDetails.getString(1));
                viewWorkBtn.addActionListener(this);
                viewWorkBtn.setFocusPainted(false);

                availabeWorkOptions.add(viewWorkBtn);

                JPanel availableWorkPanel = new JPanel(new GridLayout(3, 0));
                availableWorkPanel.setBackground(Color.decode("#d1c4b2"));
                availableWorkPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
                availableWorkPanel.add(currentWID);
                availableWorkPanel.add(currentWStart);
                availableWorkPanel.add(currentClientName);
                availableWorkPanel.add(currentLocation);
                availableWorkPanel.add(currentArea);
                availableWorkPanel.add(estimateArea);
                availableWorkPanel.add(PaymentStatusArea);

                availableWorkPanel.add(availabeWorkOptions);
                allAvailableWorkPanel.add(availableWorkPanel);

            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        f.add(allAvailabeWorkScrollPane);
    }

    public void viewGeneralInfo() {
        String sql = "select count(work) as totalwork from work ;";
        String sql1 = " select count(client) as totalclient from client;";
        String sql4 = " select count(officeStaff) as totalsupervisor from officestaff where role='Supervisor';";
        String sql5 = "  select count(work) as totalongoingwok from work where e_day is null; ";

        ResultSet totalwork, tClient, tSupervisor, tOnWork;
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            PreparedStatement prepareStatement1 = con.prepareStatement(sql1);
            PreparedStatement prepareStatement4 = con.prepareStatement(sql4);
            PreparedStatement prepareStatement5 = con.prepareStatement(sql5);
            totalwork = prepareStatement.executeQuery();
            while (totalwork.next()) {
                totalWorks = totalwork.getInt(1);
            }

            tClient = prepareStatement1.executeQuery();
            while (tClient.next()) {
                totalClients = tClient.getInt(1);
            }
            tSupervisor = prepareStatement4.executeQuery();
            while (tSupervisor.next()) {
                totalSupervisor = tSupervisor.getInt(1);
            }
            tOnWork = prepareStatement5.executeQuery();
            while (tOnWork.next()) {
                totalOngoingwork = tOnWork.getInt(1);
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        JLabel totalWorkLabel = new JLabel("Total Work Got: " + totalWorks);
        totalWorkLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        totalWorkLabel.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        totalWorkLabel.setBackground(Color.decode("#f29105"));

        JLabel totalClientLabel = new JLabel("Total Client : " + totalClients);
        totalClientLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        totalClientLabel.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        totalClientLabel.setBackground(Color.decode("#f29105"));

        JLabel totalSupervisorLabel = new JLabel("Total Supervisor : " + totalSupervisor);
        totalSupervisorLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        totalSupervisorLabel.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        totalSupervisorLabel.setBackground(Color.decode("#f29105"));

        JLabel onGoingWorkLabel = new JLabel("Works Ongoing : " + totalOngoingwork);
        onGoingWorkLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        onGoingWorkLabel.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        onGoingWorkLabel.setBackground(Color.decode("#f29105"));

        JLabel profitLabel = new JLabel("Total Profit : " + " ");
        profitLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        profitLabel.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
        profitLabel.setBackground(Color.decode("#f29105"));

        JPanel infoPanel = new JPanel(new GridLayout(3, 0));
        infoPanel.setBackground(Color.decode("#d1c4b2"));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
        infoPanel.setBounds(0, 520, (int) screenSize.getWidth(), 150);

        infoPanel.add(totalWorkLabel);
        infoPanel.add(totalClientLabel);
        infoPanel.add(totalSupervisorLabel);
        infoPanel.add(onGoingWorkLabel);
        infoPanel.add(profitLabel);

        f.add(infoPanel);
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