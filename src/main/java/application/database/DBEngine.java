package application.database;

import application.models.Dragon;
import application.models.Element;
import application.models.Rarity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBEngine {

    private Connection connection;

    public DBEngine() {
        connection = connect();
        //TODO
    }

    public boolean isConnected() {
        return (connect() != null);
    }

    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/dragonDB" +
                "?" +
                "use Unicode=yes" +
                "&" +
                "characterEncoding=UTF-8";

        Properties properties = new Properties();
        properties.put("user", System.getenv("DB_USERNAME"));
        properties.put("password", System.getenv("DB_PASSWORD"));

        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            return null;
        }
    }

    /*
     * keresek sárkányt név alapján*/

    public Dragon findDragonByName(String searchName) {
        //  String query = "SELECT * FROM dragon WHERE unique_name = " + searchName;
        String query = "SELECT * FROM " +DBHelper.TABLE_DRAGON+" WHERE unique_name =?;";

        Dragon result = null;

        try {
            //Statement statement = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, searchName);        // hozzáteszi a szükséges ''-t is!
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");        //resultSet.getLong(1); --> csak ha biztosan tudom, melyik indexen van.
                String name = resultSet.getString("unique_name");
                String text = resultSet.getString("dragon_text");
                String rarityFromDB = resultSet.getString("rarity").toUpperCase();
                Rarity rarity = Rarity.valueOf(rarityFromDB);

                result = new Dragon(id, name, text, rarity);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }

    /*utasítás, ami viszaad minden sárkányt*/

   /* public List<Dragon> listAllDragons() {
        String query = "SELECT * FROM  " +DBHelper.TABLE_DRAGON+" ;";

        List<Dragon> dragons = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long id = resultSet.getLong("id");        //resultSet.getLong(1); --> csak ha biztosan tudom, melyik indexen van.
                String name = resultSet.getString("unique_name");
                String text = resultSet.getString("dragon_text");
                String rarityFromDB = resultSet.getString("rarity").toUpperCase();
                Rarity rarity = Rarity.valueOf(rarityFromDB);

                Dragon dragon = new Dragon(id, name, text, rarity);
                dragon.setElements(findDragonsElement(id));


                dragons.add(dragon);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return dragons;
    }*/

    public Element findElementByName(String name) {
        String query = "SELECT * FROM  " +DBHelper.TABLE_ELEMENT+"  WHERE element_name = ?";

        Element element = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String elementName = resultSet.getString("element_name");

                element = new Element(elementName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return element;
    }

    public List<Element> findDragonsElement(long dragonId) {
        String query = "SELECT * FROM  " +DBHelper.TABLE_DRAGONS_ELEMENT+"  WHERE dragon_id = ?";

        List<Element> elements = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, dragonId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String elementName = resultSet.getString("element_name");
                Element element = findElementByName(elementName);
                elements.add(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return elements;
    }


    /*adjunk új elemet az adatbázishoz*/

    public boolean addDragonToDB(Dragon dragon) {
        String query = "INSERT INTO "+ DBHelper.TABLE_DRAGON +"(unique_name, dragon_text, rarity) VALUES (?, ?, ?);";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, dragon.getUniqueName());
            ps.setString(2, dragon.getDragonText());
            ps.setInt(3, dragon.getRarity().getDBIndex());

            ps.executeUpdate();
            ps.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
