package dbriot.recovery;

import dbriot.database.DbWorld;

/**
 * User: sean
 * Date: 10/19/11
 * Time: 11:20 PM
 */
public class JGitRecoverStrategy implements RecoverStrategy {
    @Override
    public boolean execute(DbWorld dbWorld) {
        boolean success = false;
        // TODO
        // mayber something like...
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
