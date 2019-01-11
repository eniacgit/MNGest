package persistencia;

public class Consultas {
	
	public String listarClientes() {
		String consulta = "SELECT nombre FROM mn_cliente ORDER BY nombre;";
		return consulta;		
	}
	
	public String insertarCliente() {
		String consulta ="INSERT INTO mn_cliente (nombre, email, telefono, celular,rut,razonSocial,tipo,direccion) VALUES (?,?,?,?,?,?,?,?);";
		return consulta;	
	}
	
	public String obtenerDatosCliente() {
		String consulta = "SELECT * FROM mn_cliente WHERE nombre = ?;";
		return consulta;
	}
	
	public String existeCliente() {
		String consulta = "SELECT nombre FROM mn_cliente WHERE nombre = ?;";
		return consulta;
	}
	
}
