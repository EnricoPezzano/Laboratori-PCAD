package Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.*;

public class MyListener implements ActionListener {

    Socket socket;
    JTextArea textstampa;
    JLabel textleggi;
    String tex;
    Boolean azione;
   
    public MyListener(Socket s,  JTextArea textstampa, JLabel textleggi, Boolean azione) {
        this.socket=s;
        this.textstampa=textstampa;
        this.textleggi=textleggi;
        this.azione=azione;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
    	tex=textleggi.getText();
        MyWorker m = new MyWorker(socket, textstampa, tex, azione);
		Thread t = new Thread(m);
        t.start();
    }
}