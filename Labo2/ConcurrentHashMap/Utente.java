package ConcurrentHashMap;

// Un thread di tipo UTENTE invia richieste di prenotazione

public class Utente implements Runnable{
   public void run(){
      System.out.println("Inizio utente.");
      try {
         Thread.sleep(1000); // aspetta la creazione degli eventi
      }
      catch (InterruptedException e) {
         e.printStackTrace();
      }

      for(int i = 0 ; i<Test.numEventi; i++)
         Test.eventi.Prenota(Test.data[i], 60);

      System.out.println("Fine utente.");
   }
}
