package eventmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class Vendor extends JFrame implements ActionListener {

    private JComboBox<String> itemComboBox;
    private JTextField newItemField;
    private JTextField priceField;
    private JButton addItemButton;
    private JButton deleteItemButton;
    private JList<String> orderedItemList; // Added component
    private DefaultListModel<String> orderedItemListModel;
    private JButton log;
    public Connection c;
    static String vendorId=null;
    Random rd=new Random();

    public Vendor(String vendorId) {
        super("Vendor");
        this.vendorId=vendorId;
        //System.out.println(vendorId);
        setLayout(null);
        getContentPane().setBackground(new Color(234,182,118));
        try {
            // Establish database connection
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/eml", "root", "Rohit@123");

            setTitle("Vendor");
            setSize(600, 400);  // Increased frame size
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(null);
           
           

            itemComboBox = new JComboBox<>();
            newItemField = new JTextField(20);
            priceField = new JTextField(20);
            addItemButton = new JButton("Add Item");
            deleteItemButton = new JButton("Delete Item");

            // Initialize the new components for displaying ordered items
            orderedItemListModel = new DefaultListModel<>();
            orderedItemList = new JList<>(orderedItemListModel);

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(15, 15, 15, 15);  // Padding

            gbc.gridx = 0;
            gbc.gridy = 0;
            add(new JLabel("Select Item:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 5;
            itemComboBox.setPreferredSize(new Dimension(200, 25));
            add(itemComboBox, gbc);
            
            gbc.gridx = 3;
            gbc.gridy = 0;
            deleteItemButton.addActionListener(e -> deleteItem());
            add(deleteItemButton, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            add(new JLabel("ADD New Product:"), gbc);
            

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            add(new JLabel("New Product:"), gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 5;
            newItemField.setPreferredSize(new Dimension(400, 25));  // Increased width
            add(newItemField, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            add(new JLabel("Add Price:"), gbc);
            
            
            
            
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            priceField.setPreferredSize(new Dimension(400, 25));  // Increased width
            add(priceField, gbc);

            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.gridwidth = 1;
            addItemButton.addActionListener(e -> addItem());
            add(addItemButton, gbc);
            
            
//            gbc.gridx =2;
//            gbc.gridy = 4;
//            gbc.gridwidth = 1;
//            addItemButton.addActionListener(e -> addItem());
//            add(addItemButton, gbc);

//            gbc.gridx = 2;
//            gbc.gridy = 3;
//            deleteItemButton.addActionListener(e -> deleteItem());
//            add(deleteItemButton, gbc);

            // New component for displaying ordered items
//            gbc.gridx = 1;
//            gbc.gridy = 3;
//            gbc.gridwidth = 3;
//            add(new JLabel("Ordered Items:"), gbc);
//
//            gbc.gridx = 1;
//            gbc.gridy = 4;
//            gbc.gridwidth = 5;
//            gbc.fill = GridBagConstraints.HORIZONTAL; // Allow vertical growth
//            add(new JScrollPane(orderedItemList), gbc);
            
            ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("electricity_icon/login.png"));
            Image i2= i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
            log =new JButton("Back", new ImageIcon(i2));
            log.setBounds(330,160,100,20);
            log.addActionListener(this);
            add(log);

            loadItems();
            loadOrderedItems();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadItems() {
        try {
            String query = "SELECT * FROM products where user_id='"+vendorId+"'";
            try (PreparedStatement preparedStatement = c.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String product = resultSet.getString("product_name")+"-"+resultSet.getString("product_id");
                    itemComboBox.addItem(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading items", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addItem() {
        String newItem = newItemField.getText();
        String price = priceField.getText();
        //System.out.print(price);

        if (newItem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a new item", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        

        try {
            Conn c = new Conn();
            String id=String.valueOf(rd.nextInt(999999));
            String query = "insert into products values('"+id+"','"+vendorId+"','"+newItem+"','"+price+"')";
            // Insert the new item into the database
             
           c.s.executeUpdate(query);
                

            JOptionPane.showMessageDialog(this, "Item added successfully");
            itemComboBox.addItem(newItem+"-"+id);
            newItemField.setText("");
            priceField.setText("");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding item", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteItem() {
        String selectedItem = (String) itemComboBox.getSelectedItem();

        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Delete the selected item from the database
            Conn c=new Conn();
            String id=getProductId(itemComboBox.getSelectedItem().toString());
            String query = "DELETE FROM products WHERE product_id='"+id+"'";
            c.s.executeUpdate(query);
//            try (PreparedStatement preparedStatement = c.s.prepareStatement(query)) {
//                //preparedStatement.setString(1, selectedItem);
//                preparedStatement.executeUpdate();
//            }

            JOptionPane.showMessageDialog(this, "Item deleted successfully");
            itemComboBox.removeItem(selectedItem);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting item", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadOrderedItems() {
        try {
            String query = "SELECT itemName FROM orderlist";
            try (PreparedStatement preparedStatement = c.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                orderedItemListModel.clear(); // Clear existing ordered items
                while (resultSet.next()) {
                    String itemName = resultSet.getString("itemName");
                    orderedItemListModel.addElement(itemName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading ordered items", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private String getProductId(String text){
            return text.substring(text.lastIndexOf('-')+1);
        }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==log){
            setVisible(false);
            new VendorUI(vendorId);
        }
    }

    public static void main(String[] args) {
        new Vendor(vendorId).setVisible(true);
    }
}