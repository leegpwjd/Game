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
import javax.swing.border.LineBorder;

import Game.Join.JoinAction;

class myRanking extends JFrame{
	JLabel idLabel, scoreLabel, idxLabel, rankingLabel, rankIdLabel, rankScoreLabel, myRankingLabel, myIdLabel, myScoreLabel;
	ArrayList<String[]> ranking;
	myRanking(String userId, int score){
		Container contentPane;
		setTitle("My Ranking");
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
		
		//³ªÀÇ ·©Å· Ãâ·Â
		
		for(int i = 0; i < ranking.size(); i++) {
			if(ranking.get(i)[0].equals(userId) && ranking.get(i)[1].equals(String.valueOf(score))){
				myRankingLabel = new JLabel(String.valueOf(i+1) + ".");
				myIdLabel = new JLabel(userId);
				myScoreLabel = new JLabel(String.valueOf(score));
			}
		}
		
		myRankingLabel.setBounds(170, 140, 80, 25);
		myIdLabel.setBounds(230, 140, 80, 25);
		myScoreLabel.setBounds(320, 140, 80, 25);
		
		myRankingLabel.setFont(new Font("°íµñ", Font.ITALIC, 20));
		myIdLabel.setFont(new Font("°íµñ", Font.ITALIC, 20));
		myScoreLabel.setFont(new Font("°íµñ", Font.ITALIC, 20));
		
		myRankingLabel.setForeground(Color.RED);
		myIdLabel.setForeground(Color.RED);
		myScoreLabel.setForeground(Color.RED);
		
		rankLayered.add(myRankingLabel);
		rankLayered.add(myIdLabel);
		rankLayered.add(myScoreLabel);
		
		//·©Å·À§±îÁö Ãâ·Â
		int y = 180;
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
		
		setLocation(700, 50);
		setSize(500, 590);
		add(rankLayered);
		setVisible(true);
		setResizable(false);
	}

}
