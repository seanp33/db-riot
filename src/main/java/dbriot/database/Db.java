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

    public BasicDataSource getDatasource() {
        return datasource;
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

    public void insert(DbTable table, Map<String, Object> values) {

        if (isOnline()) {
            DynaBean dynaBean = database.createDynaBeanFor(table.getName(), false);
            for (Map.Entry<String, Object> entry : values.entrySet()) {
                dynaBean.set(entry.getKey().toUpperCase(), entry.getValue());
            }
            platform.insert(database, dynaBean);
        }
    }

    public void findAnd(DbTable table, Map<String, Object> values) {
        if (isOnline()) {
            // TODO
            /*
            String tableName = table.getName();
            List<String> params = new ArrayList<String>();
            params.add("Some title");
            Iterator it = platform.query(database,
                    "select * from + " + tableName + " +  where title = ?",
                    params,
                    new Table[]{database.findTable(tableName)});

            while (it.hasNext()) {
                DynaBean book = (DynaBean) it.next();
                System.out.println(book.get("title"));
            }
            */
        }

    }

    public void findOr(DbTable table, Map<String, Object> values) {
        if (isOnline()) {
            // TODO
        }
    }

    public void update(DbTable table, Map<String, Object> values) {
        if (isOnline()) {
            // TODO
        }
    }

    public void delete(DbTable table, Map<String, Object> values) {
        if (isOnline()) {
            // TODO
        }
    }
}
