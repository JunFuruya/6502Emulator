package xyz.jfuruya.Emulator;
import controllers.MainController;
import views.MainFrame;

public class Application {

	private static MainFrame frame = null;

	public static void main(String[] args) {
		MainController.getInstance().execute();
	}
}
