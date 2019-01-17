package grafica;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.apache.commons.validator.routines.UrlValidator;
import org.olap4j.metadata.Measure.Aggregator;

import com.toedter.calendar.JDateChooser;

import controladores.ControladorMantenimientoProveedor;
import valueObjects.VOProveedor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class MantenimientoProveedor extends JInternalFrame {
	private JTextField textFiltro;
	private JList<String> listaProveedores;
	private JTextField textFieldNombre;
	private JTextField textFieldEmail;
	private JTextField textFieldTelefono;
	private JTextField textFieldDireccionEnvio;
	private JTextField textFieldCambiarNombre;
	private JTextField textFieldURL;
	private JLabel label_nombre;
	private JLabel label_email;
	private JLabel label_telefono;
	private JLabel label_direccion;
	private JLabel label_url;
	private JLabel lblRaznSocial;
	private JLabel label_nombreNuevo;
	private JTextField textFieldFechaInfreso;
	private JPanel panel_2;
	private JComboBox<String> comboBoxServicios;
	private JComboBox<String> comboBoxMateriales;
	private JDateChooser dateChooser;
	private JLabel label_fecha;
	private JList<String> listaServicios;
	private JList<String> listaMateriales;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MantenimientoProveedor frame = new MantenimientoProveedor();
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
	public MantenimientoProveedor() {
		setClosable(true);
		setTitle("Mantenimiento de Proveedores");
		setBounds(100, 100, 807, 654);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Lista de Proveedores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel.setBounds(12, 12, 230, 304);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 29, 55, 15);
		panel.add(lblNombre); 
		
		textFiltro = new JTextField();
		textFiltro.addKeyListener(new KeyAdapter() {
			StringBuffer txt = new StringBuffer();
			@Override
			public void keyTyped(KeyEvent e) {
				char car = e.getKeyChar();
				if (car == KeyEvent.VK_BACK_SPACE) {
					e.consume();
					if (txt.length() >=1) {
						txt.setLength(txt.length()-1);
						flitrarTuplasProveedores(proveedoresToString(),txt.toString());
					}
				}else {
					txt.append(car);
					flitrarTuplasProveedores(proveedoresToString(),txt.toString());
				}
			}
		});
		textFiltro.setBounds(71, 25, 147, 26);
		panel.add(textFiltro);
		textFiltro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 56, 206, 236);
		panel.add(scrollPane);
		
		listaProveedores = new JList(this.proveedoresToString());
		listaProveedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControladorMantenimientoProveedor controlador = new ControladorMantenimientoProveedor();
				String nombre = listaProveedores.getSelectedValue();
				VOProveedor proveedor = controlador.obtenerDatosProveedor(nombre);
				
				
				textFieldNombre.setText(proveedor.getNombre());
				textFieldDireccionEnvio.setText(proveedor.getDireccion());
				textFieldEmail.setText(proveedor.getEmail());
				textFieldTelefono.setText(proveedor.getTel());
				textFieldURL.setText(proveedor.getUrl());
				
				String fecha = proveedor.getFechaIngreso();
				if (!fecha.isEmpty()) {
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
					String ds2;
					try {
						ds2 = sdf2.format(sdf1.parse(proveedor.getFechaIngreso()));
						Date fechaDate = sdf2.parse(ds2);				
						dateChooser.setDate(fechaDate);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}									
				}
				
				listarServiciosProveedor(nombre);
				listarMaterialesProveedor(nombre);
				
				
				
			}
		});
		scrollPane.setViewportView(listaProveedores);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Alta / Modificaci\u00F3n de datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(254, 12, 531, 304);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNombre_1 = new JLabel("Nombre *");
		lblNombre_1.setBounds(32, 35, 70, 15);
		panel_1.add(lblNombre_1);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(32, 52, 194, 26);
		panel_1.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		label_nombre = new JLabel("");
		label_nombre.setIcon(new ImageIcon(MantenimientoProveedor.class.getResource("/grafica/imagenes/Error.png")));
		label_nombre.setBounds(233, 52, 20, 20);
		panel_1.add(label_nombre);
		label_nombre.setVisible(false);
		
		JLabel lblDireccinDeEnvo = new JLabel("E-mail *");
		lblDireccinDeEnvo.setBounds(32, 139, 194, 15);
		panel_1.add(lblDireccinDeEnvo);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(32, 156, 194, 26);
		panel_1.add(textFieldEmail);
		
		label_email = new JLabel("");
		label_email.setIcon(new ImageIcon(MantenimientoProveedor.class.getResource("/grafica/imagenes/Error.png")));
		label_email.setBounds(233, 156, 20, 20);
		panel_1.add(label_email);
		label_email.setVisible(false);
		
		JLabel lblTelfono = new JLabel("Teléfono *");
		lblTelfono.setBounds(32, 84, 72, 15);
		panel_1.add(lblTelfono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(32, 101, 194, 26);
		panel_1.add(textFieldTelefono);
		
		label_telefono = new JLabel("");
		label_telefono.setIcon(new ImageIcon(MantenimientoProveedor.class.getResource("/grafica/imagenes/Error.png")));
		label_telefono.setBounds(233, 101, 20, 20);
		panel_1.add(label_telefono);
		label_telefono.setVisible(false);
		
		JLabel lblDireccinDeEnvo_1 = new JLabel("Dirección *");
		lblDireccinDeEnvo_1.setBounds(284, 84, 194, 15);
		panel_1.add(lblDireccinDeEnvo_1);
		
		textFieldDireccionEnvio = new JTextField();
		textFieldDireccionEnvio.setColumns(10);
		textFieldDireccionEnvio.setBounds(284, 101, 194, 26);
		panel_1.add(textFieldDireccionEnvio);
		
		label_direccion = new JLabel("");
		label_direccion.setIcon(new ImageIcon(MantenimientoProveedor.class.getResource("/grafica/imagenes/Error.png")));
		label_direccion.setBounds(485, 101, 20, 20);
		panel_1.add(label_direccion);
		label_direccion.setVisible(false);
		
		JLabel lblCambiarNombrePor = new JLabel("Cambiar nombre por");
		lblCambiarNombrePor.setBounds(284, 35, 194, 15);
		panel_1.add(lblCambiarNombrePor);
		
		textFieldCambiarNombre = new JTextField();
		textFieldCambiarNombre.setColumns(10);
		textFieldCambiarNombre.setBounds(284, 52, 194, 26);
		panel_1.add(textFieldCambiarNombre);
		
		
		
		label_url = new JLabel("");
		label_url.setIcon(new ImageIcon(MantenimientoProveedor.class.getResource("/grafica/imagenes/Error.png")));
		label_url.setBounds(485, 156, 20, 20);
		panel_1.add(label_url);
		label_url.setVisible(false);
		
		textFieldURL = new JTextField();
		textFieldURL.setColumns(10);
		textFieldURL.setBounds(284, 156, 194, 26);
		panel_1.add(textFieldURL);
		
		lblRaznSocial = new JLabel("Sitio web");
		lblRaznSocial.setBounds(284, 139, 194, 15);
		panel_1.add(lblRaznSocial);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String nombre = textFieldNombre.getText();
				String direccion = textFieldDireccionEnvio.getText();
				String email =textFieldEmail.getText();
				String tel = textFieldTelefono.getText();
				String url = textFieldURL.getText();
				
				String fechaIngreso = "";
				int anio=0;
				int mes=0;
				int dia=0;
				if (dateChooser.getCalendar() != null) {
					anio =dateChooser.getCalendar().get(Calendar.YEAR);
					mes =dateChooser.getCalendar().get(Calendar.MONTH) +1;
					dia =dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);				
					fechaIngreso = anio + "-" + mes + "-" + dia;
				}
				
				String cambiarNombre = textFieldCambiarNombre.getText();
				
				ControladorMantenimientoProveedor controlador = new ControladorMantenimientoProveedor();
				
				// VALIDACION NOMBRE
				if (nombre.isEmpty()) {
					label_nombre.setVisible(true);
					label_nombre.setToolTipText("No ha escrito el nombre del Proveedor.");
					error = true;
				}else {
					label_nombre.setVisible(false);
					if (controlador.existeProveedor(nombre)) {
						label_nombre.setVisible(true);
						label_nombre.setToolTipText("Ya existe un Proveedor con ese nombre.");
						error = true;
					}else {
						label_nombre.setVisible(false);
					}				
				}
								
				// VALIDACION DE TELEFONO (no es campo obligatorio)
				if (!tel.isEmpty()) {
					if (!esValidoTelefono(tel)) {
						label_telefono.setVisible(true);
						label_telefono.setToolTipText("Numero de telefono incorrecto.");
						error = true;
					}else
						label_telefono.setVisible(false);
				}else
					label_telefono.setVisible(false);
				
				
				//VALIDACION EMAIL
				if (email.isEmpty()) {
					label_email.setVisible(true);
					label_email.setToolTipText("No ha escrito direccion de correo electrónico.");
					error = true;
				}else
					if (!esValidoEmail(email)) {
						label_email.setVisible(true);
						label_email.setToolTipText("Email incorrecto.");
						error = true;
					}else
						label_email.setVisible(false);
				
				
				//VALIDACION pagina web
				if (!url.isEmpty())
					if (!esValidoURL(url)) {
						label_url.setVisible(true);
						label_url.setToolTipText("No ha escrito correctamente la url de la pagina web.");
						error = true;
					}else
						label_email.setVisible(false);
			
				
			// VALIDACION FECHA DE INGRESO	
			if (!fechaIngreso.isEmpty()) {
				if (!esValidaFecha(dia +"/" + mes + "/" + anio)) {
					label_fecha.setVisible(true);
					label_fecha.setToolTipText("Formato incorrecto de fecha (dd/mm/yyyy");
				}else
					label_fecha.setVisible(false);
			}
				
			if (!error) {
				VOProveedor proveedor = new VOProveedor(nombre, direccion, email, tel, url, fechaIngreso);
				controlador.insertarProveedor(proveedor);
				listarProveedores();
				JOptionPane.showMessageDialog(getContentPane(), "Proveedor ingresado con éxito.", "Ingreso existoso", JOptionPane.PLAIN_MESSAGE);
			}
			}
		});
		btnAlta.setBounds(32, 263, 117, 25);
		panel_1.add(btnAlta);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				String nombre = textFieldNombre.getText();
				String direccion = textFieldDireccionEnvio.getText();
				String email =textFieldEmail.getText();
				String tel = textFieldTelefono.getText();
				String url = textFieldURL.getText();
				
				String fechaIngreso = "";
				int anio=0;
				int mes=0;
				int dia=0;
				if (dateChooser.getCalendar() != null) {
					anio =dateChooser.getCalendar().get(Calendar.YEAR);
					mes =dateChooser.getCalendar().get(Calendar.MONTH) +1;
					dia =dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);				
					fechaIngreso = anio + "-" + mes + "-" + dia;
				}
				
	
				ControladorMantenimientoProveedor controlador = new ControladorMantenimientoProveedor();
				
				// VALIDACION NOMBRE
				if (nombre.isEmpty()) {
					label_nombre.setVisible(true);
					label_nombre.setToolTipText("No ha escrito el nombre del Cliente.");
					error = true;
				}else {
					label_nombre.setVisible(false);
					
				}
				
				//VALIDACION EMAIL
				if (email.isEmpty()) {
					label_email.setVisible(true);
					label_email.setToolTipText("No ha escrito direccion de correo electrónico.");
					error = true;
				}else
					if (!esValidoEmail(email)) {
						label_email.setVisible(true);
						label_email.setToolTipText("Email incorrecto.");
						error = true;
					}else
						label_email.setVisible(false);
				
				// VALIDACION DE TELEFONO (no es campo obligatorio)
				if (!tel.isEmpty()) {
					if (!esValidoTelefono(tel)) {
						label_telefono.setVisible(true);
						label_telefono.setToolTipText("Numero de telefono incorrecto.");
						error = true;
					}else
						label_telefono.setVisible(false);
				}else
					label_telefono.setVisible(false);
				
				//VALIDACION pagina web
				if (!esValidoURL(url)) {
					label_url.setVisible(true);
					label_url.setToolTipText("No ha escrito correctamente la url de la pagina web.");
					error = true;
				}else
					label_email.setVisible(false);
			
				
			// VALIDACION FECHA DE INGRESO	
			if (!fechaIngreso.isEmpty()) {
				if (!esValidaFecha(dia +"/" + mes + "/" + anio)) {
					label_fecha.setVisible(true);
					label_fecha.setToolTipText("Formato incorrecto de fecha (dd/mm/yyyy");
					error = true;
				}else
					label_fecha.setVisible(false);
			}
				
			String nombreNuevo = textFieldCambiarNombre.getText();
			if (!nombreNuevo.isEmpty()) {
				if (controlador.existeProveedor(nombreNuevo)) {
					label_nombreNuevo.setVisible(true);
					label_nombreNuevo.setToolTipText("Ya existe un Cliente con ese nombre.");
					error = true;
				}else {
					label_nombreNuevo.setVisible(false);						
				}
			}
			
			if (!error) {
				VOProveedor proveedor = new VOProveedor(nombre, direccion, email, tel, url, fechaIngreso);
				String nombrProveedor = proveedor.getNombre();
				
				if (!nombreNuevo.isEmpty())						
					proveedor.setNombre(nombreNuevo);
				
				controlador.modificarProveedor(nombrProveedor, proveedor);
				listarProveedores();
				JOptionPane.showMessageDialog(getContentPane(), "Proveedor modificado con éxito.", "Modificación exitosa", JOptionPane.PLAIN_MESSAGE);
			}
			
			}
		});
		btnModificar.setBounds(161, 263, 117, 25);
		panel_1.add(btnModificar);
		
		JButton btnLimpiarDat = new JButton("Limpiar datos");
		btnLimpiarDat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNombre.setText("");
				textFieldDireccionEnvio.setText("");
				textFieldEmail.setText("");
				textFieldTelefono.setText("");
				textFieldURL.setText("");
				textFieldCambiarNombre.setText("");
				dateChooser.setDate(null);
				label_nombre.setVisible(false);
				label_direccion.setVisible(false);
				label_email.setVisible(false);
				label_telefono.setVisible(false);
				label_url.setVisible(false);
				label_nombreNuevo.setVisible(false);
				label_fecha.setVisible(false);				
			}
		});
		btnLimpiarDat.setBounds(284, 263, 194, 25);
		panel_1.add(btnLimpiarDat);
		
		label_nombreNuevo = new JLabel("");
		label_nombreNuevo.setIcon(new ImageIcon(MantenimientoProveedor.class.getResource("/grafica/imagenes/Error.png")));
		label_nombreNuevo.setBounds(485, 52, 20, 20);
		label_nombreNuevo.setVisible(false);
		panel_1.add(label_nombreNuevo);
		
		JLabel lblFechaDeIngreso = new JLabel("Fecha de ingreso");
		lblFechaDeIngreso.setBounds(32, 194, 122, 15);
		panel_1.add(lblFechaDeIngreso);
		
		/*textFieldFechaInfreso = new JTextField();
		textFieldFechaInfreso.setColumns(10);
		textFieldFechaInfreso.setBounds(32, 212, 194, 26);
		panel_1.add(textFieldFechaInfreso);*/
		
		dateChooser = new JDateChooser();
		dateChooser.setBackground(new Color(255,255,204));
		dateChooser.setBounds(32, 213, 194, 24);
		
		panel_1.add(dateChooser);
		
		label_fecha = new JLabel("");
		label_fecha.setIcon(new ImageIcon(MantenimientoProveedor.class.getResource("/grafica/imagenes/Error.png")));
		label_fecha.setBounds(233, 217, 20, 20);
		panel_1.add(label_fecha);
		label_fecha.setVisible(false);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Servicios y materiales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(12, 330, 773, 228);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 56, 178, 160);
		panel_2.add(scrollPane_1);
		
		listaServicios = new JList();
		scrollPane_1.setViewportView(listaServicios);
		
		JLabel lblNewLabel = new JLabel("Servicios");
		lblNewLabel.setBounds(12, 37, 178, 15);
		panel_2.add(lblNewLabel);
		
		JLabel lblMateriales = new JLabel("Materiales");
		lblMateriales.setBounds(208, 39, 178, 15);
		panel_2.add(lblMateriales);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(202, 56, 178, 160);
		panel_2.add(scrollPane_2);
		
		listaMateriales = new JList();
		scrollPane_2.setViewportView(listaMateriales);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Servicios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(396, 46, 355, 79);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		comboBoxServicios = new JComboBox();
		comboBoxServicios.setBounds(12, 31, 202, 24);
		panel_3.add(comboBoxServicios);
		
		ControladorMantenimientoProveedor controlador = new ControladorMantenimientoProveedor();
		List<String> lstServicios = controlador.listarServicio();
		
		if(lstServicios.size() > 0) {
			Iterator<String> iterServicios = lstServicios.iterator();
			while (iterServicios.hasNext()) {
				String strServicicio = iterServicios.next();
				comboBoxServicios.addItem(strServicicio);				
			}
		}
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String proveedor = listaProveedores.getSelectedValue();
				String servicio = (String) comboBoxServicios.getSelectedItem();
				
				ControladorMantenimientoProveedor controlador = new ControladorMantenimientoProveedor();
				if (proveedor == null) {
					JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar un Proveedor de la lista.", "Proveedor no seleccionado", JOptionPane.PLAIN_MESSAGE);
				}else
					if (controlador.existeServicioProveedor(proveedor, servicio)) {
						JOptionPane.showMessageDialog(getContentPane(), "El proveedor ya tiene asignado ese servicio.", "Servicio duplicado", JOptionPane.PLAIN_MESSAGE);
					}else				
						if (controlador.agregarServicioProveedor(proveedor, servicio) !=0) {
							listarServiciosProveedor(proveedor);
							JOptionPane.showMessageDialog(getContentPane(), "Servicio agregado con éxito.", "Modificación exitosa", JOptionPane.PLAIN_MESSAGE);
						}else
							JOptionPane.showMessageDialog(getContentPane(), "No se ha podido agregar el servicio.", "Error al insertar", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnNewButton.setBounds(226, 12, 117, 25);
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Quitar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String proveedor = listaProveedores.getSelectedValue();
				String servicio = (String) comboBoxServicios.getSelectedItem();
								
				ControladorMantenimientoProveedor controlador = new ControladorMantenimientoProveedor();
				
					
				if (controlador.eliminarServicioProveedor(proveedor, servicio) !=0) {
					listarServiciosProveedor(proveedor);
					JOptionPane.showMessageDialog(getContentPane(), "Servicio eliminado con éxito.", "Modificación exitosa", JOptionPane.PLAIN_MESSAGE);
				}else
					JOptionPane.showMessageDialog(getContentPane(), "Servicio no asignado al provedor.", "Error al eliminar", JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		btnNewButton_1.setBounds(226, 49, 117, 25);
		panel_3.add(btnNewButton_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Materiales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(396, 137, 355, 79);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		comboBoxMateriales = new JComboBox();
		comboBoxMateriales.setBounds(12, 31, 202, 24);
		panel_4.add(comboBoxMateriales);
		
		
		List<String> lstMateriales = controlador.listarMateriales();
		
		if(lstMateriales.size() > 0) {
			Iterator<String> iterMateriales = lstMateriales.iterator();
			while (iterMateriales.hasNext()) {
				String strMaterial = iterMateriales.next();
				comboBoxMateriales.addItem(strMaterial);				
			}
		}
		
		
		
		JButton button = new JButton("Agregar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String proveedor = listaProveedores.getSelectedValue();
				String material = (String) comboBoxMateriales.getSelectedItem();
				
				System.out.println("PROVEEDOR: " + proveedor);
				System.out.println("MATERIAL: " + material);
				
				ControladorMantenimientoProveedor controlador = new ControladorMantenimientoProveedor();
				
				if (controlador.existeMaterialProveedor(proveedor, material)) {
					JOptionPane.showMessageDialog(getContentPane(), "El proveedor ya tiene asignado ese material.", "Material duplicado", JOptionPane.PLAIN_MESSAGE);
				}else				
					if (controlador.agregarMaterialProveedor(proveedor, material) !=0) {						
						listarMaterialesProveedor(proveedor);
						JOptionPane.showMessageDialog(getContentPane(), "Material agregado con éxito.", "Modificación exitosa", JOptionPane.PLAIN_MESSAGE);
					}else
						JOptionPane.showMessageDialog(getContentPane(), "No se ha podido agregar el material.", "Error al insertar", JOptionPane.PLAIN_MESSAGE);
			}
		});
		button.setBounds(226, 12, 117, 25);
		panel_4.add(button);
		
		JButton button_1 = new JButton("Quitar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String proveedor = listaProveedores.getSelectedValue();
				String material = (String) comboBoxMateriales.getSelectedItem();
								
				ControladorMantenimientoProveedor controlador = new ControladorMantenimientoProveedor();
				
					
				if (controlador.eliminarMaterialProveedor(proveedor, material) !=0) {
					listarMaterialesProveedor(proveedor);
					JOptionPane.showMessageDialog(getContentPane(), "Material eliminado con éxito.", "Modificación exitosa", JOptionPane.PLAIN_MESSAGE);
				}else
					JOptionPane.showMessageDialog(getContentPane(), "Material no asignado al provedor.", "Error al eliminar", JOptionPane.PLAIN_MESSAGE);
			}
		});
		button_1.setBounds(226, 49, 117, 25);
		panel_4.add(button_1);
		
		JButton btnNewButton_2 = new JButton("Reporte de Proveedores");
		btnNewButton_2.setBounds(12, 581, 207, 25);
		getContentPane().add(btnNewButton_2);
		
		JButton btnReporteDeMateriales = new JButton("Reporte de Materiales");
		btnReporteDeMateriales.setBounds(266, 581, 207, 25);
		getContentPane().add(btnReporteDeMateriales);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(668, 581, 117, 25);
		getContentPane().add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MantenimientoProveedor.this.dispose();
			}
		});
		
	}
	
	
	public String[] proveedoresToString() {
		// Retorna un array de proveedores en el cual cada tupla esta formada por el nombre
		// Luego este array se pasa al jlist de clientes
		ControladorMantenimientoProveedor controlador = new ControladorMantenimientoProveedor();
		List<String> proveedores = controlador.listarProveedores();
		String[] tuplas = new String[proveedores.size()];
		int i=0;
		for (String pro : proveedores) {
			tuplas[i] = pro;
			i++;
		}
		return tuplas;
	}
	
	public void flitrarTuplasProveedores(String[] tuplasProveedores , String subCadena) {
		// Dado un array de tuplas de proveedores y una subcadena va filtrando los nombres del jList
		boolean filtrar=false;
		DefaultListModel modeloListaProveedores = new DefaultListModel<String>();
		for (int i=0; i < tuplasProveedores.length;i++) {
			if (tuplasProveedores[i].toUpperCase().startsWith(subCadena.toUpperCase())) {
				filtrar = true;
				modeloListaProveedores.addElement(tuplasProveedores[i]);
			}
		}
		if (filtrar) {
			listaProveedores.setModel(modeloListaProveedores);
		}		
	}
	
	public void listarProveedores() {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		ControladorMantenimientoProveedor controlador = new ControladorMantenimientoProveedor();
		List<String> lista = controlador.listarProveedores();
		if(lista.size() > 0) {
			Iterator<String> iterProveedores = lista.iterator();
			while (iterProveedores.hasNext()) {
				String cliente = iterProveedores.next();
				modelo.addElement(cliente);				
			}
		}
		listaProveedores.setModel(modelo);
	}
	
	public void listarServiciosProveedor(String proveedor) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		ControladorMantenimientoProveedor controlador = new ControladorMantenimientoProveedor();
		List<String> lista = controlador.obtenerListaServiciosProveedor(proveedor);
		if(lista.size() > 0) {
			Iterator<String> iterProveedores = lista.iterator();
			while (iterProveedores.hasNext()) {
				String cliente = iterProveedores.next();
				modelo.addElement(cliente);				
			}
		}
		listaServicios.setModel(modelo);		
	}
	
	public void listarMaterialesProveedor(String proveedor) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		ControladorMantenimientoProveedor controlador = new ControladorMantenimientoProveedor();
		List<String> lista = controlador.obtenerListaMaterialesProveedor(proveedor);
		if(lista.size() > 0) {
			Iterator<String> iterMateriales = lista.iterator();
			while (iterMateriales.hasNext()) {
				String material = iterMateriales.next();
				modelo.addElement(material);				
			}
		}
		listaMateriales.setModel(modelo);
		
	}
	
	
	///// FUNCIONES DE VALIDACION ///////////////////////////////////////////////////////
	
	public static boolean esValidoCelular (String cadena) {
		// Comienza con cero y tiene 9 digitos numericos en total
		return cadena.matches("^0\\d{8}");
	}
	
	public static boolean esValidoEmail(String cadena) {
		/*
		   ^ especifica el inicio de la entrada.
		  ([_a-z0-9-]) primer grupo. Se refiere a la aparición de uno o más caracteres compuestos por guión bajo, letras, números y guiones.
          (\\.[_a-z0-9-]) segundo grupo. Puede ser opcional y repetible, se refiere a la aparición de un punto seguido de uno o más caracteres compuestos por guión bajo, letras, números y guiones.
          *@ carácter arroba.
          ([a-z0-9-]) tercer grupo. Especifica la aparición de uno o más caracteres compuestos por letras, números y guiones.
          (\\.[a-z0-9-]) cuarto grupo. Especifica un punto seguido de uno o más caracteres compuestos por letras, números y guiones.
          (\\.[a-z]{2,4}) quinto grupo. Especifica un punto seguido de entre 2 y 4 letras, con el fin de considerar dominios terminados, por ejemplo, en .co y .info.
          $ especifica el fin de la entrada.
		 */
		return cadena.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
	}
	
	public static boolean esValidoTelefono(String cadena) {
		// Esta formado solo por números y cualquier cantidad de digitos
		return cadena.matches("\\d*");
	}
	
	public static boolean esValidoRUT (String cadena) {
		// Tiene 12 digitos numericos en total
		return cadena.matches("\\d{12}");
	}
	
	public static boolean esValidoURL(String cadena) {		
	/* se utiliza la libreria Commons Validator (http://commons.apache.org/proper/commons-validator/index.html) */	
		UrlValidator validar = new UrlValidator();
		return validar.isValid(cadena);
	}
	
	public static boolean esValidaFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
