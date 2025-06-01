package jogo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {
	
	private ServerSocket server;
	public static final int PORT = 3030;
	public static final String STOP_STRING = "##";
	public static final String BLOCK_CLIENTS = "BB";
	public static final String ALLOW_CLIENTS = "AA";
	public static final String SHUTDOWN = "XX";
	private Scanner scn;
	public AtomicBoolean clientsFlag = new AtomicBoolean();
	
	public Server() {
		System.out.println("SERVIDOR INICIADO");
		clientsFlag.set(true);
		try {
			scn = new Scanner(System.in);
			server = new ServerSocket(PORT);
			
			
			new Thread(()->{
				String line = "";
				boolean firstTypped = true;
				while (true) {
					if(!firstTypped) {
						System.out.println("Servidor ->");
						line = scn.nextLine();						
					}
					switch (line) {
					case BLOCK_CLIENTS:
						clientsFlag.set(false);
						System.out.println("PROCEDURE DE BLOQUEAMENTO DE CLIENTES");
						break;
					case ALLOW_CLIENTS:
						clientsFlag.set(true);
						System.out.println("PROCEDURE DE LIBERAMENTO DE CLIENTES");
						break;
					case SHUTDOWN:
						System.out.println("ENCERRANDO SERVIDOR...");
						System.exit(0);
						break;
					}
					firstTypped = false;
				}
				
			}).start();	
			System.out.println("INICIANDO ABERTURA DE CONEXOES");
			while (clientsFlag.get()) {
				iniConnections();	
			}
			System.out.println("FINALIZANDO ABERTURA DE CONEXOES");
		} catch (IOException e) {
			System.out.println("ERRO SERVIDOR");
			e.printStackTrace();
		}
		System.out.println("SERVIDOR FECHADO");
		
	}

	private void iniConnections() throws IOException {
		System.out.println("AGUARDANDO CLIENTE... <->");
		Socket clientSocket = server.accept();
		System.out.println("ESCUTANDO PORTAS");
		if(clientSocket.isConnected()) {
			new Thread(()->{
				System.out.println("Cliente login");
				ConnectedClient client = new ConnectedClient(clientSocket);
				client.readMessage(clientsFlag);
				client.close();
				System.out.println("Cliente logoff");
			}).start();			
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}

}
