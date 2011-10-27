package dbriot.recovery;

import dbriot.database.Db;
import dbriot.database.DbWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * User: sean
 * Date: 10/19/11
 * Time: 11:20 PM
 */
public class GoogleDiffRecoveryStrategy implements RecoveryStrategy {
    @Override
    public boolean execute(DbWorld dbWorld) {
        List<Db> databases = dbWorld.getDatabases();
        for (Db db : databases) {
            db.online();
        }

        List<String> dumps = new ArrayList<String>();
        for (Db db : databases) {
            dumps.add(db.dump("./"));
        }




        boolean success = false;
        // TODO
        // maybe something like...
        // For each RECOVERING Db
        // Obtain some kind of dump from all other ONLINE Dbs from latest starting after latest record
        // Perform some kind of merge
        // Apply result to RECOVERING Db
        // Take Db online
        // Verify that all Dbs are now equiv by diffing the dump of each... or something
        // @see org.eclipse.jgit.merge.*
        // @see org.eclipse.jgit.diff.*
        return success;
    }


}
