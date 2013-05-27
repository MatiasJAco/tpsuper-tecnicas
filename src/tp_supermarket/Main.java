package tp_supermarket;

import java.util.ArrayList;
import tp_supermarket.restriccion.*;
import tp_supermarket.producto.*;
import tp_supermarket.promocion.*;
import tp_supermarket.bonificacion.*;
import tp_supermarket.caja.Caja;
import tp_supermarket.caja.MedioDePago;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCompraEnCurso;

public class Main {

	/**
	 * @param args
	 * @throws ExceptionIniciarCompraConCompraEnCurso
	 * @throws ExceptionIniciarCompraConCajaCerrada
	 * @throws exepcionCajaCerradaoCompraEnCurso
	 */
	public static void main(String[] args)
			throws ExceptionIniciarCompraConCajaCerrada,
			ExceptionIniciarCompraConCompraEnCurso {

		System.out.println("-----------------------------------");
		System.out.println("PRUEBA1");
		System.out.println("-----------------------------------");
		prueba1();
		System.out.println("-----------------------------------");
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("PRUEBA2");
		System.out.println("-----------------------------------");
		prueba2();
		System.out.println("-----------------------------------");
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("PRUEBA3");
		System.out.println("-----------------------------------");
		prueba3();
		System.out.println("-----------------------------------");
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("PRUEBA4");
		System.out.println("-----------------------------------");
		prueba4();
		System.out.println("-----------------------------------");
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("PRUEBA5");
		System.out.println("-----------------------------------");
		prueba5();
		System.out.println("-----------------------------------");
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("PRUEBA6");
		System.out.println("-----------------------------------");
		prueba6();
		System.out.println("-----------------------------------");
		System.out.println();
	}

	public static void prueba1() {

		/*
		 * Productos
		 */
		Producto art1 = new Producto(1, "Galletas", 100, "Alimentos",
				"Galletas", "");
		Producto art2 = new Producto(2, "Manteca", 100, "Alimentos", "Manteca",
				"");
		Producto art3 = new Producto(3, "Carne Fresca", 100, "Carnes",
				"Las lilas", "");
		Producto art4 = new Producto(4, "Coca cola 1lt", 100, "Bebidas",
				"CocaCola", "");
		Producto art5 = new Producto(5, "Mcallan 24 years", 100, "Bebidas",
				"Mcallan", "");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);
		misproducts.add(art3);
		misproducts.add(art4);
		misproducts.add(art5);

		/*
		 * Restricciones
		 */
		RestriccionMarca res1 = new RestriccionMarca("CocaCola", 1);
		RestriccionMarca res2 = new RestriccionMarca("Galletas", 2);
		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		restricciones.add(res1);
		restricciones.add(res2);

