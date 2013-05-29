package tp_supermarket;

import java.util.ArrayList;

import tp_supermarket.bonificacion.Bonificacion;
import tp_supermarket.bonificacion.BonificacionDescuentoCategoria;
import tp_supermarket.bonificacion.BonificacionDescuentoMarca;
import tp_supermarket.bonificacion.BonificacionDescuentoMedioDePago;
import tp_supermarket.caja.Caja;
import tp_supermarket.caja.MedioDePago;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCompraEnCurso;
import tp_supermarket.producto.Producto;
import tp_supermarket.promocion.Promocion;
import tp_supermarket.restriccion.Restriccion;
import tp_supermarket.restriccion.RestriccionCategoria;
import tp_supermarket.restriccion.RestriccionMarca;

public class Controlador {
	
	private ArrayList<Producto> listadoProductos;
	private Caja cajaprincipal;
	ArrayList<Promocion> misPromociones;
	MedioDePago med;
	
	public Controlador(){
		this.listadoProductos = new ArrayList<Producto>();
		this.misPromociones = new ArrayList<Promocion>();
		this.cajaprincipal  = new Caja(5000, misPromociones);
		this.med = new MedioDePago();
		cargarBaseDeDatosProductos();
	}
	
	public void cargarBaseDeDatosProductos(){
		
		/*
		 * Productos
		 */
		listadoProductos.add(new Producto(1, "Galletas", 100, "Alimentos","Galletas", ""));
		listadoProductos.add( new Producto(2, "Manteca", 100, "Alimentos", "Manteca",""));
		listadoProductos.add(new Producto(3, "Carne Fresca", 100, "Carnes","Las lilas", ""));
		listadoProductos.add( new Producto(4, "Coca cola 1lt", 100, "Bebidas","CocaCola", ""));
		listadoProductos.add(new Producto(5, "Mcallan 24 years", 100, "Bebidas","Mcallan", ""));
		
		
	}
	
	public ArrayList<Producto> listadoProductos(){
		return this.listadoProductos;
	}
	
	public void cargarPromocionesYBonificaciones(){
		/*
		 * Restricciones
		 */
		RestriccionMarca res1 = new RestriccionMarca("CocaCola", 2);
		RestriccionCategoria res2 = new RestriccionCategoria("Alimentos", 2);
		RestriccionCategoria res3 = new RestriccionCategoria("Bebidas", 2);
		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		//restricciones.add(res1);
		//restricciones.add(res2);
		//restricciones.add(res3);

		/*
		 * Bonificaciones
		 */
		BonificacionDescuentoCategoria bon1 = new BonificacionDescuentoCategoria(
				"Alimentos", 30);
		BonificacionDescuentoCategoria bon2 = new BonificacionDescuentoCategoria(
				"Bebidas", 20);
		BonificacionDescuentoMarca bon3 = new BonificacionDescuentoMarca(
				"CocaCola", 1, 15);
		BonificacionDescuentoMedioDePago bon4 = new BonificacionDescuentoMedioDePago(
				25);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		//bonificaciones.add(bon1);
		//bonificaciones.add(bon2);
		//bonificaciones.add(bon3);
		bonificaciones.add(bon4);

		/*
		 * Excepciones
		 */
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();

		/*
		 * Nueva promo
		 */
		ArrayList<MedioDePago> mDePagos = new ArrayList<MedioDePago>();
		mDePagos.add(new MedioDePago("Visa","Galicia"));
		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones, mDePagos);

		ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();

		
		
		misPromociones.add(promo1);
		cajaprincipal.setPromociones(misPromociones);
	}
	
	public void abrirCaja(){
		
		cajaprincipal.abrirCaja();
		
		
	}
	
	public void agregarProducto(Producto unProducto){
		if (!cajaprincipal.isEstadoCaja()){
			if (cajaprincipal.isCompraEnCurso()){
		cajaprincipal.agregarProducto(unProducto);
	}
		}
	}
	
	public void setMedioPago(String banco, String medio){
		this.med = new MedioDePago(medio,banco);
		
	}
	
	public void terminarCompra(){
		if (!cajaprincipal.isEstadoCaja()){
		if (cajaprincipal.isCompraEnCurso()){
		
			cajaprincipal.terminarCompraActual(med);
		}
			
		}
		
		//ERROR CAJA NO INICIADA O COMPRA NO INICIADA
			
			
			
		
	}

	public int getNombreCaja(){
		
		return this.cajaprincipal.getIdentificacionCaja();
	}
	
	public void getTotalMedioPagoPorCaja(){
		this.cajaprincipal.imprimirTotalMedioDePago();
		
	}

	public void iniciarCompra() {
		// TODO Auto-generated method stub
		try {
			cajaprincipal.iniciarCompra();

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}
		
	}
	
}
