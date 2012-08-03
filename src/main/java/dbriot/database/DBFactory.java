package dbriot.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.io.DatabaseIO;
import org.apache.ddlutils.model.Database;
import org.apache.ddlutils.task.DatabaseToDdlTask;
import org.apache.ddlutils.task.WriteDataToDatabaseCommand;
import org.apache.tools.ant.Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class DBFactory {

    public static Db createDatabase(String url, String driverClassName, String username, String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setDriverClassName(driverClassName);
        ds.setUsername(username);
        ds.setPassword(password);
        Db db = new Db(ds);
        return db;
    }

    public static void initDatabase(Db db, Reader ddlReader) throws FileNotFoundException {
        Platform platform = PlatformFactory
                .createNewPlatformInstance(db.getDatasource());

        Database database = new DatabaseIO().read(ddlReader);

        platform.alterTables(database, false);
    }

    public static void populateDatabase(Db db, File data, String modelName) {
        BasicDataSource ds = db.getDatasource();

        WriteDataToDatabaseCommand subTask = new WriteDataToDatabaseCommand();
        subTask.setDataFile(data);
        subTask.setFailOnError(true);
        subTask.setUseBatchMode(false);
        subTask.setEnsureForeignKeyOrder(false);

        DatabaseToDdlTask task = new DatabaseToDdlTask();
        task.setProject(new Project());
        task.addConfiguredDatabase(ds);
        task.addWriteDataToDatabase(subTask);
        task.setModelName(modelName);
        task.execute();
    }
}
