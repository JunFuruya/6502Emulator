package views;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controllers.MainController;

public class RomFileChooser extends JFileChooser{
	private String filterText = "ROMファイル";
	private String extention = "nes";
	private MainController controller;

	/**
	 * コンストラクタ
	 */
	public RomFileChooser(MainController controller) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(filterText, extention);
		this.addChoosableFileFilter(filter);
		this.controller = controller;
	}

	/**
	 * ROMファイルを取得する
	 */
	public void getRomFile() {
		JButton button = new JButton("Open");
		if (this.showDialog(button, "開く") == JFileChooser.APPROVE_OPTION ) {
			controller.setRomFile(this.getSelectedFile());
		}
	}
}
