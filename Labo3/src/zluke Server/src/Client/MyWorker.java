package Client;

import javax.swing.*;

import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MyWorker extends SwingWorker{

	Socket socket;
    JTextArea textstampa;
    String textleggi;
    String in;
    Boolean azione;
	
    public MyWorker(Socket s,  JTextArea textstampa, String textleggi, Boolean azione) {
    	this.socket=s;
        this.textstampa=textstampa;
        this.textleggi=textleggi;
        this.azione=azione;
    }

    protected void done() {
 
		textstampa.setText(in);
	}
    
    @Override
	protected Object doInBackground() throws Exception {
    	PrintWriter outStream;

    	if(azione){
			String comandoStampa="stampa";
			outStream = new PrintWriter(socket.getOutputStream(), true);
			outStream.println(comandoStampa);
			outStream.flush();
		   	BufferedReader inStream=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		   	in=inStream.readLine();
			return null;
	    } 
    	else {
	    	outStream = new PrintWriter(socket.getOutputStream(), true);
	    	System.out.println("Text area: " + textleggi);
			outStream.println(textleggi);
	    	outStream.flush();
	    	BufferedReader inStream=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	in=inStream.readLine();
	    	return null;
    	}
    }
}
