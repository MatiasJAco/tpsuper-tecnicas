import java.util.ArrayList;


public class Caja {
	
	private int identificacionCaja;
	
	// Abierta o cerrada
	private boolean cajaCerrada;
	
	private boolean compraEnCurso;
	
	private Compra compraActual;
	
	private final ArrayList<Compra> compras;
	
	private final ArrayList<Promocion> promociones;
	

	public Caja(int numero) {
		// TODO Auto-generated constructor stub
		this.cajaCerrada = true;
		this.compraEnCurso = false;
		this.compraActual = null;
		this.identificacionCaja = numero;
		compras = new ArrayList<Compra>();
		promociones = new ArrayList<Promocion>();
	}

	public Caja(int numero, ArrayList<Promocion> p) {
		// TODO Auto-generated constructor stub
		this.cajaCerrada = true;
		this.compraEnCurso = false;
		this.compraActual = null;
		this.identificacionCaja = numero;
		compras = new ArrayList<Compra>();
		promociones = p;
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
	
	public void cerrarCaja() throws ExceptionCerrarCajaConCompraEnCurso, ExceptionCerrarCajaConCajaCerrada {
		if (this.cajaCerrada) {
			throw new ExceptionCerrarCajaConCajaCerrada();
		}		
		
		if (this.compraEnCurso) {
			throw new ExceptionCerrarCajaConCompraEnCurso();
		}
		
		cajaCerrada=true;
	}
	
	
	public void iniciarCompra() throws ExceptionIniciarCompraConCajaCerrada, ExceptionIniciarCompraConCompraEnCurso{
		
		if (this.cajaCerrada) {
			throw new ExceptionIniciarCompraConCajaCerrada();
		}		
		
		if (this.compraEnCurso) {
			throw new ExceptionIniciarCompraConCompraEnCurso();
		}		
		
		
		this.compraActual = new Compra();
		this.compraEnCurso= true;
		
	}
	
	
	public void agregarProducto(Producto unProducto){
		
		this.compraActual.agregarProducto(unProducto);
	}

	//TODO: CONFIRMAR COMPRA
	
	public void terminarCompraActual()
	{
		this.compraActual.aplicarPromociones(this.promociones);
		this.compraActual.generarFactura();
		
		//TODO:GUARDAR FACTURA GENERADA PARA ESTADISTICAS
		// CHEQUEAR SI ESTA OK EL CONCEPTO COMPRA, FACTURA, ETC.
		this.compras.add(compraActual);
		
		this.compraEnCurso=false;
		
		
	}

	
}
