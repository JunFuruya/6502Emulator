package controllers;

import java.io.File;

import models.NesRomFile;
import views.EditorPanel;
import views.MainFrame;

public class MainController extends BaseController {
	static MainController controller = new MainController();
	private MainFrame mainFrame;
	private EditorPanel editorPanel;

	private static NesRomFile rom;

	/**
	 * コンストラクタ
	 * private にしてnewできないようにする
	 */
	private MainController() {

	}

	/**
	 * オブジェクト取得
	 * @return
	 */
	public static MainController getInstance() {
		return controller;
	}

	/**
	 * 処理実行
	 */
	public void execute() {
		mainFrame = MainFrame.getInstance(controller);
	}

	/**
	 * Get the editor panel
	 *
	 * @param editorPanel
	 */
	public void setEditorPanel(EditorPanel editorPanel) {
		this.editorPanel = editorPanel;
	}

	/**
	 * Set an editor panel
	 * @return
	 */
	public EditorPanel getEditorPanel() {
		return editorPanel;
	}

	/**
	 *
	 */
	public void readRom() {
		rom.canRead();
	}

	/**
	 * ROMファイルをセットする
	 */
	public void setRomFile(File romFile) {
		System.out.println(romFile.getPath());
		rom = new NesRomFile(romFile.getPath());

		this.showBinary();
	}

	/**
	 * ROMファイルを取得する
	 */
	public static NesRomFile getRomFIle() {
		return rom;
	}

	/**
	 * ROMの内容を表示する
	 */
	public void showBinary() {
		//try {
		//	byte[] byteArray = rom.read();

			this.getEditorPanel().getLabel().setText("変更しました");

		//} catch (IOException ioe) {
		//	System.out.println(ioe.getMessage());
		//}
	}
}
