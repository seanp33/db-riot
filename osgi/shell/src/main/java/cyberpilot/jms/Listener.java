package cyberpilot.jms;

public class Listener {
	//Logger logger = Logger.getLogger(Listener.class);

    public void onMessage(Object message) {
        //logger.info("JMS message received: " + message);
        System.out.println("JMS message received: " + message);
    }
}
