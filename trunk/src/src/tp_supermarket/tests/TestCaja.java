package tp_supermarket.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import tp_supermarket.caja.Caja;
import tp_supermarket.caja.ExceptionAbrirCajaConCajaAbierta;
import tp_supermarket.caja.ExceptionTerminarCompraConCajaCerrada;
import tp_supermarket.caja.ExceptionTerminarCompraConCompraNoIniciada;
import tp_supermarket.caja.MedioDePago;
import tp_supermarket.caja.cerrarCajaConCompraEnCurso;
import tp_supermarket.caja.exceptions.ExceptionCerrarCajaConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionCerrarCajaConCompraEnCurso;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCompraEnCurso;
import tp_supermarket.producto.Producto;

public class TestCaja {

	private Caja unaCaja;

	@Before
	public void setUp() throws Exception {
		unaCaja = new Caja(1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCerrarCajaConCajaCerrada()
			throws cerrarCajaConCompraEnCurso {
		boolean estabaCerrada = false;
		boolean estaCerrada = true;
		try {
			unaCaja.cerrarCaja();
		} catch (ExceptionCerrarCajaConCajaCerrada e) {
		} catch (ExceptionCerrarCajaConCompraEnCurso e) {
			estabaCerrada = true;
			assertEquals("Verifica...", estaCerrada, estabaCerrada);
		}

	}

	@Test
	public void testCerrarCajaConCompraEnCurso()
			throws cerrarCajaConCompraEnCurso {
		unaCaja.setCompraEnCurso(true);
		try {
			unaCaja.cerrarCaja();
		} catch (ExceptionCerrarCajaConCajaCerrada e) {
		} catch (ExceptionCerrarCajaConCompraEnCurso e) {
			assertEquals(false, unaCaja.isEstadoCaja());
		}

	}

	@Test
	public void testIniciarCompraConCompraCajaCerrada()
			throws cerrarCajaConCompraEnCurso {
		unaCaja.setEstadoCaja(true);
		try {
			unaCaja.iniciarCompra();
		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
			assertEquals(true, unaCaja.isEstadoCaja());
			assertNull(unaCaja.getCompraActual());
			assertEquals(false, unaCaja.isCompraEnCurso());
		}

	}

	@Test
	public void testTerminarCompraNoTieneCompraActual()
			throws ExceptionIniciarCompraConCajaCerrada,
			ExceptionIniciarCompraConCompraEnCurso,
			ExceptionAbrirCajaConCajaAbierta, ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada {
		System.out.println("Caja cerrada: " + unaCaja.isEstadoCaja());
		unaCaja.abrirCaja();
		unaCaja.iniciarCompra();
		Producto unProducto = new Producto(1, "Coca cola", 10);
		unaCaja.agregarProducto(unProducto); // un producto
		unaCaja.terminarCompraActual(new MedioDePago());
		assertEquals(null, unaCaja.getCompraActual());
		assertEquals(false, unaCaja.isCompraEnCurso());
	}

	@Test
	public void testTerminarCompraNoAgregaCompra()
			throws ExceptionIniciarCompraConCajaCerrada,
			ExceptionIniciarCompraConCompraEnCurso,
			ExceptionAbrirCajaConCajaAbierta, ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada{
		unaCaja.abrirCaja();
		unaCaja.iniciarCompra();
		int cantCompras = unaCaja.getCompras().size();
		Producto unProducto = new Producto(1, "Coca cola", 10);
		unaCaja.agregarProducto(unProducto);
		unaCaja.terminarCompraActual(new MedioDePago());
		// verifico que se haya agregado la nueva compra, es decir tiene que
		// tener una compras mas
		assertEquals(unaCaja.getCompras().size(), cantCompras + 1);
	}

	@Test
	public void testAbrirYCerrarCaja() throws ExceptionAbrirCajaConCajaAbierta, ExceptionCerrarCajaConCompraEnCurso, ExceptionCerrarCajaConCajaCerrada {
			boolean cajaCerrada = false;
			unaCaja.abrirCaja();
			assertEquals(cajaCerrada, unaCaja.isEstadoCaja());
			unaCaja.cerrarCaja();
			cajaCerrada = true;
			assertEquals(cajaCerrada, unaCaja.isEstadoCaja());
	}
}