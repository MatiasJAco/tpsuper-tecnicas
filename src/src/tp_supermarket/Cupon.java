package tp_supermarket;

public class Cupon {

	private int nroCupon;
	private float monto;
	
	public Cupon() {
		this.nroCupon=0;
		this.monto=0;
	}
	
	public Cupon(int nro, float monto) {
		this.nroCupon=nro;
		this.monto=monto;
	}

	public int getNroCupon() {
		return nroCupon;
	}

	public void setNroCupon(int nroCupon) {
		this.nroCupon = nroCupon;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	

}
