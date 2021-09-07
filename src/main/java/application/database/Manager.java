package application.database;

import application.models.Dragon;
import application.models.Rarity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    DBEngine engine;

    public Manager(DBEngine engine) {
        this.engine = engine;
    }

    public List<Dragon> listAllDragons() {
        String query = "SELECT * FROM  " +DBHelper.TABLE_DRAGON+" ;";

        List<Dragon> dragons = new ArrayList<>();



        try {
            Statement statement = engine.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long id = resultSet.getLong("id");        //resultSet.getLong(1); --> csak ha biztosan tudom, melyik indexen van.
                String name = resultSet.getString("unique_name");
                String text = resultSet.getString("dragon_text");
                String rarityFromDB = resultSet.getString("rarity").toUpperCase();
                Rarity rarity = Rarity.valueOf(rarityFromDB);

                Dragon dragon = new Dragon(id, name, text, rarity);
                dragon.setElements(engine.findDragonsElement(id));


                dragons.add(dragon);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return dragons;
    }
}
