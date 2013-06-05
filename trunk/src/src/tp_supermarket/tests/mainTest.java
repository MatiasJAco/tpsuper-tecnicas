package tp_supermarket.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tp_supermarket.bonificacion.Bonificacion;
import tp_supermarket.bonificacion.BonificacionDescuentoCategoria;
import tp_supermarket.bonificacion.BonificacionDescuentoCupon;
import tp_supermarket.bonificacion.BonificacionDescuentoMarca;
import tp_supermarket.bonificacion.BonificacionDescuentoMedioDePago;
import tp_supermarket.bonificacion.BonificacionDescuentoNombre;
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
import tp_supermarket.restriccion.RestriccionTipoCliente;

public class mainTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDescuentoPor2por1YMedioDepago() throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada, ExceptionAbrirCajaConCajaAbierta{
		float VALOR_ESPERADO = 12.6f;		
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
		
		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones, mDePagos);
		/*
		 * Seteo de la fecha de vigencia de la promocion
		 */
		ArrayList<Integer> diasPromo = new ArrayList<Integer>();		
		diasPromo.add(Calendar.MONDAY);
		PeriodoValidez pValidez = new PeriodoValidezDiasSemana(diasPromo);
		promo1.setPeriodoValidezPromocion(pValidez);

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

			MedioDePago med = new MedioDePago("XYZ", "", 10, pValidez);
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

		assertEquals(VALOR_ESPERADO,cajaprincipal.getCompras().get(0).getTotalCD(),0.00001);
	}
	
	@Test
	public void testDescuento2daUnidadYMedioDePago() throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada, ExceptionAbrirCajaConCajaAbierta {
		float VALOR_ESPERADO = 259.2f;
		/*
		 * Productos
		 */
		Producto art1 = new Producto(1, "Maceta", 10, "Jardineria", "Maceta",
				"");
		Producto art2 = new Producto(2, "Cepillos de Dientes", 3, "Salud",
				"Cepi", "");
		Producto art3 = new Producto(3, "Vino X", 100, "Vinoteca",
				"X", "");
		Producto art4 = new Producto(4, "Chandon", 75, "Vinoteca",
				"Chandon", "");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);
		misproducts.add(art3);
		misproducts.add(art3);
		misproducts.add(art4);
		misproducts.add(art4);

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
		ArrayList<MedioDePago> mDePagos = new ArrayList<MedioDePago>();
		mDePagos.add(new MedioDePago("Tarjeta de Debito",""));

		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones,mDePagos);
		
		ArrayList<Integer> diasPromo = new ArrayList<Integer>();		
		diasPromo.add(Calendar.MONDAY);
		PeriodoValidez pValidez = new PeriodoValidezDiasSemana(diasPromo);
		promo1.setPeriodoValidezPromocion(pValidez);
		


		
		/*
		 * Seteo de la fecha de vigencia de la promocion
		 */
		ArrayList<Integer> diasPromo1 = new ArrayList<Integer>();		
		diasPromo1.add(Calendar.MONDAY);
		PeriodoValidez pValidez1 = new PeriodoValidezDiasSemana(diasPromo1);
		promo1.setPeriodoValidezPromocion(pValidez1);

		ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();
		;
		misPromociones.add(promo1);
		// Caja cajaprincipal = new Caja(1234);

		Caja cajaprincipal = new Caja(1234, misPromociones);
		cajaprincipal.abrirCaja();
		try {
			cajaprincipal.iniciarCompra();

			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art2);
			cajaprincipal.agregarProducto(art3);
			cajaprincipal.agregarProducto(art3);
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art4);

			MedioDePago med = new MedioDePago("Tarjeta de Debito", "",10,pValidez1);
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

		assertEquals(VALOR_ESPERADO,cajaprincipal.getCompras().get(0).getTotalCD(),0.00001);
	}
	
	@Test
	public void testDescuentoPorCategoriaConExcepcion()  throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada, ExceptionAbrirCajaConCajaAbierta {

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
	
	@Test
	public void testDescuentoJubilados() throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada, ExceptionAbrirCajaConCajaAbierta {
		
		float TOTAL_ESPERADO = (2*15 + 10)*(0.9f);

		Producto art1 = new Producto(1, "Lamparita", 15, "Luminaria",
				"Philips", "");
		Producto art2 = new Producto(2, "Maceta", 10, "Jardineria", "Maceta",
				"");

		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);

		/*
		 * Restricciones
		 */
		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();

		/*
		 * Bonificaciones
		 */
		
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		
		/*
		 * Restriccion de tipos de clientes
		 */

		ArrayList<RestriccionTipoCliente> cli = new ArrayList<RestriccionTipoCliente>();
		cli.add(new RestriccionTipoCliente("Jubilados", 10.0f));
		
		
		/*
		 * Nueva promo
		 */
		Promocion promo1 = new Promocion(restricciones, bonificaciones);
		promo1.setTiposClientesAplicanPromo(cli);
		
		
		ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();
		misPromociones.add(promo1);
		// Caja cajaprincipal = new Caja(1234);

		Caja cajaprincipal = new Caja(1234, misPromociones);
		cajaprincipal.abrirCaja();

		try {
			cajaprincipal.iniciarCompra();
			cajaprincipal.getCompraActual().setTipoCliente("Jubilados");

			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art2);
			
			MedioDePago med = new MedioDePago("Efectivo", "");
			cajaprincipal.terminarCompraActual(med);
			//verifico que esten bien los productos

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}
		
		
		
		assertEquals(TOTAL_ESPERADO,cajaprincipal.getCompraActual().getTotalCD(),0.00001);
		
	}
	
	@Test
	public void testRakingMasVendidos() throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada, ExceptionAbrirCajaConCajaAbierta {

		Producto art1 = new Producto(1, "CocaCola", 10, "Bebidas",
				"CocaCola", "");
		Producto art2 = new Producto(2, "Maceta", 10, "Jardineria", "Maceta",
				"");
		Producto art3 = new Producto(1002,"Sprite 1,5 lt.",10,"Bebidas","CocaCola", "");

		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);
		misproducts.add(art3);

		/*
		 * Restricciones
		 */
		RestriccionMarca res1 = new RestriccionMarca("CocaCola"); // ojo que tienen nombre de marca distinto
		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		restricciones.add(res1);

		/*
		 * Bonificaciones
		 */
		BonificacionDescuentoNombre bon1 = new BonificacionDescuentoNombre(
				"Sprite 1,5 lt.", 1,30);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();

		bonificaciones.add(bon1);
		
		/*
		 * Nueva promo
		 */
		Promocion promo1 = new Promocion(restricciones, bonificaciones);
		
		
		ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();
		misPromociones.add(promo1);
		// Caja cajaprincipal = new Caja(1234);

		Caja cajaprincipal = new Caja(1234, misPromociones);
		cajaprincipal.abrirCaja();
		ArrayList<Producto> rankProductos = null;
		try {
			cajaprincipal.iniciarCompra();

			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);

			cajaprincipal.agregarProducto(art2);
			cajaprincipal.agregarProducto(art2);
			cajaprincipal.agregarProducto(art2);
			cajaprincipal.agregarProducto(art2);
			cajaprincipal.agregarProducto(art2);

			cajaprincipal.agregarProducto(art3);
			cajaprincipal.agregarProducto(art3);
			cajaprincipal.agregarProducto(art3);
			cajaprincipal.agregarProducto(art3);
			cajaprincipal.agregarProducto(art3);
			cajaprincipal.agregarProducto(art3);

			MedioDePago med = new MedioDePago("Efectivo", "");
			cajaprincipal.terminarCompraActual(med);
			rankProductos = cajaprincipal.getRankinProductos();
			//verifico que esten bien los productos

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}
		
		Producto sprite = rankProductos.get(2);
		Producto maceta = rankProductos.get(1);
		Producto cocaCola = rankProductos.get(0);
		
		assertEquals(6,sprite.getCantidadVendidas());
		assertEquals(5,maceta.getCantidadVendidas());
		assertEquals(4,cocaCola.getCantidadVendidas());

	}
	
	@Test
	public void testPromoCuponDescuentoGeneracionDeCupon() {
		float DESCUENTO_POR_MEDIO_DE_PAGO = 0.50f;
		float valorDeCuponEsperado = 0;
		float aux;
		float total = 0;
		/*
		 * Definicion de los medios de pagos para la promocion
		 */
		ArrayList<MedioDePago> mediosDePagosPromo = new ArrayList<MedioDePago>();
		MedioDePago medioDePago1 = new MedioDePago("Cupon", "Super");
		mediosDePagosPromo.add(medioDePago1);
		
		/*
		 * Bonificacion por medio de pago
		 */
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		BonificacionDescuentoCupon bDescuento = new BonificacionDescuentoCupon(20,10,1,2,"Coca Cola");
		bonificaciones.add(bDescuento);
		/*
		 * Restricciones de la promo
		 */
		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		/*
		 * Excepciones de la promo
		 */
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();
		/*
		 * Nueva promo
		 */
		Promocion miPromo = new Promocion(restricciones, excepciones,
				bonificaciones, mediosDePagosPromo);
		/*
		 * Lista de productos
		 */
		Producto miProd1 = new Producto(1, "Coca Cola", 1, "Gaseosas",
				"Coca Cola", "");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(miProd1);
		misproducts.add(miProd1);
		misproducts.add(miProd1);
		misproducts.add(miProd1);
		misproducts.add(miProd1);
		misproducts.add(miProd1);
		misproducts.add(miProd1);
		misproducts.add(miProd1);
		misproducts.add(miProd1);
		misproducts.add(miProd1);
		/*
		 * Se aplica la promo
		 */
		for (int j = 0; j < misproducts.size(); j++) {
			miPromo.checkProducto(misproducts.get(j));
		}
		miPromo.checkProductos(misproducts);
//		for (int i = 0; i < misproducts.size(); i++) {
//			aux = misproducts.get(i).getCosto();
//			totalEsperado += (aux - (DESCUENTO_POR_MEDIO_DE_PAGO * aux));
//		}
		valorDeCuponEsperado=5;
		if (miPromo.isActiva()) {
			ArrayList<Producto> misDescuentos = miPromo
					.aplicarBonificaciones(misproducts);
			// Agregar descuentos
			for (int i = 0; i < misDescuentos.size(); i++) {
				misproducts.add(misDescuentos.get(i));
			}

		}		
		for (int i = 0; i < misproducts.size(); i++) {
			total += misproducts.get(i).getCosto();
		}
		
		assertEquals(valorDeCuponEsperado,((BonificacionDescuentoCupon) miPromo.getBonificaciones().get(0)).getCuponGenerado().getValor() , 0.0001);
	}
}

