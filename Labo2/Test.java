import java.util.Random;

public class Test extends Thread{
   public final static int numEventi = 5;
   public final static String[] data = { "poolParty", "christamnParty", "pasqua", "compleanno", "barmitzvah" };
   public static Eventi eventi = new Eventi();

   public static void main(String[] args) throws InterruptedException
   {
      Thread admin = new Thread(new Runnable(){
			public void run() {
            while(true) {
               System.out.println("\nInizio Admin.");
               eventi.ListaEventi();
               
               for(int i = 0 ; i<numEventi; i++) {
                  try {      
                     eventi.Crea(data[i], 1+i*47);
                     Thread.sleep(1000);
                     
                     eventi.Aggiungi(data[i], i+7);
                     Thread.sleep(1000);
                     
                  }
                  catch (InterruptedException e) {
                     e.printStackTrace();
                  }
               }
               
               eventi.ListaEventi();
      
               //chiusura evento random
               var r = new Random();
               int randEvent = r.nextInt(numEventi);
               eventi.Chiudi(data[randEvent]);
               System.out.println("\nFine Admin.");
               eventi.ListaEventi();
            }
			}
		});

      Thread utente1 = new Thread();
      Thread utente2 = new Thread();

      admin.start();
      utente1.start();
      utente2.start();

      admin.join();
      utente1.join();
      utente2.join();
   }
}