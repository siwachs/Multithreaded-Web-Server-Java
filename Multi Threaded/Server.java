import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {
    private static final int PORT = 8010;
    private static final int SERVER_TIMEOUT = 10000;
    private static boolean isRunning = true;

    public Consumer<Socket> getConsumer() {
        return clientSocket -> {
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Message from server");

                toClient.close();
                clientSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println(ex.getMessage());
            }
        };
    }

    public static void main(String[] args) {
        Server server = new Server();

        try {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                serverSocket.setSoTimeout(SERVER_TIMEOUT);
                System.out.println("Server is listening on PORT: " + PORT);

                while (isRunning) {
                    Socket acceptedSocket = serverSocket.accept();

                    Thread thread = new Thread(() -> server.getConsumer().accept(acceptedSocket));
                    thread.start();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }
}