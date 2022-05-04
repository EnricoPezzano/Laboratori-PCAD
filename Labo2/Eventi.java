import java.util.ArrayList;
import java.util.Random;

public class Eventi implements Runnable{

   public ArrayList<Evento> ListaEventi;

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
         while(postiDaPrenotare >= getDisponibili() && !isDone){
            try{
               this.wait();

               if(postiDaPrenotare <= getDisponibili() && !isDone)
                  notifyAll();

               if(isDone)
                  error("Evento terminato :(");

               this.PostiOccupati += postiDaPrenotare;
            }
            catch(InterruptedException e){
               return;
            }
         }
      }

   } // end class Evento

   public void run(){
      while(true){ // thread utenti 1 e 2
         try {
            var r = new Random();
            int randEvent = r.nextInt(Test.numEventi);
            this.Prenota(Test.data[randEvent], randEvent*21);
            System.out.println("\nFine.");
            this.ListaEventi();
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
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

      // manca "sblocca tutti i clienti in attesa di posti"

      for(Evento e : ListaEventi)
         if(e.getNome().equals(NomeEvento)){
            ListaEventi.remove(e);
            break;
         }

      error("L'evento digitato non esiste.");
   }
}