package Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Replay extends JFrame{
	Replay(String userId, int score){
		Container contentPane;
		setTitle("Replay");
		//리플레이 패널
		JPanel reLayered = new JPanel();
		reLayered.setBounds(0, 0, 500, 590);
		reLayered.setLayout(null);
		reLayered.setBackground(Color.WHITE);
		
		//GameOver 필드
		JLabel gameOverLabel = new JLabel("Game Over");
		gameOverLabel.setBounds(130, 130, 300, 80);
		gameOverLabel.setFont(new Font("고딕", Font.BOLD, 40));
		gameOverLabel.setForeground(Color.RED);
		reLayered.add(gameOverLabel);
		
		//score 필드
		JLabel scoreLabel = new JLabel("Score : " + score);
		scoreLabel.setBounds(180, 200, 300, 80);
		scoreLabel.setFont(new Font("고딕", Font.BOLD, 25));
		scoreLabel.setForeground(Color.BLACK);
		reLayered.add(scoreLabel);
		
		//내 랭킹확인 버튼
		JButton myRankBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("myRanking.png")));
		ImageIcon myRank_hover = new ImageIcon(getClass().getClassLoader().getResource("myRanking_hover.png"));
		myRankBtn.setRolloverIcon(myRank_hover);
		myRankBtn.setPressedIcon(myRank_hover);
		myRankBtn.setBorderPainted(false);
		myRankBtn.setContentAreaFilled(false);
		myRankBtn.setFocusPainted(false);
		myRankBtn.setBounds(130, 300, 230, 50);
		reLayered.add(myRankBtn);
		myRankBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new myRanking(userId, score);
			}
		});
		
		//RePlay 버튼
		JButton rePlayBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("replay.png")));
		ImageIcon rePlay_hover = new ImageIcon(getClass().getClassLoader().getResource("replay_hover.png"));
		rePlayBtn.setRolloverIcon(rePlay_hover);
		rePlayBtn.setPressedIcon(rePlay_hover);
		rePlayBtn.setBorderPainted(false);
		rePlayBtn.setContentAreaFilled(false);
		rePlayBtn.setFocusPainted(false);
		rePlayBtn.setBounds(130, 370, 230, 50);
		reLayered.add(rePlayBtn);
		rePlayBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Game(userId);
			}
		});

		setLocation(700, 50);
		setSize(500, 590);
		add(reLayered);
		setVisible(true);
		setResizable(false);
	}
}
