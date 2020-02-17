package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenActionListner implements ActionListener {
	private RomFileChooser fileChooser;

	/**
	 * constructor
	 *
	 * @param controller
	 */
	public OpenActionListner(RomFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public OpenActionListner() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fileChooser.getRomFile();
	}
}
