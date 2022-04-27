import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Erastotene {
   void crivello(int n)
   {
       // Create a boolean array "prime[0..n]" and initialize
       // all entries it as true. A value in prime[i] will
       // finally be false if i is Not a prime, else true.
       boolean prime[] = new boolean[n+1];
       for(int i=0;i<=n;i++)
           prime[i] = true;
        
       for(int p = 2; p*p <=n; p++)
       {
           // If prime[p] is not changed, then it is a prime
           if(prime[p] == true)
           {
               // Update all multiples of p
               for(int i = p*p; i <= n; i += p)
                   prime[i] = false;
           }
       }
        
       // Print all prime numbers
       for(int i = 2; i <= n; i++)
       {
           if(prime[i] == true)
               System.out.print(i + " ");
       }
   }
   
   public static void main(String args[])
   {
      int n = Integer.parseInt(args[0]);

      Task crivello = new Erastotene("thread1");
      // Task task2 = new Task("thread2");
      System.out.println("Starting threads");
      ExecutorService threadExecutor = Executors.newCachedThreadPool();
      threadExecutor.execute(crivello);
      // threadExecutor.execute(task2);
      threadExecutor.shutdown();
      System.out.println("Threads started, main ends\n");

      System.out.print("Following are the prime numbers until smaller than or equal to " + n);
      Erastotene g = new Erastotene();
      g.crivello(n);
   }
}