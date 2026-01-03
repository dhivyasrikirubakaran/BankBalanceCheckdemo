import java.sql.*;
import java.util.Scanner;

public class CheckBalance {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/bank_db";
        String user = "root";
        String pass = "root19"; // change this

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            while (true) {

                System.out.print("\nEnter Account Number (or 0 to exit): ");
                int accNo = sc.nextInt();

                if (accNo == 0) {
                    System.out.println("Exiting... Thank you!");
                    break;
                }

                String query = "SELECT holder_name, balance FROM accounts WHERE acc_no = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, accNo);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    System.out.println("\n=== Account Details ===");
                    System.out.println("Holder Name : " + rs.getString("holder_name"));
                    System.out.println("Balance     : ₹" + rs.getDouble("balance"));
                } else {
                    System.out.println("❌ Account Not Found!");
                }
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}