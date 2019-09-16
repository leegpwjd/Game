package Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import javazoom.jl.player.Player;

class Game extends JFrame{
	RankingDB rankdb = new RankingDB();
	Container contentPane;
	final int FLYING_UNIT = 10;
	JLabel img_pig, img_dong;
	JPanel panel;
	int barSize = 0;
	int maxBarSize;
	JProgressBar progress;
	int score;
	ImageIcon pig = new ImageIcon(getClass().getClassLoader().getResource("pig.png"));
	ImageIcon pig_left = new ImageIcon(getClass().getClassLoader().getResource("pig_left.png"));
	ImageIcon pig_right = new ImageIcon(getClass().getClassLoader().getResource("pig_right.png"));
	
	itemThread th;
	
	Game(String userId){
		
		//배경음악 재생
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				FileInputStream fin;
				try {
					fin = new FileInputStream("music/bgm.mp3");
					Player p = new Player(fin);
					p.play();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		setTitle("game...");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("background.jpg"));

		
		//background image
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(image.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
				//score 이미지위에 그리기
				g.setFont(g.getFont().deriveFont(20f));
				g.setColor(Color.BLACK);
				g.drawString("Score : " + th.getScore(), 380, 30);
				//게이지바 이미지 위에 그리기
				g.drawRect(9, 14, 151, 16);
				g.setColor(Color.red);
				g.fillRect(10, 15, th.getGuage(), 15);
				//userId 이미지 위에 그리기
				g.setFont(g.getFont().deriveFont(25f));
				g.setColor(Color.DARK_GRAY);
				g.drawString(userId, 220, 30);
				
				if(th.game_play()) {
					new Replay(userId, th.getScore());
					rankdb.insertScore(userId, th.getScore());
					dispose();
				}
			}
		};
		
		
		
		

		
		
		//돼지
		img_pig = new JLabel(pig);
		
		panel.addKeyListener(new pigMoved());	//돼지 움직이기
		
		panel.add(img_pig);	
		img_pig.setLocation(120, 480);	//돼지 위치 절대값 초기화
		img_pig.setSize(80, 80);	//돼지 크기 초기화
		
		setContentPane(panel);
		panel.setLayout(null);

		setLocation(700, 50);
		setSize(500, 590);
		setVisible(true);
		setResizable(false);
		panel .requestFocus();
		
		th = new itemThread(panel, img_pig, userId);
		th.start();
		
		
		
	}
			
	
	
	//돼지 움직이기
	class pigMoved implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			switch(keyCode) {
			case KeyEvent.VK_RIGHT:
				if(img_pig.getX() <= 460) {
					img_pig.setIcon(pig_right);
					img_pig.setLocation(img_pig.getX()+FLYING_UNIT, img_pig.getY());
				}
				if(img_pig.getX() > 460) {
					img_pig.setLocation(0, 480);
				}
				break;
			case KeyEvent.VK_LEFT:
				if(img_pig.getX() >= -50) {
					img_pig.setIcon(pig_left);
					img_pig.setLocation(img_pig.getX()-FLYING_UNIT, img_pig.getY());
				}
				if(img_pig.getX() < -50) {
					img_pig.setLocation(420, 480);
				}
				break;
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			if(img_pig.getX() <= 420 && img_pig.getX() >= 0) {
				img_pig.setIcon(pig);
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {}
		
	}

}

