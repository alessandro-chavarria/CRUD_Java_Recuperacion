package Controlador;

import Modelo.TablaBaseDatos;
import Vista.frmCrud;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

//3. Class inheritance that detects actions
public class ctrlCrud implements MouseListener{
    //1. Call the other layers
    private TablaBaseDatos Modelo;
    private frmCrud Vista;
    
    //2. Create the constructor
    public ctrlCrud(TablaBaseDatos Modelo, frmCrud Vista){
        this.Modelo = Modelo;
        this.Vista = Vista;
        
        Vista.btnAgregar.addMouseListener(this);
        Vista.btnLimpiar.addMouseListener(this);
        Vista.btnEliminar.addMouseListener(this);
        Vista.btnEditar.addMouseListener(this);
        
        //Se hará al escribir, osea letra por letra. Entonces, el boton no es necesario
        Vista.jtbCrud.addMouseListener(this);
        Modelo.Mostrar(Vista.jtbCrud);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == Vista.btnAgregar)
        {          
            if(Vista.txtNombre.getText().isEmpty() || Vista.txtPeso.getText().isEmpty() || Vista.txtEdad.getText().isEmpty() || Vista.txtCorreo.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Complete los campos.");
                return;
            }                                                                                                                                                                  
            else{
            Modelo.setNombre(Vista.txtNombre.getText());
            Modelo.setEdad(Integer.parseInt(Vista.txtEdad.getText()));
            Modelo.setPeso(Integer.parseInt(Vista.txtPeso.getText()));
            Modelo.setCorreo(Vista.txtCorreo.getText());
            Modelo.Guardar();
            
            JOptionPane.showMessageDialog(null, "Se registro el dato correctamente.");
            Modelo.Mostrar(Vista.jtbCrud);
            Clean();
            }
        }
        
        if(e.getSource() == Vista.btnLimpiar)
        {
            Clean();
            Modelo.Mostrar(Vista.jtbCrud);
            JOptionPane.showMessageDialog(null, "Se limpiaron los campos.");
        }
        
        if(e.getSource() == Vista.btnEliminar)
        {   
            Modelo.Eliminar(Vista.jtbCrud);
            JOptionPane.showMessageDialog(null, "Se eliminó el registro con éxito.");
            Clean();
            Modelo.Mostrar(Vista.jtbCrud);
        }
        
        if(e.getSource() == Vista.jtbCrud) 
        {
            Modelo.cargarDatosTabla(Vista);
            Vista.btnAgregar.setEnabled(false);
            Vista.btnAgregar.removeMouseListener(this);
        }
        
        if(e.getSource() == Vista.btnEditar)
        {
            Modelo.setNombre(Vista.txtNombre.getText());
            Modelo.setEdad(Integer.parseInt(Vista.txtEdad.getText()));
            Modelo.setPeso(Integer.parseInt(Vista.txtPeso.getText()));
            Modelo.setCorreo(Vista.txtCorreo.getText());
            Modelo.Actualizar(Vista.jtbCrud);
            Clean();
            JOptionPane.showMessageDialog(null,"Se actualizó el dato correctamente.");
            Modelo.Mostrar(Vista.jtbCrud);
        }
    }
    
    public void Clean()
    {
   
       Vista.txtNombre.setText("");
       Vista.txtPeso.setText("");
       Vista.txtEdad.setText("");
       Vista.txtCorreo.setText("");
       Vista.btnAgregar.setEnabled(true);
       Vista.btnAgregar.addMouseListener(this);
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
