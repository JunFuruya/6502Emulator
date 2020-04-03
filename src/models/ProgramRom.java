package models;

public class ProgramRom extends BaseRom {
	private int programSize = 0;

	private static ProgramRom rom = new ProgramRom();

	protected ProgramRom() {
		new BaseRom();
	}

	public static ProgramRom getInstance() {
		return rom;
	}
}
