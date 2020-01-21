import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class Devdisplay extends JFrame implements ActionListener, Runnable{
	JButton devbtn, designbtn,basebtn;
	
	private JLabel write = new JLabel("※띄어쓰기 하지 마세요.※");
	private JButton next = new JButton();
	private JButton buy = new JButton();
	private JButton BookShop = new JButton();  
	String strid, strtel;
	ImageIcon exitIcon = new ImageIcon("images/exit.jpg");
	JButton exit = new JButton(exitIcon);
	JTextField buynow=new JTextField("");
	
	//Thread 
	private Thread thread;
    private JLabel label;
    private SimpleDateFormat sf;
	
	public Devdisplay(String strid, String strtel) {
		this.strid=strid;
		this.strtel=strtel;
		
		setTitle("Book shop_개발과목"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout()); 
		 
		devbtn = new JButton(" ");//개발과
		designbtn = new JButton(" ");//디자인과
		basebtn = new JButton(" ");//인문과 
		
		ImageIcon display = new ImageIcon("images/DEVimage1.jpg"); //배경 이미지
		Image display_1= display.getImage();
		 
		JPanel image = new JPanel() {//배경 넣기 
			
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
		
		image.add(write);
		write.setBounds(195,42,400,400);
		write.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 14));
		write.setForeground(Color.pink);
		
		image.add(devbtn);
		image.add(designbtn);
		image.add(basebtn);
		
		//다시 로그인 화면으로 가게 하기
		image.add(BookShop);
		BookShop.setBounds(450,20,1000,200);
		BookShop.setContentAreaFilled(false);BookShop.setFocusPainted(false);/*버튼의 테두리 없앰*/BookShop.setBorderPainted(false);
		BookShop.addActionListener(this);
		
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
		
		image.add(next);
		next.setBounds(1670,870,86,50);
		next.setContentAreaFilled(false); 
		next.setFocusPainted(false);
		next.setBorderPainted(false);
		next.addActionListener(this);
		
		image.add(buynow);
		buynow.setBounds(133,250,280,50);
		buynow.setFont(new Font("배달의민족 도현", Font.CENTER_BASELINE, 26));
		
		
		//여기서부터 이미지 클릭 문제
		buy=new JButton();
		image.add(buy);
		buy.addActionListener(this);
		buy.setBounds(475,250,65,50);
		buy.setContentAreaFilled(false);
		
		
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
			if(strid==null) {
				new Home(); 
				dispose();
			}
			else {
				new Sign_in(strid, strtel);
			dispose();
			}
		}
		if(e.getSource()==devbtn) {
			new Devdisplay(strid, strtel); dispose();
		}
		else if(e.getSource()==designbtn) {
			new Designdisplay(strid,strtel); dispose();
		}
		else if(e.getSource()==basebtn) {
			new BasicSubject1(strid,strtel); dispose();
		}
		else if(e.getSource()==next) {
			new Devdisplay2(strid, strtel); dispose();
		}
		else if(e.getSource()==buy) {
			String buynowfield = buynow.getText();
			new payment(buynowfield,strid,strtel);
			dispose();
		}
		if(e.getSource()==exit) {
			System.exit(0);
		}
	}

}
class Devdisplay2 extends JFrame implements ActionListener, Runnable{
	JButton devbtn, designbtn,basebtn;
	
	private JLabel write = new JLabel("※띄어쓰기 하지 마세요.※");
	private JButton next2 = new JButton();
	private JButton before = new JButton();
	private JButton buy;
	private JTextField buynow=new JTextField("");
	private JButton BookShop=new JButton(); 
	ImageIcon exitIcon = new ImageIcon("images/exit.jpg");
	JButton exit = new JButton(exitIcon);
	String strid, strtel;
	
	//Thread
	private Thread thread;
    private JLabel label;
    private SimpleDateFormat sf;
	
	public Devdisplay2(String strid,String strtel) {
		this.strid=strid;
		this.strtel=strtel;
		
		setTitle("Book shop_개발과목");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout()); 
		
		devbtn = new JButton(" ");//개발과
		designbtn = new JButton(" ");//디자인과
		basebtn = new JButton(" ");//인문과 
		
		ImageIcon display = new ImageIcon("images/devimage2.jpg"); //배경 이미지
		Image display_1= display.getImage();
		 
