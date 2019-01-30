package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import valueObjects.VOCliente;
import valueObjects.VODetalleProveedor;
import valueObjects.VODetallesProducto;


public class DAOPagoProveedores {

	public List<String> listarClientesRangoFechaProduccion(String fechaInicio, String fechaFin){
		List<String> listaClientes =null;
		
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		listaClientes = new ArrayList<String>();
		String listar = consultas.listarClientesRangoFechaProduccion();
		
		try {
			pstmt =con.prepareStatement(listar);
			pstmt.setString(1, fechaInicio);
			pstmt.setString(2, fechaFin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listaClientes.add(rs.getString("CONCAT(p.cotizacion, ' : ', c.nombre)"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return listaClientes;
	}
	
	public String obtenerTipoCliente(String nombreCliente) {
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		VOCliente cliente = dao.obtenerDatosCliente(nombreCliente);
		return cliente.getTipo();
	}
	
	public VODetallesProducto obtenerDetallesProducto(String cotizacion) {
		VODetallesProducto producto = null;
		
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		String strConsulta = consultas.obtenerDetallesProducto();
		PreparedStatement pstmt=null;
		
		try {
			pstmt = con.prepareStatement(strConsulta);
			pstmt.setString(1, cotizacion);
			ResultSet rs = pstmt.executeQuery();
			rs.next();			
			producto =  new VODetallesProducto(rs.getString("pro.nombre"), rs.getString("cat.nombre"), rs.getString("pMN.cantidad"), rs.getString("pMN.fechaProduccion"), rs.getString("pMN.moneda"), rs.getString("pMN.total"));
			rs.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				pstmt.close();
				accesoBD.desconectarBD(con);
			} catch (SQLException e) {
				System.out.println(">> ERROR 2");
				e.printStackTrace();
			}			
		}
		
		return producto;
	}
	
	public List<VODetalleProveedor> obtenerListaProveedores(String cotizacion){
		VODetalleProveedor proveedor = null;
		List<VODetalleProveedor> listaProveedores=null;
		
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		listaProveedores = new ArrayList<VODetalleProveedor>();
		String listar = consultas.obtenerListaProveedores();
		
		try {
			pstmt =con.prepareStatement(listar);
			pstmt.setString(1, cotizacion);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				proveedor = new VODetalleProveedor(rs.getString("pro.tipoProveedor"), rs.getString("pro.detalles"), rs.getString("pro.moneda"), rs.getString("pro.precio"));
				listaProveedores.add(proveedor);
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				pstmt.close();
				accesoBD.desconectarBD(con);
			} catch (SQLException e) {
				System.out.println(">> ERROR 2");
				e.printStackTrace();
			}			
		}
	
		return listaProveedores;
	}
 
}
