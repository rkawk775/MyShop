import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SwingConstants;

public class FoodServer_GUI extends JDialog {
	
	public FoodServer_GUI() {
		new Server_ChatGUI();
	}
	/*
	public static void main(String[] args) {
		new Server_ChatGUI();
	}
	*/
}

class Server_ChatGUI extends JFrame implements ActionListener, KeyListener{
	private static final int Port = 9000; 
	JPanel ServerGUI_Panel = new JPanel();
	JLabel ServerLabel = new JLabel("채팅창");
	JLabel UserLabel = new JLabel("유저 목록");
	JTextField Chat = new JTextField(45);
	JButton Enter = new JButton("전송");
	TextArea ServerChatList = new TextArea(30,50);
	TextArea UserList = new TextArea(20,15);
	FoodServer_Back FSB = new FoodServer_Back();
	JTextField Name;
	JButton backBtn = new JButton("메인 화면으로");
	
	public Server_ChatGUI() {
		setTitle("메인 서버");
		setSize(750,620);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
			
		Color c1 = new Color(255,140,0); //다크오렌지
		Color c2 = new Color(237,104,60); //한라봉주황
		Color c3 = new Color(240,240,140); //카키색
		Color c4 = new Color(210,75,10); //초콜릿
		Color c5 = new Color(255,255,224); //라이트옐로
		Color c6 = new Color(250,250,210); //라이트골든로드옐로		

		
		
		ServerChatList.setBackground(c6);
		ServerChatList.setForeground(c4);
		Chat.setForeground(c4);
		UserList.setForeground(c4);
		
		Chat.setBackground(Color.WHITE);
		Chat.setForeground(c4);
		UserList.setForeground(c4);
		UserList.setBackground(c6);
		
		UserLabel.setFont(new Font("HY견고딕",Font.BOLD,13));
		ServerLabel.setFont(new Font("HY견고딕",Font.BOLD,13));
		ServerChatList.setFont(new Font("굴림",Font.PLAIN,11));
		UserList.setFont(new Font("굴림",Font.PLAIN,11));
		Chat.setFont(new Font("굴림",Font.PLAIN,13));
		Enter.setFont(new Font("굴림",Font.PLAIN,11));
		
		ServerChatList.setEditable(false);
		UserList.setEditable(false);
		Chat.addKeyListener(this);
		Enter.addActionListener(this);
		
		Enter.setBackground(Color.WHITE);	
		Enter.setForeground(c4);
		
		UserLabel.setForeground(c4);
		ServerLabel.setForeground(c4);
		
		//750,550
		ServerGUI_Panel.setBounds(0,50,750,550);
		ServerGUI_Panel.setBackground(c3);
		ServerGUI_Panel.add(ServerLabel);
		ServerGUI_Panel.add(ServerChatList);
		ServerGUI_Panel.add(UserLabel);
		ServerGUI_Panel.add(UserList);
		ServerGUI_Panel.add(Chat);
		ServerGUI_Panel.add(Enter);
		add(ServerGUI_Panel);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0,0,750,50);
		topPanel.setBackground(c3);
		topPanel.setLayout(null);
		backBtn.setBounds(5, 5, 150, 20);
		backBtn.addActionListener(this);
		backBtn.setFont(new Font("굴림",Font.PLAIN,11));
		backBtn.setBackground(Color.WHITE);	
		backBtn.setForeground(c4);
		topPanel.add(backBtn);
		add(topPanel);
		
		UserList.append("업주\n");
		FSB.setGUI(this);
		FSB.Start_Server(Port);
		FSB.start();
		setVisible(true);
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String Message = Chat.getText().trim();
		Component c = (Component)e.getSource();
		
		if(c == Enter && Message.length() > 0 ) {
			AppendMessage("업주 : "+Message+"\n");
			FSB.Transmitall("업주 : "+Message+"\n");
			Chat.setText(null);
		}
		else if(c == backBtn) {
			ReservationFrame frame = new ReservationFrame();
			frame.setVisible(true);
			dispose();
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		String Message = Chat.getText().trim();
		if(e.getKeyCode() == KeyEvent.VK_ENTER && Message.length() > 0 ) {
			AppendMessage("업주 : "+Message+"\n");
			FSB.Transmitall("업주 : "+Message+"\n");
			Chat.setText(null);
	}
}
	public void AppendMessage(String Message) {
		ServerChatList.append(Message);
	}

	public void AppendUserList(ArrayList NickName) {
		String name;
		for(int i=0; i<NickName.size(); i++) {
			name = (String) NickName.get(i);
			UserList.append(name+"\n");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}
	@Override
	public void keyReleased(KeyEvent e) {
	
	}

}
	


	
	
	
	
	