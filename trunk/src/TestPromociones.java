import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;


public class TestPromociones {

	@Test
	public void testPromoActivaMarca() {
		RestriccionMarca rMArca = new RestriccionMarca("Pepsi");
		Vector<Restriccion> restricciones= new Vector<Restriccion>();
		restricciones.add(rMArca);
		Vector<Restriccion> excepciones =new Vector<Restriccion>();
		Vector<Bonificacion> bonificaciones = new Vector<Bonificacion>();				
		Promocion miPromo =new Promocion(restricciones,excepciones,bonificaciones);
		Producto miProd = new Producto(1,"Pepsi",100);
		Vector<Producto> misproducts = new Vector<Producto>();
		misproducts.add(miProd);
		miPromo.checkProductos(misproducts);
		assertEquals(true, miPromo.isActiva());
		
	}
	
	
	@Test
	public void testPromoActivaMarcaCantidad() {
		RestriccionMarca rMArca = new RestriccionMarca("Pepsi");
		RestriccionCantidad rCantidad = new RestriccionCantidad(4);
		rMArca.setCantidad(rCantidad);
		Vector<Restriccion> restricciones= new Vector<Restriccion>();
		restricciones.add(rMArca);
		Vector<Restriccion> excepciones =new Vector<Restriccion>();
		Vector<Bonificacion> bonificaciones = new Vector<Bonificacion>();				
		Promocion miPromo =new Promocion(restricciones,excepciones,bonificaciones);
		Producto miProd = new Producto(1,"Pepsi",100);
		Vector<Producto> misproducts = new Vector<Producto>();
		misproducts.add(miProd);
		misproducts.add(miProd);
		misproducts.add(miProd);	
		misproducts.add(miProd);	
		miPromo.checkProductos(misproducts);
		assertEquals(true, miPromo.isActiva());
		
	}
	
	@Test
	public void testPromoActivaMarcaCantidadDiferentes() {
		RestriccionMarca rMArcaPepsi = new RestriccionMarca("Pepsi");
		RestriccionMarca rMArcaFanta = new RestriccionMarca("Fanta",2);
		RestriccionCantidad rCantidad = new RestriccionCantidad(3);
		rMArcaPepsi.setCantidad(rCantidad);
		Vector<Restriccion> restricciones= new Vector<Restriccion>();
		restricciones.add(rMArcaPepsi);
		restricciones.add(rMArcaFanta);
		Vector<Restriccion> excepciones =new Vector<Restriccion>();
		Vector<Bonificacion> bonificaciones = new Vector<Bonificacion>();				
		Promocion miPromo =new Promocion(restricciones,excepciones,bonificaciones);
		Producto miProdPepsi = new Producto(1,"Pepsi",100);
		Producto miProdFanta = new Producto(2,"Fanta",100);
		Vector<Producto> misproducts = new Vector<Producto>();
		misproducts.add(miProdPepsi);
		misproducts.add(miProdFanta);
		misproducts.add(miProdPepsi);
		misproducts.add(miProdPepsi);
		misproducts.add(miProdFanta);
		miPromo.checkProductos(misproducts);
		assertEquals(true, miPromo.isActiva());
	}

	@Test
	public void testPromoMarcaDescuento() {
		RestriccionMarca rMArca = new RestriccionMarca("Pepsi");
		BonificacionDescuento bDescuento = new BonificacionDescuento("Pepsi",1,50);
		Vector<Restriccion> restricciones= new Vector<Restriccion>();
		restricciones.add(rMArca);
		
		Vector<Restriccion> excepciones =new Vector<Restriccion>();
		Vector<Bonificacion> bonificaciones = new Vector<Bonificacion>();
		bonificaciones.add(bDescuento);
		Promocion miPromo =new Promocion(restricciones,excepciones,bonificaciones);
		Producto miProd = new Producto(1,"Pepsi",100);
		Vector<Producto> misproducts = new Vector<Producto>();
		misproducts.add(miProd);
		
		miPromo.checkProductos(misproducts);
		float totalEsperado =0;
		for (int i=0; i<misproducts.size();i++){
			totalEsperado+=misproducts.get(i).getCosto();			
		}
		totalEsperado -= (50 * miProd.getCosto())/100;
		if (miPromo.isActiva()){
			misproducts = miPromo.aplicarBonificaciones(misproducts);
			
		}
		float total=0;
		for (int i=0; i<misproducts.size();i++){
			total+=misproducts.get(i).getCosto();			
		}
		
		
		
		assertEquals(totalEsperado, total, 0.0001);
	}
	
}
