import java.awt.*;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame{
	
	public Game() {
		GamePanel panel = new GamePanel();
		panel.setLocation(0,0);
		panel.setSize(this.getSize());
		panel.setBackground(Color.DARK_GRAY);
		panel.setVisible(true);
		this.add(panel);
		
		addKeyListener(new Movement(panel));
	}
}
