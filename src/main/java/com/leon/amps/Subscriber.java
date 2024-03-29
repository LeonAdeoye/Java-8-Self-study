package com.leon.amps;

import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.Command;
import com.crankuptheamps.client.CommandId;
import com.crankuptheamps.client.exception.AMPSException;
import com.crankuptheamps.client.exception.ConnectionException;
import com.crankuptheamps.client.exception.DisconnectedException;
import static java.lang.Thread.sleep;

public class Subscriber
{
	public static void main() throws DisconnectedException
	{
		Client amps = new Client("subscriber");
		CommandId commandId = null;
		try
		{
			amps.connect("tcp://localhost:9007/amps/json?ip_protocol_prefer=ipv6");
			amps.logon();
			Command subscribe = new Command("subscribe").setTopic("orders").setFilter("/id = 1");
			commandId = amps.executeAsync(subscribe, (message) -> System.out.println(message.getData()));
			sleep(10000);
		}
		catch (ConnectionException e)
		{
			e.printStackTrace();
		}
		catch (AMPSException e)
		{
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			amps.unsubscribe(commandId);
			amps.close();
		}
	}
}
