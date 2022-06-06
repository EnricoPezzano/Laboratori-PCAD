package Server;
public class Utente implements Runnable {

	ListaEventi lista;
	String s;
	int n;
	
	public Utente(ListaEventi l, String s, int n) {
		this.lista=l;
		this.s=s;
		this.n=n;
	}
	
	@Override
	public void run() {
		System.out.println("Prenoto l'evento " + s + " per " + n + " posti");
		lista.Prenota(s, n);
	}

}