
package eventmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class login extends JFrame implements ActionListener {
    JButton login,cancel,signup;
    JTextField username,password;
    Choice loginin;
    login(){
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel lblusername= new JLabel("Username");
        lblusername.setBounds(300,20,100,20);
        add(lblusername);
        
        username = new JTextField();
        username.setBounds(400,20,150,20);
        add(username);
        
        JLabel lblpassword= new JLabel("Password");
        lblpassword.setBounds(300,60,100,20);
        add(lblpassword);
        
        password = new JTextField();
        password.setBounds(400,60,150,20);
        add(password);
        JLabel logininas= new JLabel("Login in as");
        logininas.setBounds(300,100,100,20);
        add(logininas);
        
        loginin= new Choice();
        loginin.add("Admin");
        loginin.add("User");
        loginin.add("Vendor");
        loginin.setBounds(400,100,150,20);
        add(loginin);
        
        login =new JButton("Login");
        login.setBounds(330,160,100,20);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        cancel =new JButton("Cancel");
        cancel.setBounds(450,160,100,20);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);
        
        
        signup =new JButton("Signup");
        signup.setBounds(380,200,100,20);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        
        ImageIcon i7= new ImageIcon(ClassLoader.getSystemResource("electricity_icon/nine.png"));
        Image i8= i7.getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT);
        ImageIcon i9 =new ImageIcon(i8);
        JLabel image=new JLabel(i9);
        image.setBounds(0, 0, 250, 250);
        add(image);
        setSize(640,300);
        setLocation(400,200);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if( ae.getSource() ==login){
            String susername = username.getText();
            String spassword = password.getText();
            String user  =loginin.getSelectedItem();
////            
            try{
               Conn c =new Conn();
               String query = "select * from user where username = '"+susername+"' and password ='"+spassword+"' and user_type = '"+user+"'";
               
               ResultSet rs = c.s.executeQuery(query);
               
               if(rs.next()){
//                   String meter= rs.getString("meter_no");
                  setVisible(false);
                  if(user.equals("User")){
                      new UserUI(rs.getString("id")).setVisible(true);
                  }
                  else if(user.equals("Admin")){
                      new admin().setVisible(true);
                  }
                  else if(user.equals("Vendor")){
//                      new Vendor().setVisible(true);
                      new VendorUI(rs.getString("id")).setVisible(true);
                  }
               } else{
                   JOptionPane.showMessageDialog(null,"Invalid Login");
                   username.setText("");
                   password.setText("");
               }
            } catch(Exception e){
                e.printStackTrace();
            }
        } else if(ae.getSource() == cancel){
            setVisible(false);
        } else if(ae.getSource() == signup){
            setVisible(false);
            new Signup();
        }
    }
    public static void main(String [] args){
        new login().setVisible(true);
    }
}
