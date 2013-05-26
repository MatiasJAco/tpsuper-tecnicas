import static org.junit.Assert.*;

import org.junit.Test;


public class TestCaja {
	
	
	@Test
	public void testCerrarCajaConCajaCerrada() throws ExceptionCerrarCajaConCompraEnCurso {
		
		Caja cajaTest = new Caja(1);
		
		boolean estabaCerrada = false;
		boolean estaCerrada = true;
	
		try {
			cajaTest.cerrarCaja();
		}
		catch (ExceptionCerrarCajaConCajaCerrada e) {
			estabaCerrada = true;
			assertEquals("Verifica...", estaCerrada, estabaCerrada);
		}
		
	}

}
