package tp_supermarket.fecha;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Utilizar las constanstes definidas por Calendar para cada dia de la semana
 * Ej: Calendar.MONDAY
 */
public class PeriodoValidezDiasSemana extends PeriodoValidez {
	
	private ArrayList<Integer> diasDeSemana;
	
	public PeriodoValidezDiasSemana(int diaDeSemana){
		this.diasDeSemana = new ArrayList<Integer>();
		this.diasDeSemana.add(new Integer(diaDeSemana));
	}
	
	public PeriodoValidezDiasSemana(ArrayList<Integer> diasDeSemana){
		this.diasDeSemana = diasDeSemana;		
	}
	
	public boolean cumplePeriodoValidez(){
		Integer dia = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		if(this.diasDeSemana.contains(dia)){
			return true;
		}
		return false;
	}
	
}
