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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class Join extends JFrame {
	JTextField nameTextField, idTextField, emailTextField;
	JPasswordField pwdTextField, pwdTextField2;

	Join() {

		setTitle("JOIN");
		
		Container contentPane;
		// ȸ������ �г�
		JPanel joinLayered = new JPanel();
		joinLayered.setBounds(0, 0, 500, 590);
		joinLayered.setLayout(null);
		joinLayered.setBackground(Color.WHITE);

		// ȸ������ �ʵ�
		JLabel joinLabel = new JLabel("Sign Up");
		joinLabel.setBounds(150, 20, 200, 80);
		joinLabel.setFont(new Font("���", Font.BOLD, 50));
		joinLabel.setForeground(Color.ORANGE);
		joinLayered.add(joinLabel);

		// �̸� �ʵ�
		JLabel nameLabel = new JLabel("Name :");
		nameLabel.setBounds(100, 150, 50, 30);
		nameLabel.setFont(new Font("���", Font.BOLD, 15));
		joinLayered.add(nameLabel);

		nameTextField = new JTextField(10);
		nameTextField.setBounds(160, 150, 200, 30);
		joinLayered.add(nameTextField);

		// ���̵� �ʵ�
		JLabel idLabel = new JLabel("ID :");
		idLabel.setBounds(123, 200, 50, 30);
		idLabel.setFont(new Font("���", Font.BOLD, 15));
		joinLayered.add(idLabel);

		idTextField = new JTextField(10);
		idTextField.setBounds(160, 200, 200, 30);
		joinLayered.add(idTextField);

		// ��й�ȣ �ʵ�
		JLabel pwdLabel = new JLabel("Password :");
		pwdLabel.setBounds(70, 250, 80, 30);
		pwdLabel.setFont(new Font("���", Font.BOLD, 15));
		joinLayered.add(pwdLabel);

		pwdTextField = new JPasswordField();
		pwdTextField.setBounds(160, 250, 200, 30);
		joinLayered.add(pwdTextField);

		// ��й�ȣ Ȯ�� �ʵ�
		JLabel pwdLabel2 = new JLabel("Password(*) :");
		pwdLabel2.setBounds(55, 300, 100, 30);
		pwdLabel2.setFont(new Font("���", Font.BOLD, 15));
		joinLayered.add(pwdLabel2);

		pwdTextField2 = new JPasswordField();
		pwdTextField2.setBounds(160, 300, 200, 30);
		joinLayered.add(pwdTextField2);

		// �̸��� �ʵ�
		JLabel emailLabel = new JLabel("Email :");
		emailLabel.setBounds(100, 350, 80, 30);
		emailLabel.setFont(new Font("���", Font.BOLD, 15));
		joinLayered.add(emailLabel);

		emailTextField = new JTextField(10);
		emailTextField.setBounds(160, 350, 200, 30);
		joinLayered.add(emailTextField);

		// ȸ������ ��ư
		JButton joinBtn = new JButton(new ImageIcon(getClass().getClassLoader().getResource("signup.png")));
		ImageIcon joinBtn_hover = new ImageIcon(getClass().getClassLoader().getResource("signup_hover.png"));
		joinBtn.setRolloverIcon(joinBtn_hover);
		joinBtn.setPressedIcon(joinBtn_hover);
		joinBtn.setBorderPainted(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setFocusPainted(false);
		joinBtn.setBounds(140, 420, 230, 40);
		joinLayered.add(joinBtn);
		joinBtn.addActionListener(new JoinAction());
		
		//�ڷΰ��� ��ư
		JButton backBtn = new JButton(new ImageIcon("images/back.png"));
		ImageIcon backBtn_hover = new ImageIcon("images/back_hover.png");
		backBtn.setRolloverIcon(backBtn_hover);
		backBtn.setPressedIcon(backBtn_hover);
		backBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setFocusPainted(false);
		backBtn.setBounds(10, 10, 30, 30);
		joinLayered.add(backBtn);
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LogIn();
			}
		});

		setLocation(700, 50);
		setSize(500, 590);
		add(joinLayered);
		setVisible(true);
		setResizable(false);
	}

	//ȸ������ �Ǵ�
	class JoinAction implements ActionListener {
		User user = new User();

		@Override
		public void actionPerformed(ActionEvent e) {
			JoinDB joindb = new JoinDB();
			if (nameTextField.getText().equals("") || idTextField.getText().equals("")
					|| new String(pwdTextField.getPassword()).equals("") || emailTextField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�Է����� ���� �׸��� �ֽ��ϴ�.");
			} else if (!(new String(pwdTextField.getPassword()).equals(new String(pwdTextField2.getPassword())))) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �߸� �Է��߽��ϴ�. Ȯ�����ּ���.");
			} else if (joindb.JoinID(idTextField.getText())) {
				JOptionPane.showMessageDialog(null, "�̹� ���� ���̵� �ֽ��ϴ�. �ٸ� ���̵� �Է����ּ���.");
			} else {
				user.setName(nameTextField.getText());
				user.setId(idTextField.getText());
				user.setPw(new String(pwdTextField.getPassword()));
				user.setEmail(emailTextField.getText());

				if (joindb.Join(user)) {
					JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�!!");
					dispose();
					new LogIn();
				} else {
					JOptionPane.showMessageDialog(null, "ȸ�������� �����߽��ϴ�. �ٽ� �ۼ��� �ּ���.");
				}
			}
		}

	}

}
