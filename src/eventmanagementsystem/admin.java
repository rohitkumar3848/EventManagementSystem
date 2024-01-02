package eventmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class admin extends JFrame implements ActionListener {

    private JComboBox<String> userComboBox;
    private JComboBox<String> vendorComboBox;
    private DefaultComboBoxModel<String> userModel;
    private DefaultComboBoxModel<String> vendorModel;
    private Connection c;
    private JButton log;

    public admin() {
        try {
            // Establish database connection
             c = DriverManager.getConnection("jdbc:mysql://localhost:3306/eml", "root", "Rohit@123");

            // Initialize frame
            setTitle("Admin Panel");
            setSize(800, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            
            getContentPane().setBackground(new Color(234,182,118));
            // Initialize user dropdown
            userModel = new DefaultComboBoxModel<>();
            userComboBox = new JComboBox<>(userModel);

            // Initialize vendor dropdown
            vendorModel = new DefaultComboBoxModel<>();
            vendorComboBox = new JComboBox<>(vendorModel);

            // Fetch user and vendor data from the database
            fetchUserData();
            fetchVendorData();

            // Set layout
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            // Add main heading
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 5;
            JLabel mainHeading = new JLabel("Admin Panel");
            mainHeading.setFont(new Font("Arial", Font.BOLD, 30));
            add(mainHeading, gbc);

            // Add user dropdown with heading
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            add(new JLabel("Users"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            add(userComboBox, gbc);

            // Add vendor dropdown with heading
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            add(new JLabel("Vendors"), gbc);

            gbc.gridx = 3;
            gbc.gridy = 1;
            add(vendorComboBox, gbc);

            // Add delete button to delete selected user or vendor
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JButton deleteButton = new JButton("Delete Selected");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteUserOrVendor();
                }
            });
            add(deleteButton, gbc);
            
            ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("electricity_icon/login.png"));
            Image i2= i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
            log =new JButton("Logout", new ImageIcon(i2));
            log.setBounds(330,160,100,20);
            log.addActionListener(this);
            add(log);
            

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fetchUserData() {
        try {
            String query = "SELECT name FROM user where user_type='User'";
            try (PreparedStatement preparedStatement = c.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String userName = resultSet.getString("name");
                    userModel.addElement(userName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching user data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fetchVendorData() {
        try {
            String query = "SELECT name FROM user where user_type='Vendor'";
            try (PreparedStatement preparedStatement = c.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String vendorName = resultSet.getString("name");
                    vendorModel.addElement(vendorName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching vendor data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteUserOrVendor() {
        String selectedUser = (String) userComboBox.getSelectedItem();
        String selectedVendor = (String) vendorComboBox.getSelectedItem();

        if (selectedUser != null) {
            deleteUser(selectedUser);
        } else if (selectedVendor != null) {
            deleteVendor(selectedVendor);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user or vendor to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteUser(String userName) {
        try {
            String query = "DELETE FROM user WHERE name=?";
            try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
                preparedStatement.setString(1, userName);
                preparedStatement.executeUpdate();
            }

            userModel.removeElement(userName);
            JOptionPane.showMessageDialog(this, "User deleted successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting user", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteVendor(String vendorName) {
        try {
            String query = "DELETE FROM user WHERE name=?";
            try (PreparedStatement preparedStatement = c.prepareStatement(query)) {
                preparedStatement.setString(1, vendorName);
                preparedStatement.executeUpdate();
            }

            vendorModel.removeElement(vendorName);
            JOptionPane.showMessageDialog(this, "Vendor deleted successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting vendor", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==log){
            setVisible(false);
            new login();
        }
    }
    
    
    public static void main(String[] args) {
        //new EventManagementSystem.setVisible(true);
        SwingUtilities.invokeLater(() -> new admin().setVisible(true));
    }
}