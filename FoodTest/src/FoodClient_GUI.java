import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FoodClient_GUI {
	public static void main(String[] args) {
		LoginGUI LG = new LoginGUI();
	}
}
class LoginGUI extends JFrame implements ActionListener{
	private JPanel Login_GUIPanel = new JPanel() ;
	private JLabel NickName_Label = new JLabel("고객명"); //데이터베이스 고객명
	private JTextField NickName_Text = new JTextField("rname", 10);
	private JTextField Port_Text = new JTextField("9000",10);
	private JTextField IPAddress_Text = new JTextField("192.168.0.35",10);
	private JLabel Port_Label = new JLabel("  포트 입력");
	private JLabel IPAddress_Label = new JLabel("  주소 입력");
	private JButton Login_GUI_Button = new JButton("접속");
	

	
	public LoginGUI() {
		setTitle("로그인 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(750,80);
		setResizable(false);
		setVisible(true);
		setLayout(new BorderLayout(50,50));
		
		Color c1 = new Color(255,204,0); //망고빛옐로
		Color c2 = new Color(237,104,60); //한라봉주황
		Color c3 = new Color(233,62,58); //자두자주
		Color c4 = new Color(210,75,10); //초콜릿
		Color c5 = new Color(255,255,224); //라이트옐로
		
		
		NickName_Label.setFont(new Font("HY견고딕",Font.PLAIN,13));
		IPAddress_Label.setFont(new Font("HY견고딕",Font.PLAIN,13));
		Port_Label.setFont(new Font("HY견고딕",Font.PLAIN,13));
		Login_GUI_Button.setFont(new Font("HY견고딕",Font.PLAIN,11));
		
		NickName_Label.setForeground(Color.WHITE);
		IPAddress_Label.setForeground(Color.WHITE);
		Port_Label.setForeground(Color.WHITE);

		Login_GUI_Button.setBackground(Color.WHITE);	
		Login_GUI_Button.setForeground(c4);
		
		NickName_Text.setBackground(c5);	
		NickName_Text.setForeground(c4);
		
		Port_Text.setBackground(c5);	
		Port_Text.setForeground(c4);
		
		IPAddress_Text.setBackground(c5);	
		IPAddress_Text.setForeground(c4);
		
		
		Login_GUI_Button.setPreferredSize(new Dimension(60,20));
		Login_GUI_Button.addActionListener(this);
		Login_GUIPanel.add(NickName_Label);
		Login_GUIPanel.add(NickName_Text);
		Login_GUIPanel.add(Port_Label);
		Login_GUIPanel.add(Port_Text);
		Login_GUIPanel.add(IPAddress_Label);
		Login_GUIPanel.add(IPAddress_Text);
		Login_GUIPanel.add(Login_GUI_Button);
		Login_GUIPanel.setBackground(c2);
		add(Login_GUIPanel);
		setContentPane(Login_GUIPanel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource() == Login_GUI_Button) {
				InetAddress Inet = InetAddress.getLocalHost();
				String NickName = NickName_Text.getText().trim();
				String IPAddress = IPAddress_Text.getText().trim();
				int Port = Integer.parseInt(Port_Text.getText().trim());
				new Client_ChatGUI(NickName, IPAddress, Port);
				setVisible(true);
			}
		} catch (Exception a) {
			JOptionPane.showMessageDialog(null, "올바르지 않은 입력입니다.");
		}
	}
}

class Client_ChatGUI extends JFrame implements ActionListener, KeyListener{
	String NickName;
	FoodClient_Back FCB = new FoodClient_Back();
	JLabel UServerLabel = new JLabel("채팅창");
	JPanel ClientGUIPanel = new JPanel();
	JLabel UserLabel = new JLabel("유저\n 목록");
	JLabel User = new JLabel(NickName);
	JTextField Chat = new JTextField(45);
	JButton Enter = new JButton("전송");
	TextArea ChatList = new TextArea(30,50);
	TextArea UserList = new TextArea(20,15);
	
	
	public Client_ChatGUI(String NickName, String IPAddress, int Port) {
		this.NickName = NickName;
		setTitle("고객 창");
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(750,620);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ChatList.setEditable(false);
		UserList.setEditable(false);
		Chat.addKeyListener(this);
		Enter.addActionListener(this);
		
		UserLabel.setFont(new Font("HY견고딕",Font.BOLD,13));
		ChatList.setFont(new Font("HY견고딕",Font.PLAIN,13));
		UserList.setFont(new Font("HY견고딕",Font.PLAIN,13));
		User.setFont(new Font("HY견고딕",Font.PLAIN,13));
		Chat.setFont(new Font("HY견고딕",Font.PLAIN,13));
		Enter.setFont(new Font("HY견고딕",Font.PLAIN,11));
		
		Color c1 = new Color(255,140,0); //다크오렌지
		Color c2 = new Color(237,104,60); //한라봉주황
		Color c3 = new Color(240,240,140); //카키색
		Color c4 = new Color(210,75,10); //초콜릿
		Color c5 = new Color(255,255,224); //라이트옐로
		Color c6 = new Color(250,250,210); //라이트골든로드옐로
		
		UserLabel.setForeground(c1);
		
		ChatList.setBackground(c6);
		UserList.setBackground(c6);
		
		ChatList.setForeground(c4);
		UserList.setForeground(c4);
		
		Enter.setBackground(Color.WHITE);	
		Enter.setForeground(c4);
		
		ClientGUIPanel.add(User);
		ClientGUIPanel.add(ChatList);
		ClientGUIPanel.add(UserLabel);
		ClientGUIPanel.add(UserList);
		ClientGUIPanel.add(Chat);
		ClientGUIPanel.add(Enter);
		add(ClientGUIPanel);
		ClientGUIPanel.setBackground(c3);
		FCB.setGUI(this);
		FCB.getUserInfo(NickName, IPAddress, Port);
		FCB.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String Message = Chat.getText().trim();
		if(e.getSource() == Enter && Message.length() > 0 ) {
			FCB.Transmit(NickName+" : "+Message+"\n");
			Chat.setText(null);
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		String Message = Chat.getText().trim();
		if (e.getKeyCode() == KeyEvent.VK_ENTER && Message.length() > 0) {
			FCB.Transmit(NickName + " : " + Message + "\n");
			Chat.setText(null);
		}
	}
	public void AppendMessage(String Message) {
		ChatList.append(Message);
	}

	public void AppendUserList(ArrayList NickName) {
		String name;
		UserList.setText(null);
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
