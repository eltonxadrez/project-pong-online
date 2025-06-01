package latencia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PingClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345); // use o IP real do servidor se for remoto
        System.out.println("Conectado ao servidor.");

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        while (true) {
            long startTime = System.nanoTime();

            out.writeUTF("ping");
            out.flush();

            String response = in.readUTF();
            long endTime = System.nanoTime();

            if (response.equals("pong")) {
                long latencyMs = (endTime - startTime) / 1_000_000;
                System.out.println("Latência: " + latencyMs + " ms");
            }

            Thread.sleep(1000); // Espera 1 segundo para o próximo ping
        }
    }
}
