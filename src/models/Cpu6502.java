package models;

public class Cpu6502 extends BaseCpu{
	/**
	 * アキュムレータ
	 */
	private byte A;

	/**
	 * インデックスレジスタ（X)
	 */
	private byte X;

	/**
	 * インデックスレジスタ（Y)
	 */
	private byte Y;

	/**
	 * スタックポインタ
	 */
	private byte S;

	/**
	 * ステータスレジスタ
	 */
	private byte P;

	/**
	 * プラグラムカウンタ
	 */
	private byte[] PC = new byte[1];

	/**
	 * 転送命令 メモリからAにロードします。[N.0.0.0.0.0.Z.0]
	 */
	public void LDA(byte bytes) {
		A = bytes;
	}

	/**
	 * 転送命令 メモリからXにロードします。[N.0.0.0.0.0.Z.0]
	 */
	public void LDX(byte bytes) {
		X = bytes;
	}

	/**
	 * 転送命令 メモリからYにロードします。[N.0.0.0.0.0.Z.0]
	 */
	public void LDY(byte bytes) {
		Y = bytes;
	}

	/**
	 * 転送命令 Aからメモリにストアします。[0.0.0.0.0.0.0.0]
	 */
	public byte STA() {
		return A;
	}

	/**
	 * 転送命令 Xからメモリにストアします。[0.0.0.0.0.0.0.0]
	 */
	public byte STX() {
		return X;
	}

	/**
	 * 転送命令 Yからメモリにストアします。[0.0.0.0.0.0.0.0]
	 */
	public byte STY() {
		return Y;
	}

	/**
	 * 転送命令 AをXへコピーします。[N.0.0.0.0.0.Z.0]
	 */
	public void TAX() {
		X = A;
	}

	/**
	 * 転送命令 AをYへコピーします。[N.0.0.0.0.0.Z.0]
	 */
	public void TAY() {
		Y = A;
	}

	/**
	 * 転送命令 SをXへコピーします。[N.0.0.0.0.0.Z.0]
	 */
	public void TSX() {
		X = S;
	}

	/**
	 * 転送命令 XをAへコピーします。[N.0.0.0.0.0.Z.0]
	 */
	public void TXA() {
		A = X;
	}

	/**
	 * 転送命令 XをSへコピーします。[N.0.0.0.0.0.Z.0]
	 */
	public void TXS() {
		S = X;
	}

	/**
	 * 転送命令 YをAへコピーします。[N.0.0.0.0.0.Z.0]
	 */
	public void TYA() {
		A = Y;
	}

	/**
	 * 算術命令 (A + メモリ + キャリーフラグ) を演算して結果をAへ返します。[N.V.0.0.0.0.Z.C]
	 */
	public void ADC() {}

	/**
	 * 算術命令 Aとメモリを論理AND演算して結果をAへ返します。[N.0.0.0.0.0.Z.0]
	 */
	public void AND() {}

	/**
	 * 算術命令 Aまたはメモリを左へシフトします。[N.0.0.0.0.0.Z.C]
	 */

	public void ASL() {}

	/**
	 * 算術命令 Aとメモリをビット比較演算します。[N.V.0.0.0.0.Z.0]
	 */
	public void BIT() {}

	/**
	 * 算術命令 Aとメモリを比較演算します。[N.0.0.0.0.0.Z.C]
	 */
	public void CMP() {}

	/**
	 * 算術命令 Xとメモリを比較演算します。[N.0.0.0.0.0.Z.C]
	 */
	public void CPX() {}

	/**
	 * 算術命令 Yとメモリを比較演算します。[N.0.0.0.0.0.Z.C]
	 */
	public void CPY() {}

	/**
	 * 算術命令 メモリをデクリメントします。[N.0.0.0.0.0.Z.0]
	 */
	public void DEC() {}

	/**
	 * 算術命令 Xをデクリメントします。[N.0.0.0.0.0.Z.0]
	 */
	public void DEX() {}

	/**
	 * 算術命令 Yをデクリメントします。[N.0.0.0.0.0.Z.0]
	 */
	public void DEY() {}

	/**
	 * 算術命令 Aとメモリを論理XOR演算して結果をAへ返します。[N.0.0.0.0.0.Z.0]
	 */
	public void EOR() {}

	/**
	 * 算術命令 メモリをインクリメントします。[N.0.0.0.0.0.Z.0]
	 */
	public void INC() {}

	/**
	 * 算術命令 Xをインクリメントします。[N.0.0.0.0.0.Z.0]
	 */
	public void INX() {}

	/**
	 * 算術命令 Yをインクリメントします。[N.0.0.0.0.0.Z.0]
	 */
	public void INY() {}

