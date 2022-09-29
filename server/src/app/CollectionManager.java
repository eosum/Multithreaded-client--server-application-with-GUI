package app;

import data.*;
import database.DatabaseManager;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
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
    ZonedDateTime initTime = ZonedDateTime.now();


    /**
     * Add an element to collection
     *
     * @param element element that is needed to add
     */
    public synchronized boolean add(HumanBeing element, String owner) {
        try {
            Long id = databaseManager.add(element, "lol");
            element.setId(id);
            collection.add(element);
            System.out.println(collection);
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
            for (HumanBeing element: collection) {
                if (databaseManager.remove(element.getId(), user)) {
                    collection.remove(element);
                }
            }
            return true;
        }
        catch (SQLException e) {
            System.out.println();
            return false;
        }
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
        return "Количество элементов = " + amount;
    }

    /**
     * Find elements with given substring and print them
     *
     * @param subString substring to be found
     */
    public synchronized LinkedList<? extends Serializable> filterStartsWithSoundtrackName(String subString) {
        return (LinkedList<? extends Serializable>) collection.stream()
                .filter(element -> element.getSoundtrackName().startsWith(subString));
    }

    /**
     *
     * Print an information about collection
     */
    public String info() {
        return " - команда выполнена успешно. '\n' Тип - " + collection.getClass() + "\n"
                + "Количество элементов - " + collection.size() + "\n"
                + "Дата инициализации - " + initTime;
    }

    /**
     * Print the collection in ascending order
     */
    public synchronized LinkedList<? extends Serializable> printAscending() {
        collection.sort(Comparator.comparing(HumanBeing::getId));
        return collection;
    }

    /**
     *
     * @param deleteID id to delete the item by
     */
    public synchronized String removeById(Long deleteID, String user) {
        try {
            if(databaseManager.remove(deleteID, user)) {
                collection.removeIf(element -> element.getId() == deleteID);
                return "Команда выполнена";
            }
            return "Нет прав на удаление или элемента не существует";
        }
        catch (SQLException e) {
            return e.getMessage();
        }
    }

    /**
     * Remove the first element in collection
     */
    public synchronized String removeFirst(String user) {
        try {
            if(!collection.isEmpty()) {
                if(databaseManager.remove(collection.get(0).getId(), user)) {
                    collection.remove(collection.get(0));
                    return "Команда выполнена";
                }
                return "Нет прав на удаление или элемента не существует";
            }
        }
        catch (SQLException e) {
            return e.getMessage();
        }
        return "Команда выполнена";
    }

    /**
     * Remove elements that greater than given one
     *
     * @param CompareElement the element to compare with
     */
    public synchronized String removeGreater(HumanBeing CompareElement, String user) {
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
            return e.getMessage();
        }
        return "Команда выполнена";
    }

    /**
     * Print the collection
     */
    public synchronized LinkedList<? extends Serializable> show() {
        return new LinkedList<>(collection);
    }

    /**
     * Updating element's data
     *
     * @param updateID id to update the item by
     */
    public synchronized String updateById(Long updateID, HumanBeing object, String user) {
        try {
            if(databaseManager.update(object, updateID, user)) {
                object.setId(updateID);
                collection.removeIf(element -> element.getId() == updateID);
                collection.add(object);
                return "Команда выполнена";
            }
            return "Объекта нет в коллекции или у вас недостаточно прав для его изменения";
        }
        catch (SQLException e) {
            return e.getMessage();
        }
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
        return result;
    }

    public void collection_initialization() {
        collection = getDatafromDB();
    }

    public LinkedList<HumanBeing> getData() {
        return getDatafromDB();
    }
}

