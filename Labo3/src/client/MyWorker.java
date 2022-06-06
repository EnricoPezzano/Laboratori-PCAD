package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import javax.swing.SwingWorker;

public class MyWorker extends SwingWorker<String, Integer> {
   
   GUI _gui;
   boolean flag;
   Socket socket;
   String eventoDaPrenotare;
   int postiDaPrenotare;


   public MyWorker(GUI gui, Socket socket, String eventoDaPrenotare, int postiDaPrenotare, boolean flag) {
      _gui = gui;
      this.flag = flag; // 0 prenota, 1 stampa
      this.socket = socket;
      this.eventoDaPrenotare = eventoDaPrenotare;
      this.postiDaPrenotare = postiDaPrenotare;
   }

   @Override
   protected String doInBackground() throws Exception {
      PrintWriter outStream;

      if(flag){
         String cmd = "stampa";
         outStream = new PrintWriter(socket.getOutputStream(), true);
         outStream.println(cmd);
         outStream.flush();

         BufferedReader inStream = new BufferedReader((new InputStreamReader(socket.getInputStream())));
         inStream.readLine();
         return null;
      }
      else{
         outStream = new PrintWriter(socket.getOutputStream(), true);
         System.out.println("Area di testo: " + eventoDaPrenotare);
         outStream.flush();

         BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         inStream.readLine();
         return null;
      }
      // return "Done!";
   }
    
   @Override
   protected void process(List<Integer> chunks) {
   }

   @Override
   protected void done() {
   //you can query status inside the implementation of this method to determine the result of this task or whether this task has been cancelled
   
      _gui.buttonPrenota.setEnabled(true);
      _gui.buttonStampa.setEnabled(true);
      // eventoDaPrenotare.setText(in);
   }
}