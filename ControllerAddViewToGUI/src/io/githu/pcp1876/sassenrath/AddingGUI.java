package io.githu.pcp1876.sassenrath;

import SassenrathGUI.Host;
import anotherAttempt.Windy;

public class AddingGUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Host h = new Host();
		Windy w = new Windy();
		
		h.getSplitPane().add(w.getFrame());
	}

}
