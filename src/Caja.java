
public class Caja {
	
	private int identificacionCaja;
	
	// Abierta o cerrada
	private boolean cajaCerrada;
	
	private boolean compraEnCurso;
	
	private Compra compraActual;
	

	public Caja(int numero) {
		// TODO Auto-generated constructor stub
		this.cajaCerrada = true;
		this.compraEnCurso = false;
		this.compraActual = null;
		this.identificacionCaja = numero;
	}



	public int getIdentificacionCaja() {
		return identificacionCaja;
	}



	public void setIdentificacionCaja(int identificacionCaja) {
		this.identificacionCaja = identificacionCaja;
	}



	public boolean isEstadoCaja() {
		return cajaCerrada;
	}



	public void setEstadoCaja(boolean estadoCaja) {
		this.cajaCerrada = estadoCaja;
	}



	public boolean isCompraEnCurso() {
		return compraEnCurso;
	}



	public void setCompraEnCurso(boolean compraEnCurso) {
		this.compraEnCurso = compraEnCurso;
	}



	public Compra getCompraActual() {
		return compraActual;
	}



	public void setCompraActual(Compra compraActual) {
		this.compraActual = compraActual;
	}
	
	
	public void abrirCaja() {
		cajaCerrada=false;
	}
	
	public void cerrarCaja() throws cerrarCajaConCompraEnCurso, cerrarCajaConCajaCerrada {
		if (this.cajaCerrada) {
			throw new cerrarCajaConCajaCerrada();
		}		
		
		if (this.compraEnCurso) {
			throw new cerrarCajaConCompraEnCurso();
		}
		
		cajaCerrada=true;
	}
	
	
	public void iniciarCompra() throws iniciarCompraConCajaCerrada, iniciarCompraConCompraEnCurso{
		
		if (this.cajaCerrada) {
			throw new iniciarCompraConCajaCerrada();
		}		
		
		if (this.compraEnCurso) {
			throw new iniciarCompraConCompraEnCurso();
		}		
		
		
		this.compraActual = new Compra();
		this.compraEnCurso= true;
		
	}
	
	
	public void agregarProducto(Producto unProducto){
		
		this.compraActual.agregarProducto(unProducto);
	}

	public void mostrarFactura()
	{
		this.compraActual.generarFactura();
	}
	
}