	/**
	 * 算術命令 Aまたはメモリを右へシフトします。[N.0.0.0.0.0.Z.C]
	 */
	public void LSR() {}

	/**
	 * 算術命令 Aとメモリを論理OR演算して結果をAへ返します。[N.0.0.0.0.0.Z.0]
	 */
	public void ORA() {}

	/**
	 * 算術命令 Aまたはメモリを左へローテートします。[N.0.0.0.0.0.Z.C]
	 */
	public void ROL() {}

	/**
	 * 算術命令 Aまたはメモリを右へローテートします。[N.0.0.0.0.0.Z.C]
	 */
	public void ROR() {}

	/**
	 * 算術命令 (A - メモリ - キャリーフラグの反転) を演算して結果をAへ返します。[N.V.0.0.0.0.Z.C]
	 */
	public void SBC() {}

	/**
	 * スタック命令 Aをスタックにプッシュダウンします。[0.0.0.0.0.0.0.0]
	 */
	public void PHA() {}

	/**
	 * スタック命令 Pをスタックにプッシュダウンします。[0.0.0.0.0.0.0.0]
	 */
	public void PHP() {}

	/**
	 * スタック命令 スタックからAにポップアップします。[N.0.0.0.0.0.Z.0]
	 */
	public void PLA() {}

	/**
	 * スタック命令 スタックからPにポップアップします。[N.V.R.B.D.I.Z.C]
	 */
	public void PLP() {}

	/**
	 * ジャンプ命令 アドレスへジャンプします。[0.0.0.0.0.0.0.0]
	 */
	public void JMP() {}

	/**
	 * ジャンプ命令 サブルーチンを呼び出します。[0.0.0.0.0.0.0.0]
	 */
	public void JSR() {}

	/**
	 * ジャンプ命令 サブルーチンから復帰します。[0.0.0.0.0.0.0.0]
	 */
	public void RTS() {}

	/**
	 * ジャンプ命令 割り込みルーチンから復帰します。[N.V.R.B.D.I.Z.C]
	 */
	public void RTI() {}

	/**
	 * 分岐命令 キャリーフラグがクリアされている時にブランチします。[0.0.0.0.0.0.0.0]
	 */
	public void BCC() {}

	/**
	 * 分岐命令 キャリーフラグがセットされている時にブランチします。[0.0.0.0.0.0.0.0]
	 */
	public void BCS() {}

	/**
	 * 分岐命令 ゼロフラグがセットされている時にブランチします。[0.0.0.0.0.0.0.0]
	 */
	public void BEQ() {}

	/**
	 * 分岐命令 ネガティブフラグがセットされている時にブランチします。[0.0.0.0.0.0.0.0]
	 */
	public void BMI() {}

	/**
	 * 分岐命令 ゼロフラグがクリアされている時にブランチします。[0.0.0.0.0.0.0.0]
	 */
	public void BNE() {}

	/**
	 * 分岐命令 ネガティブフラグがクリアされている時にブランチします。[0.0.0.0.0.0.0.0]
	 */
	public void BPL() {}

	/**
	 * 分岐命令 オーバーフローフラグがクリアされている時にブランチします。[0.0.0.0.0.0.0.0]
	 */
	public void BVC() {}

	/**
	 * 分岐命令 オーバーフローフラグがセットされている時にブランチします。[0.0.0.0.0.0.0.0]
	 */
	public void BVS() {}

	/**
	 * フラグ変更命令 キャリーフラグをクリアします。[0.0.0.0.0.0.0.C]
	 */
	public void CLC() {}

	/**
	 * フラグ変更命令 BCDモードから通常モードに戻ります。ファミコンでは実装されていません。[0.0.0.0.D.0.0.0]
	 */
	public void CLD() {}

	/**
	 * フラグ変更命令 IRQ割り込みを許可します。[0.0.0.0.0.I.0.0]
	 */
	public void CLI() {}

	/**
	 * フラグ変更命令 オーバーフローフラグをクリアします。[0.V.0.0.0.0.0.0]
	 */
	public void CLV() {}

	/**
	 * フラグ変更命令 キャリーフラグをセットします。[0.0.0.0.0.0.0.C]
	 */
	public void SEC() {}

	/**
	 * フラグ変更命令 BCDモードに設定します。ファミコンでは実装されていません。[0.0.0.0.D.0.0.0]
	 */
	public void SED() {}

	/**
	 * フラグ変更命令 IRQ割り込みを禁止します。[0.0.0.0.0.I.0.0]
	 */
	public void SEI() {}

	/**
	 * その他の命令 ソフトウェア割り込みを起こします。[0.0.0.B.0.0.0.0]
	 */
	public void BRK() {}

	/**
	 * その他の命令 空の命令を実行します。[0.0.0.0.0.0.0.0]
	 */
	public void NOP() {}
}