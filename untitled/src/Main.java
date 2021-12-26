import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Connection connection;

    public static void main(String[] args){
        Task1();
    }

    private static void Task1(){
        Map<String, Integer> res;
        try {
            var statement = connection.createStatement();
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

    private static Map<String, Integer> getTask1Data(Statement statement){
        Map<String, Integer> res = new HashMap<>();
        try{
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) health FROM data GROUP BY health ORDER BY health");
            while(rs.next()){
                if(!rs.getString(2).equals(""))
                    res.put(rs.getString(2), rs.getInt(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        Map<String, Integer> resSorted = new HashMap<>();
        resSorted.putAll(res);
        return resSorted;
    }
}
