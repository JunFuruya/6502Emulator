package builders;

import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import views.MainFrame;
import views.OpenActionListner;

public class FrameBuilder {
	private static FrameBuilder builder = new FrameBuilder();

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
	public MainFrame build(MainFrame frame) {
		// TODO refactoring
		Container pane = frame.getContentPane();
		GridBagLayout layout = new GridBagLayout();
		pane.setLayout(layout);

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.add(new JLabel("ゲーム用の画面"));
		panel2.add(new JLabel("bainery editor"));

		pane.add(panel1);
		pane.add(panel2);

		frame.setJMenuBar(getMyMenuBar());
		frame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);

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
