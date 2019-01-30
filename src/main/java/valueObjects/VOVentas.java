package valueObjects;

public class VOVentas {
	private String orden;
	private String nombreCliente;
	private String tipoCliente;
	private String nombreProducto;
	private String categoria;
	private String cantidad;
	private String moneda;
	private String total;
	private String fechaProduccion;	               
	private String fechaEntrega;
	
	public VOVentas() {
		super();
	}

	public VOVentas(String orden, String nombreCliente, String tipoCliente, String nombreProducto, String categoria,
			String cantidad, String moneda, String total, String fechaProduccion, String fechaEntrega) {
		super();
		this.orden = orden;
		this.nombreCliente = nombreCliente;
		this.tipoCliente = tipoCliente;
		this.nombreProducto = nombreProducto;
		this.categoria = categoria;
		this.cantidad = cantidad;
		this.moneda = moneda;
		this.total = total;
		this.fechaProduccion = fechaProduccion;
		this.fechaEntrega = fechaEntrega;
	}

	public String getorden() {
		return orden;
	}

	public void setorden(String orden) {
		this.orden = orden;
	}

	public String getnombreCliente() {
		return nombreCliente;
	}

	public void setnombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
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

	public String getfechaProduccion() {
		return fechaProduccion;
	}

	public void setfechaProduccion(String fechaProduccion) {
		this.fechaProduccion = fechaProduccion;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}		
}
