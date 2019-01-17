package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import valueObjects.VOProveedor;

public class DAOMantenimientoProveedor {

	public List<String> listarProveedores(){
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		List<String> lstProveedores=null;
		
		Statement stmt=null;
		PreparedStatement pstmt=null;
		
		try {
			stmt = con.createStatement();
			String strConsulta = consultas.listarProveedores();
			pstmt = con.prepareStatement(strConsulta);
			ResultSet rs = pstmt.executeQuery();
			lstProveedores = new ArrayList<String>();
			while (rs.next()) {
				lstProveedores.add(rs.getString("nombre"));
			}
			rs.close();
			stmt.close();
			
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
		return lstProveedores;				
	}
	
	
	public boolean existeProveedor(String nombre) {
		// Retorna true si el nombre del proveedor ya fue ingresado
		AccesoBD accesoBD = new AccesoBD();
			boolean existeProveedor = false;
			Connection con = accesoBD.conectarBD();
			Consultas consultas = new Consultas();
			
			String select = consultas.existeProveedor();
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
	
	public int insertarProveedor(VOProveedor proveedor) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		int rowCount=0;
		String insert = consultas.insertarProveedor();
		PreparedStatement pstmt = null;
		
		try {
			pstmt =con.prepareStatement(insert);
			
			pstmt.setString(1, proveedor.getNombre());
			pstmt.setString(2, proveedor.getDireccion());
			pstmt.setString(3, proveedor.getEmail());
			pstmt.setString(4, proveedor.getTel());
			pstmt.setString(5, proveedor.getUrl());
			pstmt.setString(6, proveedor.getFechaIngreso());

			rowCount = pstmt.executeUpdate();			
		} catch (SQLException e) {
			System.out.println(">> Tipo de datos incorrectos");
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
		return rowCount;
	}
	
	public VOProveedor obtenerDatosProveedor(String nombre) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		VOProveedor voProveedor = null;
		
		PreparedStatement pstmt=null;
		
		try {
			String strConsulta = consultas.obtenerDatosProveedor();
			pstmt = con.prepareStatement(strConsulta);
			pstmt.setString(1, nombre);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			voProveedor = new VOProveedor(nombre, rs.getString("direccion"), rs.getString("email"),rs.getString("tel"), rs.getString("url"), rs.getString("fechaIngreso"));
			rs.close();
		//	stmt.close();			
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
		return voProveedor;		
	}
	
	public int modificarProveedor(String nombre, VOProveedor proveedor) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		int rowCount=0;
		String update = consultas.modificarProveedor();
		PreparedStatement pstmt = null;
		
		try {
			pstmt =con.prepareStatement(update);			
			pstmt.setString(1, proveedor.getNombre());
			pstmt.setString(2, proveedor.getDireccion());
			pstmt.setString(3, proveedor.getEmail());
			pstmt.setString(4, proveedor.getTel());
			pstmt.setString(5, proveedor.getUrl());
			pstmt.setString(6, proveedor.getFechaIngreso());					
			pstmt.setString(7, nombre);			
			rowCount = pstmt.executeUpdate();			
		} catch (SQLException e) {
			System.out.println(">> Tipo de datos incorrectos");
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
		return rowCount;
	}
	
	public int agregarServicioProveedor(String proveedor, String servicio) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		int rowCount=0;
		String insert = consultas.agregarServicioProveedor();
		PreparedStatement pstmt = null;
		
		try {
			pstmt =con.prepareStatement(insert);
			
			pstmt.setString(1, proveedor);
			pstmt.setString(2, servicio);
			
			rowCount = pstmt.executeUpdate();			
		} catch (SQLException e) {
			System.out.println(">> Tipo de datos incorrectos");
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
		return rowCount;
	}
	
	public List<String> obtenerListaServiciosProveedor(String proveedor){
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		List<String> lstServicios = null;
		String strservicios = consultas.obtenerListaServiciosProveedor();
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(strservicios);
			pstmt.setString(1, proveedor);
			
			rs = pstmt.executeQuery();
			
			lstServicios = new ArrayList<String>();
			while(rs.next()) {
				lstServicios.add(rs.getString("s.nombre"));
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
		return lstServicios;
	}
	
	public boolean existeServicioProveedor(String proveedor, String servicio) {
		// Retorna true si el proveedor ya tiene agregado el servicio
		AccesoBD accesoBD = new AccesoBD();
			boolean existeServicioProveedor = false;
			Connection con = accesoBD.conectarBD();
			Consultas consultas = new Consultas();
			
			String select = consultas.existeServicioProveedor();
			try {
				PreparedStatement pstmt = con.prepareStatement(select);
				pstmt.setString(1, proveedor);
				pstmt.setString(2, servicio);
				
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					existeServicioProveedor = true;
				rs.close();
				pstmt.close();
				accesoBD.desconectarBD(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			return existeServicioProveedor;
	}		
	
	public int eliminarServicioProveedor(String proveedor, String servicio) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		int rowCount=0;
		String insert = consultas.eliminarServicioProveedor();
		PreparedStatement pstmt = null;
		
		try {
			pstmt =con.prepareStatement(insert);
			
			pstmt.setString(1, proveedor);
			pstmt.setString(2, servicio);
			
			rowCount = pstmt.executeUpdate();			
		} catch (SQLException e) {
			System.out.println(">> Tipo de datos incorrectos");
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
		return rowCount;
	}
	
	
	//// MATERIALES //////////////////////////////////////////////////////////////////////////////////////////
	
	public int agregarMaterialProveedor(String proveedor, String material) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		int rowCount=0;
		String insert = consultas.agregarMaterialProveedor();
		PreparedStatement pstmt = null;
		
		try {
			pstmt =con.prepareStatement(insert);
			
			pstmt.setString(1, proveedor);
			pstmt.setString(2, material);
			
			rowCount = pstmt.executeUpdate();			
		} catch (SQLException e) {
			System.out.println(">> Tipo de datos incorrectos");
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
		return rowCount;
	}
	
	public List<String> obtenerListaMaterialesProveedor(String proveedor){
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		List<String> lstMateriales = null;
		String strmateriales = consultas.obtenerListaMaterialesProveedor();
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(strmateriales);
			pstmt.setString(1, proveedor);
			
			rs = pstmt.executeQuery();
			
			lstMateriales = new ArrayList<String>();
			while(rs.next()) {
				lstMateriales.add(rs.getString("s.nombre"));
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
		return lstMateriales;
	}
	
	public boolean existeMaterialProveedor(String proveedor, String material) {
		// Retorna true si el proveedor ya tiene agregado el material
		AccesoBD accesoBD = new AccesoBD();
			boolean existeMaterialProveedor = false;
			Connection con = accesoBD.conectarBD();
			Consultas consultas = new Consultas();
			
			String select = consultas.existeMaterialProveedor();
			try {
				PreparedStatement pstmt = con.prepareStatement(select);
				pstmt.setString(1, proveedor);
				pstmt.setString(2, material);
				
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					existeMaterialProveedor = true;
				rs.close();
				pstmt.close();
				accesoBD.desconectarBD(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			return existeMaterialProveedor;
	}		
	
	public int eliminarMaterialProveedor(String proveedor, String material) {
		AccesoBD accesoBD = new AccesoBD();		
		Connection con = accesoBD.conectarBD();
		Consultas consultas = new Consultas();
		
		int rowCount=0;
		String insert = consultas.eliminarMaterialProveedor();
		PreparedStatement pstmt = null;
		
		try {
			pstmt =con.prepareStatement(insert);
			
			pstmt.setString(1, proveedor);
			pstmt.setString(2, material);
			
			rowCount = pstmt.executeUpdate();			
		} catch (SQLException e) {
			System.out.println(">> Tipo de datos incorrectos");
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
		return rowCount;
	}
	
		
}
