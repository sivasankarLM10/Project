import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class work implements ActionListener, ItemListener {

  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

  JFrame reqWorkFrame;
  JTextField nameField, siteLocField, siteAreaField, estimateArea;
  JButton addWorkBtn, updateWorkBtn;
  JLabel workIdArea;
  JComboBox<String> workTypesBox, paymentStatusComboBox, workStatusComboBox;

  Config connection = new Config();
  Connection con = connection.dbConnect();

  String workTypes[] = { "House", "offices", "Shopping Complex" };
  private String name, c_id;
  public int s_day, s_month, s_year, e_day, e_month, e_year;

  public work(String c_id) {
    if (c_id != null) {
      String sql = " select * from client where c_id=? ";
      ResultSet rs;
      this.c_id = c_id;

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

      reqWorkFrame = new JFrame();
      reqWorkFrame.setLayout(new GridLayout(6, 0));
      reqWorkFrame.setSize((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() + 550) / 3);
      reqWorkFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);
      reqWorkFrame.getContentPane().setBackground(Color.decode("#d1c4b2"));

      JPanel forheaderLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      forheaderLabel.setBackground(Color.decode("#40392f"));
      forheaderLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
      forheaderLabel.setBounds(0, 0, (int) screenSize.getWidth(), 80);

      JLabel headerLabel = new JLabel("Request A Work");
      headerLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
      headerLabel.setHorizontalAlignment(JLabel.LEFT);
      headerLabel.setForeground(Color.decode("#ebc38a"));
      headerLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));

      forheaderLabel.add(headerLabel);

      JLabel userLabel = new JLabel();
      userLabel.setText("<html>&nbsp;&nbsp;&nbsp;Name: </html>");
      userLabel.setBackground(Color.DARK_GRAY);

      nameField = new JTextField(15);
      nameField.setText(this.name);
      siteLocField = new JTextField(15);
      siteAreaField = new JTextField(15);

      JLabel siteLocLabel = new JLabel();
      siteLocLabel.setText("<html>&nbsp;&nbsp;&nbsp;Site Location: </html>");
      siteLocLabel.setBackground(Color.DARK_GRAY);

      JLabel siteAreaLabel = new JLabel();
      siteAreaLabel.setText("<html>&nbsp;&nbsp;&nbsp;Site Area in sq.ft: </html>");
      siteAreaLabel.setBackground(Color.DARK_GRAY);

      JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      namePanel.add(userLabel);
      namePanel.add(nameField);
      namePanel.setBackground(Color.decode("#d1c4b2"));

      JPanel sitePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      sitePanel.add(siteLocLabel);
      sitePanel.add(siteLocField);
      sitePanel.add(siteAreaLabel);
      sitePanel.add(siteAreaField);
      sitePanel.setBackground(Color.decode("#d1c4b2"));

      JLabel workTypeLabel = new JLabel();
      workTypeLabel.setText("<html>&nbsp;&nbsp;&nbsp;Work Type </html>");
      workTypeLabel.setBackground(Color.DARK_GRAY);

      workTypesBox = new JComboBox<>(workTypes);
      workTypesBox.addItemListener(this);

      JPanel workTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      workTypePanel.add(workTypeLabel);
      workTypePanel.add(workTypesBox);
      workTypePanel.setBackground(Color.decode("#d1c4b2"));

      addWorkBtn = new JButton("<html>&nbsp;Request A Work</html>");
      addWorkBtn.setBackground(Color.decode("#40392f"));
      addWorkBtn.setForeground(Color.decode("#ebc38a"));
      addWorkBtn.setFocusPainted(false);
      addWorkBtn.addActionListener(this);

      JPanel submitPanel = new JPanel();
      submitPanel.setAlignmentY(SwingConstants.BOTTOM);
      submitPanel.add(addWorkBtn);
      submitPanel.setBackground(Color.decode("#d1c4b2"));

      reqWorkFrame.add(forheaderLabel);
      reqWorkFrame.add(namePanel);
      reqWorkFrame.add(sitePanel);
      reqWorkFrame.add(workTypePanel);
      reqWorkFrame.add(submitPanel);

      reqWorkFrame.setVisible(true);
    }

  }

  public void updateWorkProgess(String w_id) {

    JFrame updateWorkFrame = new JFrame();
    updateWorkFrame.setLayout(null);
    updateWorkFrame.setSize((int) screenSize.getWidth() / 2, 310);
    updateWorkFrame.setLocation((int) screenSize.getWidth() / 4, (int) screenSize.getHeight() / 4);
    updateWorkFrame.setTitle("Work Details");
    updateWorkFrame.getContentPane().setBackground(Color.decode("#d1c4b2"));

    JPanel forheaderLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    forheaderLabel.setBackground(Color.decode("#40392f"));
    forheaderLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#ebc38a")));
    forheaderLabel.setBounds(0, 0, (int) screenSize.getWidth() / 2, 80);

    JLabel headerLabel = new JLabel("Update Work Details");
    headerLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
    headerLabel.setHorizontalAlignment(JLabel.LEFT);
    headerLabel.setForeground(Color.decode("#ebc38a"));
    headerLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));

    forheaderLabel.add(headerLabel);

    JLabel wordIdLabel = new JLabel("Work Id:");
    wordIdLabel.setBounds(80, 80, 200, 30);

    JLabel estLabel = new JLabel("Total Estimate:");
    estLabel.setBounds(80, 120, 200, 30);

    JLabel payLabel = new JLabel("Payment Status");
    payLabel.setBounds(80, 165, 200, 30);

    JLabel workStatusLabel = new JLabel("Work Status");
    workStatusLabel.setBounds(80, 165, 200, 30);

    String[] paymentStatusToChoose = { "true", "false" };

    paymentStatusComboBox = new JComboBox<>(paymentStatusToChoose);
    paymentStatusComboBox.setBackground(Color.white);

    String[] options = { "Work in Progress", "Completed" };
    workStatusComboBox = new JComboBox<>(options);
    workStatusComboBox.setBackground(Color.white);

    workIdArea = new JLabel(w_id);

    estimateArea = new JTextField();

    updateWorkBtn = new JButton("Submit");
    updateWorkBtn.setBounds(260, 230, 150, 30);
    updateWorkBtn.setBackground(Color.decode("#a39887"));
    updateWorkBtn.setFocusPainted(false);
    updateWorkBtn.setName(w_id);
    updateWorkBtn.addActionListener(this);

    workIdArea.setBounds(300, 80, 300, 30);

    estimateArea.setBounds(300, 120, 290, 30);
    paymentStatusComboBox.setBounds(300, 170, 290, 30);
    workStatusComboBox.setBounds(300, 170, 290, 30);

    updateWorkFrame.add(forheaderLabel);
    updateWorkFrame.add(wordIdLabel);
    updateWorkFrame.add(workIdArea);

    updateWorkFrame.add(estLabel);
    updateWorkFrame.add(estimateArea);

    updateWorkFrame.add(workStatusLabel);
    updateWorkFrame.add(workStatusComboBox);
    updateWorkFrame.add(updateWorkBtn);
    updateWorkFrame.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == addWorkBtn) {
      String sql = "insert into work (w_id, c_id, s_day, s_month, s_year, workType) values (?,?,?,?,?,?); insert into site (s_id, w_id, siteloc, totalArea) values (?,?,?,?);";
      this.s_day = LocalDate.now().getDayOfMonth();
      this.s_month = LocalDate.now().getMonthValue();
      this.s_year = LocalDate.now().getYear();
      try {
        UUID w_id = UUID.randomUUID();
        UUID site_id = UUID.randomUUID();

        PreparedStatement prepareStatement = con.prepareStatement(sql);
        prepareStatement.setObject(1, w_id);
        prepareStatement.setObject(2, UUID.fromString(c_id));
        prepareStatement.setString(3, String.valueOf(s_day));
        prepareStatement.setString(4, String.valueOf(s_month));
        prepareStatement.setString(5, String.valueOf(s_year));
        prepareStatement.setString(6, (String) workTypesBox.getSelectedItem());

        prepareStatement.setObject(7, site_id);
        prepareStatement.setObject(8, w_id);
        prepareStatement.setString(9, siteLocField.getText());
        prepareStatement.setString(10, siteAreaField.getText());

        int updatedCount = prepareStatement.executeUpdate();
        System.out.println(updatedCount + " updated ");
        this.reqWorkFrame.dispose();

      } catch (SQLException e1) {

        e1.printStackTrace();
      }
    } else if (e.getSource() == updateWorkBtn) {
      if (workStatusComboBox.getSelectedItem() == "Completed") {
        String sql1 = "update work set totalestimate=?, e_day=?, e_month=?, e_year=? where w_id=?";
        this.e_day = LocalDate.now().getDayOfMonth();
        this.e_month = LocalDate.now().getMonthValue();
        this.e_year = LocalDate.now().getYear();

        try {
          PreparedStatement prepareStatement1 = con.prepareStatement(sql1);
          prepareStatement1.setString(1, estimateArea.getText());
          prepareStatement1.setInt(2, e_day);
          prepareStatement1.setInt(3, e_month);
          prepareStatement1.setInt(4, e_year);
          prepareStatement1.setObject(5, UUID.fromString(updateWorkBtn.getName()));
          int count = prepareStatement1.executeUpdate();

          System.out.println(count + " row updated with date");
        } catch (SQLException e1) {

          e1.printStackTrace();
        }

      } else {
        String sql = "update work set totalestimate=? where w_id=? ";
        try {
          PreparedStatement prepareStatement = con.prepareStatement(sql);
          prepareStatement.setString(1, estimateArea.getText());
          prepareStatement.setObject(2, UUID.fromString(updateWorkBtn.getName()));

          int count = prepareStatement.executeUpdate();
          System.out.println(count + " update without date");
        } catch (SQLException e1) {

          e1.printStackTrace();
        }
      }

    }

  }

  @Override
  public void itemStateChanged(ItemEvent e) {

  }

}