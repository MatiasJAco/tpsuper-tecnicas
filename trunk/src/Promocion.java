import java.util.Vector;


public class Promocion {

private final	Vector<Bonificacion> bonificaciones;
private final	Vector <Restriccion> excepciones;
private final Vector <Restriccion> restricciones;
private	boolean activa;
	
	public Promocion(Vector <Restriccion> r, Vector <Restriccion> e,Vector <Bonificacion> b) {
		restricciones=r;
		excepciones=e;
		bonificaciones=b;
		activa = false;
	
	}
	
	
	public void checkProductos(Vector <Producto> p){
		updateRestricciones(p);
		updateExcepciones(p);		
		if(checkRestricciones() && !checkExcepciones()){
			this.activa=true;
		}		
		
	}
	
	private boolean checkExcepciones() {
//		boolean cumpleExcepcion = false;
//		
//		for (int i=0;i<excepciones.size();i++){
//			if (excepciones.get(i).is)
//			
//		}
		
		return false;
	}


	private boolean checkRestricciones() {
		boolean cumpleRestricciones=true;
		for (int i=0;i<restricciones.size();i++){
			if (!restricciones.get(i).isActiva())
				cumpleRestricciones = false;			
		}
		return cumpleRestricciones ;
	}


	private void updateExcepciones(Vector<Producto> p) {
		
		
	}


	private void updateRestricciones(Vector<Producto> p) {
		for (int i=0;i<p.size();i++){
			for (int j=0;j<restricciones.size();j++){
				restricciones.get(j).cumpleRestriccion(p.get(i));	
			}			
		}
		
	}


	public boolean isActiva(){
		return activa;
	}


	public Vector<Producto> aplicarBonificaciones(Vector<Producto> misproducts) {
		Vector<Producto> listaResultado=null;
		if (this.activa){
			for (int i=0;i<this.bonificaciones.size();i++){
				listaResultado = this.bonificaciones.get(i).bonificar(misproducts);					
				}				
		}else
			listaResultado=misproducts;
				
		return listaResultado;
	}


	
	
	
}
