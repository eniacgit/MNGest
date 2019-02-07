package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import persistencia.AccesoBD;
import persistencia.DAOCorreoPosVenta;
import persistencia.DAOMantenimientoClientes;
import persistencia.DAOMantenimientoMaterial;
import persistencia.DAOMantenimientoProveedor;
import persistencia.DAOMantenimientoServicio;
import persistencia.DAOPagoProveedores;
import persistencia.DAOVenta;
import valueObjects.VOArchivoAdjunto;
import valueObjects.VOCliente;
import valueObjects.VODetalleProveedor;
import valueObjects.VODetallesProducto;
import valueObjects.VOMaterial;
import valueObjects.VOProveedor;
import valueObjects.VOServicio;
import valueObjects.VOVenta;
import valueObjects.VOEmail;

public class Fachada implements IFachada{

	private static IFachada fachada;
	
	private Fachada(){
		
	}
	
	public static IFachada getInstanciaSingleton() {
		if (fachada == null) {
			fachada = new Fachada();
		}
		return fachada;
	}
	
	public List<String> listarClientes(){
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		return dao.listarClientes();
	}
	
	public VOCliente obtenerDatosCliente(String nombre) {
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		return dao.obtenerDatosCliente(nombre);
	}
	
	public int insertarCliente(VOCliente cliente) {
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		return dao.insertarCliente(cliente);
	}
	public boolean existeCliente(String nombre) {
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		return dao.existeCliente(nombre);
	}
	
	public int modificarCliente(String nombre, VOCliente cliente) {
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		return dao.modificarCliente(nombre, cliente);
	}
	
	public List<VOCliente> listarTuplasClientes(String tipoCliente){
		DAOMantenimientoClientes dao = new DAOMantenimientoClientes();
		return dao.listarTuplasClientes(tipoCliente);
	}
	
	//Mantenimiento de servicios

	public List<String> listarServicio(){
		DAOMantenimientoServicio dao = new DAOMantenimientoServicio();
		return dao.listarServicio();
	}
	
	public VOServicio obtenerDatosServicio(String nombre) {
		DAOMantenimientoServicio dao = new DAOMantenimientoServicio();
		return dao.obtenerDatosServicio(nombre);
	}
	
	public boolean existeServicio(String nombre) {
		DAOMantenimientoServicio dao = new DAOMantenimientoServicio();
		return dao.existeServicio(nombre);
	}

	public int insertarServicio(VOServicio servicio) {
		DAOMantenimientoServicio dao = new DAOMantenimientoServicio();
		return dao.insertarServicio(servicio);
	}

	public int modificarServicio(String nombre, VOServicio servicio) {
		DAOMantenimientoServicio dao = new DAOMantenimientoServicio();
		return dao.modificarServicio(nombre, servicio);
	}
	
	//Mantenimiento de materiales
		
	public List<String> listarMateriales(){
		DAOMantenimientoMaterial dao = new DAOMantenimientoMaterial();
		return dao.listarMateriales();
	}
	
	public VOMaterial obtenerDatosMaterial(String nombre) {
		DAOMantenimientoMaterial dao = new DAOMantenimientoMaterial();
		return dao.obtenerDatosMaterial(nombre);
	}
	
	public boolean existeMaterial(String nombre) {
		DAOMantenimientoMaterial dao = new DAOMantenimientoMaterial();
		return dao.existeMaterial(nombre);
	}
	
	public int insertarMaterial(VOMaterial material) {
		DAOMantenimientoMaterial dao = new DAOMantenimientoMaterial();
		return dao.insertarMaterial(material);
	}
	
	public int modificarMaterial(String nombre, VOMaterial material) {
		DAOMantenimientoMaterial dao = new DAOMantenimientoMaterial();
		return dao.modificarMaterial(nombre, material);
	}
	
	// Mantenimiento de proveedores
	
	public List<String> listarProveedores(){
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.listarProveedores();
	}
	
