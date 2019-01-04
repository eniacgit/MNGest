package grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DesktopPaneUI;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private MantenimientoCliente mantenimientoCliente; // No instancio las ventanas para ahorrar memoria
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setTitle("MNGest (C) 2019");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1355, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Sistema");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cerrar sesión");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal.this.dispose();
			}
		});
		mnNewMenu.add(mntmSalir);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Alta/Modificación");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(mantenimientoCliente instanceof MantenimientoCliente)) {
					mantenimientoCliente = new MantenimientoCliente();
				}
				centrarVentanaInterna(mantenimientoCliente);
			}
		});
		mnClientes.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Reporte");
		mnClientes.add(mntmNewMenuItem_2);
		
		JMenu mnProveedores = new JMenu("Proveedores");
		menuBar.add(mnProveedores);
		
		JMenu mnMateriales = new JMenu("Materiales");
		menuBar.add(mnMateriales);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 25, 1343, 677);
		contentPane.add(desktopPane);
		//desktopPane.setSize(getMaximumSize());
		this.setExtendedState(MAXIMIZED_BOTH);
		
	}
	
	// Métodos auxiliares
	private void centrarVentanaInterna(JInternalFrame internalFrame) {
		int x = (desktopPane.getWidth() /2) - internalFrame.getWidth() /2;
		int y = (desktopPane.getHeight() /2) - internalFrame.getHeight() /2;
		
		// Compruebo si la ventana está siendo ejecutada
		if (internalFrame.isShowing()) {
			internalFrame.setLocation(x, y);
		}else {
			desktopPane.add(internalFrame);
			internalFrame.setLocation(x, y);
			internalFrame.show();
		}
	}
}
