package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class OpenActionListner implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		RomFileChooser fileChooser = new RomFileChooser();

		//FileFilter filter = new FileFilter();
		//fileChooser.addChoosableFileFilter(filter);

		JButton button = new JButton("Open");

		if (fileChooser.showDialog(button, "開く") == JFileChooser.APPROVE_OPTION ) {
			File file = fileChooser.getSelectedFile();
			System.out.println(file.getName());
		}
	}
}
