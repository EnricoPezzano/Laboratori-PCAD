import java.util.ArrayList;
// import java.util.concurrent;

public class Eventi implements Runnable{

   private ArrayList<Evento> ListaEventi;

   public class Evento{
      private final String NomeEvento;
      private int PostiOccupati;
      private int PostiMax;
      private boolean isDone;

      public Evento(String nome, int posti){
         this.NomeEvento = nome;
         this.PostiMax = posti;
      }

      public String getNome(){ return this.NomeEvento; }

      public int getDisponibili(){ return this.PostiMax-this.PostiOccupati; }

      public void addPeople(int postiDaPrenotare) throws InterruptedException {
         while(postiDaPrenotare <= getDisponibili() && !isDone)
            this.wait(); // ??

         if(isDone)
            error("Evento terminato :(");

         this.PostiOccupati += postiDaPrenotare;
      }

      

   } // end class Evento

   public void run(){
      // 
   }

   public void error(String message){
      System.err.println(message);
      System.exit(-1);
   }

   public synchronized void Crea(String NomeEvento, int PostiTot){
      for(Evento e : ListaEventi)
         if(e.getNome().equals(NomeEvento)) // se c'è già...
            error("L'evento "+NomeEvento+" esiste già.");

      ListaEventi.add(new Evento(NomeEvento, PostiTot));
   }

   public synchronized void Aggiungi(String NomeEvento, int postiDaAggiungere){
      for(Evento e : ListaEventi)
         if(e.getNome().equals(NomeEvento)){
            if(e.PostiMax <= 0)
               throw new IllegalStateException();

            e.PostiMax += postiDaAggiungere;
         }
      error("L'evento digitato non esiste.");
   }

   public void Prenota(String NomeEvento, int postiDaPrenotare) throws InterruptedException{
      for(Evento e : ListaEventi)
         if(e.getNome().equals(NomeEvento)){
            e.addPeople(postiDaPrenotare);
            break;
         }
   
      error("L'evento digitato non esiste.");
   }

   public void ListaEventi(){
      for(Evento e : ListaEventi)
         System.out.println("Evento: "+e.getNome()+"\t Posti disponibili: "+e.getDisponibili());
   }

   public synchronized void Chiudi(String NomeEvento){
      for(Evento e : ListaEventi)
         if(e.getNome().equals(NomeEvento)){
            ListaEventi.remove(e);
            break;
         }

      error("L'evento digitato non esiste.");
   }
}