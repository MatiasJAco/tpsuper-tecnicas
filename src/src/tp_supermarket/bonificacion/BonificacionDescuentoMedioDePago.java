package tp_supermarket.bonificacion;

import java.util.ArrayList;

import tp_supermarket.producto.Producto;
import tp_supermarket.restriccion.Restriccion;

public class BonificacionDescuentoMedioDePago extends Bonificacion {

	private final float porcentaje;
	private boolean aplicada;

	public BonificacionDescuentoMedioDePago(float p) {
		this.porcentaje = p;
		this.aplicada = false;
	}

	@Override
	public ArrayList<Producto> bonificar(ArrayList<Producto> misproducts,
			ArrayList<Restriccion> excepciones) {
		ArrayList<Producto> descuentos = new ArrayList<Producto>();
		Producto nuevoDescuento;
		float result = 0;
		float total = 0;
		if (!aplicada) {
			for (int i = 0; i < misproducts.size(); i++) {
				if (!isInExcepciones(excepciones, misproducts.get(i))) {
					result = misproducts.get(i).getCosto();
					float costo = result;
					float descuento = ((costo * porcentaje) / 100) * -1;
					total += descuento;
				}
			}
			aplicada = true;
		}
		nuevoDescuento = new Producto(0, "Descuento de "
				+ porcentaje + " % con su medio de pago", total);
		descuentos.add(nuevoDescuento);
		return descuentos;
	}

}
