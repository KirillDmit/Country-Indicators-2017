import java.sql.ResultSet;
import java.sql.SQLException;

public class Task3 {

    public static void run(){
        try {
            var statement = Main.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT country FROM data ORDER BY ABS(health - (SELECT AVG(health) FROM data WHERE region = 'Western Europe' OR region = 'Sub-Saharan Africa')) limit 1;");
            System.out.println(rs.getString(1));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
