package tp_supermarket.caja;

public class MedioDePago {

	private String medio;
	private String entidadFinanciera;
	
	public MedioDePago(){
		this.medio = "Efectivo";
		this.entidadFinanciera = "";
	}
	
	public MedioDePago(String medio, String entidadFinanciera) {
		//Medio es Efectivo, Tarjeta Credito, Tarjeta Debito, Vales, Voucher
		this.setMedio(medio);
		//Banco es Entidad Financiera: Columbia, HSBC....
		this.setBanco(entidadFinanciera);

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

}
