package views;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controllers.MyController;

public class RomFileChooser extends JFileChooser{
	private String filterText = "ROMファイル";
	private String extention = "nes";

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
		JButton button = new JButton("Open");
		if (this.showDialog(button, "開く") == JFileChooser.APPROVE_OPTION ) {
			MyController.getInstance().setRomFile(this.getSelectedFile());
		}
	}
}
