package Datagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Client {
	public static void main(String[] args){
		DatagramSocket clientSocket;
		DatagramPacket clientPack;
		InetAddress serverAddress;
		InetSocketAddress serverSocket;
		BufferedReader buf;
		String letta;
		try {
			clientSocket=new DatagramSocket();
			buf=new BufferedReader(new InputStreamReader(System.in));
			letta=buf.readLine();
			clientPack=new DatagramPacket(letta.getBytes(), letta.length());
			serverAddress=InetAddress.getLocalHost();
			serverSocket=new InetSocketAddress(serverAddress,5000);
			clientPack.setSocketAddress(serverSocket);
			clientSocket.send(clientPack);
			
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
