package jdbc;
import java.sql.*;

public class JdbcConnector {
    public static void main(String[] args) {
        try {
            try {
                // Step 1: "Load" the JDBC driver
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch (Exception e){
                e.printStackTrace();
            }

            // Step 2: Establish the connection to the database
            String url = "jdbc:mysql://localhost:3306/starview?verifyServerCertificate=false&useSSL=false";
            Connection con = DriverManager.getConnection(url, "starviewuser", "testdb");

            System.out.println("connection to Starview-Database successful...");

            String query = "" +
                    "select Zimmernummer," +
                    "Buchung," +
                    "VonDatum," +
                    "BisDatum," +
                    "VornameGast," +
                    "NachnameGast" +
                    " from belegungsuebersicht order by Zimmernummer";

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {

                int zimmernummer = res.getInt("Zimmernummer");
                int buchungsid = res.getInt("Buchung");
                Date vonDatum = res.getDate("VonDatum");
                Date bisDatum = res.getDate("BisDatum");
                String vorname = res.getString("VornameGast");
                String nachname = res.getString("NachnameGast");

                System.out.println("Zimmernummer: "+ zimmernummer);
                System.out.println("Buchungs-ID: " + buchungsid + " Gebucht von: " + vonDatum + " bis " + bisDatum);
                System.out.println("Gast Vorname: " + vorname + " Nachname: " + nachname);
                System.out.println("-----------------------------------------");
            }
            con.close();

        } catch (SQLException e) {
            System.err.println("Connection Fail");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
