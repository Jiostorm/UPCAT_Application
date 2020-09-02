package game;

import game.init.LoadingScreen;
import game.main.AppScreen;

public class AppLauncher {

	public static void main(String[] args) {
		loadWindow(1);
	}
	
	public static void loadWindow(int orient) {
		switch(orient) {
		case 1:
			new LoadingScreen("Start-Up Application");
			break;
		case 2:
			new AppScreen("UP College Admission Test Application");
			break;
		}
	}

}
