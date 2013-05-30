package tp_supermarket.bonificacion;

import java.util.ArrayList;

import tp_supermarket.producto.Producto;
import tp_supermarket.restriccion.Restriccion;

public class BonificacionDescuentoNombre extends Bonificacion {
	
	String nombre;
	int  cant;
	float porcentaje;
	
	
	public BonificacionDescuentoNombre(String n, int c, float p ) {
		// TODO Auto-generated constructor stub
		this.nombre=n;
		this.cant=c;
		this.porcentaje=p;
	}
	
	public BonificacionDescuentoNombre(String n, float p ) {
		// TODO Auto-generated constructor stub
		this.nombre=n;
		this.cant=1;
		this.porcentaje=p;
	}


	@Override
	public ArrayList<Producto> bonificar(ArrayList<Producto> misproducts, ArrayList<Restriccion> excepciones) {
		ArrayList<Producto> descuentos= new ArrayList<Producto>();
		float costo =buscarPrecio(nombre, misproducts);
		float descuento = ((costo * porcentaje)/100)*-1;
		Producto nuevoDescuento = new Producto(0,"Descuento de " + porcentaje+ " % por " + nombre,descuento);
		descuentos.add(nuevoDescuento);
		return descuentos;
	}


	private float buscarPrecio(String marca2, ArrayList<Producto> misproducts) {
		float result=0 ;
		for (int i=0;i<misproducts.size();i++){			
			if(misproducts.get(i).getNombre().equals(nombre)){
				result=misproducts.get(i).getCosto();
				i=misproducts.size();
			}
			
		}
		return result;
	}
	

}
