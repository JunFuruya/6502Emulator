package models;

import java.util.Arrays;

public class ProgramRom extends BaseRom {
	private static int programSize = 0;

	private static ProgramRom rom = new ProgramRom();

	private byte[] programData;

	protected ProgramRom() {
		new BaseRom();
	}

	public static ProgramRom getInstance() {
		return rom;
	}

	/**
	 * 5byte目: プログラム領域のサイズ
	 *
	 * 横１行16byte
	 * 縦 1024行 = 4 * 256 = 4 * 16 * 16 = 4 * [00-FF] = [000-3FF]
	 *
	 * zelda の場合は5byte目が「8」-> 2 * [$0000-$FFFF]までがプログラム
	 *
	 * @param num
	 */
	public void setSize(int num) {
		programSize = 16 * 1024 * num;
		System.out.println(programSize);
	}

	/**
	 * プログラムデータをセットする
	 *
	 * @param programData
	 */
	public void setProgram(byte[] programData) {
		this.programData = programData;
		System.out.println(this.programData[this.programSize - 1]);
	}

	/**
	 * プログラムデータを取得する
	 *
	 * @return
	 */
	public int getSize() {
		return programSize;
	}

	/**
	 * 指定した番号（アドレス）のデータを取得する。
	 *
	 * @param num
	 * @return
	 */
	public byte[] getProgramData(int num) {
		int from = num * 16 + 1;
		return Arrays.copyOfRange(this.programData, num, from + 16);
	}
}
