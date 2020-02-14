package builders;

import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controllers.MainController;
import views.EditorPanel;
import views.MainFrame;
import views.OpenActionListner;

public class FrameBuilder {
	private static FrameBuilder builder = new FrameBuilder();
	private static String TITLE = "6502Emulator";

	/**
	 * private constructor
	 */
	private FrameBuilder() {

	}

	/**
	 * getInstance
	 *
	 * @return
	 */
	public static FrameBuilder getInstance() {
		return builder;
	}

	/**
	 * build
	 *
	 * @return
	 */
	public MainFrame build(MainFrame frame, MainController controller) {
		// TODO refactoring
		Container pane = frame.getContentPane();
		GridBagLayout layout = new GridBagLayout();
		pane.setLayout(layout);

		// TODO ゲーム画面用のPanelクラス作成
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("ゲーム用の画面"));
		pane.add(panel1);

		EditorPanel editorPanel = EditorPanel.getInstance();
		pane.add(editorPanel);

		// 画面パーツ作成時に各パーツへの参照もmainframeに持っておく
		controller.setEditorPanel(editorPanel);
		//controller.setGameWindowPanel();

		frame.setJMenuBar(getMyMenuBar(controller));
		frame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		frame.setTitle(TITLE);
		frame.setBounds(0, 0, 800, 600);
		// FIXME magic number
		frame.setVisible(true);
		return frame;
	}

	/**
	 * メニューバーを返す
	 * @return
	 */
	private JMenuBar getMyMenuBar(MainController controller) {
		JMenuBar jMenuBar = new JMenuBar();
		System.out.println(jMenuBar.getClass());

		JMenu jMenuFile = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("Open");
		menuItem.addActionListener(new OpenActionListner(controller));
		jMenuFile.add(menuItem);

		jMenuBar.add(jMenuFile);
		return jMenuBar;
	}
}
