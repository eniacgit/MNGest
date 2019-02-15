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
	
	public String listarTuplasVentas() {
		String consulta ="SELECT p.cotizacion, c.nombre, c.tipo, pMN.nombre, cat.nombre, pMN.cantidad, pMN.moneda, pMN.total, pMN.fechaProduccion,pMN.fechaEntrega\n" + 
				"FROM mn_presupuesto p\n" + 
				"INNER JOIN mn_cliente_presupuesto cp ON cp.idPresupuesto = p.idPresupuesto\n" + 
				"INNER JOIN mn_cliente c ON c.idCliente = cp.idCliente\n" + 
				"INNER JOIN mn_productoMN pMN ON pMN.idClientePresupuesto = cp.idClientePresupuesto\n" + 
				"INNER JOIN mn_producto pd ON pd.idPresupuesto = p.idPresupuesto\n" + 
				"INNER JOIN mn_categoria cat ON cat.idCategoria =  pd.idCategoria\n" + 
				"WHERE cp.estado=4 AND pMN.fechaEntrega BETWEEN ? AND ? ORDER BY p.cotizacion;";
		return consulta;
	}
	
	// PAGO PROVEEDORES
	
	public String listarClientesRangoFechaProduccion() {
		String consulta = "SELECT CONCAT(p.cotizacion, ' : ', c.nombre)\n" + 
				"FROM mn_cliente c\n" + 
				"INNER JOIN mn_cliente_presupuesto cp ON cp.idCliente = c.idCliente\n" + 
				"INNER JOIN mn_productoMN pmn ON pmn.idClientePresupuesto = cp.idClientePresupuesto\n" + 
				"INNER JOIN mn_presupuesto p ON p.idPresupuesto =cp.idPresupuesto\n" + 
				"WHERE cp.estado = 3 AND pmn.fechaProduccion BETWEEN ? AND ? \n" + 
				"ORDER BY c.nombre;";
		return consulta;
	}
	
	public String obtenerDetallesProducto() {
		String consulta = "SELECT pro.nombre, cat.nombre, pMN.cantidad, pMN.moneda, pMN.total, pMN.fechaProduccion\n" + 
				"FROM mn_producto pro\n" + 
				"INNER JOIN mn_presupuesto pre ON pre.idPresupuesto = pro.idPresupuesto\n" + 
				"INNER JOIN mn_categoria cat ON cat.idCategoria = pro.idCategoria\n" + 
				"INNER JOIN mn_cliente_presupuesto cp ON cp.idPresupuesto = pre.idPresupuesto\n" + 
				"INNER JOIN mn_productoMN pMN ON pMN.idClientePresupuesto = cp.idClientePresupuesto\n" + 
				"WHERE pre.cotizacion = ?;";
		return consulta;
	}
	
	public String obtenerListaProveedores() {
		String consulta = "SELECT pro.tipoProveedor, pro.detalles, pro.moneda, pro.precio\n" + 
				"FROM mn_proveedorMN pro\n" + 
				"INNER JOIN mn_productoMN_proveedorMN propro ON propro.idProveedorMN = pro.idProveedorMN\n" + 
				"INNER JOIN mn_productoMN pMN ON pMN.idProductoMN = propro.idProductoMN\n" + 
				"INNER JOIN mn_cliente_presupuesto cp ON cp.idClientePresupuesto = pMN.idClientePresupuesto\n" + 
				"INNER JOIN mn_presupuesto p ON p.idPresupuesto = cp.idPresupuesto\n" + 
				"WHERE p.cotizacion = ?;";
		return consulta;
	}
	
	public String obtenerListaProductosVenta() {
		String consulta ="SELECT pmn.fechaEntrega AS fecha, p.nombre AS producto, c.nombre AS categoria, pres.unidades AS unidades, pres.costo AS total\n" + 
				"FROM mn_producto p\n" + 
				"INNER JOIN mn_categoria c ON c.idCategoria = p.idCategoria\n" + 
				"INNER JOIN mn_cliente_presupuesto cp ON cp.idPresupuesto = p.idPresupuesto\n" + 
				"INNER JOIN mn_productoMN pmn ON pmn.idClientePresupuesto = cp.idClientePresupuesto\n" + 
				"INNER JOIN mn_presupuesto pres ON pres.idPresupuesto = cp.idPresupuesto\n" + 
				"INNER JOIN mn_cliente cli ON cli.idCliente = cp.idCliente\n" + 
				"WHERE cp.estado=4 AND cli.nombre = ? AND pmn.fechaEntrega BETWEEN ? AND ?;";
		return consulta;
	}
	
	public String listaClientesFechaEntrega() {
		String consulta ="SELECT DISTINCT(c.nombre) AS nombre\n" +
				"FROM mn_cliente c\n" + 
				"INNER JOIN mn_cliente_presupuesto cp ON cp.idCliente= c.idCliente\n" + 
				"INNER JOIN mn_productoMN pmn ON pmn.idClientePresupuesto = cp.idClientePresupuesto\n" + 
				"WHERE cp.estado=4 AND c.tipo = ? AND pmn.fechaEntrega BETWEEN ? AND ?;";
		return consulta;
	}
	
	public String obtenerRemitente() {
		String consulta ="select email from mn_email where id=?;";
		return consulta;
	}
	
	public String obtenerPasswordRemitente() {
		String consulta ="select password from mn_email where id=?;";
		return consulta;
	}
	
	public String obtenerPassWordSHA512() {
		String consulta ="SELECT PWD_ FROM ACT_ID_USER WHERE ID_= ?;";
		return consulta;
	}
	
	public String existeUsuarioLogin() {
		String consulta ="SELECT * FROM ACT_ID_USER WHERE ID_= ?";
		return consulta;
	}
	
}
