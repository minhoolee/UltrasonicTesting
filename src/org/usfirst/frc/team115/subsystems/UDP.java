package org.usfirst.frc.team115.subsystems;

/*
 * Create a UDP server that can accept data from a standard UDP port
 * @author Michael Wan and Min Hoo Lee
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.InetAddress;

public class UDP
{
	private String host;
	private String dataString;
	private int port;
	private int DATAGRAM_LENGTH;

	private DatagramSocket ssocket;
	private DatagramPacket rpacket;
	private byte[] buffer;

	public UDP(String host, int port, int DATAGRAM_LENGTH)
	{
		this.host = host;
		this.port = port;
		this.DATAGRAM_LENGTH = DATAGRAM_LENGTH;

		try
		{
			this.ssocket = new DatagramSocket(this.port);
		}
		catch (SocketException e)
		{
			System.out.println("Incorrect socket");
		}
		this.buffer = new byte[this.DATAGRAM_LENGTH];
		InetAddress ia = null;
		try
		{
			ia = InetAddress.getByName(host);
		}
		catch (IOException e)
		{
			System.out.println("Unable to acquire the IP Address of the host");
		}
		this.rpacket = new DatagramPacket(this.buffer, buffer.length, ia, this.port);
	}

	public UDP() {}
	public UDP(String host) { this(host, 5810, 6400); }
	public UDP(String host, int port) { this(host, port, 6400); }

	public String getString()
	{
		String get = "";
		try
		{
			buffer = new byte[this.DATAGRAM_LENGTH];
			this.rpacket = new DatagramPacket(buffer, DATAGRAM_LENGTH);
			this.ssocket.receive(rpacket);
			get = new String(rpacket.getData());
		}
		catch (Exception e)
		{
			System.out.println("Error receiving package");
			e.printStackTrace();
		}
		return get.trim();
	}
	
	public double getDouble() {
		int ind = dataString.indexOf(" ");
		if (ind == -1) 
			dataString = getString();
		String str = dataString.substring(0, ind);
		dataString = dataString.substring(ind);
		return Double.parseDouble(str);
	}

	public void send(String str)
	{
		DatagramPacket reply;
		reply = new DatagramPacket(str.getBytes(), str.length(), this.rpacket.getAddress(), this.port);
		try
		{
			this.ssocket.send(reply);
		}
		catch (Exception e)
		{
			System.out.println("Error sending package");
			e.printStackTrace();
		}
	}

	public void closeSocket()
	{
		if (this.ssocket != null)
			this.ssocket.close();
	}
}
