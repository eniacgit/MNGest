package grafica;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controladores.ControladorMantenimientoMaterial;
import controladores.ControladorMantenimientoServicio;
import valueObjects.VOMaterial;
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
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MantenimientoMaterial extends JInternalFrame {
	private JTextField textField;
	private JTextField textFieldNombre;
	private JTextField textFieldCambiarNombre;
	private JLabel labelNombre;
	private JLabel labelCambiarNombre;
	private JList<String> listaMateriales;
	private JTextArea textAreaDescripcion;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MantenimientoMaterial frame = new MantenimientoMaterial();
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
	public MantenimientoMaterial() {
		setClosable(true);
		setTitle("Mantenimiento de materiales");
		setBounds(100, 100, 783, 361);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Lista de materiales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 279, 305);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(12, 31, 59, 15);
		panel.add(lblMaterial);
		
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
						flitrarTuplasMateriales(materialesToString(),txt.toString());
					}
				}else {
					txt.append(car);
					flitrarTuplasMateriales(materialesToString(),txt.toString());
				}
			}
		});
		textField.setBounds(85, 26, 180, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 58, 253, 235);
		panel.add(scrollPane);
		
		listaMateriales = new JList(this.materialesToString());
		listaMateriales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// limpio etiquetas de error
				labelNombre.setVisible(false);
				labelCambiarNombre.setVisible(false);
				
				ControladorMantenimientoMaterial controlador = new ControladorMantenimientoMaterial();
				String nombre = listaMateriales.getSelectedValue();
				
				textFieldNombre.setText(nombre);
				VOMaterial material = controlador.obtenerDatosMaterial(nombre);
				textAreaDescripcion.setText(material.getDescripcion());
			}
		});
		scrollPane.setViewportView(listaMateriales);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Alta / Modificaci\u00F3n de materiales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
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
		labelNombre.setIcon(new ImageIcon(MantenimientoMaterial.class.getResource("/grafica/imagenes/Error.png")));
		labelNombre.setBounds(210, 52, 20, 20);
		panel_1.add(labelNombre);
		labelNombre.setVisible(false);
		
		labelCambiarNombre = new JLabel("");
		labelCambiarNombre.setIcon(new ImageIcon(MantenimientoMaterial.class.getResource("/grafica/imagenes/Error.png")));
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
				
				ControladorMantenimientoMaterial controlador = new ControladorMantenimientoMaterial();
				
				// VALIDACION NOMBRE
				if (nombre.isEmpty()) {
					labelNombre.setVisible(true);
					labelNombre.setToolTipText("No ha escrito el nombre del Material.");
					error = true;
				}else {
					labelNombre.setVisible(false);
					if (controlador.existeMaterial(nombre)) {
						labelNombre.setVisible(true);
						labelNombre.setToolTipText("Ya existe un Material con ese nombre.");
						error = true;
					}else {
						labelNombre.setVisible(false);
					}
				}
				
				if (!error) {
					VOMaterial material = new VOMaterial(nombre, descripcion);
					controlador.insertarMaterial(material);
					listarMateriales();
					JOptionPane.showMessageDialog(getContentPane(), "Material ingresado con éxito.", "Ingreso existoso", JOptionPane.PLAIN_MESSAGE);
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
				
				ControladorMantenimientoMaterial controlador = new ControladorMantenimientoMaterial();
				
				// VALIDACION NOMBRE
				if (nombre.isEmpty()) {
					labelNombre.setVisible(true);
					labelNombre.setToolTipText("Debe seleccinar un material a modificar.");
					error = true;
				}
				
				if (cambiarNombre.isEmpty()) {
					labelCambiarNombre.setVisible(true);
					labelCambiarNombre.setToolTipText("El nombre a cambiar no debe estar vacío.");
					error=true;
				}else {
					if (controlador.existeMaterial(cambiarNombre)) {
						labelCambiarNombre.setVisible(true);
						labelCambiarNombre.setToolTipText("El nombre a cambiar ya existe.");
						error=true;
					}else
						labelCambiarNombre.setVisible(false);
				}
												
				if (!error) {
					VOMaterial material = new VOMaterial(cambiarNombre, descripcion);
					controlador.modificarMterial(nombre, material);
					listarMateriales();
					JOptionPane.showMessageDialog(getContentPane(), "Material modificado con éxito.", "Ingreso existoso", JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		});
		btnModificar.setBounds(156, 268, 117, 25);
		panel_1.add(btnModificar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MantenimientoMaterial.this.dispose();
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
	public String[] materialesToString() {
		// Retorna un array de materiales en el cual cada tupla esta formada por el nombre
		// Luego este array se pasa al jlist de materiales
		ControladorMantenimientoMaterial controlador = new ControladorMantenimientoMaterial();		
		List<String> materiales = controlador.listarMateriales();
		String[] tuplas = new String[materiales.size()];
		int i=0;
		for (String serv : materiales) {
			tuplas[i] = serv;
			i++;
		}
		return tuplas;
	}
	
	
	public void listarMateriales() {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		ControladorMantenimientoMaterial controlador = new ControladorMantenimientoMaterial();
		List<String> lista = controlador.listarMateriales();
		if(lista.size() > 0) {
			Iterator<String> iterMateriales = lista.iterator();
			while (iterMateriales.hasNext()) {
				String material = iterMateriales.next();
				modelo.addElement(material);				
			}
		}
		listaMateriales.setModel(modelo);
	}
	
	public void flitrarTuplasMateriales(String[] tuplasMateriales , String subCadena) {
		// Dado un array de tuplas de materiales y una subcadena va filtrando los nombres del jList
		boolean filtrar=false;
		DefaultListModel<String> modeloListaMateriales = new DefaultListModel<String>();
		for (int i=0; i < tuplasMateriales.length;i++) {
			if (tuplasMateriales[i].toUpperCase().startsWith(subCadena.toUpperCase())) {
				filtrar = true;
				modeloListaMateriales.addElement(tuplasMateriales[i]);
			}
		}
		if (filtrar) {
			listaMateriales.setModel(modeloListaMateriales);
		}		
	}
	
	
	
}
