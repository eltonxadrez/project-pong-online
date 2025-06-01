package jogo;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectedClient {
	
	private Socket clientSocket;
	private DataInputStream in;
	
	public ConnectedClient(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			this.in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readMessage(AtomicBoolean clientsFlag) {
		String line  = "";
		while(!line.equals(Server.STOP_STRING) && clientsFlag.get()) {
			try {
				line = in.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Client -> " + line);
		}
	}

	public void close() {
		try {
			clientSocket.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
