import java.sql.*;

public class Config {

    public Config() {
    }

    public Connection dbConnect() {
        Connection con;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Construction_Management", "postgres", "Azeem@12");
            return con;
        } catch (Exception error) {
            System.out.println(error);
            return null;
        }

    }

    public void establishDb() {
        Connection connect;
        Config c1 = new Config();
        connect = c1.dbConnect();
        String sql = "create table client(c_id uuid primary key, fname varchar(30), lname varchar(30) );create table officestaff (id uuid primary key, fname varchar(30), lname varchar(30), role varchar(11) );create table contact (id uuid primary key , foreign key(id) references client(c_id) ,oid uuid , foreign key (oid) references officestaff(id),phone varchar(11), mailid varchar(40) );create table logindetails (id uuid  , foreign key(id) references client(c_id) ,oid uuid , foreign key (oid) references officestaff(id),username varchar(30) primary key  ,password varchar(30)  );create table work (w_id uuid primary key, c_id uuid, foreign key(c_id) references client(c_id),o_id uuid, foreign key (o_id) references officestaff(id), s_day varchar(2),s_month varchar(2), s_year varchar(4), e_day varchar(2), e_month varchar(2),e_year varchar(4), worktype varchar(30), paymentstatus varchar(10),totalestimate varchar(10)  );create table  site (s_id uuid primary key, w_id uuid , foreign key (w_id) references work(w_id),siteloc varchar(50), sitelabours int, totalarea varchar(10), daysworked varchar(10), labourcost varchar (10));create table materialspec (m_id uuid primary key, m_name varchar(30), m_cost int, m_size varchar (20), m_company varchar(30));create table materials (m_id uuid, foreign key (m_id) references materialspec (m_id),site_id uuid, foreign key (site_id) references site(s_id),m_quantity int);create table payment (pay_id uuid primary key, w_id uuid, foreign key (w_id) references work(w_id),estimate int);";

        try {
            PreparedStatement prepareStatement = connect.prepareStatement(sql);
            prepareStatement.executeUpdate();
            System.out.println("  inserted tables  ");

        } catch (SQLException e1) {
            System.out.println("Database Tables Already Created ");
        }
    }

}
