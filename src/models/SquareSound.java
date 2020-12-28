package models;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SquareSound extends BaseSound {
	// NES はリトルエンディアン
	boolean isBigEndian = false;
    private final long DURATION = 3000; // 一度に音を鳴らす時間[ms]
    private double amplitude = 3; // 現在出力中の音声波形の最大振幅(音量に相当)
	double radian = 0.0f;

    // 変数宣言
    byte[] buffer = null; // 現在出力中の音声波形データ

	// 出力音声のフォーマットを指定
	AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED ,    // オーディオのデータフォーマット
			44100.0f ,                           // サンプリングレート
			Byte.SIZE ,                          // 1サンプルを表すビット数
			1,                                   // モノラル(1)・ステレオ指定(2)
			1,                                   // フレームのバイト数 = 第3引数 * 第4引数 / 8
			44100.0f ,                           // 1秒あたりのフレーム数 = 第2引数
			isBigEndian );                       // ビッグエンディアンかどうか

	SourceDataLine line;

    public void beep() {
    	// 音声波形データ格納用のバッファを作成
    	int bufferSize  = (int)(format.getSampleRate() * format.getFrameSize() * DURATION / 1000.0 );

    	buffer = new byte[bufferSize];

    	// 音声出力ラインを作成
    	DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		try {
			line = (SourceDataLine) AudioSystem.getLine(info);

	    	// 音声出力ラインを開く
	    	line.open(format, buffer.length);
	    	line.start();

	        // 新しい音階の波形データをバッファに書き込み
	        for( int i=0 ; i<buffer.length ; i++ )
	        {
	            // サンプル1つ分の値を書き込み
	            // 波形はサイン波
	            radian = radian + 2.0 * Math.PI * 440.0d / format.getSampleRate(); // 440hz
	            radian = Math.toRadians(Math.toDegrees(radian));
	            double tmp = amplitude * Math.sin(radian);
	            buffer[i] = (byte)tmp;
	        }

    		// 音声データ再生
    		line.write(buffer, 0, buffer.length); // 0 から readBytes まで再生する

		} catch (LineUnavailableException e) {
			System.out.println(e.getMessage());
    	} finally {
    		// 再生終了を待つ
    		line.drain();

    		// 音声再生終了
    		line.close();
    	}
	}
}
