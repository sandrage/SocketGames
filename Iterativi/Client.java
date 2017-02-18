import java.net.*;
import java.io.*;
public class Client{
	public static void main(String[] args){
		String hostName,letta;
		int port;
		boolean finished=false;
		byte[] buffer;
		if(args.length != 2){
			System.out.println("Errore inserimento host e/o porta");
			System.exit(1);
		}
		hostName=args[0];
		try{
			port=Integer.valueOf(args[1]);
			if(port<0){
				System.out.println("Porta inserita erroneamente.");
				System.exit(1);
			}

			Socket clientSocket=new Socket();
			InetSocketAddress socketServer=new InetSocketAddress(hostName,port);
			clientSocket.connect(socketServer);
			InputStream fromServer=clientSocket.getInputStream();
			OutputStream toServer=clientSocket.getOutputStream();
			System.out.println("Ora sono connesso a :"+clientSocket.getInetAddress().getHostAddress()+" sulla porta:"+clientSocket.getPort());
			BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
			do{
				letta=buf.readLine();
				buffer=letta.getBytes();
				if(!clientSocket.isClosed()){
					toServer.write(buffer,0,letta.length());
				if(!letta.equalsIgnoreCase("stop")){
					int letti=fromServer.read(buffer);
					if(letti>0){
						letta=new String(buffer,0,letti);
						System.out.println("Ho ricevuto dal server:"+letta);

					}else{
						System.out.println("Connection lost.");
						finished=true;
					}
				}
				else{
					System.out.println("Ricevuto stop.");
					finished=true;
				}
			}
			else{
				System.out.println("Connessione persa");
				finished=true;
			}

			}while(!finished);
			clientSocket.close();
			}
		catch(NumberFormatException nf){
			System.out.println("Porta errata");
			System.exit(1);
		}

		catch(IOException io){
			System.out.println("Errore io");
			System.exit(1);
		}

	}
}