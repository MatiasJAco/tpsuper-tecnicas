package tp_supermarket;

import java.util.ArrayList;
import java.util.Calendar;

import tp_supermarket.bonificacion.Bonificacion;
import tp_supermarket.bonificacion.BonificacionDescuentoCategoria;
import tp_supermarket.bonificacion.BonificacionDescuentoMarca;
import tp_supermarket.bonificacion.BonificacionDescuentoMedioDePago;
import tp_supermarket.bonificacion.BonificacionDescuentoNombre;
import tp_supermarket.caja.Caja;
import tp_supermarket.caja.ExceptionAbrirCajaConCajaAbierta;
import tp_supermarket.caja.ExceptionActualizarPromosConCajaCerrada;
import tp_supermarket.caja.ExceptionCajaCerrada;
import tp_supermarket.caja.ExceptionCompraIniciada;
import tp_supermarket.caja.ExceptionTerminarCompraConCajaCerrada;
import tp_supermarket.caja.ExceptionTerminarCompraConCompraNoIniciada;
import tp_supermarket.caja.MedioDePago;
import tp_supermarket.caja.exceptions.ExceptionCerrarCajaConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionCerrarCajaConCompraEnCurso;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCompraEnCurso;
import tp_supermarket.fecha.PeriodoValidezDiasSemana;
import tp_supermarket.fecha.PeriodoValidezEntreFechaYFecha;
import tp_supermarket.producto.Producto;
import tp_supermarket.promocion.Promocion;
import tp_supermarket.restriccion.Restriccion;
import tp_supermarket.restriccion.RestriccionCantidad;
import tp_supermarket.restriccion.RestriccionCategoria;
import tp_supermarket.restriccion.RestriccionMarca;
import tp_supermarket.restriccion.RestriccionNombreProducto;
import tp_supermarket.xml.ParserXml;

public class Controlador {
	
	private final String path = "src/tp_supermarket/xml/ofertas.xml";

	private ArrayList<Producto> listadoProductos;
	private Caja cajaprincipal;
	ArrayList<Promocion> misPromociones;
	MedioDePago med;

	public Controlador() {
		this.listadoProductos = new ArrayList<Producto>();
		this.misPromociones = new ArrayList<Promocion>();
		this.cajaprincipal = new Caja(5000, misPromociones);
		this.med = new MedioDePago();
		cargarBaseDeDatosProductos();
	}

	public void cargarBaseDeDatosProductos() {

		/*
		 * Productos
		 */
		listadoProductos.add(new Producto(1, "Galletas", 100, "Alimentos",
				"Galletas", ""));
		listadoProductos.add(new Producto(2, "Manteca", 100, "Alimentos",
				"Manteca", ""));
		listadoProductos.add(new Producto(3, "Carne Fresca", 100, "Carnes",
				"Las lilas", ""));
		listadoProductos.add(new Producto(4, "Coca cola 1,5 lts", 100, "Bebidas",
				"CocaCola", ""));
		listadoProductos.add(new Producto(5, "Mcallan 24 years", 100,
				"Bebidas", "Mcallan", ""));
		listadoProductos.add(new Producto(6, "Lays", 15,
				"Alimentos", "Lays", ""));


	}

	public ArrayList<Producto> listadoProductos() {
		return this.listadoProductos;
	}

	public void cargarPromocionesYBonificaciones() {	
		ArrayList<Promocion> misPromociones = ParserXml.getPromocionesFromXml(this.path);
		try {
			cajaprincipal.setPromociones(misPromociones);
			System.out.println("Promociones y Bonificaciones Actualizadas");
		} catch (ExceptionActualizarPromosConCajaCerrada e) {
			System.out.println("ERROR: No se puede actualizar: Caja CERRADA");
		}	
		
//		try {
//			/*
//			 * Restricciones DIA
//			 */
//			ArrayList<Integer> diasPromo = new ArrayList<Integer>();
//			diasPromo.add(Calendar.THURSDAY);
//			PeriodoValidezDiasSemana promodiaJueves = new PeriodoValidezDiasSemana(
//					diasPromo);
//
//			/*
//			 * Restricciones
//			 */
//			RestriccionNombreProducto res1 = new RestriccionNombreProducto(
//					"Coca cola 1lt", 2);
//			ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
//			restricciones.add(res1);
//			// restricciones.add(res2);
//			// restricciones.add(res3);
//
//			/*
//			 * Definicion de los medios de pagos para la promocion
//			 */
//			ArrayList<MedioDePago> mediosDePagosPromo = new ArrayList<MedioDePago>();
//			MedioDePago medioDePago1 = new MedioDePago("Visa", "Galicia");
//			mediosDePagosPromo.add(medioDePago1);
//
//			/*
//			 * Bonificaciones
//			 */
//			BonificacionDescuentoNombre bon1 = new BonificacionDescuentoNombre(
//					"Coca cola 1lt", 1, 100);
//			BonificacionDescuentoMedioDePago bon2 = new BonificacionDescuentoMedioDePago(
//					10);
//			ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
//			bonificaciones.add(bon1);
//			bonificaciones.add(bon2);
//			// bonificaciones.add(bon3);
//			// bonificaciones.add(bon4);
//
//			/*
//			 * Excepciones
//			 */
//			ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();
//
//			/*
//			 * Nueva promo
//			 */
//			Promocion promo1 = new Promocion(restricciones, excepciones,
//					bonificaciones, mediosDePagosPromo);
//			promo1.setPeriodoValidezPromocion(promodiaJueves);
//
//			ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();
//
//			misPromociones.add(promo1);
//			cajaprincipal.setPromociones(misPromociones);
//
//			System.out.println("Promociones y Bonificaciones Actualizadas");
//		} catch (ExceptionActualizarPromosConCajaCerrada e) {
//			System.out.println("ERROR: No se puede actualizar: Caja CERRADA");
//		}
	}

