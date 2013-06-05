package tp_supermarket.bonificacion;

public class BonificacionTipoCliente {

	private float porcentaje;
	
	public BonificacionTipoCliente(float p){
		this.porcentaje = p;
	}
	
	public float bonificar(float total){
		return total*((100-porcentaje)/100);
	}
	
}
