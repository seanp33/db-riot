package dbriot.disaster;

import dbriot.Util;
import dbriot.database.Db;
import dbriot.database.DbWorld;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: sean
 * Date: 10/19/11
 * Time: 10:51 PM
 */
public class SevereDisasterScenario implements DisasterScenario {

    /**
     * Takes all but one Dbs offline and updates data
     *
     * @param dbWorld
     */
    @Override
    public void execute(DbWorld dbWorld) {

        initData(dbWorld);

        List<Db> databases = dbWorld.getDatabases();

        takeOffline(databases);

        updateData(dbWorld);

    }

    private void initData(DbWorld dbWorld) {
        URL url = getClass().getResource("/SevereDisasterScenario_initial_data.xml");
        File data = new File(url.getFile());

        List<Db> databases = dbWorld.getDatabases();
        for (Db db : databases) {
            dbWorld.populateDatabase(db, data, "SevereDisasterScenario");
        }
    }

    private void takeOffline(List<Db> databases) {
        int offline = databases.size() - 2;
        for (int i = 0; i < offline; i++) {
            Db db = databases.get(i);
            db.offline();
        }
    }

    private void updateData(DbWorld dbWorld) {
        URL url = getClass().getResource("/SevereDisasterScenario_partial_data.xml");
        File data = new File(url.getFile());

        List<Db> databases = dbWorld.getDatabases();
        for (Db db : databases) {
            dbWorld.populateDatabase(db, data, "SevereDisasterScenario");
        }
    }
}
