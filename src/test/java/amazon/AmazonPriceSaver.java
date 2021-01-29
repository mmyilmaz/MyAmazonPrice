package amazon;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AmazonPriceSaver extends JFrame {

    private static final long serialVersionUID = 1L;
        private JPanel contentPane;
        private JTextField amazon_id;
        private JTextField first_price;
        private JButton btnNewButton;

        /**
         * Launch the application.
         */
        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        AmazonPriceSaver frame = new AmazonPriceSaver();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /**
         * Create the frame.
         */

        public AmazonPriceSaver() throws IOException {
            setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(450, 190, 1014, 597);
            setResizable(false);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel lblNewUserRegister = new JLabel("Amazon Price Tracker");
            lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
            lblNewUserRegister.setBounds(362, 52, 325, 50);
            contentPane.add(lblNewUserRegister);

            JLabel lblName = new JLabel("Amazon ID");
            lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
            lblName.setBounds(58, 152, 99, 43);
            contentPane.add(lblName);

            JLabel lblNewLabel = new JLabel("First Price");
            lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
            lblNewLabel.setBounds(58, 243, 110, 29);
            contentPane.add(lblNewLabel);

            amazon_id = new JTextField();
            amazon_id.setFont(new Font("Tahoma", Font.PLAIN, 32));
            amazon_id.setBounds(214, 151, 228, 50);
            contentPane.add(amazon_id);
            amazon_id.setColumns(10);

            first_price = new JTextField();
            first_price.setFont(new Font("Tahoma", Font.PLAIN, 32));
            first_price.setBounds(214, 235, 228, 50);
            contentPane.add(first_price);
            first_price.setColumns(10);
            btnNewButton = new JButton("Register");
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String firstName = amazon_id.getText();
                    String lastName = first_price.getText();
                     try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/amazonprices?useTimezone=true&serverTimezone=UTC", "root", "Mtk.202123733");

                         String query = "INSERT INTO account values('" + firstName + "','" + lastName + "')";

                        Statement sta = connection.createStatement();
                        int x = sta.executeUpdate(query);
                        if (x == 0) {
                            JOptionPane.showMessageDialog(btnNewButton, "This is alredy exist");
                        } else {
                            JOptionPane.showMessageDialog(btnNewButton,
                                    "Welcome, " +  "Your account is sucessfully created");
                        }

                        connection.close();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
            btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
            btnNewButton.setBounds(399, 447, 259, 74);
            contentPane.add(btnNewButton);
        }
    }


