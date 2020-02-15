package entities;

import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import views.EditorPanel;
import views.OpenActionListner;
import views.RomFileChooser;

public class FrameComponentEntity extends BaseEntity {
	private static String TITLE = "6502Emulator";

	private static JFrame mainFrame = new JFrame();

	private static JPanel gameWindowPanel = new JPanel();
	private static JLabel gameWindowLabel = new JLabel("ゲーム用の画面");
	private static JPanel editorPanel = EditorPanel.getInstance();
	private static JLabel editorLabel = new JLabel("Editor用の画面");

	private static JMenuBar menuBar = new JMenuBar();
	private static JMenu fileMenu = new JMenu("File");
	private static JMenuItem openMenuItem = new JMenuItem("Open");

	private static RomFileChooser fileChooser = new RomFileChooser();;
	private static OpenActionListner openActionLitenser;

	private static JTextField jTextFIeld000000000 = new JTextField();
	private static JTextField jTextFIeld000000001 = new JTextField();
	private static JTextField jTextFIeld000000002 = new JTextField();
	private static JTextField jTextFIeld000000003 = new JTextField();
	private static JTextField jTextFIeld000000004 = new JTextField();

	public FrameComponentEntity() {
		Container pane = mainFrame.getContentPane();
		pane.setLayout(new GridBagLayout());
		pane.add(gameWindowPanel);
		pane.add(editorPanel);

		gameWindowPanel.add(gameWindowLabel);
		editorPanel.add(editorLabel);

		openActionLitenser = new OpenActionListner(fileChooser);
		openMenuItem.addActionListener(openActionLitenser);

		fileMenu.add(openMenuItem);
		menuBar.add(fileMenu);

		mainFrame.setJMenuBar(menuBar);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle(TITLE);
		// FIXME magic number
		mainFrame.setBounds(0, 0, 800, 600);
		mainFrame.setVisible(true);
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public JPanel getGameWindowPanel() {
		return gameWindowPanel;
	}

	public JLabel getGameWindowLabel() {
		return gameWindowLabel;
	}

	public JPanel getEditorPanel() {
		return editorPanel;
	}

	public JLabel getEditorLabel() {
		return editorLabel;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public JMenuItem getOpenMenuItem() {
		return openMenuItem;
	}

	public JTextField getJTextFIeld000000000() {
		return jTextFIeld000000000;
	}

	public JTextField getjTextFIeld000000001() {
		return jTextFIeld000000001;
	}

	public JTextField getJTextFIeld000000002() {
		return jTextFIeld000000002;
	}

	public JTextField getJTextFIeld000000003() {
		return jTextFIeld000000003;
	}

	public JTextField getJTextFIeld000000004() {
		return jTextFIeld000000004;
	}
}