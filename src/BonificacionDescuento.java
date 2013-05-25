import java.util.Vector;


public class BonificacionDescuento extends Bonificacion {
	
	String marca;
	int  cant;
	float porcentaje;
	
	
	public BonificacionDescuento(String m, int c, float p ) {
		// TODO Auto-generated constructor stub
		this.marca=m;
		this.cant=c;
		this.porcentaje=p;
	}


	@Override
	public Vector<Producto> bonificar(Vector<Producto> misproducts) {
		float costo =buscarPrecio(marca, misproducts);
		float descuento = (costo - (costo * porcentaje)/100)*-1;
		Producto nuevoDescuento = new Producto(0,"Descuento de " + porcentaje+ " % por " + marca,descuento);
		misproducts.add(nuevoDescuento);
		return misproducts;
	}


	private float buscarPrecio(String marca2, Vector<Producto> misproducts) {
		float result=0 ;
		for (int i=0;i<misproducts.size();i++){			
			if(misproducts.get(i).getNombre()== marca){
				result=misproducts.get(i).getCosto();
				i=misproducts.size();
			}
			
		}
		return result;
	}
	

}