		/*
		 * Bonificaciones
		 */
		BonificacionDescuentoMarca bon1 = new BonificacionDescuentoMarca(
				"Galletas", 2, 30);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bon1);

		/*
		 * Excepciones
		 */
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();

		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones);

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
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art5);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);
			MedioDePago med = new MedioDePago();
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

	}

	public static void prueba2() {

		/*
		 * Productos
		 */
		Producto art1 = new Producto(1, "Galletas", 100, "Alimentos",
				"Galletas", "");
		Producto art2 = new Producto(2, "Manteca", 100, "Alimentos", "Manteca",
				"");
		Producto art3 = new Producto(3, "Carne Fresca", 100, "Carnes",
				"Las lilas", "");
		Producto art4 = new Producto(4, "Coca cola 1lt", 100, "Bebidas",
				"CocaCola", "");
		Producto art5 = new Producto(5, "Mcallan 24 years", 100, "Bebidas",
				"Mcallan", "");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);
		misproducts.add(art3);
		misproducts.add(art4);
		misproducts.add(art5);

		/*
		 * Restricciones
		 */
		RestriccionMarca res1 = new RestriccionMarca("CocaCola", 1);
		RestriccionMarca res2 = new RestriccionMarca("Galletas", 4);

		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		restricciones.add(res1);
		restricciones.add(res2);

		/*
		 * Bonificaciones
		 */
		BonificacionDescuentoMarca bon1 = new BonificacionDescuentoMarca(
				"Galletas", 2, 30);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bon1);

		/*
		 * Excepciones
		 */
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();

		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones);

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
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art5);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);

			MedioDePago med = new MedioDePago();
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

	}

	public static void prueba3() {

		/*
		 * Productos
		 */
		Producto art1 = new Producto(1, "Galletas", 100, "Alimentos",
				"Galletas", "");
		Producto art2 = new Producto(2, "Manteca", 100, "Alimentos", "Manteca",
				"");
		Producto art3 = new Producto(3, "Carne Fresca", 100, "Carnes",
				"Las lilas", "");
		Producto art4 = new Producto(4, "Coca cola 1lt", 100, "Bebidas",
				"CocaCola", "");
		Producto art5 = new Producto(5, "Mcallan 24 years", 100, "Bebidas",
				"Mcallan", "");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);
		misproducts.add(art3);
		misproducts.add(art4);
		misproducts.add(art5);

		/*
		 * Restricciones
		 */
		RestriccionMarca res1 = new RestriccionMarca("CocaCola", 2);
		RestriccionMarca res2 = new RestriccionMarca("Galletas", 2);

		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		restricciones.add(res1);
		restricciones.add(res2);

		/*
		 * Bonificaciones
		 */
		BonificacionDescuentoMarca bon1 = new BonificacionDescuentoMarca(
				"Galletas", 2, 30);
		BonificacionDescuentoMarca bon2 = new BonificacionDescuentoMarca(
				"CocaCola", 1, 15);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bon1);
		bonificaciones.add(bon2);

		/*
		 * Excepciones
		 */
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();

		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones);

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
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art5);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);
			MedioDePago med = new MedioDePago();
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

	}

	public static void prueba4() {

		/*
		 * Productos
		 */
		Producto art1 = new Producto(1, "Galletas", 100, "Alimentos",
				"Galletas", "");
		Producto art2 = new Producto(2, "Manteca", 100, "Alimentos", "Manteca",
				"");
		Producto art3 = new Producto(3, "Carne Fresca", 100, "Carnes",
				"Las lilas", "");
		Producto art4 = new Producto(4, "Coca cola 1lt", 100, "Bebidas",
				"CocaCola", "");
		Producto art5 = new Producto(5, "Mcallan 24 years", 100, "Bebidas",
				"Mcallan", "");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);
		misproducts.add(art3);
		misproducts.add(art4);
		misproducts.add(art5);

		/*
		 * Restricciones
		 */
		RestriccionMarca res1 = new RestriccionMarca("CocaCola", 2);
		RestriccionCategoria res2 = new RestriccionCategoria("Alimentos", 2);
		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		restricciones.add(res1);
		restricciones.add(res2);

		/*
		 * Bonificaciones
		 */
		BonificacionDescuentoCategoria bon1 = new BonificacionDescuentoCategoria(
				"Alimentos", 30);
		BonificacionDescuentoMarca bon2 = new BonificacionDescuentoMarca(
				"CocaCola", 1, 15);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bon1);
		bonificaciones.add(bon2);

		/*
		 * Excepciones
		 */
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();

		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones);

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
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art5);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art2);
			cajaprincipal.agregarProducto(art3);
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art5);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);

			MedioDePago med = new MedioDePago();
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

	}

	public static void prueba5() {

		/*
		 * Productos
		 */
		Producto art1 = new Producto(1, "Galletas", 100, "Alimentos",
				"Galletas", "");
		Producto art2 = new Producto(2, "Manteca", 100, "Alimentos", "Manteca",
				"");
		Producto art3 = new Producto(3, "Carne Fresca", 100, "Carnes",
				"Las lilas", "");
		Producto art4 = new Producto(4, "Coca cola 1lt", 100, "Bebidas",
				"CocaCola", "");
		Producto art5 = new Producto(5, "Mcallan 24 years", 100, "Bebidas",
				"Mcallan", "");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);
		misproducts.add(art3);
		misproducts.add(art4);
		misproducts.add(art5);

		/*
		 * Restricciones
		 */
		RestriccionMarca res1 = new RestriccionMarca("CocaCola", 2);
		RestriccionCategoria res2 = new RestriccionCategoria("Alimentos", 2);
		RestriccionCategoria res3 = new RestriccionCategoria("Bebidas", 2);
		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		restricciones.add(res1);
		restricciones.add(res2);
		restricciones.add(res3);

		/*
		 * Bonificaciones
		 */
		BonificacionDescuentoCategoria bon1 = new BonificacionDescuentoCategoria(
				"Alimentos", 30);
		BonificacionDescuentoCategoria bon2 = new BonificacionDescuentoCategoria(
				"Bebidas", 20);
		BonificacionDescuentoMarca bon3 = new BonificacionDescuentoMarca(
				"CocaCola", 1, 15);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bon1);
		bonificaciones.add(bon2);
		bonificaciones.add(bon3);

		/*
		 * Excepciones
		 */
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();

		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones);

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
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art5);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);

			MedioDePago med = new MedioDePago();
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

	}

	public static void prueba6() {

		/*
		 * Productos
		 */
		Producto art1 = new Producto(1, "Galletas", 100, "Alimentos",
				"Galletas", "");
		Producto art2 = new Producto(2, "Manteca", 100, "Alimentos", "Manteca",
				"");
		Producto art3 = new Producto(3, "Carne Fresca", 100, "Carnes",
				"Las lilas", "");
		Producto art4 = new Producto(4, "Coca cola 1lt", 100, "Bebidas",
				"CocaCola", "");
		Producto art5 = new Producto(5, "Mcallan 24 years", 100, "Bebidas",
				"Mcallan", "");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);
		misproducts.add(art3);
		misproducts.add(art4);
		misproducts.add(art5);

		/*
		 * Restricciones
		 */
		RestriccionMarca res1 = new RestriccionMarca("CocaCola", 2);
		RestriccionCategoria res2 = new RestriccionCategoria("Alimentos", 2);
		RestriccionCategoria res3 = new RestriccionCategoria("Bebidas", 2);
		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		restricciones.add(res1);
		restricciones.add(res2);
		restricciones.add(res3);

		/*
		 * Bonificaciones
		 */
		BonificacionDescuentoCategoria bon1 = new BonificacionDescuentoCategoria(
				"Alimentos", 30);
		BonificacionDescuentoCategoria bon2 = new BonificacionDescuentoCategoria(
				"Bebidas", 20);
		BonificacionDescuentoMarca bon3 = new BonificacionDescuentoMarca(
				"CocaCola", 1, 15);
		BonificacionDescuentoMedioDePago bon4 = new BonificacionDescuentoMedioDePago(
				25);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bon1);
		bonificaciones.add(bon2);
		bonificaciones.add(bon3);
		bonificaciones.add(bon4);

		/*
		 * Excepciones
		 */
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();

		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones, "Visa", "Galicia");

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
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art5);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art1);
			cajaprincipal.agregarProducto(art1);

			MedioDePago med = new MedioDePago("Visa", "Galicia");
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

	}

}
