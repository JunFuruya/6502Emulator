package models;

import java.io.IOException;

import exceptions.NesFileNotExecutableException;

public class NesRomFile extends RomFile {
	/**
	 * コンストラクタ
	 * @param pathname
	 * @throws NesFileNotExecutableException
	 * @throws IOException
	 */
	public NesRomFile(String pathname) throws NesFileNotExecutableException, IOException {
		super(pathname);
		filepath = pathname;
		read();
	}
}
