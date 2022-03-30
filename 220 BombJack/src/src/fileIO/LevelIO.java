package src.fileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Class: LevelIO
 * 
 * @author Carson Batt, Max Li
 *         
 *       
 */
public class LevelIO {
	private LevelIO() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static String readFile(int level) {
		String lvltxt = "";
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("levels/level_" + level + ".txt"));
			StringBuilder bld = new StringBuilder();
			while (scanner.hasNext()) {
				String line = scanner.next();
				bld.append(line);
			}
			lvltxt = bld.toString();
		} catch (FileNotFoundException e) {
			System.out.println("uh oh spgettio");
			e.printStackTrace();
		}
		scanner.close();
		return lvltxt;

	}
}
