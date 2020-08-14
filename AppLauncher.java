package game;

import game.initial.StartScreen;
import game.main.ExamScreen;

public class AppLauncher {

	public static void main(String[] args) {
		loadScreen(1);
	}
	public static void loadScreen(int screen) {
		if(screen == 1) new StartScreen();
		if(screen == 2) new ExamScreen();
	}
}