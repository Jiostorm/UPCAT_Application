package game.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextLoader {
	
	private BufferedReader reader;
	//
	public static String[] neededtext;
	//
	public TextLoader(int index) {
		neededtext = new String[index];
		
		reader = new BufferedReader(new InputStreamReader(TextLoader.class.getResourceAsStream("/textfiles/generaltext.txt")));
		
		String line;
		try {
			for(int lineindex = 0; (line = reader.readLine()) != null; lineindex++) {
				neededtext[lineindex] = "";
				String[] linesplit = line.split("`");
				for(int ctr = 0; ctr < linesplit.length; ctr++) {
					neededtext[lineindex] += linesplit[ctr]+"\n";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}