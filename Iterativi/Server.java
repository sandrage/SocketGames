import java.net.*;
import java.io.*;
public class Server{
	public static void main(String[] args){
		try{
		ServerSocket serverSocket=new ServerSocket(0);
		Socket clientSocket;
		boolean finished=false;
		byte[] buffer=new byte[100];
		String letta;
		System.out.println("Server disponibile su "+serverSocket.getInetAddress().getHostAddress()+" sulla porta:"+serverSocket.getLocalPort());
		while(true){
			clientSocket=serverSocket.accept();
			System.out.println("Si è collegato "+clientSocket.getLocalAddress()+" sulla porta: "+clientSocket.getPort());
			InputStream fromClient=clientSocket.getInputStream();
			OutputStream toClient=clientSocket.getOutputStream();
			do{	
				int letti=fromClient.read(buffer);
				if(letti>0){
					letta=new String(buffer,0,letti);
					System.out.println("Ho ricevuto: "+letta);
					if(!letta.equalsIgnoreCase("stop"))
					{
						System.out.println("Sto mandando....");
						toClient.write(letta.getBytes(),0,letti);
					}
					else{
						System.out.println("Termino anche io");
						finished=true;
					}
				}
				else{
					System.out.println("Connessione persa");
					finished=true;
				}
			}while(!finished);
			clientSocket.close();
			finished=false;
		}
	}
	catch(IOException io){
		System.out.println("Errore io: ");
		io.printStackTrace();
		System.exit(1);
		}
	}
}