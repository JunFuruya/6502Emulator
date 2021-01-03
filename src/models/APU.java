package models;

public class APU {
	//再生方法
	//I/O【$4015】で設定するチャンネルの再生をオフにする
	//チャンネルのレジスタに情報を設定
	//I/O【$4015】で設定したチャンネルの再生をオンにする

	/**
	 * 周波数（hz）
	 */
	private int herz;

	/**
	 * constructor
	 * @param herz
	 */
	public APU(int herz) {
		this.herz = herz;
	}

	/**
	 * レジスタの値 = CPUのクロック周波数 / (再生したい周波数 * 32) - 1
	 * -> 再生したい周波数 = (CPUのクロック周波数  / (レジスタの値 + 1)) / 32
	 * @param pitch
	 * @return
	 */
	private double getHerz(byte registerdByte) {
		return ((registerdByte + 1) * herz / 32);
	}

	/**
	 * 矩形波
	 */
	private SquareSound squareSound = new SquareSound();

	/**
	 * 三角波
	 */
	private TriangleSound triangleSound = new TriangleSound();

	// TODO ノイズ
	// TODO DPCM 任意の波形


}
