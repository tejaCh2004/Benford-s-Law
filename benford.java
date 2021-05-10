import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Benford {
  public static void main(String[] args) throws java.nio.file.InvalidPathException {
    Scanner reader = new Scanner(System.in);
    String newLine = "";
    System.out
        .println("Enter the entire file path of the file you would like to read. Including the file name (sales.csv)!");
    String fileName = reader.nextLine();
    File file = new File(fileName);
    // create Scanner sales
    try {
      Scanner sales = new Scanner(file).useDelimiter(",\\s*");
      List<String> salesList = new ArrayList<String>();
      // while loop
      while (sales.hasNext()) {
        // find next line
        newLine = sales.next();
        salesList.add(newLine);
      }
      sales.close();
      String[] salesArray = salesList.toArray(new String[0]);
      frequencyCalculation(salesArray);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  public static void frequencyCalculation(String[] salesArray) {
    double[] count = new double[10];
    count = inCount_c();

    int firstNum;
    for (String s : salesArray) {
      firstNum = Character.getNumericValue(s.charAt(0));
      // System.out.print(s);

      // Calculating the number of times the first digit appears.
      for (int j = 1; j < 10; j++) {
        if (firstNum == j) {
          count[j]++;
        }
      }
    }
    double total = salesArray.length;
    double frequency1 = 0;

    for (int i = 1; i < 10; i++) {
      double frequency = ((count[i]) / total) * 100;
      System.out.print(i + " ");
      System.out.format("%.1f", frequency);
      System.out.println();
    }
    frequency1 = ((count[1]) / total) * 100;
    if (frequency1 >= 29 && frequency1 <= 32) {
      System.out
          .println("Since frequency of 1 being in the first digit is between 29% to 32 %, fraud likely did not occur.");

    } else {
      System.out.println("There is a fraud");
    }
  }

  public static double[] inCount_c() {
    double[] count = new double[10];
    for (double i = 1; i < 10; i++) {
      count[(int) i] = 0;
    }
    return count;
  }
}