package valueObjects;

public class VOTablaProveedores {
	private String proveedor;
	private String trabajoRealizado;
	private String moneda;
	private String costo;
	
	public VOTablaProveedores() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VOTablaProveedores(String proveedor, String trabajoRealizado, String moneda, String costo) {
		super();
		this.proveedor = proveedor;
		this.trabajoRealizado = trabajoRealizado;
		this.moneda = moneda;
		this.costo = costo;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
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