		JPanel image = new JPanel() {//배경 넣기 
			 
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
		
		image.add(write);
		write.setBounds(195,35,400,400);
		write.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 14));
		write.setForeground(Color.pink);
		
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
		
		image.add(next2);
		next2.setBounds(1670,870,86,50);
		next2.setContentAreaFilled(false); 
		next2.setFocusPainted(false);
		next2.setBorderPainted(false);
		next2.addActionListener(this);
		
		image.add(before);
		before.setBounds(28,870,86,50);
		before.setContentAreaFilled(false); 
		before.setFocusPainted(false);
		before.setBorderPainted(false);
		before.addActionListener(this);
		
		image.add(BookShop);
		BookShop.setBounds(450,20,1000,200);
		BookShop.setContentAreaFilled(false);BookShop.setFocusPainted(false);/*버튼의 테두리 없앰*/BookShop.setBorderPainted(false);
		BookShop.addActionListener(this);
		
		image.add(buynow);
		buynow.setBounds(133,250,280,50);
		buynow.setFont(new Font("배달의민족 도현", Font.CENTER_BASELINE, 26));
		
		
		//여기서부터 이미지 클릭 문제
		buy=new JButton();
		image.add(buy);
		buy.addActionListener(this);
		buy.setBounds(475,250,65,50); 
		buy.setContentAreaFilled(false);
		
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
			if(strid==null) {
				new Home(); 
				dispose();
			}
			else {
				new Sign_in(strid, strtel);
			dispose();
			}
		}
		if(e.getSource()==devbtn) {
			new Devdisplay(strid,strtel); dispose();
		}
		else if(e.getSource()==designbtn) {
			new Designdisplay(strid,strtel); dispose();
		}
		else if(e.getSource()==basebtn) {
			new BasicSubject1(strid,strtel); dispose();
		}
		else if(e.getSource()==next2) {
			new Devdisplay3(strid,strtel); dispose();
		}
		else if(e.getSource()==before) {
			new Devdisplay(strid,strtel); dispose();
		}
		else if(e.getSource()==buy) {
			String buynowfield = buynow.getText();
			new payment(buynowfield,strid,strtel);
			dispose();
		}
		if(e.getSource()==exit) {
			System.exit(0);
		}
	} 
}
class Devdisplay3 extends JFrame implements ActionListener, Runnable{
	JButton devbtn, designbtn,basebtn;

	private JLabel write = new JLabel("※띄어쓰기 하지 마세요.※");
	private JButton before2 = new JButton();
	private JButton buy;
	private JTextField buynow=new JTextField("");
	private JButton BookShop = new JButton();
	ImageIcon exitIcon = new ImageIcon("images/exit.jpg");
	JButton exit = new JButton(exitIcon);
	String strid, strtel;
	
	//Thread
	private Thread thread;
    private JLabel label;
    private SimpleDateFormat sf;

	public Devdisplay3(String strid, String strtel) {
		this.strid=strid;
		this.strtel=strtel;
		setTitle("Book shop_개발과목");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout()); 
		
		devbtn = new JButton(" ");//개발과
		designbtn = new JButton(" ");//디자인과
		basebtn = new JButton(" ");//인문과 
		
		ImageIcon display = new ImageIcon("images/devimage3.jpg"); //배경 이미지
		Image display_1= display.getImage();
		 
		JPanel image = new JPanel() {//배경 넣기 
			 
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
		
		image.add(write);
		write.setBounds(195,35,400,400);
		write.setFont(new Font("맑은 고딕", Font.CENTER_BASELINE, 14));
		write.setForeground(Color.pink);
		
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
		
		image.add(before2);
		before2.setBounds(28,870,86,50);
		before2.setContentAreaFilled(false); 
		before2.setFocusPainted(false);
		before2.setBorderPainted(false);
		before2.addActionListener(this);
		
		image.add(BookShop);
		BookShop.setBounds(450,20,1000,200);
		BookShop.setContentAreaFilled(false);BookShop.setFocusPainted(false);/*버튼의 테두리 없앰*/BookShop.setBorderPainted(false);
		BookShop.addActionListener(this);
		
		image.add(buynow);
		buynow.setBounds(133,250,280,50);
		buynow.setFont(new Font("배달의민족 도현", Font.CENTER_BASELINE, 26));
		
		
		//여기서부터 이미지 클릭 문제
		buy=new JButton();
		image.add(buy);
		buy.addActionListener(this);
		buy.setBounds(475,250,65,50);
		buy.setContentAreaFilled(false);
		
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
			if(strid==null) {
				new Home(); 
				dispose();
			}
			else {
				new Sign_in(strid, strtel);
			dispose();
			}
		}
		if(e.getSource()==devbtn) {
			new Devdisplay(strid,strtel); dispose();
		}
		else if(e.getSource()==designbtn) {
			new Designdisplay(strid,strtel); dispose();
		}
		else if(e.getSource()==basebtn) {
			new BasicSubject1(strid,strtel); dispose();
		}
		else if(e.getSource()==before2) {
			new Devdisplay2(strid,strtel); dispose();
		}
		else if(e.getSource()==buy) {
			String buynowfield = buynow.getText();
			new payment(buynowfield,strid,strtel);
			dispose();
		}
		if(e.getSource()==exit) {
			System.exit(0);
		}
	}
}
