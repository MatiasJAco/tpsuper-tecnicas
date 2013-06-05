package tp_supermarket.restriccion;

import java.util.ArrayList;

import tp_supermarket.bonificacion.BonificacionTipoCliente;

public class RestriccionTipoCliente{

	protected boolean activa = false;
	
	private ArrayList<String> clientesValidos;
	
	private BonificacionTipoCliente bonificacion;
	
	public RestriccionTipoCliente(ArrayList<String> clientes, float bonificacion) {
		this.clientesValidos = clientes;
		this.bonificacion = new BonificacionTipoCliente(bonificacion);
	}
	public RestriccionTipoCliente(String cliente, float bonificacion) {
		this.clientesValidos = new ArrayList<String>();
		this.clientesValidos.add(cliente);
		this.bonificacion = new BonificacionTipoCliente(bonificacion);
	}
	
	public RestriccionTipoCliente() {
		this.clientesValidos = new ArrayList<String>();
		this.clientesValidos.add("ALL");
		this.bonificacion = new BonificacionTipoCliente(100.0f);
		this.activa = true;
	}
	
	public boolean cumpleRestriccion(String tipoCliente){
		if(this.clientesValidos.get(0).equals("ALL"))
			return true;
		if(this.clientesValidos.contains(tipoCliente)){
			this.activa = true;
		}
		return this.activa;		
	}

	public boolean isActiva() {
		return activa;
	}
	
	public float aplicarBonificacion(float total){
		float ret = 0;
		if(this.isActiva()){
			ret = this.bonificacion.bonificar(total);
		}
		return ret;
	}

	public void reset(){
		this.activa = false;		
	}
	
}
