package Server;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executors;
public class Server {
	

	ServerSocket serverSocket = null;
	Socket socketClient = null; 
	protected boolean isStopped = false;
	ListaEventi lista;
	LinkedList <Evento>	eventi;
	protected int porta = 1024;
	
	public Server(ListaEventi l, LinkedList <Evento> e) {
		this.lista=l;
		this.eventi=e;
	};
	
	//metodo per la connessione
	public void serverStart() {
		/***IMPLENTO la parte dell'admin in modo che il client possa prenotare degli eventi***/
		for(int i=0; i<eventi.size() ; i++) {
			System.out.println("Creo l'evento " + eventi.get(i).getNome() + " con " + eventi.get(i).getPosti() + " posti");
			lista.crea(eventi.get(i));
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Aggiungo 10 posti all'evento corsa");
		lista.Aggiungi("corsa", 10);
		System.out.println("Aggiungo 5 posti all'evento circo");
		lista.Aggiungi("circo", 5);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		/*-----------------------------------------------------------------------------------*/
		
		var threadPool= Executors.newFixedThreadPool(4);
	
		try {
			System.out.println("Server attivo");
			//attivare server sulla porta in modo chge il client possa mettersi in ascolto sulla porta
			serverSocket = new ServerSocket(this.porta);
			}catch (IOException e) {
				// TODO Auto-generated catch block
				 System.out.println("Could not listen on port");
				e.printStackTrace();
			}
			
		while(!isStopped()){
			Socket clientSocket = null;
			try {
				// quindi inizializziamo servizio
				//dobbiamo dare la possibilità al client di connettersi
				clientSocket = this.serverSocket.accept(); /*.accept mette in ascolto sulla porta che abbiamo aperto, aspetta che 
				qualcuno provi ad utilizzared il servizio. Ritorna una variabile socket del client*/
				
				} catch (IOException e) {
					if(isStopped()) { 
						System.out.println("Server Stopped.");
						return; 
					}
						throw new RuntimeException("Error accepting client connection", e);
				}
			threadPool.execute(new WorkerRunnable(clientSocket, lista));
		} 
			
		System.out.println("Server Stopped");
			
	}

	private synchronized boolean isStopped() {
		return this.isStopped;
	}
	
}
