package dbriot.database;

import dbriot.Util;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.io.DatabaseIO;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.task.DatabaseToDdlTask;
import org.apache.ddlutils.task.DdlToDatabaseTask;
import org.apache.ddlutils.task.WriteDataToDatabaseCommand;
import org.apache.tools.ant.Project;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: sean
 * Date: 10/19/11
 * Time: 9:17 PM
 */
public class DbWorld {

    public static final String HSQLDB_ROOT = "jdbc:hsqldb:mem:db";
    public static final String HSQLDB_DRIVER = "org.hsqldb.jdbcDriver";
    public static final String HSQLDB_USERNAME = "SA";
    public static final String HSQLDB_PASSWORD = "";

    private List<Db> databases;

    public DbWorld() {
    }

    public List<Db> getDatabases() {
        return databases;
    }

    public Db getRandomDatabase() {
        return databases.get(Util.randRange(0, databases.size() - 1));
    }

    public List<Db> init(int count) {
        databases = new ArrayList<Db>();

        for (int i = 0; i < count; i++) {
            databases.add(createDatabase(HSQLDB_ROOT + i));
        }

        return databases;
    }

    public void destroy() throws SQLException {
        for (Db db : databases) {
            db.getDatasource().close();
        }
    }

    private Db createDatabase(String url) {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setDriverClassName(HSQLDB_DRIVER);
        ds.setUsername(HSQLDB_USERNAME);
        ds.setPassword(HSQLDB_PASSWORD);
        Db db = new Db(ds);
        initDatabase(ds);
        return db;
    }

    private void initDatabase(BasicDataSource ds) {
        Platform platform = PlatformFactory
                .createNewPlatformInstance(ds);

        Database database = new DatabaseIO().read(new InputStreamReader(
                getClass().getResourceAsStream("/ddl.xml")));

        platform.alterTables(database, false);

        initData(database, ds);
    }

    private void initData(Database database, BasicDataSource ds) {

        URL url = getClass().getResource("/data.xml");
        File data = new File(url.getFile());

        WriteDataToDatabaseCommand subTask = new WriteDataToDatabaseCommand();
        subTask.setDataFile(data);
        subTask.setFailOnError(true);
        subTask.setUseBatchMode(false);
        subTask.setEnsureForeignKeyOrder(false);

        DatabaseToDdlTask task = new DatabaseToDdlTask();
        task.setProject(new Project());
        task.addConfiguredDatabase(ds);
        task.addWriteDataToDatabase(subTask);
        task.setModelName("raceday");
        task.execute();

        /*
        Map<String, Object> driverData = new HashMap<String, Object>();
        driverData.put("id", Util.getUUID());
        driverData.put("name", "Driver1");
        driverData.put("email", "driver1@drivers.foo");

        driverData.put("id", Util.getUUID());
        driverData.put("name", "Driver2");
        driverData.put("email", "driver2@drivers.foo");

        db.insert(DbTable.DRIVERS, driverData);
        */
    }

}