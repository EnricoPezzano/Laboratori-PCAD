import java.util.HashMap;
import java.util.concurrent.Callable;
import java.lang.*;

public class Eventi implements Callable{
   
   private HashMap ListaEventi = new HashMap<String, Integer>();
   // private String NomeEvento;
   // private Integer PostiDisponibili;
   // private Integer PostiMax;

   public Eventi(String nome, Integer posti){
      // synchronized(this){
      //    // this.NomeEvento = nome;
      //    // this.PostiMax = this.PostiDisponibili = posti;
      // }
   }

   public Integer call(){


      return 0;
   }

   public synchronized void Crea(String NomeEvento, Integer Posti){
      if(ListaEventi.putIfAbsent(NomeEvento, Posti) == null)
         throw new IllegalArgumentException();
   }

   public synchronized void Aggiungi(String NomeEvento, Integer postiDaAggiungere){
      Integer oldValue = (Integer)ListaEventi.get(NomeEvento);
      Integer newValue = sum(ListaEventi.get(NomeEvento), postiDaAggiungere);

      ListaEventi.replace(NomeEvento, oldValue, newValue);
   }

   private Integer sum(Object object, Integer postiDaAggiungere) {
      return (Integer)ListaEventi.get(object) + postiDaAggiungere;
   }

   public void Prenota(String NomeEvento, Integer Posti){
      
   }

   public void ListaEventi(){
      
   }

   public synchronized void Chiudi(String NomeEvento){
      
   }
}