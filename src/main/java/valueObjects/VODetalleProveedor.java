package valueObjects;

public class VODetalleProveedor {
	private String nombre;
	private String trabajoRealizado;
	private String moneda;
	private String costo;
	
	public VODetalleProveedor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VODetalleProveedor(String nombre, String trabajoRealizado, String moneda, String costo) {
		super();
		this.nombre = nombre;
		this.trabajoRealizado = trabajoRealizado;
		this.moneda = moneda;
		this.costo = costo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTrabajoRealizado() {
		return trabajoRealizado;
	}

	public void setTrabajoRealizado(String trabajoRealizado) {
		this.trabajoRealizado = trabajoRealizado;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}	
}
