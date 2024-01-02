
package eventmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class VendorUI extends JFrame implements ActionListener{
    
    static String vendorId=null;
    
    VendorUI(String vendorId){
        this.vendorId=vendorId;
                    //System.out.println(vendorId);

        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("electricity_icon/eve.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        JMenuBar mb = new JMenuBar();
        setJMenuBar (mb);
        


        JMenu info = new JMenu("YOUR ITEMS");
        info.setForeground(Color.RED);
        
        JMenuItem insert = new JMenuItem("InsertDelete");
        insert.setFont(new Font("monospaced", Font.BOLD,14));
        insert.setBackground(Color.WHITE);
        insert.addActionListener(this);
        info.add(insert);
        
        JMenu user = new JMenu("ORDER STATUS");
        user.setForeground(Color.red);
        
        JMenuItem productstatus = new JMenuItem("Product Status");
        productstatus.setFont(new Font("monospaced", Font.BOLD,14));
        productstatus.setBackground(Color.WHITE);
        productstatus.addActionListener(this);
        user.add(productstatus);
        
       
        
        JMenu mexit = new JMenu("LOGOUT");
        mexit.setForeground(Color.RED);
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(new Font("monospaced", Font.BOLD,14));
        exit.setBackground(Color.WHITE);
        exit.addActionListener(this);
        mexit.add(exit);
        
//        if(atype.equals("Vendor")){
//            
//        }
//        } else{
            //mb.add(master);
            mb.add(info);
            mb.add(user);
//            mb.add(report);
//        }
        
        //mb.add(utilities);
        mb.add(mexit);
        
        
        
        setLayout(new FlowLayout());
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        String msg= ae.getActionCommand();
        if(msg.equals("InsertDelete")){
            new Vendor(vendorId).setVisible(true);
        }else if(msg.equals("Exit")){
            setVisible(false);
            new EventManagementSystem().setVisible(true);
        }
        else if(msg.equals("Product Status")){
            new Status(vendorId).setVisible(true);
        }
    }
    public static void main(String [] args){
        
        new VendorUI(vendorId).setVisible(true);
    }
    
}

