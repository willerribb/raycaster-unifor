import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener{

	Player player;
	Timer gameTimer;
	
	public int mapX = 10, mapY = 10, mapTiles = 64;
	public int map[] = 
		{
				1,1,1,1,1,1,1,1,1,1,
				1,0,0,0,1,0,0,0,0,1,
				1,0,0,0,1,0,0,0,0,1,
				1,0,0,0,1,1,1,0,0,1,
				1,0,0,0,0,0,0,0,0,1,
				1,0,0,1,0,0,0,0,0,1,
				1,1,1,1,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,1,
				1,0,0,0,0,0,0,0,0,1,
				1,1,1,1,1,1,1,1,1,1
		};
	ArrayList<Wall> mapInfo = new ArrayList<>();
	//
	public GamePanel() {
		player = new Player(470, 300, this);
		mapGenerate();
		//player.calculateDeltas();
		//
		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				player.set();
				repaint();
			}
			
		}, 0, 33); // 25FPS
	}
	public void mapGenerate() {
		for(int i = 0; i < mapX; i++) {
			for(int j = 0; j < mapY; j++) {
				if(map[j*10 + i] == 1 ) mapInfo.add(new Wall(i * mapTiles, j * mapTiles, mapTiles, mapTiles));
			}
		}
	}
	//
	public class Wall{
		int x, y, width, height;
		public Wall(int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		public void draw(Graphics2D gtd) {
			gtd.setColor(Color.BLACK);
			gtd.drawRect(x, y, width, height);
			gtd.setColor(Color.WHITE);
			gtd.fillRect(x+1, y+1, width-2, height-2);
		}
		
	}
	//
	
	//
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D gtd = (Graphics2D) g;
		//for(Wall wall : mapInfo) wall.draw(gtd);
		//player.draw(gtd);
		player.drawRays2D(gtd);
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A') player.keyLeft = true;
		if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D') player.keyRight = true;
		if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W') player.keyUp = true;
		if(e.getKeyChar() == 's' || e.getKeyChar() == 'S') player.keyDown = true;
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A') player.keyLeft = false;
		if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D') player.keyRight = false;
		if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W') player.keyUp = false;
		if(e.getKeyChar() == 's' || e.getKeyChar() == 'S') player.keyDown = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
