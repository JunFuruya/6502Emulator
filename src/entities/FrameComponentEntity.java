package entities;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
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
import views.SoundActionListener;

public class FrameComponentEntity extends BaseEntity {
	private static String TITLE = "6502Emulator";

	private static JFrame mainFrame = new JFrame();
	private static Container pane = mainFrame.getContentPane();

	private static JPanel gameWindowPanel = new JPanel();
	private static JLabel gameWindowLabel = new JLabel("ゲーム用の画面");

	private static JTextArea editorTextArea = new JTextArea();
	private static JScrollPane editorPane = new JScrollPane(
			editorTextArea,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private static JPanel editorPanel = EditorPanel.getInstance();

	private static GridBagLayout layout = new GridBagLayout();
	private static GridBagConstraints constraints = new GridBagConstraints();

	private static JMenuBar menuBar = new JMenuBar();
	private static JMenu fileMenu = new JMenu("File");
	private static JMenuItem openMenuItem = new JMenuItem("Open");

	private static RomFileChooser fileChooser = new RomFileChooser();;
	private static OpenActionListner openActionLitenser;

	// FIXME リファクタ ここから
	private static JButton soundButton = new JButton("sound");
	private static SoundActionListener soundActionListener = new SoundActionListener();
	private static JLabel headerLabel = new JLabel();
	// FIXME リファクタ ここまで

	public FrameComponentEntity() {
		constraints.anchor = GridBagConstraints.NORTHWEST;
		layout.setConstraints(gameWindowPanel, constraints);
		layout.setConstraints(editorPane, constraints);

		pane.setLayout(layout);
		pane.add(gameWindowPanel);
		pane.add(editorPanel);

		// FIXME リファクタ ここから
		soundButton.addActionListener(soundActionListener);
		pane.add(soundButton);
		headerLabel.setText("Header:");
		pane.add(headerLabel);
		// FIXME リファクタ ここまで

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

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public JMenuItem getOpenMenuItem() {
		return openMenuItem;
	}

	public JTextArea getEditorTextArea() {
		return editorTextArea;
	}

	/**
	 * エディタにテキストを表示する
	 * @param text
	 */
	public void setTextToEditor(String text) {
		editorTextArea.setText(text);
	}
}