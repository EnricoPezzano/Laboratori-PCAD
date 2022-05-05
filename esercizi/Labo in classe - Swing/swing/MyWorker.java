import javax.swing.*;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ExecutionException;

public class MyWorker extends SwingWorker<String, Integer> {

    public MyWorker() {
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
