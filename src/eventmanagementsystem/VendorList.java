
package eventmanagementsystem;
import javax.swing.*;
import java.awt.*;
//import java.util.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorList extends JFrame implements ActionListener {
    JTextField tfname;
    JButton next,cancel;
    Choice vendorname;
    static String user_id=null;
    //JComboBox<String> vendorname;
    private Connection c;
    VendorList(String user_id){
        super("Choose Vendor");
        this.user_id=user_id;
        try{
        
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/eml", "root", "Rohit@123");
        setSize(500,300);
        setLocation(500,300);
        
        setVisible(true);
        
        JPanel p =new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(234,182,118));
        add(p);
        
        JLabel heading = new JLabel("Vendor");
        heading.setBounds(210, 10, 200, 25);
        heading.setFont(new Font("Tahoma",Font.PLAIN, 24));
        p.add(heading);
        
        JLabel lblname = new JLabel("Vendors");
        lblname.setBounds(100, 80, 100, 20);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname);
        
        vendorname= new Choice();
//        vendorname.add("Vendor1");
//        vendorname.add("Vendor2");
//        vendorname.add("Vendor3");
//        vendorname.add("Vendor4");
        vendorname.setBounds(200,80,200,25);
        p.add(vendorname);
        
        fetchVendors();
        
        
        
        next= new JButton("Next");
        next.setBounds(100,160,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
         next.addActionListener(this);
        p.add(next);
        
        cancel= new JButton("Cancel");
        cancel.setBounds(300,160,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
         cancel.addActionListener(this);
        p.add(cancel);
        }
        catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
       
        
    }
    
    
    private void fetchVendors() {
        

        try {
            String sql = "SELECT * FROM user where user_type='Vendor'";
            try (PreparedStatement preparedStatement = c.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                //List<String> vendors = new ArrayList<>();
                while (resultSet.next()) {
                    vendorname.add(resultSet.getString("name")+"-"+resultSet.getString("id"));
                }

                // Populate the choice component with vendors
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public String getId(String text){
        return text.substring(text.lastIndexOf("-")+1);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==next){
            String vendorId= getId(vendorname.getSelectedItem());
            //System.out.println(vendorId+" "+user_id);
            setVisible(false);
            new ProductList(vendorId,user_id);
            
        }else if(ae.getSource()==cancel){
            setVisible(false);
        }
    }
    
    
    
    public static void main(String[] args){
        new VendorList(user_id);
    }
    
}