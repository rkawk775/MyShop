import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 파일 선택해서 이미지 경로 받아옴
 * 입력받은 이미지 경로를 JLabel에 붙여서 이미지를 띄운다.
 * */
public class MenuInsertFrame extends JFrame implements ActionListener{
	private JLabel jltitle, jlimg, jlmemo, jlview;   //메뉴명, 사진넣기, 메모, 사진 보이는 뷰
	private JTextField tftitle, tfmemo;
	private JButton filebtn, addbtn;       //파일 업로드 버튼, 추가 버튼
	private String imgPath = "";
	private JPanel info_panel;
	

	public MenuInsertFrame() {
		RealInsertFrame();
	}
	
	public void RealInsertFrame() {
		setTitle("메뉴 추가");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,600);
		Container c = getContentPane();
		c.setLayout(null);
		
		//top_panel(상단 패널)
		JPanel top_panel = new JPanel();
		top_panel.setBounds(0,0,400,60);
		top_panel.setBackground(new Color(243,144,63));
		
		JLabel logo = new JLabel();
		logo.setBounds(0,0,150,50);
		
		ImageIcon icon = new ImageIcon("images/logo1.png");
		Image img = icon.getImage();
		Image sizeImge = img.getScaledInstance(150, 50, Image.SCALE_SMOOTH);
		ImageIcon sizeIcon = new ImageIcon(sizeImge);
		logo.setIcon(sizeIcon);
		top_panel.add(logo);
		
		c.add(top_panel);
		
		
		//작성창
		info_panel = new JPanel();
		info_panel.setLayout(null);
		info_panel.setBounds(0,60,400,540);
		c.add(info_panel);
		
		jltitle = new JLabel("메뉴명");
		jltitle.setFont(new Font("굴림", Font.BOLD, 15));
		jltitle.setBounds(50, 30, 57, 40);
		info_panel.add(jltitle);
		
		tftitle = new JTextField();
		tftitle.setColumns(10);
		tftitle.setBounds(120, 38, 150, 21);
		info_panel.add(tftitle);
		
		jlimg = new JLabel("메뉴 사진");
		jlimg.setFont(new Font("굴림", Font.BOLD, 15));
		jlimg.setBounds(50, 70, 80, 40);
		info_panel.add(jlimg);
		
		jlview = new JLabel("버튼을 눌러 사진을 추가해주세요.");
		jlview.setBounds(50, 110, 300, 180);
		setImagLabel();
		System.out.println("-----------------");
		

		
		filebtn = new JButton("파일 업로드");
		filebtn.setFont(new Font("굴림", Font.BOLD, 10));
		filebtn.setBounds(250, 300, 100, 30);
		info_panel.add(filebtn);
		filebtn.addActionListener(this);
		
		jlmemo = new JLabel("메뉴 설명");
		jlmemo.setFont(new Font("굴림", Font.BOLD, 15));
		jlmemo.setBounds(50, 330, 80, 40);
		info_panel.add(jlmemo);
		
		tfmemo = new JTextField();
		tfmemo.setColumns(20);
		tfmemo.setBounds(50, 365, 250, 21);
		info_panel.add(tfmemo);
		
		addbtn = new JButton("메뉴 추가");
		addbtn.setFont(new Font("굴림", Font.BOLD, 10));
		addbtn.setBounds(140, 430, 100, 30);
		info_panel.add(addbtn);
		addbtn.addActionListener(this);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Component c = (Component) e.getSource();
		if (c == filebtn) {  //파일명 가져오기
	           JFileChooser fc = new JFileChooser();
	           FileNameExtensionFilter ft = new FileNameExtensionFilter("파일선택", "jpg", "png");
	           fc.setFileFilter(ft);
	           int result = fc.showOpenDialog(null);      
	           if(result == fc.APPROVE_OPTION) {
	              File sfile = fc.getSelectedFile();
	              String menuimg = sfile.getPath();   //파일 이름 가져옴
	              getImgFile(menuimg);
	           }
	           
		}else if(c == addbtn) { 
			insertMenuFrame();   //입력받은 값 DB에 저장
			//Food_ShopManagement shopmenu = new Food_ShopManagement();
			dispose();
		}
	}
	
	//파일명 받아오기
	public String getImgFile(String file) {
		imgPath = file;
		System.out.println(imgPath);
		return file;
	}
	
	//파일명에 맞는 이미지 라벨에 붙이기
	public void setImagLabel() {
		//메소드로 만들어
		if(imgPath.equals("")) {
			jlview.setFont(new Font("굴림", Font.PLAIN, 15));
			jlview.setBackground(Color.CYAN);
			jlview.setOpaque(true);
			info_panel.add(jlview);
		}else {
			/*
			ImageIcon menuicon = new ImageIcon(imgPath);
			Image menuimg = menuicon.getImage();
			Image mSizeImge = menuimg.getScaledInstance(300, 180, Image.SCALE_SMOOTH);
			ImageIcon menuIcon = new ImageIcon(mSizeImge);
			jlview.setIcon(menuIcon);
			*/
			jlview.setText("테스트");
			/*
			ImageIcon image1 = new ImageIcon(imgPath);
			jlview.setIcon(image1);
			*/
			//info_panel.add(jlview);
			info_panel.add(jlview);
		}
		
	}
	
	//입력받은 정보를 DB에 연결하는 메소드
	public void insertMenuFrame() {
		String title = tftitle.getText();
		String memo = tfmemo.getText();

		if(title.equals("")) {
			JOptionPane.showMessageDialog(this, "메뉴명을 입력해주세요.");
			tftitle.requestFocus();
			return;
		       }

		if(memo.equals("")) {
			JOptionPane.showMessageDialog(this, "메뉴 설명을 적어주세요.");
			tfmemo.requestFocus();
			return;
		       }
		if(imgPath.equals("")) {
			JOptionPane.showMessageDialog(this, "사진을 업로드해주세요.");
			return;
		       }

		MenuDTO menu = new MenuDTO();
		menu.setTitle(title);
		menu.setImage(imgPath);
		menu.setMemo(memo);

		int rows = MenuDAO.getDAO().insertMenu(menu);
	}
	
	public static void main(String[] args) {
		new MenuInsertFrame();

	}

}
