package grafica;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class MantenimientoCliente extends JInternalFrame {

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
		setBounds(100, 100, 450, 300);

	}

}
