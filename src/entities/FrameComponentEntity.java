package entities;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import views.EditorPanel;
import views.OpenActionListner;
import views.RomFileChooser;

public class FrameComponentEntity extends BaseEntity {
	private static String TITLE = "6502Emulator";

	private static JFrame mainFrame = new JFrame();
	private static Container pane = mainFrame.getContentPane();

	private static JPanel gameWindowPanel = new JPanel();
	// ファミコンの画面サイズは 256 x 240 ピクセル

	private static JLabel gameWindowLabel = new JLabel("ゲーム用の画面");

	private static JTextArea editorTextArea = new JTextArea();
	private static JScrollPane editorPane = new JScrollPane(
			editorTextArea,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private static JPanel editorPanel = new EditorPanel();
	private static JLabel addressLabel = new JLabel();

	private static GridBagLayout layout = new GridBagLayout();
	private static GridBagConstraints constraints = new GridBagConstraints();

	private static JMenuBar menuBar = new JMenuBar();
	private static JMenu fileMenu = new JMenu("File");
	private static JMenuItem openMenuItem = new JMenuItem("Open");

	private static RomFileChooser fileChooser = new RomFileChooser();;
	private static OpenActionListner openActionLitenser;


	public FrameComponentEntity() {
		constraints.anchor = GridBagConstraints.NORTHWEST;
		layout.setConstraints(gameWindowPanel, constraints);
		layout.setConstraints(editorPane, constraints);

		pane.setLayout(layout);
		pane.add(gameWindowPanel);
		editorPanel.add(addressLabel);
		pane.add(editorPanel);

		gameWindowPanel.setBackground(Color.BLACK);
		gameWindowPanel.setPreferredSize(new Dimension(300, 400));;
		gameWindowPanel.add(gameWindowLabel);

		editorPanel.add(editorPane);
		// FIXME magic number
		editorTextArea.setColumns(32);
		editorTextArea.setRows(20);
		editorTextArea.setLineWrap(true);

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

	public static JFrame getMainFrame() {
		return mainFrame;
	}

	public static JPanel getGameWindowPanel() {
		return gameWindowPanel;
	}

	public static JLabel getGameWindowLabel() {
		return gameWindowLabel;
	}

	public static JPanel getEditorPanel() {
		return editorPanel;
	}

	public static JMenuBar getMenuBar() {
		return menuBar;
	}

	public static JMenu getFileMenu() {
		return fileMenu;
	}

	public static JMenuItem getOpenMenuItem() {
		return openMenuItem;
	}

	public static JTextArea getEditorTextArea() {
		return editorTextArea;
	}
}