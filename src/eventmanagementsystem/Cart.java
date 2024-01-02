/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventmanagementsystem;

/**
 *
 * @author rohit
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cart extends JFrame implements ActionListener  {
    JLabel lorderID,name,address,price,pricelabel,email,emaillabel,product_nameLabel,nameLabel,order_statusLabel ,adsressLabel, product_name, order_status;
    Choice product_id;
    JButton back, remove;
    static String user_id=null;

    Cart(String user_id) {
        setLayout(null);
        this.user_id=user_id;

        Font accent = new Font("Verdana", Font.BOLD, 25);

        lorderID = new JLabel("Your orders List");
        lorderID.setForeground(Color.BLACK);
        lorderID.setFont(accent);
        lorderID.setBounds(20, 40, 360, 50);
        add(lorderID);

        product_id = new Choice();
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from orders where user_id='" +user_id + "'");
            while (rs.next()) {
                product_id.add(rs.getString("product_id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        product_id.setBounds(390, 40, 220, 50);
        product_id.setFont(accent);
        add(product_id);

        nameLabel = new JLabel("User Name : ");
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(accent);
        nameLabel.setBounds(40, 110, 300, 50);
        add(nameLabel);

        name = new JLabel();
        name.setForeground(Color.BLACK);
        name.setFont(accent);
        name.setBounds(360, 110, 300, 50);
        add(name);
        
        
        emaillabel = new JLabel("Email : ");
        emaillabel.setForeground(Color.BLACK);
        emaillabel.setFont(accent);
        emaillabel.setBounds(40, 170, 300, 50);
        add(emaillabel);

        email = new JLabel();
        email.setForeground(Color.BLACK);
        email.setFont(accent);
        email.setBounds(360, 170, 300, 50);
        add(email);

        adsressLabel = new JLabel("Address : ");
        adsressLabel.setForeground(Color.BLACK);
        adsressLabel.setFont(accent);
        adsressLabel.setBounds(40, 230, 300, 50);
        add(adsressLabel);

        address = new JLabel();
        address.setForeground(Color.BLACK);
        address.setFont(accent);
        address.setBounds(360, 230, 300, 50);
        add(address);


        product_nameLabel = new JLabel("Product Name : ");
        product_nameLabel.setForeground(Color.BLACK);
        product_nameLabel.setFont(accent);
        product_nameLabel.setBounds(40, 290, 300, 50);
        add(product_nameLabel);

        product_name = new JLabel();
        product_name.setForeground(Color.BLACK);
        product_name.setFont(accent);
        product_name.setBounds(360, 290, 300, 50);
        add(product_name);
        
        
        order_statusLabel = new JLabel("Order Status : ");
        order_statusLabel.setForeground(Color.BLACK);
        order_statusLabel.setFont(accent);
        order_statusLabel.setBounds(40, 350, 300, 50);
        add(order_statusLabel);

        order_status = new JLabel();
        order_status.setForeground(Color.BLACK);
        order_status.setFont(accent);
        order_status.setBounds(360, 350, 300, 50);
        add(order_status);
        
        
        pricelabel = new JLabel("Price : ");
        pricelabel.setForeground(Color.BLACK);
        pricelabel.setFont(accent);
        pricelabel.setBounds(40, 410, 300, 50);
        add(pricelabel);
        
        
        price = new JLabel();
        price.setForeground(Color.BLACK);
        price.setFont(accent);
        price.setBounds(360, 410, 300, 50);
        add(price);
        

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from orders where product_id='" + product_id.getSelectedItem() + "' AND user_id='" + user_id + "'");
            if (rs.next()) {
                name.setText(rs.getString("name"));
                email.setText(rs.getString("email"));
                address.setText(rs.getString("address"));
                product_name.setText(rs.getString("product_name"));
                order_status.setText(rs.getString("order_status"));
                price.setText(rs.getString("price"));
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        product_id.addItemListener(e -> {
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from orders where product_id='" + product_id.getSelectedItem() + "' AND user_id='" + user_id + "'");
                while (rs.next()) {
                        name.setText(rs.getString("name"));
                        email.setText(rs.getString("email"));
                        address.setText(rs.getString("address"));
                        product_name.setText(rs.getString("product_name"));
                        order_status.setText(rs.getString("order_status"));
                        price.setText(rs.getString("price"));
                }
                rs.close();
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        
        
        

//        ImageIcon bgDelete = new ImageIcon(ClassLoader.getSystemResource("images/bgDelete.png"));
//        Image bgDeleteScaled = bgDelete.getImage().getScaledInstance(550, 750, Image.SCALE_DEFAULT);
//        JLabel bgDeleteLabel = new JLabel(new ImageIcon(bgDeleteScaled));
//        bgDeleteLabel.setBounds(600, 20, 550, 750);
//        add(bgDeleteLabel);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(accent);
        back.setBounds(40, 500, 250, 80);
        back.addActionListener(this);
        add(back);


        //getContentPane().setBackground(Color.WHITE);
        getContentPane().setBackground(new Color(234,182,118));
        setTitle("Status Users");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            setVisible(false);
            new UserUI(user_id);
        }
    }

    public static void main(String[] args) {
        new Cart(user_id);
    }
}