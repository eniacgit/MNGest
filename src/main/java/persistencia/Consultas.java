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
	
}
