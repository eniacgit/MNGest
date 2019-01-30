package grafica;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.zxing.client.result.VCardResultParser;

import controladores.ControladorMantenimientoClientes;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import valueObjects.VOCliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class ReporteClientes extends JInternalFrame {
	private JTable tablaClientes;
	private DefaultTableModel modeloTabla;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReporteClientes frame = new ReporteClientes();
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
	public ReporteClientes() {
		setClosable(true);
		setTitle("Reporte de Clientes");
		setBounds(100, 100, 1139, 389);
		getContentPane().setLayout(null);
		
		JLabel lblTipoDeCliente = new JLabel("Tipo de Cliente");
		lblTipoDeCliente.setBounds(12, 12, 106, 15);
		getContentPane().add(lblTipoDeCliente);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloTabla = new DefaultTableModel();
				ControladorMantenimientoClientes controlador = new ControladorMantenimientoClientes();
				
				if (comboBox.getSelectedItem().equals("CONSUMIDOR FINAL")) {					
					modeloTabla.addColumn("Nombre");
					modeloTabla.addColumn("E-mail");
					modeloTabla.addColumn("Teléfono");
					modeloTabla.addColumn("Celular");					
					modeloTabla.addColumn("Dirección de envío");		
					
					List<VOCliente> listaClientes = controlador.listarTuplasClientes((String) comboBox.getSelectedItem());
					if (listaClientes.size()>0) {
						Iterator<VOCliente> iterClientes = listaClientes.iterator();
						while (iterClientes.hasNext()) {
							VOCliente cliente = iterClientes.next();
							Object[] fila = new Object[5];
							fila[0]= cliente.getNombre();
							fila[1]= cliente.getEmail();
							fila[2]= cliente.getTelefono();
							fila[3]= cliente.getCelular();
							fila[4]= cliente.getDireccion();
							modeloTabla.addRow(fila);
						}
						
					}
					
				}else {
					modeloTabla = new DefaultTableModel();
					modeloTabla.addColumn("Nombre");
					modeloTabla.addColumn("E-mail");
					modeloTabla.addColumn("Teléfono");
					modeloTabla.addColumn("Celular");
					modeloTabla.addColumn("RUT");
					modeloTabla.addColumn("Razón social");
					modeloTabla.addColumn("Dirección de envío");
					
					List<VOCliente> listaClientes = controlador.listarTuplasClientes((String) comboBox.getSelectedItem());
					if (listaClientes.size()>0) {
						Iterator<VOCliente> iterClientes = listaClientes.iterator();
						while (iterClientes.hasNext()) {
							VOCliente cliente = iterClientes.next();
							Object[] fila = new Object[7];
							fila[0]= cliente.getNombre();
							fila[1]= cliente.getEmail();
							fila[2]= cliente.getTelefono();
							fila[3]= cliente.getCelular();
							fila[4]= cliente.getRut();
							fila[5]= cliente.getRazonSocial();
							fila[6]= cliente.getDireccion();
							modeloTabla.addRow(fila);
						}
						
					}
					
				}
				tablaClientes.setModel(modeloTabla);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"CONSUMIDOR FINAL", "CORPORATIVO"}));
		comboBox.setBounds(12, 28, 189, 24);
		getContentPane().add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 64, 1105, 242);
		getContentPane().add(scrollPane);
		
		/*modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("E-mail");
		modeloTabla.addColumn("Teléfono");
		modeloTabla.addColumn("Celular");
		modeloTabla.addColumn("RUT");
		modeloTabla.addColumn("Razón social");
		modeloTabla.addColumn("Dirección de envío");*/
		
		//tablaClientes = new JTable(modeloTabla);
		tablaClientes = new JTable();
		scrollPane.setViewportView(tablaClientes);
		
		JButton btnGenerarReporte = new JButton("Generar reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("CONSUMIDOR FINAL")) {
					Map parametros = new HashMap();
					
					JasperPrint print;
					try {
						print = JasperFillManager.fillReport("reportes//jasper//clientes_consumidor_final.jasper", parametros,new JRBeanCollectionDataSource(getTuplasTablaClientesConsumidorFinal()));
						JasperExportManager.exportReportToPdfFile(print, "reportes//pdf//clientes_consumidor_final.pdf");
						JasperViewer.viewReport(print,false);
						
						
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else { // si es corporativo
					Map parametros = new HashMap();
					
					JasperPrint print;
					try {
						print = JasperFillManager.fillReport("reportes//jasper//clientes_corporativos.jasper", parametros,new JRBeanCollectionDataSource(getTuplasTablaClientesCorporativos()));
						JasperExportManager.exportReportToPdfFile(print, "reportes//pdf/clientes_corporativos.pdf");
						JasperViewer.viewReport(print,false);
						
						
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
			}
		});
		btnGenerarReporte.setBounds(801, 318, 150, 25);
		getContentPane().add(btnGenerarReporte);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteClientes.this.dispose();
			}
		});
		btnSalir.setBounds(1000, 318, 117, 25);
		getContentPane().add(btnSalir);

	}
	
	/// METODOS AUXILIARES ////////////////////////////////////////////////////////////////////////////////
	List<VOCliente> getTuplasTablaClientesConsumidorFinal(){
		List<VOCliente> tuplas = new ArrayList<VOCliente>();		
		for (int i=0; i< tablaClientes.getRowCount(); i++) {
			VOCliente cliente = new VOCliente();
			cliente.setNombre((String) tablaClientes.getValueAt(i, 0));
			cliente.setEmail((String) tablaClientes.getValueAt(i, 1));			
			cliente.setTelefono((String) tablaClientes.getValueAt(i,2));
			cliente.setCelular((String) tablaClientes.getValueAt(i, 3));
			cliente.setDireccion((String) tablaClientes.getValueAt(i, 4));
			tuplas.add(cliente);
		}
		return tuplas;
	}
	
	List<VOCliente> getTuplasTablaClientesCorporativos(){
		List<VOCliente> tuplas = new ArrayList<VOCliente>();		
		for (int i=0; i< tablaClientes.getRowCount(); i++) {
			VOCliente cliente = new VOCliente();
			cliente.setNombre((String) tablaClientes.getValueAt(i, 0));
			cliente.setEmail((String) tablaClientes.getValueAt(i, 1));			
			cliente.setTelefono((String) tablaClientes.getValueAt(i,2));
			cliente.setCelular((String) tablaClientes.getValueAt(i, 3));
			cliente.setRut((String) tablaClientes.getValueAt(i, 4));
			cliente.setRazonSocial((String) tablaClientes.getValueAt(i, 5));
			cliente.setDireccion((String) tablaClientes.getValueAt(i, 6));
			tuplas.add(cliente);
		}
		return tuplas;
	}
}
