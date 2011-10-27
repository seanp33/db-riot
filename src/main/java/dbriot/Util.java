package dbriot;

import java.util.UUID;

/**
 * User: sean
 * Date: 10/19/11
 * Time: 10:56 PM
 */
public class Util {

    private static String lastId;

    public static String tmpDir = System.getProperty("java.io.tmpdir");

    public static String getUUID(){
        lastId = UUID.randomUUID().toString();
        return lastId;
    }

    public static String getLastID(){
        return lastId;
    }

    public static int randRange(int low, int high) {
        Double rand = (Math.floor(Math.random() * (high - low + 1)) + low);
        return rand.intValue();
    }
}
