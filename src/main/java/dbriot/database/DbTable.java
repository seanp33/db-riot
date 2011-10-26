package dbriot.database;

/**
 * User: sean
 * Date: 10/19/11
 * Time: 11:40 PM
 */
public enum DbTable {
    CARS, DRIVERS, CAR_DRIVER;

    public String getName(){
        return this.toString().toLowerCase();
    }
}
