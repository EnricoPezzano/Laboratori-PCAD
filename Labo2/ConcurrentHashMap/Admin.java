package ConcurrentHashMap;

import java.util.Random;

public class Admin implements Runnable{
   public void run() {
      while(true) {
         System.out.println("Inizio Admin.");

         for(int i = 0 ; i<Test.numEventi; i++)
            Test.eventi.Crea(Test.data[i], 1+i*47);
         System.out.println("\nAdmin: creati tutti gli eventi:");
         Test.eventi.ListaEventi();

         try {
            Thread.sleep(2000);
         } catch (InterruptedException e1) {
            e1.printStackTrace();
         }
         System.out.println("svegliato dopo i 2 secondi.");

         for(int i = 0 ; i<Test.numEventi; i++) {
            Test.eventi.Aggiungi(Test.data[i], i+7);
            System.out.println("\nAdmin: aggiunti "+i+7+" posti.");
         }

         try {
            Thread.sleep(2000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         
         System.out.println("\npippo Admin.");

         Test.eventi.ListaEventi();
         
         //chiusura evento random
         var r = new Random();
         int randEvent = r.nextInt(Test.numEventi);
         Test.eventi.Chiudi(Test.data[randEvent]);
         Test.eventi.Chiudi(Test.data[randEvent+1]);

         System.out.println("\nFine Admin.");
         Test.eventi.ListaEventi();
      }
   }
}