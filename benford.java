import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class benford{ 
  public static void main(String[] args)throws InvalidPathException, FileNotFoundException {
  
    double [] count=new double[10];
    count=inCount_c();

    int firstNum;
    String newLine = "";
  
  
    

    for (String s : salesArray) {
        firstNum = Character.getNumericValue(s.charAt(0));
       // System.out.print(s);
      for(int j = 1; j < 10 ; j ++){
        if(firstNum ==j ){
          count[j]++;  
        }
      }
    }

   double total = salesArray.length; 
   double frequency1 = 0;

   for(int i=1; i< 10 ; i++ ){ 
    double frequency= ((count[i])/ total)*100;
    System.out.print(i + " ");
    System.out.format("%.1f", frequency);
    System.out.println(); 
   }
   frequency1= ((count[1])/total)*100;
   if(frequency1 >= 29 && frequency1 <= 32){ 
     System.out.println("Since frequency of 1 being in the first digit is between 29% to 32 %, fraud likely did not occur."); 
    
   }
   else{ 
    System.out.println("There is a fraud");
   }
  } 
  public static double[] inCount_c(){ 
    double[]count = new double [10]; 
    for(double i = 1; i < 10; i++){ 
      count[(int) i]= 0;
    }
    return count; 
  }
}