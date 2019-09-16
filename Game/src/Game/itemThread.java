package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Game.Game.pigMoved;
import javazoom.jl.player.Player;

class itemThread extends Thread {
	JPanel panel;
	JLabel img_pig, label, item_apple, startView;
	ArrayList<JLabel> items;
	ArrayList<JLabel> item_dong;
	int score = 0, guage = 150, time = 300, second = 0;
	String userId;
	
	boolean p = false;
	
	ImageIcon pig = new ImageIcon(getClass().getClassLoader().getResource("pig.png"));
	ImageIcon pig_attact = new ImageIcon(getClass().getClassLoader().getResource("pig_attact.png"));
	ImageIcon pig_item = new ImageIcon(getClass().getClassLoader().getResource("pig_item.png"));
	
	itemThread(JPanel panel, JLabel img_pig, String userId) {
		this.panel = panel;
		this.img_pig = img_pig;
		this.userId = userId;
	}

	public void run() {

		int rand_num = 0;
		int rand_item = 0;

		// 똥
		item_dong = new ArrayList<JLabel>();
		ImageIcon dong = new ImageIcon(getClass().getClassLoader().getResource("dong.png"));

		items = new ArrayList<JLabel>();
		ImageIcon apple = new ImageIcon(getClass().getClassLoader().getResource("apple.png"));
		ImageIcon banana = new ImageIcon(getClass().getClassLoader().getResource("banana.png"));
		ImageIcon fruit = new ImageIcon(getClass().getClassLoader().getResource("fruit.png"));
		
		items.add(new JLabel(apple));
		items.add(new JLabel(banana));
		items.add(new JLabel(fruit));
		
		ImageIcon start = new ImageIcon(getClass().getClassLoader().getResource("background.png"));
		
		startView = new JLabel(start);
		startView.setLocation(0, 0);
		startView.setSize(500, 590);
		startView.setLocation(700, 50);
		panel.add(startView);

		while (true) {
			if (guage == 0) {
				
				
				p = true;
			}

			dongAttact(item_dong);

			dongMoved(item_dong);


			rand_num = (int) (Math.random() * 10);

			item_dong.add(new JLabel(dong));
			panel.add(item_dong.get(item_dong.size() - 1));
			panel.repaint();

			item_dong.get(item_dong.size() - 1).setLocation(rand_num * 50, -50);
			item_dong.get(item_dong.size() - 1).setSize(30, 30); // 똥 크기 초기화

			second++;

			// 일정시간마다 랜덤으로 아이텀 떨어뜨리기
			if (second % 40 == 0) {
				rand_item = (int) (Math.random() * 3);

				panel.add(items.get(rand_item));
				panel.repaint();
				items.get(rand_item).repaint();

				items.get(rand_item).setLocation(rand_num * 50, -50);
				items.get(rand_item).setSize(60, 60); // 아이템 크기 초기화

			}
			itemMoved(items.get(rand_item));
			itemAttact(items.get(rand_item), rand_item);

			// 아이템 내려오는 속도 줄이기
			if (time >= 100) {
				if (second % 30 == 0) {
					time -= 10;
				}
			}

			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void dispose() {
		// TODO Auto-generated method stub
		
	}

	// 똥움직이기
	private void dongMoved(ArrayList<JLabel> item) {

		for (int i = 0; i < item.size(); i++) {
			(item.get(i)).setLocation(item.get(i).getX(), item.get(i).getY() + 30);
			panel.repaint();
		}
	}

	// 아이탬 움직이기
	private void itemMoved(JLabel item) {
		item.setLocation(item.getX(), item.getY() + 40);
		panel.repaint();
		item.repaint();
	}

	// 똥맞기
	private void dongAttact(ArrayList<JLabel> item) {
		for (int i = 0; i < item.size(); i++) {
			// 원숭이와 똥의 위치값 차이
			int dif = (item.get(i).getX()) - (img_pig.getX());

			// 돼지와 똥의 attact조건
			if (dif >= -10 && dif <= 50 && item.get(i).getY() >= 450 && item.get(i).getY() <= 500) {
				item.get(i).setVisible(false);
				item.remove(i);
				attactMusic();
				attact(img_pig.getX() + 5, img_pig.getY() + 5);

				// 게이지 줄이기
				reduceGuage();
				(item.get(i)).revalidate();
			} else {
				if (item.get(i).getY() == 490)
					score++;
			}
			if (item.get(i).getY() > 560) {
				item.get(i).setVisible(false);
				item.remove(i);

			}
			panel.repaint();
		}
	}

	// 아이템맞기
	private void itemAttact(JLabel items, int num) {

		int dif = (items.getX()) - (img_pig.getX());

		if (dif >= -25 && dif <= 55 && items.getY() >= 450 && items.getY() <= 500) {
			item(img_pig.getX(), img_pig.getY());
			items.setVisible(false);
			switch (num) {
			case 0:
				guage += 15;
				break;
			case 1:
				clearScreen();
				break;
			case 2:
				guage = 150;
			}

		} else {
			if (items.getY() > 560) {
				items.setVisible(false);
			}
			panel.repaint();
		}
	}

	// 화면지우기
	private void clearScreen() {
		for (int i = 0; i < item_dong.size(); i++) {
			item_dong.get(i).setVisible(false);
		}
		for(int i = 0; i < item_dong.size(); i++) {
			item_dong.remove(i);
		}
		panel.repaint();
	}

	// attact 효과음
	public void attactMusic() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				FileInputStream fin;
				try {
					fin = new FileInputStream("dong.mp3");
					Player p = new Player(fin);
					p.play();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	// attact 느낌표
	public void attact(int x, int y) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				FileInputStream fin;
				try {
					ImageIcon attact = new ImageIcon(getClass().getClassLoader().getResource("attact.png"));
					JLabel img_attact = new JLabel(attact);
					img_pig.setIcon(pig_attact);
					panel.add(img_attact);
					img_attact.setLocation(x + 50, y - 20);
					img_attact.setSize(20, 20);
					Thread.sleep(250);
					img_attact.setVisible(false);
					img_pig.setIcon(pig);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	// item을 먹었을때
	public void item(int x, int y) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				FileInputStream fin;
				try {
					ImageIcon heart = new ImageIcon(getClass().getClassLoader().getResource("heart.png"));
					JLabel img_heart = new JLabel(heart);
					itemMusic();
					img_pig.setIcon(pig_item);
					panel.add(img_heart);
					img_heart.setLocation(x+20, y - 20); // 원숭이 위치 절대값 초기화
					img_heart.setSize(50, 50);
					Thread.sleep(250);
					img_heart.setVisible(false);
					img_pig.setIcon(pig);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	// item 효과음
		public void itemMusic() {
			new Thread(new Runnable() {

				@Override
				public void run() {
					FileInputStream fin;
					try {
						fin = new FileInputStream("music/apple.mp3");
						Player p = new Player(fin);
						p.play();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		
	public void reduceGuage() {
		if (guage <= 0) {
			guage = 0;
		} else {
			guage -= 15;
		}
	}

	public int getScore() {
		return score;
	}

	public int getGuage() {
		return guage;
	}
	
	public boolean game_play() {
		return p;
	}

}
