package tp_supermarket.fecha;

import java.util.Calendar;

public class Fecha {

	private Calendar fecha;

	// Fecha actual
	public Fecha() {
		this.fecha = Calendar.getInstance();
	}

	public Fecha(int dia, int mes, int año) {
		this.fecha = Calendar.getInstance();
		this.fecha.set(año, mes - 1, dia, 00, 00, 00);
	}

	public Calendar getFecha() {
		return this.fecha;
	}

}
