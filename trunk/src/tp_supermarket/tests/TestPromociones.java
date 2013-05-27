package tp_supermarket.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import tp_supermarket.bonificacion.Bonificacion;
import tp_supermarket.bonificacion.BonificacionDescuentoCategoria;
import tp_supermarket.bonificacion.BonificacionDescuentoMarca;
import tp_supermarket.bonificacion.BonificacionDescuentoNombre;
import tp_supermarket.fecha.Fecha;
import tp_supermarket.producto.Producto;
import tp_supermarket.promocion.Promocion;
import tp_supermarket.restriccion.Restriccion;
import tp_supermarket.restriccion.RestriccionCantidad;
import tp_supermarket.restriccion.RestriccionCategoria;
import tp_supermarket.restriccion.RestriccionMarca;
import tp_supermarket.restriccion.RestriccionNombreProducto;

public class TestPromociones {

	@Test
	public void testPromoActivaMarca() {
		RestriccionMarca rMArca = new RestriccionMarca("Pepsi");
		ArrayList<Restriccion> restricciones= new ArrayList<Restriccion>();
		restricciones.add(rMArca);
		ArrayList<Restriccion> excepciones =new ArrayList<Restriccion>();
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();				
		Promocion miPromo =new Promocion(restricciones,excepciones,bonificaciones);
		Producto miProd = new Producto(1,"Pepsi",100,"Alimentos","Pepsi","");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(miProd);
		for (int j=0;j<misproducts.size();j++){
			miPromo.checkProducto(misproducts.get(j));
		}
		miPromo.checkProductos(misproducts);
		assertEquals(true, miPromo.isActiva());
		
	}
	
	
	@Test
	public void testPromoActivaMarcaCantidad() {
		RestriccionMarca rMArca = new RestriccionMarca("Pepsi");
		RestriccionCantidad rCantidad = new RestriccionCantidad(4);
		rMArca.setCantidad(rCantidad);
		ArrayList<Restriccion> restricciones= new ArrayList<Restriccion>();
		restricciones.add(rMArca);
		ArrayList<Restriccion> excepciones =new ArrayList<Restriccion>();
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();				
		Promocion miPromo =new Promocion(restricciones,excepciones,bonificaciones);
		Producto miProd = new Producto(1,"Pepsi",100,"Alimentos","Pepsi","");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(miProd);
		misproducts.add(miProd);
		misproducts.add(miProd);	
		misproducts.add(miProd);
		for (int j=0;j<misproducts.size();j++){
			miPromo.checkProducto(misproducts.get(j));
		}
//		miPromo.checkProductos(misproducts);
		assertEquals(true, miPromo.isActiva());
		
	}
	
	@Test
	public void testPromoActivaMarcaCantidadDiferentes() {
		RestriccionMarca rMArcaPepsi = new RestriccionMarca("Pepsi");
		RestriccionMarca rMArcaFanta = new RestriccionMarca("Fanta",2);
		RestriccionCantidad rCantidad = new RestriccionCantidad(3);
		rMArcaPepsi.setCantidad(rCantidad);
		ArrayList<Restriccion> restricciones= new ArrayList<Restriccion>();
		restricciones.add(rMArcaPepsi);
		restricciones.add(rMArcaFanta);
		ArrayList<Restriccion> excepciones =new ArrayList<Restriccion>();
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();				
		Promocion miPromo =new Promocion(restricciones,excepciones,bonificaciones);
		Producto miProdPepsi = new Producto(1,"Pepsi",100,"Alimentos","Pepsi","");
		Producto miProdFanta = new Producto(2,"Fanta",100,"Alimentos","Fanta","");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(miProdPepsi);
		misproducts.add(miProdFanta);
		misproducts.add(miProdPepsi);
		misproducts.add(miProdPepsi);
		misproducts.add(miProdFanta);
//		miPromo.checkProductos(misproducts);
		for (int j=0;j<misproducts.size();j++){
			miPromo.checkProducto(misproducts.get(j));
		}
		assertEquals(true, miPromo.isActiva());
	}

