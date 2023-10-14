import java.util.Properties;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HelloWorldJMSConsumer {

	private static final Logger log = Logger.getLogger(HelloWorldJMSConsumer.class.getName());

	// Set up all the default values
	private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DEFAULT_DESTINATION = "jms/queue/t24AAExtQueue";
	private static final String DEFAULT_MESSAGE_COUNT = "1";
	private static final String DEFAULT_USERNAME = "namnd";
	private static final String DEFAULT_PASSWORD = "Abc@1234";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "http-remoting://172.16.101.113:8080";

	public static void main(String[] args) {

		Context namingContext = null;

		try {
			String userName = System.getProperty("username", DEFAULT_USERNAME);
			String password = System.getProperty("password", DEFAULT_PASSWORD);

			// Set up the namingContext for the JNDI lookup
			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
			env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
			env.put(Context.SECURITY_PRINCIPAL, userName);
			env.put(Context.SECURITY_CREDENTIALS, password);
			namingContext = new InitialContext(env);

			// Perform the JNDI lookups
			String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
			ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);

			int count = Integer.parseInt(System.getProperty("message.count", DEFAULT_MESSAGE_COUNT));
			String destinationString = System.getProperty("destination", DEFAULT_DESTINATION);
			Destination destination = (Destination) namingContext.lookup(destinationString);

			try (JMSContext context = connectionFactory.createContext(userName, password)) {
				System.out.println("Received...");
				// Create the JMS consumer
				JMSConsumer consumer = context.createConsumer(destination);
				// Then receive the same number of messages that were sent
				for (int i = 0; i < count; i++) {
					String text = consumer.receiveBody(String.class, 5000);
					System.out.println(text);
				}

			}
		} catch (NamingException e) {
			e.printStackTrace();
			log.severe(e.getMessage());
		} finally {
			if (namingContext != null) {
				try {
					namingContext.close();
				} catch (NamingException e) {
					log.severe(e.getMessage());
				}
			}
		}
	}
}