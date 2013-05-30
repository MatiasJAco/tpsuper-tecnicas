package tp_supermarket.bonificacion;

import java.util.ArrayList;
import tp_supermarket.producto.*;
import tp_supermarket.restriccion.*;

public class BonificacionDescuentoMarca extends Bonificacion {

	String marca;
	int cant;
	float porcentaje;

	public BonificacionDescuentoMarca(String m, int c, float p) {
		// TODO Auto-generated constructor stub
		this.marca = m;
		this.cant = c;
		this.porcentaje = p;
	}

	public BonificacionDescuentoMarca(String m, float p) {
		// TODO Auto-generated constructor stub
		this.marca = m;
		this.cant = 1;
		this.porcentaje = p;
	}

	@Override
	public ArrayList<Producto> bonificar(ArrayList<Producto> misproducts,
			ArrayList<Restriccion> excepciones) {
		ArrayList<Producto> descuentos = new ArrayList<Producto>();
		float costo = buscarPrecio(marca, misproducts);
		float descuento = ((costo * porcentaje) / 100.0f) * -1.0f;
		Producto nuevoDescuento = new Producto(0, porcentaje + " % " + "a "
				+ this.cant + " uni. de " + marca, descuento * this.cant);
		descuentos.add(nuevoDescuento);
		return descuentos;
	}

	private float buscarPrecio(String marca2, ArrayList<Producto> misproducts) {
		float result = 0;
		for (int i = 0; i < misproducts.size(); i++) {
			if (misproducts.get(i).getMarca().equals(marca)) {
				result = misproducts.get(i).getCosto();
				i = misproducts.size();
			}
		}
		return result;
	}

	public String getMarca() {
		return this.marca;
	}

	public int getCantidad() {
		return this.cant;
	}

	public float getPorcentajeDescuento() {
		return this.porcentaje;
	}

}
