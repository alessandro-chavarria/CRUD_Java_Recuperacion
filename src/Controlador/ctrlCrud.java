package Controlador;

import Modelo.TablaBaseDatos;
import Vista.frmCrud;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ctrlCrud implements MouseListener{
    private TablaBaseDatos Modelo;
    private frmCrud Vista;
    
    public ctrlCrud(TablaBaseDatos Modelo, frmCrud Vista){
        this.Modelo = Modelo;
        this.Vista = Vista;
        
        Vista.btnAgregar.addMouseListener(this);
        Vista.btnLimpiar.addMouseListener(this);
        Vista.btnEliminar.addMouseListener(this);
        Vista.btnEditar.addMouseListener(this);
        
        Vista.jtbCrud.addMouseListener(this);
        Modelo.Mostrar(Vista.jtbCrud);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == Vista.btnAgregar)
        {          
            if(Vista.txtNombre.getText().isEmpty() || Vista.txtMarcaCarro.getText().isEmpty() || Vista.txtTelefono.getText().isEmpty() || Vista.txtModeloCarro.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Complete los campos.");
                return;
            }                                                                                                                                                                  
            else{
            Modelo.setNombre(Vista.txtNombre.getText());
            Modelo.setTelefono((Vista.txtTelefono.getText()));
            Modelo.setMarca(Vista.txtMarcaCarro.getText());
            Modelo.setModelo(Vista.txtModeloCarro.getText());
            Modelo.setAno(Integer.parseInt(Vista.txtAnoCarro.getText()));
            Modelo.setProblema(Vista.txtProblema.getText());
            Modelo.setEstado(Vista.txtEstado.getText());
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
        }
        
        if(e.getSource() == Vista.btnEditar)
        {
            Modelo.setNombre(Vista.txtNombre.getText());
            Modelo.setTelefono((Vista.txtTelefono.getText()));
            Modelo.setMarca(Vista.txtMarcaCarro.getText());
            Modelo.setModelo(Vista.txtModeloCarro.getText());
            Modelo.setAno(Integer.parseInt(Vista.txtAnoCarro.getText()));
            Modelo.setProblema(Vista.txtProblema.getText());
            Modelo.setEstado(Vista.txtEstado.getText());
            Modelo.Actualizar(Vista.jtbCrud);
            Clean();
            JOptionPane.showMessageDialog(null,"Se actualizó el dato correctamente.");
            Modelo.Mostrar(Vista.jtbCrud);
        }
    }
    
    public void Clean()
    {
       Vista.txtNombre.setText("");
       Vista.txtMarcaCarro.setText("");
       Vista.txtTelefono.setText("");
       Vista.txtModeloCarro.setText("");
       Vista.txtAnoCarro.setText("");
       Vista.txtProblema.setText("");
       Vista.txtEstado.setText("");
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
