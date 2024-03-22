import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Food_Login_Register extends JFrame {
	
	private JPanel fpanel;
	private JLabel flabel;
	private JLabel lId, lPassword, lName, lOwnername, lTel, lLocation;
	private JTextField tId, tPassword, tName, tOwnername, tTel, tLocation;
	private JButton fButton;

	public Food_Login_Register() {

		super("회원가입");
		super.setResizable(false);
		setSize(400, 450);
		setLocationRelativeTo(null);

		fpanel = new JPanel();
		fpanel.setLayout(new BorderLayout(2, 30));
		setContentPane(fpanel);
		fpanel.setBackground(new Color(243,144,63));

		flabel = new JLabel("회원가입");
		flabel.setFont(new Font("굴림", Font.BOLD, 50));
		flabel.setPreferredSize(new Dimension(120, 100));
		flabel.setHorizontalAlignment(SwingConstants.CENTER);
		fpanel.add(flabel, BorderLayout.NORTH);

		JPanel main = new JPanel(new GridLayout(7, 10, 10, 10));

		lId = new JLabel("아이디");
		lId.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(lId);

		tId = new JTextField();
		main.add(tId);

		lPassword = new JLabel("비밀번호");
		lPassword.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(lPassword);

		tPassword = new JTextField();
		main.add(tPassword);

		lName = new JLabel("매장이름");
		lName.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(lName);

		tName = new JTextField();
		main.add(tName);

		lOwnername = new JLabel("사업자이름");
		lOwnername.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(lOwnername);

		tOwnername = new JTextField();
		main.add(tOwnername);

		lTel = new JLabel("매장전화번호");
		lTel.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(lTel);

		tTel = new JTextField();
		main.add(tTel);

		lLocation = new JLabel("매장위치");
		lLocation.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(lLocation);

		tLocation = new JTextField();
		main.add(tLocation);

		fpanel.add(main, BorderLayout.CENTER);
		
		main.setBackground(new Color(243,144,63));

		JPanel smain = new JPanel();
		fpanel.add(smain, BorderLayout.EAST);

		fButton = new JButton("등록하기");
		fButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		fpanel.add(fButton, BorderLayout.SOUTH);
		
		smain.setBackground(new Color(243,144,63));
		
		try {
		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		

		fButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Food_LoginDTO dto = new Food_LoginDTO();

				dto.setId(tId.getText());
				dto.setPassword(tPassword.getText());
				dto.setName(tName.getText());
				dto.setOwnername(tOwnername.getText());
				dto.setTel(tTel.getText());
				dto.setLocation(tLocation.getText());

				Food_Login_DAO dao = Food_Login_DAO.getInstance();
				int result = dao.insertinfo(dto);
				
				if(tId.equals("")) {
					return;
				}
				if(tPassword.equals("")) {
					return;
				}
				if(tName.equals("")) {
					return;
				}
				if(tOwnername.equals("")) {
					return;
				}
				if(tTel.equals("")) {
					return;
				}
				if(tLocation.equals("")) {
					return;
				}

				if (result == 1) {
					JOptionPane.showMessageDialog(null, "사업자등록성공");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "사업자등록실패");
					dispose();
				}
			}
		});

	}
/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Food_Login_Register flr = new Food_Login_Register();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}*/
}
