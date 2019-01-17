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
	
	public String modificarCliente() {
		String consulta = "UPDATE mn_cliente SET nombre=?, email=?, telefono=?, celular=?, rut=?, razonSocial=?, tipo=?, direccion=? WHERE nombre=?";
		return consulta;
	}
	
	public String listarTuplasClientes() {
		String consulta = "SELECT nombre, email, telefono, celular, rut, razonSocial, direccion FROM mn_cliente WHERE tipo=? ORDER BY nombre;";
		return consulta;
	}
	
	public String listarServicios() {
		String consulta = "SELECT nombre FROM mn_servicio ORDER BY nombre;";
		return consulta;
	}
	
	public String obtenerDatosServicio() {
		String consulta = "SELECT * FROM mn_servicio WHERE nombre = ?;";
		return consulta;
	}
	
	public String existeServicio() {
		String consulta = "SELECT nombre FROM mn_servicio WHERE nombre = ?;";
		return consulta;
	}
	
	public String insertarServicio() {
		String consulta ="INSERT INTO mn_servicio (nombre, descripcion) VALUES (?,?);";
		return consulta;	
	}
	
	public String modificarServicio() {
		String consulta = "UPDATE mn_servicio SET nombre=?, descripcion=? WHERE nombre=?";
		return consulta;
	}
	
	// Material
	
	public String listarMateriales() {
		String consulta = "SELECT nombre FROM mn_material ORDER BY nombre;";
		return consulta;
	}
	
	public String obtenerDatosMaterial() {
		String consulta = "SELECT * FROM mn_material WHERE nombre = ?;";
		return consulta;
	}
	
	public String existeMaterial() {
		String consulta = "SELECT nombre FROM mn_material WHERE nombre = ?;";
		return consulta;
	}
	
	public String insertarMaterial() {
		String consulta ="INSERT INTO mn_material (nombre, descripcion) VALUES (?,?);";
		return consulta;	
	}
	
	public String modificarMaterial() {
		String consulta = "UPDATE mn_material SET nombre=?, descripcion=? WHERE nombre=?";
		return consulta;
	}
	
	// PROVEEDOR
	public String insertarProveedor() {
		String consulta ="INSERT INTO mn_proveedor (nombre, direccion, email, tel, url, fechaIngreso) VALUES (?,?,?,?,?,?);";
		return consulta;	
	}
	

	public String listarProveedores() {
		String consulta = "SELECT nombre FROM mn_proveedor ORDER BY nombre;";
		return consulta;
	}
	
	public String obtenerDatosProveedor() {
		String consulta = "SELECT * FROM mn_proveedor WHERE nombre = ?;";
		return consulta;
	}
	
	public String existeProveedor() {
		String consulta = "SELECT nombre FROM mn_proveedor WHERE nombre = ?;";
		return consulta;
	}
	
	public String modificarProveedor() {
		String consulta = "UPDATE mn_proveedor SET nombre=?, direccion=?, email=?, tel=?, url=?, fechaIngreso=? WHERE nombre=?";
		return consulta;
	}
	
	public String agregarServicioProveedor() {
		String consulta = "INSERT INTO mn_proveedor_servicio (idProveedor, idServicio) \n" + 
				"VALUES ((SELECT idProveedor FROM mn_proveedor WHERE nombre=?), (SELECT idServicio FROM mn_servicio WHERE nombre=?));";
		return consulta;
	}
	
	public String obtenerListaServiciosProveedor() {
		String consulta = "SELECT s.nombre \n" + 
				"FROM mn_servicio s\n" + 
				"INNER JOIN mn_proveedor_servicio ps ON ps.idServicio = s.idServicio\n" + 
				"INNER JOIN mn_proveedor p ON ps.idProveedor = p.idProveedor\n" + 
				"WHERE p.nombre =?;";
		return consulta;
	}
	
	public String existeServicioProveedor() {
		String consulta = "SELECT * FROM mn_proveedor_servicio \n" + 
				"WHERE idProveedor IN (SELECT p.idProveedor FROM mn_proveedor p WHERE p.nombre=?)\n" + 
				"AND idServicio IN (SELECT s.idServicio FROM mn_servicio s  WHERE s.nombre=?);";
		return consulta;
	}
	
	public String eliminarServicioProveedor() {
		String consulta ="DELETE FROM mn_proveedor_servicio\n" + 
				"WHERE idProveedor IN (SELECT p.idProveedor FROM mn_proveedor p WHERE p.nombre=?)\n" + 
				" AND idServicio IN (SELECT s.idServicio FROM mn_servicio s  WHERE s.nombre=?);";
		return consulta;
	}
	
	/// MATERIALES //////////////////////////////////////////////////////
	
	public String agregarMaterialProveedor() {
		String consulta = "INSERT INTO mn_proveedor_material (idProveedor, idMaterial) \n" + 
				"VALUES ((SELECT idProveedor FROM mn_proveedor WHERE nombre=?), (SELECT idMaterial FROM mn_material WHERE nombre=?));";
		return consulta;
	}
	
	public String obtenerListaMaterialesProveedor() {
		String consulta = "SELECT s.nombre \n" + 
				"FROM mn_material s\n" + 
				"INNER JOIN mn_proveedor_material ps ON ps.idMaterial = s.idMaterial\n" + 
				"INNER JOIN mn_proveedor p ON ps.idProveedor = p.idProveedor\n" + 
				"WHERE p.nombre =?;";
		return consulta;
	}
	
	public String existeMaterialProveedor() {
		String consulta = "SELECT * FROM mn_proveedor_material \n" + 
				"WHERE idProveedor IN (SELECT p.idProveedor FROM mn_proveedor p WHERE p.nombre=?)\n" + 
				"AND idMaterial IN (SELECT s.idMaterial FROM mn_material s  WHERE s.nombre=?);";
		return consulta;
	}
	
	public String eliminarMaterialProveedor() {
		String consulta ="DELETE FROM mn_proveedor_material\n" + 
				"WHERE idProveedor IN (SELECT p.idProveedor FROM mn_proveedor p WHERE p.nombre=?)\n" + 
				" AND idMaterial IN (SELECT s.idMaterial FROM mn_material s  WHERE s.nombre=?);";
		return consulta;
	}
	
	
	
}
