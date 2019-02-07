package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import valueObjects.VOVenta;


public class DAOCorreoPosVenta {

	// Retorna una lista de todos los clientes (segun su tipo) que hicieron compras entre 2 fechas dadas
	public List<String> listaClientesFechaEntrega(String fechaInicio, String fechaFin, String tipoCliente){
		List<String> listaClientes =null;
		
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		listaClientes = new ArrayList<String>();
		String lista = consultas.listaClientesFechaEntrega();
				
		try {
			pstmt =con.prepareStatement(lista);
			pstmt.setString(1, tipoCliente);
			pstmt.setString(2, fechaInicio);
			pstmt.setString(3, fechaFin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listaClientes.add(rs.getString("c.nombre"));
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
	
	// Retorna el mail con el que se enviarán los presupuestos 
	// Precondicion: en la tabla mn_email solo existe una tupla con id=1
	public String obtenerRemitente() {
		int id = 1;
		String remitente="";
		
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		String select = consultas.obtenerRemitente();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(select);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			remitente = rs.getString(1);
			
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
		return remitente;
	}
	// A partir del nombre del cliente y un rango de fechas retorna la lista de productos que compro
	public List<VOVenta> listarTuplasVentas(String fechaInicio, String fechaFin, String nombreCliente){
		VOVenta venta = null;
		List<VOVenta> tuplas = null;
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		tuplas = new ArrayList<VOVenta>();		
		
		try {
			String listar = consultas.obtenerListaProductosVenta();
			pstmt =con.prepareStatement(listar);
			pstmt.setString(1, nombreCliente);
			pstmt.setString(2, fechaInicio);
			pstmt.setString(3, fechaFin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				venta = new VOVenta(rs.getString("producto"), rs.getString("categoria"), rs.getString("unidades"), rs.getString("fecha"), rs.getInt("total"));								
				tuplas.add(venta);
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
		return tuplas;
	}
	
	
	
	
	/*
	
	public String obtenerPasswordRemitente() {
		// Retorna el password del remitente con el que se enviarán los presupuestos 
		// Precondicion: en la tabla mn_email solo existe una tupla con id=1
		int id = 1;
		String password="";
		Connection con = this.conectarBD();
		Consultas consultas = new Consultas();
		String select = consultas.obtenerPasswordRemitente();
		try {
			PreparedStatement pstmt = con.prepareStatement(select);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			password = rs.getString(1);
			rs.close();
			pstmt.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.desconectarBD(con);
		return password;
	}
	 */
}
