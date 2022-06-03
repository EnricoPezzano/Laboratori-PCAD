package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import eventi.Eventi;

public class ThreadPoolServer {
   Eventi ev = new Eventi();

   protected int          serverPort   = 1234;
   protected ServerSocket serverSocket = null;
   protected boolean      isStopped    = false;
   protected Thread       runningThread= null;
   protected ExecutorService threadPool = Executors.newFixedThreadPool(10);
   protected int port = 1234;

   public ThreadPoolServer(int port, Eventi ev){
      this.serverPort = port;
      this.ev = ev;
   }

   public void start(){
      // synchronized(this){
      //    this.runningThread = Thread.currentThread();
      // }
      ev.Crea("poolParty2", 20);
      ev.Crea("natale2", 20);
      ev.Crea("compleanno2", 20);

      ev.Aggiungi("poolParty", 10);
      ev.Aggiungi("compleanno", 10);
      ev.Aggiungi("natale", 10);

      try {
			Thread.sleep(1000);
			// serverSocket = new ServerSocket(this.port);
		}
      catch(InterruptedException e1){
			e1.printStackTrace();
		}

      openServerSocket();
      System.out.println("Server attivato");
		//attivo il server sulla porta 1234 in modo chge il client possa mettervisi in ascolto

      while(!isStopped()){
         Socket clientSocket = null;
         try {
            clientSocket = this.serverSocket.accept();
         }
         catch (IOException e) {
            if(isStopped()) {
               System.out.println("Server Stopped.") ;
               break;
            }
            throw new RuntimeException(
               "Error accepting client connection", e);
         }
         this.threadPool.execute(new WorkerRunnable(clientSocket, "Thread Pooled Server <3", ev));
      }
      this.threadPool.shutdown();
      System.out.println("Server Stopped.") ;
   }

   private synchronized boolean isStopped() {
      return this.isStopped;
   }

   public synchronized void stop(){
      this.isStopped = true;
      try {
         this.serverSocket.close();
      } catch (IOException e) {
         throw new RuntimeException("Error closing server", e);
      }
   }

   private void openServerSocket() {
      try {
         this.serverSocket = new ServerSocket(this.serverPort);
      } catch (IOException e) {
         throw new RuntimeException("Cannot open port 8080", e);
      }
   }

   public String ServerPrints(){
      return ev.ListaEventi();
   }

   public void ServerBooks(String eventoDaPrenotare, int postiDaPrenotare){
      ev.Prenota(eventoDaPrenotare, postiDaPrenotare);
   }
}