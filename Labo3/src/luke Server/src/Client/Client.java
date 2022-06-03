package Client;

import java.io.*;
import java.net.*;

public class Client {
	
	protected static int porta=1024;//porta del server
	
	public static void main (String[]args) {
		try {
			Socket socket=new Socket("localhost",porta);
		    GUI g = new GUI(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}

}
