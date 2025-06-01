package jogo;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectedClient {
	
	private Socket clientSocket;
	private DataInputStream in;
	public PrintWriter out;
	
	public ConnectedClient(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			this.in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readMessage(AtomicBoolean clientsFlag, Set<PrintWriter> clientes) {
		String line  = "";
		while(!line.equals(Server.STOP_STRING) && clientsFlag.get()) {
			try {
				line = in.readUTF();
				for (PrintWriter printWriter : clientes) {
					if(printWriter != this.out) {
						printWriter.println(line);
					}
				}
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
