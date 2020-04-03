package models;

public class ProgramRom extends BaseRom {
	private static int programSize = 0;

	private static ProgramRom rom = new ProgramRom();

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
		this.programSize = 16 * 1024 * num;
		System.out.println(this.programSize);
	}
}
