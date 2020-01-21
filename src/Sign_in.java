import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sign_in extends JFrame implements ActionListener, Runnable{
	
	JButton devbtn, designbtn,basebtn;
	ImageIcon exitIcon = new ImageIcon("images/exit.jpg");
	JButton BookShop = new JButton();
	JButton exit = new JButton(exitIcon);
	String strid, strtel;
	
	//Thread
		private Thread thread;
	    private JLabel label;
	    private SimpleDateFormat sf;
	
	Sign_in(String strid, String strtel){ 
		
		this.strid=strid;
		this.strtel=strtel;
		
		setTitle("book shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout()); 
		c.setLayout(null); 
		 
		ImageIcon login = new ImageIcon("images/alldisplay.jpg"); //배경 이미지
		Image login1= login.getImage();
		   
		JPanel image = new JPanel() {//배경 넣기 
			 
			public void paintComponent(Graphics g) {
				g.drawImage(login1,0,0,1781,950,this);
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
		
		image.add(BookShop);
		BookShop.setBounds(450,20,1000,200);
		BookShop.setContentAreaFilled(false);BookShop.setFocusPainted(false);/*버튼의 테두리 없앰*/BookShop.setBorderPainted(false);
		
		devbtn = new JButton();
		designbtn = new JButton();
		basebtn = new JButton();
		
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
		if(e.getSource()==exit) {
			System.exit(0);
		}  
		if(e.getSource()==BookShop) {
			new Sign_in(strid, strtel); dispose();
		}
		if(e.getSource()==devbtn) {
			new Devdisplay(strid,strtel); dispose();
		}
		else if(e.getSource()==designbtn) {
			new Designdisplay(strid,strtel); dispose();
		}
		else if(e.getSource()==basebtn) {
			new BasicSubject1(strid,strtel);dispose();	
		}
	}
	
}
