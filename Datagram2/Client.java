import java.net.*;
import java.io.*;
public class Client{
	
	public static void main(String[] args){
		boolean finished=false;
		String letta, hostName, ricevuta;
		int port;
		byte[] bytes;
		if(args.length!=2){
			System.out.println("Errore inserimento host/porta ");
			System.exit(1);
		}
		hostName=args[0];
		port=Integer.valueOf(args[1]);
		if(port<0){
			System.out.println("Porta non accettabile");
			System.exit(1);
		}
		try{
			DatagramSocket clientSocket=new DatagramSocket();
			System.out.println("Ho aperto una connessione su:"+clientSocket.getLocalPort()+" e il mio indirizzo e': "+clientSocket.getLocalAddress());
			InetSocketAddress serverSocket=new InetSocketAddress(hostName,port);

			BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
			do{
				letta=buf.readLine();
				bytes=letta.getBytes();
				DatagramPacket pack=new DatagramPacket(bytes,bytes.length,serverSocket);
				clientSocket.send(pack);
				System.out.println("Ho mandato: "+letta+" a:"+pack.getAddress()+" sulla porta:"+pack.getPort());
				
				if(letta.equalsIgnoreCase("stop")){
					finished=true;
				}

			}while(!finished);
			clientSocket.close();
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