package grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class PantallaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JButton btnAceptar;
	private JPasswordField passwordField;

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
					PantallaLogin frame = new PantallaLogin();
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
	public PantallaLogin() {
		setResizable(false);
		setTitle("Ingreso al Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PantallaLogin.class.getResource("/resources/mnrep.png")));
		lblNewLabel.setBounds(12, 12, 150, 137);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
		lblUsuario.setBounds(174, 44, 60, 17);
		contentPane.add(lblUsuario);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(262, 40, 165, 25);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Arial", Font.BOLD, 14));
		lblContrasea.setBounds(174, 76, 81, 17);
		contentPane.add(lblContrasea);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = textFieldUsuario.getText();
				char[] pass = passwordField.getPassword();
				
				if (esPasswordCorrecto(pass)) {
					setVisible(false);
					Principal ventanaPPAL = new Principal();
					ventanaPPAL.setVisible(true);
					
				}else {
					JOptionPane.showMessageDialog(getContentPane(),
			                "Usuario o Contraseña inválidos. Intente nuevamente.",
			                "Mensaje de error",
			                JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		btnAceptar.setBounds(223, 124, 89, 25);
		contentPane.add(btnAceptar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaLogin.this.dispose();
			}
		});
		btnSalir.setBounds(338, 124, 89, 25);
		contentPane.add(btnSalir);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usuario = textFieldUsuario.getText();
				char[] pass = passwordField.getPassword();
				
				if (esPasswordCorrecto(pass)) {
					setVisible(false);
					Principal ventanaPPAL = new Principal();
					ventanaPPAL.setVisible(true);
					
				}else {
					JOptionPane.showMessageDialog(getContentPane(),
			                "Usuario o Contraseña inválidos. Intente nuevamente.",
			                "Mensaje de error",
			                JOptionPane.ERROR_MESSAGE);
				}				
			
			}
		});
		passwordField.setBounds(262, 72, 165, 25);
		contentPane.add(passwordField);
	}
	
	
// FUNCIONES AUXILIARES	
	private static boolean esPasswordCorrecto(char[] input) {
	    boolean isCorrect = true;
	    char[] correctPassword = { 'a', 'd', 'm', 'i', 'n'};

	    if (input.length != correctPassword.length) {
	        isCorrect = false;
	    } else {
	        isCorrect = Arrays.equals (input, correctPassword);
	    }

	    //Zero out the password.
	    Arrays.fill(correctPassword,'0');
	    return isCorrect;
	}
}
