package GUI;

import java.util.List;
import javax.swing.SwingWorker;

public class MyWorker extends SwingWorker<String, Integer> {
   
   GUI _gui ;

   public MyWorker(GUI gui) {
      _gui = gui;
   }

   @Override
   protected String doInBackground() throws Exception {
      Thread.sleep(500); 
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
   }
}