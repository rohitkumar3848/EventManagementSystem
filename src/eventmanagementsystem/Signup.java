
package eventmanagementsystem;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
public class Signup extends JFrame implements ActionListener{
    JButton create,back;
    Choice accountType;
    JTextField meter, username,name,password;
    Random rd=new Random();
    Signup(){
        setSize(700,400);
        setLocation(450,150);
        setVisible(true);
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JPanel panel =new JPanel();
        panel.setBounds(30,30,650,300);
        panel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2), "Create-Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,0,0)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34,139,34));
        add(panel);
        
        JLabel heading = new JLabel("Create Account As");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.BLACK);
        heading.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(heading);
       
        accountType= new Choice();
        accountType.add("User");
        accountType.add("Vendor");
        accountType.setBounds(260,50,150,20);
        panel.add(accountType);
       
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100,90,140,20);
        lblmeter.setForeground(Color.BLACK);
        lblmeter.setFont(new Font("Tahoma", Font.BOLD,14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);
        
        meter= new JTextField();
        meter.setBounds(260,90,150,20);
        meter.setVisible(false);
        panel.add(meter);
        
        
       
        JLabel lblusername = new JLabel("User Name");
        lblusername.setBounds(100,130,140,20);
        lblusername.setForeground(Color.BLACK);
        lblusername.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(lblusername);
       
        username= new JTextField();
        username.setBounds(260,130,150,20);
        panel.add(username);
       
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100,170,140,20);
        lblname.setForeground(Color.BLACK);
        lblname.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(lblname);
       
        name= new JTextField();
        name.setBounds(260,170,150,20);
        panel.add(name);
        
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100,210,140,20);
        lblpassword.setForeground(Color.BLACK);
        lblpassword.setFont(new Font("Tahoma", Font.BOLD,14));
        panel.add(lblpassword);
       
        password= new JTextField();
        password.setBounds(260,210,150,20);
        panel.add(password);
               
        create= new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setBounds(140, 260, 120, 25);
        create.addActionListener(this);
        panel.add(create);
        
        back= new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(300, 260, 120, 25);
        back.addActionListener(this);
        panel.add(back);
        
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("electricity_icon/eight.png"));
        Image i2= i1.getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image =new JLabel(i3);
        image.setBounds(415,30,250,250);
        panel.add(image);
    }  
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == create){
            String atype = accountType.getSelectedItem();
            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            String id=String.valueOf(rd.nextInt(999999));
//            
            try{
                Conn c = new Conn();
                String query = null;
                query = "insert into user values('"+id+"','"+susername+"','"+sname+"','"+spassword+"','"+atype+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Account Created Successfully");
                setVisible(false);
                new login();
               
            } catch(Exception e){
                e.printStackTrace();
            }
//            
        } else if(ae.getSource() == back){
            setVisible(false);
            new login().setVisible(true);
        }
        
    }
    
    public static void main(String[] args){
        
        new Signup().setVisible(true);
    }
}
