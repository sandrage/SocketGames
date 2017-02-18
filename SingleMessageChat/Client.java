import java.net.*;
import java.io.*;
public class Client {
	public static void main(String[] args){
		if(args.length!=2){
			System.err.println("Il client deve essere lanciato cosi': java Client <host> <porta>");
			System.exit(-1);
		}
		
		String hostName=args[0];
		try{
			int porta = Integer.valueOf(args[1]);
			if(porta<0){
				System.err.println("La porta non puo' essere negativa");
				System.exit(-1);
			}
			
			InetSocketAddress server=new InetSocketAddress(hostName,porta);
			DatagramSocket socket=new DatagramSocket();
			System.out.println("Sono collegato alla porta: "+socket.getLocalPort());
			DatagramPacket toServer, fromServer;
			BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
			String letta=buf.readLine(), lettaFromServer;
			int bufferdim=100;
			byte[] buffer=new byte[bufferdim];
			while(!letta.equals(".")){
				toServer=new DatagramPacket(letta.getBytes(),letta.length(),server);
				socket.send(toServer);
				System.out.println("Spedito!");
				fromServer=new DatagramPacket(buffer,bufferdim);
				socket.receive(fromServer);
				lettaFromServer=new String(fromServer.getData(),0,fromServer.getLength());
				System.out.println("Ho ricevuto dal server: "+lettaFromServer);
				letta=buf.readLine();
			}
			
		}
		catch(NumberFormatException nf){
			System.err.println("Errore formato porta. Riprovate");
			System.exit(-1);
		}catch(IllegalArgumentException ill){
			System.err.println("Porta fuori range");
			System.exit(-1);
		} catch (SocketException e) {
			System.err.println("Errore socket.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Errore io.");
			e.printStackTrace();
		}
	}
}
