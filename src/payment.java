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

public class payment extends JFrame implements ActionListener, Runnable{

	private long delay;
	
	String bookName=null, strid=null, strtel=null, realBook=null, realPay=null, total = null;
	JButton pay = new JButton();
	JTextField userName=new JTextField("");//아이디
	JTextField userNum=new JTextField("");//번호
	JTextField totalPrice = new JTextField("");//총 금액
	JTextField subject = new JTextField("");//과목 
	JButton BookShop = new JButton();
	ImageIcon exitIcon = new ImageIcon("images/exit.jpg");
	JButton exit = new JButton(exitIcon);
	JButton devbtn = new JButton(" ");//개발과
	JButton designbtn = new JButton(" ");//디자인과
	JButton basebtn = new JButton(" ");//인문과 
		 
	private PreparedStatement pstmt = null;
	private Connection conn = null;
	private ResultSet view = null; 
	
	//Thread
		private Thread th;
	    private JLabel label;
	    private SimpleDateFormat simple;
	
	public payment(String textbookName,String strid, String strtel) { //교과서 창에서 가져온 책 이름
		
		
		bookName = textbookName; //교과서 창에서 가져온 책 이름
		this.strid=strid;
		this.strtel=strtel;
		
		try { 
			Class.forName("org.gjt.mm.mysql.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","mirim2");//jdbc에 연결하귀~~~~~~~~
		}catch(ClassNotFoundException e) {
			handleError(e.getMessage());
		}catch (SQLException e) {
			handleError(e.getMessage());
		}
		
		setTitle("PeachMelon 회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout()); 
		c.setLayout(null);//배치관리자를  null로 고정시킴
		//배경 이미지
		ImageIcon display_pay = new ImageIcon("images/pay.jpg");  
		Image display_pay2= display_pay.getImage();
				
		JPanel image = new JPanel() {//배경 넣기 	
			public void paintComponent(Graphics g) {
				g.drawImage(display_pay2,0,0,1781,950,this);
				setOpaque(false); 
				super.paintComponent(g);
			}  
		};
		 
		image.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(image);
		setContentPane(scrollPane);
		
		setUndecorated(true);//메뉴바 안보이게 하는 중
		
		ShowAll();// id, tel, 총 금액, 과목 뜨게 하기
		
		image.add(exit);
		exit.setBounds(1736, 0, 50,50);
		exit.setContentAreaFilled(false); 
		exit.setFocusPainted(false);
		exit.setBorderPainted(false);
		exit.addActionListener(this);
		
		/*Thread*/
		label = new JLabel();
        label.setFont(new Font("배달의민족 도현", Font.PLAIN, 15));
        
        simple = new SimpleDateFormat("yyyy년MM월dd일 a hh:mm:ss");
        if (th == null) {
            th = new Thread(this);
            th.start(); 
        }
        image.add(label);
        label.setBounds(1400,130,500,80);		
		/*Thread*/
		
		image.add(pay);
		image.add(userName);
		image.add(userNum);
		image.add(totalPrice);
		image.add(subject);
		
		image.add(BookShop);
		BookShop.addActionListener(this);
		BookShop.setBounds(450,20,1000,200);
		BookShop.setContentAreaFilled(false);BookShop.setFocusPainted(false);/*버튼의 테두리 없앰*/BookShop.setBorderPainted(false);
		BookShop.addActionListener(this);
		
		pay.setBounds(1233,520,210,210);
		userName.setBounds(579,450,440,70); 
		userName.setFont(new Font("배달의민족 도현", Font.CENTER_BASELINE, 25));
		userNum.setBounds(579,530,440,70);
		userNum.setFont(new Font("배달의민족 도현", Font.CENTER_BASELINE, 25));
		totalPrice.setBounds(579,707,440,70);
		totalPrice.setFont(new Font("배달의민족 도현", Font.CENTER_BASELINE, 25));
		subject.setBounds(579,787,440,70);
		subject.setFont(new Font("배달의민족 도현", Font.CENTER_BASELINE, 25));
		
		//버튼의 내용을 투명하게 해준다
		pay.setContentAreaFilled(false); 
		pay.setFocusPainted(false);//버튼의 테두리 없앰 
		pay.setBorderPainted(false);
		pay.addActionListener(this);  

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
		
		//버튼의 테두리를 투명하게 해준다
		devbtn.setBorderPainted(false);
		designbtn.setBorderPainted(false);
		basebtn.setBorderPainted(false);
		devbtn.addActionListener(this);
		designbtn.addActionListener(this);
		basebtn.addActionListener(this);
		
		setBounds(80,20,1785,950);
		setVisible(true);
	}
	 public void run() {
	        while (true) {
	           label.setText(simple.format(new Date())); 
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e){}
	        }
	    }
	public void ShowAll(){//id, 번호, 총금액, 과목 이름 보여야 함 
		  
		if((bookName.equals("JAVA"))||(bookName.equals("정보처리와관리"))||(bookName.equals("C++"))||(bookName.equals("Python"))||(bookName.equals("C"))||(bookName.equals("시각디자인"))||(bookName.equals("UIUX엔지니어링"))||(bookName.equals("뉴미디어콘텐츠제작"))||(bookName.equals("서비스경험디자인"))) total="12000";
		else if((bookName.equals("뉴미디어디자인기초"))||(bookName.contentEquals("디자인일반"))||(bookName.equals("웹콘텐츠제작일반"))||(bookName.contentEquals("광고콘텐츠제작일반"))||(bookName.contentEquals("컴퓨터구조"))||(bookName.equals("뉴미디어전송"))||(bookName.contentEquals("정보보호"))) total="10500";
		else if(bookName.equals("수학") || (bookName.equals("수학1"))||(bookName.equals("수학2"))||(bookName.equals("한국사"))||(bookName.equals("통합과학"))||(bookName.equals("일본어"))||(bookName.equals("영어"))||(bookName.equals("자료구조"))) total ="8900";
		else if((bookName.equals("영어권문화"))||(bookName.equals("진로영어"))||(bookName.equals("체육"))||(bookName.equals("기술가정"))||(bookName.equals("국어"))||(bookName.equals("웹사이트제작일반"))||(bookName.equals("뉴미디어콘텐츠"))||(bookName.equals("웹프로그래밍"))||(bookName.equals("데이터베이스"))||(bookName.equals("서버구축및운영"))) total = "11800";
		
		totalPrice.setText(total);
		userName.setText(strid);
		userNum.setText(strtel);
		realPay=totalPrice.getText();
		System.out.println("totalPrice : "+ realPay);
		subject.setText(bookName); realBook=subject.getText();
		
	}
	private void handleError(String message) {
		// TODO Auto-generated method stub
		System.out.println("문제 : "+ message);
		System.exit(1);
	} 
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==pay) { 
			if(strid==null || strtel == null) {
				JOptionPane.showConfirmDialog(null, "회원님의 정보가 옳바르지 않습니다. \n 다시 확인해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
			}else{
				if(realBook==null || realPay==null) {realBook="null"; realPay="null";}
				int result=JOptionPane.showConfirmDialog(null, strid+"님! 구매하실 책은 " +realBook+"\n가격은 "+realPay+"원 입니다. \n 맞으면 확인을 눌러주세요.", "결제확인", JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION) {  
					JOptionPane.showMessageDialog(null, "구매되었습니다.\n 돈을 들고 유병석 선생님께 가세요.", "결제확인", JOptionPane.DEFAULT_OPTION);
					new Sign_in(strid, strtel);
					dispose();
				}else { 
						JOptionPane.showMessageDialog(null, "다시 해주십시요.", "결제확인", JOptionPane.ERROR_MESSAGE);
								new payment(bookName,strid,strtel); dispose();
								
				}
			} 
		}
		if(e.getSource()==exit) {
			System.exit(0);
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
		if(e.getSource()==BookShop) {
			if(strid==null) {
				new Home(); 
				dispose();
			}
			else {
				new Sign_in(strid, strtel);
			dispose();
			}
		}
		
	}
}
