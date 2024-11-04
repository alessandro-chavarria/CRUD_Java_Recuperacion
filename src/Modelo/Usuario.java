package Modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.UUID;

public class Usuario {

    public String getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(String ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    private String ID_Usuario;
    private String Nombre;
    private String Contraseña;



    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }
    
    
       public void GuardarUsuario() {
        
        Connection conexion = ClaseConexion.getConexion();
        try {
            
            PreparedStatement addPelicula = conexion.prepareStatement("INSERT INTO tbUsuario(ID, Nombre, Contrasena) VALUES (?, ?, ?)");
            
            addPelicula.setString(1, UUID.randomUUID().toString());
            addPelicula.setString(2, getNombre());
            addPelicula.setString(3, getContraseña());
 
            
            addPelicula.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el Modeloo:metodo guardar " + ex);
        }
    }
       
       //El método devuelve un valor booleano (verdadero o falso)
       //Verdadero si existe el usuario y Falso si no existe
       public boolean iniciarSesion() {
        //Obtenemos la conexión a la base de datos
        Connection conexion = ClaseConexion.getConexion();
        boolean resultado = false;

        try {
            String sql = "SELECT * FROM tbUsuario WHERE Nombre = ? AND Contrasena = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, getNombre());
            statement.setString(2, getContraseña());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultado = true;
            }

        } catch (SQLException ex) {
            System.out.println("Error en el Modeloo: método iniciarSesion " + ex);
        }

        return resultado;
    }
       
       public String convertirSHA256(String password) {
	MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("SHA-256");
	}
	catch (NoSuchAlgorithmException e) {
		System.out.println(e.toString());
		return null;
	}
	byte[] hash = md.digest(password.getBytes());
	StringBuffer sb = new StringBuffer();
 
	for(byte b : hash) {
		sb.append(String.format("%02x", b));
	}
 
	return sb.toString();
}
       
    
    
}
