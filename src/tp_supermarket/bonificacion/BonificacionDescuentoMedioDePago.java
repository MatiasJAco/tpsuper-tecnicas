package tp_supermarket.bonificacion;

import java.util.ArrayList;

import tp_supermarket.producto.Producto;
import tp_supermarket.restriccion.Restriccion;

public class BonificacionDescuentoMedioDePago extends Bonificacion {

	private final String medioDePago;
	private final int cant;
	private final float porcentaje;
	private boolean aplicada;

	public BonificacionDescuentoMedioDePago(String med, int c, float p) {
		// TODO Auto-generated constructor stub
		this.medioDePago = med;
		this.cant = c;
		this.porcentaje = p;
		this.aplicada = false;
	}

	public BonificacionDescuentoMedioDePago(String med, float p) {
		// TODO Auto-generated constructor stub
		this.medioDePago = med;
		this.cant = 1;
		this.porcentaje = p;
	}

	@Override
	public ArrayList<Producto> bonificar(ArrayList<Producto> misproducts,
			ArrayList<Restriccion> excepciones) {
		ArrayList<Producto> descuentos = new ArrayList<Producto>();
		float result = 0;
		if (!aplicada) {
			for (int i = 0; i < misproducts.size(); i++) {
				if (misproducts.get(i).getMedioDePago() == medioDePago
						&& !isInExcepciones(excepciones, misproducts.get(i))) {
					result = misproducts.get(i).getCosto();
					float costo = result;
					float descuento = ((costo * porcentaje) / 100) * -1;
					Producto nuevoDescuento = new Producto(0, "Descuento de "
							+ porcentaje + " % por pago con " + medioDePago, descuento);
					descuentos.add(nuevoDescuento);
				}
				;
			}
			;
			aplicada = true;
		}
		;
		return descuentos;
	}

}
