package dbriot.database;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.model.Database;

import java.util.Map;

/**
 * User: sean
 * Date: 10/19/11
 * Time: 10:28 PM
 */
public class Db {

    public static final String DATABASE_NAME = "dbriot";

    private DbStatus status;

    private BasicDataSource datasource;

    private Platform platform;

    private Database database;

    public Db(BasicDataSource datasource) {
        this.datasource = datasource;
        platform = PlatformFactory.createNewPlatformInstance(datasource);
        database = platform.readModelFromDatabase(DATABASE_NAME);
        status = DbStatus.ONLINE;
    }

    public DbStatus getStatus() {
        return status;
    }

    public BasicDataSource getDatasource() {
        return datasource;
    }

    public Database getDatabase() {
        return database;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void offline() {
        status = DbStatus.OFFLINE;
    }

    public boolean isOffline() {
        return status == DbStatus.OFFLINE;
    }

    public void online() {
        status = DbStatus.ONLINE;
    }

    public boolean isOnline() {
        return status == DbStatus.ONLINE;
    }

    public void recover(){
        status = DbStatus.RECOVERING;
    }

    public boolean isRecovering(){
        return status == DbStatus.RECOVERING;
    }

    public void insert(String table, Map<String, Object> values) {
        if (isOnline()) {
            DynaBean dynaBean = database.createDynaBeanFor(table, false);
            for (Map.Entry<String, Object> entry : values.entrySet()) {
                dynaBean.set(entry.getKey().toUpperCase(), entry.getValue());
            }
            platform.insert(database, dynaBean);
        }
    }
}
