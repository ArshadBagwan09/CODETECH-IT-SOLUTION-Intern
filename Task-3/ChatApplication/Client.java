import java.io.*;
import java.net.*;

public class Client {

  private static final String SERVER_ADDRESS = "localhost";
  private static final int SERVER_PORT = 1234;

  public static void main(String[] args) {

    try {
      Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

      BufferedReader in = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));

      PrintWriter out = new PrintWriter(
          socket.getOutputStream(), true);

      BufferedReader consoleInput = new BufferedReader(
          new InputStreamReader(System.in));

      // Thread to listen messages from server
      new Thread(() -> {
        try {
          String serverMessage;
          while ((serverMessage = in.readLine()) != null) {
            System.out.println(serverMessage);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }).start();

      // Send messages to server
      String userInput;
      while ((userInput = consoleInput.readLine()) != null) {
        out.println(userInput);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}