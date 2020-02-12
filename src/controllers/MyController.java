package controllers;

import java.io.File;

import models.MyRomFile;

public class MyController {
	static MyController controller = new MyController();

	private static MyRomFile rom;

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
	 * ROMファイルをセットする
	 */
	public void setRomFile(File romFile) {
		System.out.println(romFile.getName());
		rom = new MyRomFile(romFile.getPath());
	}

	/**
	 * ROMファイルを取得する
	 */
	public static MyRomFile getRomFIle() {
		return rom;
	}
}
