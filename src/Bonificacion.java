import java.util.ArrayList;


public abstract class  Bonificacion {

	
	
	public Bonificacion() {
		// TODO Auto-generated constructor stub
	}
	public abstract ArrayList<Producto> bonificar(ArrayList<Producto> misproducts, ArrayList<Restriccion> excepciones);

	
	protected boolean isInExcepciones(ArrayList<Restriccion> excepciones,	Producto producto) {
		boolean result = false;
		for (int i=0;i<excepciones.size();i++){
			if(excepciones.get(i).cumpleRestriccion(producto))
				result=true;			
		};		
		return result;
	}
	
}
