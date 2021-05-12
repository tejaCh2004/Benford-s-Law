
/*
*Date: May 12, 2021 
*Name: Teja Chilukuri. 
*Partner name: Michelle Chan 
*Teacher: Mr.Ho 
*Description: This program helps a retail company as a sales analysis system. The program will validate the sales data using Benford's Law
*And it will export the digit frequency in the form of a .csv file called “results.csv”. These results will be in the form of a table and a graph. 
* */

//import packages
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Benford {
  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    System.out.println("Would you like to start the program (yes or no) ");
    String begin = reader.nextLine();
    if (begin.equals("yes") || begin.equals("Yes")) {
      // call method called fileReader()
      fileReader();
    } else {
      System.out.println("The program has terminated");
      reader.close();
    }

  }

  /**
   * @Author : Teja
   * @version: 1.56.1
   *           <p>
   * @Description: This method prompts the user to enter the file name, and reads
   *               the file. It then stores all of the values of the file an array
   *
   *               <p>
   * @peram none
   * @return: none
   */

  public static void fileReader() throws java.nio.file.InvalidPathException {
    Scanner reader = new Scanner(System.in);
    String newLine = "";
    // prompt the the user to enter the file name or path. Path would be written as
    // "C:\Users\<folder name> \<folder name> \ sales.csv"
    System.out.println("Enter the entire file path, or the name of the file you would like to read (sales.csv)!");

    try {
      String fileName = reader.nextLine();
      // store the sales.csv in a variable called file
      File file = new File(fileName);
      // seperate the postal code and sales using the delimiter.
      Scanner sales = new Scanner(file).useDelimiter(",\\s*");
      // store the values in a array list, using a while loop. This can be useful if
      // the user decides to add more values to the sales.csv file.
      List<String> salesList = new ArrayList<String>();
      // while loop
      while (sales.hasNext()) {
        // find next line
        newLine = sales.next();
        salesList.add(newLine);
      }
      sales.close();
      // covert the array list and store the values of the file in an array called
      // "salesArray"
      String[] salesArray = salesList.toArray(new String[0]);
      // call frequency Calculation() method.
      frequencyCalculation(salesArray);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static void fileWrite(double[] benfordArray) {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter the output filename only (results.csv)!");
    String pathFileName = scan.nextLine();
    scan.close();
    WriteToFile.writeFile(pathFileName, benfordArray);
  }

  /**
   * @Author : Teja
   * @version: 1.56.1
   *           <p>
   * @Description: This method calculates the frequency of all the first digits in
   *               the sales.csv files. It then validates to check of the
   *               frequency number "1" is between 29% and 32%. The frequencies
   *               calculated in this method are then exported (which is done by
   *               my partner) to the graph and results.csv file
   *               <p>
   * @peram: String[] salesArray all the values of the data imported from the
   *         sales.csv file is stored in this array.
   * @return: none
   */
  public static void frequencyCalculation(String[] salesArray) {
    // initializing variables
    double total = salesArray.length;
    double frequency1 = 0;
    double[] benfordArray = new double[10];
    double[] count = new double[10];
    // call method initalCount_c, and store the value in the an array called
    // "count".
    count = initalCount_c();

    int firstNum;
    // convert the array called "salesArray" into a string
    for (String s : salesArray) {
      // get the value of the first number of each line.
      firstNum = Character.getNumericValue(s.charAt(0));
      // System.out.print(s);

      // Calculating the number of times the first digit appears By comparing the
      // value of the first number in each line, with numbers 1-9
      for (int j = 1; j < 10; j++) {
        if (firstNum == j) {
          count[j]++;
        }
      }
    }

    System.out.println("These are the frequencies which will be exported into the results.csv file: ");
    // used a for-loop to get the frequency of each number (1-9)
    for (int i = 1; i < 10; i++) {
      // frequency = (the number of times the particular first digit appeared/ total
      // number of lines)*100
      double frequency = ((count[i]) / total) * 100;
      benfordArray[i] = frequency;
      System.out.print(i + ": ");
      System.out.format("%.1f", frequency);
      System.out.println(" %");
    }
    // frequency of the number 1.
    frequency1 = ((count[1]) / total) * 100;
    // validate to see of the frequency of the "1" being the first digit is between
    // 29 and 32%, using a if condition.
    if (frequency1 >= 29 && frequency1 <= 32) {
      System.out.println(
          "Therefore, since frequency of 1 being as the first digit is between 29% to 32%, fraud likely did not occur.");

    } else {
      System.out.println("There is a fraud");
    }
    // done by michelle.
    fileWrite(benfordArray);
    PlotChart plotChart = new PlotChart();
    plotChart.plotGraph(benfordArray);

  }

  /**
   * @Author : Teja
   * @version: 1.56.1
   *           <p>
   * @Description: This method prompts for the Customer Info. the method Stores
   *               the value inside a String variable in a CSV format, and returns
   *               the value to the main function. This method Also calls the
   *               validatePostalCode and the creditCard method.
   *               <p>
   * @peram none
   * @return: count Array used to initial the frequency of the first digits.
   */
  public static double[] initalCount_c() {
    // use a for loop to initialize the frequency value of each number.
    double[] count = new double[10];
    for (double i = 1; i < 10; i++) {
      count[(int) i] = 0;
    }
    // return the value of count.
    return count;
  }
}