package game.init;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.AppLauncher;

public class LoadingScreen {

	private final int width = 400, height = 300;
	private String title;
	
	private static JFrame initframe;

	public LoadingScreen(String title) {
		this.title = title;
		setScreen();
	}

	private void setScreen() {
		initframe = new JFrame(title);
		initframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		initframe.setSize(width, height);
		initframe.setLocationRelativeTo(null);
		initframe.setResizable(false);
		initframe.setIconImage(Toolkit.getDefaultToolkit().getImage(AppLauncher.class.getResource("/img/bg/upcaticon.png")));
		//
		new InitialScreen(width, height, "upcat2.png");
		
		initframe.setVisible(true);

		initframe.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				int xmes = JOptionPane.showConfirmDialog(initframe, "Are You Sure You Want to Exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if(xmes == JOptionPane.YES_OPTION) {
					initframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		return initframe;
	}
}
