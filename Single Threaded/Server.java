import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8010;
    private static final int SERVER_TIMEOUT = 10000;
    private boolean isRunning = true;

    private void establishClientConnection(ServerSocket serverSocket) {
        try (Socket acceptedConnection = serverSocket.accept();
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(acceptedConnection.getInputStream()));

                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);) {
            System.out.println("Connection accepted from Client: " + acceptedConnection.getRemoteSocketAddress());

            toClient.println("Message from server");

            String line = fromClient.readLine();
            System.out.println(line);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error handling client connection: " + ex.getMessage());
        }
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setSoTimeout(SERVER_TIMEOUT);
            System.out.println("Server is listening on PORT: " + PORT);

            while (isRunning) {
                establishClientConnection(serverSocket);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server = new Server();

        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }
}