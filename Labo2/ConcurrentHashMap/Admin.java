package ConcurrentHashMap;

import java.util.Random;

public class Admin implements Runnable{
   public void run() {
      System.out.println("Inizio Admin.");

      for(int i = 0 ; i<Test.numEventi; i++)
         Test.eventi.Crea(Test.data[i], 47+i*3);
         
      System.out.println("\nAdmin: creati tutti gli eventi:");
      Test.eventi.ListaEventi();

      try {
         Thread.sleep(2000);
      } catch (InterruptedException e1) {
         e1.printStackTrace();
      }
      System.out.println("\nAdmin: svegliato dopo i 2 secondi.");

      for(int i = 0 ; i<Test.numEventi; i++) {
         Test.eventi.Aggiungi(Test.data[i], 1+i*7);
         System.out.println("\nAdmin: aggiunti "+1+i*7+" posti.");
      }

      try {
         Thread.sleep(2000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      
      System.out.println("\nAdmin: fine aggiunta dei posti.");

      Test.eventi.ListaEventi();
      
      //chiudo 2 eventi randomicamente
      var r = new Random();
      int randEvent = r.nextInt(Test.numEventi);
      Test.eventi.Chiudi(Test.data[randEvent]);
      Test.eventi.Chiudi(Test.data[randEvent+1]);

      Test.eventi.ListaEventi();
      System.out.println("Fine Admin.");
   }
}