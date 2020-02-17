package controllers;

import java.io.File;

import entities.FrameComponentEntity;
import models.NesRomFile;

public class MainController extends BaseController {
	private static FrameComponentEntity frameComponentEntity;
	private static NesRomFile rom;

	private static char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

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

		StringBuilder builder = new StringBuilder();

		int len = bytes.length;
		for (int i = 0; i < len; i++) {
			builder.append(chars[(bytes[i] >>> 4) & 0x0F]); // 符号なし4ビット右シフト（上の桁）
			builder.append(chars[bytes[i] & 0x0F]); // 下の桁
		}

		frameComponentEntity.getEditorTextArea().setText(builder.toString());
	}
}
