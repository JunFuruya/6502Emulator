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
	 * ROM の内容を読みオム
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
	public static NesRomFile getRomFile() {
		return rom;
	}

	/**
	 * ROMの内容を表示する
	 */
	public static void showRomContent() {
		frameComponentEntity.setTextToEditor(rom.getRomByteHexString());
		frameComponentEntity.setHeaderText(rom.getHeaderHexString());
	}
}
