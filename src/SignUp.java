import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;


public class SignUp extends JFrame implements ActionListener, Runnable{
	
	private PreparedStatement pstmt = null;
	private Connection conn = null; 
	private ResultSet view = null;
	
	
	private JLabel idL, passwdL, number;
	private JTextField Idfield,numberfield;
	private JPasswordField Passwdfield;
	private JButton checkbtn,createbtn, BookShop;//bookShop -> BookShop으로 가는 페이지 ;
	JButton devbtn = new JButton(" ");//개발과
	JButton designbtn = new JButton(" ");//디자인과
	JButton basebtn = new JButton(" ");//인문과  
	private ImageIcon checkIcon, okayIcon;
	  
	String strid, strtel;
	
	ImageIcon exitIcon = new ImageIcon("images/exit.jpg");
	JButton exit = new JButton(exitIcon); 
	
	//Thread
		private Thread thread;
	    private JLabel label;
	    private SimpleDateFormat sf;
	
	SignUp(){   
		 
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","mirim2");//jdbc에 연결하귀~~~~~~~~
			String sql="select * from VM";
			pstmt = conn.prepareStatement(sql);
		}catch(ClassNotFoundException e) {
			handleError(e.getMessage());
		}catch (SQLException e) {
			handleError(e.getMessage());
		}
		
		setTitle("BookShop 회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout()); 
		c.setLayout(null);//배치관리자를  null로 고정시킴
		
		 
		
		idL= new JLabel("          ID : ");
		passwdL = new JLabel("      Pwd : ");
		number=new JLabel("    전화번호 :");
		BookShop=new JButton("");
		//배경 이미지
		ImageIcon display_sign = new ImageIcon("images/signUp.jpg");  
		Image display_sign2= display_sign.getImage();
				  
		JPanel image = new JPanel() {//배경 넣기 
			public void paintComponent(Graphics g) {
				g.drawImage(display_sign2,0,0,1781,950,this);
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
		
		//추가
		image.add(number);
		image.add(idL);
		image.add(passwdL);

		image.add(BookShop);
		
		
		//아이디 비밀번호 위치 & 가로세로 조정
		idL.setBounds(529,400,200,200); 
		passwdL.setBounds(529,470,200,200);
		number.setBounds(500,530,200,200);
		
		BookShop.setBounds(450,20,1000,200);
		BookShop.setContentAreaFilled(false);BookShop.setFocusPainted(false);/*버튼의 테두리 없앰*/BookShop.setBorderPainted(false);
		BookShop.addActionListener(this);
		
		//아이디 비밀번호 폰트 & 크기
		idL.setFont(new Font("메이플스토리 OTF", Font.ITALIC, 35)); 
		passwdL.setFont(new Font("메이플스토리 OTF", Font.ITALIC, 35)); 
		number.setFont(new Font("메이플스토리 OTF", Font.ITALIC, 35));
		 
		Idfield = new JTextField("");
		Passwdfield = new JPasswordField(""); 
		numberfield=new JTextField("");
		//추가
		image.add(Idfield); 
		image.add(Passwdfield);
		image.add(numberfield); 
		//아이디 비밀번호 적기의 위칭 & 가로세로 조정
		Idfield.setBounds(690, 470, 400, 50);
		Passwdfield.setBounds(690, 540, 400,50);
		numberfield.setBounds(690,610,400,50);
		//써지는 폰트 정하기 
		Idfield.setFont(new Font("맑은고딕", Font.ITALIC, 20)); 
		Passwdfield.setFont(new Font("맑은고딕", Font.ITALIC, 20)); 
		numberfield.setFont(new Font("맑은고딕",Font.ITALIC,20));
		//중복체크 이미지 아이콘
		checkIcon = new ImageIcon("images/check1.png"); 
		checkbtn = new JButton("id 중복체크",checkIcon); 
		//중복체크버튼 위치 & 가로 세로 조정
		checkbtn.setBounds(1100,450,190,80);
		//중복체크버튼 폰트 정하기
		checkbtn.setFont(new Font("메이플스토리 OTF", Font.ITALIC, 18)); 
		//추가
		image.add(checkbtn);
		checkbtn.addActionListener(this);
		  
		
		//오케이 이미지 아이콘
		okayIcon = new ImageIcon("images/okay.png");
 		createbtn=new JButton(okayIcon);
		//생성버튼 위치 & 가로세로 조정
		createbtn.setBounds(1105,630,70,70);
		//생성버튼 위치 & 가로세로 조정
		createbtn.setFont(new Font("메이플스토리 OTF", Font.ITALIC, 15));
		//추가
		image.add(createbtn);//오케이 버튼
		createbtn.addActionListener(this);
		//버튼의 테두리 없애기
		createbtn.setBorderPainted(false);
		//버튼의 선택되었을 때 생기는 테두리 없앤다.
		checkbtn.setFocusPainted(false);
		createbtn.setFocusPainted(false);
		//버튼의 내용을 투명하게 해준다
		checkbtn.setContentAreaFilled(false);
		createbtn.setContentAreaFilled(false); 

		 
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
	private void handleError(String message) {
		// TODO Auto-generated method stub
		System.out.println("문제 : "+ message);
		System.exit(1);
		
	} 
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==BookShop) {
			new Home();  dispose();
		}
		if(e.getSource()==checkbtn) { 
			String textid = Idfield.getText();//textfield에 있는 내용을 id에 넣는다.
			Checkid(textid);
		}
		if(e.getSource()==createbtn) {
			String 아이디 = Idfield.getText();
			String 비밀번호 = Passwdfield.getText();
			String 전화번호 = numberfield.getText();
			insertInfo(아이디, 비밀번호, 전화번호);
			new Home(); dispose();
		}
		if(e.getSource()==devbtn) {
			new Devdisplay(strid, strtel); dispose();
		}
		if(e.getSource()==designbtn) {
			new Designdisplay(strid, strtel); dispose();
		}
		if(e.getSource()==basebtn) {
			new BasicSubject1(strid, strtel); dispose();
		}
		if(e.getSource()==exit) {
			System.exit(0);
		}
	}  
	 
	private void Checkid(String textid) {//it : textfield에 있는 내용
		try {
			System.out.println("id :|"+textid +"|");
			String sql = "select id from info where id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, textid);
			view = pstmt.executeQuery();		 
				if(view.next()) {
					String ch =  view.getString("id");
					System.out.println("a : "+ ch);
					JOptionPane.showMessageDialog(null,"이미 존재하는 id입니다.", "id 중복확인", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"\t\t                      "
							+ "  사용 가능합니다.", "id 중복확인", JOptionPane.DEFAULT_OPTION);
				}
			   
		}catch(Exception e){ 
			e.printStackTrace();
			handleError(e.getMessage()); 
		} 
	}
	 
	private void insertInfo(String 아이디, String 비밀번호, String 전화번호) {
		try {
			String sql = "insert into info (id, pw, tel) VALUES (?,?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, 아이디);
			pre.setString(2, 비밀번호);
			pre.setString(3, 전화번호);
			pre.executeUpdate();		
			
			Idfield.setText(""); 
			Passwdfield.setText("");
			numberfield.setText("");
			
			pre.close();
		}catch(Exception e){ 
			e.printStackTrace();
			handleError(e.getMessage());
		}
	}
}