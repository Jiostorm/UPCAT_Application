package game.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UserField {

	private final String defpath = System.getProperty("user.home")+"\\Desktop\\UPCAT Application";
	//
	private File userdir, appdir, currentfile;
	//
	private static PrintWriter writer;
	//
	public UserField() {
		appdir = new File(defpath);
		userdir = new File(defpath+"\\Results");
		currentfile = new File(defpath+"\\Results\\Examinees.txt");
		if(!appdir.exists()) appdir.mkdir();
		if(!userdir.exists()) userdir.mkdir();
		try {
			writer = new PrintWriter(new FileWriter(currentfile, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//
	public static void accessUser(String name, String school, int score, int maxscore) {
		writer.println("Examinee Name : "+name);
		writer.println("School Name : "+school);
		writer.println();
		if(maxscore == 30) writer.println("Test Difficulty : Easy");
		if(maxscore == 60) writer.println("Test Difficulty : Normal");
		if(maxscore == 90) writer.println("Test Difficulty : Hard");
		writer.println("Test Score : "+score+"/"+maxscore);
		writer.println("*****************************************************************");
		writer.println();
	}
	public static void accessClose() {
		writer.close();
	}
}