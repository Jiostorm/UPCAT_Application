package game.initial;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.GameLauncher;

public class StartScreen {
	
	private static JFrame startframe;
	//
	private int width = 400, height = 310;
	private String title = "Start-Up Application";
	//
	public StartScreen() {
		setScreen();
	}
	//
	private void setScreen() {
		startframe = new JFrame(title);
		startframe.setSize(width, height);
		startframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		startframe.setLocationRelativeTo(null);
		startframe.setResizable(false);
		startframe.setFocusable(true);
		startframe.setIconImage(Toolkit.getDefaultToolkit().getImage(GameLauncher.class.getResource("/image/attributes/upcaticon.png")));
		
		new StartMain(width, height);
		startframe.setVisible(true);
		startframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				if(JOptionPane.showConfirmDialog(startframe, "Do You Want To Close The Application?", "CONFIRMATION DIALOG", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					startframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
	}
	public static JFrame getJFrame() {
		return startframe;
	}
}