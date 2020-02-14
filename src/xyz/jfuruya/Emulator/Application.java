package xyz.jfuruya.Emulator;
import views.MainFrame;

public class Application {

	private static MainFrame frame = null;

	public static void main(String[] args) {
		MainFrame.getInstance();
	}
}
