package models;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NesRomFile extends File {
	private static final int BYTE_SIZE = 0;
	private byte[] byteArray;

	/**
	 * コンストラクタ
	 * @param pathname
	 */
	public NesRomFile(String pathname) {
		super(pathname);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * ファイル読み込み
	 * @return
	 * @throws IOException
	 */
	public byte[] read() throws IOException {
		FileInputStream inputStream = new FileInputStream(this);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		byte[] buffer = new byte[this.BYTE_SIZE];
		while(true) {
			int len = inputStream.read(buffer);
			if (len < 0) {
				break;
			}
			outputStream.write(buffer, 0 ,len);
		}

		return outputStream.toByteArray();
	}
}
