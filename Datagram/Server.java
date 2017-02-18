package Datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
	public static void main(String[] args){
		try {
			DatagramSocket socketServer=new DatagramSocket(5000);
			byte[] buffer=new byte[100];
			DatagramPacket pack=new DatagramPacket(buffer,buffer.length);
			socketServer.receive(pack);
			String letta=new String(buffer,0,pack.getLength());
			
			System.out.println("Ho letto: "+letta+" da: "+pack.getAddress()+" sulla porta:"+pack.getPort());
			socketServer.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
