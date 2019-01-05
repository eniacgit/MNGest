package persistencia;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.jfree.util.Log;

public class AccesoBD {
	private String driver;
	private String username;
	private String pass;
	private String url;
	private String base;
	
	public Connection conectarBD() {
		// Carga los datos desde el archivo de configuracion
		// y se conecta a la base del servidor
		Connection con = null;		
		
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("config/parametros.txt"));
			driver = p.getProperty("driver");
			Class.forName(driver);
			//System.out.println("driver: " + driver);
			
			username = p.getProperty("usuario");
			pass = p.getProperty("password");
			url = p.getProperty("url");
			base = p.getProperty("bdatos");
			
			con = DriverManager.getConnection(url + base, username, pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return con;
	}
	
	public void desconectarBD(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.error(e);
			System.out.println(">> ERROR 3");
			e.printStackTrace();
		}
	}
}
