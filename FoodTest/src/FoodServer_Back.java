import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import javax.sound.midi.Receiver;

public class FoodServer_Back extends Thread {
	Vector<ReceiveInfo> ClientList = new Vector<ReceiveInfo>();
	ArrayList<String> NickNameList = new ArrayList<String>();
	ServerSocket serversocket;
	Socket socket;
	private Server_ChatGUI serverchatgui;
	
	public void setGUI(Server_ChatGUI serverchatgui) {
		this.serverchatgui = serverchatgui;
	}
	
	public void Start_Server(int Port) {
		try {
			Collections.synchronizedList(ClientList);
			serversocket = new ServerSocket(Port);
			System.out.println("현재 아이피와 포트 넘버는 ["+InetAddress.getLocalHost()+"]");
			
		}catch (Exception e) {
			System.out.println("오류났수");
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void run() {
		try {
			NickNameList.add("Admin");
			while (true) {
				System.out.println("새 접속을 대기합니다.");
				socket = serversocket.accept();
				System.out.println("["+socket.getInetAddress()+"]에서 접속하셨습니다.");
				ReceiveInfo receive = new ReceiveInfo(socket);
				ClientList.add(receive);
				receive.start();
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void Transmitall(String Message) {
		for(int i=0; i<ClientList.size(); i++) {
			try {
			ReceiveInfo ri = ClientList.elementAt(i);
			ri.Transmit(Message);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
	
public void removeClient(ReceiveInfo Client, String NickName) {
	ClientList.removeElement(Client);
	NickNameList.remove(NickName);
	System.out.println(NickName+"을 삭제 완료했습니다.");
	serverchatgui.UserList.setText(null);
	serverchatgui.AppendUserList(NickNameList);
}

class ReceiveInfo extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	String NickName;
	String Message;
	
	public ReceiveInfo(Socket socket) {
		try {
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			NickName = in.readUTF();
			NickNameList.add(NickName);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void run() {
		try {
			serverchatgui.UserList.setText(null);
			serverchatgui.AppendUserList(NickNameList);
			Transmitall(NickName+"님이 입장하셨습니다. \n");
			for(int i=0; i<NickNameList.size(); i++) {
				Transmitall("+++닉네임의 시작+++"+NickNameList.get(i));
			}
			serverchatgui.AppendMessage(NickName+"님이 입장하셨습니다. \n");
			while(true) {
				Message = in.readUTF();
				serverchatgui.AppendMessage(Message);
				Transmitall(Message);
			}
		}catch (Exception e) {
			System.out.println(NickName+"님이 퇴장하셨습니다. \n");
			removeClient(this, NickName);
			Transmitall(NickName+"님이 퇴장하셨습니다. \n");
			for(int i=0; i<NickNameList.size();i++) {
				Transmitall("+++닉네임의 시작+++"+NickNameList.get(i));
			}
			serverchatgui.AppendMessage(NickName+"님이 퇴장하셨습니다. \n");
		}
	}

	public void Transmit(String message) {
		try {
			out.writeUTF(Message);
			out.flush();
		} catch (Exception e) {
			e.getStackTrace();
			}
		}
	}
}