package Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class LogIn extends JFrame {
	Container contentPane;
	ImageIcon start_log;
	JLabel img_start;
	JTextField idTextField;
	JPasswordField pwdField;

	LogIn() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel layered = new JPanel();
		layered.setBounds(0, 0, 500, 590);
		layered.setLayout(null);
		layered.setBackground(Color.WHITE);
		start_log = new ImageIcon(getClass().getClassLoader().getResource("start.png"));
		//����ȭ�� �׸�
		img_start = new JLabel(start_log);
		layered.add(img_start);
		img_start.setLocation(0, 0);
		img_start.setSize(500, 200);
		
		//�α����ʵ�
		JLabel loginLabel = new JLabel("LogIn");
		loginLabel.setBounds(175, 220, 160, 60);
		loginLabel.setFont(new Font("���", Font.BOLD, 48));
		loginLabel.setForeground(Color.ORANGE);
		layered.add(loginLabel);
		
		//���̵� �ʵ�
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(110, 310, 50, 30);
		idLabel.setFont(new Font("���", Font.BOLD, 15));
		layered.add(idLabel);
		
		idTextField = new JTextField(10);
		idTextField.setBounds(140, 310, 200, 30);
		layered.add(idTextField);
		
		//��й�ȣ �ʵ�
		JLabel pwdLabel = new JLabel("PW");
		pwdLabel.setBounds(105, 360, 50, 30);
		pwdLabel.setFont(new Font("���", Font.BOLD, 15));
		layered.add(pwdLabel);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(140, 360, 200, 30);
		layered.add(pwdField);
		
		pwdField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
				
			}
			
		});
		
		//�α��� ����
		idTextField.setOpaque(false);
		idTextField.setForeground(Color.darkGray);
		//��й�ȣ ����
		pwdField.setOpaque(false);
		pwdField.setForeground(Color.darkGray);
		
		//�α��� ��ư
		JButton loginBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("login.png")));
		ImageIcon login_hover = new ImageIcon(getClass().getClassLoader().getResource("login_hover.png"));
		loginBtn.setRolloverIcon(login_hover);
		loginBtn.setPressedIcon(login_hover);
		loginBtn.setBorderPainted(false);
		loginBtn.setContentAreaFilled(false);
		loginBtn.setFocusPainted(false);
		loginBtn.setBounds(350, 306, 85, 85);
		layered.add(loginBtn);
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
			}	
		});
		
		//��ŷȮ�� ��ư
		JButton rankBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("rank.png")));
		ImageIcon rank_hover = new ImageIcon(getClass().getClassLoader().getResource("rank_hover.png"));
		rankBtn.setRolloverIcon(rank_hover);
		rankBtn.setPressedIcon(rank_hover);
		rankBtn.setBorderPainted(false);
		rankBtn.setContentAreaFilled(false);
		rankBtn.setFocusPainted(false);
		rankBtn.setBounds(120, 410, 150, 65);
		layered.add(rankBtn);
		rankBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Ranking();
			}
		});
		
		//ȸ������ ��ư
		JButton joinBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("join.png")));
		ImageIcon join_hover = new ImageIcon(getClass().getClassLoader().getResource("join_hover.png"));
		joinBtn.setRolloverIcon(join_hover);
		joinBtn.setPressedIcon(join_hover);
		joinBtn.setBorderPainted(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setFocusPainted(false);
		joinBtn.setBounds(280, 410, 70, 65);
		layered.add(joinBtn);
		joinBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Join();
			}
		});


		setLocation(700, 50);
		setSize(500, 590);
		add(layered);
		setVisible(true);
		setResizable(false);
	}
	public void isLoginCheck() {
		LoginDB logindb = new LoginDB();
		if(logindb.LoginID(idTextField.getText())) {
			if(logindb.Login(idTextField.getText(), new String(pwdField.getPassword()))){
				JOptionPane.showMessageDialog(null, "���� ����!!");
				dispose();
				new Game(idTextField.getText());
			}else {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է����ּ���.");
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "�ش� ���̵� �����ϴ�. �ٽ� �Է����ּ���.");
		}
	}

	public static void main(String[] args) {
		new LogIn();
	}
}
