package Server;
import java.io.*;
import java.net.*;

public class WorkerRunnable implements Runnable {
	ListaEventi lista;
	protected Socket clientSocket = null; 
	PrintWriter output=null;
	BufferedReader input=null;
	String azione;
 
	public WorkerRunnable(Socket clientSocket, ListaEventi lista) { 
		this.clientSocket = clientSocket;
		this.lista=lista;
 }
	
	public void run() {
		try {
			input = new BufferedReader(new  InputStreamReader(clientSocket.getInputStream()));
			String line;
			
			while((line=input.readLine())!=null) {
				if(line.contains("-")) {					//se l'input contiene il separatore -
					String[] prenota = line.split("-");		//divido l'input
					if(prenota[0].equals("prenota")) {
						if(prenota.length==3) {
							lista.Prenota(prenota[1], Integer.valueOf(prenota[2]));
							azione="Evento prenotato";
						} else {
							azione="Formato non corretto scrivere: prenota-nomeevento-postidaprenotare";
						}
					}else {
						azione="Formato non corretto scrivere: prenota-nomeevento-postidaprenotare";
					}
				}
				 else if(line.equals("stampa")) {
					azione=lista.listaEventi();
				} else {
					System.out.println("Errore");
					break;
				}
			
				output = new PrintWriter (this.clientSocket.getOutputStream(), true);
				output.println(azione);
			}
		   
		} catch (IOException e) {
			//report exception somewhere.
			e.printStackTrace(); 
		}
		
		try {
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.close();
			}
			this.clientSocket.close();
			System.err.println("Lost connection to " + this.clientSocket.getRemoteSocketAddress());
		} 
		catch (IOException ioe2) {
			// Ignored
		}
	}
}