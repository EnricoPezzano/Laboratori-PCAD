package ConcurrentHashMap;

import java.util.Random;

// Un thread di tipo UTENTE invia richieste di prenotazione

public class Utente implements Runnable{
   public void run(){
      System.out.println("Inizio utente.");
      // while(true){ // thread utenti 1 e 2
         try {
            Thread.sleep(1000);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }
         var r = new Random();
         int randEvent = r.nextInt(Test.numEventi);

         Test.eventi.Prenota(Test.data[randEvent], 1+randEvent*21);
         System.out.println("\nEvento "+Test.data[randEvent]+" prenotato. Posti prenotati: "+1+randEvent*3);

         System.out.println("Fine utente.");
      // } // while(1)
   }
}
