package models;

import exceptions.NesFileNotExecutableException;

public class GraphicRom extends BaseRom{
	private static int graphicSize = 0;

	private static GraphicRom rom = new GraphicRom();

	/**
	 * オブジェクトを取得する
	 * @return
	 */
	public static GraphicRom getInstance() {
		return rom;
	}

	/**
	 * 6byte: グラフィック領域のサイズ
	 *
	 * 1以上ならROMのサイズを表示し、「0」の場合はCPU上のRAMにコピーして使用する
	 *
	 * TODO zelda の場合は6byte目が「0」なのでCHR RAMを使用する
	 * @throws NesFileNotExecutableException
	 */
	public void setSize(int num) throws NesFileNotExecutableException {
		if (num == 0) {
			// TODO ゼルダとか動くようにする。
			throw new NesFileNotExecutableException();
		} else {
			graphicSize = 8 * 1024 * num;
		}
		System.out.println(this.graphicSize);
	}

}
