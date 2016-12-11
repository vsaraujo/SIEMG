/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splunk;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Vítor
 */
public class TelaLogin extends JFrame implements ActionListener{
    
    private static final int HEIGHT = 90;
    private static final int WIDTH = 400;
    
    private SplunkConnect splunkcon;
    private boolean status;
    
    JTextField user = new JTextField(10);
    JTextField srv = new JTextField(10);
    JPasswordField pwd = new JPasswordField(10);
    JButton login = new JButton("Login");
        
    public TelaLogin(SplunkConnect con){
        
        super("Login - Splunk");
        splunkcon = con;      
        status = Boolean.TRUE;
      
       Container pane = getContentPane();
       setLayout(new FlowLayout());

        add(new JLabel("Usuario:"));
            add(user);
        add(new JLabel("Senha:"));
            add(pwd);
        add(new JLabel("Servidor:"));
            add(srv);

            add(login);
                login.addActionListener(this);
            
       setSize(WIDTH, HEIGHT);
       setResizable(false);
       setLocation(500, 300);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setVisible(true); 
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        splunkcon.setUsuario(user.getText());
        splunkcon.setSenha(pwd.getText());
        splunkcon.setServidor(srv.getText());
        
        this.dispose();
        
        status = Boolean.FALSE;
                
    }
    public boolean executando(){
        return status;
    }
    
}
