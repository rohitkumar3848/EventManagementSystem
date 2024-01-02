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

public class Status extends JFrame implements ActionListener  {
    JLabel lorderID,name,address,product,changed_statuslabel,email,emaillabel,product_nameLabel,nameLabel,order_statusLabel ,adsressLabel, product_name, order_status;
    Choice order_id,changed_status;
    JButton back, remove;
    static String vendorId=null;

    Status(String vendorId) {
        setLayout(null);
        this.vendorId=vendorId;

        Font accent = new Font("Verdana", Font.BOLD, 25);

        lorderID = new JLabel("Select order by Order ID ");
        lorderID.setForeground(Color.BLACK);
        lorderID.setFont(accent);
        lorderID.setBounds(20, 40, 360, 50);
        add(lorderID);

        order_id = new Choice();
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from orders where vendor_id='" +vendorId + "'");
            while (rs.next()) {
                order_id.add(rs.getString("order_id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        order_id.setBounds(390, 40, 220, 50);
        order_id.setFont(accent);
        add(order_id);

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
        
        
        changed_statuslabel = new JLabel("Changed Status : ");
        changed_statuslabel.setForeground(Color.BLACK);
        changed_statuslabel.setFont(accent);
        changed_statuslabel.setBounds(40, 410, 300, 50);
        add(changed_statuslabel);
        
        
        changed_status = new Choice();
        changed_status.setBounds(360, 410, 300, 50);
        changed_status.setFont(accent);
        changed_status.add("Pending");
        changed_status.add("Ready For Shipping");
        changed_status.add("Out For Delivery");
        add(changed_status);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from orders where order_id='" + order_id.getSelectedItem() + "'");
            if (rs.next()) {
                name.setText(rs.getString("name"));
                email.setText(rs.getString("email"));
                address.setText(rs.getString("address"));
                product_name.setText(rs.getString("product_name"));
                order_status.setText(rs.getString("order_status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        order_id.addItemListener(e -> {
            try {
                Conn conn = new Conn();
                ResultSet rs = conn.s.executeQuery("select * from orders where order_id='" + order_id.getSelectedItem() + "' AND vendor_id='" + vendorId + "'");
                while (rs.next()) {
                        name.setText(rs.getString("name"));
                        email.setText(rs.getString("email"));
                        address.setText(rs.getString("address"));
                        product_name.setText(rs.getString("product_name"));
                        order_status.setText(rs.getString("order_status"));
                }
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

        remove = new JButton("Update Status");
        remove.setBackground(Color.RED);
        remove.setForeground(Color.WHITE);
        remove.setFont(accent);
        remove.setBounds(300, 500, 250, 80);
        remove.addActionListener(this);
        add(remove);

        //getContentPane().setBackground(Color.WHITE);
        getContentPane().setBackground(new Color(234,182,118));
        setTitle("Status Users");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    @Override
//    public void actionPerformed(ActionEvent ae) {
//        if (ae.getSource() == remove) {
//            try {
//                Conn conn = new Conn();
//                conn.s.executeUpdate("delete from employees where id='" + selectEmployee.getSelectedItem() + "'");
//                JOptionPane.showMessageDialog(null, "Employee with Id: " + selectEmployee.getSelectedItem() + " has been removed");
//                new Home();
//                setVisible(false);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        } else {
//            new VendorUI(vendorId);
//            setVisible(false);
//        }
//    }
    public void actionPerformed(ActionEvent ae){
        try{
            Conn c = new Conn();
            if(ae.getSource() == remove){
            String orderStatus = changed_status.getSelectedItem();
            String selectedOrderId = order_id.getSelectedItem(); // Using a different variable name

            String query = "UPDATE orders SET order_status='" + orderStatus + "' WHERE order_id='" + selectedOrderId + "'";
            c.s.executeUpdate(query);

            }
            ResultSet rs = c.s.executeQuery("select * from orders where order_id='" + order_id.getSelectedItem() + "' AND vendor_id='" + vendorId + "'");
            while (rs.next()) {
                        name.setText(rs.getString("name"));
                        email.setText(rs.getString("email"));
                        address.setText(rs.getString("address"));
                        product_name.setText(rs.getString("product_name"));
                        order_status.setText(rs.getString("order_status"));
            }
        }
        catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error while changing status of users", "Error", JOptionPane.ERROR_MESSAGE);
            }
        if(ae.getSource()==back){
            setVisible(false);
            new VendorUI(vendorId);
        }
        
    }

    public static void main(String[] args) {
        new Status(vendorId);
    }
}