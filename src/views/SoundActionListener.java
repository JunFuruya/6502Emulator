package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.SquareSound;

public class SoundActionListener implements ActionListener  {

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("SoundActionListener");
		SquareSound squareSound = new SquareSound();
		squareSound.beep();
		//System.out.println("Beep End");
	}
}
