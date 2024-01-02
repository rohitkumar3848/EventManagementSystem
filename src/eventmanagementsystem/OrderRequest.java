package eventmanagementsystem;

import static eventmanagementsystem.ProductList.user_id;
import static eventmanagementsystem.ProductList.vendor_id;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JPanel;
import java.util.Random;

public class OrderRequest extends JFrame implements ActionListener {

    JButton home, logout, order;
    JTextField tfname, tfadress, tfemail, tfpayment;
    //JTextField address;
    //JTextField email;
    static String vendor_id = null;
    static String user_id = null;
    static String product_id = null;
    static String product_name = null;
    static String price = null;
    //public Connection c=nulll;
    public Conn c = null;
    Random rd = new Random();

    OrderRequest(String vendor_id, String user_id, String product_id,String product_name,String price) {
        super("Order Request");
        
        //c = DriverManager.getConnection("jdbc:mysql://localhost:3306/eml", "root", "Rohit@123");
        this.vendor_id = vendor_id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        System.out.println(vendor_id+" "+user_id+" "+product_id);
        setSize(800, 500);
        setLocation(380, 200);
        setVisible(true);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(234, 182, 118));
        add(p);

        home = new JButton("Home");
        home.setBounds(30, 20, 100, 30);
        home.setBackground(Color.BLACK);
        home.setForeground(Color.WHITE);
        home.addActionListener(this);
        p.add(home);

        logout = new JButton("Logout");
        logout.setBounds(660, 20, 100, 30);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        logout.addActionListener(this);
        p.add(logout);

        JLabel heading = new JLabel("USERS ORDERS");
        heading.setBounds(300, 40, 550, 70);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        p.add(heading);

        JLabel name = new JLabel(" Name");
        name.setBounds(160, 130, 120, 20);
        name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(name);

        tfname = new JTextField();
        tfname.setBounds(280, 130, 170, 20);
        tfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(tfname);

        JLabel adress = new JLabel(" Address");
        adress.setBounds(160, 190, 120, 20);
        adress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(adress);

        tfadress = new JTextField();
        tfadress.setBounds(280, 190, 170, 20);
        tfadress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(tfadress);

        JLabel email = new JLabel(" Email");
        email.setBounds(160, 250, 120, 20);
        email.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(email);

        tfemail = new JTextField();
        tfemail.setBounds(280, 250, 170, 20);
        tfemail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(tfemail);

        JLabel payment = new JLabel(" Payment Mode");
        payment.setBounds(160, 310, 120, 20);
        payment.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(payment);

        tfpayment = new JTextField();
        tfpayment.setText("Cash on Delivery");
        tfpayment.setEditable(false);
        tfpayment.setBounds(280, 310, 170, 20);
        tfpayment.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(tfpayment);

        order = new JButton("Orders");
        order.setBounds(280, 370, 100, 30);
        order.setBackground(Color.BLACK);
        order.setForeground(Color.WHITE);
        order.addActionListener(this);
        p.add(order);

//        JLabel lblname2 = new JLabel("Email");
//        lblname2.setBounds(250, 130, 100, 20);
//        lblname2.setFont(new Font("Tahoma",Font.PLAIN,16));
//        p.add(lblname2);
//        
//        JLabel email = new JLabel("");
//        email.setBounds(250,180,100,20);
//        p.add(email);
//        
//        JLabel lblname3 = new JLabel("Address");
//        lblname3.setBounds(450, 130, 100, 20);
//        lblname3.setFont(new Font("Tahoma",Font.PLAIN,16));
//        p.add(lblname3);
//        
//        JLabel address = new JLabel("");
//        address.setBounds(450,180,100,20);
//        p.add(address);
//        
//        JLabel lblname4 = new JLabel("Status");
//        lblname4.setBounds(630, 130, 100, 20);
//        lblname4.setFont(new Font("Tahoma",Font.PLAIN,16));
//        p.add(lblname4);
//        
//        JLabel status = new JLabel("");
//        status.setBounds(630,180,100,20);
//        p.add(status);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == order) {
            
            String name = tfname.getText();
            String address = tfadress.getText();
            String email = tfemail.getText();
            String order_status = "pending";
            try {
                c = new Conn();
                String order_id = String.valueOf(rd.nextInt(999999));
                String query = "insert into orders values('" + order_id + "','" + user_id + "','" + product_id + "','" + order_status + "','" + product_name + "','" + name + "','" + address + "','" + email + "','" + vendor_id + "','" + price + "')";
                // Insert the new item into the database

                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(this, "Item ordered successfully");
                setVisible(false);
                new OrderStatus(user_id).setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error ordering item", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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
        new OrderRequest(vendor_id,user_id,product_id,product_name,price);
    }
    
}
