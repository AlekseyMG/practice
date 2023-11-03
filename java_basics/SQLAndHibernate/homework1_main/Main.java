import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "skillbox";
        String pass = "testtest";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT c.name, " +
                    "COUNT(*) / (MAX(MONTH(pl.subscription_date)) - (MIN(MONTH(pl.subscription_date)) - 1)) 'avg' " +
                    "FROM courses c " +
                    "JOIN purchaselist pl ON pl.course_name = c.name " +
                    "GROUP BY c.name " +
                    "ORDER BY c.name");
            while (resultSet.next()) {
                String courseName = resultSet.getString("name");
                String avg = resultSet.getString("avg");
                System.out.println(courseName + " | " + avg);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}