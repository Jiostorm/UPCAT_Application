package game.main;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.AppLauncher;

public class ExamScreen {
	
	private static JFrame examframe;
	//
	private final int width = 700, height = 550;
	private final String title = "UP College Admission Test Application";
	//
	public ExamScreen() {
		setScreen();
	}
	//
	private void setScreen() {
		examframe = new JFrame(title);
		examframe.setSize(width, height);
		examframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		examframe.setResizable(false);
		examframe.setLocationRelativeTo(null);
		examframe.setFocusable(true);
		examframe.setIconImage(Toolkit.getDefaultToolkit().getImage(AppLauncher.class.getResource("/image/bg/upcaticon.png")));
		
		new ExamLogic(width, height);
		examframe.setVisible(true);
		JOptionPane.showMessageDialog(examframe, "Please Click The \"Sign In\" Button To Enter Some Of Your\nBasic Information In Order To Start The Application.", "INSTRUCTION", JOptionPane.INFORMATION_MESSAGE);
		examframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int exmes = JOptionPane.showConfirmDialog(examframe, "Do You Want To Close The Application?", "CONFIRMATION DIALOG", JOptionPane.YES_NO_OPTION);
				if(exmes == JOptionPane.YES_OPTION) {
					examframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
	}
	public static JFrame getJFrame() {
		return examframe;
	}
}