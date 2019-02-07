package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private MantenimientoCliente mantenimientoCliente; // No instancio las ventanas para ahorrar memoria
	private ReporteClientes reporteClientes; 
	private MantenimientoServicio mantenimientoServicio;
	private MantenimientoMaterial mantenimientoMaterial; 
	private MantenimientoProveedor mantenimientoProveedor;
	private ReporteVentas reporteVentas;
	private ReportePagosProveedores reportePagosProveedores;
	private CorreoPosventa correoPosventa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		
		System.out.println("ANCHO: " + obtenerAnchoPantalla());
		System.out.println("ALTO: " + obtenerAltoPantalla());
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
		menuBar.setBounds(0, 0, (int) obtenerAnchoPantalla(), 21);
		//menuBar.setMinimumSize(obtenerAnchoPantalla());; // ajusto menuBar al largo de la pantalla
		contentPane.add(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmCerrarSesin = new JMenuItem("Cerrar sesión");
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaLogin plogin = new PantallaLogin();
				plogin.setVisible(true);
				Principal.this.dispose();
			}
		});
		mnSistema.add(mntmCerrarSesin);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal.this.dispose();
			}
		});
		mnSistema.add(mntmSalir);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmAltamodificacin = new JMenuItem("Alta/Modificación");
		mntmAltamodificacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(mantenimientoCliente instanceof MantenimientoCliente)) {
					mantenimientoCliente = new MantenimientoCliente();
				}
				centrarVentanaInterna(mantenimientoCliente);
			}
		});
		mnClientes.add(mntmAltamodificacin);
		
		JMenuItem mntmReportes = new JMenuItem("Reportes");
		mntmReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(reporteClientes instanceof ReporteClientes)) {
					reporteClientes = new ReporteClientes();
				}
				centrarVentanaInterna(reporteClientes);				
			}
		});
		mnClientes.add(mntmReportes);
		
		JMenu mnProveedores = new JMenu("Proveedores");
		menuBar.add(mnProveedores);
		
		JMenuItem mntmServicios = new JMenuItem("Servicios");
		mntmServicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(mantenimientoServicio instanceof MantenimientoServicio)) {
					mantenimientoServicio = new MantenimientoServicio();
				}
				centrarVentanaInterna(mantenimientoServicio);				
			
			}
		});
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Alta / Modificación");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!(mantenimientoProveedor instanceof MantenimientoProveedor)) {
					mantenimientoProveedor = new MantenimientoProveedor();
				}
				centrarVentanaInterna(mantenimientoProveedor);
			}
		});
		mnProveedores.add(mntmNewMenuItem);
		mnProveedores.add(mntmServicios);
		
		JMenuItem mntmMateriales = new JMenuItem("Materiales");
		mntmMateriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(mantenimientoMaterial instanceof MantenimientoMaterial)) {
					mantenimientoMaterial = new MantenimientoMaterial();
				}
				centrarVentanaInterna(mantenimientoMaterial);				
			}
		});
		mnProveedores.add(mntmMateriales);
		
		JMenu mnNewMenu = new JMenu("Ventas");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmReporteDeVentas = new JMenuItem("Reporte de Ventas");
		mntmReporteDeVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!(reporteVentas instanceof ReporteVentas)) {
					reporteVentas = new ReporteVentas();
				}
				centrarVentanaInterna(reporteVentas);	
			}
		});
		mnNewMenu.add(mntmReporteDeVentas);
		
		JMenuItem mntmReporteDePagos_1 = new JMenuItem("Pagos a Proveedores");
		mntmReporteDePagos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (!(reportePagosProveedores instanceof ReportePagosProveedores)) {
					reportePagosProveedores = new ReportePagosProveedores();
				}
				centrarVentanaInterna(reportePagosProveedores);
			}
		});
		mnNewMenu.add(mntmReporteDePagos_1);
		
		JMenuItem mntmCorreoPosve = new JMenuItem("Correo Posventa");
		mntmCorreoPosve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(correoPosventa instanceof CorreoPosventa)) {
					correoPosventa = new CorreoPosventa();
				}
				centrarVentanaInterna(correoPosventa);
				
			}
		});
		mnNewMenu.add(mntmCorreoPosve);
		
		desktopPane = new JDesktopPane();
		//desktopPane.setBounds(0, 26, 394, 244);
		desktopPane.setBounds(0, 26, (int) obtenerAnchoPantalla(), (int) obtenerAltoPantalla());
		
		contentPane.add(desktopPane);
		this.setExtendedState(MAXIMIZED_BOTH);
	}
	

	// Métodos auxiliares //////////////////////////////////////////////////////////////////////////////////////////////////
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
	
	private static double obtenerAnchoPantalla() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension tamanio = tk.getScreenSize();
		return tamanio.getWidth();
		
	}
	
	private static double obtenerAltoPantalla() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension tamanio = tk.getScreenSize();
		return tamanio.getHeight();
		
	}
}
