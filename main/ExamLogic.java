package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import game.utils.ImageLoader;

public class ExamLogic implements ActionListener{

	private ExamBackGround bgpanel;
	//
	private JTabbedPane cont;
	private JPanel subpanel, userpanel;
	//
	private JButton instruction, exam, about, exit, signin, signout, goback, ready, nextpage, previouspage, firstpage, lastpage, submit, progress, jump, mainmenu;
	private JRadioButton easy, medium, hard, optA, optB, optC, optD, optE;
	private JTextArea textarea, userarea;
	private JLabel header, score, thetimer, developerimg, imagepane;
	private JTable resulttable;
	private JTextField namef, schoolf;
	//
	private Thread timer;
	private ButtonGroup bg;
	private DefaultTableModel deftablemodel;
	private Font font1, font2;
	private Color fore = Color.BLACK, back = Color.LIGHT_GRAY;
	//
	private String[] userans, corans, remark;
	private int[] randcorstor;
	private int[][] randstor;
	//
	private int numques, numctr = 1, userscore, timelefthr, timeleftmins, timeleftsec;
	private boolean tocontinue;
	private Object[] userinfo;
	//
	public ExamLogic(int width, int height) {
		bgpanel = new ExamBackGround(width, height);

		initAllFields();
		ExamScreen.getJFrame().add(bgpanel);
	}
	//
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == instruction) {
			removeAllAndRepaint(subpanel);
			cont.setTitleAt(0, "Instruction");
			textarea.setBounds(30, 20, 570, 300);
			textarea.setText("\n                   The Application consists of three main control buttons to click, click the \"Take Exam\" Button to start the UPCAT exam. If you want to know who develop this application, just click the \"About Developer\" button to view the information about the developer of this application.\n\n                   The \"Take Exam\" button also consists of another three buttons to choose the difficulty of the exam, in all the three main buttons, just click the \"Go Back\" whenever you want to have access to the three main control buttons.\n\n                   If you found any application bug, please report it immediately to the developer; thank you.");
			subpanel.add(textarea);
			subpanel.add(goback);
		}
		if(ae.getSource() == exam) {
			removeAllAndRepaint(subpanel);
			cont.setTitleAt(0, "Exam Settings");
			textarea.setBounds(30, 20, 440, 290);
			textarea.setText("\n         Welcome!! Dear Student, Please choose one difficulty from the choices in your right before you start taking the UPCAT Exam. GoodLuck! and go get a high score from one of the difficulties and after choosing, just click the \"I'm Ready!\" button below to start. In the exam panel, click the \"Your Progress\" button to check your current answer(s).");
			subpanel.add(textarea);
			subpanel.add(easy);
			subpanel.add(medium);
			subpanel.add(hard);
			subpanel.add(ready);
			subpanel.add(goback);
			ready.setEnabled(false);
		}
		if(ae.getSource() == about) {
			removeAllAndRepaint(subpanel);
			cont.setTitleAt(0, "About Developer");
			textarea.setBounds(30, 20, 570, 300);
			textarea.setText("\n                   The Developer of this game is George William Sison from 11-Programming 2,He live in Dinaga Canaman Camarines Sur. He developed this application alone with efforts and passion for computer programming. Since his second year in junior high, he picked the computer programming specialization because he likes solving logic and mathematical problems and it is present in the computer programming subject and he will continue his dream to become a professional computer programmer and become a software developer in Microsoft Corporation someday.");
			subpanel.add(textarea);
			subpanel.add(developerimg);
			subpanel.add(goback);
		}
		if(ae.getSource() == exit) {
			if(JOptionPane.showConfirmDialog(ExamScreen.getJFrame(), "Do You Want To Close The Application?", "CONFIRMATION DIALOG", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		if(ae.getSource() == signin) {
			int confmes = 1, xmes;
			while(confmes != 0) {
				if((xmes = JOptionPane.showConfirmDialog(subpanel, userinfo, "FILL UP INFORMATION", JOptionPane.OK_CANCEL_OPTION)) == JOptionPane.OK_OPTION && !namef.getText().isEmpty() && !schoolf.getText().isEmpty()) {
					if(JOptionPane.showConfirmDialog(subpanel, "Is The Information You Entered Are Correct?", "CONFIRMATION DIALOG", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						confmes = 0;
						removeAllAndRepaint(userpanel);
						addMenuComponents(1);
						userpanel.setBorder(BorderFactory.createTitledBorder(null, "-Current User-", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font1));
						userarea.setText("User Name:\n*"+namef.getText()+"\n\nSchool Name:\n*"+schoolf.getText());
						userpanel.add(signout);
						userpanel.add(userarea);
						JOptionPane.showMessageDialog(subpanel, "Welcome! "+namef.getText()+", \nPlease Use This App to Review For the Upcoming UPCAT Exam. \nSpecifically, the Math Subject is Only the Focus of this Application.", "WELCOME", JOptionPane.INFORMATION_MESSAGE);
					}
				} else if(xmes == JOptionPane.CANCEL_OPTION || xmes == JOptionPane.CLOSED_OPTION) {
					break;
				} else {
					JOptionPane.showMessageDialog(subpanel, "Incomplete Information Entered!", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		if(ae.getSource() == signout) {
			if(JOptionPane.showConfirmDialog(subpanel, "Are You Sure You Want To Resign?", "CONFIRMATION DIALOG", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				removeAllAndRepaint(userpanel);
				addMenuComponents(2);
				userpanel.setBorder(BorderFactory.createTitledBorder(null, " ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font1));
				namef.setText(""); schoolf.setText("");
			}
		}
		if(ae.getSource() == goback) {
			bg.clearSelection();
			cont.setTitleAt(0, "Main Menu");
			textarea.setText("");
			addMenuComponents(1);
		}
		if(ae.getSource() == ready) {
			numctr = 1; userscore = 0;
			if(JOptionPane.showConfirmDialog(subpanel, "You Cannot Change The Difficulty If You Already Chose One, \nAre You Okay With Your Difficulty?", "CONFIRMATION DIALOG", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				removeAllAndRepaint(subpanel);
				cont.setTitleAt(0, "Actual Examination");
				userans = new String[numques];
				corans = new String[numques];
				examRandomizing();
				for(int index = 0; index < numques; index++) {
					userans[index] = "Blank";
				}
				JOptionPane.showMessageDialog(subpanel, "Welcome Student!\nIn This Part, There are 12 Different Buttons;\n*The 5 Buttons Below Are The Control Buttons, There's Only 4 Buttons\n When You Are Still Not In The Last Question.\n\n*The Other 5 Buttons Are The Choices For The Question.\n\n*Last But Not The Least, The Button In The Top Left And Right Corner, Click\n That Button In The Right When You Want To View Your Current Answer(s)\n While The Button In The Left, Click It When You Want To Jump To A Page.\n\nYou Have "+timelefthr+"Hour(s) And "+timeleftmins+" Minute(s) To Answer The Exam.\nThat's All! And Goodluck On Your Exam!", "INSTRUCTION", JOptionPane.INFORMATION_MESSAGE);
				addExamComponents();
				examTimer();
			}
		}
		if(ae.getSource() == nextpage) {
			if(numctr < numques) {
				numctr++;
			}
			addExamComponents();
		}
		if(ae.getSource() == previouspage) {
			if(numctr > 1) {
				numctr--;
			}
			addExamComponents();
		}
		if(ae.getSource() == firstpage) {
			numctr = 1;
			addExamComponents();
		}
		if(ae.getSource() == lastpage) {
			numctr = numques;
			addExamComponents();
		}
		if(ae.getSource() == submit) {
			if(JOptionPane.showConfirmDialog(subpanel, "Are You Sure You Want To Submit Your Exam?", "CONFIRMATION DIALOG", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				toSubmit();
			}
		}
		if(ae.getSource() == progress) {
			new ExamProgress(userans, numques);
		}
		if(ae.getSource() == jump) {
			String topage = JOptionPane.showInputDialog(subpanel, "Enter The Page Number You Want\nTo Jump On:");
			if(topage != null && !topage.isEmpty()) {
				try {
					if(Integer.parseInt(topage) > 0 && Integer.parseInt(topage) <= numques) {
						numctr = Integer.parseInt(topage);
						addExamComponents();
					} else {
						JOptionPane.showMessageDialog(subpanel, "The Inputted Page Number Is Not In The Range Of Questions.", "WARNING", JOptionPane.WARNING_MESSAGE);
					}
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(subpanel, "Invalid Input!", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		if(ae.getSource() == mainmenu) {
			if(JOptionPane.showConfirmDialog(ExamScreen.getJFrame(), "Are You Done Viewing Your Exam Result?", "CONFIRMATION DIALOG", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				bg.clearSelection();
				cont.setTitleAt(0, "Main Menu");
				textarea.setText("");
				userarea.setText("User Name:\n*"+namef.getText()+"\n\nSchool Name:\n*"+schoolf.getText()+"\n\nLatest Score: "+userscore+"/"+numques);
				addMenuComponents(1);
			}
		}
		//
		if(easy.isSelected()) {
			numques = 30;
			textarea.setText("\n*Easy Mode:\n         This mode consists of "+numques+" different kinds of question related to Math subject.");
			ready.setEnabled(true);
			timelefthr = 0; timeleftmins = 30; timeleftsec = 0;
		}
		if(medium.isSelected()) {
			numques = 60;
			textarea.setText("\n*Medium Mode:\n         This mode consists of "+numques+" different kinds of question related to Math subject.");
			ready.setEnabled(true);
			timelefthr = 1; timeleftmins = 0; timeleftsec = 0;
		}
		if(hard.isSelected()) {
			numques = 90;
			textarea.setText("\n*Hard Mode:\n         This mode consists of "+numques+" different kinds of question related to Math subject.");
			ready.setEnabled(true);
			timelefthr = 1; timeleftmins = 30; timeleftsec = 0;
		}
		if(optA.isSelected()) {
			userans[numctr-1] = "a";
		}
		if(optB.isSelected()) {
			userans[numctr-1] = "b";
		}
		if(optC.isSelected()) {
			userans[numctr-1] = "c";
		}
		if(optD.isSelected()) {
			userans[numctr-1] = "d";
		}
		if(optE.isSelected()) {
			userans[numctr-1] = "e";
		}
	}
	private void addExamComponents() {
		removeAllAndRepaint(subpanel);
		subpanel.add(header);
		header.setText("Question Number: " + numctr);

		bg.clearSelection();
		
		if(userans[numctr-1].equals("a")) optA.setSelected(true);
		if(userans[numctr-1].equals("b")) optB.setSelected(true);
		if(userans[numctr-1].equals("c")) optC.setSelected(true);
		if(userans[numctr-1].equals("d")) optD.setSelected(true);
		if(userans[numctr-1].equals("e")) optE.setSelected(true);
		
		imagepane.setIcon(new ImageIcon(new ImageIcon(ImageLoader.getImage("/image/question/"+corans[numctr-1]+"/"+randstor[numctr-1][randcorstor[numctr-1]]+".PNG")).getImage().getScaledInstance(470, 285, Image.SCALE_SMOOTH)));

		firstpage.setEnabled(true);
		previouspage.setEnabled(true);
		nextpage.setEnabled(true);
		lastpage.setEnabled(true);
		subpanel.add(thetimer);
		subpanel.add(progress);
		subpanel.add(jump);
		subpanel.add(optA);
		subpanel.add(optB);
		subpanel.add(optC);
		subpanel.add(optD);
		subpanel.add(optE);
		subpanel.add(nextpage);
		subpanel.add(previouspage);
		subpanel.add(firstpage);
		subpanel.add(lastpage);
		subpanel.add(imagepane);
		if(numctr == numques) subpanel.add(submit);
		
		if(numctr == 1) {
			firstpage.setEnabled(false);
			previouspage.setEnabled(false);
		} else if(numctr == numques) {
			nextpage.setEnabled(false);
			lastpage.setEnabled(false);
		}
	}
	private void examTimer() {
		header.setBounds(215, 5, 200, 30);
		tocontinue = true;
		timer = new Thread(new Runnable() {
			@Override
			public void run() {
				while(timelefthr >= 0 && timeleftmins >= 0 && timeleftsec <= 60 && tocontinue) {
					try {
						if(timeleftmins <= 9 && timeleftsec <= 9) {
							thetimer.setText("Time: 0"+timelefthr+":0"+timeleftmins+":0"+timeleftsec);
						} else if(timeleftmins > 9 && timeleftsec <= 9){
							thetimer.setText("Time: 0"+timelefthr+":"+timeleftmins+":0"+timeleftsec);
						} else if(timeleftmins <= 9 && timeleftsec > 9) {
							thetimer.setText("Time: 0"+timelefthr+":0"+timeleftmins+":"+timeleftsec);
						} else if(timeleftmins > 9 && timeleftsec > 9){
							thetimer.setText("Time: 0"+timelefthr+":"+timeleftmins+":"+timeleftsec);
						}
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(timeleftmins == 0 && timeleftsec == 0) {
						timelefthr--;
						timeleftmins = 60;
					}
					if(timeleftsec == 0) {
						timeleftmins--;
						timeleftsec = 60;
					}
					timeleftsec--;
				}
				if(tocontinue) {
					JOptionPane.showConfirmDialog(subpanel, "TIME'S UP!!! Click The Button Below to Continue.", "INSTRUCTION", JOptionPane.PLAIN_MESSAGE);
					toSubmit();
				}
			}
		});
		timer.start();
	}
	@SuppressWarnings("serial")
	private void examResult() {
		remark = new String[numques];
		for(int index = 0; index < numques; index++) {
			remark[index] = userans[index].equalsIgnoreCase(corans[index]) ? "Check" : "Wrong";
			userscore = remark[index].equalsIgnoreCase("Check") ? userscore+1 : userscore;
		}
		Object[] tableheader = {"Question No", "Your Answer", "Correct Answer", "Remarks"};

		deftablemodel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		deftablemodel.setColumnIdentifiers(tableheader);

		resulttable = new JTable(deftablemodel);
		resulttable.setBackground(back);
		resulttable.setForeground(fore);
		resulttable.setFont(font1);
		resulttable.setRowHeight(30);

		Object[] tabrow = new Object[4];
		for(int tabindex = 0; tabindex < numques; tabindex++) {
			tabrow[0] = tabindex+1;
			tabrow[1] = userans[tabindex];
			tabrow[2] = corans[tabindex];
			tabrow[3] = remark[tabindex];
			deftablemodel.addRow(tabrow);
		}
		JScrollPane tablescr = new JScrollPane(resulttable);
		tablescr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		tablescr.setBounds(65, 70, 500, 300);

		header.setText("Your Exam Result");
		header.setBounds(210, 10, 200, 30);
		score.setText("Your Total Score: "+userscore+"/"+numques);
		score.setBounds(65, 380, 150, 30);

		subpanel.add(tablescr);
		subpanel.add(header);
		subpanel.add(score);
		subpanel.add(mainmenu);
	}
	private void examRandomizing() {
		Random rand = new Random();
		int randnum, randchose;
		randstor = new int[numques][5];
		randcorstor = new int[numques];

		boolean ifExist;
		for(int index = 0; index < numques; index++) {
			ifExist = false;
			randchose = rand.nextInt(5);
			if(randchose == 0) {
				randnum = rand.nextInt(16);
			} else if(randchose == 1) {
				randnum = rand.nextInt(26);
			} else if(randchose == 2) {
				randnum = rand.nextInt(28);
			} else {
				randnum = rand.nextInt(15);
			}
			for(int arrctr = 0; arrctr < numques; arrctr++) {
				if(randnum == randstor[arrctr][randchose]) {
					ifExist = true;
					index--;
					break;
				}
			}
			if(!ifExist) {
				randstor[index][randchose] = randnum;
				randcorstor[index] = randchose;
				switch(randcorstor[index]) {
				case 0: corans[index] = "a"; break;
				case 1: corans[index] = "b"; break;
				case 2: corans[index] = "c"; break;
				case 3: corans[index] = "d"; break;
				case 4: corans[index] = "e"; break;
				}
			}
		}
	}
	//
	private void initAllFields() {
		font1 = new Font("Cambria", Font.TRUETYPE_FONT, 13);
		font2 = new Font("Cambria", Font.TRUETYPE_FONT, 18);
		//
		subpanel = new JPanel(null);
		subpanel.setOpaque(false);

		userpanel = new JPanel(null);
		userpanel.setBackground(back);
		userpanel.setBounds(330, 5, 285, 180);
		userpanel.setBorder(BorderFactory.createTitledBorder(null, " ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font1));
		//
		initAllButtons();
		//
		UIManager.put("TabbedPane.contentOpaque", false);
		cont = new JTabbedPane();
		cont.setBounds(30, 20, 635, 480);
		cont.setFont(font1);
		//
		textarea = new JTextArea();
		textarea.setBackground(back);
		textarea.setForeground(fore);
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setFont(font2);

		userarea = new JTextArea();
		userarea.setBackground(back);
		userarea.setForeground(fore);
		userarea.setEditable(false);
		userarea.setLineWrap(true);
		userarea.setWrapStyleWord(true);
		userarea.setFont(font1);
		userarea.setBounds(10, 20, 250, 130);
		//
		namef = new JTextField();
		schoolf = new JTextField();
		//
		header = new JLabel("", JLabel.CENTER);
		header.setBackground(back);
		header.setForeground(fore);
		header.setOpaque(true);
		header.setFont(font2);

		score = new JLabel("", JLabel.CENTER);
		score.setBackground(back);
		score.setForeground(fore);
		score.setOpaque(true);
		score.setFont(font1);

		thetimer = new JLabel("", JLabel.CENTER);
		thetimer.setBackground(back);
		thetimer.setForeground(fore);
		thetimer.setBounds(270, 40, 90, 25);
		thetimer.setOpaque(true);
		thetimer.setFont(font1);

		developerimg = new JLabel();
		developerimg.setIcon(new ImageIcon(new ImageIcon(ImageLoader.getImage("/image/bg/george.png")).getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
		developerimg.setBounds(30, 330, 110, 110);

		imagepane = new JLabel();
		imagepane.setBounds(80, 70, 470, 290);
		//
		userinfo = new Object[]{"User Name: ", namef, "School Name: ", schoolf};
		cont.addTab("Main Menu", subpanel);
		//
		addMenuComponents(2);
		//
		bgpanel.add(cont);
	}
	private void initAllButtons() {
		instruction = new JButton("Instruction");
		instruction.setBackground(back);
		instruction.setForeground(fore);
		instruction.setBounds(30, 310, 95, 25);
		instruction.setFont(font1);

		exam = new JButton("Start Exam");
		exam.setBackground(back);
		exam.setForeground(fore);
		exam.setBounds(30, 350, 95, 25);
		exam.setFont(font1);

		about = new JButton("About Developer");
		about.setBackground(back);
		about.setForeground(fore);
		about.setBounds(30, 390, 130, 25);
		about.setFont(font1);

		exit = new JButton("Exit App");
		exit.setBackground(back);
		exit.setForeground(fore);
		exit.setBounds(510, 390, 90, 25);
		exit.setFont(font1);

		signin = new JButton("Sign In");
		signin.setBackground(back);
		signin.setForeground(fore);
		signin.setBounds(435, 190, 80, 25);
		signin.setFont(font1);

		signout = new JButton("Sign Out");
		signout.setBackground(back);
		signout.setForeground(fore);
		signout.setBounds(195, 150, 85, 25);
		signout.setFont(font1);

		goback = new JButton("Go Back");
		goback.setBackground(back);
		goback.setForeground(fore);
		goback.setBounds(510, 390, 90, 25);
		goback.setFont(font1);

		ready = new JButton("I'm Ready!");
		ready.setBackground(back);
		ready.setForeground(fore);
		ready.setBounds(200, 330, 100, 25);
		ready.setFont(font1);

		nextpage = new JButton("Next Page>");
		nextpage.setBackground(back);
		nextpage.setForeground(fore);
		nextpage.setBounds(265, 420, 95, 25);
		nextpage.setFont(font1);

		previouspage = new JButton("<Previous Page");
		previouspage.setBackground(back);
		previouspage.setForeground(fore);
		previouspage.setBounds(135, 420, 120, 25);
		previouspage.setFont(font1);

		firstpage = new JButton("<<First Page");
		firstpage.setBackground(back);
		firstpage.setForeground(fore);
		firstpage.setBounds(20, 420, 105, 25);
		firstpage.setFont(font1);

		lastpage = new JButton("Last Page>>");
		lastpage.setBackground(back);
		lastpage.setForeground(fore);
		lastpage.setBounds(370, 420, 100, 25);
		lastpage.setFont(font1);

		submit = new JButton("^Submit Exam^");
		submit.setBackground(back);
		submit.setForeground(fore);
		submit.setBounds(490, 420, 120, 25);
		submit.setFont(font1);

		jump = new JButton("Jump Page");
		jump.setBackground(back);
		jump.setForeground(fore);
		jump.setBounds(0, 0, 95, 25);
		jump.setFont(font1);

		progress = new JButton("Current Answers");
		progress.setBackground(back);
		progress.setForeground(fore);
		progress.setBounds(505, 0, 130, 25);
		progress.setFont(font1);

		mainmenu = new JButton("Main Menu");
		mainmenu.setBackground(back);
		mainmenu.setForeground(fore);
		mainmenu.setBounds(500, 390, 100, 25);
		mainmenu.setFont(font1);
		//
		easy = new JRadioButton("Easy Mode");
		easy.setBackground(back);
		easy.setForeground(fore);
		easy.setBounds(500, 50, 110, 30);
		easy.setFont(font1);

		medium = new JRadioButton("Medium Mode");
		medium.setBackground(back);
		medium.setForeground(fore);
		medium.setBounds(500, 150, 110, 30);
		medium.setFont(font1);

		hard = new JRadioButton("Hard Mode");
		hard.setBackground(back);
		hard.setForeground(fore);
		hard.setBounds(500, 250, 110, 30);
		hard.setFont(font1);

		optA = new JRadioButton("Option A");
		optA.setBackground(back);
		optA.setForeground(fore);
		optA.setBounds(80, 370, 80, 25);
		optA.setFont(font1);

		optB = new JRadioButton("Option B");
		optB.setBackground(back);
		optB.setForeground(fore);
		optB.setBounds(180, 370, 80, 25);
		optB.setFont(font1);

		optC = new JRadioButton("Option C");
		optC.setBackground(back);
		optC.setForeground(fore);
		optC.setBounds(275, 370, 80, 25);
		optC.setFont(font1);

		optD = new JRadioButton("Option D");
		optD.setBackground(back);
		optD.setForeground(fore);
		optD.setBounds(370, 370, 80, 25);
		optD.setFont(font1);

		optE = new JRadioButton("Option E");
		optE.setBackground(back);
		optE.setForeground(fore);
		optE.setBounds(470, 370, 80, 25);
		optE.setFont(font1);

		bg = new ButtonGroup();
		bg.add(easy);
		bg.add(medium);
		bg.add(hard);
		bg.add(optA);
		bg.add(optB);
		bg.add(optC);
		bg.add(optD);
		bg.add(optE);
		//
		addListeners();
	}
	private void addListeners() {
		instruction.addActionListener(this);
		exam.addActionListener(this);
		about.addActionListener(this);
		exit.addActionListener(this);
		signin.addActionListener(this);
		signout.addActionListener(this);
		goback.addActionListener(this);
		ready.addActionListener(this);
		nextpage.addActionListener(this);
		previouspage.addActionListener(this);
		firstpage.addActionListener(this);
		lastpage.addActionListener(this);
		submit.addActionListener(this);
		progress.addActionListener(this);
		jump.addActionListener(this);
		mainmenu.addActionListener(this);
		//
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);
		optA.addActionListener(this);
		optB.addActionListener(this);
		optC.addActionListener(this);
		optD.addActionListener(this);
		optE.addActionListener(this);
	}
	private void toSubmit() {
		removeAllAndRepaint(subpanel);
		cont.setTitleAt(0, "Exam Result");
		examResult();
		tocontinue = false;
	}
	private void removeAllAndRepaint(JPanel atmpanel) {
		atmpanel.removeAll();
		atmpanel.revalidate();
		atmpanel.repaint();
	}
	private void addMenuComponents(int orient) {
		removeAllAndRepaint(subpanel);
		switch(orient) {
		case 1:
			subpanel.add(userpanel);
			subpanel.add(instruction);
			subpanel.add(exam);
			subpanel.add(about);
			subpanel.add(exit);
			break;
		case 2:
			subpanel.add(userpanel);
			subpanel.add(signin);
			subpanel.add(exit);
		}
	}
}