package game.initial;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.AppLauncher;

public class StartScreen {
	
	private static JFrame startframe;
	//
	private final int width = 400, height = 300;
	private final String title = "Start-Up Application";
	//
	public StartScreen() {
		setScreen();
	}
	//
	private void setScreen() {
		startframe = new JFrame(title);
		startframe.setSize(width, height);
		startframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		startframe.setResizable(false);
		startframe.setLocationRelativeTo(null);
		startframe.setFocusable(true);
		startframe.setIconImage(Toolkit.getDefaultToolkit().getImage(AppLauncher.class.getResource("/image/bg/upcaticon.png")));
		
		new StartLogic(width, height);
		startframe.setVisible(true);
		startframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int exmes = JOptionPane.showConfirmDialog(startframe, "Do You Want To Close The Application?", "CONFIRMATION DIALOG", JOptionPane.YES_NO_OPTION);
				if(exmes == JOptionPane.YES_OPTION) {
					startframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
	}
	public static JFrame getJFrame() {
		return startframe;
	}
}