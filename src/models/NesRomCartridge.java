package models;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import exceptions.NesFileNotExecutableException;

public class NesRomCartridge extends BaseCartridge {
	private static NesRomCartridge rom;
	private static NesRomFile romFile;
	private static final String NES = "NES";
	private byte[] romByteData;

	private static ProgramRom programRom = ProgramRom.getInstance();
	private static GraphicRom graphicRom = GraphicRom.getInstance();

	private static char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	/**
	 * Header (16 bytes)
	 * Trainer, if present (0 or 512 bytes)
	 * PRG ROM data (16384 * x bytes)
	 * CHR ROM data, if present (8192 * y bytes)
	 * PlayChoice INST-ROM, if present (0 or 8192 bytes)
	 * PlayChoice PROM, if present (16 bytes Data, 16 bytes CounterOut) (this is often missing, see PC10 ROM-Images for details)
	 */
	private byte[] header = new byte[16];

	/**
	 * ミラーリングタイプ
	 */
	private int mirroringType;
	private static int HORIZONTAL_MIRRORING = 0;
	private static int VERTICAL_MIRROROING = 1;

	/**
	 * battery-backed RAM at $6000-$7FFF
	 */
	private boolean hasBatteryBackedRam = false;

	/**
	 * a 512-byte trainer at $7000-$71FF
	 */
	private boolean hasTrainer = false;

	/**
	 * a four-screen VRAM layout
	 */
	private boolean hasFourScreenVramLayout = false;

	/**
	 * getInstance
	 * @param selectedFile
	 *
	 * @return
	 */
	public static NesRomCartridge getInstance(File selectedFile) {
		rom = new NesRomCartridge();
		rom.setRomFile(selectedFile);
		rom.load();
		return rom;
	}

