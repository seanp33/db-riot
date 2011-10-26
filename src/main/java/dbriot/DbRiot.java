package dbriot;


import dbriot.database.DbWorld;
import dbriot.disaster.DisasterScenario;
import dbriot.disaster.SevereDisasterScenario;
import dbriot.recovery.JGitRecoverStrategy;
import dbriot.recovery.RecoverStrategy;

import java.sql.SQLException;

public class DbRiot {

    private DbWorld dbWorld;

    public DbRiot() {
    }

    public void init() {
        dbWorld = new DbWorld();
        dbWorld.init(10);

        DisasterScenario severeDisasterScenario = new SevereDisasterScenario();
        severeDisasterScenario.execute(dbWorld);

        RecoverStrategy jGitRecoverStrategy = new JGitRecoverStrategy();
        System.out.println("Success? " + jGitRecoverStrategy.execute(dbWorld));
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
