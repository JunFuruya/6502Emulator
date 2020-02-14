package controllers;

import java.io.File;

import models.NesRomFile;

public class MyController extends BaseController {
	static MyController controller = new MyController();

	private static NesRomFile rom;

	/**
	 * コンストラクタ
	 * private にしてnewできないようにする
	 */
	private MyController() {

	}

	/**
	 * オブジェクト取得
	 * @return
	 */
	public static MyController getInstance() {
		return controller;
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
		System.out.println(romFile.getName());
		rom = new NesRomFile(romFile.getPath());
	}

	/**
	 * ROMファイルを取得する
	 */
	public static NesRomFile getRomFIle() {
		return rom;
	}
}
