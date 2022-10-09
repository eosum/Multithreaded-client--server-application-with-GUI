package packages.app;

import packages.data.*;
import packages.database.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * CollectionManager Class
 * <p>
 * managing a collection of items
 */
public class CollectionManager {
    LinkedList<HumanBeing> collection = new LinkedList<>();
    private final DatabaseManager databaseManager = new DatabaseManager();
    LocalDate initTime = LocalDate.now();


    /**
     * Add an element to collection
     *
     * @param element element that is needed to add
     */
    public synchronized boolean add(HumanBeing element, String owner) {
        try {
            Long id = databaseManager.add(element, owner);
            element.setId(id);
            collection.add(element);
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Add an element to collection if it is smaller than the smallest existing one
     *
     * @param element element is needed to add
     */
    public synchronized boolean addIfMin(HumanBeing element, String owner) {
        Collections.sort(collection);
        try {
            if (collection.isEmpty() || collection.get(0).compareTo(element) >= 0) {
                Long id = databaseManager.add(element, owner);
                element.setId(id);
                collection.add(element);
                return true;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Clear the collection
     */
    public synchronized boolean clear(String user) {
        try {
            if (databaseManager.removeAll(user)) {
                collection.removeIf(humanBeing -> humanBeing.getOwner().equals(user));
            }
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String info() {
        return collection.getClass() + " " + collection.size() + " " + initTime;
    }

    /**
     * Count the number of elements with smaller value of weapon type
     *
     * @param weaponType the value to be compared with
     */
    public synchronized String countLessThanWeaponType(String weaponType) {
        int amount = 0;
        for(HumanBeing element: collection) {
            if (element.getWeaponType().length() < weaponType.length()) {
                amount++;
            }
        }
        return "" + amount;
    }

    /**
     *
     * @param deleteID id to delete the item by
     */
    public synchronized boolean removeById(Long deleteID, String user) {
        try {
            if(databaseManager.remove(deleteID, user)) {
                collection.removeIf(element -> element.getId() == deleteID);
                return true;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Remove the first element in collection
     */
    public synchronized boolean removeFirst(String user) {
        try {
            if(!collection.isEmpty()) {
                if(databaseManager.remove(collection.get(0).getId(), user)) {
                    collection.remove(collection.get(0));
                    return true;
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Remove elements that greater than given one
     *
     * @param CompareElement the element to compare with
     */
    public synchronized boolean removeGreater(HumanBeing CompareElement, String user) {
        try {
            for (HumanBeing element: collection) {
                if (element.compareTo(CompareElement) > 0) {
                    if (databaseManager.remove(element.getId(), user)) {
                        collection.remove(element);
                    }
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public synchronized boolean updateById(HumanBeing object, String user) {
        try {
            if(databaseManager.update(object, object.getId(), user)) {
                collection.removeIf(element -> element.getId() == object.getId());
                collection.add(object);
                return true;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public LinkedList<HumanBeing> getDatafromDB() {
        LinkedList<HumanBeing> result = new LinkedList<>();
        try {
            ResultSet collectionFromDB = databaseManager.getFromDB();
            while(collectionFromDB.next()) {
                HumanBeing element = new HumanBeing(
                        collectionFromDB.getLong("id"),
                        collectionFromDB.getString("name"),
                        new Coordinates(collectionFromDB.getFloat("coordinate_x"),
                                collectionFromDB.getFloat("coordinate_y")),
                        collectionFromDB.getBoolean("real_hero"),
                        collectionFromDB.getBoolean("has_toothpick"),
                        collectionFromDB.getLong("impact_speed"),
                        collectionFromDB.getString("soundtrack"),
                        collectionFromDB.getInt("minutes_of_waiting"),
                        WeaponType.getWeaponType(collectionFromDB.getString("weapon_type")),
                        new Car(collectionFromDB.getString("car")),
                        collectionFromDB.getString("owner")
                );
                result.add(element);
            }
        }
        catch (SQLException e) {
            System.out.println("Коллекция неинициализирована");
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }

    public void collection_initialization() {
        collection = getDatafromDB();
    }

    public LinkedList<HumanBeing> getData() {
        return getDatafromDB();
    }
}

