package dbriot;

import dbriot.database.DbWorld;
import dbriot.disaster.DisasterScenario;
import dbriot.disaster.SevereDisasterScenario;
import dbriot.recovery.GoogleDiffRecoveryStrategy;
import dbriot.recovery.RecoveryStrategy;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DbRiot {
	
	private static Logger logger = Logger.getLogger(DbRiot.class);
	
	@Autowired
    private DbWorld dbWorld;

    private DisasterScenario disasterScenario;
 
    private RecoveryStrategy recoveryStrategy;
    
    public DbRiot() {
    }

    public void init() {
    	disasterScenario.execute(dbWorld);
        logger.info("Success? " + recoveryStrategy.execute(dbWorld));
    }

    public void destroy() throws SQLException {
        dbWorld.destroy();
    }
    
    public DisasterScenario getDisasterScenario() {
		return disasterScenario;
	}

	public void setDisasterScenario(DisasterScenario disasterScenario) {
		this.disasterScenario = disasterScenario;
	}

	public RecoveryStrategy getRecoveryStrategy() {
		return recoveryStrategy;
	}

	public void setRecoveryStrategy(RecoveryStrategy recoveryStrategy) {
		this.recoveryStrategy = recoveryStrategy;
	}

	public static void main(String args[]) {
    	
    	String contextFile = "SpringBeans.xml";
    	if (args.length != 0){
    		contextFile = args[0];
    	}
    	
    	ApplicationContext context = 
    	    	  new ClassPathXmlApplicationContext(new String[] {contextFile});
    	
        DbRiot dbRiot = (DbRiot)context.getBean("dbRiot");
    }

}
