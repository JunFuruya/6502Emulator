package views;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controllers.MainController;

public class RomFileChooser extends JFileChooser{
	private String filterText = "ROMファイル";
	private String extention = "nes";
	private JButton button = new JButton("Open");

	/**
	 * コンストラクタ
	 */
	public RomFileChooser() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(filterText, extention);
		this.addChoosableFileFilter(filter);
	}

	/**
	 * ROMファイルを取得する
	 */
	public void getRomFile() {
		if (this.showDialog(button, "開く") == JFileChooser.APPROVE_OPTION ) {
			MainController.setRomFile(this.getSelectedFile());
			MainController.showRomContent();
		}
	}
}
