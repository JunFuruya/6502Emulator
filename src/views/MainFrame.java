package views;

import javax.swing.JFrame;

import builders.FrameBuilder;
import controllers.MainController;

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
	public static MainFrame getInstance(MainController controller) {
		return builder.build(frame, controller);
	}
}
