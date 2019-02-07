package persistencia;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAOPantallaLogin {

	public static String encriptarSHA512(String input) { 
        try { 
            // getInstance() para llamar al metodo con el algoritmo de encriptacion SHA-512 
            MessageDigest md = MessageDigest.getInstance("SHA-512"); 
  
            // digest() llama al metodo
            // para calcular el mensaje digest del string de entrada
            // returna un array ode bytes 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convierte el array 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convierte el array a hexadecimal 
            String hashtext = no.toString(16); 
  
            // Se genera en 32bits 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // retorna el HashText 
            return hashtext; 
        } 
  
        // para especificar mensaajes de error digest en el algoritmo 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
	
	/*public String obtenerPassSHA512(String usuario) {
		String passSHA512="";
		
		
		return passSHA512;
	}*/
	
	public String obtenerPassSHA512(String usuario) {
		AccesoBD accesoBD = new AccesoBD();
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		String select = consultas.obtenerPassWordSHA512();
		String passSHA512 = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, usuario);
			rs = pstmt.executeQuery();
			rs.next();
			passSHA512 = rs.getString(1);			
		} catch (SQLException e) {
			System.out.println(">> Tipo de datos incorrectos");
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				pstmt.close();
				accesoBD.desconectarBD(con);
			} catch (SQLException e) {
				System.out.println(">> ERROR 2");
				e.printStackTrace();
			}			
		}						
		return passSHA512;
	}	
	
	
	// Retorna true si el nombre del usuario que se loguea existe en la bd	
	public boolean existeUsuarioLogin(String nombre) {
		AccesoBD accesoBD = new AccesoBD();
			boolean existeProveedor = false;
			Connection con = accesoBD.conectarBD();
			Consultas consultas = new Consultas();
			
			String select = consultas.existeUsuarioLogin();
			try {
				PreparedStatement pstmt = con.prepareStatement(select);
				pstmt.setString(1, nombre);
				
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					existeProveedor = true;
				rs.close();
				pstmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			accesoBD.desconectarBD(con);
			return existeProveedor;
		}	
	
	
	public String obtenerPassWordSHA512(String usuario) {
		AccesoBD accesoBD = new AccesoBD();
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		String select = consultas.obtenerPassWordSHA512();
		String passSHA512 = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, usuario);
			rs = pstmt.executeQuery();
			rs.next();
			passSHA512 = rs.getString(1);			
		} catch (SQLException e) {
			System.out.println(">> Tipo de datos incorrectos");
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				pstmt.close();
				accesoBD.desconectarBD(con);
			} catch (SQLException e) {
				System.out.println(">> ERROR 2");
				e.printStackTrace();
			}			
		}						
		return passSHA512;
	}
	
	
	
}
