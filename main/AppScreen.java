package game.main;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.AppLauncher;

public class AppScreen {
	
	private final int width = 800, height = 700;
	private String title;
	
	private static JFrame mainframe;
	
	public AppScreen(String title) {
		this.title = title;
		setScreen();
	}
	
	private void setScreen() {
		mainframe = new JFrame(title);
		mainframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainframe.setSize(width, height);
		mainframe.setLocationRelativeTo(null);
		mainframe.setResizable(false);
		mainframe.setIconImage(Toolkit.getDefaultToolkit().getImage(AppLauncher.class.getResource("/img/bg/upcaticon.png")));
		
		new AppLogic(width, height, "upcat.png");
		
		mainframe.setVisible(true);
		JOptionPane.showMessageDialog(mainframe, "Welcome! Dear Student, \nPlease Use This App to Review For the Upcoming UPCAT Exam. Specifically, the Math Subject is Only the Focus of this Application.", "Welcome", JOptionPane.INFORMATION_MESSAGE);
		
		mainframe.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				int xmes = JOptionPane.showConfirmDialog(mainframe, "Are You Sure You Want to Exit?", "Confirmation Dialog", JOptionPane.YES_NO_OPTION);
				if(xmes == JOptionPane.YES_OPTION) {
					mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}
		});
	}
	public static JFrame getframe() {
		return mainframe;
	}
}
