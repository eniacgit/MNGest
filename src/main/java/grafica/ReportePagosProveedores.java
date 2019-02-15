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
import valueObjects.VOCliente;
import valueObjects.VODetalleProveedor;
import valueObjects.VODetallesProducto;
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
import javax.swing.SwingConstants;

public class ReportePagosProveedores extends JInternalFrame {
	private JTable tablaProveedores;
	private DefaultTableModel modeloTabla;
    private JDateChooser dateChooserInicio;
    private JDateChooser dateChooserFin;
    private JTextField textFieldOrden;
    private JTextField textFieldCliente;
    private JTextField textFieldTipoCliente;
    private JTextField textFieldProducto;
    private JTextField textFieldCategoria;
    private JTextField textFieldUnidades;
    private JTextField textFieldFechaProduccion;
    private JTextField textFieldMoneda;
    private JTextField textFieldTotal;
    private JList<String> listaClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportePagosProveedores frame = new ReportePagosProveedores();
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
	public ReportePagosProveedores() {
		setClosable(true);
		setTitle("Reporte de pago a proveedores");
		setBounds(100, 100, 950, 512);
		getContentPane().setLayout(null);
		
		// COLUMNAS DE LA TABLA VENTAS
		modeloTabla = new DefaultTableModel();
		
		
		modeloTabla.addColumn("Proveedor");
		modeloTabla.addColumn("Trabajo realizado");
		modeloTabla.addColumn("Moneda");
		modeloTabla.addColumn("Costo");
		
		
		
		JButton btnGenerarReporte = new JButton("Generar reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				Map parametros = new HashMap();
				parametros.put("orden", textFieldOrden.getText());
				parametros.put("cliente",textFieldCliente.getText());
				parametros.put("tipoCliente",textFieldTipoCliente.getText());
				parametros.put("producto", textFieldProducto.getText());
				parametros.put("categoria", textFieldCategoria.getText());
				parametros.put("unidades",textFieldUnidades.getText());
				parametros.put("fechaProduccion", textFieldFechaProduccion.getText());
				parametros.put("moneda", textFieldMoneda.getText());
				parametros.put("total", textFieldTotal.getText());
				
				JasperPrint print;
				try {
					print = JasperFillManager.fillReport("reportes//jasper//pagoProveedores.jasper", parametros,new JRBeanCollectionDataSource(getTuplasTablaProveedores()));
					JasperExportManager.exportReportToPdfFile(print, "reportes//pdf//pagoProveedores.pdf");
					JasperViewer.viewReport(print,false);
				
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
					
					
					
				
			}
		});
		btnGenerarReporte.setBounds(623, 443, 150, 25);
		getContentPane().add(btnGenerarReporte);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePagosProveedores.this.dispose();
			}
		});
		btnSalir.setBounds(811, 443, 117, 25);
		getContentPane().add(btnSalir);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Rango de fechas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 23, 315, 123);
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
		
		JButton btnNewButton = new JButton("Consultar pagos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((dateChooserInicio.getCalendar() == null) || (dateChooserFin.getCalendar() == null)) {
					JOptionPane.showMessageDialog(getContentPane(), "Los campos de fecha no pueden quedar en blanco.", "Error en el rango de fechas.", JOptionPane.ERROR_MESSAGE);
				}else{
					String fechaInicio = dateChooserToFechaString(dateChooserInicio);
					String fechaFin = dateChooserToFechaString(dateChooserFin);
					
					if (esMayorOIgualFecha(fechaInicio, fechaFin)) {
						JOptionPane.showMessageDialog(getContentPane(), "La fecha de inicio debe ser menor o igual que la final.", "Error en el rango de fechas.", JOptionPane.ERROR_MESSAGE);						
					}else {
						listarClientes(fechaInicio, fechaFin);
					}
					
				}
				
				
			}
		});
		btnNewButton.setBounds(12, 83, 285, 25);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 160, 315, 263);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 27, 291, 224);
		panel_1.add(scrollPane_1);
		
		listaClientes = new JList();
		listaClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!listaClientes.isSelectionEmpty()) {
					ControladorPagoProveedores controlador = new ControladorPagoProveedores();
					String cotizacionCliente = listaClientes.getSelectedValue();
					
					String[] parts = cotizacionCliente.split(":");
					String orden = parts[0].trim();
					String cliente = parts[1].trim();
					
					textFieldOrden.setText(orden);
					textFieldCliente.setText(cliente);
					textFieldTipoCliente.setText(controlador.obtenerTipoCliente(cliente));
					
					VODetallesProducto producto = controlador.obtenerDetallesProducto(orden);
					textFieldProducto.setText(producto.getNombre());
					textFieldCategoria.setText(producto.getCategoria());
					textFieldUnidades.setText(producto.getUnidades());
					textFieldFechaProduccion.setText(controlador.formatearFecha(producto.getFechaProduccion()));
					textFieldMoneda.setText(producto.getMoneda());
					textFieldTotal.setText(producto.getTotal());
				
					
					List<VODetalleProveedor> listaProveedores = controlador.obtenerListaProveedores(orden);
					if (listaProveedores.size() >0) {
						modeloTabla.setRowCount(0); // limpio la tabla
						Iterator<VODetalleProveedor> iterProveedores = listaProveedores.iterator();
						while (iterProveedores.hasNext()) {
							VODetalleProveedor proveedor = iterProveedores.next();
							Object[] fila = new Object[4];
							fila[0] = proveedor.getNombre();
							fila[1] = proveedor.getTrabajoRealizado();
							fila[2] = proveedor.getMoneda();
							fila[3] = proveedor.getCosto();
							modeloTabla.addRow(fila);
						}						
					}
					
					tablaProveedores.setModel(modeloTabla);
					
				}
			}
		});
		scrollPane_1.setViewportView(listaClientes);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Cliente & Producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_2.setBounds(339, 23, 589, 218);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Órden");
		lblNewLabel.setBounds(12, 27, 44, 15);
		panel_2.add(lblNewLabel);
		
		textFieldOrden = new JTextField();
		textFieldOrden.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldOrden.setEditable(false);
		textFieldOrden.setBounds(12, 46, 114, 25);
		panel_2.add(textFieldOrden);
		textFieldOrden.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(162, 27, 238, 15);
		panel_2.add(lblCliente);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCliente.setEditable(false);
		textFieldCliente.setColumns(10);
		textFieldCliente.setBounds(162, 46, 238, 25);
		panel_2.add(textFieldCliente);
		
		JLabel lblTipoDeCliente = new JLabel("Tipo de Cliente");
		lblTipoDeCliente.setBounds(428, 27, 149, 15);
		panel_2.add(lblTipoDeCliente);
		
		textFieldTipoCliente = new JTextField();
		textFieldTipoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTipoCliente.setEditable(false);
		textFieldTipoCliente.setColumns(10);
		textFieldTipoCliente.setBounds(428, 46, 149, 25);
		panel_2.add(textFieldTipoCliente);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 83, 565, 2);
		panel_2.add(separator);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(12, 108, 114, 15);
		panel_2.add(lblProducto);
		
		textFieldProducto = new JTextField();
		textFieldProducto.setEditable(false);
		textFieldProducto.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldProducto.setColumns(10);
		textFieldProducto.setBounds(12, 127, 300, 25);
		panel_2.add(textFieldProducto);
		
		JLabel lblCategora = new JLabel("Categoría");
		lblCategora.setBounds(333, 108, 149, 15);
		panel_2.add(lblCategora);
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setEditable(false);
		textFieldCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCategoria.setColumns(10);
		textFieldCategoria.setBounds(333, 127, 149, 25);
		panel_2.add(textFieldCategoria);
		
		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setBounds(500, 108, 77, 15);
		panel_2.add(lblUnidades);
		
		textFieldUnidades = new JTextField();
		textFieldUnidades.setEditable(false);
		textFieldUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUnidades.setColumns(10);
		textFieldUnidades.setBounds(500, 127, 77, 25);
		panel_2.add(textFieldUnidades);
		
		JLabel lblFechaDeProduccin = new JLabel("Fecha de producción");
		lblFechaDeProduccin.setBounds(12, 162, 146, 15);
		panel_2.add(lblFechaDeProduccin);
		
		textFieldFechaProduccion = new JTextField();
		textFieldFechaProduccion.setEditable(false);
		textFieldFechaProduccion.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFechaProduccion.setColumns(10);
		textFieldFechaProduccion.setBounds(12, 181, 163, 25);
		panel_2.add(textFieldFechaProduccion);
		
		JLabel lblMoneda = new JLabel("Moneda");
		lblMoneda.setBounds(199, 162, 57, 15);
		panel_2.add(lblMoneda);
		
		textFieldMoneda = new JTextField();
		textFieldMoneda.setEditable(false);
		textFieldMoneda.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMoneda.setColumns(10);
		textFieldMoneda.setBounds(199, 181, 114, 25);
		panel_2.add(textFieldMoneda);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(333, 162, 57, 15);
		panel_2.add(lblTotal);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setEditable(false);
		textFieldTotal.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTotal.setColumns(10);
		textFieldTotal.setBounds(333, 181, 114, 25);
		panel_2.add(textFieldTotal);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Proveedores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(339, 264, 589, 159);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 26, 565, 121);
		panel_3.add(scrollPane);
		
	
		
		tablaProveedores = new JTable(modeloTabla);
		tablaProveedores.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
		scrollPane.setViewportView(tablaProveedores);

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
	
	// retorna una lista de clientes comprendidos entre 2 fechas
	private void listarClientes(String fechaInicio, String fechaFin) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		ControladorPagoProveedores controlador = new ControladorPagoProveedores();
		List<String> lista = controlador.listarClientesRangoFechaProduccion(fechaInicio, fechaFin);
		if(lista.size() > 0) {
			Iterator<String> iterClientes = lista.iterator();
			while (iterClientes.hasNext()) {
				String cliente = iterClientes.next();
				modelo.addElement(cliente);				
			}
		}
		listaClientes.setModel(modelo);
	}
	
	List<VOTablaProveedores> getTuplasTablaProveedores(){
		List<VOTablaProveedores> tuplas = new ArrayList<VOTablaProveedores>();
		for (int i=0; i< tablaProveedores.getRowCount(); i++) {
			VOTablaProveedores proveedor = new VOTablaProveedores();
			proveedor.setProveedor((String) tablaProveedores.getValueAt(i, 0));
			proveedor.setTrabajoRealizado((String) tablaProveedores.getValueAt(i, 1));
			proveedor.setMoneda((String) tablaProveedores.getValueAt(i, 2));
			proveedor.setCosto((String) tablaProveedores.getValueAt(i, 3));
			tuplas.add(proveedor);
		}		
		return tuplas;
	}
		
}
