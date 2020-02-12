package xyz.jfuruya.Emulator;
import views.MyFrame;

public class Application {

	private static MyFrame frame = null;

	public static void main(String[] args) {
		frame = MyFrame.getInstance();
		frame.build();
	}
}
