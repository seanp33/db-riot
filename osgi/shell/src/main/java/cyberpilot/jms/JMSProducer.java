package cyberpilot.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class JMSProducer {

	public void send(JmsTemplate t)
	{
		System.out.println("Sending request");
		if (t != null){
        t.send
        	(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createTextMessage("hello queue world");
			}
        });
		} else {
			System.out.println("t is null");
		}
	}
    
    public void init(){
    	System.out.println("JMProducer init()");
    }
	
}
