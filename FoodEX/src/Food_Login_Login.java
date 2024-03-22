import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.sun.tools.javac.Main;

public class Food_Login_Login extends JFrame {

	private JPanel lPanel;
	private JLabel lLogin, lId, lPassword;
	private JTextField tId;
	private JPasswordField tPassword;
	private JButton login, register, exit;

	public Food_Login_Login() {
		super("로그인");
		super.setResizable(false);
		setSize(365, 375);
		setLocationRelativeTo(null);

		lPanel = new JPanel();
		lPanel.setLayout(new BorderLayout());
		setContentPane(lPanel);
		lPanel.setBackground(new Color(119, 187, 221));

		
		ImageIcon icon = new ImageIcon("images/LoginLogo.png"); 
		Image img = icon.getImage();
		Image changeimg = img.getScaledInstance(350, 120,Image.SCALE_SMOOTH);
		ImageIcon changeicon = new ImageIcon(changeimg);
		 

		lLogin = new JLabel();
		lLogin.setFont(new Font("굴림", Font.BOLD, 50));
		lLogin.setIcon(changeicon);
		lLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lLogin.setPreferredSize(new Dimension(120, 120));
		lLogin.setForeground(new Color(119, 187, 221));
		lPanel.add(lLogin, BorderLayout.NORTH);

		JPanel Main = new JPanel(new GridLayout(2, 2, 15, 15));

		lId = new JLabel("아이디");
		lId.setFont(new Font("굴림", Font.BOLD, 15));
		lId.setHorizontalAlignment(SwingConstants.CENTER);
		Main.add(lId);

		tId = new JTextField();
		tId.setColumns(10);
		Main.add(tId);

		lPassword = new JLabel("비밀번호");
		lPassword.setFont(new Font("굴림", Font.BOLD, 15));
		lPassword.setHorizontalAlignment(SwingConstants.CENTER);
		Main.add(lPassword);

		tPassword = new JPasswordField();
		tPassword.setColumns(10);
		Main.add(tPassword);

		lPanel.add(Main, BorderLayout.WEST);

		Main.setBackground(new Color(119, 187, 221));

		JPanel btnMain = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 60));

		login = new JButton("로그인");
		btnMain.add(login);
		register = new JButton("회원가입");
		btnMain.add(register);
		exit = new JButton("종료");
		btnMain.add(exit);
		lPanel.add(btnMain, BorderLayout.SOUTH);
		btnMain.setBackground(new Color(119, 187, 221));
		
		try {
		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String id = tId.getText();
				String password = tPassword.getText();

				Food_Login_DAO dao = Food_Login_DAO.getInstance();

				if(id.equals("")) {
		               JOptionPane.showMessageDialog(null, "로그인 실패");
		               return;
		            }
		            if(password.equals("")) {
		               JOptionPane.showMessageDialog(null, "로그인 실패");
		               return;
		            }
				
				int result = dao.idpassword(id, password);
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "로그인 성공");
					ReservationFrame frame = new ReservationFrame();
					frame.setVisible(true);
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}

			}
		});

		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Food_Login_Register fr = new Food_Login_Register();

			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Food_Login_Login lif = new Food_Login_Login();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
