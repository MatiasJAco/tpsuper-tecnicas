
public class RestriccionMarca extends Restriccion{

	String marca;
	RestriccionCantidad rCant;
	
	public RestriccionMarca(String mar) {
		super();
		this.marca=mar;
		this.rCant=new RestriccionCantidad(1);
	}
	
	public RestriccionMarca(String mar, RestriccionCantidad rc) {
		super();
		this.marca=mar;
		this.rCant=rc;
	}
	
	public RestriccionMarca(String mar, int cant) {
		super();
		this.marca=mar;
		this.rCant=new RestriccionCantidad(cant);
	}

	
	
	@Override
	public boolean cumpleRestriccion(Producto p) {
		
		if (p.getNombre() == this.marca){
				
				
				this.activa=checkCantidad(p);
		}
		
		
		return activa;
	}

	private boolean checkCantidad(Producto p) {
		
			return this.rCant.cumpleRestriccion(p);
				
	}

	public void setCantidad(RestriccionCantidad rCantidad) {
		this.rCant=rCantidad;		
	}

}