	public void abrirCaja() {

		try {
			cajaprincipal.abrirCaja();

			System.out.println("Caja: " + cajaprincipal.getIdentificacionCaja()
					+ " ahora esta ABIERTA");
			System.out
					.println("Pulse Iniciar Compra para comenzar una nueva compra ");

		} catch (ExceptionAbrirCajaConCajaAbierta e) {
			System.out.println("ERROR: La caja ya esta abierta!");
		}

	}

	public void agregarProducto(Producto unProducto) {
		if (!cajaprincipal.isEstadoCaja()) {
			if (cajaprincipal.isCompraEnCurso()) {
				cajaprincipal.agregarProducto(unProducto);
			}
		}
	}

	public void setMedioPago(String banco, String medio) {
		this.med = new MedioDePago(medio, banco);

	}

	public boolean terminarCompra() {
		boolean ret = false;
		try {
			cajaprincipal.terminarCompraActual(med);
			ret = true;

		} catch (ExceptionTerminarCompraConCajaCerrada e) {
			System.out
					.println("ERROR:No se puede terminar compra: caja CERRADA");
		} catch (ExceptionTerminarCompraConCompraNoIniciada e) {
			System.out.println("ERROR:No hay una compra iniciada!");
		}
		return ret;

	}

	public int getNombreCaja() {

		return this.cajaprincipal.getIdentificacionCaja();
	}

	public void getTotalMedioPagoPorCaja() {
		try {
			this.cajaprincipal.imprimirTotalMedioDePago();
		} catch (ExceptionCajaCerrada e) {
			System.out.println("ERROR:No se puede mostrar: caja CERRADA");
		} catch (ExceptionCompraIniciada e) {
			System.out.println("ERROR:No se puede mostrar: compra en curso");
		}

	}

	public void getTotalSinDescuento() {
		try {
			this.cajaprincipal.imprimirTotalSinDescuento();
		} catch (ExceptionCajaCerrada e) {
			System.out.println("ERROR:No se puede mostrar: caja CERRADA");
		} catch (ExceptionCompraIniciada e) {
			System.out.println("ERROR:No se puede mostrar: compra en curso");
		}
	}

	public void getTotalDescuentos() {
		try {
			this.cajaprincipal.imprimirTotalDescuentos();
		} catch (ExceptionCajaCerrada e) {
			System.out.println("ERROR:No se puede mostrar: caja CERRADA");
		} catch (ExceptionCompraIniciada e) {
			System.out.println("ERROR:No se puede mostrar: compra en curso");
		}
	}

	public void iniciarCompra() {
		// TODO Auto-generated method stub
		try {
			cajaprincipal.iniciarCompra();
			System.out
					.println("Nueva Compra en curso, seleccione los productos de la lista");
			System.out.println("Doble click para agregar producto");

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
			System.out
					.println("ERROR:No se puede iniciar compra: caja CERRADA");
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
			System.out.println("ERROR:Ya hay una compra en curso!");
		}

	}

	public boolean cerrarCaja() {
		boolean ret = false;
		try {
			cajaprincipal.cerrarCaja();
			ret = true;
		} catch (ExceptionCerrarCajaConCompraEnCurso e) {
			System.out.println("ERROR:No se puede cerrar: compra en curso");
		} catch (ExceptionCerrarCajaConCajaCerrada e) {
			System.out.println("ERROR:No se puede cerrar: caja CERRADA");
		}
		return ret;
	}

}
