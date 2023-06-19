package com.leon.amps;

import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.exception.AMPSException;
import com.crankuptheamps.client.exception.ConnectionException;

public class Publisher
{
	public static void main()
	{
		Client amps = new Client("publisher");
		try
		{
			amps.connect("tcp://localhost:9007/amps/json?ip_protocol_prefer=ipv6");
			amps.logon();
			amps.publish("orders", "{\"symbol\":\"ABCD\",\"price\":100.0,\"quantity\":1000}");
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
			amps.disconnect();
			amps.close();
		}
	}
}
