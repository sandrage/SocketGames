import java.io.*;
import java.net.*;
public class ClientMultith{
	public static void main(String[] args){
		Socket clientSocket=new Socket();
		InetSocketAddress serverSocket;

		if(args.length!=2){
			System.out.println("Errore nell'inserimento di porta e/o indirizzo del server.");
			System.exit(1);
		}

		String address= args[0];
		String porta = args[1];
		boolean finished=false;
		byte[] buffer;
		int portaNum;
		try{
			portaNum = Integer.valueOf(porta);
			serverSocket=new InetSocketAddress(address,portaNum);
			clientSocket.connect(serverSocket);
			InputStream fromServer = clientSocket.getInputStream();
			OutputStream toServer = clientSocket.getOutputStream();
			System.out.println("Io client sono collegato a: "+clientSocket.getInetAddress()+" sulla porta:"+clientSocket.getPort());
			BufferedReader buf =new BufferedReader(new InputStreamReader(System.in));
			String quantoLetto;
			do{
				quantoLetto=buf.readLine();
				buffer=quantoLetto.getBytes();
				toServer.write(buffer,0,buffer.length);
				if(!quantoLetto.equalsIgnoreCase("stop")){
					System.out.println("Il server mi ha notificato: "+fromServer.read(buffer)+" :"+new String(buffer,0,buffer.length));

				}else{
					finished=true;
				}

			}while(!finished);
			clientSocket.close();

		}
		catch(NumberFormatException exp){
			System.out.println("Numero di porta non accettabile.");
			System.exit(1);
		}
		catch(IllegalArgumentException illegal){
			System.out.println("Dati errati per formare la socket");
			System.exit(1);
		}
		catch(IOException io){
			System.out.println("Errore nella raccolta dell'input");
			System.exit(1);
		}
	}
}