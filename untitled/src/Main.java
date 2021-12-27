import com.opencsv.exceptions.CsvException;
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

    public static void main(String[] args) throws IOException, CsvException {
        Parser.parse();
        Task1.run();
        //Task2();
        //Task3();
    }
}
