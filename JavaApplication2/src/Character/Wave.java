package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wave {
		public int speed;
		public int x;
		public int y;
		Timer timeMove;
		public Wave(int x,int y,int speed,JPanel page) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.move(page);
		}
		
		public void move(JPanel page) {
				 this.timeMove = new Timer(speed,new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							if(x<=0) {
								x = (int) (1000+(-00+Math.random()*1000));
							}
							x -= 30;
							page.repaint();
					}
				});
				 this.timeMove.start();
		}
		
		public BufferedImage getImage() {
			BufferedImage image = null;
			try {
                            URL imageURLwave = this.getClass().getResource("/img/rock.png") ;
                            image = ImageIO.read(imageURLwave);
				 return image;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return image;
		}
}