package tp_supermarket.fecha;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PeriodoValidezEntreFechaYFecha extends PeriodoValidez {

	private Fecha fechaInicioValidez = null;
	private Fecha fechaFinValidez = null;

	public PeriodoValidezEntreFechaYFecha(Fecha fechaIV, Fecha fechaFV) {
		this.fechaInicioValidez = fechaIV;
		this.fechaFinValidez = fechaFV;
	}

	public boolean cumplePeriodoValidez() {
		if (this.fechaInicioValidez != null && this.fechaFinValidez != null) {
			Date fechaActual = Calendar.getInstance().getTime();
			if (fechaActual.after(this.fechaInicioValidez.getFecha().getTime())
					&& fechaActual.before(this.fechaFinValidez.getFecha()
							.getTime())) {
				return true;
			}
		}
		return false;
	}
}
