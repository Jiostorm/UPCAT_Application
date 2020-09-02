package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import game.AppLauncher;

public class AppLogic implements ActionListener{

	private AppBackGround mainpanel;

	private JTabbedPane cont;
	private JPanel subpanel, menupanel, inspanel, aboutpanel, testsetpanel, actualtestpanel, imagepanel, tablepanel;

	private Font hfont, bfont, tfont;

	private JLabel header, insimage;
	private JLabel[] imageQ;
	private JButton test, help, about, exit, back, ready, next, preview, firstpage, lastpage, submit, progress;
	private JTextArea textarea;
	private JRadioButton easy, medium, hard, optA, optB, optC, optD, optE;
	private JTable examtable;

	private ButtonGroup bg;
	private DefaultTableModel defmodel;

	private int activepanel = 0, qctr = 1, qnum;

	private String[] chosed, correct, remark;
	int[] randchosestor;
	int[][] randstor;

	public AppLogic(int width, int height, String imgname) {
		mainpanel = new AppBackGround(width, height, imgname);

		subpanel = new JPanel(null);
		subpanel.setBounds(0, 0, width, height);
		subpanel.setOpaque(false);

		initAll();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		back.setText("Go Back");
		if(ae.getSource() == test) {
			removeAllAndRepaint(menupanel);
			cont.remove(menupanel);
			cont.addTab("Test Settings", testsetpanel);
			testsetpanel.add(back);
			testsetpanel.add(easy);
			testsetpanel.add(medium);
			testsetpanel.add(hard);
			testsetpanel.add(textarea);
			textarea.setText("\n         Welcome!! Dear Student, Please choose one difficulty from the choices in your right before you start taking the UPCAT Exam. GoodLuck! and go get a high score from one of the difficulties and after choosing, just click the \"I'm Ready!\" button below to start. In the exam panel, click the \"Your Progress\""
					+ " button to check your current answer(s).");
			textarea.setBounds(50, 70, 400, 300);
			testsetpanel.add(ready);
			ready.setEnabled(false);
			activepanel = 3;
		}
		if(ae.getSource() == help) {
			removeAllAndRepaint(menupanel);
			cont.remove(menupanel);
			cont.addTab("Instruction", inspanel);
			inspanel.add(back);
			inspanel.add(textarea);
			textarea.setText("\n	The Application consists of three main control buttons to click, click the \"Take Exam\" Button to start the UPCAT exam. If you want to know who develop this application, just click the \"About Developer\" button to view the information about the developer of this application.\r\n\n" + 
					"	The \"Take Exam\" button also consists of another three buttons to choose the difficulty of the exam, in all the three main buttons, just click the \"Go Back\" whenever you want to have access to the three main control buttons.\n\n	If you found any application bug, please report it immediately to the developer; thank you.");
			textarea.setBounds(50, 50, 600, 300);
			activepanel = 1;
		}
		if(ae.getSource() == about) {
			removeAllAndRepaint(menupanel);
			cont.remove(menupanel);
			cont.addTab("About Developer", aboutpanel);
			aboutpanel.add(back);
			aboutpanel.add(textarea);
			aboutpanel.add(insimage);
			textarea.setText("\n	The Developer of this game is George William Sison from 11-Programming 2,He live in Dinaga Canaman Camarines Sur. He developed this application alone with efforts and passion for computer programming. Since his second year in junior high, he picked the computer programming specialization because he likes solving logic and "
					+ "mathematical problems and it is present in the computer programming subject and he will continue his dream to become a professional computer programmer and become a software developer in Microsoft Corporation someday.");
			textarea.setBounds(50, 50, 600, 300);
			activepanel = 2;
		}
		if(ae.getSource() == back) {
			switch(activepanel) {
			case 1: removeAllAndRepaint(inspanel); cont.remove(inspanel); break;
			case 2: removeAllAndRepaint(aboutpanel); cont.remove(aboutpanel); break;
			case 3: bg.clearSelection(); removeAllAndRepaint(testsetpanel); cont.remove(testsetpanel); break;
			case 4: removeAllAndRepaint(tablepanel); cont.remove(tablepanel); break;
			}
			cont.addTab("Main Menu", menupanel);
			menupanel.add(test);
			menupanel.add(help);
			menupanel.add(about);
			menupanel.add(exit);
			textarea.setText("");
			activepanel = 0;
		}
		if(ae.getSource() == exit) {
			int xmes = JOptionPane.showConfirmDialog(AppScreen.getframe(), "Are You Sure You Want to Exit?", "Confirmation Dialog", JOptionPane.YES_NO_OPTION);
			if(xmes == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		if(ae.getSource() == ready) {
			int xmes = JOptionPane.showConfirmDialog(testsetpanel, "You Cannot Change The Difficulty If You Already Chose One, \nAre You Okay With Your Difficulty?", "Confirmation Dialog", JOptionPane.YES_NO_OPTION);
			if(xmes == JOptionPane.YES_OPTION) {
				removeAllAndRepaint(testsetpanel);
				cont.remove(testsetpanel);
				cont.addTab("Actual Examination", actualtestpanel);
				imageQ = new JLabel[qnum];
				chosed = new String[qnum];
				correct = new String[qnum];
				examRandomizing();
				for(int count = 0; count < qnum; count++) {
					imageQ[count] = new JLabel();
					imageQ[count].setBounds(0, 0, 490, 300);
					imageQ[count].setOpaque(true);
					chosed[count] = "Blank";
				}
				addExamComponents();
			}
		}
		if(ae.getSource() == next) {
			removeAllAndRepaint(actualtestpanel);
			if(qctr < qnum) {
				qctr++;
			}
			addExamComponents();
		}
		if(ae.getSource() == preview) {
			removeAllAndRepaint(actualtestpanel);
			if(qctr > 1) {
				qctr--;
			}
			addExamComponents();
		}
		if(ae.getSource() == firstpage) {
			removeAllAndRepaint(actualtestpanel);
			qctr = 1;
			addExamComponents();
		}
		if(ae.getSource() == lastpage) {
			removeAllAndRepaint(actualtestpanel);
			qctr = qnum;
			addExamComponents();
		}
		if(ae.getSource() == submit) {
			int xmes = JOptionPane.showConfirmDialog(actualtestpanel, "Are You Sure You Want To Submit Your Exam?", "Confirmation Dialog", JOptionPane.YES_NO_OPTION);
			if(xmes == JOptionPane.YES_OPTION) {
			removeAllAndRepaint(actualtestpanel);
			cont.remove(actualtestpanel);
			cont.addTab("Exam Result", tablepanel);
			examResult();
			activepanel = 4;
			}
		}
		if(ae.getSource() == progress) {
			new ExamProgress(chosed, qnum);
		}
		//
		if(easy.isSelected()) {
			textarea.setText("\n         Easy Mode: this mode consists of 10 different kinds of question related to Math subject.");
			qnum = 10;
			ready.setEnabled(true);
		}
		if(medium.isSelected()) {
			textarea.setText("\n         Medium Mode: this mode consists of 20 different kinds of question related to Math subject.");
			qnum = 20;
			ready.setEnabled(true);
		}
		if(hard.isSelected()) {
			textarea.setText("\n         Hard Mode: this mode consists of 40 different kinds of question related to Math subject.");
			qnum = 40;
			ready.setEnabled(true);
		}
		if(optA.isSelected()) {
			chosed[qctr-1] = "A";
			System.out.println(qctr+" "+chosed[qctr-1]);
		}
		if(optB.isSelected()) {
			chosed[qctr-1] = "B";
			System.out.println(qctr+" "+chosed[qctr-1]);
		}
		if(optC.isSelected()) {
			chosed[qctr-1] = "C";
			System.out.println(qctr+" "+chosed[qctr-1]);
		}
		if(optD.isSelected()) {
			chosed[qctr-1] = "D";
			System.out.println(qctr+" "+chosed[qctr-1]);
		}
		if(optE.isSelected()) {
			chosed[qctr-1] = "E";
			System.out.println(qctr+" "+chosed[qctr-1]);
		}
	}
	private void addExamComponents() {
		actualtestpanel.add(header);
		header.setText("Question No. "+qctr);

		bg.clearSelection();
		actualtestpanel.add(optA);
		actualtestpanel.add(optB);
		actualtestpanel.add(optC);
		actualtestpanel.add(optD);
		actualtestpanel.add(optE);

		removeAllAndRepaint(imagepanel);
		URL testimgurl = AppLauncher.class.getResource("/img/question/"+correct[qctr-1]+"/"+randstor[qctr-1][randchosestor[qctr-1]]+".png");
		imageQ[qctr-1].setIcon(new ImageIcon(new ImageIcon(testimgurl).getImage().getScaledInstance(490, 300, Image.SCALE_SMOOTH)));
		imagepanel.add(imageQ[qctr-1]);

		actualtestpanel.add(imagepanel);

		actualtestpanel.add(next);
		actualtestpanel.add(preview);
		actualtestpanel.add(firstpage);
		actualtestpanel.add(lastpage);
		actualtestpanel.add(progress);
		if(qctr == qnum) {
			actualtestpanel.add(submit);
		}
	}
	private void examRandomizing() {
		Random rand = new Random();
		int randnum, randchose;
		randchosestor = new int[qnum];
		randstor = new int[qnum][5];

		boolean ifExist;

		for(int ind = 0; ind < qnum; ind++) {
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
			for(int arrctr = 0; arrctr < qnum; arrctr++) {
				if(randnum == randstor[arrctr][randchose]) {
					ifExist = true;
					ind--;
					break;
				}
			}
			if(!ifExist) {
				randstor[ind][randchose] = randnum;
				randchosestor[ind] = randchose;

				switch(randchosestor[ind]) {
				case 0: correct[ind] = "A"; break;
				case 1: correct[ind] = "B"; break;
				case 2: correct[ind] = "C"; break;
				case 3: correct[ind] = "D"; break;
				case 4: correct[ind] = "E"; break;
				}

				System.out.println((ind+1)+". IMG NO: "+randstor[ind][randchose]+" Right Answer: "+correct[ind]);
			}
		}
	}
	private void examResult() {
		remark = new String[qnum];
		for(int i = 0; i < qnum; i++) {
			remark[i] = chosed[i].equalsIgnoreCase(correct[i]) ? "Right" : "Wrong";
			System.out.println("Question No: "+(i+1)+" Remark: "+remark[i]);
		}
		Object[] tableheader = {"Question No", "Your Answer", "Correct Answer", "Remarks"};

		defmodel = new DefaultTableModel();
		defmodel.setColumnIdentifiers(tableheader);

		examtable = new JTable(defmodel);
		examtable.setBackground(Color.DARK_GRAY);
		examtable.setForeground(Color.WHITE);
		examtable.setFont(bfont);
		examtable.setRowHeight(30);

		Object[] tabrow = new Object[4];
		for(int tabindex = 0; tabindex < qnum; tabindex++) {
			tabrow[0] = tabindex+1;
			tabrow[1] = chosed[tabindex];
			tabrow[2] = correct[tabindex];
			tabrow[3] = remark[tabindex];
			defmodel.addRow(tabrow);
		}

		JScrollPane tablescr = new JScrollPane(examtable);
		tablescr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		tablescr.setBounds(100, 70, 500, 300);
		
		header.setText("Your Exam Result");
		header.setBounds(250, 20, 200, 30);
		
		back.setText("Main Menu");
		
		tablepanel.add(tablescr);
		tablepanel.add(header);
		tablepanel.add(back);
	}
	private void initAll() {
		bfont = new Font("Cambria", 5, 12);
		hfont = new Font("Times New Roman", 5, 13);
		tfont = new Font("Times New Roman", 5, 18);
		//
		menupanel = new JPanel(null);
		menupanel.setOpaque(false);
		inspanel = new JPanel(null);
		inspanel.setOpaque(false);
		aboutpanel = new JPanel(null);
		aboutpanel.setOpaque(false);
		testsetpanel = new JPanel(null);
		testsetpanel.setOpaque(false);
		actualtestpanel = new JPanel(null);
		actualtestpanel.setOpaque(false);
		tablepanel = new JPanel(null);
		tablepanel.setOpaque(false);

		imagepanel = new JPanel(null);
		imagepanel.setBorder(BorderFactory.createTitledBorder(""));
		imagepanel.setOpaque(false);
		imagepanel.setBounds(100, 60, 490, 300);

		UIManager.put("TabbedPane.contentOpaque", false);
		cont = new JTabbedPane();
		cont.setBounds(50, 45, 700, 550);
		cont.setFont(hfont);
		//
		header = new JLabel("",JLabel.CENTER);
		header.setBounds(230, 0, 200, 30);
		header.setFont(tfont);
		header.setOpaque(true);
		header.setBackground(Color.DARK_GRAY);
		header.setForeground(Color.WHITE);

		URL insimgurl = AppLauncher.class.getResource("/img/bg/George.png");

		insimage = new JLabel();
		insimage.setBounds(50, 370, 120, 120);
		insimage.setOpaque(true);
		insimage.setIcon(new ImageIcon(new ImageIcon(insimgurl).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
		//
		initButtons();
		//
		textarea = new JTextArea();
		textarea.setBackground(Color.DARK_GRAY);
		textarea.setForeground(Color.WHITE);
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setFont(tfont);
		//
		menupanel.add(test);
		menupanel.add(help);
		menupanel.add(about);
		menupanel.add(exit);
		//
		cont.addTab("Main Menu", menupanel);

		subpanel.add(cont);

		mainpanel.add(subpanel);
		AppScreen.getframe().add(mainpanel);

		addListener();
	}
	private void initButtons() {
		test = new JButton("Start Exam");
		test.setBackground(Color.DARK_GRAY);
		test.setForeground(Color.WHITE);
		test.setBounds(30, 430, 95, 25);
		test.setFont(bfont);

		help = new JButton("Instruction");
		help.setBackground(Color.DARK_GRAY);
		help.setForeground(Color.WHITE);
		help.setBounds(30, 390, 100, 25);
		help.setFont(bfont);

		about = new JButton("About Developer");
		about.setBackground(Color.DARK_GRAY);
		about.setForeground(Color.WHITE);
		about.setBounds(30, 470, 130, 25);
		about.setFont(bfont);

		exit = new JButton("Exit");
		exit.setBounds(600, 470, 70, 25);
		exit.setBackground(Color.DARK_GRAY);
		exit.setForeground(Color.WHITE);
		exit.setFont(bfont);

		back = new JButton("Go Back");
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(Color.WHITE);
		back.setBounds(600, 470, 75, 25);
		back.setFont(bfont);

		ready = new JButton("I'm Ready!");
		ready.setBackground(Color.DARK_GRAY);
		ready.setForeground(Color.WHITE);
		ready.setBounds(200, 400, 90, 25);
		ready.setFont(bfont);

		next = new JButton("Next Page>");
		next.setBackground(Color.DARK_GRAY);
		next.setForeground(Color.WHITE);
		next.setBounds(160, 470, 95, 25);
		next.setFont(bfont);

		preview = new JButton("<Preview Page");
		preview.setBackground(Color.DARK_GRAY);
		preview.setForeground(Color.WHITE);
		preview.setBounds(270, 470, 115, 25);
		preview.setFont(bfont);

		firstpage = new JButton(">First Page<");
		firstpage.setBackground(Color.DARK_GRAY);
		firstpage.setForeground(Color.WHITE);
		firstpage.setBounds(20, 470, 100, 25);
		firstpage.setFont(bfont);

		lastpage = new JButton("<Last Page>");
		lastpage.setBackground(Color.DARK_GRAY);
		lastpage.setForeground(Color.WHITE);
		lastpage.setBounds(420, 470, 100, 25);
		lastpage.setFont(bfont);

		submit = new JButton("^Submit Exam^");
		submit.setBackground(Color.DARK_GRAY);
		submit.setForeground(Color.WHITE);
		submit.setBounds(560, 470, 120, 25);
		submit.setFont(bfont);

		progress = new JButton("Your Progress");
		progress.setBackground(Color.DARK_GRAY);
		progress.setForeground(Color.WHITE);
		progress.setBounds(570, 20, 110, 25);
		progress.setFont(bfont);
		//
		easy = new JRadioButton("Easy Mode");
		easy.setBackground(Color.DARK_GRAY);
		easy.setForeground(Color.WHITE);
		easy.setBounds(550, 100, 100, 30);
		easy.setFont(bfont);

		medium = new JRadioButton("Medium Mode");
		medium.setBackground(Color.DARK_GRAY);
		medium.setForeground(Color.WHITE);
		medium.setBounds(550, 200, 100, 30);
		medium.setFont(bfont);

		hard = new JRadioButton("Hard Mode");
		hard.setBackground(Color.DARK_GRAY);
		hard.setForeground(Color.WHITE);
		hard.setBounds(550, 300, 100, 30);
		hard.setFont(bfont);

		optA = new JRadioButton("Option A");
		optA.setBackground(Color.DARK_GRAY);
		optA.setForeground(Color.WHITE);
		optA.setBounds(110, 380, 70, 30);
		optA.setFont(bfont);

		optB = new JRadioButton("Option B");
		optB.setBackground(Color.DARK_GRAY);
		optB.setForeground(Color.WHITE);
		optB.setBounds(210, 380, 70, 30);
		optB.setFont(bfont);

		optC = new JRadioButton("Option C");
		optC.setBackground(Color.DARK_GRAY);
		optC.setForeground(Color.WHITE);
		optC.setBounds(310, 380, 70, 30);
		optC.setFont(bfont);

		optD = new JRadioButton("Option D");
		optD.setBackground(Color.DARK_GRAY);
		optD.setForeground(Color.WHITE);
		optD.setBounds(410, 380, 71, 30);
		optD.setFont(bfont);

		optE = new JRadioButton("Option E");
		optE.setBackground(Color.DARK_GRAY);
		optE.setForeground(Color.WHITE);
		optE.setBounds(510, 380, 71, 30);
		optE.setFont(bfont);

		bg = new ButtonGroup();
		bg.add(easy);
		bg.add(medium);
		bg.add(hard);
		bg.add(optA);
		bg.add(optB);
		bg.add(optC);
		bg.add(optD);
		bg.add(optE);
	}
	//
	private void removeAllAndRepaint(JPanel atmpanel) {
		atmpanel.removeAll();
		atmpanel.revalidate();
		atmpanel.repaint();
	}
	private void addListener() {
		test.addActionListener(this);
		help.addActionListener(this);
		about.addActionListener(this);
		exit.addActionListener(this);
		back.addActionListener(this);
		ready.addActionListener(this);
		next.addActionListener(this);
		preview.addActionListener(this);
		firstpage.addActionListener(this);
		lastpage.addActionListener(this);
		submit.addActionListener(this);
		progress.addActionListener(this);
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
}