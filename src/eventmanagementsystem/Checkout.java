
package eventmanagementsystem;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Checkout extends JFrame implements ActionListener{
    
    JTextField gtotal,name,phone,email,city,state,pin,address;
    Choice payment;
    JButton order;
   
    Checkout(){
        
        super("CheckOut Details");
        
        setSize(700,560);
        setLocation(400,180);
        setVisible(true);
        
        JPanel p = new JPanel();
        p.setBackground(new Color(234,182,118));
        p.setLayout(null);
        add(p);        
        
        JLabel lblname1 = new JLabel("Grand Total");
        lblname1.setBounds(220, 30, 400, 20);
        lblname1.setFont(new Font("Tahoma",Font.BOLD,18));
        p.add(lblname1);
        
        gtotal= new JTextField();
        gtotal.setBounds(350,30,120,20);
        p.add(gtotal);
        
        JLabel lblname2 = new JLabel("Details");
        lblname2.setBounds(300, 90, 400, 20);
        lblname2.setFont(new Font("Tahoma",Font.PLAIN,20));
        p.add(lblname2);
        
        JLabel lblname3 = new JLabel("Name");
        lblname3.setBounds(40, 160, 400, 20);
        lblname3.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname3);
        
        name= new JTextField();
        name.setBounds(120,160,160,20);
        p.add(name);
        
        JLabel lblname4 = new JLabel("Phone Number");
        lblname4.setBounds(360, 160, 400, 20);
        lblname4.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname4);
        
        phone= new JTextField();
        phone.setBounds(490,160,160,20);
        p.add(phone);
//        
        JLabel lblname5 = new JLabel("Email");
        lblname5.setBounds(40, 230, 400, 20);
        lblname5.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname5);
        
        email= new JTextField();
        email.setBounds(120,230,160,20);
        p.add(email);
//        
        JLabel lblname6 = new JLabel("Address");
        lblname6.setBounds(360, 230, 400, 20);
        lblname6.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname6);
        
        address= new JTextField();
        address.setBounds(490,230,160,20);
        p.add(address);
//        
        JLabel lblname7 = new JLabel("City");
        lblname7.setBounds(40, 300, 400, 20);
        lblname7.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname7);
        
        city= new JTextField();
        city.setBounds(120,300,160,20);
        p.add(city);
//        
        JLabel lblname8 = new JLabel("State");
        lblname8.setBounds(360, 300, 400, 20);
        lblname8.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname8);
        
        state= new JTextField();
        state.setBounds(490,300,160,20);
        p.add(state);
//        
        JLabel lblname9 = new JLabel("Pin Code");
        lblname9.setBounds(40, 370, 400, 20);
        lblname9.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname9);
        
        pin= new JTextField();
        pin.setBounds(120,370,160,20);
        p.add(pin);
//        
        JLabel lblname10 = new JLabel("Payment");
        lblname10.setBounds(360, 370, 100, 20);
        lblname10.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname10);
        
        payment= new Choice();
        payment.add("BHIM UPI");
        payment.add("CASH ON DELIVERY");
        payment.setBounds(490,370,160,20);
        p.add(payment);
        
        order= new JButton("ORDER");
        order.setBounds(250,440,200,35);
        order.setBackground(Color.BLACK);
        order.setForeground(Color.WHITE);
        order.addActionListener(this);
        p.add(order);
        
        
        
       
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.equals(ae)== payment.equals("CASH ON DELIVERY")){
            if(ae.getSource()==order){
                setVisible(false);
                new Success().setVisible(true);
            }
        }else if(payment.equals("BHIM UPI")){
            if(ae.getSource()==payment){
                setVisible(false);
            }
        }
        
    }
    
    public static void main(String [] args){
        new Checkout();
    }
    
}
