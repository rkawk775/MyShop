import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Food_ShopManagement extends JFrame {

	private JPanel ppanel;
	private JButton sbutton, ibutton, dbutton, reservMenu, manageMenu, menuMenu;
	private JTextField stextfield;
	private LineBorder bb;

	public Food_ShopManagement() {

		setTitle("매장관리");
		setSize(800, 455);
		setResizable(false);
		setLocationRelativeTo(null);

		LineBorder bb = new LineBorder(Color.MAGENTA, 7, false);

		class MyMouseListener extends MouseAdapter {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				JLabel j = (JLabel) e.getSource();
				boolean flag = true;
				String ltext = j.getText();
				stextfield.setText(ltext);
				if (flag = true) {
					j.setBorder(new LineBorder(Color.MAGENTA, 7, false));
					flag = false;
					return;
				} else {
					j.setBorder(new LineBorder(Color.BLACK, 7, false));
					flag = true;
					return;
				}
			}
		}

		ppanel = new JPanel();
		ppanel.setLayout(null);
		Container ct = getContentPane();
		ct.add(ppanel);

		ppanel.setBackground(new Color(119, 187, 221));

		stextfield = new JTextField(20);
		ppanel.add(stextfield);
		stextfield.setBounds(200, 20, 150, 26);

		ibutton = new JButton("메뉴넣기");
		ppanel.add(ibutton);
		ibutton.setBounds(500, 20, 90, 25);
		ibutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuDTO mdto = new MenuDTO();
				
				mdto.getTitle();								
			}

		});
		
		//삭제(DB)
		dbutton = new JButton("메뉴삭제");
		ppanel.add(dbutton);
		dbutton.setBounds(600, 20, 90, 25);
		dbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals("")) {
					JOptionPane.showMessageDialog(null, "삭제실패");
					return;
				} else {
					e.setSource("");
					JOptionPane.showMessageDialog(null, "삭제완료");
				}
			}
		});

		JPanel menu = new JPanel(new GridLayout(3, 4, 10, 10));
		ppanel.add(menu);
		menu.setBounds(150, 70, 600, 300);
		menu.setBackground(new Color(119, 187, 221));
		menu.setBorder(new LineBorder(Color.BLACK, 0, false));

		MyMouseListener listener = new MyMouseListener();

		JLabel menu1 = new JLabel("라면");
		menu1.setBorder(new LineBorder(Color.BLACK, 5, false));
		menu.add(menu1);
		menu1.addMouseListener(listener);

		JLabel menu2 = new JLabel(" ");
		menu2.setBorder(new LineBorder(Color.BLACK, 5, false));
		menu.add(menu2);
		menu2.addMouseListener(listener);

		JLabel menu3 = new JLabel(" ");
		menu3.setBorder(new LineBorder(Color.BLACK, 5, false));
		menu.add(menu3);
		menu3.addMouseListener(listener);

		JLabel menu4 = new JLabel(" ");
		menu4.setBorder(new LineBorder(Color.BLACK, 5, false));
		menu.add(menu4);
		menu4.addMouseListener(listener);

		JLabel menu5 = new JLabel(" ");
		menu5.setBorder(new LineBorder(Color.BLACK, 5, false));
		menu.add(menu5);
		menu5.addMouseListener(listener);

		JLabel menu6 = new JLabel("");
		menu6.setBorder(new LineBorder(Color.BLACK, 5, false));
		menu.add(menu6);
		menu6.addMouseListener(listener);

		JLabel menu7 = new JLabel(" ");
		menu7.setBorder(new LineBorder(Color.BLACK, 5, false));
		menu.add(menu7);
		menu7.addMouseListener(listener);

		JLabel menu8 = new JLabel(" ");
		menu8.setBorder(new LineBorder(Color.BLACK, 5, false));
		menu.add(menu8);
		menu8.addMouseListener(listener);

		JLabel menu9 = new JLabel(" ");
		menu9.setBorder(new LineBorder(Color.BLACK, 5, false));
		menu.add(menu9);
		menu9.addMouseListener(listener);

		JLabel menu10 = new JLabel(" ");
		menu10.setBorder(new LineBorder(Color.BLACK, 5, false));
		menu.add(menu10);
		menu10.addMouseListener(listener);

		JLabel menu11 = new JLabel("");
		menu11.setBorder(new LineBorder(Color.BLACK, 5, false));
		menu.add(menu11);
		menu11.addMouseListener(listener);

		JLabel menu12 = new JLabel("");
		menu12.setBorder(new LineBorder(Color.BLACK, 5, false));
		menu.add(menu12);
		menu12.addMouseListener(listener);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(0, 0, 120, 432);
		ppanel.add(panel_3);
		panel_3.setBackground(new Color(243, 144, 63));

		JLabel logo = new JLabel();
		logo.setBounds(4, 5, 220, 70);

		ImageIcon icon = new ImageIcon("images/logo1.png");
		Image img = icon.getImage();
		Image sizeImge = img.getScaledInstance(110, 70, Image.SCALE_SMOOTH);
		ImageIcon sizeIcon = new ImageIcon(sizeImge);
		logo.setIcon(sizeIcon);
		panel_3.add(logo);
		
		//메뉴 검색
		sbutton = new JButton("메뉴검색");
		ppanel.add(sbutton);
		sbutton.setBounds(400, 20, 90, 25);
		sbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = stextfield.getText();
				List<MenuDTO> menuList = null;
				if (text.equals("")) {
					JOptionPane.showMessageDialog(null, "검색창에 메뉴명을 입력해주세요.");
					return;
				}
				else {
					menuList=MenuDAO.getDAO().selectMenuList(text);
				}
			}
		});

		sbutton.setBounds(400, 20, 90, 25);
		reservMenu = new JButton("예약 관리");
		reservMenu.setBounds(12, 80, 95, 30);
		reservMenu.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		reservMenu.setBackground(Color.WHITE);
		panel_3.add(reservMenu);

		manageMenu = new JButton("매장 관리");
		manageMenu.setBounds(12, 120, 95, 30);
		manageMenu.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		manageMenu.setBackground(Color.WHITE);
		panel_3.add(manageMenu);
		menuMenu = new JButton("메뉴 관리");
		menuMenu.setBounds(12, 160, 95, 30);
		menuMenu.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		menuMenu.setBackground(Color.WHITE);
		panel_3.add(menuMenu);

		/*
		 * @Override public void actiondPerforme(ActionEvent ev) {
		 * 
		 * Component c = (Component) ev.getSource(); if(c == reservMenu) {
		 * Food_ShopManagement frame = new Food_ShopManagement();
		 * frame.setVisible(true); } else if(c == manageMenu){ } else { return; }
		 * 
		 * 
		 * reservMenu.addActionListener(this); manageMenu.addActionListener(this);
		 * menuMenu.addActionListener(this);
		 */

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			/*
			 * ClassNotFoundException InstantiationException IllegalAccessException
			 * UnsupportedLookAndFeelException
			 */
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Food_ShopManagement fsm = new Food_ShopManagement();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
