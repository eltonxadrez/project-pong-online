package game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private EstadoJogoAtual estadoJogoAtual;
	public static final int PORT = 12345;
	
	public Client(EstadoJogoAtual estadoJogoAtual) {
		this.estadoJogoAtual = estadoJogoAtual;
		this.init();
	}
	
	private void init() {
		try {
			socket = new Socket("127.0.0.1", PORT);
			
			//ENVIAR
			this.out = new ObjectOutputStream(socket.getOutputStream());
			
			//RECEBER
			this.in = new ObjectInputStream(socket.getInputStream());
			//RECEBIMENTO DE INFORMAÇÃO
	        new Thread(() -> {
	        	while (true) {
					try {
						DadosJogoPong dadosRecebidos;
						dadosRecebidos = (DadosJogoPong) in.readObject();
						estadoJogoAtual.updateAll(dadosRecebidos);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}
	        }).start();
//	        new Thread(() -> {
//	        	while (true) {
//
//	        	}
//	        }).start();
		} catch (IOException e1) {
			System.out.println("Conexao recusada ou servidor off");
//			this.close();
		} 
	}
	
	public void sendingCommand(DadosComandosPong dadosComandosPong){
		try {
//			DadosJogoPong dadosEnviados = new DadosJogoPong();
//			dadosEnviados = estadoJogoAtual.snapshot();
//			this.out.writeObject(dadosEnviados);
			
			this.out.writeObject(dadosComandosPong);
			this.out.flush();
//			Thread.sleep(1000 / 30);
		} catch (IOException e) {
			e.printStackTrace();
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

}
