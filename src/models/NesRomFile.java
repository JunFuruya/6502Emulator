package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class NesRomFile extends File {
	private String filepath;
	private byte[] bytes;
	private String hexNumberString;
	private static final String NES = "NES";

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
		// byte -> String
		setHexNumber();
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
	}

	/**
	 *
	 *
	 * @return
	 */
	private void setHexNumber() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < bytes.length ; i++) {
			builder.append(String.format("%02x", bytes[i]));
		}

		this.hexNumberString = builder.toString();
	}

	/**
	 * get ROM contents as byte array
	 * @return
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * get ROM contents as a string
	 */
	public String getHexNumber() {
		return hexNumberString;
	}

	/**
	 * Check the first 3 charactoers matches "NES"
	 *
	 * @return
	 */
	public boolean isNesFile() {
		return hexNumberString.startsWith(NES);
	}
}
