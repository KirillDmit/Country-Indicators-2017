import java.awt.dnd.DropTarget;
import java.awt.image.AreaAveragingScaleFilter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
    private String country;
    private String region;
    private int happinessRank;
    private double happinessScore;
    private double whiskerHigh;
    private double whiskerLow;
    private double economyGdpPerCap;
    private double family;
    private double health;
    private double freedom;
    private double generosity;
    private double trustGovernment;
    private double dystopiaResidual;

    public static void fillDB(ArrayList<String[]> rows){
        ArrayList<Data> dataList  = new ArrayList<>();
        for(int i = 0; i < rows.size(); i++){
            var data = new Data();
            var row = rows.get(i);
            data.country = row[0];
            data.region = row[1];
            data.happinessRank = Integer.parseInt(row[2]);
            data.happinessScore = Double.parseDouble(row[3]);
            data.whiskerHigh = Double.parseDouble(row[4]);
            data.whiskerLow = Double.parseDouble(row[5]);
            data.economyGdpPerCap = Double.parseDouble(row[6]);
            data.family = Double.parseDouble(row[7]);
            data.health = Double.parseDouble(row[8]);
            data.freedom = Double.parseDouble(row[9]);
            data.generosity = Double.parseDouble(row[10]);
            data.trustGovernment = Double.parseDouble(row[11]);
            data.dystopiaResidual = Double.parseDouble(row[12]);

        }
    }

    private static void addData(Data data){
        try{
            PreparedStatement insertStatement = Main.connection.prepareStatement(
                    "INSERT INTO data (country, region, happinessRank, happinessScore, whiskerHigh, whiskerLow, economyGdpPerCap, family, health, freedom, generosity, trustGovernment, dystopiaResidual) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            insertStatement.setString(1, data.country);
            insertStatement.setString(2, data.region);
            insertStatement.setInt(3, data.happinessRank);
            insertStatement.setDouble(4, data.happinessScore);
            insertStatement.setDouble(5, data.whiskerHigh);
            insertStatement.setDouble(6, data.whiskerLow);
            insertStatement.setDouble(7, data.economyGdpPerCap);
            insertStatement.setDouble(8, data.family);
            insertStatement.setDouble(9, data.health);
            insertStatement.setDouble(10, data.freedom);
            insertStatement.setDouble(11, data.generosity);
            insertStatement.setDouble(12, data.trustGovernment);
            insertStatement.setDouble(13, data.dystopiaResidual);
            insertStatement.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
