package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class NesRomFile extends File {
	private String filepath;
	private byte[] bytes;
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
	 * get ROM contents as byte array
	 * @return
	 */
	public byte[] getBytes() {
		return bytes;
	}
}
