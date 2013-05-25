import static org.junit.Assert.*;

import org.junit.Test;


public class cajaTest {
	
	
	@Test
	public void testCerrarCajaConCajaCerrada() throws cerrarCajaConCompraEnCurso {
		
		Caja cajaTest = new Caja(1);
		
		boolean estabaCerrada = false;
		boolean estaCerrada = true;
	
		try {
			cajaTest.cerrarCaja();
		}
		catch (cerrarCajaConCajaCerrada e) {
			estabaCerrada = true;
			assertEquals("Verifica...", estaCerrada, estabaCerrada);
		}
		
	}

}
