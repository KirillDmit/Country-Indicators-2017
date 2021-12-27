import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.beans.Statement;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Parser {
    public static void parse() throws IOException, CsvException {
        var fileName = "Happiness index by country 2017.csv";
        var dbName = "data.db";
        Path path = Paths.get(fileName);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        try {
            Main.connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            try{
                var br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
                var reader = new CSVReaderBuilder(br).withCSVParser(parser).build();
                var rows = reader.readAll();
                rows.remove(0);
                var statement = Main.connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM data");
                if(resultSet.getInt(1) != rows.size())
                    Data.fillDB(new LinkedList<>(rows));
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
