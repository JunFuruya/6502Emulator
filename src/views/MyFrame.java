package views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyFrame extends JFrame{

	private static MyFrame frame = new MyFrame();

	/**
	 * コンストラクタ
	 * private にしてgetInstance以外から呼べないようにする
	 */
	private MyFrame() {

	}

	/**
	 * インスタンスを取得する
	 * @return
	 */
	public static MyFrame getInstance() {
		return frame;
	}

	/**
	 * ウィンドウを生成する
	 *
	 * @return
	 */
	public MyFrame build() {
		frame.setJMenuBar(frame.getMyMenuBar());
		frame.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);

		// FIXME magic number
		frame.setTitle("6502Emulator");
		frame.setBounds(0, 0, 800, 600);
		frame.setVisible(true);

		return frame;
	}

	/**
	 * メニューバーを返す
	 * @return
	 */
	private JMenuBar getMyMenuBar() {
		JMenuBar jMenuBar = new JMenuBar();
		System.out.println(jMenuBar.getClass());

		JMenu jMenuFile = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("Open");
		menuItem.addActionListener(new OpenActionListner());
		jMenuFile.add(menuItem);

		jMenuBar.add(jMenuFile);
		return jMenuBar;
	}
}
