package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class NesRomFile extends File {
	private String filepath;
	private byte[] bytes;
	private byte[] headerBytes = new byte[16];
	private static final String NES = "NES";

	private static char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	/**
	 * コンストラクタ
	 * @param pathname
	 */
	public NesRomFile(String pathname) {
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
	private void read() throws IOException {
		InputStream inputStream = new FileInputStream(filepath);
		byte[] bytes = inputStream.readAllBytes();
		inputStream.close();
		this.bytes = bytes;

		// header情報
		headerBytes = Arrays.copyOfRange(bytes, 0, 16); // 16byteまでがHeader
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
		return convertByteToHexString(headerBytes);
	}
}
