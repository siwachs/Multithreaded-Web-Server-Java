import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService threadPool;
    private static final int PORT = 8010;
    private static final int POOL_SIZE = 10;
    private static final int SERVER_TIMEOUT = 10000;
    private static boolean isRunning = true;

    public Server(int poolSize) {
        this.threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void handleClient(Socket clientSocket) {
        try (PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(), true)) {
            toSocket.println("Message from server " + clientSocket.getInetAddress());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(POOL_SIZE);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setSoTimeout(SERVER_TIMEOUT);
            System.out.println("Server is listening on port " + PORT);

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();

                server.threadPool.execute(() -> server.handleClient(clientSocket));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        } finally {
            server.threadPool.shutdown();
        }
    }
}