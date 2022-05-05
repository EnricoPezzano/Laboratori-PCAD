package ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

public class Eventi{

   public ConcurrentHashMap<String, Evento> ListaEventi = new ConcurrentHashMap<String, Evento>();

   public class Evento{
      private final String NomeEvento;
      private int PostiOccupati;
      private int PostiMax;
      private boolean isDone;

      public Evento(String nome, int posti){
         this.NomeEvento = nome;
         this.PostiMax = posti;
         this.isDone = false;
      }

      public String getNome(){ return this.NomeEvento; }

      public int getDisponibili(){ return this.PostiMax-this.PostiOccupati; }

      public synchronized Evento addPeople(int postiDaAggiungere) throws InterruptedException {
         while(postiDaAggiungere >= getDisponibili() && !this.isDone){
            try{
               wait();

               if(postiDaAggiungere <= getDisponibili() && !this.isDone)
                  notifyAll();

               if(isDone)
                  error("error: Evento terminato.");

               this.PostiOccupati += postiDaAggiungere;
            }
            catch(InterruptedException e){
               return null;
            }
         }
         return this;
      }

      public synchronized Evento bookPeople(int postiDaPrenotare) throws InterruptedException {
         while(postiDaPrenotare >= getDisponibili() && !this.isDone){
            try{
               wait();

               if(postiDaPrenotare <= getDisponibili() && !this.isDone)
                  notifyAll();

               if(isDone)
                  error("error: Evento terminato.");

               this.PostiOccupati += postiDaPrenotare;
            }
            catch(InterruptedException e){
               return null;
            }
         }
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

      System.out.println("\nEvento "+NomeEvento+" creato.");
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
   }
}