	/**
	 * ROMファイルをセットする
	 */
	private void setRomFile(File file) {
		try {
			romFile = new NesRomFile(file.getPath());
		} catch (NesFileNotExecutableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ファイルを読み込む
	 * @return
	 */
	public void load() {
		byte[] romData = this.romFile.getBytes();

		int size = romData.length / 2;
		romByteData = new byte[size];

		for(int i = 0; i < size; i++) {
			romByteData[i] = romData[i];
			//System.out.println(addressArray[i]);
		}
	}

	/**
	 * ROMデータを取得する
	 * @return
	 */
	public byte[] getRomData() {
		return romByteData;
	}

	/**
	 * ROMのプログラムを実行する
	 */
	public void execute(Cpu6502 cpu) {
		// プログラムカウンタから現在実行中のアドレスを取得する
		byte address = cpu.getAddressFromPoagramCounter();

		StringBuilder builder = new StringBuilder();

		// 最初の16bitはHeader情報
		for(int i = 0; i < 16; i++) {
			this.header[i] = this.romByteData[i];
			builder.append(this.chars[(romByteData[i] >>> 4) & 0x0F]); // 符号なし4ビット右シフト（上の桁）
			builder.append(this.chars[romByteData[i] & 0x0F]); // 下の桁
		}

		System.out.println(builder.toString());

		/**
		 * 5byte目: プログラム領域のサイズ
		 */
		programRom.setSize((int) romByteData[4]);

		/**
		 * 6byte目: グラフィック領域のサイズ
		 */
		try {
			graphicRom.setSize((int) romByteData[5]);
		} catch (NesFileNotExecutableException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		/**
		 * 7byte bit 0     1 for vertical mirroring, 0 for horizontal mirroring
		 *       bit 1     1 for battery-backed RAM at $6000-$7FFF
		 *       bit 2     1 for a 512-byte trainer at $7000-$71FF
		 *       bit 3     1 for a four-screen VRAM layout
		 *                 This is only available with certain types of mappers,
		 *                 for example type #1 (BoulderDash) and type #5 (Castlevania3).
		 *       bit 4-7   Four lower bits of ROM Mapper Type
		 * The 6502 is little endian
		 */
		this.mirroringType           = (romByteData[6] % 2 == 0) ? HORIZONTAL_MIRRORING : VERTICAL_MIRROROING;
		this.hasBatteryBackedRam     = ((romByteData[6] >>> 1) % 2 == 0) ? false : true;
		this.hasTrainer              = ((romByteData[6] >>> 2) % 2 == 0) ? false : true;
		this.hasFourScreenVramLayout = ((romByteData[6] >>> 3) % 2 == 0) ? false : true;

		System.out.println(mirroringType);
		System.out.println(hasTrainer);

		/**
		 * 8byte bit 0-3   Reserved, must be zeroes!
		 *       bit 4-7   Four higher bits of ROM Mapper Type
		 * The 6502 is little endian
		 *
		 * bit 0
		 * bit 1 １：バッテリーバックアップＲＡＭ($6000-$7FFF)
		 * bit 2 1：512byte trainer ($7000-$71FF)
		 * bit 3 1：four-screen VRAM?
		 * bit 4-15 マッパー情報
		 *       0　- バンク切り替え無し		No mapper             All 32kB ROM + 8kB VROM games
		 *       1　- MMC1						Nintendo MMC1         Megaman2, Bomberman2, etc.
		 *       2　- 74HC161/74HC32			Simple ROM switch     Castlevania, LifeForce, many games hacked for use with FFE copier
		 *       3　- VROM Switch				Simple ROM switch     Castlevania, LifeForce, many games hacked for use with FFE copier
		 *       4　- MMC3						Nintendo MMC3         SilverSurfer, SuperContra, Immortal, etc.
		 *       5　- MMC5						Nintendo MMC5         Castlevania3
		 *       6　- F4xxx(FFE)				FFE F4xxx             F4xxx games off FFE CDROM
		 *       7　- ROM Switch				32kB ROM switch       WizardsAndWarriors, Solstice, etc.
		 *       8　- F3xxx(FFE)				FFE F3xxx             F3xxx games off FFE CDROM
		 *       9　- MMC2						Nintendo MMC2         Punchout
		 *       10 - MMC4						Nintendo MMC4         Punchout2
		 *       11 - ＃１１					ColorDreams chip      CrystalMines, TaginDragon, etc.
		 *       12 - F6xxx(FFE)				FFE F6xxx             F6xxx games off FFE CDROM
		 *       15 - 100-in-1　カートリッジ	100-in-1 switch       100-in-1 cartridge
		 *       16 - Bandai					Bandai chip           Japanese DragonBallZ series, etc.
		 *       17 - F8xxx(FFE)				FFE F8xxx             F8xxx games off FFE CDROM
		 *       18 - Jaleco SS8806				Jaleco SS8806 chip    Japanese Baseball3, etc.
		 *       19 - Namcot 106				Namcot 106 chip       Japanese GhostHouse2, Baseball90, etc.
		 *       20 - ディスクシステム			Nintendo DiskSystem   Reserved. Don't use this mapper!
		 *       21 - konami VRC4				Konami VRC4           Japanese WaiWaiWorld2, etc.
		 *       22 - konami VRC2#1				Konami VRC2 (a)       Japanese TwinBee3
		 *       23 - konami VRC2#2				Konami VRC2 (b)       Japanese WaiWaiWorld, MoonWindLegend, etc.
		 *       24 - konami VRC6				Konami VRC6           ???
		 *       32 - Irem G-101				Irem G-101 chip       Japanese ImageFight, etc.
		 *       33 - Taito TC0190/TC0350		Taito TC0190/TC0350   Japanese PowerBlazer
		 *       34 - iNES Mapper #34			32kB ROM switch       ImpossibleMission2 and DeadlyTowers
		 * ８－１５　　　　　　予約済み
		 */

		// Trainer がある場合は、512byte分進めて
		int from = 0;
		if (this.hasTrainer) {
			// 8byte は header
			from = from + 512;
		}
		this.programRom.setProgram(Arrays.copyOfRange(romByteData, from, from + programRom.getSize()));

		// Header の処理が終わったので、次の行に進む。
		cpu.setAddress(1);
		// 受け取ったROMデータを実行する。
		cpu.execute(ProgramRom.getInstance());
	}
}

