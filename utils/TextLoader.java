package game.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextLoader {

	@SuppressWarnings("resource")
	public static String getTextFile(String name, int space) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src\\game\\res\\txt\\"+name));
			StringBuilder wtext = new StringBuilder(32);
			String line;
			wtext.append("\n");
			try {
				while((line = reader.readLine()) != null) {
					if(space == 1) {
						wtext.append(line).append("\n");
					} else if(space == 2) {
						wtext.append(line).append("\n\n");
					}
				}
				return wtext.toString();
			} catch(IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("resource")
	public static String getTextFile(int orient) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src\\game\\res\\txt\\mode.txt"));

			int ctr = 1;
			String line;
			try {
				while((line = reader.readLine()) != null) {
					if(ctr == orient) {
						return "\n"+line;
					}
					ctr++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
