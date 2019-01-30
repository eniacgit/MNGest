package valueObjects;

public class VODetallesProducto {
	private String nombre;
	private String categoria;
	private String unidades;
	private String fechaProduccion;
	private String moneda;
	private String total;
	
	public VODetallesProducto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VODetallesProducto(String nombre, String categoria, String unidades, String fechaProduccion, String moneda,
			String total) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.unidades = unidades;
		this.fechaProduccion = fechaProduccion;
		this.moneda = moneda;
		this.total = total;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getUnidades() {
		return unidades;
	}

	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}

	public String getFechaProduccion() {
		return fechaProduccion;
	}

	public void setFechaProduccion(String fechaProduccion) {
		this.fechaProduccion = fechaProduccion;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}	
}
