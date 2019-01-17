package valueObjects;

public class VOProveedor {
	
	private String nombre;
	private String direccion;
	private String email;
	private String tel;
	private String url;
	private String fechaIngreso;
	
	public VOProveedor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VOProveedor(String nombre, String direccion, String email, String tel, String url, String fechaIngreso) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.email = email;
		this.tel = tel;
		this.url = url;
		this.fechaIngreso = fechaIngreso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}	
}
