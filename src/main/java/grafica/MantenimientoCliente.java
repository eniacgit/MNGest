package grafica;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controladores.ControladorMantenimientoClientes;
import valueObjects.VOCliente;

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
import javax.swing.JComboBox;

public class MantenimientoCliente extends JInternalFrame {
	private JTextField textFiltro;
	private JList<String> listaClientes;
	private JTextField textFieldNombre;
	private JTextField textFieldCelular;
	private JTextField textFieldEmail;
	private JTextField textFieldTelefono;
	private JTextField textFieldDireccionEnvio;
	private JTextField textFieldCambiarNombre;
	private JTextField textFieldRazonSocial;
	private JTextField textFieldRUT;
	private JLabel label_nombre;
	private JLabel label_celular;
	private JLabel label_email;
	private JLabel label_telefono;
	private JLabel label_direccion;
	private JLabel label_razonSocial;
	private JLabel lblRut;
	private JLabel lblRaznSocial;
	private JComboBox<String> comboBox;
	private JLabel label_rut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MantenimientoCliente frame = new MantenimientoCliente();
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
	public MantenimientoCliente() {
		setClosable(true);
		setTitle("Alta y Modificación de Clientes");
		setBounds(100, 100, 807, 384);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Lista de Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 230, 329);
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
						flitrarTuplasClientes(clientesToString(),txt.toString());
					}
				}else {
					txt.append(car);
					flitrarTuplasClientes(clientesToString(),txt.toString());
				}
			}
		});
		textFiltro.setBounds(71, 25, 147, 26);
		panel.add(textFiltro);
		textFiltro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 56, 206, 261);
		panel.add(scrollPane);
		
		listaClientes = new JList(this.clientesToString());
		listaClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// limpio etiquetas de error
				label_nombre.setVisible(false);
				label_celular.setVisible(false);
				label_email.setVisible(false);
				label_telefono.setVisible(false);
				label_rut.setVisible(false);
				label_razonSocial.setVisible(false);
				label_direccion.setVisible(false);
				
				ControladorMantenimientoClientes controlador = new ControladorMantenimientoClientes();
				String nombre =listaClientes.getSelectedValue();
				textFieldNombre.setText(nombre);
				VOCliente voCliente= controlador.obtenerDatosCliente(nombre);
				textFieldCelular.setText(voCliente.getCelular());
				textFieldEmail.setText(voCliente.getEmail());
				textFieldRUT.setText(voCliente.getRut());
				textFieldTelefono.setText(voCliente.getTelefono());
				textFieldDireccionEnvio.setText(voCliente.getDireccion());
				textFieldRazonSocial.setText(voCliente.getRazonSocial());
				
				String tipoCliente = voCliente.getTipo();
				comboBox.removeAllItems();
				if (tipoCliente.equals("CORPORATIVO")){
					comboBox.addItem("CORPORATIVO");
					comboBox.addItem("CONSUMIDOR FINAL");
					lblRut.setVisible(true);
					textFieldRUT.setVisible(true);
					lblRaznSocial.setVisible(true);
					textFieldRazonSocial.setVisible(true);
				}else {
					comboBox.addItem("CONSUMIDOR FINAL");
					comboBox.addItem("CORPORATIVO");
					lblRut.setVisible(false);
					textFieldRUT.setVisible(false);
					lblRaznSocial.setVisible(false);
					textFieldRazonSocial.setVisible(false);					
				}
								
			}
		});
		scrollPane.setViewportView(listaClientes);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Alta / Modificaci\u00F3n de datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(254, 12, 531, 329);
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
		label_nombre.setIcon(new ImageIcon(MantenimientoCliente.class.getResource("/grafica/imagenes/Error.png")));
		label_nombre.setBounds(233, 52, 20, 20);
		panel_1.add(label_nombre);
		label_nombre.setVisible(false);
		
		JLabel lblTelfon = new JLabel("Celular *");
		lblTelfon.setBounds(32, 87, 70, 15);
		panel_1.add(lblTelfon);
		
		textFieldCelular = new JTextField();
		textFieldCelular.setColumns(10);
		textFieldCelular.setBounds(32, 104, 194, 26);
		panel_1.add(textFieldCelular);
		
		label_celular = new JLabel("");
		label_celular.setIcon(new ImageIcon(MantenimientoCliente.class.getResource("/grafica/imagenes/Error.png")));
		label_celular.setBounds(233, 104, 20, 20);
		panel_1.add(label_celular);
		label_celular.setVisible(false);
		
		JLabel lblDireccinDeEnvo = new JLabel("E-mail *");
		lblDireccinDeEnvo.setBounds(32, 139, 194, 15);
		panel_1.add(lblDireccinDeEnvo);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(32, 156, 194, 26);
		panel_1.add(textFieldEmail);
		
		label_email = new JLabel("");
		label_email.setIcon(new ImageIcon(MantenimientoCliente.class.getResource("/grafica/imagenes/Error.png")));
		label_email.setBounds(233, 156, 20, 20);
		panel_1.add(label_email);
		label_email.setVisible(false);
		
		JLabel label = new JLabel("Teléfono");
		label.setBounds(284, 87, 70, 15);
		panel_1.add(label);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(284, 104, 194, 26);
		panel_1.add(textFieldTelefono);
		
		label_telefono = new JLabel("");
		label_telefono.setIcon(new ImageIcon(MantenimientoCliente.class.getResource("/grafica/imagenes/Error.png")));
		label_telefono.setBounds(485, 104, 20, 20);
		panel_1.add(label_telefono);
		label_telefono.setVisible(false);
		
		JLabel lblDireccinDeEnvo_1 = new JLabel("Dirección de envío *");
		lblDireccinDeEnvo_1.setBounds(284, 139, 194, 15);
		panel_1.add(lblDireccinDeEnvo_1);
		
		textFieldDireccionEnvio = new JTextField();
		textFieldDireccionEnvio.setColumns(10);
		textFieldDireccionEnvio.setBounds(284, 156, 194, 26);
		panel_1.add(textFieldDireccionEnvio);
		
		label_direccion = new JLabel("");
		label_direccion.setIcon(new ImageIcon(MantenimientoCliente.class.getResource("/grafica/imagenes/Error.png")));
		label_direccion.setBounds(485, 156, 20, 20);
		panel_1.add(label_direccion);
		label_direccion.setVisible(false);
		
		JLabel lblCambiarNombrePor = new JLabel("Cambiar nombre por");
		lblCambiarNombrePor.setBounds(284, 35, 194, 15);
		panel_1.add(lblCambiarNombrePor);
		
		textFieldCambiarNombre = new JTextField();
		textFieldCambiarNombre.setColumns(10);
		textFieldCambiarNombre.setBounds(284, 52, 194, 26);
		panel_1.add(textFieldCambiarNombre);
		
		JLabel lblTipoDeCliente = new JLabel("Tipo de Cliente *");
		lblTipoDeCliente.setBounds(32, 187, 194, 15);
		panel_1.add(lblTipoDeCliente);
		
		
		
		label_razonSocial = new JLabel("");
		label_razonSocial.setIcon(new ImageIcon(MantenimientoCliente.class.getResource("/grafica/imagenes/Error.png")));
		label_razonSocial.setBounds(485, 205, 20, 20);
		panel_1.add(label_razonSocial);
		label_razonSocial.setVisible(false);
		
		textFieldRazonSocial = new JTextField();
		textFieldRazonSocial.setColumns(10);
		textFieldRazonSocial.setBounds(284, 205, 194, 26);
		panel_1.add(textFieldRazonSocial);
		
		lblRaznSocial = new JLabel("Razón social *");
		lblRaznSocial.setBounds(284, 188, 194, 15);
		panel_1.add(lblRaznSocial);
		
		lblRut = new JLabel("RUT *");
		lblRut.setBounds(32, 240, 70, 15);
		panel_1.add(lblRut);
		
		textFieldRUT = new JTextField();
		textFieldRUT.setColumns(10);
		textFieldRUT.setBounds(32, 257, 194, 26);
		panel_1.add(textFieldRUT);
		
		label_rut = new JLabel("");
		label_rut.setIcon(new ImageIcon(MantenimientoCliente.class.getResource("/grafica/imagenes/Error.png")));
		label_rut.setBounds(233, 257, 20, 20);
		panel_1.add(label_rut);
		label_rut.setVisible(false);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldNombre.getText();
				String celular = textFieldCelular.getText();
				String email = textFieldEmail.getText();
				String rut = textFieldRUT.getText();
				String cambiarNombre = textFieldCambiarNombre.getText();
				String telefono = textFieldTelefono.getText();
				String direccion = textFieldDireccionEnvio.getText();				
				String razonSocial = textFieldRazonSocial.getText();
				
				boolean error = false;
				
				ControladorMantenimientoClientes controlador = new ControladorMantenimientoClientes();
				
				// VALIDACION NOMBRE
				if (nombre.isEmpty()) {
					label_nombre.setVisible(true);
					label_nombre.setToolTipText("No ha escrito el nombre del Cliente.");
					error = true;
				}else {
					label_nombre.setVisible(false);
					if (controlador.existeCliente(nombre)) {
						label_nombre.setVisible(true);
						label_nombre.setToolTipText("Ya existe un Cliente con ese nombre.");
						error = true;
					}else {
						label_nombre.setVisible(false);
					}
				}
				
				// VALIDACION CELULAR
				if (celular.isEmpty()) {
					label_celular.setVisible(true);
					label_celular.setToolTipText("No ha escrito el numero de celular.");
					error = true;
				}else
					if (!esValidoCelular(celular)) {
						label_celular.setVisible(true);
						label_celular.setToolTipText("Número de celular incorrecto.");
						error = true;
					}else
						label_celular.setVisible(false);
				
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
				if (!telefono.isEmpty()) {
					if (!esValidoTelefono(telefono)) {
						label_telefono.setVisible(true);
						label_telefono.setToolTipText("Numero de telefono incorrecto.");
						error = true;
					}else
						label_telefono.setVisible(false);
				}else
					label_telefono.setVisible(false);
				
				
				// VALIDACION DIRECCION DE ENVIO
				if (direccion.isEmpty()) {
					label_direccion.setVisible(true);
					label_direccion.setToolTipText("No ha escrito direccion de envio.");
					error = true;
				}else
					label_direccion.setVisible(false);
				
				
				String tipoCliente = (String) comboBox.getSelectedItem();
				if (tipoCliente.equals("CORPORATIVO")) {
					// VALIDACION RUT
					if (!rut.isEmpty()) {
						if (!esValidoRUT(rut)) {
							label_rut.setVisible(true);
							label_rut.setToolTipText("RUT incorrecto, deben ser 12 digitos numericos.");
							error = true;
						}else
							label_rut.setVisible(false);
					}else {
						label_rut.setVisible(true);
						label_rut.setToolTipText("No ha escrito el RUT.");
						error = true;
					}
					
					// VALIDACION RAZON SOCIAL
					if (razonSocial.isEmpty()) {
						label_razonSocial.setVisible(true);
						label_razonSocial.setToolTipText("No ha escrito razón social.");
						error = true;
					}
					
					
					
				}else {
					// si es consumidor final
					label_rut.setVisible(false);
					label_razonSocial.setVisible(false);
				}
				
				if (!error) {
					VOCliente cliente = new VOCliente(nombre, email, telefono, celular, rut, razonSocial, tipoCliente, direccion);
					controlador.insertarCliente(cliente);
					listarClientes();
					JOptionPane.showMessageDialog(getContentPane(), "Cliente ingresado con éxito.", "Ingreso existoso", JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		});
		btnAlta.setBounds(32, 292, 117, 25);
		panel_1.add(btnAlta);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(166, 292, 117, 25);
		panel_1.add(btnModificar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MantenimientoCliente.this.dispose();
			}
		});
		btnSalir.setBounds(388, 292, 117, 25);
		panel_1.add(btnSalir);
		
		JButton btnLimpiarDat = new JButton("Limpiar datos");
		btnLimpiarDat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNombre.setText("");
				textFieldCelular.setText("");
				textFieldEmail.setText("");
				textFieldRUT.setText("");
				textFieldCambiarNombre.setText("");
				textFieldTelefono.setText("");
				textFieldDireccionEnvio.setText("");
				textFieldDireccionEnvio.setText("");
				textFieldRazonSocial.setText("");			
				
				label_nombre.setVisible(false);
				label_celular.setVisible(false);
				label_email.setVisible(false);
				label_telefono.setVisible(false);
				label_rut.setVisible(false);
				label_razonSocial.setVisible(false);
				label_direccion.setVisible(false);
			}
		});
		btnLimpiarDat.setBounds(388, 256, 117, 25);
		panel_1.add(btnLimpiarDat);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipoCliente = (String) comboBox.getSelectedItem();
				
				if (tipoCliente != null) {
					if (tipoCliente.equals("CORPORATIVO")){						
						lblRut.setVisible(true);
						textFieldRUT.setVisible(true);
						lblRaznSocial.setVisible(true);
						textFieldRazonSocial.setVisible(true);
					}else {
						lblRut.setVisible(false);
						textFieldRUT.setVisible(false);
						lblRaznSocial.setVisible(false);
						textFieldRazonSocial.setVisible(false);		
						label_rut.setVisible(false);
						label_razonSocial.setVisible(false);
					}					
				}				
			}
		});
		comboBox.setBounds(32, 205, 194, 24);
		panel_1.add(comboBox);
		comboBox.addItem("CORPORATIVO");
		comboBox.addItem("CONSUMIDOR FINAL");
		
	}
	
	
	public String[] clientesToString() {
		// Retorna un array de clientes en el cual cada tupla esta formada por el nombre
		// Luego este array se pasa al jlist de clientes
		ControladorMantenimientoClientes controlador = new ControladorMantenimientoClientes();
		List<String> clientes = controlador.listarClientes();
		String[] tuplas = new String[clientes.size()];
		int i=0;
		for (String cli : clientes) {
			tuplas[i] = cli;
			i++;
		}
		return tuplas;
	}
	
	public void flitrarTuplasClientes(String[] tuplasClientes , String subCadena) {
		// Dado un array de tuplas de clientes y una subcadena va filtrando los nombres del jList
		boolean filtrar=false;
		DefaultListModel modeloListaClientes = new DefaultListModel<String>();
		for (int i=0; i < tuplasClientes.length;i++) {
			if (tuplasClientes[i].toUpperCase().startsWith(subCadena.toUpperCase())) {
				filtrar = true;
				modeloListaClientes.addElement(tuplasClientes[i]);
			}
		}
		if (filtrar) {
			listaClientes.setModel(modeloListaClientes);
		}		
	}
	
	public void listarClientes() {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		ControladorMantenimientoClientes controlador = new ControladorMantenimientoClientes();
		List<String> lista = controlador.listarClientes();
		if(lista.size() > 0) {
			Iterator<String> iterClientes = lista.iterator();
			while (iterClientes.hasNext()) {
				String cliente = iterClientes.next();
				modelo.addElement(cliente);				
			}
		}
		listaClientes.setModel(modelo);
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
}
