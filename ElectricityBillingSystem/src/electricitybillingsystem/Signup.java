package electricitybillingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Signup extends JFrame implements ActionListener{
    JTextField name, username, password, meterno;
    JButton createbtn, backbtn;
    Choice accountType;
    Signup(){
        setSize(1000, 700);
        setLocation(250, 80);
        setTitle("Signup Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        
        JPanel p = new JPanel();
        p.setLayout(null);
//        p.setBackground(new Color(246, 246, 246));
        p.setBackground(Color.WHITE);
        add(p);
        
        
        JLabel heading = new JLabel("Create an account");
        heading.setBounds(140, 50, 400, 40);
        heading.setForeground(Color.BLACK);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
        p.add(heading);
        
        JLabel accountTypeLabel = new JLabel("Type of account");
        accountTypeLabel.setBounds(50, 120, 140, 40);
        accountTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(accountTypeLabel);
        
        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(50, 160, 180, 40);
        accountType.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        p.add(accountType);
        
        JLabel lblmeter = new JLabel("Meter No");
        lblmeter.setBounds(250, 120, 100, 40);
        lblmeter.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(lblmeter);
        
        meterno = new JTextField();
        meterno.setBounds(250, 160, 180, 27);
        meterno.setMargin(new Insets(0, 5, 0, 5));
        meterno.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        p.add(meterno);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(50, 190, 100, 40);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(lblname);
        
        name = new JTextField();
        name.setBounds(50, 230, 380, 40);
        name.setMargin(new Insets(0, 10, 0, 10));
        name.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        p.add(name);
        
        JLabel lbluser = new JLabel("Username");
        lbluser.setBounds(50, 270, 100, 40);
        lbluser.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(lbluser);
        
        username = new JTextField();
        username.setBounds(50, 310, 380, 40);
        username.setMargin(new Insets(0, 10, 0, 10));
        username.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        p.add(username);
        
        JLabel lblpass = new JLabel("Password");
        lblpass.setBounds(50, 350, 100, 40);
        lblpass.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(lblpass);
        
        password = new JTextField();
        password.setBounds(50, 390, 380, 40);
        password.setMargin(new Insets(0, 10, 0, 10));
        password.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        p.add(password);
        
        createbtn = new JButton("Create account");
        createbtn.setBounds(50, 450, 380,40);
        createbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        createbtn.setBackground(new Color(68, 138, 255));
        createbtn.setForeground(Color.WHITE);
        createbtn.addActionListener(this);
        p.add(createbtn);
        
        backbtn = new JButton("Existing User? Login");
        backbtn.setBounds(50, 510, 380,40);
        backbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        backbtn.setBackground(new Color(232, 240, 254));
        backbtn.setForeground(new Color(68, 138, 255));
//        backbtn.setBorder(null);
        backbtn.addActionListener(this);
        p.add(backbtn);
        
        
        setLayout(new BorderLayout());
        
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/signup_background.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "East");
        
//        getContentPane().setBackground(Color.WHITE);
        
        
        setVisible(true);
      
    }
    
    File f = new File("login_details");
    int ln;
    String MeterNo, Name, Username, Password;
    
    void createFolder(){
        if(!f.exists()){
            try{
                f.mkdirs();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    void FileReader(){
        try {
            FileReader fr = new FileReader(f + "\\logins.txt");
            System.out.println("File exists!");
        } catch (FileNotFoundException ex) {
            try{
                FileWriter fw = new FileWriter(f + "\\logins.txt");
                System.out.println("File created");
            }catch(IOException ex1){
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
    }
    
    void addData(String mtrno, String name, String usr, String pswd){
        try {
            RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt", "rw");
            for(int i=0; i<ln; i++){
                raf.readLine();
            }
            
            raf.writeBytes("Username:" + usr + "\r\n");
            raf.writeBytes("Password:" + pswd + "\r\n");
            raf.writeBytes("Meter No:" + mtrno + "\r\n");
            raf.writeBytes("Name:" + name + "\r\n");
            raf.writeBytes("\r\n");
            raf.writeBytes("\r\n");
            
            JOptionPane.showMessageDialog(null, "Account created successfully");
            setVisible(false);
            new Login();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   void CheckData(String usr, String pswd){
       try {
           RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt", "rw");
            String line = raf.readLine();
            Username=line.substring(9);
            Password=raf.readLine().substring(9);
           if(usr.equals(Username) & pswd.equals(Password)){
                JOptionPane.showMessageDialog(null, "Password matched");
           }
           else{
                JOptionPane.showMessageDialog(null, "Invalid login");
            }
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   
   void logic(String usr, String pswd){
        try {
            RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt", "rw");
            for(int i=0;i<ln;i++){
                String forUser = raf.readLine().substring(9);
                String forPswd = raf.readLine().substring(9);
                if(usr.equals(forUser) & pswd.equals(forPswd)){
                    System.out.println("Password matched");
                }else{
                    System.out.println("Invalid login");
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    void countLines(){
        try {
            ln=1;
            RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt", "rw");
            for(int i=0;raf.readLine()!=null;i++){
                ln++;
            }
            System.out.println("number of lines:" + ln);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == createbtn){
            String atype = accountType.getSelectedItem();
            createFolder();
            FileReader();
            countLines();
            addData(meterno.getText(),name.getText(), username.getText(), password.getText());
            
            
        }
        else if(ae.getSource() == backbtn){
            setVisible(false);
            new Login();
        }
    }
    
    public static void main(String[] args) {
        new Signup();
    }
}
