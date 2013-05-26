import java.util.ArrayList;


public class BonificacionDescuentoCategoria extends Bonificacion {
	
	private final String categoria;
	private final 	int  cant;
	private final 	float porcentaje;
	private boolean aplicada;
	
	public BonificacionDescuentoCategoria(String cat, int c, float p ) {
		// TODO Auto-generated constructor stub
		this.categoria=cat;
		this.cant=c;
		this.porcentaje=p;
		this.aplicada=false;
	}
	
	public BonificacionDescuentoCategoria(String cat,float p ) {
		// TODO Auto-generated constructor stub
		this.categoria=cat;
		this.cant=1;
		this.porcentaje=p;
	}


	@Override
	public ArrayList<Producto> bonificar(ArrayList<Producto> misproducts, ArrayList<Restriccion> excepciones) {
		ArrayList<Producto> descuentos= new ArrayList<Producto>();
		float result=0;
		if (!aplicada){
			for (int i=0;i<misproducts.size();i++){			
				if(misproducts.get(i).getCategoria()== categoria && !isInExcepciones(excepciones,misproducts.get(i))){
					result=misproducts.get(i).getCosto();
					float costo =result;
					float descuento = ((costo * porcentaje)/100)*-1;
					Producto nuevoDescuento = new Producto(0,"Descuento de " + porcentaje+ " % por " + categoria,descuento);
					descuentos.add(nuevoDescuento);
				};			
			};
			aplicada=true;
		};
		return descuentos;
	}


}


