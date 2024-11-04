package Controlador;

import Modelo.Usuario;
import Vista.frmLogin;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ctrlLogin implements MouseListener {

    Usuario Modeloo;
    frmLogin vista;

    public ctrlLogin(Usuario Modeloo, frmLogin Vista) {
        this.Modeloo = Modeloo;
        this.vista = Vista;

        vista.btnIngresar.addMouseListener(this);
        vista.btnIrARegistro.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnIngresar) {
            Modeloo.setNombre(vista.txtUsuario.getText());
            Modeloo.setContraseña(Modeloo.convertirSHA256(vista.txtContraseña.getText()));
           
            boolean comprobar = Modeloo.iniciarSesion();

            if (comprobar == true) {
                JOptionPane.showMessageDialog(vista,"Usuario existe, ¡Bienvenido!");
                Vista.frmCrud.initFrmCrud();
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista, "Usuario no encontrado");

            }
        }
        
        if(e.getSource() == vista.btnIrARegistro){
            Vista.frmRegistro.initFrmRegistro();
             vista.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
