import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

class Find_id extends JFrame implements ActionListener, Runnable {

	JLabel findphone;
	JTextField phonefield; 
	JButton phonebtn;
	JButton devbtn = new JButton(" ");//개발과
	JButton designbtn = new JButton(" ");//디자인과
	JButton basebtn = new JButton(" ");//인문과 
	JButton BookShop = new JButton();
	
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
	
	Find_id(){ 
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","mirim2");//jdbc에 연결하귀~~~~~~~~
			//pstmt = conn.prepareStatement(sql); 
		}catch(ClassNotFoundException e) {
			handleError(e.getMessage());
		}catch (SQLException e) {
			handleError(e.getMessage());
		}	
		 
		 
		setTitle ("Book Shop 아이디 찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout()); 
		c.setLayout(null);//배치관리자를  null로 고정시킴
		  
		findphone = new JLabel("전화번호 : "); 
		phonefield = new JTextField("");
		phonebtn = new JButton("확인"); 
		
		//배경 이미지
		ImageIcon display_id = new ImageIcon("images/signUp.jpg");  
		Image display_id2= display_id.getImage();
		 
		JPanel image = new JPanel() {//배경 넣기 
			public void paintComponent(Graphics g) {
				g.drawImage(display_id2,0,0,1781,950,this);
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
		
		image.add(findphone); 
		image.add(phonefield);
		image.add(phonebtn); 
		
		image.add(BookShop); 
		BookShop.setBounds(450,50,1000,180);
		BookShop.setContentAreaFilled(false);
		BookShop.setFocusPainted(false);//버튼의 테두리 없앰
		BookShop.setBorderPainted(false);	
		BookShop.addActionListener(this);
		 
		findphone.setFont(new Font("메이플스토리 OTF", Font.ITALIC, 35));
		phonefield.setFont(new Font("맑은고딕", Font.ITALIC, 20));
		phonebtn.setFont(new Font("메이플스토리 OTF", Font.ITALIC,20));
		 
		findphone.setBounds(540,400,200,200);
		phonefield.setBounds(700, 470, 400, 50);
		phonebtn.setBounds(540,550,560,50);
		
		//버튼의 내용을 투명하게 해준다  
		phonebtn.setContentAreaFilled(false);
		
		phonebtn.addActionListener(this);  
		
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
		if(e.getSource()==phonebtn) {
			String str = phonefield.getText();
			FindshowID(str);
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

	
	private void FindshowID(String str) {
		// TODO Auto-generated method stub
		try {  
			System.out.println("str :|"+ str+"|");
			String sql="select id from info where tel = ?";			
			pstmt=conn.prepareStatement(sql);
			//System.out.println("step1");
			pstmt.setString(1,str);
			//System.out.println("step2");
			view = pstmt.executeQuery();
			//System.out.println("step3");
            		 
			if(view.next()) {
				String check = view.getString("id");
				System.out.println("check : "+ check);
				JOptionPane.showMessageDialog(null, "회원님의 아이디는 "+ check+"입니다.","id 확인", 
						JOptionPane.DEFAULT_OPTION); 
				}
				else {   
				JOptionPane.showMessageDialog(null, "\t			없는 전화번호 입니다.\n "
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
