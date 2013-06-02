package tp_supermarket.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tp_supermarket.bonificacion.Bonificacion;
import tp_supermarket.bonificacion.BonificacionDescuentoCategoria;
import tp_supermarket.bonificacion.BonificacionDescuentoMarca;
import tp_supermarket.bonificacion.BonificacionDescuentoMedioDePago;
import tp_supermarket.caja.Caja;
import tp_supermarket.caja.ExceptionAbrirCajaConCajaAbierta;
import tp_supermarket.caja.ExceptionTerminarCompraConCajaCerrada;
import tp_supermarket.caja.ExceptionTerminarCompraConCompraNoIniciada;
import tp_supermarket.caja.MedioDePago;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCompraEnCurso;
import tp_supermarket.fecha.PeriodoValidez;
import tp_supermarket.fecha.PeriodoValidezDiasSemana;
import tp_supermarket.producto.Producto;
import tp_supermarket.promocion.Promocion;
import tp_supermarket.restriccion.Restriccion;
import tp_supermarket.restriccion.RestriccionCategoria;
import tp_supermarket.restriccion.RestriccionMarca;

public class mainTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada, ExceptionAbrirCajaConCajaAbierta{
		/*
		 * Productos
		 */
		Producto art1 = new Producto(1, "CocaCola", 1, "Bebidas",
				"CocaCola", "");
		Producto art2 = new Producto(2, "Maceta", 10, "Jardineria", "Maceta",
				"");
		Producto art3 = new Producto(3, "Cepillos de Dientes", 3, "Salud",
				"Cepi", "");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);
		misproducts.add(art3);

		/*
		 * Restricciones
		 */
		RestriccionMarca res1 = new RestriccionMarca("CocaCola", 2);
		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		restricciones.add(res1);

		/*
		 * Bonificaciones
		 */
		BonificacionDescuentoMarca bon1 = new BonificacionDescuentoMarca(
				"CocaCola", 100);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bon1);
		/*
		 * Excepciones
		 */
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();

		/*
		 * Nueva promo
		 */
		ArrayList<MedioDePago> mDePagos = new ArrayList<MedioDePago>();
		mDePagos.add(new MedioDePago("XYZ",""));
		/*
		 * Bonificacion por medio de pago
		 */
		
		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones, mDePagos);
		/*
		 * Seteo de la fecha de vigencia de la promocion
		 */
		ArrayList<Integer> diasPromo = new ArrayList<Integer>();		
		diasPromo.add(Calendar.SUNDAY);
		PeriodoValidez pValidez = new PeriodoValidezDiasSemana(diasPromo);
		promo1.setPeriodoValidezPromocion(pValidez);

		ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();
		;
		misPromociones.add(promo1);
		// Caja cajaprincipal = new Caja(1234);

		Caja cajaprincipal = new Caja(1234, misPromociones);
		cajaprincipal.abrirCaja();
		try {
			cajaprincipal.iniciarCompra();

			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art2);
			cajaprincipal.agregarProducto(art3);

			MedioDePago med = new MedioDePago("XYZ", "", 10, pValidez);
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

		assertEquals(12.6f,cajaprincipal.getCompras().get(0).getTotalCD(),0.00001);
	}
	
