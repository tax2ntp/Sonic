package Charactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Sonic{
	public int x ;
	public int y;
	public int health=180;
	public static int jump=170;
	
	public Sonic() {
		
	}
	
	public Sonic(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public void jump(JPanel page) {
		this.y -= jump;
		page.repaint();
		//--- fall ---
		Timer timer =new Timer(500,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					y += jump;
					page.repaint();
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	public BufferedImage getImage() {
		BufferedImage image = null;
		try {
                    URL imageURLdog = this.getClass().getResource("/img/dog.png") ;
			image = ImageIO.read(imageURLdog);
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}