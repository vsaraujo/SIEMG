/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Vítor
 */
public class TelaLogin3 extends JFrame implements ActionListener {

    private static final int HEIGHT = 150;
    private static final int WIDTH = 220;

    private Credenciais credenciais;
    private boolean status;

    JTextField user = new JTextField(10);
    JTextField srv = new JTextField(10);
    JPasswordField pwd = new JPasswordField(10);
    JButton login = new JButton("Login");

    public TelaLogin3(Credenciais cre) {

        super("Login");
        credenciais = cre;
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
        setLocation(500, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        credenciais.setUsuario(user.getText());
        credenciais.setSenha(pwd.getText());
        credenciais.setServidor(srv.getText());

        this.dispose();

        status = Boolean.FALSE;

    }

    public boolean executando() {
        return status;
    }

}
