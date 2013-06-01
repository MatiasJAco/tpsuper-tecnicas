package tp_supermarket;

import java.util.ArrayList;
import java.util.Calendar;

import tp_supermarket.restriccion.*;
import tp_supermarket.producto.*;
import tp_supermarket.promocion.*;
import tp_supermarket.bonificacion.*;
import tp_supermarket.caja.Caja;
import tp_supermarket.caja.ExceptionAbrirCajaConCajaAbierta;
import tp_supermarket.caja.ExceptionTerminarCompraConCajaCerrada;
import tp_supermarket.caja.ExceptionTerminarCompraConCompraNoIniciada;
import tp_supermarket.caja.MedioDePago;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCompraEnCurso;
import tp_supermarket.fecha.PeriodoValidez;
import tp_supermarket.fecha.PeriodoValidezDiasSemana;

public class Main {

	/**
	 * @param args
	 * @throws ExceptionIniciarCompraConCompraEnCurso
	 * @throws ExceptionIniciarCompraConCajaCerrada
	 * @throws ExceptionTerminarCompraConCompraNoIniciada 
	 * @throws ExceptionTerminarCompraConCajaCerrada 
	 * @throws ExceptionAbrirCajaConCajaAbierta 
	 * @throws exepcionCajaCerradaoCompraEnCurso
	 */
	public static void main(String[] args)
			throws ExceptionIniciarCompraConCajaCerrada,
			ExceptionIniciarCompraConCompraEnCurso, ExceptionAbrirCajaConCajaAbierta, ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada {

//		System.out.println("-----------------------------------");
//		System.out.println("PRUEBA1");
//		System.out.println("-----------------------------------");
//		System.out.println("-----------------------------------");
//		System.out.println();
//		System.out.println("-----------------------------------");
//		System.out.println("PRUEBA2");
//		System.out.println("-----------------------------------");
//		System.out.println("-----------------------------------");
//		System.out.println();
//		System.out.println("-----------------------------------");
//		System.out.println("PRUEBA3");
//		System.out.println("-----------------------------------");
//		System.out.println("-----------------------------------");
//		System.out.println();
//		System.out.println("-----------------------------------");
//		System.out.println("PRUEBA4");
//		System.out.println("-----------------------------------");
//		prueba4();
//		System.out.println("-----------------------------------");
//		System.out.println();
//		System.out.println("-----------------------------------");
//		System.out.println("PRUEBA5");
//		System.out.println("-----------------------------------");
//		prueba5();
//		System.out.println("-----------------------------------");
//		System.out.println();
//		System.out.println("-----------------------------------");
//		System.out.println("PRUEBA6");
//		System.out.println("-----------------------------------");
//		prueba6();
//		System.out.println("-----------------------------------");
//		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("PRUEBA1");
		System.out.println("-----------------------------------");
		prueba7();
		System.out.println("-----------------------------------");
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("PRUEBA2");
		System.out.println("-----------------------------------");
		prueba8();
		System.out.println("-----------------------------------");
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("PRUEBA3");
		System.out.println("-----------------------------------");
		prueba9();
		System.out.println("-----------------------------------");
		System.out.println();
	}

