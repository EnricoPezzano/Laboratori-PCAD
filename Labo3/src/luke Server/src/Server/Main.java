package Server;
import java.util.LinkedList;

public class Main {
	public static void main (String[]args) {
		
		ListaEventi lista=new ListaEventi();
		
		LinkedList <Evento>	eventi = new LinkedList<Evento>();
		
		eventi.add(new Evento(20, "circo"));
		eventi.add(new Evento(100, "partita"));
		eventi.add(new Evento(50, "corsa"));
		
		Server s = new Server(lista, eventi);
		s.serverStart();
		
	}

}