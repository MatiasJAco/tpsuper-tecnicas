import java.util.ArrayList;


public class Promocion {

private final	ArrayList<Bonificacion> bonificaciones;
private final	ArrayList <Restriccion> excepciones;
private final ArrayList <Restriccion> restricciones;
private	boolean activa;
private int vecesActivada;
	
	
	public Promocion() {
		restricciones= new ArrayList<Restriccion>();
		excepciones=new ArrayList<Restriccion>();
		bonificaciones=new ArrayList<Bonificacion>();
		activa = false;
		vecesActivada=0;
	
	}
	

	public Promocion(ArrayList <Restriccion> r, ArrayList <Restriccion> e,ArrayList <Bonificacion> b) {
		restricciones=r;
		excepciones=e;
		bonificaciones=b;
		activa = false;
		vecesActivada=0;
	
	}
	
	public Promocion(ArrayList <Restriccion> r,ArrayList <Bonificacion> b) {
		restricciones=r;
		excepciones= new ArrayList<Restriccion>();
		bonificaciones=b;
		activa = false;
		vecesActivada=0;
		
	}
	
	
	public void checkProductos(ArrayList <Producto> p){
		if (!isActiva()){
			updateRestricciones(p);
			updateExcepciones(p);		
			if(checkRestricciones() && !checkExcepciones()){
				this.activa=true;
				this.vecesActivada++;
			}		
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


	private void updateExcepciones(ArrayList<Producto> p) {
		
		
	}


	private void updateRestricciones(ArrayList<Producto> p) {
		for (int i=0;i<p.size();i++){
			for (int j=0;j<restricciones.size();j++){
				if (!restricciones.get(j).isActiva())
					restricciones.get(j).cumpleRestriccion(p.get(i));	
			}			
		}
		
	}


	public boolean isActiva(){
		return activa;
	}


	public ArrayList<Producto> aplicarBonificaciones(ArrayList<Producto> misproducts) {
		ArrayList<Producto> listaResultado=new ArrayList<Producto>();
		if (this.vecesActivada>0){
			for (int j =0;j<this.vecesActivada;j++){
				for (int i=0;i<this.bonificaciones.size();i++){
					listaResultado.addAll(this.bonificaciones.get(i).bonificar(misproducts,excepciones));					
				}				
			}
		}else
			listaResultado=new ArrayList<Producto>();
		return listaResultado;
	}


	public void addRestriccion(RestriccionMarca res) {
		this.restricciones.add(res);		
	}


	public void addExcepcion(RestriccionMarca exc) {
		this.excepciones.add(exc);
		
	}


	public void addBonificacion(BonificacionDescuentoMarca bon) {
		this.bonificaciones.add(bon);
		
	}


	public void checkProducto(Producto producto) {
		if (isActiva()){
			this.activa=false;
		};
		updateRestricciones(producto);
		updateExcepciones(producto);		
		if(checkRestricciones() && !checkExcepciones()){
			this.activa=true;
			this.vecesActivada++;
			resetRestricciones();			
		}		
	}


	private void resetRestricciones() {
		for (int j=0;j<restricciones.size();j++){
			this.restricciones.get(j).reset();			
		}		
	}


	private void updateRestricciones(Producto producto) {
		for (int j=0;j<restricciones.size();j++){
				if (!restricciones.get(j).isActiva())
					restricciones.get(j).cumpleRestriccion(producto);	
		}	
	}


	private void updateExcepciones(Producto producto) {
		// TODO Auto-generated method stub
		
	}


	
	
	
}
