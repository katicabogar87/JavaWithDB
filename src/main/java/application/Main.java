package application;

import application.database.DBEngine;
import application.database.Manager;
import application.models.Dragon;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // playground :-)

        /**
         * public static void main(String[] args) {
         *         DBEngine engine = new DBEngine();       // should connect
         *
         *         if (engine.isConnected()) {
         *             List<Dragon> dragons = engine.listAllDragons();
         *
         *             for (Dragon dragon : dragons) {
         *                 System.out.println(dragon);
         *             }
         *         } else {
         *             System.out.println("no connection");
         *         }
         *     }*/

        DBEngine engine = new DBEngine();  //should connect
        Manager manager = new Manager(engine);

        System.out.println(engine.isConnected());
        if (engine.isConnected()) {
                    List<Dragon> myDragons = manager.listAllDragons();

                     for (Dragon dragon : myDragons) {
                          System.out.println(dragon);
                      }
                  } else {
                      System.out.println("no connection");
                  }


        Dragon myDragon = engine.findDragonByName("'Mushu'");
        System.out.println(myDragon);
    }



}