	@Test
	public void testPromoMarcaDescuento() {
		RestriccionMarca rMArca = new RestriccionMarca("Pepsi");
		BonificacionDescuentoMarca bDescuento = new BonificacionDescuentoMarca("Pepsi",1,50);
		ArrayList<Restriccion> restricciones= new ArrayList<Restriccion>();
		restricciones.add(rMArca);
		
		ArrayList<Restriccion> excepciones =new ArrayList<Restriccion>();
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bDescuento);
		Promocion miPromo =new Promocion(restricciones,excepciones,bonificaciones);
		Producto miProd = new Producto(1,"Pepsi",100,"Alimentos","Pepsi","");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(miProd);
		for (int j=0;j<misproducts.size();j++){
			miPromo.checkProducto(misproducts.get(j));
		}
//		miPromo.checkProductos(misproducts);
		float totalEsperado =0;
		for (int i=0; i<misproducts.size();i++){
			totalEsperado+=misproducts.get(i).getCosto();			
		}
		totalEsperado -= (50 * miProd.getCosto())/100;
		if (miPromo.isActiva()){
			ArrayList<Producto> misDescuentos = miPromo.aplicarBonificaciones(misproducts);
			//Agregar descuentos
			for (int i=0;i<misDescuentos.size();i++){
				misproducts.add(misDescuentos.get(i));
			}
			
		}
		float total=0;
		for (int i=0; i<misproducts.size();i++){
			total+=misproducts.get(i).getCosto();			
		}
		
		
		
		assertEquals(totalEsperado, total, 0.0001);
	}
	
	
	@Test
	public void testPromoCategoriaVinotecaDescuento() {
		RestriccionCategoria rMArca = new RestriccionCategoria("Vinoteca");
		BonificacionDescuentoCategoria bDescuento = new BonificacionDescuentoCategoria("Vinoteca",40);
		ArrayList<Restriccion> restricciones= new ArrayList<Restriccion>();
		restricciones.add(rMArca);
		
		ArrayList<Restriccion> excepciones =new ArrayList<Restriccion>();
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bDescuento);
		Promocion miPromo =new Promocion(restricciones,excepciones,bonificaciones);
		Producto miProd1 = new Producto(1,"Vino Toro",100,"Vinoteca","Vino Toro","");
		Producto miProd2 = new Producto(1,"Vino Toro Tetra",40,"Vinoteca","Vino Toro","");
		Producto miProd3 = new Producto(1,"Chandon",500,"Vinoteca","Chandon","");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(miProd1);
		misproducts.add(miProd2);
		misproducts.add(miProd3);
		for (int j=0;j<misproducts.size();j++){
			miPromo.checkProducto(misproducts.get(j));
		}
