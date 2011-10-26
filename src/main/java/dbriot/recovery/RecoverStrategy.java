package dbriot.recovery;

import dbriot.database.DbWorld;

/**
 * User: sean
 * Date: 10/19/11
 * Time: 11:07 PM
 */
public interface RecoverStrategy {

    public boolean execute(DbWorld dbWorld);
}
