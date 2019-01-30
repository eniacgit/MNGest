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

import controladores.ControladorVentas;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import valueObjects.VOCliente;
import valueObjects.VOVentas;

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

public class ReporteVentas extends JInternalFrame {
	private JTable tablaVentas;
	private DefaultTableModel modeloTabla;
    private JDateChooser dateChooserInicio;
    private JDateChooser dateChooserFin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReporteVentas frame = new ReporteVentas();
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
	public ReporteVentas() {
		setClosable(true);
		setTitle("Reporte de Ventas");
		setBounds(100, 100, 1139, 389);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 64, 1105, 242);
		getContentPane().add(scrollPane);
		
		// COLUMNAS DE LA TABLA VENTAS
		modeloTabla = new DefaultTableModel();
		
		
		modeloTabla.addColumn("Orden");
		modeloTabla.addColumn("Cliente");
		modeloTabla.addColumn("T. Cliente");
		modeloTabla.addColumn("Producto");
		modeloTabla.addColumn("Categoría");
		modeloTabla.addColumn("Unidades");
		modeloTabla.addColumn("Moneda");
		modeloTabla.addColumn("Total");
		modeloTabla.addColumn("F. producción");
		modeloTabla.addColumn("F. entrega");
	
		
		tablaVentas = new JTable(modeloTabla);
		tablaVentas.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
		scrollPane.setViewportView(tablaVentas);
		
		
		
		JButton btnGenerarReporte = new JButton("Generar reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				ControladorVentas controlador = new ControladorVentas();
				Map parametros = new HashMap();
				String fechaInicio = controlador.formatearFecha(dateChooserToFechaString(dateChooserInicio));
				String fechaFin = controlador.formatearFecha(dateChooserToFechaString(dateChooserFin));
				parametros.put("fechaInicio", fechaInicio);
				parametros.put("fechaFin", fechaFin);
				JasperPrint print;
				
				try {
					print = JasperFillManager.fillReport("reportes//jasper//ventas.jasper", parametros,new JRBeanCollectionDataSource(getTuplasTablaVentas()));
					JasperExportManager.exportReportToPdfFile(print, "reportes//pdf//ventas.pdf");
					JasperViewer.viewReport(print,false);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
					
					
				
			}
		});
		btnGenerarReporte.setBounds(801, 318, 150, 25);
		getContentPane().add(btnGenerarReporte);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteVentas.this.dispose();
			}
		});
		btnSalir.setBounds(1000, 318, 117, 25);
		getContentPane().add(btnSalir);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio *");
		lblFechaDeInicio.setBounds(12, 28, 115, 15);
		getContentPane().add(lblFechaDeInicio);
		
		/*textField = new JTextField();
		textField.setBounds(135, 19, 114, 32);
		getContentPane().add(textField);
		textField.setColumns(10);
		*/
		
		dateChooserInicio = new JDateChooser();
		dateChooserInicio.setBackground(new Color(255,255,204));
		dateChooserInicio.setBounds(135, 19, 194, 32);
		getContentPane().add(dateChooserInicio);
		
		JLabel lblFechaDeFin = new JLabel("Fecha de fin *");
		lblFechaDeFin.setBounds(385, 28, 96, 15);
		getContentPane().add(lblFechaDeFin);
		
		dateChooserFin = new JDateChooser();
		dateChooserFin.setBackground(new Color(255, 255, 204));
		dateChooserFin.setBounds(508, 19, 194, 32);
		getContentPane().add(dateChooserFin);
		
		JButton btnListarVentas = new JButton("Listar ventas");
		btnListarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((dateChooserInicio.getCalendar() == null) || (dateChooserFin.getCalendar() == null)) {
					JOptionPane.showMessageDialog(getContentPane(), "Los campos de fecha no pueden quedar en blanco.", "Error en el rango de fechas.", JOptionPane.ERROR_MESSAGE);
				}else {
					String fechaInicio = dateChooserToFechaString(dateChooserInicio);
					String fechaFin = dateChooserToFechaString(dateChooserFin);
					
					System.out.println(fechaInicio);
					System.out.println(fechaFin);
					
					if (esMayorOIgualFecha(fechaInicio, fechaFin)) {
						JOptionPane.showMessageDialog(getContentPane(), "La fecha de inicio debe ser menor o igual que la final.", "Error en el rango de fechas.", JOptionPane.ERROR_MESSAGE);						
					}else {
						ControladorVentas controlador = new ControladorVentas();
						
						List<VOVentas> listaVentas = controlador.listarTuplasVentas(fechaInicio, fechaFin);
						if (listaVentas.size()>0) {
							Iterator<VOVentas> iterVentas = listaVentas.iterator();
							while (iterVentas.hasNext()) {
								VOVentas venta = iterVentas.next();
								Object[] fila = new Object[10];
								fila[0] = venta.getorden();
								fila[1] = venta.getnombreCliente();
								fila[2] = venta.getTipoCliente();
								fila[3] = venta.getNombreProducto();
								fila[4] = venta.getCategoria();
								fila[5] = venta.getCantidad();
								fila[6] = venta.getMoneda();
								fila[7] = venta.getTotal();
								fila[8] = controlador.formatearFecha(venta.getfechaProduccion());
								fila[9] = controlador.formatearFecha(venta.getFechaEntrega());
								modeloTabla.addRow(fila);
							}
							
						}
						
					}
						
					
					
				}
			}
		});
		btnListarVentas.setBounds(801, 23, 150, 25);
		getContentPane().add(btnListarVentas);

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
	
	// Convierte una jDateChooser a String con frmato
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
	
	
	
	
	
	List<VOVentas> getTuplasTablaVentas(){
		List<VOVentas> tuplas = new ArrayList<VOVentas>();		
		for (int i=0; i< tablaVentas.getRowCount(); i++) {
			VOVentas venta = new VOVentas();
			venta.setorden((String) tablaVentas.getValueAt(i, 0));
			venta.setnombreCliente((String) tablaVentas.getValueAt(i, 1));
			venta.setTipoCliente((String) tablaVentas.getValueAt(i,2));
			venta.setNombreProducto((String) tablaVentas.getValueAt(i,3));
			venta.setCategoria((String) tablaVentas.getValueAt(i,4));
			venta.setCantidad((String) tablaVentas.getValueAt(i,5));
			venta.setMoneda((String) tablaVentas.getValueAt(i,6));
			venta.setTotal((String) tablaVentas.getValueAt(i,7));
			venta.setfechaProduccion((String) tablaVentas.getValueAt(i,8));
			venta.setFechaEntrega((String) tablaVentas.getValueAt(i,9));
			tuplas.add(venta);
		}
		return tuplas;
	}
	
		
}
