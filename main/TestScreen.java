package game.main;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.GameLauncher;

public class TestScreen {
	
	private static JFrame testframe;
	//
	private final int width = 700, height = 550;
	private final String title = "UP College Admission Test Application";
	//
	public TestScreen() {
		setScreen();
	}
	//
	private void setScreen() {
		testframe = new JFrame(title);
		testframe.setSize(width, height);
		testframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		testframe.setLocationRelativeTo(null);
		testframe.setResizable(false);
		testframe.setFocusable(true);
		testframe.setIconImage(Toolkit.getDefaultToolkit().getImage(GameLauncher.class.getResource("/image/attributes/upcaticon.png")));
		
		new TestMain(width, height);
		testframe.setVisible(true);
		JOptionPane.showMessageDialog(testframe, "Please Click The \"Sign In\" Button To Enter Some Of Your\nBasic Information In Order To Start The Application.", "INSTRUCTION", JOptionPane.INFORMATION_MESSAGE);
		testframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				if(JOptionPane.showConfirmDialog(testframe, "Do You Want To Close The Application?", "CONFIRMATION DIALOG", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
	}
	public static JFrame getJFrame() {
		return testframe;
	}
}