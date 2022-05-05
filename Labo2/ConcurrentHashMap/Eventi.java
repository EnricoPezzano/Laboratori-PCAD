package ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

public class Eventi{

   public ConcurrentHashMap<String, Evento> ListaEventi = new ConcurrentHashMap<String, Evento>();

   public class Evento{
      private final String NomeEvento;
      private int PostiOccupati;
      private int PostiMax;
      // private boolean isDone;

      public Evento(String nome, int posti){
         this.NomeEvento = nome;
         this.PostiMax = posti;
         // this.isDone = false;
      }

      public String getNome(){ return this.NomeEvento; }

      public int getDisponibili(){ return this.PostiMax-this.PostiOccupati; }

      public synchronized Evento addPeople(int postiDaAggiungere) throws InterruptedException {
         while(postiDaAggiungere <= getDisponibili() && ListaEventi.containsKey(NomeEvento)){
            try{
               System.out.println("\naddPeople: sto aspettando.");
               wait();
            }
            catch(InterruptedException e){
               return null;
            }
         }

         System.out.println("\naddPeople: non sto più aspettando.");

         if(postiDaAggiungere <= getDisponibili() && ListaEventi.containsKey(NomeEvento))
            notifyAll();

         if(!ListaEventi.containsKey(NomeEvento))
            error("error: Evento terminato.");

         this.PostiOccupati += postiDaAggiungere;

         return this;
      }

      public synchronized Evento bookPeople(int postiDaPrenotare) throws InterruptedException {
         while(postiDaPrenotare >= getDisponibili() && ListaEventi.containsKey(NomeEvento)){
            try{
               System.out.println("\nbookPeople: sto aspettando.");
               wait();
            }
            catch(InterruptedException e){
               return null;
            }
         }

         System.out.println("\nbookPeople: non sto più aspettando.");

         if(postiDaPrenotare <= getDisponibili() && ListaEventi.containsKey(NomeEvento))
            notifyAll();

         if(!ListaEventi.containsKey(NomeEvento))
            error("error: Evento terminato.");

         this.PostiOccupati += postiDaPrenotare;

         return this;
      }

   } // end class Evento

   public void error(String message){
      System.err.println(message);
      System.exit(-1);
   }

   public synchronized void Crea(String NomeEvento, int PostiTot){
      Evento e = new Evento(NomeEvento, PostiTot);
      
      if(ListaEventi.containsKey(NomeEvento))
         error("L'evento "+NomeEvento+" esiste già.");

      ListaEventi.put(NomeEvento, e);

      System.out.println("Evento "+NomeEvento+" creato.");
   }

   public synchronized void Aggiungi(String NomeEvento, int postiDaAggiungere){
      if(ListaEventi.get(NomeEvento).PostiMax <= 0)
         throw new IllegalStateException("postiMax <= 0");

      if(!ListaEventi.containsKey(NomeEvento))
         error("Aggiungi: L'evento "+NomeEvento+" non esiste.");
      
      ListaEventi.compute(NomeEvento, (key, val) -> {
         try {
            return val.addPeople(postiDaAggiungere);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }
         return val;
      });
         
   }

   public void Prenota(String NomeEvento, int postiDaPrenotare){
      if(ListaEventi.get(NomeEvento).PostiMax <= 0)
         throw new IllegalStateException("postiMax <= 0");

      if(!ListaEventi.containsKey(NomeEvento))
         error("Prenota: L'evento "+NomeEvento+" non esiste.");
      
      ListaEventi.compute(NomeEvento, (key, val) -> {
         try {
            return val.addPeople(postiDaPrenotare);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }
         return val;
      });
   }

   public void ListaEventi(){
   //    ListaEventi.entrySet().forEach(entry -> {
   //       System.out.println("Evento: "+entry.getKey()+"\t Posti disponibili: "+entry.getValue());
   //   });

      System.out.println("\n");
      for (String key: ListaEventi.keySet()) {
         // String key = s.toString();
         int value = ListaEventi.get(key).getDisponibili();
         System.out.println("Evento: "+key+"\t Posti disponibili: "+value);
     }
   }

   public synchronized void Chiudi(String NomeEvento){
      if(!ListaEventi.containsKey(NomeEvento))
         error("Chiudi: L'evento "+NomeEvento+" non esiste.");

      ListaEventi.remove(NomeEvento);
      System.out.println("\nChiudi: evento "+NomeEvento+" chiuso.");
   }
}