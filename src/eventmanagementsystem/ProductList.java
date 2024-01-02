
package eventmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductList extends JFrame implements ActionListener{
    Choice productDropdown;
    JButton add,next,cancel,home,back;
    private Connection c;
    static String vendor_id=null;
    static String  user_id=null;
    
    ProductList(String vendor_id,String user_id){
        
        super("Choose Items");
        this.vendor_id=vendor_id;
        this.user_id=user_id;
        //System.out.println(vendor_id+" "+user_id);
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/eml", "root", "Rohit@123");
        setSize(500,500);
        setLocation(500,200);
        
        setVisible(true);
        
        JPanel p =new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(234,182,118));
        add(p);
        
        JLabel heading = new JLabel("Products Details");
        heading.setBounds(180, 15, 200, 25);
        heading.setFont(new Font("Tahoma",Font.PLAIN, 24));
        p.add(heading);
        
        JLabel lblname1 = new JLabel("Products");
        lblname1.setBounds(140, 120, 100, 20);
        lblname1.setFont(new Font("Tahoma",Font.PLAIN,16));
        p.add(lblname1);
        
        productDropdown= new Choice();
        productDropdown.setBounds(140,160,200,25);
        p.add(productDropdown);
        getProducts();
        
        add= new JButton("Buy Now");
        add.setBounds(380,160,100,20);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        p.add(add);
        
        
        
       
        
        
        
        
        
        
        
        home= new JButton("Home");
        home.setBounds(48,20,100,25);
        home.setBackground(Color.WHITE);
        home.setForeground(Color.BLACK);
        home.addActionListener(this);
        p.add(home);
        
        back= new JButton("Back");
        back.setBounds(380,20,100,25);
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        p.add(back);
        
        
        cancel= new JButton("Cancel");
        cancel.setBounds(370,300,100,25);
        cancel.setBackground(Color.WHITE);
        cancel.setForeground(Color.BLACK);
         cancel.addActionListener(this);
        p.add(cancel);
        }
        
        
       catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void getProducts() {
        

        try {
            String sql = "SELECT * FROM products where user_id='"+vendor_id+"'";
            try (PreparedStatement preparedStatement = c.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                //List<String> vendors = new ArrayList<>();
                while (resultSet.next()) {
                    productDropdown.add(resultSet.getString("product_name")+"-"+resultSet.getString("product_id"));
                }

                // Populate the choice component with vendors
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    
  

    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==cancel){
            setVisible(false);
            new UserUI(user_id).setVisible(true);
        }else if(ae.getSource()==back){
            setVisible(false);
            new VendorList(user_id).setVisible(true);
        }else if(ae.getSource()==home){
           new UserUI(user_id).setVisible(true);
        }
        else if(ae.getSource()==add){
            String pr= productDropdown.getSelectedItem();
            String product_name=pr.substring(0,pr.indexOf('-'));
            String product_id=pr.substring(pr.lastIndexOf('-')+1);
            String price=null;
            try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select price from products where product_id='" +product_id + "'");
            while (rs.next()) {
                price=rs.getString("price");
            }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //System.out.print(product_id);
 
            setVisible(false);
            new OrderRequest(vendor_id,user_id,product_id,product_name,price).setVisible(true);
        }
    }
    
    public static void main(String [] args){
        new ProductList(vendor_id,user_id);
    }
    
}
