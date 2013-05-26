
public class Producto {
	
	private int id;
	private String nombre;
	private float precio;
	
	private String categoria;
	private String marca;
	
	private String descripcion;
	
	
	public Producto(int id, String nombre, float costo) {	
		this.id=id;
		this.nombre=nombre;
		this.precio=costo;	
	}
	
	public Producto(int id, String nombre, float costo,String cat) {	
		this.id=id;
		this.nombre=nombre;
		this.precio=costo;	
		this.categoria=cat;
	}
	
	public Producto(int id, String nombre, float costo,String cat,String Marca , String descripcion) {	
		this.id=id;
		this.nombre=nombre;
		this.precio=costo;	
		this.categoria=cat;
		this.marca=Marca;
		this.descripcion=descripcion;
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getCosto() {
		return precio;
	}

	public void setCosto(float costo) {
		this.precio = costo;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
