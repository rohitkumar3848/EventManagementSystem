
package eventmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class UserUI extends JFrame implements ActionListener{
    String user_id=null;
    UserUI(String user_id){
        this.user_id=user_id;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("electricity_icon/bg2.png"));
        Image i2 = i1.getImage().getScaledInstance(1500, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        JMenuBar mb = new JMenuBar();
        setJMenuBar (mb);
        
        JMenu vendor = new JMenu("VENDOR");
        vendor.setForeground(Color.RED);
        JMenuItem vendor1 = new JMenuItem("Vendor");
        vendor1.setFont(new Font("monospaced", Font.BOLD,14));
        vendor1.setBackground(Color.WHITE);
        vendor1.addActionListener(this);
        vendor.add(vendor1);
        

        
       
       
        
        JMenu order = new JMenu("ORDER STATUS");
        order.setForeground(Color.red);
        
        JMenuItem orderstatus = new JMenuItem("Order Status");
        orderstatus.setFont(new Font("monospaced", Font.BOLD,14));
        orderstatus.setBackground(Color.WHITE);
        orderstatus.addActionListener(this);
        order.add(orderstatus);
        
        
        JMenu mexit = new JMenu("LOGOUT");
        mexit.setForeground(Color.RED);
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(new Font("monospaced", Font.PLAIN,14));
        exit.setBackground(Color.WHITE);
        exit.addActionListener(this);
        mexit.add(exit);
        
            mb.add(vendor);
            
            mb.add(order);
        
        mb.add(mexit);
        
        
        
        setLayout(new FlowLayout());
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        String msg= ae.getActionCommand();
        if(msg.equals("Vendor")){
            new VendorList(user_id);
        }
        
        else if(msg.equals("Order Status")){
            new Cart(user_id).setVisible(true);
        } 
        
        else if(msg.equals("Exit")){
            setVisible(false);
            new EventManagementSystem().setVisible(true);
        }
    }
    public static void main(String [] args){
        
        new UserUI("").setVisible(true);
    }
    
}


