package com.leon.amps;

import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.exception.AMPSException;
import com.crankuptheamps.client.exception.ConnectionException;

public class Subscriber
{
	public static void main()
	{
		Client amps = new Client("subscriber");
		try
		{
			amps.connect("tcp://128.0.0.1:9007/amps/json");
			amps.logon();
			for(Message message : amps.subscribe("orders", "/symbol LIKE 'ABCD'"))
			{
				System.out.println(message.getData());
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
