package grafica;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.springframework.core.enums.LabeledEnum;

import controladores.ControladorMantenimientoClientes;
import controladores.ControladorMantenimientoServicio;
import valueObjects.VOCliente;
import valueObjects.VOServicio;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MantenimientoServicio extends JInternalFrame {
	private JTextField textField;
	private JTextField textFieldNombre;
	private JTextField textFieldCambiarNombre;
	private JLabel labelNombre;
	private JLabel labelCambiarNombre;
	private JTextArea textAreaDescripcion;
	private JList<String> listaServicios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MantenimientoServicio frame = new MantenimientoServicio();
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
	public MantenimientoServicio() {
		setClosable(true);
		setTitle("Mantenimiento de servicios");
		setBounds(100, 100, 783, 361);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Lista de servicios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 279, 305);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblServicio = new JLabel("Servicio");
		lblServicio.setBounds(12, 31, 55, 15);
		panel.add(lblServicio);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			StringBuffer txt = new StringBuffer();
			@Override
			public void keyTyped(KeyEvent e) {
				char car = e.getKeyChar();
				if (car == KeyEvent.VK_BACK_SPACE) {
					e.consume();
					if (txt.length() >=1) {
						txt.setLength(txt.length()-1);
						flitrarTuplasServicios(serviciosToString(),txt.toString());
					}
				}else {
					txt.append(car);
					flitrarTuplasServicios(serviciosToString(),txt.toString());
				}
				
			}
		});
		textField.setBounds(85, 26, 180, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 58, 253, 235);
		panel.add(scrollPane);
		
		listaServicios = new JList(this.serviciosToString());
		listaServicios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// limpio etiquetas de error
				labelNombre.setVisible(false);
				labelCambiarNombre.setVisible(false);
				
				ControladorMantenimientoServicio controlador = new ControladorMantenimientoServicio();
				String nombre = listaServicios.getSelectedValue();
				
				textFieldNombre.setText(nombre);
				VOServicio servicio = controlador.obtenerDatosServicio(nombre);
				textAreaDescripcion.setText(servicio.getDescripcion());
			}
		});
		scrollPane.setViewportView(listaServicios);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Alta / Modificaci\u00F3n de servicios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(303, 12, 457, 305);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre *");
		lblNombre.setBounds(27, 30, 65, 15);
		panel_1.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(27, 49, 180, 26);
		panel_1.add(textFieldNombre);
		
		JLabel lblCambiarNombrePor = new JLabel("Cambiar nombre por");
		lblCambiarNombrePor.setBounds(242, 27, 144, 15);
		panel_1.add(lblCambiarNombrePor);
		
		textFieldCambiarNombre = new JTextField();
		textFieldCambiarNombre.setColumns(10);
		textFieldCambiarNombre.setBounds(242, 49, 180, 26);
		panel_1.add(textFieldCambiarNombre);
		
		labelNombre = new JLabel("");
		labelNombre.setIcon(new ImageIcon(MantenimientoServicio.class.getResource("/resources/Error.png")));
		labelNombre.setBounds(210, 52, 20, 20);
		panel_1.add(labelNombre);
		labelNombre.setVisible(false);
		
		labelCambiarNombre = new JLabel("");
		labelCambiarNombre.setIcon(new ImageIcon(MantenimientoServicio.class.getResource("/resources/Error.png")));
		labelCambiarNombre.setBounds(425, 52, 20, 20);
		panel_1.add(labelCambiarNombre);
		labelCambiarNombre.setVisible(false);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(27, 98, 82, 15);
		panel_1.add(lblDescripcin);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldNombre.getText().toUpperCase();
				String descripcion = textAreaDescripcion.getText();
				
				boolean error = false;
				
				ControladorMantenimientoServicio controlador = new ControladorMantenimientoServicio();
				
				// VALIDACION NOMBRE
				if (nombre.isEmpty()) {
					labelNombre.setVisible(true);
					labelNombre.setToolTipText("No ha escrito el nombre del Servicio.");
					error = true;
				}else {
					labelNombre.setVisible(false);
					if (controlador.existeServicio(nombre)) {
						labelNombre.setVisible(true);
						labelNombre.setToolTipText("Ya existe un Servicio con ese nombre.");
						error = true;
					}else {
						labelNombre.setVisible(false);
					}
				}
				
				if (!error) {
					VOServicio servicio = new VOServicio(nombre, descripcion);
					controlador.insertarServicio(servicio);
					listarServicios();
					JOptionPane.showMessageDialog(getContentPane(), "Servicio ingresado con éxito.", "Ingreso existoso", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnAlta.setBounds(27, 268, 117, 25);
		panel_1.add(btnAlta);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldNombre.getText().toUpperCase();
				String descripcion = textAreaDescripcion.getText();
				String cambiarNombre = textFieldCambiarNombre.getText().toUpperCase();
				
				boolean error = false;
				
				ControladorMantenimientoServicio controlador = new ControladorMantenimientoServicio();
				
				// VALIDACION NOMBRE
				if (nombre.isEmpty()) {
					labelNombre.setVisible(true);
					labelNombre.setToolTipText("Debe seleccinar un servicio a modificar.");
					error = true;
				}
				
				if (cambiarNombre.isEmpty()) {
					labelCambiarNombre.setVisible(true);
					labelCambiarNombre.setToolTipText("El nombre a cambiar no debe estar vacío.");
					error=true;
				}else {
					if (controlador.existeServicio(cambiarNombre)) {
						labelCambiarNombre.setVisible(true);
						labelCambiarNombre.setToolTipText("El nombre a cambiar ya existe.");
						error=true;
					}else
						labelCambiarNombre.setVisible(false);
				}
												
				if (!error) {
					VOServicio servicio = new VOServicio(cambiarNombre, descripcion);
					controlador.modificarServicio(nombre, servicio);
					listarServicios();
					JOptionPane.showMessageDialog(getContentPane(), "Servicio modificado con éxito.", "Ingreso existoso", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnModificar.setBounds(156, 268, 117, 25);
		panel_1.add(btnModificar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MantenimientoServicio.this.dispose();
			}
		});
		btnSalir.setBounds(325, 268, 117, 25);
		panel_1.add(btnSalir);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(27, 122, 395, 111);
		panel_1.add(scrollPane_1);
		
		textAreaDescripcion = new JTextArea();
		scrollPane_1.setViewportView(textAreaDescripcion);
	}
	
	// 	FUNCIONES AUXILIARES
	public String[] serviciosToString() {
		// Retorna un array de servicios en el cual cada tupla esta formada por el nombre
		// Luego este array se pasa al jlist de clientes
		ControladorMantenimientoServicio controlador = new ControladorMantenimientoServicio();
		List<String> servicios = controlador.listarServicio();
		String[] tuplas = new String[servicios.size()];
		int i=0;
		for (String serv : servicios) {
			tuplas[i] = serv;
			i++;
		}
		return tuplas;
	}
	
	
	public void listarServicios() {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		ControladorMantenimientoServicio controlador = new ControladorMantenimientoServicio();
		List<String> lista = controlador.listarServicio();
		if(lista.size() > 0) {
			Iterator<String> iterServicios = lista.iterator();
			while (iterServicios.hasNext()) {
				String servicio = iterServicios.next();
				modelo.addElement(servicio);				
			}
		}
		listaServicios.setModel(modelo);
	}
	
	public void flitrarTuplasServicios(String[] tuplasServicios , String subCadena) {
		// Dado un array de tuplas de servicios y una subcadena va filtrando los nombres del jList
		boolean filtrar=false;
		DefaultListModel<String> modeloListaServicios = new DefaultListModel<String>();
		for (int i=0; i < tuplasServicios.length;i++) {
			if (tuplasServicios[i].toUpperCase().startsWith(subCadena.toUpperCase())) {
				filtrar = true;
				modeloListaServicios.addElement(tuplasServicios[i]);
			}
		}
		if (filtrar) {
			listaServicios.setModel(modeloListaServicios);
		}		
	}
}
