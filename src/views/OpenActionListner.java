package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.MainController;

public class OpenActionListner implements ActionListener {
	private MainController controller;

	/**
	 * constructor
	 *
	 * @param controller
	 */
	public OpenActionListner(MainController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new RomFileChooser(controller).getRomFile();
	}
}
