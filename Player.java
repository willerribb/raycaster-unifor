import java.awt.*;

public class Player {
		GamePanel panel;
		double px, py, pdx, pdy, pa;
		int width, height;
		double xspeed, yspeed, dR = Math.PI/180;
		//Rectangle hitBox;
		boolean keyLeft, keyRight, keyUp, keyDown;
		
		public Player (double px, double py, GamePanel panel) {
			this.panel = panel;
			this.px = px;
			this.py = py;
		}
		public void set() {
			/*if(keyLeft && keyRight || !keyLeft && !keyRight) xspeed *= 0.8;
			else if(keyLeft && !keyRight) xspeed--;
			else if(keyRight && !keyLeft) xspeed++;
			
			x += xspeed;
			y += yspeed;*/
			if(keyLeft) {
				pa-=0.1; 
				if(pa < 0) pa+=2*Math.PI;
				pdx = Math.cos(pa)*7.5; pdy = Math.sin(pa)*7.5;
			}
			if(keyRight)  {
				pa+=0.1; 
				if(pa > 2*Math.PI) pa-=2*Math.PI;
				pdx = Math.cos(pa)*7.5; pdy = Math.sin(pa)*7.5;
			}
			if(keyUp) {px += pdx; py += pdy;}
			if(keyDown) {px -= pdx; py -= pdy;}
		}
		public void draw(Graphics2D gtd) {
			gtd.setColor(Color.YELLOW);
			gtd.fillRect((int)px, (int)py, 10, 10);
			//gtd.drawLine((int)px+5, (int)py+5, (int)(px+pdx*5), (int)(py+pdy*5));
		}
		public double distance(double ax, double ay, double bx, double by, double ang) {
			return(Math.sqrt((bx-ax)*(bx-ax)+(by-ay)*(by-ay)));
		}
		public void drawRays2D(Graphics2D gtd) {
			int r, mx, my, mp, dof; 
			double rx = 0, ry = 0, ra, xo = 0, yo = 0, disT = 0;
			double P2 = Math.PI/2; double P3 = 3*(Math.PI/2);
			ra = pa - dR*30; if(ra < 0) { ra+= 2*Math.PI;} if(ra > 2*Math.PI) { ra-= 2*Math.PI; }
			for(r = 0; r < 60; r++) {
				// HORIZONTAL LINE CHECK
				dof=0;
				double disH = 1000000, hx = px, hy = py;
				double inverseTan = -1/Math.tan(ra);
				if(ra > Math.PI) {
					ry = (((int)py >> 6)<< 6)-0.0001; rx = (py-ry)*inverseTan + px;
					yo = -64;
					xo = -yo*inverseTan;
				} // looking up
				if(ra < Math.PI) {
					ry = (((int)py >> 6)<< 6)+64; rx = (py-ry)*inverseTan + px;
					yo = 64;
					xo = -yo*inverseTan;
				} // looking down
				if(ra == 0 || ra == Math.PI) {
					rx = px; ry = py;
					dof = 8;
				}
				do {
					mx = (int)(rx) >> 6; my = (int)(ry) >> 6; mp = my*panel.mapX+mx;
					if(mp > 0 && mp < panel.mapX*panel.mapY && panel.map[mp] == 1) { hx=rx; hy=ry; disH=distance(px,py,hx,hy,ra); dof = 8; }
					else rx += xo; ry += yo; dof++;
				}while(dof<8);
				// VERTICAL LINE CHECK
				dof=0;
				double disV = 1000000, vx = px, vy = py;
				double negativeTan = -Math.tan(ra);
				if(ra > P2 && ra < P3) {
					rx = (((int)px >> 6)<< 6)-0.0001; ry = (px-rx)*negativeTan + py;
					xo = -64;
					yo = -xo*negativeTan;
				} // looking left
				if(ra < P2 || ra > P3) {
					rx = (((int)px >> 6)<< 6)+64; ry = (px-rx)*negativeTan + py;
					xo = 64;
					yo = -xo*negativeTan;
				} // looking right
				if(ra == 0 || ra == Math.PI) {
					rx = px; ry = py;
					dof = 8;
				}
				do {
					mx = (int)(rx) >> 6; my = (int)(ry) >> 6; mp = my*panel.mapX+mx;
					if(mp > 0 && mp < panel.mapX*panel.mapY && panel.map[mp] == 1) { vx=rx; vy=ry; disV=distance(px,py,vx,vy,ra); dof = 8; }
					else rx += xo; ry += yo; dof++;
				}while(dof<8);
				if(disV < disH) { rx = vx; ry = vy; disT = disV; gtd.setColor(new Color(230, 0, 0));; }
				if(disH < disV) { rx = hx; ry = hy; disT = disH; gtd.setColor(new Color(178, 0, 0));}
				gtd.setStroke(new BasicStroke(16));
				// -- RAYCASTING BABEY --
				double ca = pa-ra; if(ca < 0) { ca+= 2*Math.PI; } if(ca > 2*Math.PI) { ca-= 2*Math.PI; } disT = disT*Math.cos(ca);
				double lineH = (panel.mapTiles*960)/disT; if(lineH > 960) lineH = 960;
				double lineO = 480-(lineH/2);
				gtd.drawLine(r*16, (int)lineO, r*16, (int)(lineH+lineO));
				ra+=dR; if(ra < 0) { ra+= 2*Math.PI;} if(ra > 2*Math.PI) { ra-= 2*Math.PI; }
				
			}
			
		}
		
		
		
		
		
		
	}