import java.net.*;
import java.io.*;
public class PrimaryServer{
	public static void main(String[] args){
		ServerSocket serverSocket;
		try{
			serverSocket=new ServerSocket(0);
			Socket withClient;
			int idThread=1;
			System.out.println("Sono sull'host: "+serverSocket.getInetAddress().getHostName()+" sulla porta: "+serverSocket.getLocalPort());
			while(true){
				withClient=serverSocket.accept();
				SecondaryServer littleserver = new SecondaryServer(withClient,idThread);
				System.out.println("Primary server sta incrementando il numero di thread");
				idThread++;
			}
			
		}
		catch(IOException io){
			System.out.println("Errore input/output stream");
			System.exit(1);
		}
	}
}