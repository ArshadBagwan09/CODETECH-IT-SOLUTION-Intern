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

  public static void writeFile(String data) {
    try (FileWriter writer = new FileWriter(file_name)) {
      writer.write(data);
      System.out.println("Data written successfully.");
    } catch (IOException e) {
      System.out.println("Error while writing to file.");
      e.printStackTrace();
    }
  }

  public static void readFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader(file_name))) {
      String line;
      System.out.println("\n--- File Content ---");
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
      System.out.println("--------------------");
    } catch (IOException e) {
      System.out.println("Error while reading file.");
      e.printStackTrace();
    }
  }

  public static void modifyFile(String newData) {
    try (FileWriter writer = new FileWriter(file_name, true)) {
      writer.write("\n" + newData);
      System.out.println("File modified successfully (data appended).");
    } catch (IOException e) {
      System.out.println("Error while modifying file.");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
      System.out.println("\n===== CODTECH FILE HANDLING UTILITY =====");
      System.out.println("1. Create File");
      System.out.println("2. Write to File");
      System.out.println("3. Read File");
      System.out.println("4. Modify File (Append Data)");
      System.out.println("5. Exit");
      System.out.print("Enter your choice: ");

      choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          createfile();
          break;

        case 2:
          System.out.print("Enter data to write: ");
          String data = scanner.nextLine();
          writeFile(data);
          break;

        case 3:
          readFile();
          break;

        case 4:
          System.out.print("Enter data to append: ");
          String newData = scanner.nextLine();
          modifyFile(newData);
          break;

        case 5:
          System.out.println("Exiting program...");
          break;

        default:
          System.out.println("Invalid choice. Try again.");
      }

    } while (choice != 5);

    scanner.close();
  }
}
