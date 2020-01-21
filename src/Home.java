import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
public class Home extends JFrame implements ActionListener, Runnable {
	
	JLabel idtext = new JLabel("아이디 ");
	JLabel pwtext = new JLabel("비밀번호 ");
	JTextField id=new JTextField(""); //ID입력란
	JPasswordField pw=new JPasswordField(""); //PW입력란
	
	JButton findID=new JButton("ID찾기"); //아이디 찾기
	JButton findPW=new JButton("PW 찾기"); //비밀번호 찾기
	JButton Login=new JButton("로그인"); //로그인 
	JButton AddInfor=new JButton("회원가입"); //회원가입
	JButton devbtn = new JButton(" ");//개발과
	JButton designbtn = new JButton(" ");//디자인과
	JButton basebtn = new JButton(" ");//인문과 
	ImageIcon exitIcon = new ImageIcon("images/exit.jpg");
	JButton exit = new JButton(exitIcon);
	
	//Thread
	private Thread thread;
    private JLabel label;
    private SimpleDateFormat sf;
    
	JPanel image;
	String strid, strtel;
	private PreparedStatement pstmt = null;
	private Connection conn = null;
	private ResultSet view = null;

	
	public Home(){
		
		try { 
			Class.forName("org.gjt.mm.mysql.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","mirim2");//jdbc에 연결하귀~~~~~~~~
			//pstmt = conn.prepareStatement(sql); 
		}catch(ClassNotFoundException e) {
			handleError(e.getMessage());
		}catch (SQLException e) {
			handleError(e.getMessage());
		}	 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 
		Container c = getContentPane();
		
		   
		ImageIcon display = new ImageIcon("images/alldisplay.jpg"); //배경 이미지
		Image display_1= display.getImage();
	
		image = new JPanel() {//배경 넣기 
			 
			public void paintComponent(Graphics g) {
				g.drawImage(display_1,0,0,1781,950,this);
				setOpaque(false);
				super.paintComponent(g);
			} 
		};
		image.setLayout(null);
		
	
		
		JScrollPane scrollPane=new JScrollPane(image);
		setContentPane(scrollPane);
		
		setUndecorated(true);//메뉴바 안보이게 하는 중
		
		//고 이미지
		ImageIcon beauty = new ImageIcon("images/RR1.gif"); 
		JLabel imageLabel = new JLabel(beauty);
		image.add(imageLabel);
		imageLabel.setBounds(150,500,734,340);
		
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
        
		//JLabel
		image.add(idtext);
		image.add(pwtext);
		
		idtext.setBounds(1050,330,80,45);
		pwtext.setBounds(1033,400,100,45);
		idtext.setFont(new Font("배달의민족 도현",Font.CENTER_BASELINE, 20));
		pwtext.setFont(new Font("배달의민족 도현",Font.CENTER_BASELINE, 20));
		//TextField
		image.add(id);
		image.add(pw);
		
		id.setBounds(1127,327,208,52);  // x, y, 가로, 세로
		pw.setBounds(1127,390,208,52);
		id.setFont(new Font("배달의민족 도현",Font.CENTER_BASELINE, 18));
		
		//Button
		image.add(findID);
		image.add(findPW);
		image.add(Login);
		image.add(AddInfor);
		image.add(devbtn);
		image.add(designbtn);
		image.add(basebtn);
	
		
		findID.setBounds(1360,329,120,45);  // x, y, 가로, 세로
		findPW.setBounds(1527,329,120,45);
		Login.setBounds(1360,390,120,45);
		AddInfor.setBounds(1527,390,120,45);
		devbtn.setBounds(673,250,140,45);
	    designbtn.setBounds(890,250,140,45);
		basebtn.setBounds(1137,250,140,45);
		
		findID.setFont(new Font("배달의민족 도현",Font.CENTER_BASELINE, 20));
		findPW.setFont(new Font("배달의민족 도현", Font.CENTER_BASELINE, 20));
		Login.setFont(new Font("배달의민족 도현", Font.CENTER_BASELINE, 20)); 
		AddInfor.setFont(new Font("배달의민족 도현", Font.CENTER_BASELINE, 20));
		
		//버튼을 투명하게 바꿔준다
		findID.setContentAreaFilled(false); 
		findPW.setContentAreaFilled(false);
		Login.setContentAreaFilled(false);
		AddInfor.setContentAreaFilled(false);
		devbtn.setContentAreaFilled(false);
		designbtn.setContentAreaFilled(false);
		basebtn.setContentAreaFilled(false);
		 
		//버튼선택후에 생기는 표시 없앰
		findID.setFocusPainted(false);
		findPW.setFocusPainted(false);
		Login.setFocusPainted(false);
		AddInfor.setFocusPainted(false);
		devbtn.setFocusPainted(false);
		designbtn.setFocusPainted(false);
		basebtn.setFocusPainted(false);
		
		//버튼의 테두리를 투명하게 해준다
		devbtn.setBorderPainted(false);
		designbtn.setBorderPainted(false);
		basebtn.setBorderPainted(false);
		
		//창 이동
		findID.addActionListener(this);
		findPW.addActionListener(this);
		Login.addActionListener(this);
		AddInfor.addActionListener(this);
		devbtn.addActionListener(this);
		designbtn.addActionListener(this);
		basebtn.addActionListener(this);
		
		
		setBounds(80,20,1785,950);
		setVisible(true);
	}
	private void handleError(String message) {
		// TODO Auto-generated method stub
		System.out.println("문제 : "+ message); 
		System.exit(1);
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
		// TODO Auto-generated method stub
		if(e.getSource()==findID) {
			new Find_id(); dispose();
		}
		if(e.getSource()==findPW) {
			new Find_pwd(); dispose();
		}
		if(e.getSource()==Login) {
			String idtext = id.getText();
			String pwtext = pw.getText(); 
			CheckIdPw(idtext, pwtext);
		} 
		if(e.getSource()==AddInfor) {
			new SignUp(); dispose(); 
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
	

	private void CheckIdPw(String idtext, String pwtext) {
		try{
			System.out.println("id : |"+idtext+", pwd : |"+pwtext+"|");
			String sql = "select id, pw, tel from info where id = ? and pw = ?";
		
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, idtext);
			pstmt.setString(2, pwtext);
			view=pstmt.executeQuery(); 
		
		
			if(view.next()) { 
				strid = view.getString("id");
				strtel=view.getString("tel");
				
				System.out.println("strId : "+strid);
				System.out.println("strPw : "+strtel);
				new Sign_in(strid, strtel); dispose(); 
				
			}else if(!view.next()){
				JOptionPane.showMessageDialog(null,"아이디 혹은 비밀번호가 틀렸습니다.\n 다시 입력해주세요.", "아이디/비밀번호 확인", JOptionPane.ERROR_MESSAGE);
				id.setText("");
				pw.setText(""); 
			}	
		}catch (SQLException e) {
			handleError(e.getMessage());
		}
	}
}