	public static void prueba1() throws ExceptionAbrirCajaConCajaAbierta, ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada {

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

	public static void prueba2() throws ExceptionAbrirCajaConCajaAbierta, ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada {

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

	public static void prueba3() throws ExceptionAbrirCajaConCajaAbierta, ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada {

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

	public static void prueba4() throws ExceptionAbrirCajaConCajaAbierta, ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada {

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

	public static void prueba5() throws ExceptionAbrirCajaConCajaAbierta, ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada {

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

	public static void prueba6() throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada, ExceptionAbrirCajaConCajaAbierta {

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

		/*
		 * Nueva promo
		 */
		ArrayList<MedioDePago> mDePagos = new ArrayList<MedioDePago>();
		mDePagos.add(new MedioDePago("Visa","Galicia"));
		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones, mDePagos);

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
	
	public static void prueba7() throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada, ExceptionAbrirCajaConCajaAbierta {

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
		BonificacionDescuentoMedioDePago bDescuento = new BonificacionDescuentoMedioDePago(
				10);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bon1);
		bonificaciones.add(bDescuento);
		/*
		 * Excepciones
		 */
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();

		/*
		 * Nueva promo
		 */
		ArrayList<MedioDePago> mDePagos = new ArrayList<MedioDePago>();
		mDePagos.add(new MedioDePago("XYZ",""));
		/*
		 * Bonificacion por medio de pago
		 */
		
		Promocion promo1 = new Promocion(restricciones, excepciones,
				bonificaciones, mDePagos);
		/*
		 * Seteo de la fecha de vigencia de la promocion
		 */
		ArrayList<Integer> diasPromo = new ArrayList<Integer>();		
		diasPromo.add(Calendar.THURSDAY);
		PeriodoValidez pValidez = new PeriodoValidezDiasSemana(diasPromo);
		promo1.setPeriodoValidezPromocion(pValidez);

		ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();
		;
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

			MedioDePago med = new MedioDePago("XYZ", "");
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

	}

	public static void prueba8() throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada, ExceptionAbrirCajaConCajaAbierta {

		/*
		 * Productos
		 */
		Producto art1 = new Producto(1, "CocaCola", 1, "Bebidas",
				"CocaCola", "");
		Producto art2 = new Producto(2, "Maceta", 10, "Jardineria", "Maceta",
				"");
		Producto art3 = new Producto(3, "Cepillos de Dientes", 3, "Salud",
				"Cepi", "");
		Producto art4 = new Producto(4, "Vino X", 100, "Vinoteca",
				"X", "");
		Producto art5 = new Producto(5, "Chandon", 75, "Vinoteca",
				"Chandon", "");
		ArrayList<Producto> misproducts = new ArrayList<Producto>();
		misproducts.add(art1);
		misproducts.add(art2);
		misproducts.add(art3);
		misproducts.add(art4);
		misproducts.add(art5);

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
		BonificacionDescuentoMedioDePago bDescuento = new BonificacionDescuentoMedioDePago(
				10);
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		bonificaciones.add(bon1);
		bonificaciones.add(bDescuento);
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
				bonificaciones, mDePagos);
		/*
		 * Seteo de la fecha de vigencia de la promocion
		 */
		ArrayList<Integer> diasPromo = new ArrayList<Integer>();		
		diasPromo.add(Calendar.MONDAY);
		PeriodoValidez pValidez = new PeriodoValidezDiasSemana(diasPromo);
		promo1.setPeriodoValidezPromocion(pValidez);

		ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();
		;
		misPromociones.add(promo1);
		// Caja cajaprincipal = new Caja(1234);

		Caja cajaprincipal = new Caja(1234, misPromociones);
		cajaprincipal.abrirCaja();
		try {
			cajaprincipal.iniciarCompra();

			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art4);
			cajaprincipal.agregarProducto(art5);
			cajaprincipal.agregarProducto(art5);
			cajaprincipal.agregarProducto(art3);
			cajaprincipal.agregarProducto(art2);

			MedioDePago med = new MedioDePago("Tarjeta de Debito", "");
			cajaprincipal.terminarCompraActual(med);

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
		}

	}
	
	public static void prueba9() throws ExceptionTerminarCompraConCajaCerrada, ExceptionTerminarCompraConCompraNoIniciada, ExceptionAbrirCajaConCajaAbierta {

		/*
		 * Productos
		 */
		Producto art1 = new Producto(1, "ELL", 10, "Vinoteca",
				"ELL", "");
		Producto art2 = new Producto(2, "XXZ", 20, "Vinoteca",
				"XXZ", "");
		Producto art3 = new Producto(3, "LLL", 20, "Vinoteca",
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

	}
	
	
}
