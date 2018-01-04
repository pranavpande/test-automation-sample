package testRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

public class ConfigManager {
	public static Hashtable <String, String> configInfo = new Hashtable<String,String>();
	public static synchronized void readConfigFile() throws FileNotFoundException {
		File configFile = new File("src/main/resources/config");
		Scanner scanner = new Scanner(configFile);

		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			if(!line.equals("") && line.charAt(0) != '/'){
				String [] creds = line.split("::");
				configInfo.put(creds[0].toLowerCase(), creds[1]);
			}
		}
		scanner.close();
	}
}
