package game;

import javax.swing.SwingUtilities;

import game.initial.StartScreen;
import game.main.TestScreen;

public class GameLauncher {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
					loadScreen(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void loadScreen(int screen) {
		if(screen == 1) new StartScreen();
		if(screen == 2) new TestScreen();
	}
}