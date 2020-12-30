package controllers;

import java.io.File;
import java.io.IOException;

import entities.FrameComponentEntity;
import exceptions.NesFileNotExecutableException;
import models.Cpu6502;
import models.NesRomCartridge;
import models.NesRomFile;

public class MainController extends BaseController {
	private static FrameComponentEntity frameComponentEntity;
	private static NesRomCartridge nesRom;
	private static Cpu6502 cpu = new Cpu6502();
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

	//***************** 以下は Viewから呼び出すメソッド ***************************
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
		try {
			rom = new NesRomFile(romFile.getPath());
			// ROM からプログラムデータを取得する
			cpu.setProgram(rom.getByteProgram());
		} catch (NesFileNotExecutableException | IOException e) {
			e.printStackTrace();
		}
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