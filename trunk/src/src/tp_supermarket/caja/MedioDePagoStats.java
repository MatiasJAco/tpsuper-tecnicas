package tp_supermarket.caja;

public class MedioDePagoStats {

	private String medio;
	private String entidadFinanciera;
	//TODO:VER
	private float totales;
	
	public MedioDePagoStats(){
		this.medio = "Efectivo";
		this.entidadFinanciera = "";
		this.totales=0;
	}
	
	public MedioDePagoStats(String medio, String entidadFinanciera) {
		//Medio es Efectivo, Tarjeta Credito, Tarjeta Debito, Vales, Voucher
		this.setMedio(medio);
		//Banco es Entidad Financiera: Columbia, HSBC....
		this.setBanco(entidadFinanciera);
		this.totales=0;

	}

	public String getMedio() {
		return medio;
	}

	public void setMedio(String medio) {
		this.medio = medio;
	}

	public String getBanco() {
		return entidadFinanciera;
	}

	public void setBanco(String banco) {
		this.entidadFinanciera = banco;
	}
	
	public boolean equals(MedioDePago med){
		if(this.medio == med.getMedio() && this.entidadFinanciera == med.getBanco())
			return true;
		return false;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.medio+" "+this.entidadFinanciera;
	}
	
	//TODO:VER

	public int compareTo(MedioDePago m) {
		    if(this.entidadFinanciera.equals(m.getBanco())&&this.medio.equals(m.getMedio()))
		       return 1;
		    else
		       return 0;
	}

	//TODO:VER
	public float getTotales() {
		return totales;
	}

	public void setTotales(float totales) {
		this.totales = totales;
	}

}