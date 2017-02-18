import java.net.*;
import java.io.*;
public class Server{
	public static void main(String[] args){
		try{
			DatagramSocket socketServer=new DatagramSocket();
			byte[] buffer=new byte[100];
			String ricevuta;
			System.out.println("I'm the server: "+socketServer.getLocalAddress()+" connected on port:"+socketServer.getLocalPort());
			DatagramPacket pack=new DatagramPacket(buffer,buffer.length);
			while(true){
				socketServer.receive(pack);
				ricevuta=new String(pack.getData(),0,pack.getLength());
				System.out.println("I received "+ricevuta+" from "+pack.getAddress()+" on port:"+pack.getPort());
				if(ricevuta.equalsIgnoreCase("stop")){
					break;
				}

			}
			socketServer.close();
		}
		catch(SocketException se){
			System.out.println("Errore socket");
			System.exit(1);
		}
		catch(IOException io){
			System.out.println("Errore io");
			System.exit(1);
		}
	}
}