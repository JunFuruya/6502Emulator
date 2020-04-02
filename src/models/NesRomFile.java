package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import exceptions.NesFileNotExecutableException;

public class NesRomFile extends File {
	private String filepath;
	private byte[] bytes;
	private static final String NES = "NES";
	private byte[] header;
	private int programSize;
	private int graphicSize;

	/**
	 *
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

	/**
	 * ファイル読み込み
	 * @return
	 * @return
	 * @throws IOException
	 * @throws NesFileNotExecutableException
	 */
	private void read() throws IOException, NesFileNotExecutableException {
		InputStream inputStream = new FileInputStream(filepath);
		byte[] bytes = inputStream.readAllBytes();
		inputStream.close();

		this.header = Arrays.copyOfRange(bytes, 0, 8);
		/**
		 * Header (16 bytes)
		 * Trainer, if present (0 or 512 bytes)
		 * PRG ROM data (16384 * x bytes)
		 * CHR ROM data, if present (8192 * y bytes)
		 * PlayChoice INST-ROM, if present (0 or 8192 bytes)
		 * PlayChoice PROM, if present (16 bytes Data, 16 bytes CounterOut) (this is often missing, see PC10 ROM-Images for details)
		 */


		/**
		 * 5byte: プログラム領域のサイズ
		 *
		 * 横１行16byte
		 * 縦 1024行 = 4 * 256 = 4 * 16 * 16 = 4 * [00-FF] = [000-3FF]
		 *
		 * zelda の場合は5byte目が「8」-> 2 * [$0000-$FFFF]までがプログラム
		 */
		this.programSize = 16 * 1024 * bytes[4];
		this.programRom = new ProgramRom(Arrays.copyOfRange(bytes, this.header.length, this.header.length + this.programSize));

		/**
		 * 6byte: グラフィック領域のサイズ
		 *
		 * 1以上ならROMのサイズを表示し、「0」の場合はCPU上のRAMにコピーして使用する
		 *
		 * TODO zelda の場合は6byte目が「0」なのでCHR RAMを使用する
		 */
		if (bytes[5] == 0) {
			throw new NesFileNotExecutableException();
		} else {
			graphicSize = 8 * 1024 * bytes[5];
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
		mirroringType           = (bytes[6] % 2 == 0) ? HORIZONTAL_MIRRORING : VERTICAL_MIRROROING;
		hasBatteryBackedRam     = ((bytes[6] >>> 1) % 2 == 0) ? false : true;
		hasTrainer              = ((bytes[6] >>> 2) % 2 == 0) ? false : true;
		hasFourScreenVramLayout = ((bytes[6] >>> 3) % 2 == 0) ? false : true;

		/**
		 * 8byte bit 0-3   Reserved, must be zeroes!
		 *       bit 4-7   Four higher bits of ROM Mapper Type
		 * The 6502 is little endian
		 *
		 *          bit 0
　　　　　　　　　　bit 1 １：バッテリーバックアップＲＡＭ($6000-$7FFF)
　　　　　　　　　　bit 2 １：512byte trainer ($7000-$71FF)
　　　　　　　　　　bit 3 １：four-screen VRAM?
　　　　　　　　　　bit 4-15 マッパー情報
　　　　　　　　　　　　　　0　- バンク切り替え無し
　　　　　　　　　　　　　　1　- MMC1
　　　　　　　　　　　　　　2　- 74HC161/74HC32
　　　　　　　　　　　　　　3　- VROM Switch
　　　　　　　　　　　　　　4　- MMC3
　　　　　　　　　　　　　　5　- MMC5
　　　　　　　　　　　　　　6　- F4xxx(FFE)
　　　　　　　　　　　　　　7　- ROM Switch
　　　　　　　　　　　　　　8　- F3xxx(FFE)
　　　　　　　　　　　　　　9　- MMC2
　　　　　　　　　　　　　　10 - MMC4
　　　　　　　　　　　　　　11 - ＃１１
　　　　　　　　　　　　　　12 - F6xxx(FFE)
　　　　　　　　　　　　　　15 - 100-in-1　カートリッジ
　　　　　　　　　　　　　　16 - Bandai
　　　　　　　　　　　　　　17 - F8xxx(FFE)
　　　　　　　　　　　　　　18 - Jaleco SS8806
　　　　　　　　　　　　　　19 - Namcot 106
　　　　　　　　　　　　　　20 - ディスクシステム
　　　　　　　　　　　　　　21 - konami VRC4
　　　　　　　　　　　　　　22 - konami VRC2#1
　　　　　　　　　　　　　　23 - konami VRC2#2
　　　　　　　　　　　　　　24 - konami VRC6
　　　　　　　　　　　　　　32 - Irem G-101
　　　　　　　　　　　　　　33 - Taito TC0190/TC0350
　　　　　　　　　　　　　　34 - iNES Mapper #34

0        No mapper             All 32kB ROM + 8kB VROM games
1        Nintendo MMC1         Megaman2, Bomberman2, etc.
2        Simple ROM switch     Castlevania, LifeForce, many games
                               hacked for use with FFE copier
3        Simple VROM switch    QBert, PipeDream, Cybernoid, many
                               Japanese games
4        Nintendo MMC3         SilverSurfer, SuperContra, Immortal, etc.
5        Nintendo MMC5         Castlevania3
6        FFE F4xxx             F4xxx games off FFE CDROM
7        32kB ROM switch       WizardsAndWarriors, Solstice, etc.
8        FFE F3xxx             F3xxx games off FFE CDROM
9      - Nintendo MMC2         Punchout
10       Nintendo MMC4         Punchout2
11       ColorDreams chip      CrystalMines, TaginDragon, etc.
12     - FFE F6xxx             F6xxx games off FFE CDROM
15       100-in-1 switch       100-in-1 cartridge
16       Bandai chip           Japanese DragonBallZ series, etc.
17       FFE F8xxx             F8xxx games off FFE CDROM
18       Jaleco SS8806 chip    Japanese Baseball3, etc.
19       Namcot 106 chip       Japanese GhostHouse2, Baseball90, etc.
20       Nintendo DiskSystem   Reserved. Don't use this mapper!
21       Konami VRC4           Japanese WaiWaiWorld2, etc.
22       Konami VRC2 (a)       Japanese TwinBee3
23       Konami VRC2 (b)       Japanese WaiWaiWorld, MoonWindLegend, etc.
24     - Konami VRC6           ???
32       Irem G-101 chip       Japanese ImageFight, etc.
33       Taito TC0190/TC0350   Japanese PowerBlazer
34       32kB ROM switch       ImpossibleMission2 and DeadlyTowers


　　　　　　８－１５　　　　　　予約済み
		 */

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
