
package eventmanagementsystem;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;


public class UserRequest extends JFrame{
    JButton home,logout;
    
    UserRequest(){
        
        super("User Reqest");
        
        setSize(700,300);
        setLocation(420,270);
        setVisible(true);
        
        JPanel p = new JPanel();
        p.setBackground(new Color(234,182,118));
        p.setLayout(null);
        add(p); 
        
        JLabel lblname1 = new JLabel("REQUEST ITEM");
        lblname1.setBounds(265, 30, 400, 40);
        lblname1.setFont(new Font("Tahoma",Font.BOLD,20));
        p.add(lblname1);
        
        home= new JButton("Home");
        home.setBounds(30,20,100,30);
        home.setBackground(Color.BLACK);
        home.setForeground(Color.WHITE);
//        home.addActionListener(this);
        p.add(home);
        
        logout= new JButton("Logout");
        logout.setBounds(550,20,100,30);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
//        logout.addActionListener(this);
        p.add(logout);
        
        JLabel lblname2 = new JLabel("Item 1");
        lblname2.setBounds(30, 110, 400, 20);
        lblname2.setFont(new Font("Tahoma",Font.PLAIN,18));
        p.add(lblname2);
        
        JLabel item1 = new JLabel("");
        item1.setBounds(30,170,100,20);
        p.add(item1);
        
        JLabel lblname3 = new JLabel("Item 2");
        lblname3.setBounds(190, 110, 400, 20);
        lblname3.setFont(new Font("Tahoma",Font.PLAIN,18));
        p.add(lblname3);
        
        JLabel item2 = new JLabel("");
        item2.setBounds(190,170,100,20);
        p.add(item2);
        
        JLabel lblname4 = new JLabel("Item 3");
        lblname4.setBounds(370, 110, 400, 20);
        lblname4.setFont(new Font("Tahoma",Font.PLAIN,18));
        p.add(lblname4);
        
        JLabel item3 = new JLabel("");
        item3.setBounds(370,170,100,20);
        p.add(item3);
        
        JLabel lblname5 = new JLabel("Item 4");
        lblname5.setBounds(540, 110, 400, 20);
        lblname5.setFont(new Font("Tahoma",Font.PLAIN,18));
        p.add(lblname5);
        
        JLabel item4 = new JLabel("");
        item4.setBounds(540,170,100,20);
        p.add(item4);
        
    }
    
    public static void main(String[] args){
        new UserRequest();
    }
    
}
