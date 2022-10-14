
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

public class Login extends JFrame implements ActionListener{
    
    JTextField username, password;
    JButton loginbtn, signupbtn;
    Login(){
        setSize(1000, 700);
        setLocation(250, 80);
        setTitle("Login Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        JPanel p = new JPanel();
        p.setLayout(null);
//        p.setBackground(new Color(246, 246, 246));
        p.setBackground(Color.WHITE);
        add(p);
        
        JLabel heading = new JLabel("Login");
        heading.setBounds(200, 60, 200, 40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 28));
        p.add(heading);
        
        JLabel lblname = new JLabel("Username");
        lblname.setBounds(60, 140, 100, 40);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(lblname);
        
        username = new JTextField();
        username.setBounds(60, 180, 360, 40);
        username.setMargin(new Insets(0, 10, 0, 10));
        username.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        p.add(username);
        
        JLabel lblpass = new JLabel("Password");
        lblpass.setBounds(60, 220, 100, 40);
        lblpass.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(lblpass);
        
        password = new JTextField();
        password.setBounds(60, 260, 360, 40);
        password.setMargin(new Insets(0, 10, 0, 10));
        password.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        p.add(password);
        
        JLabel loggininas = new JLabel("Logging in as");
        loggininas.setBounds(60, 300, 100, 40);
        loggininas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        p.add(loggininas);
        
        Choice logginin = new Choice();
        logginin.add("Admin");
        logginin.add("Customer");
        logginin.setBounds(60, 340, 360, 40);
        logginin.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        p.add(logginin);
        
        loginbtn = new JButton("Login");
        loginbtn.setBounds(60, 400, 360,40);
        loginbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        loginbtn.setBackground(new Color(68, 138, 255));
        loginbtn.setForeground(Color.WHITE);
        loginbtn.addActionListener(this);
        p.add(loginbtn);
        
        signupbtn = new JButton("New User? Sign Up");
        signupbtn.setBounds(60, 460, 360,40);
        signupbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        signupbtn.setBackground(new Color(201, 229, 255));
        signupbtn.setBackground(new Color(232, 240, 254));
        signupbtn.setForeground(new Color(68, 138, 255));
//        signupbtn.setBorder(null);
        signupbtn.addActionListener(this);
        p.add(signupbtn);
        
//        JLabel text = new JLabel("Don't have an account ?");
//        text.setBounds(610, 460, 500, 20);
//        text.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        add(text);
//        
//        signup = new JButton("Sign Up");
//        signup.setBounds(790, 460, 80, 20);
//        signup.setFont(new Font("Tahoma", Font.BOLD, 16));
//        signup.setBackground(new Color(246, 246, 246));
//        signup.setForeground(new Color(68, 138, 255));
//        signup.setBorder(null);
////        signup.addActionListener(this);
//        add(signup);
        
        
        setLayout(new BorderLayout());
        
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/login_background.png"));
        Image i2 = i1.getImage().getScaledInstance(500, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    
    File f = new File("login_details");
    int ln;
    String Username, Password;
    
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
            
            raf.writeBytes("Meter No:" + mtrno + "\r\n");
            raf.writeBytes("Name:" + name + "\r\n");
            raf.writeBytes("Username:" + usr + "\r\n");
            raf.writeBytes("Password:" + pswd + "\r\n");
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
            for(int i=0;i<ln;i+=6){
                System.out.println("count" + i);
                String forUser = raf.readLine().substring(9);
                String forPswd = raf.readLine().substring(9);
                if(usr.equals(forUser) & pswd.equals(forPswd)){
                    JOptionPane.showMessageDialog(null, "Password matched");
                    break;
                }else if(i==(ln-5)){
                    JOptionPane.showMessageDialog(null, "Invalid login");
                    break;
                }
                for(int k=1;k<=4;k++){
                    raf.readLine();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
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
        if(ae.getSource() == loginbtn){
            createFolder();
            FileReader();
            countLines();
            logic(username.getText(), password.getText());
        }
        else if(ae.getSource() == signupbtn){
            setVisible(false);
            new Signup();
        }
    }
 
    
    public static void main(String[] args) {
        new Login();
        
    }
}


