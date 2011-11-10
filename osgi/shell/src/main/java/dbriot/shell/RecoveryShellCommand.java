package dbriot.shell;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

import cyberpilot.jms.JMSProducer;
import org.springframework.jms.core.JmsTemplate;

@Command(scope = "recovery", name = "hello", description="Says hello")
public class RecoveryShellCommand extends OsgiCommandSupport {
	
	private JMSProducer producer;
	
    public JMSProducer getProducer() {
		return producer;
	}

	public void setProducer(JMSProducer producer) {
		this.producer = producer;
	}

	private JmsTemplate t;

	public JmsTemplate getJmsTemplate() {
		return t;
	}
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		System.out.println("RecoveryShellCommand Setting JmsTemplate");
		if (jmsTemplate == null){
			System.out.println("JMS Template is NULL");
		}
		this.t = jmsTemplate;
		System.out.println("T = " + t);
	}
	
	@Argument(index = 0, name = "arg", description = "The command argument", required = false, multiValued = false)
    String arg = null;
	
    @Override
    protected Object doExecute() throws Exception {
        System.out.println("Executing Hello command");
        
        JMSProducer p = new JMSProducer();
        System.out.println("TT = " + t);
        p.send(t);
        //producer.send();
        return null;
    }
    
    public void init(){
    	System.out.println("RecoveryShellCommand.init()");
    }
}
