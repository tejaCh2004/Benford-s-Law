import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author michelle chan
 *
 */
public class WriteToFile {
	
	/**
	 * @param pathFileName
	 * @param benfordArray
	 */
	public static void writeFile(String pathFileName, double[] benfordArray) {
		// writes customer information into CSV output file, if the file already exists, the information will be appended to the bottom of the file
	
		if (benfordArray == null || benfordArray.length == 0) {
			return;
		}
		// checks if the filename is empty
		if (pathFileName == null || pathFileName.length() == 0) {
			pathFileName = ".\result.csv";
			// checks if the path starts with c:\\, if no path, then it is automatically added
		} else if (!pathFileName.startsWith("c:\\") || !pathFileName.startsWith("C:\\") || !pathFileName.startsWith("..\\") || !pathFileName.startsWith(".\\")) {
			pathFileName = ".\\" + pathFileName;
		}
		
		// open a new file for writing
		FileWriter outFile = null;
		PrintWriter out = null;
		try {
			File file = new File(pathFileName);
			boolean isFileExist = file.exists();
			file.getParentFile().mkdirs();
			outFile = new FileWriter(file, true);
			out = new PrintWriter(outFile);
		// checks if file already exists. if the file already exists, header does not need to be written into the file. otherwise, a header will be included. 
		if (!isFileExist) {
			String header = "Digit,Percent";
			out.println(header);
		}
		// loop through the benford array to print each entry to the csv file
		for (int i=1; i < benfordArray.length; i++) {
			String msg = String.format("%d,%.1f%%", i, benfordArray[i]);
			out.println(msg);
		}
		// prints "done" and outputs file
		System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		// lastly closes file even if there is an exception  
		} finally {
			if (out != null) {
				out.close();
			}
			try {
				if (outFile != null)
				outFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
