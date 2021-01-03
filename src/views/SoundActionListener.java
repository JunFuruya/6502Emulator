package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.SquareSound;
import models.TriangleSound;

public class SoundActionListener implements ActionListener  {

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("SoundActionListener");
		SquareSound squareSound = new SquareSound();
		TriangleSound traiangleSound = new TriangleSound();

		traiangleSound.sound(261.626);
		traiangleSound.sound(293.665);
		traiangleSound.sound(329.628);
		traiangleSound.sound(349.228);
		traiangleSound.sound(391.995);
		traiangleSound.sound(440.0f);
		traiangleSound.sound(493.883);
		traiangleSound.sound(523.251);

		squareSound.sound(261.626);
		squareSound.sound(293.665);
		squareSound.sound(329.628);
		squareSound.sound(349.228);
		squareSound.sound(391.995);
		squareSound.sound(440.0f);
		squareSound.sound(493.883);
		squareSound.sound(523.251);

		//System.out.println("Beep End");
	}
}
