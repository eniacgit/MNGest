package grafica;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.lowagie.text.Font;
import com.toedter.calendar.JDateChooser;

import controladores.ControladorCorreoPosVenta;
import controladores.ControladorMantenimientoClientes;
import controladores.ControladorPagoProveedores;
import controladores.ControladorVentas;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.xml.JRFontFactory.TextElementFontFactory;
import net.sf.jasperreports.view.JasperViewer;
import valueObjects.VOArchivoAdjunto;
import valueObjects.VOCliente;
import valueObjects.VODetalleProveedor;
import valueObjects.VODetallesProducto;
import valueObjects.VOEmail;
import valueObjects.VOTablaProveedores;
import valueObjects.VOVenta;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.mail.MessagingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

public class CorreoPosventa extends JInternalFrame {
	private JTable tablaProductos;
	private DefaultTableModel modeloTabla;
    private JDateChooser dateChooserInicio;
    private JDateChooser dateChooserFin;
    private JList<String> listaClientes;
    private JTextField textFieldCliente;
    private JTextField textFieldTipoCliente;
    private JTextField textFieldTel;
    private JTextField textFieldAsunto;
    private JTextField textFieldPara;
    private JTextField textFieldDe;
    private JComboBox comboBoxTipoCliente;
    private JTextArea textAreaCuerpo;
    private JTextField textFieldArchivo;
    private JButton btnAdjuntar;
    
