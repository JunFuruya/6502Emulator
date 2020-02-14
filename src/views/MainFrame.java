package views;

import javax.swing.JFrame;

import builders.FrameBuilder;

public class MainFrame extends JFrame{

	private static MainFrame frame = new MainFrame();
	private static FrameBuilder builder = FrameBuilder.getInstance();

	/**
	 * コンストラクタ
	 * private にしてgetInstance以外から呼べないようにする
	 */
	private MainFrame() {

	}

	/**
	 * インスタンスを取得する
	 * @return
	 */
	public static MainFrame getInstance() {
		return builder.build(frame);
	}
}
