import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.validator.routines.UrlValidator;

public class TestValidaciones {

	public static boolean validarCelular (String cadena) {
		// Comienza con cero y tiene 9 digitos numericos
		return cadena.matches("^0\\d{8}");
	}
	
	public static boolean esValidoEmail(String cadena) {
		return cadena.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
	}
	
	public static boolean esValidoTelefono(String cadena) {
		return cadena.matches("\\d*");
	}
	
	public static boolean esValidoURL(String cadena) {		
		UrlValidator validar = new UrlValidator();
		return validar.isValid(cadena);
	}
	
	
	public static boolean esValidaFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
	
	
	public static void main(String[] args) {
		
		String celular = "023456790";
		if (validarCelular(celular)) {
			System.out.println("Es celular valido");
		}else
			System.out.println("NO es celular valido");
	
	System.out.println("---------------------------------------------------------------------------");
	
	String email="juan.perez.rodriguez.garcia.com@gmail.com";
	if (esValidoEmail(email))
		System.out.println("Email correcto.");
	else
		System.out.println("Email incorrecto.");
	
	
	System.out.println("---------------------------------------------------------------------------");
	
	String telefono ="12345678";
	if (esValidoTelefono(telefono)) {
		System.out.println("Telefono correcto.");
	}else
		System.out.println("Telefono incorrecto.");
	
	
	System.out.println("---------------------------------------------------------------------------");
	
	String url ="https://www.google.com.uy";
	
	if (esValidoURL(url)) {
		System.out.println("url correcto!.");		
	}else
		System.out.println("url incorrecto!.");
	
	
	System.out.println("---------------------------------------------------------------------------");
	
	String fecha ="14/01/2019";
	if (esValidaFecha(fecha)) {
		System.out.println("fecha valida!");		
	}else
		System.out.println("fecha invalida!");
	}
}
