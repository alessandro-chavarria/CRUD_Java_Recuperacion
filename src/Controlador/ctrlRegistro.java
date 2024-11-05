package Controlador;

import Modelo.Usuario;
import Vista.frmRegistro;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ctrlRegistro implements MouseListener{
    
    //1-Llamar a las otras capas
    Usuario Modeloo;
    frmRegistro vista;
    
    //2-Constructor 
    public ctrlRegistro(Usuario Modeloo, frmRegistro Vista){
        this.Modeloo = Modeloo;
        this.vista = Vista;
        
        vista.btnRegistrarme.addMouseListener(this);
        vista.btnIrLogin.addMouseListener(this);
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vista.btnRegistrarme){
            Modeloo.setNombre(vista.txtNombre.getText());
            Modeloo.setApellidos(vista.txtApellidos.getText());
            Modeloo.setCorreo(vista.txtCorreo.getText());
            Modeloo.setContrasena(Modeloo.convertirSHA256(vista.txtContrasena.getText()));
            Modeloo.setEdad(Integer.parseInt(vista.txtEdad.getText()));
            
            if(vista.txtCorreo.getText().isEmpty() || vista.txtNombre.getText().isEmpty() || vista.txtApellidos.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtContrasena.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(vista, "Complete los campos");   
            }
            else
            {
                Modeloo.GuardarUsuario();
            
            JOptionPane.showMessageDialog(vista, "Usuario Guardado");
            Vista.frmLogin.initFrmLogin();
            vista.dispose(); 
            }
        }

        if(e.getSource() == vista.btnIrLogin){
            Vista.frmLogin.initFrmLogin();
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