    private static String rutaArchivoAdjunto = "";
    private static String nombreArchivoAdjunto = "";
    private static int cont = 0; // variable para controlar el estado del boton de adjuntar archivo
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CorreoPosventa frame = new CorreoPosventa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CorreoPosventa() {
		setClosable(true);
		setTitle("Correo Posventa");
		setBounds(100, 100, 950, 650);
		getContentPane().setLayout(null);
		
		// COLUMNAS DE LA TABLA VENTAS
		modeloTabla = new DefaultTableModel();
		
		
		modeloTabla.addColumn("Fecha entrega");
		modeloTabla.addColumn("Producto");
		modeloTabla.addColumn("Categoría");
		modeloTabla.addColumn("Unidades");
		modeloTabla.addColumn("Total (USD)");
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CorreoPosventa.this.dispose();
			}
		});
		btnSalir.setBounds(90, 581, 117, 25);
		getContentPane().add(btnSalir);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Rango de fechas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 23, 315, 179);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		dateChooserInicio = new JDateChooser();
		dateChooserInicio.setBounds(12, 43, 130, 25);
		panel.add(dateChooserInicio);
		dateChooserInicio.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooserInicio.setBackground(SystemColor.window);
		
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
		lblInicio.setBounds(12, 22, 70, 15);
		panel.add(lblInicio);
		
		dateChooserFin = new JDateChooser();
		dateChooserFin.setBounds(167, 43, 130, 25);
		panel.add(dateChooserFin);
		dateChooserFin.setBackground(new Color(255, 255, 204));
		
		JLabel lblFin = new JLabel("Fin");
		lblFin.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
		lblFin.setBounds(167, 22, 70, 15);
		panel.add(lblFin);
		
		JButton btnNewButton = new JButton("Consultar ventas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldCliente.setText("");
				textFieldTipoCliente.setText("");
				textFieldTel.setText("");
				textFieldDe.setText("");
				textFieldPara.setText("");
				textFieldAsunto.setText("");
				textAreaCuerpo.setText("");
				
				if ((dateChooserInicio.getCalendar() == null) || (dateChooserFin.getCalendar() == null)) {
					JOptionPane.showMessageDialog(getContentPane(), "Los campos de fecha no pueden quedar en blanco.", "Error en el rango de fechas.", JOptionPane.ERROR_MESSAGE);
				}else{
					String fechaInicio = dateChooserToFechaString(dateChooserInicio);
					String fechaFin = dateChooserToFechaString(dateChooserFin);
					String tipoCliente = (String) comboBoxTipoCliente.getSelectedItem();
					
					if (esMayorOIgualFecha(fechaInicio, fechaFin)) {
						JOptionPane.showMessageDialog(getContentPane(), "La fecha de inicio debe ser menor o igual que la final.", "Error en el rango de fechas.", JOptionPane.ERROR_MESSAGE);						
					}else {
						listarClientes(fechaInicio, fechaFin, tipoCliente);
					}
					
				}
				
				
			}
		});
		btnNewButton.setBounds(12, 142, 285, 25);
		panel.add(btnNewButton);
		
		comboBoxTipoCliente = new JComboBox();
		comboBoxTipoCliente.setModel(new DefaultComboBoxModel(new String[] {"CONSUMIDOR FINAL", "CORPORATIVO"}));
		comboBoxTipoCliente.setBounds(12, 100, 285, 24);
		panel.add(comboBoxTipoCliente);
		
		JLabel lblTipoDeCliente = new JLabel("Tipo de cliente");
		lblTipoDeCliente.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 12));
		lblTipoDeCliente.setBounds(12, 80, 285, 15);
		panel.add(lblTipoDeCliente);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 214, 315, 355);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 27, 291, 316);
		panel_1.add(scrollPane_1);
		
		listaClientes = new JList();
		listaClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!listaClientes.isSelectionEmpty()) {
					String nombreCliente = listaClientes.getSelectedValue();
					
					textFieldCliente.setText(nombreCliente);
					textFieldTipoCliente.setText((String) comboBoxTipoCliente.getSelectedItem());
					
					ControladorCorreoPosVenta controlador = new ControladorCorreoPosVenta();
					VOCliente cliente = controlador.obtenerDatosCliente(nombreCliente);
					
					textFieldTel.setText(cliente.getCelular());
					textFieldPara.setText(cliente.getEmail());
					textFieldDe.setText(controlador.obtenerRemitente());
					
					String fechaInicio = dateChooserToFechaString(dateChooserInicio);
					String fechaFin = dateChooserToFechaString(dateChooserFin);
					List<VOVenta> listaVentas = controlador.listarTuplasVentas(fechaInicio, fechaFin, nombreCliente);
					
					if (listaVentas.size()>0) {
						Iterator<VOVenta> iterVentas = listaVentas.iterator();
						while (iterVentas.hasNext()) {
							VOVenta venta = iterVentas.next();
							Object[] fila = new Object[5];
							fila[0] = controlador.formatearFecha(venta.getFechaEntrega());
							fila[1] = venta.getNombreProducto();
							fila[2] = venta.getCategoria();							
							fila[3] = venta.getUnidades();
							fila[4] = venta.getTotal();							
							modeloTabla.addRow(fila);
						}
						
					}
					
					
				}
				
			}
			
		});
		scrollPane_1.setViewportView(listaClientes);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Productos adquiridos por el cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_3.setBounds(339, 23, 589, 257);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 124, 565, 121);
		panel_3.add(scrollPane);
		
	
		
		tablaProductos = new JTable(modeloTabla);
		tablaProductos.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
		scrollPane.setViewportView(tablaProductos);
		
		JLabel label = new JLabel("Cliente");
		label.setBounds(12, 32, 238, 15);
		panel_3.add(label);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCliente.setEditable(false);
		textFieldCliente.setColumns(10);
		textFieldCliente.setBounds(12, 51, 238, 25);
		panel_3.add(textFieldCliente);
		
		textFieldTipoCliente = new JTextField();
		textFieldTipoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTipoCliente.setEditable(false);
		textFieldTipoCliente.setColumns(10);
		textFieldTipoCliente.setBounds(278, 51, 149, 25);
		panel_3.add(textFieldTipoCliente);
		
		JLabel label_1 = new JLabel("Tipo de Cliente");
		label_1.setBounds(278, 32, 149, 15);
		panel_3.add(label_1);
		
		JLabel lblTelcel = new JLabel("Tel/Cel");
		lblTelcel.setBounds(449, 32, 128, 15);
		panel_3.add(lblTelcel);
		
		textFieldTel = new JTextField();
		textFieldTel.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTel.setEditable(false);
		textFieldTel.setColumns(10);
		textFieldTel.setBounds(449, 51, 128, 25);
		panel_3.add(textFieldTel);
		
		JLabel lblProductosAdquiridos = new JLabel("Productos adquiridos");
		lblProductosAdquiridos.setBounds(12, 100, 238, 15);
		panel_3.add(lblProductosAdquiridos);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Correo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(339, 292, 589, 314);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblDe = new JLabel("De:");
		lblDe.setBounds(12, 28, 71, 15);
		panel_2.add(lblDe);
		
		JLabel lblPara = new JLabel("Para:");
		lblPara.setBounds(12, 55, 71, 15);
		panel_2.add(lblPara);
		
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(12, 82, 71, 15);
		panel_2.add(lblAsunto);
		
		textFieldAsunto = new JTextField();
		textFieldAsunto.setBounds(88, 77, 343, 25);
		panel_2.add(textFieldAsunto);
		textFieldAsunto.setColumns(10);
		
		textFieldPara = new JTextField();
		textFieldPara.setEditable(false);
		textFieldPara.setColumns(10);
		textFieldPara.setBounds(88, 50, 343, 25);
		panel_2.add(textFieldPara);
		
		textFieldDe = new JTextField();
		textFieldDe.setEditable(false);
		textFieldDe.setColumns(10);
		textFieldDe.setBounds(88, 23, 343, 25);
		panel_2.add(textFieldDe);
		
		JButton btnNewButton_1 = new JButton("Enviar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String remitente = textFieldDe.getText();
				String destinatario = textFieldPara.getText();
				String asunto = textFieldAsunto.getText();
				String cuerpo = textAreaCuerpo.getText();
				
				if (remitente.isEmpty() || destinatario.isEmpty() || asunto.isEmpty() || cuerpo.isEmpty()) {
					JOptionPane.showMessageDialog(getContentPane(), "Debe completar todos los datos para enviar el mail.", "Faltan datos.", JOptionPane.ERROR_MESSAGE);
				}else {
					ControladorCorreoPosVenta controlador = new ControladorCorreoPosVenta();
					VOEmail email = new VOEmail();
					
					try {
						email.setRemitente(remitente);
						email.setDestinatario(destinatario);
						email.setAsunto(asunto);
						email.setCuerpo(cuerpo);
						email.setLstArchivosAdjuntos(null);
						
						
						if (!rutaArchivoAdjunto.isEmpty()) {
						VOArchivoAdjunto archivoAdjunto = new VOArchivoAdjunto();
							archivoAdjunto.setRutaArchivoAdjunto(rutaArchivoAdjunto + "/");							
							archivoAdjunto.setNombreArchivoAdjunto(nombreArchivoAdjunto);
														
							ArrayList<VOArchivoAdjunto> lstArchivosAdjuntos = new ArrayList<VOArchivoAdjunto>();
							lstArchivosAdjuntos.add(archivoAdjunto);
							email.setLstArchivosAdjuntos(lstArchivosAdjuntos);
						}
						
						controlador.enviarConGmail(email);
						JOptionPane.showMessageDialog(null, "Email enviado con éxito!.");
					} catch (MessagingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		btnNewButton_1.setBounds(449, 25, 117, 101);
		panel_2.add(btnNewButton_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 147, 565, 155);
		panel_2.add(scrollPane_2);
		
		textAreaCuerpo = new JTextArea();
		scrollPane_2.setViewportView(textAreaCuerpo);
		
		btnAdjuntar = new JButton("Adjuntar archivo");
		btnAdjuntar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cont !=0) {
					cont=0;
					rutaArchivoAdjunto="";
					nombreArchivoAdjunto="";
					textFieldArchivo.setText("");
					btnAdjuntar.setText("Adjuntar archivo");
					cont=0;
				}
				else {
					cont++;
					btnAdjuntar.setText("Quitar adjunto");
					JFileChooser filechooser = new JFileChooser();
					int result = filechooser.showOpenDialog(null);
					if(result == JFileChooser.APPROVE_OPTION){
						try{
						   //aqui asigno al string PathArchivo la ruta del archivo que elegi con el JFileChooser										  
							File archivo = new File(filechooser.getSelectedFile().getAbsolutePath());
							textFieldArchivo.setText(archivo.getName());
							
							rutaArchivoAdjunto = archivo.getParent();
							nombreArchivoAdjunto = archivo.getName(); 
						}
						catch(Exception ex){
							JOptionPane.showMessageDialog(null, ex);
							}
					}else 
						if (result == JFileChooser.CANCEL_OPTION){				
						}
					} 
				}
				
			
		});
		btnAdjuntar.setBounds(12, 110, 150, 25);
		panel_2.add(btnAdjuntar);
		
		textFieldArchivo = new JTextField();
		textFieldArchivo.setEditable(false);
		textFieldArchivo.setColumns(10);
		textFieldArchivo.setBounds(174, 110, 257, 25);
		panel_2.add(textFieldArchivo);

	}
	
	/// METODOS AUXILIARES ////////////////////////////////////////////////////////////////////////////////
	
	private boolean esValidaFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
	
	// Convierte una jDateChooser a String con formato
	private String dateChooserToFechaString(JDateChooser dateChooser) {
		String fecha ="";
		int anio=0;
		int mes=0;
		int dia=0;
		if (dateChooser.getCalendar() != null) {
			anio =dateChooser.getCalendar().get(Calendar.YEAR);
			mes =dateChooser.getCalendar().get(Calendar.MONTH) +1;
			dia =dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);				
			fecha = anio + "-" + mes + "-" + dia;		
		}
		return fecha;
	}
	
	// Retorna true si fecha1 >= fecha2
	private boolean esMayorOIgualFecha(String fecha1, String fecha2) {
	//PRE: AMBAS FECHAS VIENEN CON FORMATO yyyy-MM-dd
		boolean esMayor=false;		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date1 = sdf.parse(fecha1);
			Date date2 = sdf.parse(fecha2);
			
			esMayor = date1.after(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return esMayor;
	}
	
	private void listarClientes(String fechaInicio, String fechaFin, String tipoCliente) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		ControladorCorreoPosVenta controlador = new ControladorCorreoPosVenta();
		List<String> lista = controlador.listaClientesFechaEntrega(fechaInicio, fechaFin, tipoCliente);
		if(lista.size() > 0) {
			Iterator<String> iterClientes = lista.iterator();
			while (iterClientes.hasNext()) {
				String cliente = iterClientes.next();
				modelo.addElement(cliente);				
			}
		}
		listaClientes.setModel(modelo);
	}
}
