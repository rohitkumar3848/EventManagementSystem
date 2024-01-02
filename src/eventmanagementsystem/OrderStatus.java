
package eventmanagementsystem;

import static eventmanagementsystem.OrderRequest.product_id;
import static eventmanagementsystem.OrderRequest.user_id;
import static eventmanagementsystem.OrderRequest.vendor_id;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author rohit
 */



public class OrderStatus extends JFrame implements ActionListener{
    JButton home,logout;
    JPanel p;
    static String user_id=null;
    int x=160;
    int y=160;
    int z=160;
    int a=160;
    int b=160;
    int d=160;
    JTextField tname,taddress,temail,tpname,tstatus,tprice;
    OrderStatus(String user_id){
        super("Order Status");
        this.user_id=user_id;
        setSize(800,300);
        setLocation(380,280);
        setVisible(true);
        
         p =new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(234,182,118));
        add(p);
        
        home= new JButton("Home");
        home.setBounds(30,20,100,30);
        home.setBackground(Color.BLACK);
        home.setForeground(Color.WHITE);
        home.addActionListener(this);
        p.add(home);
        
        logout= new JButton("Logout");
        logout.setBounds(660,20,100,30);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        logout.addActionListener(this);
        p.add(logout);
        
        JLabel heading = new JLabel("USER ORDER STATUS");
        heading.setBounds(300,40,550,70);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        p.add(heading);
        
        JLabel lblname1 = new JLabel(" Name");
        lblname1.setBounds(40, 130, 80, 20);
        lblname1.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname1);
        

        
        JLabel lblname2 = new JLabel("Email");
        lblname2.setBounds(140, 130, 100, 20);
        lblname2.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname2);
        

        
        JLabel lblname3 = new JLabel("Address");
        lblname3.setBounds(260, 130, 100, 20);
        lblname3.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname3);
        
        JLabel lblname4 = new JLabel("Product Name");
        lblname4.setBounds(370, 130, 100, 20);
        lblname4.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname4);
        
        JLabel lblname5 = new JLabel("Status");
        lblname5.setBounds(490, 130, 100, 20);
        lblname5.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname5);
        
        JLabel lblname6 = new JLabel("Price");
        lblname6.setBounds(610, 130, 100, 20);
        lblname6.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname6);
        
        
        addData(user_id);
        
        setVisible(true);
    }
    public void addData(String user_i){
        try{
        Conn c=new Conn();
        String query="select * from orders where user_id='"+user_id+"'";
        ResultSet rs = c.s.executeQuery(query);
        //System.out.print("Hello");
        while(rs.next()){
            String order_id = rs.getString("order_id");
            String user_id = rs.getString("user_id");
            String product_id = rs.getString("product_id");
            String order_status = rs.getString("order_status");
            String product_name=rs.getString("product_name");
            String name = rs.getString("name");
            String address = rs.getString("address");
            String email=rs.getString("email");
            String vendor_id=rs.getString("vendor_id");
            String price=rs.getString("price");
            
            //Name Fiedl
            tname=new JTextField(name);
            tname.setBounds(40, x=x+30, 80, 20);
            tname.setFont(new Font("Tahoma",Font.PLAIN,16));
            p.add(tname);
            
            
            
            
            
            //Email Fiedl
            temail=new JTextField(email);
            temail.setBounds(140, z=z+30, 100, 20);
            temail.setFont(new Font("Tahoma",Font.PLAIN,16));
            p.add(temail);
            
            
            //Address Fiedl
            taddress=new JTextField(address);
            taddress.setBounds(260, y=y+30, 100, 20);
            taddress.setFont(new Font("Tahoma",Font.PLAIN,16));
            p.add(taddress);
            
            
            //Product Name Fiedl
            tpname=new JTextField(product_name);
            tpname.setBounds(370, a=a+30, 100, 20);
            tpname.setFont(new Font("Tahoma",Font.PLAIN,16));
            p.add(tpname);
            
            
            //Status Fiedl
            tstatus=new JTextField(order_status);
            tstatus.setBounds(490, b=b+30, 100, 20);
            tstatus.setFont(new Font("Tahoma",Font.PLAIN,16));
            p.add(tstatus);
            
            
            tprice=new JTextField(price);
            tprice.setBounds(610, d=d+30, 100, 20);
            tprice.setFont(new Font("Tahoma",Font.PLAIN,16));
            p.add(tprice);
            
            
            
        }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            //JOptionPane.showMessageDialog(this, "Error Order Status item", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==home){
            setVisible(false);
            new UserUI(user_id);
        }
        if(ae.getSource()==logout){
            setVisible(false);
            new EventManagementSystem();
        }
    }
    
    public static void main(String[]args){
        new OrderStatus(user_id);
    }
    
}

