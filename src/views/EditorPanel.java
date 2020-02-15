package views;

import java.awt.GridLayout;

import javax.swing.JTextField;

public class EditorPanel extends BasePanel {
	static EditorPanel panel = new EditorPanel();
	private static EditorLabel label = new EditorLabel();

	/**
	 * private constructor
	 */
	private EditorPanel() {

	}

	public static EditorPanel getInstance() {
		// FIXME magic number
		GridLayout layout = new GridLayout(1, 5);
		layout.addLayoutComponent("No1", new JTextField());
		layout.addLayoutComponent("No1", new JTextField());
		layout.addLayoutComponent("No1", new JTextField());
		layout.addLayoutComponent("No1", new JTextField());
		layout.addLayoutComponent("No1", new JTextField());
		panel.setLayout(layout);

		//panel.add(label);

		return panel;
	}

	/**
	 * getLabel
	 *
	 */
	public EditorLabel getLabel() {
		return label;
	}


	public void setBinary(byte[] bytes) {


	}
}