	public boolean existeProveedor(String nombre) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.existeProveedor(nombre);
	}
	
	public int insertarProveedor(VOProveedor proveedor) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.insertarProveedor(proveedor);
	}
	
	public VOProveedor obtenerDatosProveedor(String nombre) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.obtenerDatosProveedor(nombre);
	}
	
	public int modificarProveedor(String nombre, VOProveedor proveedor) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.modificarProveedor(nombre, proveedor);
	}	
	
	public int agregarServicioProveedor(String proveedor, String servicio) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.agregarServicioProveedor(proveedor, servicio);
	}
	
	public List<String> obtenerListaServiciosProveedor(String proveedor){
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.obtenerListaServiciosProveedor(proveedor);
	}
	public boolean existeServicioProveedor(String proveedor, String servicio) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.existeServicioProveedor(proveedor, servicio);
	}
	
	public int eliminarServicioProveedor(String proveedor, String servicio) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.eliminarServicioProveedor(proveedor, servicio);
	}
	
	
	/// MATERIALES //////////////////////////////////
	public int agregarMaterialProveedor(String proveedor, String material) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.agregarMaterialProveedor(proveedor, material);
	}
	
	public List<String> obtenerListaMaterialesProveedor(String proveedor){
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.obtenerListaMaterialesProveedor(proveedor);
	}
	
	public boolean existeMaterialProveedor(String proveedor, String material) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.existeMaterialProveedor(proveedor, material);
	}
	
	public int eliminarMaterialProveedor(String proveedor, String material) {
		DAOMantenimientoProveedor dao = new DAOMantenimientoProveedor();
		return dao.eliminarMaterialProveedor(proveedor, material);
	}
	
	// VENTAS
	public List<VOVenta> listarTuplasVentas(String fechaInicio, String fechaFin){
		DAOVenta dao = new DAOVenta();
		return dao.listarTuplasVentas(fechaInicio,fechaFin);
	}
	
	public List<String> listarClientesRangoFechaProduccion(String fechaInicio, String fechaFin){
		DAOPagoProveedores dao = new DAOPagoProveedores();
		return dao.listarClientesRangoFechaProduccion(fechaInicio, fechaFin);
	}
	
	// FUNCIONES UTILES
	// Convierte una fecha con formato yyyy-MM-dd a dd/MM/yyyy
	public String formatearFecha(String fecha) {
		String fechAux="";		
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			fechAux = sdf2.format(sdf1.parse(fecha));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fechAux;
	}
	
	public String obtenerTipoCliente(String nombreCliente) {
		DAOPagoProveedores dao = new DAOPagoProveedores();
		return dao.obtenerTipoCliente(nombreCliente);
	}
	
	public VODetallesProducto obtenerDetallesProducto(String cotizacion) {
		DAOPagoProveedores dao = new DAOPagoProveedores();
		return dao.obtenerDetallesProducto(cotizacion);
	}
	
	public List<VODetalleProveedor> obtenerListaProveedores(String cotizacion){
		DAOPagoProveedores dao = new DAOPagoProveedores();
		return dao.obtenerListaProveedores(cotizacion);
	}
	
	public List<String> listaClientesFechaEntrega(String fechaInicio, String fechaFin, String tipoCliente){
		DAOCorreoPosVenta dao = new DAOCorreoPosVenta();
		return dao.listaClientesFechaEntrega(fechaInicio, fechaFin, tipoCliente);
	}
	
	public String obtenerRemitente() {
		DAOCorreoPosVenta dao = new DAOCorreoPosVenta();
		return dao.obtenerRemitente();
	}
	
	
	// A partir del nombre del cliente y un rango de fechas retorna la lista de productos que compro
	public List<VOVenta> listarTuplasVentas(String fechaInicio, String fechaFin, String nombreCliente){
		DAOCorreoPosVenta dao = new DAOCorreoPosVenta();
		return dao.listarTuplasVentas(fechaInicio, fechaFin, nombreCliente);
	}
	
	private static void addAtachment(Multipart multipart, String rutaArcvhivo, String nombreArchivo) throws MessagingException {
		DataSource source = new FileDataSource(rutaArcvhivo+nombreArchivo);
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(nombreArchivo);
		multipart.addBodyPart(messageBodyPart);
	}
	
	public void enviarConGmail (VOEmail voEmail) throws MessagingException {
		// Envia un correo electronico con archivos adjuntos (ArrayList) utilizando el email y contrasenia
		// almacenados en la tabla mn_email
			//String remitente = voEmail.getRemitente(); // este campo viene vacio
			String destinatario = voEmail.getDestinatario();
			String asunto = voEmail.getAsunto();
			String cuerpo = voEmail.getCuerpo();
			ArrayList<VOArchivoAdjunto> lstArchivosAdjuntos = voEmail.getLstArchivosAdjuntos();
			
			AccesoBD accesoBD = new AccesoBD();
			String email = obtenerRemitente();
			String [] arrayEmail = email.split("@");
			String remitente = arrayEmail[0];
			String clave = accesoBD.obtenerPasswordRemitente();			
			
			// Se obtiene el objeto Session. La configuración es para una cuenta de gmail
			Properties props = new Properties();		
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.user", "remitente");
			props.put("mail.smtp.clave", clave);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "587");
			
			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);
			message.addRecipients(Message.RecipientType.TO, destinatario);
			message.setSubject(asunto);
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(cuerpo);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			
			
			if (lstArchivosAdjuntos !=null ) {
				// obtengo archivos adjuntos de la lista
				for (int i=0; i<lstArchivosAdjuntos.size();i++) {
					String rutaArchivo = lstArchivosAdjuntos.get(i).getRutaArchivoAdjunto();
					String nombreArchivo = lstArchivosAdjuntos.get(i).getNombreArchivoAdjunto();
					addAtachment(multipart, rutaArchivo, nombreArchivo);
				}
			}
			message.setContent(multipart);
			
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, clave);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();		
		}
	
	
}
