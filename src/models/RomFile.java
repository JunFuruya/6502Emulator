package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import exceptions.NesFileNotExecutableException;

public class RomFile extends File {
	protected String filepath;
	protected byte[] bytes;

	public RomFile(String pathname) throws IOException, NesFileNotExecutableException {
		super(pathname);
		filepath = pathname;
		read();
	}

	/**
	 * ファイル読み込み
	 * @return
	 * @return
	 * @throws IOException
	 * @throws NesFileNotExecutableException
	 */
	protected void read() throws IOException, NesFileNotExecutableException {
		InputStream inputStream = new FileInputStream(filepath);
		byte[] bytes = inputStream.readAllBytes();
		inputStream.close();

		this.bytes = bytes;
	}

	/**
	 * get file contents as byte array
	 * @return
	 */
	public byte[] getBytes() {
		return bytes;
	}
}
