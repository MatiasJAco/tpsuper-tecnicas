package tp_supermarket.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import tp_supermarket.caja.Caja;
import tp_supermarket.caja.cerrarCajaConCompraEnCurso;
import tp_supermarket.caja.exceptions.ExceptionCerrarCajaConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionCerrarCajaConCompraEnCurso;

public class cajaTest {

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
}
