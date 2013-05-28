package tp_supermarket.bonificacion;

import java.util.ArrayList;
import java.util.Vector;
import tp_supermarket.producto.*;
import tp_supermarket.restriccion.*;

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


	@Override
	public ArrayList<Producto> bonificar(ArrayList<Producto> misproducts,
			ArrayList<Restriccion> excepciones) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