//		miPromo.checkProductos(misproducts);
		float totalEsperado =0;
		for (int i=0; i<misproducts.size();i++){
			totalEsperado+=(misproducts.get(i).getCosto()-((40 * misproducts.get(i).getCosto())/100));			
		}
		if (miPromo.isActiva()){
			ArrayList<Producto> misDescuentos = miPromo.aplicarBonificaciones(misproducts);
			//Agregar descuentos
			for (int i=0;i<misDescuentos.size();i++){
				misproducts.add(misDescuentos.get(i));
			}
			
		}
		float total=0;
		for (int i=0; i<misproducts.size();i++){
			total+=misproducts.get(i).getCosto();			
		}		
		assertEquals(totalEsperado, total, 0.0001);
	}
	
	@Test
	public void testPromoCategoriaVinotecaDescuentoConExcepciones() {
		RestriccionCategoria rMArca = new RestriccionCategoria("Vinoteca");
		RestriccionMarca exc = new RestriccionMarca("Chandon");
		BonificacionDescuentoCategoria bDescuento = new BonificacionDescuentoCategoria("Vinoteca",40);
		ArrayList<Restriccion> restricciones= new ArrayList<Restriccion>();
		restricciones.add(rMArca);
		
		ArrayList<Restriccion> excepciones =new ArrayList<Restriccion>();
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		excepciones.add(exc);
		bonificaciones.add(bDescuento);
		Promocion miPromo =new Promocion(restricciones,excepciones,bonificaciones);
		Producto miProd1 = new Producto(1,"Vino Toro",100,"Vinoteca","Vino Toro","");
		Producto miProd2 = new Producto(1,"Vino Toro Tetra",40,"Vinoteca","Vino Toro","");
		Producto miProd3 = new Producto(1,"Chandon",500,"Vinoteca","Chandon","");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(miProd1);
		misproducts.add(miProd2);
		misproducts.add(miProd3);
		for (int j=0;j<misproducts.size();j++){
			miPromo.checkProducto(misproducts.get(j));
		}
//		miPromo.checkProductos(misproducts);
		float totalEsperado =0;
		for (int i=0; i<misproducts.size();i++){
			if (misproducts.get(i).getMarca() =="Chandon")
				totalEsperado+=misproducts.get(i).getCosto();
			else
				totalEsperado+=(misproducts.get(i).getCosto()-((40 * misproducts.get(i).getCosto())/100));			
		}
		if (miPromo.isActiva()){
			ArrayList<Producto> misDescuentos = miPromo.aplicarBonificaciones(misproducts);
			//Agregar descuentos
			for (int i=0;i<misDescuentos.size();i++){
				misproducts.add(misDescuentos.get(i));
			}
			
		}
		float total=0;
		for (int i=0; i<misproducts.size();i++){
			total+=misproducts.get(i).getCosto();			
		}		
		assertEquals(totalEsperado, total, 0.0001);
	}
	
	@Test
	public void testPromoActivaPorFecha() {
		RestriccionMarca rMArca = new RestriccionMarca("Pepsi");
		ArrayList<Restriccion> restricciones= new ArrayList<Restriccion>();
		restricciones.add(rMArca);
		ArrayList<Restriccion> excepciones =new ArrayList<Restriccion>();
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();				
		Promocion miPromo =new Promocion(restricciones,excepciones,bonificaciones);
		/*
		 * Seteo de la fecha de vigencia de la promocion
		 */
		Fecha fechaI = new Fecha();
		Fecha fechaF = new Fecha();
		fechaI.getFecha().set(Calendar.HOUR_OF_DAY, 00);
		fechaI.getFecha().set(Calendar.MINUTE, 00);
		fechaI.getFecha().set(Calendar.SECOND, 00);
		fechaF.getFecha().add(Calendar.DAY_OF_MONTH, 2);
		miPromo.setPeriodoValidezPromocion(fechaI, fechaF);
		miPromo.verificarSiPromocionEstaActivaPorFecha();
		//-----------------------------------
		assertEquals(true, miPromo.isActivaPorFecha());
	}
	
	@Test
	public void testPromoNombreDescuento() {
		RestriccionNombreProducto rNombre = new RestriccionNombreProducto("Pepsi");
		BonificacionDescuentoNombre bDescuento = new BonificacionDescuentoNombre("Pepsi",1,50);
		ArrayList<Restriccion> restricciones= new ArrayList<Restriccion>();
		restricciones.add(rNombre);
		
		ArrayList<Restriccion> excepciones =new ArrayList<Restriccion>();
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bDescuento);
		Promocion miPromo =new Promocion(restricciones,excepciones,bonificaciones);
		Producto miProd = new Producto(1,"Pepsi",100,"Alimentos","","");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(miProd);
		for (int j=0;j<misproducts.size();j++){
			miPromo.checkProducto(misproducts.get(j));
		}
//		miPromo.checkProductos(misproducts);
		float totalEsperado =0;
		for (int i=0; i<misproducts.size();i++){
			totalEsperado+=misproducts.get(i).getCosto();			
		}
		totalEsperado -= (50 * miProd.getCosto())/100;
		if (miPromo.isActiva()){
			ArrayList<Producto> misDescuentos = miPromo.aplicarBonificaciones(misproducts);
			//Agregar descuentos
			for (int i=0;i<misDescuentos.size();i++){
				misproducts.add(misDescuentos.get(i));
			}
			
		}
		float total=0;
		for (int i=0; i<misproducts.size();i++){
			total+=misproducts.get(i).getCosto();			
		}		
		assertEquals(totalEsperado, total, 0.0001);
	}
	
	
	
}
