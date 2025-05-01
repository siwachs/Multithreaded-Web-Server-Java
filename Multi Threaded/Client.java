import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                final int PORT = 8010;

                try {
                    InetAddress address = InetAddress.getByName("localhost");
                    try (Socket socket = new Socket(address, PORT);
                            PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
                            BufferedReader fromSocket = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()))) {
                        toSocket.println("Message from Client");

                        String line = fromSocket.readLine();
                        System.out.println(line);
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.err.println(ex.getMessage());
                }
            }
        };
    }

    public static void main(String[] args) {
        Client client = new Client();

        for (int i = 0; i < 100; i++) {
            try {
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println(ex.getMessage());
            }
        }
    }
}
