import java.sql.*;

public class DBConnection {

    private static Connection connection;

//    private static String dbName = "learn";
//    private static String dbUser = "root";
//    private static String dbPass = "ya78yrc8n4w3984";
    private static String dbName = "skillbox4";
    private static String dbUser = "skillbox";
    private static String dbPass = "testtest";
    private static StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName +
                        "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "`count` INT NOT NULL, " +
                    "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
                "VALUES" + insertQuery.toString();
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void countVoter(String name, String birthDay, int count) throws SQLException {
        //birthDay = birthDay.replace('.', '-');
        if (insertQuery.length() > 5_000_000) {
            System.out.println(insertQuery.length());
            executeMultiInsert();
            insertQuery.setLength(0);
        }
        insertQuery.append(insertQuery.length() == 0 ? "" : ",")
                .append("('")
                .append(name)
                .append("', '")
                .append(birthDay)
                .append("', ")
                .append(count)
                .append(")");

//        String sql =
//            "SELECT id FROM voter_count WHERE birthDate='" + birthDay + "' AND name='" + name + "'";
//        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
//        if (!rs.next()) {
//            DBConnection.getConnection().createStatement()
//                .execute("INSERT INTO voter_count(name, birthDate, `count`) VALUES('" +
//                    name + "', '" + birthDay + "', 1)");
//        } else {
//            Integer id = rs.getInt("id");
//            DBConnection.getConnection().createStatement()
//                .execute("UPDATE voter_count SET `count`=`count`+1 WHERE id=" + id);
//        }
//        rs.close();
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }
}
