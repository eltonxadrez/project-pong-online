package latencia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Servidor aguardando conexões...");

        Socket client = serverSocket.accept();
        System.out.println("Cliente conectado: " + client.getInetAddress());

        DataInputStream in = new DataInputStream(client.getInputStream());
        DataOutputStream out = new DataOutputStream(client.getOutputStream());

        while (true) {
            String message = in.readUTF();
            if (message.equals("ping")) {
                out.writeUTF("pong");
                out.flush();
            }
        }
    }
}