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

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

   

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

  

    private String ID_Usuario;
    private String Nombre;
    private String Apellidos;
    private String Correo;
    private String Contrasena;
    private int Edad;

    
       public void GuardarUsuario() {
        
        Connection conexion = ClaseConexion.getConexion();
        try {
            
            PreparedStatement agregarUsuario = conexion.prepareStatement("INSERT INTO tbUsuario(ID, Nombre, Apellidos, Correo, Contrasena, Edad) VALUES (?, ?, ?, ?, ?, ?)");
            agregarUsuario.setString(1, UUID.randomUUID().toString());
            agregarUsuario.setString(2, getNombre());
            agregarUsuario.setString(3, getApellidos());
            agregarUsuario.setString(4, getCorreo());
            agregarUsuario.setString(5, getContrasena());
            agregarUsuario.setInt(6, getEdad());
 
            
            agregarUsuario.executeUpdate();
 
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
            String sql = "SELECT * FROM tbUsuario WHERE Correo = ? AND Contrasena = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, getNombre());
            statement.setString(2, getContrasena());

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
