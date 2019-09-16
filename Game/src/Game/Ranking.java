package Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Game.Join.JoinAction;

class Ranking extends JFrame{
	JLabel idLabel, scoreLabel, idxLabel, rankingLabel, rankIdLabel, rankScoreLabel;
	ArrayList<String[]> ranking;
	Ranking(){
		Container contentPane;
		setTitle("Ranking");
		//·©Å· ÆÐ³Î
		JPanel rankLayered = new JPanel();
		rankLayered.setBounds(0, 0, 500, 590);
		rankLayered.setLayout(null);
		rankLayered.setBackground(Color.WHITE);
		
		//·©Å· ÇÊµå
		JLabel rankLabel = new JLabel("Ranking List");
		rankLabel.setBounds(130, 10, 300, 80);
		rankLabel.setFont(new Font("°íµñ", Font.BOLD, 40));
		rankLabel.setForeground(Color.ORANGE);
		rankLayered.add(rankLabel);
		
		RankingDB rankdb = new RankingDB();
		
		ranking = rankdb.Ranking();
		
		rankingLabel = new JLabel("RANK");
		rankIdLabel = new JLabel("ID");
		rankScoreLabel = new JLabel("Score");
		
		rankingLabel.setBounds(150, 105, 100, 30);
		rankIdLabel.setBounds(250, 105, 100, 30);
		rankScoreLabel.setBounds(300, 105, 100, 30);
		
		rankingLabel.setFont(new Font("°íµñ", Font.BOLD, 20));
		rankIdLabel.setFont(new Font("°íµñ", Font.BOLD, 20));
		rankScoreLabel.setFont(new Font("°íµñ", Font.BOLD, 20));
		
		rankLayered.add(rankingLabel);
		rankLayered.add(rankIdLabel);
		rankLayered.add(rankScoreLabel);
		
		int y = 140;
		for(int i = 0; i < ranking.size() && i<10; i++) {
			
			idxLabel = new JLabel(String.valueOf(i+1)+".");
			idLabel = new JLabel(ranking.get(i)[0]);
			scoreLabel = new JLabel(ranking.get(i)[1]);
			
			idxLabel.setBounds(170, y, 80, 20);
			idLabel.setBounds(230, y, 80, 20);
			scoreLabel.setBounds(320, y, 80, 20);
			
			idxLabel.setFont(new Font("°íµñ", Font.ITALIC, 17));
			idLabel.setFont(new Font("°íµñ", Font.ITALIC, 17));
			scoreLabel.setFont(new Font("°íµñ", Font.ITALIC, 17));
			
			
			rankLayered.add(idxLabel);
			rankLayered.add(idLabel);
			rankLayered.add(scoreLabel);
			y += 35;
		}
		
		JButton homeBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("home.png")));
		ImageIcon homeBtn_hover = new ImageIcon(getClass().getClassLoader().getResource("home_hover.png"));
		homeBtn.setRolloverIcon(homeBtn_hover);
		homeBtn.setPressedIcon(homeBtn_hover);
		homeBtn.setBorderPainted(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setFocusPainted(false);
		homeBtn.setBounds(140, 500, 230, 40);
		rankLayered.add(homeBtn);
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new LogIn();
			}
			
		});
		
		setLocation(700, 50);
		setSize(500, 590);
		add(rankLayered);
		setVisible(true);
		setResizable(false);
	}
}
