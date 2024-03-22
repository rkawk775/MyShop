import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FoodClient_Back extends Thread {
	private String NickName, IPAddress;
	private int Port;
	private Socket socket;
	private String Message;
	private DataInputStream in;
	private DataOutputStream out;
	private Client_ChatGUI chatgui;
	ArrayList<String> NickNameList = new ArrayList<String>();
	
	public void getUserInfo(String NickName, String IPAddress, int Port) {
		this.NickName = NickName;
		this.IPAddress = IPAddress;
		this.Port = Port;
	}
	public void setGUI(Client_ChatGUI chatgui) {
		this.chatgui = chatgui;
	}
	@Override
	public void run() {
		try {
			socket = new Socket(IPAddress, Port);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			out.writeUTF(NickName);
			while (in != null) { 
				Message = in.readUTF();
				if (Message.contains("+++닉네임의시작+++")) { 
					chatgui.UserList.setText(null);
					NickNameList.add(Message.substring(12));
					chatgui.AppendUserList(NickNameList);
				} else if (Message.contains("님이 입장하셨습니다.")) {
					NickNameList.clear();
					chatgui.UserList.setText(null);
					chatgui.AppendMessage(Message);
				} else if (Message.contains("님이 퇴장하셨습니다.")) {
					NickNameList.clear();
					chatgui.UserList.setText(null);
					chatgui.AppendMessage(Message);
				} else {
					chatgui.AppendMessage(Message);
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void Transmit(String Message) {
		try {
			out.writeUTF(Message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

