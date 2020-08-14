package game.initial;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import game.AppLauncher;

public class StartLogic {

	private StartBackGround mainpanel;
	//
	private JLabel footer;
	private JButton start;
	private JProgressBar loadbar;
	//
	private Font font1;
	//
	private String title = "Launching UP College Admission Test";
	//
	public StartLogic(int width, int height) {
		mainpanel = new StartBackGround(width, height);
		initAll();
		
		mainpanel.add(start);
		StartScreen.getJFrame().add(mainpanel);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				StartScreen.getJFrame().setTitle(title);
				mainpanel.remove(start);
				mainpanel.revalidate();
				mainpanel.repaint();
				mainpanel.add(loadbar);
				mainpanel.add(footer);
				Thread loading = new Thread(new Runnable() {
					@Override
					public void run() {
						for(int tick = 0; tick <= 100; tick++) {
							final int now = tick;
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									loadbar.setValue(now);
									if(loadbar.getValue() == 100) {
										footer.setText("Initialization Complete!");
										SwingUtilities.invokeLater(new Runnable() {
											@Override
											public void run() {
												try {
													Thread.sleep(2000);
												} catch (InterruptedException e) {
													e.printStackTrace();
												} finally {
													StartScreen.getJFrame().dispose();
													AppLauncher.loadScreen(2);
												}
											}
										});
									}
								}		
							});
							try {
								Thread.sleep(40);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				});
				Thread initializing = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							while(loadbar.getValue() < 100) {
								footer.setText("Initializing.");
								StartScreen.getJFrame().setTitle(title+".");
								Thread.sleep(300);
								footer.setText("Initializing..");
								StartScreen.getJFrame().setTitle(title+"..");
								Thread.sleep(300);
								footer.setText("Initializing...");
								StartScreen.getJFrame().setTitle(title+"...");
								Thread.sleep(300);
								footer.setText("Initializing....");
								StartScreen.getJFrame().setTitle(title+"....");
								Thread.sleep(300);
								footer.setText("Initializing.....");
								StartScreen.getJFrame().setTitle(title+".....");
								Thread.sleep(300);
							} 
						}catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});
				loading.start();
				initializing.start();
			}
		});
	}
	private void initAll() {
		font1 = new Font("Book Antiqua", 5, 15);

		footer = new JLabel("",JLabel.LEFT);
		footer.setBounds(0, 255, 200, 15);
		footer.setFont(font1);

		start = new JButton("Start App");
		start.setForeground(Color.BLACK);
		start.setBackground(Color.LIGHT_GRAY);
		start.setFocusable(false);
		start.setBounds(147, 170, 100, 20);		
		start.setFont(font1);

		loadbar = new JProgressBar(0, 100);
		loadbar.setForeground(Color.BLACK);
		loadbar.setBackground(Color.LIGHT_GRAY);
		loadbar.setValue(0);
		loadbar.setStringPainted(true);
		loadbar.setBounds(100, 200, 200, 20);
		loadbar.setFont(font1);
	}
}