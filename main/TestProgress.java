package game.main;

import javax.swing.JOptionPane;

public class TestProgress {
	
	public TestProgress(String[] userans, int numques) {
		String progress = "Your Current Answer(s): ", space = "";
		//
		progress += "\n";
		for(int index = 0; index < numques; index++) {
			for(int ctr = 5; ctr != userans[index].length(); ctr--) {
				space+="  ";
			}
			if(index < 9) {
				progress += "|| Question No: 0"+(index+1)+" = "+userans[index]+space+" || ";
			} else {
				progress += "|| Question No: "+(index+1)+" = "+userans[index]+space+" || ";
			}
			if(index%3 == 2) {
				progress += "\n";
			}
			space = "";
		}
		JOptionPane.showMessageDialog(TestScreen.getJFrame(), progress, "YOUR EXAM PROGRESS", JOptionPane.DEFAULT_OPTION);
	}
}