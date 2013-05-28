package tp_supermarket.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import tp_supermarket.caja.Caja;
import tp_supermarket.caja.cerrarCajaConCompraEnCurso;
import tp_supermarket.caja.exceptions.ExceptionCerrarCajaConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionCerrarCajaConCompraEnCurso;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCompraEnCurso;

public class TestCaja {
	@Test
	public void testCerrarCajaConCajaCerrada()
			throws cerrarCajaConCompraEnCurso {
		Caja cajaTest = new Caja(1);
		boolean estabaCerrada = false;
		boolean estaCerrada = true;
		try {
			cajaTest.cerrarCaja();
		} catch (ExceptionCerrarCajaConCajaCerrada e) {
		} catch (ExceptionCerrarCajaConCompraEnCurso e) {
			estabaCerrada = true;
			assertEquals("Verifica...", estaCerrada, estabaCerrada);
		}

	}

	@Test
	public void testCerrarCajaConCompraEnCurso()
			throws cerrarCajaConCompraEnCurso {
		Caja unaCaja = new Caja(1);
		unaCaja.setCompraEnCurso(true);
		try {
			unaCaja.cerrarCaja();
		} catch (ExceptionCerrarCajaConCajaCerrada e) {
		} catch (ExceptionCerrarCajaConCompraEnCurso e) {
			assertEquals(unaCaja.isEstadoCaja(), false);
		}

	}

	@Test
	public void testIniciarCompraConCompraCajaCerrada()
			throws cerrarCajaConCompraEnCurso {
		Caja unaCaja = new Caja(1);
		unaCaja.setEstadoCaja(true);
		try {
			unaCaja.iniciarCompra();
		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
			assertEquals(unaCaja.isEstadoCaja(), true);
			assertNull(unaCaja.getCompraActual());
			assertEquals(unaCaja.isCompraEnCurso(), false);
		}

	}
}