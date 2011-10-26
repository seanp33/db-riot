package dbriot.disaster;

import dbriot.database.Db;
import dbriot.database.DbWorld;

import java.util.List;

/**
 * User: sean
 * Date: 10/19/11
 * Time: 10:51 PM
 */
public class SevereDisasterScenario implements DisasterScenario {

    /**
     * Takes all but one Ds offline and updates data
     * @param dbWorld
     */
    @Override
    public void execute(DbWorld dbWorld) {
        List<Db> databases = dbWorld.getDatabases();
        int offline = databases.size() - 2;

        for (int i = 0; i < offline; i++) {
            Db db = databases.get(i);
            db.offline();
        }
    }
}
