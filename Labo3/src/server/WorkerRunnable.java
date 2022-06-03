package server;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import eventi.Eventi;

public class WorkerRunnable implements Runnable{

   protected Socket clientSocket = null;
   protected String serverText   = null;
   Eventi ev;
   PrintWriter output = null;
   BufferedReader input = null;
   String msg;
   // this.flag = flag; // 0 prenota, 1 stampa

   public WorkerRunnable(Socket clientSocket, String serverText, Eventi ev) {
      this.clientSocket = clientSocket;
      this.serverText   = serverText;
      this.ev = ev;
   }

   public void run() {
      try {
         input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String line;
         while ((line=input.readLine()) != null){
            if(line.contains("-")){
               String[] prenota = line.split("-");
               if(prenota.length == 3){
                  ev.Prenota(prenota[1], Integer.valueOf(prenota[2]));
                  msg = "prenotazione effettuata";
               }
               else
                  msg = "prenotazione sbagliata! (nomeEvento-postiDaPrenotare)"; 
            }
            else
               msg = "prenotazione sbagliata! (nomeEvento-postiDaPrenotare)"; 
         }

         InputStream input  = clientSocket.getInputStream();
         OutputStream output = clientSocket.getOutputStream();
         long time = System.currentTimeMillis();
         output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " +this.serverText + " - " + time + "").getBytes());
         output.close();
         input.close();
         System.out.println("Request processed time: " + time);
      } catch (IOException e) {
         //report exception somewhere.
         e.printStackTrace();
      }
   }
}