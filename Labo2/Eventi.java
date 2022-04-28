import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Eventi implements Callable{
   private ConcurrentHashMap <K, V> cmap;
   // ConcurrentHashMap<K, V> chm = new ConcurrentHashMap<>(Map m);
   // cmap = new ConcurrentHashMap<String,AtomicInteger>(NomeEvento, Posti);
   private String NomeEvento;
   // private AtomicInteger Posti;
   private AtomicInteger Posti;

   public Eventi(String nome, AtomicInteger posti){
      this.cmap = new ConcurrentHashMap<>(Map m);
      this.NomeEvento = nome;
      this.Posti = posti;
   }

   public Integer call(){


      return 0;
   }

   public void Crea(String NomeEvento, int Posti){
      
   }

   public void Aggiungi(String NomeEvento, int Posti){
      
   }

   public void Prenota(String NomeEvento, int Posti){
      
   }

   public void ListaEventi(){
      
   }

   public void Chiudi(String NomeEvento){
      
   }
}