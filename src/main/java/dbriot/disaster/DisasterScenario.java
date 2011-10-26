package dbriot.disaster;

import dbriot.database.DbWorld;

/**
 * User: sean
 * Date: 10/19/11
 * Time: 10:47 PM
 */
public interface DisasterScenario {

    void execute(DbWorld dbWorld);

}
