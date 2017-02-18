import java.net.*;
import java.io.*;
public class SecondaryServer extends Thread{
		private Socket withClient;
		private int myId;
		public SecondaryServer(Socket s,int idThread){
			this.withClient=s;
			this.myId=idThread;
			start();
		}
		public void run(){
			try{
				InputStream fromClient=withClient.getInputStream();
				OutputStream toClient=withClient.getOutputStream();
				byte[] buffer=new byte[100];
				int numletti;
				String letta;
				boolean finished=false;
				do{
					numletti=fromClient.read(buffer);
					if(numletti >0){
						letta=new String(buffer,0,numletti);
						System.out.println("Io "+myId+" Ho letto dal client: "+letta);
						if(!letta.equalsIgnoreCase("stop")){
							System.out.println("Io "+myId+" gli ho rimandato il messaggio");
							toClient.write(letta.getBytes(),0,letta.length());
						}
						else{
							System.out.println("Io server: "+myId+" ho ricevuto stop");
							finished=true;
						}
					}else{
						System.out.println("Server: "+myId+" L'input stream è stato chiuso.");
						finished=true;
					}
				}while(!finished);
			}
			catch(IOException io){
				System.out.println("Errore nell'input/output stream");
				System.exit(1);
			}
			finally{
				try{
					System.out.println("Sto chiudendo..."+myId);
					withClient.close();
				}
				catch(IOException io){
					System.out.println("Errore chiusura connessione....");
					System.exit(1);
				}
			}
		}

}