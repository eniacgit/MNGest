package grafica;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controladores.ControladorMantenimientoClientes;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class MantenimientoCliente extends JInternalFrame {
	private JTextField textField;
	private JList list;

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
		setIconifiable(true);
		setResizable(true);
		setTitle("Alta y Modificación de Clientes");
		setMaximizable(true);
		setBounds(100, 100, 536, 367);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Lista de Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 230, 311);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 29, 55, 15);
		panel.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(71, 25, 147, 23);
		panel.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 56, 206, 243);
		panel.add(scrollPane);
		
		JList list = new JList(this.clientesToString());
		scrollPane.setViewportView(list);
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
	
	
	
}
