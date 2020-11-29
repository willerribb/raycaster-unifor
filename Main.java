import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Game frame = new Game();
		frame.setTitle("RAYCASTING ALL THE BITCHES");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 960);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
