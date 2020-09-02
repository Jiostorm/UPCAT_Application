package game.initial;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import game.GameLauncher;

public class StartMain {
	
	private StartBackGround mainpanel;
	//
	private JProgressBar loadingbar;
	private JButton start;
	private JLabel footer;
	//
	private Font font1, font2;
	private Thread loading, initializing;
	//
	private final String title = "Launching UP College Admission Test";
	//
	public StartMain(int width, int height) {
		mainpanel = new StartBackGround(width, height);
		initAll();
	}
	private void startNow() {
		mainpanel.add(start);
		StartScreen.getJFrame().add(mainpanel);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				StartScreen.getJFrame().setTitle(title);
				mainpanel.remove(start);
				mainpanel.revalidate();
				mainpanel.repaint();
				mainpanel.add(loadingbar);
				mainpanel.add(footer);
				loading = new Thread(new Runnable() {
					@Override
					public void run() {
						for(int tick = 0; tick <= 100; tick++) {
							final int now = tick;
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									loadingbar.setValue(now);
									if(loadingbar.getValue() == 100) {
										footer.setText("Initialization Complete!");
										SwingUtilities.invokeLater(new Runnable() {
											@Override
											public void run() {
												try {
													initializing.join();
													Thread.sleep(2000);
												} catch (InterruptedException e) {
													e.printStackTrace();
												} finally {
													StartScreen.getJFrame().dispose();
													GameLauncher.loadScreen(2);
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
				initializing = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							while(loadingbar.getValue() < 100) {
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
		font1 = new Font("Monospaced", Font.TRUETYPE_FONT, 13);
		font2 = new Font("Cambria", Font.TRUETYPE_FONT, 13);
		//
		UIManager.put("OptionPane.messageFont", new FontUIResource(font2));
		UIManager.put("OptionPane.buttonFont", new FontUIResource(font2));
		//
		footer = new JLabel("",JLabel.LEFT);
		footer.setBounds(0, 255, 200, 15);
		footer.setFont(font1);
		//
		start = new JButton("Start App");
		start.setForeground(Color.BLACK);
		start.setBackground(Color.LIGHT_GRAY);
		start.setFocusable(false);
		start.setBounds(141, 170, 111, 20);		
		start.setFont(font1);
		//
		loadingbar = new JProgressBar(0, 100);
		loadingbar.setForeground(Color.BLACK);
		loadingbar.setBackground(Color.LIGHT_GRAY);
		loadingbar.setValue(0);
		loadingbar.setStringPainted(true);
		loadingbar.setBounds(100, 200, 200, 20);
		loadingbar.setFont(font1);
		//
		startNow();
	}
}