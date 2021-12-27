import org.jfree.data.category.DefaultCategoryDataset;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task2 {

    public static void run(){
        try {
            var statement = Main.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT AVG(health) FROM data WHERE region = 'Western Europe' OR region = 'Sub-Saharan Africa'");
            System.out.println(rs.getDouble(1));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
