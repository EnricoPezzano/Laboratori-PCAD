package Server;

public class Evento {
	
	int numeroPosti;
	String nomeEvento;
	
	public Evento(int n, String s) {
			this.numeroPosti=n;
			this.nomeEvento=s;
	}
	
	public float getPosti() {
		return numeroPosti;
	}
	
	public String getNome() {
		return nomeEvento;
	}

}