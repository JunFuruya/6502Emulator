package controllers;

import java.io.File;

import entities.FrameComponentEntity;
import models.NesRomFile;

public class MainController extends BaseController {
	private static FrameComponentEntity frameComponentEntity;
	private static NesRomFile rom;

	/**
	 * コンストラクタ
	 * private にしてnewできないようにする
	 */
	private MainController() { }

	/**
	 * 処理実行
	 */
	public static void execute() {
		frameComponentEntity = new FrameComponentEntity();
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
	public static void setRomFile(File romFile) {
		rom = new NesRomFile(romFile.getPath());
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
	public static void showBinary() {
		byte[] bytes = rom.getBytes();
		System.out.println(String.format("%02x", bytes[0]));
		System.out.println(String.format("%02x", bytes[1]));
		System.out.println(String.format("%02x", bytes[2]));
		System.out.println(String.format("%02x", bytes[3]));
		System.out.println(String.format("%02x", bytes[4]));
		System.out.println(String.format("%02x", bytes[5]));
		System.out.println(String.format("%02x", bytes[6]));
		System.out.println(String.format("%02x", bytes[7]));

		frameComponentEntity.getEditorLabel().setText(String.format("%02x", bytes[0]));
		//((EditorPanel) swingMap.get("editorPanel")).setBinary(rom.getBytes());
	}
}
