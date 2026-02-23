import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

  private static final int PORT = 1234;
  private static Set<ClientHandler> clientHandlers = new HashSet<>();

  public static void main(String[] args) {
    System.out.println("Server started...");

    try (ServerSocket serverSocket = new ServerSocket(PORT)) {

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("New client connected");

        ClientHandler clientHandler = new ClientHandler(socket);
        clientHandlers.add(clientHandler);

        Thread thread = new Thread(clientHandler);
        thread.start();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static class ClientHandler implements Runnable {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String name;

    public ClientHandler(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      try {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        out.println("Enter your name:");
        name = in.readLine();
        System.out.println(name + " joined the chat.");

        broadcast(name + " joined the chat.");

        String message;
        while ((message = in.readLine()) != null) {
          broadcast(name + ": " + message);
        }

      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          clientHandlers.remove(this);
          socket.close();
          broadcast(name + " left the chat.");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    private void broadcast(String message) {
      for (ClientHandler client : clientHandlers) {
        client.out.println(message);
      }
    }
  }
}