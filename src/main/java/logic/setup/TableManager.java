package logic.setup;

import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;

import java.sql.*; 

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TableManager {

    private String tableName = "notebooks";

    private String url = "jdbc:mysql://173.249.18.27:3306/rozetka?"
        + "useUnicode=true&characterEncoding=utf8&"
        + "useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "ulka";
    private String password = "F4rrocop1234$F";
    private Connection connection;

    private Map<String, String> tableMap;

    public TableManager(String schemaPath) throws
            SQLException, ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            IOException {

        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(url, username, password);


        Path fileName = Paths.get(schemaPath);
        String content = new String(Files.readAllBytes(fileName));

        // System.out.println(content);

        tableMap = new Gson()
            .fromJson(content, new TypeToken<HashMap<String, String>>() { }.getType());
        // System.out.println(tableMap);
    }

    public void createTable() throws SQLException {
        String dropQuery = "DROP TABLE IF EXISTS " + tableName;
        String tableQuery = "CREATE TABLE " + tableName + " (\n"
            + "id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY\n"
            + ",name varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci \n"
            + ",link varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci\n";

        for (Map.Entry<String, String> entry : tableMap.entrySet()) {
            tableQuery += ", " + entry.getValue() + " varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci \n";
        }
        tableQuery += ")\n";

        // System.out.println(tableQuery);

        Statement statement = connection.createStatement();
        statement.execute(dropQuery);
        statement.execute(tableQuery);

    }

    public void addItem(String name, String link, Map<String, String>product)
        throws SQLException {

        String insertQuery = "INSERT INTO " + tableName + " SET \n"
            + "name=?\n"
            + ", link=?\n";

        Map<Integer, String> values = new HashMap<Integer, String>();

        int offset = 2;

        for (Map.Entry<String, String> entry : product.entrySet()) {
            if (!tableMap.containsKey(entry.getKey())) {
                continue;
            }

            offset++;
            String column = tableMap.get(entry.getKey());
            insertQuery += ", " + column + "=?\n";

            values.put(offset, entry.getValue());
        }

         System.out.println(insertQuery);

        PreparedStatement statement = connection.prepareStatement(insertQuery);
        statement.setString(1, name);
        statement.setString(2, link);
       
        for (Map.Entry<Integer, String> entry : values.entrySet()) {
            statement.setString(entry.getKey(), entry.getValue());
        }

        statement.executeUpdate();
    }

    public String getReport() throws SQLException {
        String selectQuery = "SELECT * from " + tableName;

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(selectQuery);

        String columnFormat = "%30.30s | ";
        String valueFormat = "%28.28s | ";

        String header = String.format(columnFormat, "");
        Map<String, String> report = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : tableMap.entrySet()) {
            report.put(entry.getValue(),
                String.format(columnFormat, entry.getKey().replaceAll("\\n", ""))
            );
        }

        while (rs.next()) {
            header += String.format(valueFormat, rs.getString("name").replaceAll("\\n", ""));

            for (Map.Entry<String, String> entry : tableMap.entrySet()) {
                String old = report.get(entry.getValue());
                report.put(entry.getValue(), old
                    + String.format(valueFormat, rs.getString(entry.getValue().replaceAll("\\n", ""))));
                
            }
        }

        return header + "\n" + String.join("\n", report.values());
    }
}
