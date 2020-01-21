import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class Find_pwd extends JFrame implements ActionListener, Runnable{
	
	JLabel find_id, find_phone;  
	JTextField id_pwd, findphone;
	JButton findpwdbtn; 
	JButton devbtn = new JButton(" ");//개발과
	JButton designbtn = new JButton(" ");//디자인과
	JButton basebtn = new JButton(" ");//인문과 
	JButton BookShop = new JButton(" ");//bookshop으로 가는 페이지 
	
	private PreparedStatement pstmt = null;
	private Connection conn = null;
	private ResultSet view = null;
	 
	String strid, strtel;
	
	ImageIcon exitIcon = new ImageIcon("images/exit.jpg");
	JButton exit = new JButton(exitIcon);
	
	//Thread
		private Thread thread;
	    private JLabel label;
	    private SimpleDateFormat sf;
	  
	public Find_pwd() {  
		setTitle("PeachMelon 비밀번호 찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout()); 
		c.setLayout(null);//배치관리자를  null로 고정시킴
		
		try { 
			Class.forName("org.gjt.mm.mysql.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","mirim2");//jdbc에 연결하귀~~~~~~~~
		}catch(ClassNotFoundException e) {
			handleError(e.getMessage());
		}catch (SQLException e) {
			handleError(e.getMessage());
		}	
	 
		find_id = new JLabel("아이디 : ");
		find_phone = new JLabel("전화번호 : ");
		findphone = new JTextField("");
		id_pwd = new JTextField("");
		findpwdbtn = new JButton("확인");
		
		//배경 이미지
		ImageIcon display_pwd = new ImageIcon("images/signUp.jpg");
		Image display_pwd2 = display_pwd.getImage();
		
		JPanel image = new JPanel() {//배경 넣기
			public void paintComponent(Graphics g) {
				g.drawImage(display_pwd2,0,0,1781,950,this);
				setOpaque(false);
				super.paintComponent(g);
			}
		}; 
		
		image.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(image);
		setContentPane(scrollPane);
		
		setUndecorated(true);//메뉴바 안보이게 하는 중
		
		image.add(exit);
		exit.setBounds(1736, 0, 50,50);
		exit.setContentAreaFilled(false); 
		exit.setFocusPainted(false);
		exit.setBorderPainted(false);
		exit.addActionListener(this);
		
		/*Thread*/
		label = new JLabel(); 
        label.setFont(new Font("배달의민족 도현", Font.PLAIN, 15));
        
        sf = new SimpleDateFormat("yyyy년MM월dd일 a hh:mm:ss");
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        image.add(label);
        label.setBounds(1400,130,500,80);		
		/*Thread*/
		
		//개발과 디자인과 인문과목
				image.add(devbtn);
				image.add(designbtn);
				image.add(basebtn);
				devbtn.setBounds(673,250,140,45);
			    designbtn.setBounds(890,250,140,45);
				basebtn.setBounds(1137,250,140,45);
				devbtn.setContentAreaFilled(false);
				designbtn.setContentAreaFilled(false);
				basebtn.setContentAreaFilled(false);
				devbtn.setFocusPainted(false);
				designbtn.setFocusPainted(false);
				basebtn.setFocusPainted(false); 
				devbtn.setBorderPainted(false);
				designbtn.setBorderPainted(false);
				basebtn.setBorderPainted(false);
				devbtn.addActionListener(this);
				designbtn.addActionListener(this);
				basebtn.addActionListener(this);
		
		image.add(find_id);
		image.add(id_pwd);
		image.add(find_phone);
		image.add(findphone);
		image.add(findpwdbtn);
		
		image.add(BookShop);
		BookShop.setBounds(450,20,1000,200);
		BookShop.setContentAreaFilled(false);BookShop.setFocusPainted(false);//버튼의 테두리 없앰
		BookShop.setBorderPainted(false);
		BookShop.addActionListener(this);
		
		find_id.setBounds(600, 460, 200, 100);
		id_pwd.setBounds(750,485,300,40);
		find_phone.setBounds(565,535,200,100);
		findphone.setBounds(750,565,300,40);
		findpwdbtn.setBounds(1080,495,100,100); 
		
		find_id.setFont(new Font("메이플스토리 OTF", Font.ITALIC, 35));
		id_pwd.setFont(new Font("맑은고딕",Font.ITALIC,20));
		find_phone.setFont(new Font("메이플스토리 OTF",Font.ITALIC,35));
		findphone.setFont(new Font("맑은고딕",Font.ITALIC,20));
		findpwdbtn.setFont(new Font("메이플스토리 OTF",Font.ITALIC,25));
		//버튼의 내용을 투명하게 해준다
		findpwdbtn.setContentAreaFilled(false);
		findpwdbtn.addActionListener(this);
		
		setBounds(80,20,1785,950); 
		setVisible(true); 
	}
	 public void run() {
	        while (true) {
	           label.setText(sf.format(new Date()));
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e){}
	        }
	    }
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==BookShop) {
			new Home(); dispose();
		}
		if(e.getSource()==findpwdbtn) {
			String i_d = findphone.getText(); 
			String p_w_d = id_pwd.getText(); 
			FindPwd(i_d, p_w_d);
		}
		if(e.getSource()==devbtn) {
			new Devdisplay(strid, strtel); dispose();
		}
		if(e.getSource()==designbtn) {
			new Designdisplay(strid, strtel); dispose();
		}
		if(e.getSource()==basebtn) {
			new BasicSubject1(strid,strtel); dispose();
		}
		
		if(e.getSource()==exit) {
			System.exit(0);
		}
		
	} 
	private void FindPwd(String i_d, String p_w_d) {
		try {
			System.out.println("id :|"+i_d +"|");
			System.out.println("pwd : |"+p_w_d+"|");
			String sql = "select pw from info where tel =? and id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i_d);
			pstmt.setString(2, p_w_d);
			view = pstmt.executeQuery();
			
			if(view.next()) { 
				String checkPwd = view.getString("pw");
				JOptionPane.showMessageDialog(null, "회원님의 아이디는 "+ checkPwd+"입니다.",
						"id 확인", JOptionPane.DEFAULT_OPTION); 
			}
			else {   
			JOptionPane.showMessageDialog(null, "\t			없는 아이디 입니다.\n "
				+ "\t		다시 확인해주세요." , "id 확인", JOptionPane.ERROR_MESSAGE);	
			}
		}catch (SQLException e){
			handleError(e.getMessage());
		} 
	}

	private void handleError(String message) {
		// TODO Auto-generated method stub
		System.out.println("문제 : "+ message);
		System.exit(1);
	}
	
}
