import java.io.*;
import java.rmi.server.SocketSecurityException;
import java.util.Scanner;

public class FILE_HANDLING_UTILITY {

  private static final String file_name = "CodeTech It Solution .txt";

  public static void createfile() {
    try {
      File file = new File(file_name);
      if (file.createNewFile()) {
        System.err.println("File is create Successfully: " + file_name);
      } else {
        System.err.println("File is Alredy created");
      }
    } catch (IOException e) {
      System.err.println("Error While File creation");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {

  }
}