//	@Test
	public void test2() throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada, ExceptionAbrirCajaConCajaAbierta {

		/*
		 * Productos
		 */
		Producto art1 = new Producto(1, "CocaCola", 1, "Bebidas",
				"CocaCola", "");
		Producto art2 = new Producto(2, "Maceta", 10, "Jardineria", "Maceta",
				"");
		Producto art3 = new Producto(3, "Cepillos de Dientes", 3, "Salud",
				"Cepi", "");
		Producto art4 = new Producto(4, "Vino X", 100, "Vinoteca",
				"X", "");
		Producto art5 = new Producto(5, "Chandon", 75, "Vinoteca",
				"Chandon", "");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);
		misproducts.add(art3);
		misproducts.add(art4);
		misproducts.add(art5);

		/*
		 * Restricciones
		 */
		RestriccionCategoria res1 = new RestriccionCategoria("Vinoteca");
		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		restricciones.add(res1);

		/*
		 * Bonificaciones
		 */
		BonificacionDescuentoCategoria bon1 = new BonificacionDescuentoCategoria(
				"Vinoteca", 75,1);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();

		bonificaciones.add(bon1);
		/*
		 * Excepciones
		 */
		RestriccionMarca execp1 = new RestriccionMarca("Chandon");
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();
		excepciones.add(execp1);
		/*
		 * Nueva promo
		 */

		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones);

		
		//-------------------------------------------------
		/*
		 * Restricciones
		 */
		ArrayList<Restriccion> restricciones2 = new ArrayList<Restriccion>();

		/*
		 * Bonificaciones
		 */
		BonificacionDescuentoMedioDePago bDescuento = new BonificacionDescuentoMedioDePago(
				10);
		ArrayList<Bonificacion> bonificaciones2 = new ArrayList<Bonificacion>();

		bonificaciones.add(bDescuento);
		/*
		 * Excepciones
		 */
		ArrayList<Restriccion> excepciones2 = new ArrayList<Restriccion>();	

		ArrayList<MedioDePago> mDePagos = new ArrayList<MedioDePago>();
		mDePagos.add(new MedioDePago("Tarjeta de Debito",""));
		
		Promocion promo2 = new Promocion(restricciones2, excepciones2,bonificaciones2
				,mDePagos);
		/*
		 * Seteo de la fecha de vigencia de la promocion
		 */
		ArrayList<Integer> diasPromo = new ArrayList<Integer>();		
		diasPromo.add(Calendar.MONDAY);
		PeriodoValidez pValidez = new PeriodoValidezDiasSemana(diasPromo);
		promo1.setPeriodoValidezPromocion(pValidez);
		promo2.setPeriodoValidezPromocion(pValidez);

		ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();
		;
		misPromociones.add(promo1);
		misPromociones.add(promo2);
		// Caja cajaprincipal = new Caja(1234);

		Caja cajaprincipal = new Caja(1234, misPromociones);
		cajaprincipal.abrirCaja();
		try {
			cajaprincipal.iniciarCompra();

			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art5);
			cajaprincipal.agregarProducto(art5);
			cajaprincipal.agregarProducto(art3);
			cajaprincipal.agregarProducto(art2);

			MedioDePago med = new MedioDePago("Tarjeta de Debito", "");
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

		assertEquals(12.4f,cajaprincipal.getCompras().get(0).getTotalCD(),0.00001);
	}
	
	@Test
	public void test3()  throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada, ExceptionAbrirCajaConCajaAbierta {

		/*
		 * Productos
		 */
		Producto art1 = new Producto(1, "ELL", 10, "Vinoteca",
				"ELL", "");
		Producto art2 = new Producto(2, "XXZ", 20, "Vinoteca",
				"XXZ", "");
		Producto art3 = new Producto(3, "LLL", 30, "Vinoteca",
				"LLL", "");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);
		misproducts.add(art3);

		/*
		 * Restricciones
		 */
		RestriccionCategoria res1 = new RestriccionCategoria("Vinoteca");
		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		restricciones.add(res1);

		/*
		 * Bonificaciones
		 */
		BonificacionDescuentoCategoria bon1 = new BonificacionDescuentoCategoria(
				"Vinoteca", 10);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bon1);
		/*
		 * Excepciones
		 */
		RestriccionMarca execp1 = new RestriccionMarca("XXZ");
		RestriccionMarca execp2 = new RestriccionMarca("LLL");
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();
		excepciones.add(execp1);
		excepciones.add(execp2);
		/*
		 * Nueva promo
		 */
		ArrayList<MedioDePago> mDePagos = new ArrayList<MedioDePago>();
		mDePagos.add(new MedioDePago("Efectivo",""));
		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones, mDePagos);
		
		
		ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();
		misPromociones.add(promo1);
		
		
		// Caja cajaprincipal = new Caja(1234);

		Caja cajaprincipal = new Caja(1234, misPromociones);
		cajaprincipal.abrirCaja();
		try {
			cajaprincipal.iniciarCompra();

			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art2);
			cajaprincipal.agregarProducto(art3);
			cajaprincipal.agregarProducto(art3);

			MedioDePago med = new MedioDePago("Efectivo", "");
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

	assertEquals(98f,cajaprincipal.getCompras().get(0).getTotalCD(),0.00001);
	}
}

