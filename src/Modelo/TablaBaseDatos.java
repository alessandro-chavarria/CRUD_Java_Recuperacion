package Modelo;

import Vista.frmCrud;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TablaBaseDatos {

    public String getID() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    private String id;
    private String nombre;
    private int edad;
    private int peso;
    private String correo;
    
    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement nuevoRegistro = conexion.prepareStatement("INSERT INTO TablaBaseDatos (ID, Nombre,Edad,Peso,Correo) VALUES(?,?,?,?,?)");
            //Establecer valores de la consulta SQL
            nuevoRegistro.setString(1,UUID.randomUUID().toString());
            nuevoRegistro.setString(2, getNombre());
            nuevoRegistro.setInt(3, getEdad());
            nuevoRegistro.setInt(4, getPeso());
            nuevoRegistro.setString(5, getCorreo());
            
            //Ejecutar la consulta
            nuevoRegistro.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el Modeloo de la tabla
        DefaultTableModel ModeloDeDatos = new DefaultTableModel();
        
        ModeloDeDatos.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Edad", "Peso","Correo"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM TablaBaseDatos");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el Modeloo por cada vez que recorremos el resultSet
                ModeloDeDatos.addRow(new Object[]{rs.getString("ID"), 
                    rs.getString("NOMBRE"), 
                    rs.getInt("EDAD"), 
                    rs.getString("PESO"),
                    rs.getString("CORREO")});
            }
            //Asignamos el nuevo Modeloo lleno a la tabla
            tabla.setModel(ModeloDeDatos);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String id = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement eliminarRegistro = conexion.prepareStatement("DELETE FROM TablaBaseDatos WHERE ID = ?");
            eliminarRegistro.setString(1, id);
            eliminarRegistro.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void cargarDatosTabla(frmCrud Vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = Vista.jtbCrud.getSelectedRow();
 
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = Vista.jtbCrud.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = Vista.jtbCrud.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = Vista.jtbCrud.getValueAt(filaSeleccionada, 2).toString();
            String pesoDeTB = Vista.jtbCrud.getValueAt(filaSeleccionada, 3).toString();
            String correoDeTB = Vista.jtbCrud.getValueAt(filaSeleccionada, 4).toString();
 
            // Establece los valores en los campos de texto
            Vista.txtNombre.setText(NombreDeTB);
            Vista.txtEdad.setText(EdadDeTb);
            Vista.txtPeso.setText(pesoDeTB);
            Vista.txtCorreo.setText(correoDeTB);
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String ID = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement actualizarRegistro = conexion.prepareStatement("UPDATE TablaBaseDatos SET Nombre = ?, Edad = ?, Peso = ?, Correo = ? WHERE ID = ?");
                actualizarRegistro.setString(1, getNombre());
                actualizarRegistro.setInt(2, getEdad());
                actualizarRegistro.setInt(3, getPeso());
                actualizarRegistro.setString(4, getCorreo());
                actualizarRegistro.setString(5, ID);
                actualizarRegistro.executeUpdate();
                
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        } else {
            System.out.println("Error");
        }
    }
}