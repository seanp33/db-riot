package dbriot;


import dbriot.database.DbWorld;
import dbriot.disaster.DisasterScenario;
import dbriot.disaster.SevereDisasterScenario;
import dbriot.recovery.GoogleDiffRecoveryStrategy;
import dbriot.recovery.RecoveryStrategy;

import java.sql.SQLException;

public class DbRiot {

    private DbWorld dbWorld;

    public DbRiot() {
    }

    public void init() {
        dbWorld = new DbWorld();
        dbWorld.init(10, "/ddl.xml");

        DisasterScenario severeDisasterScenario = new SevereDisasterScenario();
        severeDisasterScenario.execute(dbWorld);

        RecoveryStrategy googleDiffRecoveryStrategy = new GoogleDiffRecoveryStrategy();
        System.out.println("Success? " + googleDiffRecoveryStrategy.execute(dbWorld));
    }

    public void destroy() throws SQLException {
        dbWorld.destroy();
    }

    public static void main(String args[]) {
        DbRiot dbRiot = new DbRiot();

        dbRiot.init();

        try {
            dbRiot.destroy();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
