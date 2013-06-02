package tp_supermarket.caja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	
	private final ArrayList<MedioDePagoStats> medio;

	private final ArrayList<Producto> rank;

	private ArrayList<Promocion> promociones;

	public Caja(int numero) {
		// TODO Auto-generated constructor stub
		this.cajaCerrada = true;
		this.compraEnCurso = false;
		this.compraActual = null;
		this.identificacionCaja = numero;
		compras = new ArrayList<Compra>();
		promociones = new ArrayList<Promocion>();
		medio = new ArrayList<MedioDePagoStats>();
		rank = new ArrayList<Producto>();

	}

	public Caja(int numero, ArrayList<Promocion> p) {
		// TODO Auto-generated constructor stub
		this.cajaCerrada = true;
		this.compraEnCurso = false;
		this.compraActual = null;
		this.identificacionCaja = numero;
		compras = new ArrayList<Compra>();
		promociones = p;
		medio = new ArrayList<MedioDePagoStats>();
		rank = new ArrayList<Producto>();

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
		this.compraActual.setNroCompra(this.compras.size());
		this.compraActual.setCaja(this.identificacionCaja);
		this.compraActual.descuentoMedioDePago();
		this.compraActual.generarFactura();
		// TODO:GUARDAR FACTURA GENERADA PARA ESTADISTICAS
		// CHEQUEAR SI ESTA OK EL CONCEPTO COMPRA, FACTURA, ETC.
		this.compras.add(compraActual);
		//TODO:VER
		
		
		if(!this.searchMedioDePago(medioDePago,this.compraActual.getTotalSD())){
			MedioDePagoStats nuevoMedioStats = new MedioDePagoStats();
			nuevoMedioStats.setBanco(medioDePago.getBanco());
			nuevoMedioStats.setMedio(medioDePago.getMedio());
			nuevoMedioStats.setTotales(this.compraActual.getTotalSD());
			this.medio.add(nuevoMedioStats);
			
		}
		this.rankingProductos();
		this.compraEnCurso = false;
		this.resetPromociones();
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
	
	public void mostrarrankingProductos(){
		System.out.println("RANKING PRODUCTOS");
		
		// DE ultima comentar esto
		Collections.sort(rank, new Comparator<Producto>(){
		     public int compare(Producto o1, Producto o2){
		         if(o1.getCantidadVendidas() == o2.getCantidadVendidas())
		             return 0;
		         return o1.getCantidadVendidas() > o2.getCantidadVendidas() ? -1 : 1;
		     }
		});
	
		for(int i = 0; i < this.rank.size(); i++)
	    {
			System.out.print("Nombre: ");
			System.out.print(this.rank.get(i).getNombre());
			System.out.print(" ");
			System.out.print("Cant: ");
			System.out.print(this.rank.get(i).getCantidadVendidas());
			System.out.println("");
			}
			
	    }
	
	
	public void rankingProductos(){
		for(int i = 0; i < this.compraActual.verProductos().size(); i++)
	    {
			if (!this.searchRank(this.compraActual.verProductos().get(i)) ){
				Producto nuevoProdRank = new Producto(this.compraActual.verProductos().get(i).getId(),this.compraActual.verProductos().get(i).getNombre(),this.compraActual.verProductos().get(i).getCosto());
				nuevoProdRank.setCantidadVendidas(1);
				this.rank.add(nuevoProdRank);

			}
			
	    }
	}
	
	public boolean searchRank(Producto p)
	{

	    for(int i = 0; i < this.rank.size(); i++)
	    {
	         if(this.rank.get(i).compareTo(p) == 1)
	         {  
	        	 int cantidadVendidas = this.rank.get(i).getCantidadVendidas() +1;
	        	 this.rank.get(i).setCantidadVendidas(cantidadVendidas);
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
		
		System.out.println("");
		System.out.println("TOTALES POR MEDIO DE PAGO");
		
		for (int i=0; i< this.medio.size(); i++){
			System.out.print(this.medio.get(i).getMedio());
			System.out.print(" ");
			System.out.print(this.medio.get(i).getBanco());
			System.out.print(" ");
			System.out.print(this.medio.get(i).getTotales());
			System.out.println("");
		}
			

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
		System.out.println("");
		System.out.println("SUMA TOTAL DE VENTAS");
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
		
		System.out.println("");
		System.out.println("SUMA TOTAL DE DESCUENTOS");
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
	
	private void resetPromociones(){
		for(Promocion promo : this.promociones){
			promo.reset();
		}
	}

	public ArrayList<Compra> getCompras() {
		return compras;
	}

	public ArrayList<Producto> getRankinProductos() {
		return this.rank;
		
	}
	
}

