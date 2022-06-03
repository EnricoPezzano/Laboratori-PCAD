package GUI;

import java.net.Socket;
import java.util.List;
import javax.swing.SwingWorker;
import eventi.Eventi;

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
      String eventoDaPrenotare = _gui.textField.getText();
      int postiDaPrenotare = Integer.valueOf(_gui.seatsField.getText());

      Eventi ev = new Eventi();

      if(this.flag == 1)
         _gui.textArea.setText(ev.ListaEventi());
      else
         ev.Prenota(eventoDaPrenotare, postiDaPrenotare);

      return "Done!";
   }
    
   @Override
   protected void process(List<Integer> chunks) {
   }

   @Override
   protected void done() {
   //you can query status inside the implementation of this method to determine the result of this task or whether this task has been cancelled
   
   //   _gui.counter += 1;
   //   _gui.statusLabel.setText(Integer.toString(_gui.counter));
      _gui.prenota.setEnabled(true);
      _gui.stampa.setEnabled(true);
   }
}