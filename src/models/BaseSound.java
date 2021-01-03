package models;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

public abstract class BaseSound extends InputStream{
	boolean isBigEndian = false; // NES はリトルエンディアン
	protected final long DURATION = 500; // 一度に音を鳴らす時間[ms]
	protected double amplitude = 3; // 現在出力中の音声波形の最大振幅(音量に相当)
	protected double radian = 0.0f;
	protected byte[] buffer = null; // 現在出力中の音声波形データ

	// 出力音声のフォーマットを指定
	protected AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, // オーディオのデータフォーマット
			44100.0f,     // サンプリングレート
			Byte.SIZE,    // 1サンプルを表すビット数
			1,            // モノラル(1)・ステレオ指定(2)
			1,            // フレームのバイト数 = 第3引数 * 第4引数 / 8
			44100.0f,     // 1秒あたりのフレーム数 = 第2引数
			isBigEndian); // ビッグエンディアンかどうか

	protected SourceDataLine line;

	@Override
	public int read() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	protected abstract void sound(double herz);
}
