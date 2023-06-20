package com.leon.amps;

import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.FIXShredder;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.MessageStream;
import com.crankuptheamps.client.exception.AMPSException;
import java.util.Map;

public class FIXSubscriber
{
	public static void main() throws AMPSException
	{
		Client client = new Client("FIXSubscriber");

		try
		{
			// connect to the AMPS server and logon
			client.connect("tcp://localhost:9007/amps/json?ip_protocol_prefer=ipv6");

			// subscribe to the test-topic topic.
			// when a message arrives, print the message.

			MessageStream ms = client.subscribe("orders");
			try
			{
				// Create a shredder -- since this just returns
				// the Map, we can reuse the same shredder.
				FIXShredder shredder = new FIXShredder((byte) 0x01);

				for (Message m : ms)
				{
					// Skip messages with no data.
					if (m.getCommand() != Message.Command.SOW && m.getCommand() != Message.Command.Publish) continue;

					System.out.println("Got a message");

					// Shred the message into a Map
					Map<Integer,CharSequence> fields = shredder.toMap(m.getData());

					// Iterate over the keys in the map and print the key and data
					for (Integer key : fields.keySet())
					{
						System.out.println("  " +key + "=" + fields.get(key));
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				ms.close();
			}
		}
		catch(AMPSException e)
		{
			e.printStackTrace();
		}
		finally
		{
			client.close();
		}
	}
}
