package display;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Charactor.*;
import Element.Element;
import event.Event;
import java.net.URL;

public class Game extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;
	
	private static int speed = 50,dogSize = 140 ,waveHeight = 50;
	private static int base=400,xStart = 1000;
	private long point = 0,lastPress=0;
	
	private final Sonic dog = new Sonic(100,base-70);
	static Display display;
//	------------------Wave SIze ----------------------------
	private Wave[] waveSet = makeWave(2);
//--------------------Cloud--------------------------------
	private Environment[] envSet = makeEnv(2,Environment.CLOUD);
	private Environment building = new Environment(xStart-100,base-150,this,Environment.BUILDING,4);
        
        public static void main(String[] arg) {
		 display = new Display();
	}
	
		public Game(){
		this.setBounds(0,0,1000,600);
		this.addKeyListener(this);
		this.setLayout(null);
		this.setFocusable(true);
	}
	
	@Override   
	public void paint(Graphics g) {
		try {
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			this.drawBackground(g2);
			//---POINT----
			g2.setFont(Element.getFont(30));
			g2.setColor(Color.white);
			g2.drawString("Point : "+point,750,40);
			//--- Sonic --
			g2.setColor(Color.RED);
			drawDogHealth(g2);
				g2.drawImage(dog.getImage(),dog.x,dog.y,dogSize,dogSize, null);
			//----Wave----
			for(Wave item : waveSet) {
				drawWave(item,g2);
			}
			this.point+=1;
		} catch (IOException e) {
		}
	}
	private void drawBackground(Graphics2D g2) throws IOException {
            URL imageURLsky = this.getClass().getResource("/img/sky.png") ;
            g2.drawImage(ImageIO.read(imageURLsky),0,0,2000,1000, null);
            g2.drawImage(building.getImage(),building.x,building.y,500,200,null);
            URL imageURLdir = this.getClass().getResource("/img/dir.png") ;
            g2.drawImage(ImageIO.read(imageURLdir),0,base+50,2000,220, null);
		for(Environment item:envSet) {
			g2.drawImage(item.getImage(),item.x,item.y,250,160, null);
		}
	}
	
	private void drawDogHealth(Graphics2D g2) {
		try {
                    URL imageURLheart = this.getClass().getResource("/img/heart.png");
			g2.drawImage(ImageIO.read(imageURLheart),10,20, 30,30,null);
			g2.setStroke(new BasicStroke(18.0f));
			g2.setColor(new Color(241, 98, 69));
			g2.drawLine(60, 30,60+dog.health,30);	
			g2.setColor(Color.red);
			g2.setStroke(new BasicStroke(6.0f));
			g2.drawRect(50,20, 200,20);
		} catch (IOException e) {
		}
	}
	
	private Wave[] makeWave(int size) {
		Wave[] waveSet = new Wave[size];
		int far = 500;
		for(int i=0;i<size;i++) {
			waveSet[i] = new Wave(xStart+far,base,speed,this);
			far+=500;
		}
		return waveSet;
	}
	
	private Environment[] makeEnv(int size,int eType){
		Environment[] envSet = new Environment[size];
		int far = 0;
		for(int i=0;i<size;i++) {
			envSet[i] = new Environment(xStart+far,20,this,eType,10);
			far+=600;
		}
		return envSet;
	}
	
	private void drawWave(Wave wave,Graphics2D g2) {
			g2.drawImage(wave.getImage(),wave.x ,(wave.y-waveHeight),80,waveHeight+80,null);
			if(Event.checkHit(dog,wave,dogSize,waveHeight)){
					g2.setColor(new Color(241, 98, 69));
					g2.fillRect(0, 0,1000,1000);			
					dog.health-=20;
					if(dog.health<=0) {
						display.endGame(this.point);
						dog.health = new Sonic().health;
						this.point = 0;	
					}
			}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(System.currentTimeMillis() - lastPress > 600) {
			if(e.getKeyCode()==32||e.getKeyCode()==38) {
					dog.jump(this);
					lastPress = System.currentTimeMillis();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//---
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//---
	}
	
	
}