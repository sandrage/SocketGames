
import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args){
		DatagramSocket socket;
		try {
			socket = new DatagramSocket();
			System.out.println("Sono connesso alla porta: "+socket.getLocalPort());
			System.out.println("Sono all'ip address: "+socket.getLocalAddress());
			
			byte[] buffer=new byte[100];
			DatagramPacket packetFromClient=new DatagramPacket(buffer,100), packetToClient;
			SocketAddress address;
			BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
			String toSend;
			while(true){
				socket.receive(packetFromClient);
				String ricevuto=new String(packetFromClient.getData(),0,packetFromClient.getLength());
				System.out.println("Ho ricevuto "+ricevuto);
				address=packetFromClient.getSocketAddress();
				System.out.println("Cosa vogliamo rispondergli?");
				toSend=buf.readLine();
				packetToClient=new DatagramPacket(toSend.getBytes(),toSend.length(),address);
				socket.send(packetToClient);
				System.out.println("Spedito.");
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
