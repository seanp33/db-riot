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
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
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

    public List<Db> init(int count, String ddlClasspathFile) {
        databases = new ArrayList<Db>();

        for (int i = 0; i < count; i++) {
            try {
                Db db = DBFactory.createDatabase(HSQLDB_ROOT + i, HSQLDB_DRIVER, HSQLDB_USERNAME, HSQLDB_PASSWORD);
                DBFactory.initDatabase(db, new InputStreamReader(getClass().getResourceAsStream(ddlClasspathFile)));
                databases.add(db);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return databases;
    }

    public void destroy() throws SQLException {
        for (Db db : databases) {
            db.getDatasource().close();
        }
    }
}
