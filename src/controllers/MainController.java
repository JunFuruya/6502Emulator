package controllers;

import java.io.File;

import entities.FrameComponentEntity;
import models.Cpu6502;
import models.NesRom;

public class MainController extends BaseController {
	private static FrameComponentEntity frameComponentEntity;
	private static NesRom nesRom;
	private static Cpu6502 cpu = new Cpu6502();

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

	//***************** 以下は Viewから呼び出すメソッド ***************************
	/**
	 * ゲームを開始する
	 *
	 * @param selectedFile
	 */
	public static void startGame(File selectedFile) {
		nesRom = NesRom.getInstance(selectedFile);
		MainController.showBinary();
		nesRom.startProgram();
	}

	/**
	 * ROMの内容を表示する
	 */
	private static void showBinary() {
		Integer[] bytes = nesRom.getRomData();

		StringBuilder builder = new StringBuilder();

		int len = bytes.length;
		for (int i = 0; i < len; i++) {
			builder.append(chars[(bytes[i] >>> 4) & 0x0F]); // 符号なし4ビット右シフト（上の桁）
			builder.append(chars[bytes[i] & 0x0F]); // 下の桁

			if (i % 16 == 15) {
				builder.append("\r\n");
			}
		}

		FrameComponentEntity.getEditorTextArea().setText(builder.toString());
	}
}
