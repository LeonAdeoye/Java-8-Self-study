package com.leon.amps;

import com.crankuptheamps.client.*;
import com.crankuptheamps.client.exception.AMPSException;
import com.crankuptheamps.client.exception.ConnectionException;
import com.crankuptheamps.client.exception.DisconnectedException;

public class SOWAndSubscriber
{
	public static void main() throws DisconnectedException
	{
		Client amps = new Client("subscriber");
		CommandId commandId = null;
		try
		{
			amps.connect("tcp://localhost:9007/amps/json?ip_protocol_prefer=ipv6");
			amps.logon();

			// Sends the command to the AMPS server.
			MessageStream ms = amps.execute(new Command(Message.Command.SOWAndSubscribe)
					.setTopic("orders-sow"));

			for (Message m : ms)
			{
				if (m.getCommand() == Message.Command.GroupBegin)
				{
					System.out.println("Receiving messages from SOW " +
							"(beginning of group).");
					continue;
				}
				if (m.getCommand() == Message.Command.GroupEnd)
				{
					System.out.println("Finished receiving messages from"
							+" SOW (end of group).");
					continue;
				}
				System.out.println(m.getData());
			}
		}
		catch (ConnectionException e)
		{
			e.printStackTrace();
		}
		catch (AMPSException e)
		{
			e.printStackTrace();
		}
		finally
		{
			amps.close();
		}
	}
}
