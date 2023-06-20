package com.leon.amps;

import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.FIXBuilder;
import com.crankuptheamps.client.exception.AMPSException;
import com.crankuptheamps.client.exception.CommandException;
import com.crankuptheamps.client.exception.ConnectionException;

public class FIXPublisher
{
	public static void main()
	{
		Client amps = new Client("publisher");
		try
		{
			String topic = "orders";
			amps.connect("tcp://localhost:9007/amps/json?ip_protocol_prefer=ipv6");
			amps.logon();
			FIXBuilder builder = buildFIxMessage();
			amps.publish(topic.getBytes(), 0, topic.length(), builder.getBytes(), 0, builder.getSize());
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

	public static FIXBuilder buildFIxMessage() throws CommandException
	{
		FIXBuilder fixBuilder = new FIXBuilder(1024, (byte) 0x01);
		fixBuilder.append(8, "FIX.4.4");
		fixBuilder.append(35, "D");
		fixBuilder.append(49, "CLIENT");
		fixBuilder.append(56, "SERVER");
		fixBuilder.append(34, "");
		fixBuilder.append(52, "20160101-00:00:00.000");
		fixBuilder.append(11, "1");
		fixBuilder.append(21, "1");
		fixBuilder.append(40, "1");
		fixBuilder.append(54, "1");
		fixBuilder.append(38, "1");
		return fixBuilder;
	}
}
