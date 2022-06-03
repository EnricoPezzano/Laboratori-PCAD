import java.io.*;
import java.net.*;
import GUI.GUI;

public class Client {
	public static void main(String[] args)
	{
		// establish a connection by providing host and port number
		try (Socket socket = new Socket("localhost", 1234)) {
         GUI gui = new GUI(socket);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}