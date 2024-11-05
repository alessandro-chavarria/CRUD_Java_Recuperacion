package Modelo;

import Vista.frmCrud;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablaBaseDatos {

    private String id;
    private String nombre;
    private String telefono;
    private String marca;
    private String modelo;
    private int ano;
    private String problema;
    private String estado;
    
    public String getId() {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    public void Guardar() {
        Connection conexion = ClaseConexion.getConexion();
        try {
            PreparedStatement nuevoRegistro = conexion.prepareStatement("INSERT INTO tbTaller (ID, Nombre,Telefono,Marca,Modelo,Ano,Problema,Estado) VALUES(?,?,?,?,?,?,?,?)");
            nuevoRegistro.setString(1,UUID.randomUUID().toString());
            nuevoRegistro.setString(2, getNombre());
            nuevoRegistro.setString(3, getTelefono());
            nuevoRegistro.setString(4, getMarca());
            nuevoRegistro.setString(5, getModelo());
            nuevoRegistro.setInt(6, getAno());
            nuevoRegistro.setString(7, getProblema());
            nuevoRegistro.setString(8, getEstado());
            
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
        
        ModeloDeDatos.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Telefono", "Marca","Modelo","Ano","Problema","Estado"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbTaller");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el Modeloo por cada vez que recorremos el resultSet
                ModeloDeDatos.addRow(new Object[]{rs.getString("ID"), 
                    rs.getString("nombre"), 
                    rs.getString("telefono"), 
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("Ano"),
                    rs.getString("Problema"),
                    rs.getString("Estado")
                });
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
            PreparedStatement eliminarRegistro = conexion.prepareStatement("DELETE FROM tbTaller WHERE ID = ?");
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
            String uuid = Vista.jtbCrud.getValueAt(filaSeleccionada, 0).toString();
            String nombre = Vista.jtbCrud.getValueAt(filaSeleccionada, 1).toString();
            String telefono = Vista.jtbCrud.getValueAt(filaSeleccionada, 2).toString();
            String marca = Vista.jtbCrud.getValueAt(filaSeleccionada, 3).toString();
            String modelo = Vista.jtbCrud.getValueAt(filaSeleccionada, 4).toString();
            String ano = Vista.jtbCrud.getValueAt(filaSeleccionada, 5).toString();
            String problema = Vista.jtbCrud.getValueAt(filaSeleccionada, 6).toString();
            String estado = Vista.jtbCrud.getValueAt(filaSeleccionada, 7).toString();
 
            // Establece los valores en los campos de texto
            Vista.txtNombre.setText(nombre);
            Vista.txtTelefono.setText(telefono);
            Vista.txtMarcaCarro.setText(marca);
            Vista.txtModeloCarro.setText(modelo);
            Vista.txtAnoCarro.setText(ano);
            Vista.txtProblema.setText(problema);
            Vista.txtEstado.setText(estado);
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
                PreparedStatement actualizarRegistro = conexion.prepareStatement("UPDATE tbTaller SET Nombre = ?, Telefono = ?, Marca = ?, Modelo = ? , Ano = ?, Problema = ?, Estado = ? WHERE ID = ?");
            actualizarRegistro.setString(1, getNombre());
            actualizarRegistro.setString(2, getTelefono());
            actualizarRegistro.setString(3, getMarca());
            actualizarRegistro.setString(4, getModelo());
            actualizarRegistro.setInt(5, getAno());
            actualizarRegistro.setString(6, getProblema());
            actualizarRegistro.setString(7, getEstado());
            actualizarRegistro.setString(8,ID);
                
            actualizarRegistro.executeUpdate();
                
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        } else {
            System.out.println("Error");
        }
    }
}