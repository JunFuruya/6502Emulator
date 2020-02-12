package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenActionListner implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		new RomFileChooser().getRomFile();
	}
}
