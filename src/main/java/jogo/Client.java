package jogo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	private Socket socket;
	private DataOutputStream out;
	private Scanner in;
	private static final String RECONECTAR = "SIM";
	private static final String ENCERRAR = "NÃO";
	
	public Client() {
		in = new Scanner(System.in);
		this.init();
	}
	
	private void init() {
		try {
			socket = new Socket("127.0.0.1", Server.PORT);
			out = new DataOutputStream(socket.getOutputStream());
			writeMessage();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("O Servidor está indisponivel");
			
			
			
//			socket.connect();
//			SocketAddress
//			tratativaErroConexao();
		}
	}

	private void writeMessage() {
		String line = "";
		while (!line.equals(Server.STOP_STRING)) {
			line = in.nextLine();
			try {
				out.writeUTF(line);
			} catch (IOException e) {
				System.out.println("O Servidor está indisponivel");
				tratativaErroConexao();
			}
		}
		close();
	}
	
	private void tratativaErroConexao() {
		System.out.println("Deseja tentar uma reconexão?");
		String line = "";
		line = in.nextLine();
		switch (line) {
		case RECONECTAR:
			System.out.println("Tentando reconexão...");
			reconexão();
			break;
		case ENCERRAR:
			System.out.println("Fechando cliente");
			close();
			System.exit(0);
			break;
		default:
			System.out.println("Fechando cliente");
			System.exit(0);
			break;
		}
	}
	
	private void reconexão(){
		int tentativas = 4;
		boolean reconectado = false;
		for (int i = 1; i <= tentativas; i++) {
			try {
//				socket.
//				socket = new Socket("127.0.0.99", Server.PORT);
				socket.close();
				reconectado = true;
				break;
			} catch (IOException e) {
				System.out.println(i + " Tentativa");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(reconectado) {
			this.init();
		}
		else {
			tratativaErroConexao();
		}
	}

	private void close() {
		try {
			socket.close();
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Client();
	}
}
