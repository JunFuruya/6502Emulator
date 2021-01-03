package models;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SquareSound extends BaseSound {
	@Override
	public void sound(double herz) {
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
			for(int i=0 ; i<buffer.length ; i++) {
				// サンプル1つ分の値を書き込み
				buffer[i] = convertSquareWave(herz);
			}

			line.write(buffer, 0, buffer.length); // 0 から readBytes まで音声データを再生する
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} finally {
			line.drain(); // 再生終了を待つ
			line.close(); // 音声再生終了
		}
	}

	/**
	 * sin 波に変換
	 * @return
	 */
	private byte convertSquareWave(double herz) {
		radian = radian + 2.0 * Math.PI * herz / format.getSampleRate();
		radian = Math.toRadians(Math.toDegrees(radian));
		return (byte) (amplitude * Math.sin(radian));
	}
}
