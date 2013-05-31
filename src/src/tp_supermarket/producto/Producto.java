package tp_supermarket.producto;


public class Producto {
	
	private int id;
	private String nombre;
	private float precio;
	private String categoria;
	private String marca;
	private int cantidadVendidas;

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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub System.out.printf("%1$-5d %2$-20s %3$-10s $%4$-10.2f\n",
		String prod = String.format("%1$-2d %2$-20s $%3$-10.2f\n",this.id,this.nombre,this.precio);
		return prod;
	}

	public int compareTo(Producto p) {
		 if(this.nombre.equals(p.getNombre()))
		       return 1;
		    else
		       return 0;
	}

	public int getCantidadVendidas() {
		return cantidadVendidas;
	}

	public void setCantidadVendidas(int cantidadVendidas) {
		this.cantidadVendidas = cantidadVendidas;
	}
}
