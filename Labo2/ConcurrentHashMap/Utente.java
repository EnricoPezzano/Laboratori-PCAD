package ConcurrentHashMap;

// Un thread di tipo UTENTE invia richieste di prenotazione

public class Utente implements Runnable{
   public void run(){
      System.out.println("Inizio utente.");
      try {
         System.out.println("utente: dormo.");
         Thread.sleep(1000); // aspetta la creazione degli eventi
      }
      catch (InterruptedException e) {
         e.printStackTrace();
      }
      System.out.println("\nutente sveglio...");

      for(int i = 0 ; i<Test.numEventi; i++){
         System.out.println("\nutente: provo a prenotare.");
         Test.eventi.Prenota(Test.data[i], 60);
      }

      System.out.println("Fine utente.");
   }
}
