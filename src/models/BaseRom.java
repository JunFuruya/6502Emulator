package models;

public class BaseRom {

	byte[] romDataArray;

	/**
	 * 未使用
	 */
	protected BaseRom() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * 指定したアドレスのデータを読み込みます。
	 * @param address
	 * @return
	 */
	public short read(short address) {
		return romDataArray[address];
	}
}
