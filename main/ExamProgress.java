package game.main;

import javax.swing.JOptionPane;

public class ExamProgress {

	public ExamProgress(String[] chosed, int qnum) {
		String progress = "Your Current Answers: ";

		for(int index = 0; index < qnum; index++) {
			if(index%2 == 0) {
				if(index < 10) {
					progress += "\nQuestion No: "+(index+1)+" = "+chosed[index]+"  Question No: "+(index+2)+" = "+chosed[index+1];
				} else if(index >= 10) {
					progress += "\nQuestion No:"+(index+1)+" = "+chosed[index]+"  Question No:"+(index+2)+" = "+chosed[index+1];
				}
			}
		}
		JOptionPane.showMessageDialog(AppScreen.getframe(), progress, "Your Exam Progress", JOptionPane.DEFAULT_OPTION);

	}
}
