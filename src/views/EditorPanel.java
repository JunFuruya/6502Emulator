package views;

import javax.swing.JLabel;

public class EditorPanel extends BasePanel {
	static EditorPanel panel = new EditorPanel();
	private static JLabel label = new JLabel("bainery editor");

	/**
	 * private constructor
	 */
	private EditorPanel() {

	}

	public static EditorPanel getInstance() {
		panel.add(label);

		return panel;
	}

	/**
	 * getLabel
	 *
	 */
	public JLabel getLabel() {
		return label;
	}
}
