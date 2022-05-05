import java.util.List;
import javax.swing.SwingWorker;

public class MyWorker extends SwingWorker<String, Integer> {

   public MyWorker(GUI gui) {
      //
   }


   @Override
   protected String doInBackground() throws Exception {
      Thread.sleep(1000); 
      return "Done!";
   }
   
   @Override
   protected void process(List<Integer> chunks) {
   }

   @Override
   protected void done() {

   }

}