import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/***
 * 해결해야하는 문제
 * 8. 채팅 접속 시, 사용자 이름 뜨게 하기
 * 4. 왼쪽 메뉴 테이블 연동시키기(기능 추가) 
 * 6. 현재 매장 남은 좌석 보이게 하기 (할 수 있으면)
 * 7. 예약관리 페이지일때, 옆에 버튼 색상 다르게 (해당 페이지인 것 인지할 수 있게 하기)
 */

public class ReservationFrame extends JFrame implements ActionListener { 
	private static final long serialVersionUID = 1L;
	private JTable table;
	
	private JPanel contentPane;
	private JTextField idTF, nameTF,phoneTF,dateTF,countTF,searchNTF;
	private JComboBox<String> timeJR;
	
	String Time[] = {"예약시간 선택하세요.","17:00","18:00","19:00","20:00","21:00"};
	JButton updateButton, deleteButton, serachButton, chatButton, clearButton;   
	JButton reservMenu, manageMenu,menuMenu;            //예약관리페이지(reservationFrame), 매장정보관리, 메뉴관리페이지
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationFrame frame = new ReservationFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}			}
		});
	}*/
	/**
	 * Create the frame.
	 */
	
	public ReservationFrame() {
		setResizable(false);
		setTitle("예약 관리 시스템");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 1000, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//JTable 
		String[] columnTitles={"대기번호","이름","전화번호","인원수","날짜","예약시간"};
		String[][] rowData={};
		
		DefaultTableModel tableModel = new DefaultTableModel(rowData, columnTitles);
		table = new JTable(new DefaultTableModel(new Object[][] {},
			new String[] {
				"대기번호","이름", "전화번호", "인원수", "날짜", "예약시간"
			}
		));
		table.setShowGrid(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int rowIndex = table.getSelectedRow();
				idTF.setText(table.getValueAt(rowIndex, 0)+"");
				nameTF.setText(table.getValueAt(rowIndex, 1)+"");
				phoneTF.setText(table.getValueAt(rowIndex, 2)+"");
				countTF.setText(table.getValueAt(rowIndex, 3)+"");
				dateTF.setText(table.getValueAt(rowIndex, 4)+"");	
				
				String timeValue = table.getValueAt(rowIndex, 5)+"";

				if(timeValue.equals(Time[0]+""))
					timeJR.setSelectedItem(Time[0]);
				else if(timeValue.equals(Time[1]+""))
					timeJR.setSelectedItem(Time[1]);
				else if(timeValue.equals(Time[2]+""))
					timeJR.setSelectedItem(Time[2]);
				else if(timeValue.equals(Time[3]+""))
					timeJR.setSelectedItem(Time[3]);
				else if(timeValue.equals(Time[4]+""))
					timeJR.setSelectedItem(Time[4]);
				else if(timeValue.equals(Time[5]+""))
					timeJR.setSelectedItem(Time[5]);
			}
		});
		
		// 테이블 내용 가운데 정렬하기
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); 
		// Renderer의 정렬을 가운데 정렬로 지정
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		//정렬할 테이블의 ColumnModel을 가져옴 
		TableColumnModel tcm = table.getColumnModel();
		
		 
		//반복문을 이용 전체 열에 지정
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
			}
		
		JScrollPane pane_table = new JScrollPane(table);
		pane_table.setSize(571, 252);
		pane_table.setLocation(306, 86);
		table.setBounds(450, 96, 300, 228);
		
		getContentPane().add(pane_table, BorderLayout.CENTER);
		
		//상단 검색 패널
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(300, 30, 600, 383);
		contentPane.add(panel_2);

		JLabel searchNTFLabel = new JLabel("대기 번호 검색");
		searchNTFLabel.setFont(new Font("굴림", Font.PLAIN, 11));
		searchNTFLabel.setBounds(11, 305, 57, 15);
		panel_2.add(searchNTFLabel);
		
		searchNTF = new JTextField();
		searchNTF.setColumns(10);
		searchNTF.setBounds(62, 302, 200, 21);
		panel_2.add(searchNTF);
		
		serachButton = new JButton("예약 검색");
		serachButton.setBounds(590, 15, 93, 36);
		serachButton.setFont(new Font("굴림", Font.PLAIN, 13));
		panel_2.add(serachButton);
		
		clearButton = new JButton("새로고침");
		clearButton.setBounds(610, 15, 93, 36);
		clearButton.setFont(new Font("굴림", Font.PLAIN, 13));
		panel_2.add(clearButton);
		
		
		//메뉴 패널
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(0,0,100,432);
		contentPane.add(panel_3);
		panel_3.setBackground(new Color(243,144,63));
		
		JLabel logo = new JLabel();
		logo.setBounds(3,0,100,70);
		
		ImageIcon icon = new ImageIcon("images/logo1.png");
		Image img = icon.getImage();
		Image sizeImge = img.getScaledInstance(100, 70, Image.SCALE_SMOOTH);
		ImageIcon sizeIcon = new ImageIcon(sizeImge);
		logo.setIcon(sizeIcon);
		panel_3.add(logo);
		
		reservMenu = new JButton("예약 관리");         //버튼 클릭 시, reservationFrame으로 이동
		reservMenu.setBounds(0,80,100,30);
		reservMenu.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		reservMenu.setBackground(Color.WHITE);
		//reservMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE));  버튼 테두리
		panel_3.add(reservMenu);
		manageMenu = new JButton("매장 관리");         //버튼 클릭 시, 매장 내 좌석 여부 관리
		manageMenu.setBounds(0,110,100,30);
		manageMenu.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		manageMenu.setBackground(Color.WHITE);
		panel_3.add(manageMenu);
		menuMenu = new JButton("메뉴 관리");           //버튼 클릭 시, 메뉴 관리
		menuMenu.setBounds(0,140,100,30);
		menuMenu.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		menuMenu.setBackground(Color.WHITE);
		panel_3.add(menuMenu);
		
		
		
		
		
		//정보 표출 패널
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(100, 0, 400, 383);   
		contentPane.add(panel);
		
		JLabel idLabel = new JLabel("대기번호");
		idLabel.setFont(new Font("굴림", Font.PLAIN, 11));
		idLabel.setBounds(11, 30, 57, 15);
		panel.add(idLabel);
		
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("굴림", Font.PLAIN, 11));
		nameLabel.setBounds(11, 65, 57, 15);
		panel.add(nameLabel);
		
		JLabel telLabel = new JLabel("전화번호");
		telLabel.setFont(new Font("굴림", Font.PLAIN, 11));
		telLabel.setBounds(11, 100, 57, 15);
		panel.add(telLabel);
		
		JLabel countLabel = new JLabel("인원 수");
		countLabel.setFont(new Font("굴림", Font.PLAIN, 11));
		countLabel.setBounds(11, 135, 57, 15);
		panel.add(countLabel);
		
		JLabel dateLabel = new JLabel("날짜");
		dateLabel.setFont(new Font("굴림", Font.PLAIN, 11));
		dateLabel.setBounds(11, 170, 57, 15);
		panel.add(dateLabel);
		
		JLabel timeLabel = new JLabel("시간");
		timeLabel.setFont(new Font("굴림", Font.PLAIN, 11));
		timeLabel.setBounds(11, 210, 40, 15);
		panel.add(timeLabel);
		
		idTF = new JTextField();
		idTF.setColumns(10);
		idTF.setBounds(62, 27, 116, 21);
		idTF.setEditable(false);                     //id값 변경 불가능하게 textfield 막아둠
		idTF.setBackground(Color.WHITE);
		panel.add(idTF);
		
		nameTF = new JTextField();
		nameTF.setColumns(10);
		nameTF.setBounds(62, 62, 116, 21);
		panel.add(nameTF);
		
		phoneTF = new JTextField();
		phoneTF.setColumns(10);
		phoneTF.setBounds(62, 97, 116, 21);
		panel.add(phoneTF);
		
		countTF = new JTextField();
		countTF.setColumns(10);
		countTF.setBounds(62, 132, 116, 21);
		panel.add(countTF);
		
		dateTF = new JTextField();
		dateTF.setColumns(10);
		dateTF.setBounds(62, 167, 116, 21);
		panel.add(dateTF);
		
		timeJR = new JComboBox<String>(Time);
		timeJR.setBounds(62, 202, 130, 30);
		timeJR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String time = timeJR.getSelectedItem().toString();
			}
		});
		panel.add(timeJR);
		
		updateButton = new JButton("예약 변경");
		updateButton.setBounds(62, 250, 93, 36);
		updateButton.setFont(new Font("굴림", Font.PLAIN, 13));
		panel.add(updateButton);
		
		deleteButton = new JButton("예약 삭제");
		deleteButton.setBounds(62, 290, 93, 36);
		deleteButton.setFont(new Font("굴림", Font.PLAIN, 13));
		panel.add(deleteButton);
		
		chatButton = new JButton("고객 상담");
		chatButton.setBounds(62, 330, 93, 36);
		chatButton.setFont(new Font("굴림", Font.PLAIN, 13));
		panel.add(chatButton);
		

		
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);
		serachButton.addActionListener(this);
		chatButton.addActionListener(this);
		clearButton.addActionListener(this);
		reservMenu.addActionListener(this);          //예약 reservationFrame으로 감
		manageMenu.addActionListener(this);          //매장 관리 프레임으로 이동
		menuMenu.addActionListener(this);            //메뉴 관리 프레임으로 이동

		
		displayAllLReservManage(0,0);
	}
	
	
	//테이블에 저장된 모든 정보를 검색하여 JTable 컴퍼넌트에 출력하는 메소드   - 테이블 초기화(refresh)
	public void displayAllLReservManage(int mode,int temp) {
		List<ReservationDTO> reservationList=null;
		if(mode == 0) {
			reservationList=ReservationDAO.getDAO().selectAllManagerList();
		} else if(mode==1) {
			reservationList=ReservationDAO.getDAO().selectIdManageList(temp);
		}
		
		DefaultTableModel model=(DefaultTableModel)table.getModel(); 
		
		for (int i = model.getRowCount(); i >0; i--) {
			model.removeRow(0);
		}	
		//JTable 컴퍼넌트에 반환받은 예약정보를 출력 
		for(ReservationDTO reservation:reservationList) {
			Vector<Object> rowData=new Vector<Object>();
			rowData.add(reservation.getRid());
			rowData.add(reservation.getRname());
			rowData.add(reservation.getRtel());
			rowData.add(reservation.getRcount());
			rowData.add(reservation.getRdate());
			rowData.add(reservation.getRtime());
			model.addRow(rowData);
		}
	}
	
	//정보란 초기화
	public void infoClear() {
		idTF.setText("");
		nameTF.setText("");
		phoneTF.setText("");
		countTF.setText("");
		dateTF.setText("");	
		timeJR.setSelectedItem(Time[0]);
	}
	
	
	//id제공, 예약 정보 삭제 - '정보 삭제'
	public void deleteReservManage() {
		int id = Integer.parseInt(idTF.getText()); 
		
		if (Integer.toString(id).equals("")) {
			JOptionPane.showMessageDialog(this, "대기번호를 반드시 입력해 주세요");
			idTF.requestFocus();
			return;
		}
				
		int rows=ReservationDAO.getDAO().deleteReserv(id);
		
		if(rows>0) {
			JOptionPane.showMessageDialog(this,rows+"개의 예약정보를 삭제하였습니다.");
			displayAllLReservManage(0, 0);
		} else {
			JOptionPane.showMessageDialog(this, "삭제하고자 하는 대기번호가 없습니다.");
		}
	}
		
	
	//예약정보 제공, 예약정보 변경 - '정보 변경'
	public void updateLunchManage() {
		//변경하기 위해서 modify
		int id=Integer.parseInt(idTF.getText());
		
		String name = nameTF.getText();
		
		if(name.equals("")) {//입력값이 없는 경우
			JOptionPane.showMessageDialog(this, "이름을 반드시 입력해 주세요.");
			nameTF.requestFocus();//입력초점을 이동하는 메소드
			return;
		}
		
		String nameReg="[가-힣]{2,7}";//정규표현식
		if(!Pattern.matches(nameReg, name)) {     //정규표현식과 입력값의 형식이 다른 경우
			JOptionPane.showMessageDialog(this, "이름은 2~5 범위의 한글만 입력해 주세요.");
			nameTF.requestFocus();
			return;
		}
		
		String phone=phoneTF.getText();
		
		if(phone.equals("")) {//입력값이 없는 경우
			JOptionPane.showMessageDialog(this, "전화번호를 반드시 입력해 주세요.");
			phoneTF.requestFocus();//입력초점을 이동하는 메소드
			return;
		}
		
		String phoneReg="\\d{2,3}-\\d{3,4}-\\d{4}"; //정규표현식
		if(!Pattern.matches(phoneReg, phone)) {     //정규표현식과 입력값의 형식이 다른 경우
			JOptionPane.showMessageDialog(this, "전화번호를 형식에 맞게 입력해 주세요.");
			phoneTF.requestFocus();
			return;
		}
		
		int count=Integer.parseInt(countTF.getText());
		
		if(Integer.toString(count).equals("")) {
			JOptionPane.showMessageDialog(this, "인원 수를 반드시 입력해 주세요.");
			countTF.requestFocus();      
			return;
		}
		
		String date = dateTF.getText();
		
		if (date.equals("")) {
			JOptionPane.showMessageDialog(this, "날짜를 반드시 입력해 주세요");
			dateTF.requestFocus();
			return;
		}
		
		String time = timeJR.getSelectedItem().toString();
		
		if(time.equals("예약시간 선택하세요.")) {
			JOptionPane.showMessageDialog(this, "예약시간을 선택해주세요.");
			timeJR.requestFocus();
			return;
		}
		
		//DTO 인스턴스를 생성하고 컴퍼넌트의 입력값으로 필드값 변경
		ReservationDTO reservation = new ReservationDTO();
		reservation.setRid(id);
		reservation.setRname(name);
		reservation.setRtel(phone);
		reservation.setRcount(count);
		reservation.setRdate(date);
		reservation.setRtime(time);
		
		//예약정보를 전달하여 reservation 테이블에 저장된 기존 예약정보를 변경하는 DAO 클래스의 메소드호출
		int rows = ReservationDAO.getDAO().updateReserv(reservation);
		
		JOptionPane.showMessageDialog(this, "대기번호 "+id+" 번의 예약이 변경되었습니다.");
		
		displayAllLReservManage(0,0);
		
	}
	
	/**
	 * 채팅창(고객상담)에 고객 이름,대기 번호 보여주는 메소드(TextField값 가져오기)
	 * id값 받아오고, 해당 id값을 이용해서 DAO에 select문 작성 가져오기
	 * */
	public String chatClientInfo() {
		int id=Integer.parseInt(idTF.getText());
		String name = nameTF.getText();
		
		String cid = Integer.toString(id);
		String cname = name;
		String iname = cid+"번 ,"+cname;
		
		return cid+cname;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		// 메소드 호출 
		Component c = (Component) ev.getSource();
		if (c == deleteButton) {
			deleteReservManage();
		} else if (c == updateButton ) {
			updateLunchManage();
		} else if (c == serachButton) { 
			int id = Integer.parseInt(searchNTF.getText());
			if(!Integer.toString(id).equals("")) {
				searchNTF.setText("");
				displayAllLReservManage(1,id);
			}
			else {
				displayAllLReservManage(0,0);
			}
		}else if(c == chatButton) {  
			if(idTF.getText().equals("")) { //id수정 불가능하기 때문에 반드시 테이블에서 가져와야하기 때문
				JOptionPane.showMessageDialog(this, "예약 고객 정보가 필요합니다. \n"+"회원정보를 테이블에서 가져와야합니다.");
				infoClear();
				displayAllLReservManage(0,0);
				return;
			}else {
				FoodServer_GUI chat = new FoodServer_GUI();
				dispose();
			}
		}else if(c == clearButton) {  //테이블 초기화
			displayAllLReservManage(0,0);
			infoClear();
		}else if(c == reservMenu) {  //예약 페이지로 이동
			ReservationFrame frame = new ReservationFrame();
			frame.setVisible(true);
			dispose();
		}
	}
}