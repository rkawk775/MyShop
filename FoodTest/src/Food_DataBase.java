import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Food_DataBase {

   public static Connection conn;

   public static Connection getConnection() {

      Connection conn = null;

      try {
         String id = "root";
         String pw = "1234";
         String url = "jdbc:mysql://192.168.0.35:3306/myfood";
         //String url = "jdbc:mysql://localhost:3306/myfood";
         
         Class.forName("com.mysql.cj.jdbc.Driver");

         conn = DriverManager.getConnection(url, id, pw);

         System.out.println("접속성공");
      } catch (Exception e) {
         System.out.println("접속실패");
         e.printStackTrace();
      }
      return conn;
   }
   
   public static void close(Connection con, PreparedStatement ps) {
	   try {
		   if(ps != null)
			   ps.close();
		   if(con != null)
			   con.close();
	   }catch(Exception e){
		   e.printStackTrace();
	   }
   }

   public static void main(String[] args) {
      getConnection();
   }

}