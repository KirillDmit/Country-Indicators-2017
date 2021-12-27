import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Task1 {

    public static void run(){
        Map<String, Double> res;
        try {
            var statement = Main.connection.createStatement();
            res = getTask1Data(statement);
            var dataSet = new DefaultCategoryDataset();
            for(var key : res.keySet())
                dataSet.addValue(res.get(key), key, "");
            var barChart = ChartFactory.createBarChart(
                    "График по показателю здоровья",
                    "Название страны",
                    "Показатель здоровья",
                    dataSet,
                    PlotOrientation.VERTICAL,
                    true, false, false
            );
            try {
                ChartUtilities.saveChartAsPNG(new File("Task1.png"), barChart, 800, 600);
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static Map<String, Double> getTask1Data(Statement statement){
        Map<String, Double> res = new HashMap<>();
        try{
            ResultSet rs = statement.executeQuery("SELECT COUNT(*), health FROM data GROUP BY region ORDER BY health");
            while(rs.next()){
                if(!rs.getString(1).equals(""))
                    res.put(rs.getString(1), rs.getDouble(2));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        Map<String, Double> resSorted = new HashMap<>();
        resSorted.putAll(res);
        return resSorted;
    }
}
