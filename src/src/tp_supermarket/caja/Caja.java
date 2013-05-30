package tp_supermarket.caja;

import java.util.ArrayList;

import tp_supermarket.caja.exceptions.ExceptionCerrarCajaConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionCerrarCajaConCompraEnCurso;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCompraEnCurso;
import tp_supermarket.compra.Compra;
import tp_supermarket.producto.Producto;
import tp_supermarket.promocion.Promocion;

public class Caja {

	private int identificacionCaja;

	// Abierta o cerrada
	private boolean cajaCerrada;

	private boolean compraEnCurso;

	private Compra compraActual;

	private final ArrayList<Compra> compras;
	
	private final ArrayList<MedioDePago> medio;

	private ArrayList<Promocion> promociones;

	public Caja(int numero) {
		// TODO Auto-generated constructor stub
		this.cajaCerrada = true;
		this.compraEnCurso = false;
		this.compraActual = null;
		this.identificacionCaja = numero;
		compras = new ArrayList<Compra>();
		promociones = new ArrayList<Promocion>();
		medio = new ArrayList<MedioDePago>();
	}

	public Caja(int numero, ArrayList<Promocion> p) {
		// TODO Auto-generated constructor stub
		this.cajaCerrada = true;
		this.compraEnCurso = false;
		this.compraActual = null;
		this.identificacionCaja = numero;
		compras = new ArrayList<Compra>();
		promociones = p;
		medio = new ArrayList<MedioDePago>();
	}

	public int getIdentificacionCaja() {
		return identificacionCaja;
	}

	public void setIdentificacionCaja(int identificacionCaja) {
		this.identificacionCaja = identificacionCaja;
	}

	public void setPromociones(ArrayList<Promocion> p) throws ExceptionActualizarPromosConCajaCerrada {
		if (this.cajaCerrada) {
			throw new ExceptionActualizarPromosConCajaCerrada();
		}
		this.promociones = p;
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

	public void abrirCaja() throws ExceptionAbrirCajaConCajaAbierta {
		
		if (!this.cajaCerrada) {
			throw new ExceptionAbrirCajaConCajaAbierta();
		}
		cajaCerrada = false;
	}

	public void cerrarCaja() throws ExceptionCerrarCajaConCompraEnCurso,
			ExceptionCerrarCajaConCajaCerrada {
		if (this.cajaCerrada) {
			throw new ExceptionCerrarCajaConCajaCerrada();
		}

		if (this.compraEnCurso) {
			throw new ExceptionCerrarCajaConCompraEnCurso();
		}

		cajaCerrada = true;
	}

	public void iniciarCompra() throws ExceptionIniciarCompraConCajaCerrada,
			ExceptionIniciarCompraConCompraEnCurso {
		if (this.cajaCerrada) {
			throw new ExceptionIniciarCompraConCajaCerrada();
		}
		if (this.compraEnCurso) {
			throw new ExceptionIniciarCompraConCompraEnCurso();
		}
		this.compraActual = new Compra();
		this.compraEnCurso = true;
	}

	public void agregarProducto(Producto unProducto) {

		this.compraActual.agregarProducto(unProducto);
	}

	// TODO: CONFIRMAR COMPRA

	public void terminarCompraActual(MedioDePago medioDePago) throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada {
		
		if (this.cajaCerrada) {
			throw new ExceptionTerminarCompraConCajaCerrada();
		}
		if (!this.compraEnCurso) {
			throw new ExceptionTerminarCompraConCompraNoIniciada();
		}
		
		this.compraActual.setMedioDePago(medioDePago);
		this.compraActual.aplicarPromociones(this.promociones);
		this.compraActual.generarFactura();
		// TODO:GUARDAR FACTURA GENERADA PARA ESTADISTICAS
		// CHEQUEAR SI ESTA OK EL CONCEPTO COMPRA, FACTURA, ETC.
		this.compras.add(compraActual);
		//TODO:VER
		
		
		if(!this.searchMedioDePago(medioDePago,this.compraActual.getTotalSD())){
			this.medio.add(medioDePago);
			medioDePago.setTotales(this.compraActual.getTotalSD());
		}
		this.compraEnCurso = false;
		this.compraActual = null;
	}

	//TODO:VER
	public boolean searchMedioDePago(MedioDePago m,float t)
	{

	    for(int i = 0; i < this.medio.size(); i++)
	    {
	         if(this.medio.get(i).compareTo(m) == 1)
	         {  
	        	 float nuevoTotal=this.medio.get(i).getTotales()+t;
	        	 this.medio.get(i).setTotales(nuevoTotal);
	        	 return true;
	         } 
	        	 
	    }
	    return false;
	}
	
	public void imprimirTotalMedioDePago() throws ExceptionCajaCerrada, ExceptionCompraIniciada{
		
		if (this.cajaCerrada) {
			throw new ExceptionCajaCerrada();
		}
		if (this.compraEnCurso) {
			throw new ExceptionCompraIniciada();
		}
	
		
		for (int i=0; i< this.medio.size(); i++){
			System.out.print(this.medio.get(i).getMedio());
			System.out.print(" ");
			System.out.print(this.medio.get(i).getBanco());
			System.out.print(" ");
			System.out.print(this.medio.get(i).getTotales());
			System.out.println("");
		}
			

		
			/*
		for (int i=0; i< this.compras.size(); i++){
			System.out.print("Compra: "+i);
			System.out.print("\t");
			System.out.print(this.compras.get(i).getMedioDePago().getMedio());
			System.out.print(" ");
			System.out.print(this.compras.get(i).getMedioDePago().getBanco());
			System.out.print("\t");
			System.out.print("Total Sin Descuento: ");
			System.out.print(this.compras.get(i).getTotalSD());
			System.out.println("");
		}*/
	}
	
	public void imprimirTotalSinDescuento() throws ExceptionCajaCerrada, ExceptionCompraIniciada{
		
		if (this.cajaCerrada) {
			throw new ExceptionCajaCerrada();
		}
		if (this.compraEnCurso) {
			throw new ExceptionCompraIniciada();
		}
		
		float totalsindescuento=0;
		for (int i=0; i< this.compras.size(); i++){
			totalsindescuento+=this.compras.get(i).getTotalSD();
		}
		System.out.print("Nro de compras: "+this.compras.size());
		System.out.print(" ");
		System.out.print("Total Ventas Sin Descuento: ");
		System.out.print(totalsindescuento);
		System.out.println("");
	}
	
	public void imprimirTotalDescuentos() throws ExceptionCajaCerrada, ExceptionCompraIniciada{
		
		if (this.cajaCerrada) {
			throw new ExceptionCajaCerrada();
		}
		if (this.compraEnCurso) {
			throw new ExceptionCompraIniciada();
		}
		
		float totaldescuentos=0;
		for (int i=0; i< this.compras.size(); i++){
			totaldescuentos+=this.compras.get(i).getTotalDesc();
		}
		System.out.print("Nro de compras: "+this.compras.size());
		System.out.print(" ");
		System.out.print("Total Descuentos: ");
		System.out.print(totaldescuentos);
		System.out.println("");
	}

	public ArrayList<Compra> getCompras() {
		return compras;
	}
	
	
}
