package tp_supermarket;

import java.util.ArrayList;
import tp_supermarket.restriccion.*;
import tp_supermarket.producto.*;
import tp_supermarket.promocion.*;
import tp_supermarket.bonificacion.*;
import tp_supermarket.caja.Caja;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCompraEnCurso;

public class Main {

	/**
	 * @param args
	 * @throws ExceptionIniciarCompraConCompraEnCurso 
	 * @throws ExceptionIniciarCompraConCajaCerrada 
	 * @throws exepcionCajaCerradaoCompraEnCurso 
	 */
	public static void main(String[] args) throws ExceptionIniciarCompraConCajaCerrada, ExceptionIniciarCompraConCompraEnCurso{

		Producto art1 = new Producto(1,"Galletas",100);
		Producto art2 = new Producto(2,"Manteca",100);
		Producto art3 = new Producto(3,"Carne Fresca",100);
		Producto art4 = new Producto(4,"Coca cola 1lt",100);
		Producto art5 = new Producto(5,"Mcallan 24 years",100);

		RestriccionMarca res1 = new RestriccionMarca("Coca cola 1lt", 1);
		RestriccionMarca res2 = new RestriccionMarca("Galletas", 2);
		BonificacionDescuentoMarca bon1 = new BonificacionDescuentoMarca("Galletas", 2, 30);
		
				
		ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();	
		Promocion promo1 = new Promocion();
		promo1.addRestriccion(res1);		
		promo1.addRestriccion(res2);
		promo1.addBonificacion(bon1);
		misPromociones.add(promo1);
//		Caja cajaprincipal = new Caja(1234);
		
		Caja cajaprincipal = new Caja(1234,misPromociones);
		cajaprincipal.abrirCaja();
		cajaprincipal.iniciarCompra();
		
		cajaprincipal.agregarProducto(art1);
		cajaprincipal.agregarProducto(art2);
		cajaprincipal.agregarProducto(art3);
		cajaprincipal.agregarProducto(art4);
		cajaprincipal.agregarProducto(art5);
		cajaprincipal.agregarProducto(art1);
		cajaprincipal.agregarProducto(art4);
		cajaprincipal.agregarProducto(art1);
		cajaprincipal.agregarProducto(art1);
		
		cajaprincipal.terminarCompraActual();
	
		
	}

}
