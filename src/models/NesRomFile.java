package models;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import exceptions.NesFileNotExecutableException;

public class NesRomFile extends RomFile {
	private String filepath;
	private byte[] bytes;
	private byte[] headerBytes = new byte[16];
	private static final String NES = "NES";
	private int prgRomBankNum = 0; //PRGロムバンク数
	private int chrRomBankNum = 0; //CHRロムバンク数

	private static char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	/**
	 * コンストラクタ
	 * @param pathname
	 * @throws NesFileNotExecutableException
	 * @throws IOException
	 */
	public NesRomFile(String pathname) throws NesFileNotExecutableException, IOException {
		super(pathname);
		filepath = pathname;

		try {
			read();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	/**
	 * ファイル読み込み
	 * @return
	 * @return
	 * @throws IOException
	 */
	protected void read() throws IOException {
		InputStream inputStream = new FileInputStream(filepath);
		byte[] bytes = inputStream.readAllBytes();
		inputStream.close();
		this.bytes = bytes;

		// header情報
		headerBytes = Arrays.copyOfRange(bytes, 0, 16); // 16byteまでがHeader
		System.out.println(convertByteToHexString(Arrays.copyOfRange(headerBytes, 0, 3)));
		//PRGロムバンク数
		prgRomBankNum = (int) headerBytes[4];
		System.out.println(prgRomBankNum);
		//CHRロムバンク数
		chrRomBankNum = (int) headerBytes[5];
		System.out.println(chrRomBankNum);
	}

	/**
	 * byte配列から16進数の文字列取得
	 * @param byteArray
	 * @return
	 */
	private String convertByteToHexString(byte[] byteArray) {
		StringBuilder builder = new StringBuilder();

		int len = byteArray.length;
		for (int i = 0; i < len; i++) {
			builder.append(chars[(byteArray[i] >>> 4) & 0x0F]); // 符号なし4ビット右シフト（上の桁）
			builder.append(chars[byteArray[i] & 0x0F]); // 下の桁
		}

		return builder.toString();
	}

	/**
	 * get ROM contents as byte array
	 * @return
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * ROMのバイト配列を16進数の文字列にして返す
	 * @return
	 */
	public String getRomByteHexString() {
		return convertByteToHexString(bytes);
	}

	/**
	 * ROM Headerのバイト配列を16進数の文字列にして返す
	 * @return
	 */
	public String getHeaderHexString() {
		StringBuilder builder = new StringBuilder();
		builder.append(convertByteToHexString(headerBytes));
		builder.append("");
		builder.append("0\n");
		builder.append("1\n");
		//builder.append("2/n");
		//builder.append("3/n");
		//builder.append("4/n");
		//builder.append("5/n");
		//builder.append("6/n");
		//builder.append("7/n");
		//builder.append("8/n");
		//builder.append("9/n");
		//builder.append("11/n");
		//builder.append("12/n");
		//builder.append("13/n");
		//builder.append("14/n");
		//builder.append("15/n");
		//builder.append("16/n");
		return builder.toString();
	}

	/**
	 * プログラムデータを取得する
	 * @return
	 */
	public byte[] getByteProgram() {
		return Arrays.copyOfRange(bytes, 16, prgRomBankNum * 16 * 1024 + 16); // 16kb * バンク数 + Header
	}
}
