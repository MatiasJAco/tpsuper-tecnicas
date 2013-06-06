package tp_supermarket;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Calendar;

import tp_supermarket.bonificacion.Bonificacion;
import tp_supermarket.bonificacion.BonificacionDescuentoCategoria;
import tp_supermarket.bonificacion.BonificacionDescuentoCupon;
import tp_supermarket.bonificacion.BonificacionDescuentoMarca;
import tp_supermarket.bonificacion.BonificacionDescuentoMedioDePago;
import tp_supermarket.bonificacion.BonificacionDescuentoNombre;
import tp_supermarket.caja.Caja;
import tp_supermarket.caja.ExceptionAbrirCajaConCajaAbierta;
import tp_supermarket.caja.ExceptionActualizarPromosConCajaCerrada;
import tp_supermarket.caja.ExceptionCajaCerrada;
import tp_supermarket.caja.ExceptionCompraIniciada;
import tp_supermarket.caja.ExceptionTerminarCompraConCajaCerrada;
import tp_supermarket.caja.ExceptionTerminarCompraConCompraNoIniciada;
import tp_supermarket.caja.MedioDePago;
import tp_supermarket.caja.exceptions.ExceptionCerrarCajaConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionCerrarCajaConCompraEnCurso;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCompraEnCurso;
import tp_supermarket.fecha.PeriodoValidezDiasSemana;
import tp_supermarket.fecha.PeriodoValidezEntreFechaYFecha;
import tp_supermarket.producto.Producto;
import tp_supermarket.promocion.Promocion;
import tp_supermarket.restriccion.Restriccion;
import tp_supermarket.restriccion.RestriccionCantidad;
import tp_supermarket.restriccion.RestriccionCategoria;
import tp_supermarket.restriccion.RestriccionMarca;
import tp_supermarket.restriccion.RestriccionNombreProducto;
import tp_supermarket.restriccion.RestriccionTipoCliente;
import tp_supermarket.xml.ParserXml;

public class Controlador {
	
	private final String path = "src/tp_supermarket/xml/ofertas.xml";

	private ArrayList<Producto> listadoProductos;
	private Caja cajaprincipal;
	private ArrayList<Promocion> misPromociones;
	private MedioDePago med;
	private ArrayList<MedioDePago> mediosDePagoDisponibles;
	private ArrayList<String> tipoDeCliente;
	private ArrayList<Cupon> cupones;
	

	public Controlador() {
		this.listadoProductos = new ArrayList<Producto>();
		this.misPromociones = new ArrayList<Promocion>();
		this.mediosDePagoDisponibles = new ArrayList<MedioDePago>();
		this.cajaprincipal = new Caja(5000, misPromociones);
		this.med = new MedioDePago();
		this.tipoDeCliente = new ArrayList<String>();
		this.setCupones(new ArrayList<Cupon>());
		cargarBaseDeDatosProductos();
		cargarMediosDePagoDisponibles();
		cargarTipoDeClientes();
		cargarCupones();
	}

	public void cargarBaseDeDatosProductos() {

		/*
		 * Productos
		 */
		listadoProductos.add(new Producto(1, "Galletas", 100, "Alimentos",
				"Galletas", ""));
		listadoProductos.add(new Producto(2, "Manteca", 100, "Alimentos",
				"Manteca", ""));
		listadoProductos.add(new Producto(3, "Carne Fresca", 100, "Carnes",
				"Las lilas", ""));
		listadoProductos.add(new Producto(4, "Coca cola 1,5 lts", 100, "Bebidas",
				"Coca cola 1,5 lts", ""));
		listadoProductos.add(new Producto(5, "Mcallan 24 years", 100,
				"Bebidas", "Mcallan", ""));
		listadoProductos.add(new Producto(6, "Lays", 15,
				"Alimentos", "Lays", ""));
		listadoProductos.add(new Producto(997, "CocaCola", 100, "Bebidas","CocaCola", ""));
		listadoProductos.add(new Producto(998,"Cepillos de Dientes",3,"Salud","Sin Marca", ""));
		listadoProductos.add(new Producto(999,"Maceta",10,"Jardin","Sin Marca", ""));
		listadoProductos.add(new Producto(1000,"Coca Cola 1,5 lt.",10,"Bebidas","Coca Cola", ""));
		listadoProductos.add(new Producto(1001,"Fanta 1,5 lt.",11,"Bebidas","Coca Cola", ""));
		listadoProductos.add(new Producto(1002,"Sprite 1,5 lt.",9,"Bebidas","Coca Cola", ""));
		listadoProductos.add(new Producto(1003,"Agua Mineral Bon 1,5lt",8,"Bebidas","Coca Cola", ""));
		listadoProductos.add(new Producto(1004,"Jugo Cepita 1,5 lt.",8,"Bebidas","Coca Cola", ""));
		listadoProductos.add(new Producto(1005,"Pepsi Cola 1,5 lt.",9,"Bebidas","Pepsi", ""));
		listadoProductos.add(new Producto(1006,"Seven Up 1,5 lt.",9,"Bebidas","Pepsi", ""));
		listadoProductos.add(new Producto(1007,"Leche Sancor 1 lt.",6,"Lacteos","Sancor", ""));
		listadoProductos.add(new Producto(1008,"Leche La Serenisima 1lt.",8,"Lacteos","La Serenisima", ""));
		listadoProductos.add(new Producto(1009,"Manteca Sancor 200 gr ",8,"Lacteos","Sancor", ""));
		listadoProductos.add(new Producto(1010,"Manteca La Serenisima 200 gr ",9,"Lacteos","La Serenisima", ""));
		listadoProductos.add(new Producto(1011,"Queso Pategras 500 gr Sancor",25,"Lacteos","Sancor", ""));
		listadoProductos.add(new Producto(1012,"Queso Azul 500 gr Sancor",20,"Lacteos","Sancor", ""));
		listadoProductos.add(new Producto(1013,"Queso Blanco 500 gr Sancor",18,"Lacteos","Sancor", ""));
		listadoProductos.add(new Producto(1014,"Queso Mozzarella 500 gr Sancor",30,"Lacteos","Sancor", ""));
		listadoProductos.add(new Producto(1015,"Queso Cremoso 500 gr Sancor",30,"Lacteos","Sancor", ""));
		listadoProductos.add(new Producto(1016,"Queso PortSalut 500 gr Sancor",28,"Lacteos","Sancor", ""));
		listadoProductos.add(new Producto(1017,"Queso Pategras 500 gr La Serenisima",35,"Lacteos","La Serenisima", ""));
		listadoProductos.add(new Producto(1018,"Queso Azul 500 gr La Serenisima",33,"Lacteos","La Serenisima", ""));
		listadoProductos.add(new Producto(1019,"Queso Blanco 500 gr La Serenisima",30,"Lacteos","La Serenisima", ""));
		listadoProductos.add(new Producto(1020,"Queso Mozzarella 500 gr La Serenisima",24,"Lacteos","La Serenisima", ""));
		listadoProductos.add(new Producto(1021,"Queso Cremoso 500 gr La Serenisima",50,"Lacteos","La Serenisima", ""));
		listadoProductos.add(new Producto(1022,"Queso PortSalut 500 gr La Serenisima",25,"Lacteos","La Serenisima", ""));
		listadoProductos.add(new Producto(1023,"Banana 1kg",20,"Verduleria","Sin Marca", ""));
		listadoProductos.add(new Producto(1024,"Manzana 1kg",30,"Verduleria","Sin Marca", ""));
		listadoProductos.add(new Producto(1025,"Apio 1kg",10,"Verduleria","Sin Marca", ""));
		listadoProductos.add(new Producto(1026,"Lechuga 1kg",12,"Verduleria","Sin Marca", ""));
		listadoProductos.add(new Producto(1027,"Tomate 1kg",25,"Verduleria","Sin Marca", ""));
		listadoProductos.add(new Producto(1028,"Dolares 100",870,"Varios","Sin Marca", ""));
		listadoProductos.add(new Producto(1029,"Aceitunas Verdes",15,"Almacen","Nucete", ""));
		listadoProductos.add(new Producto(1030,"Aceitunas Negras",12,"Almacen","Nucete", ""));
		listadoProductos.add(new Producto(1031,"Lays Papas",15,"Almacen","Coca Cola", ""));
		listadoProductos.add(new Producto(1032,"Palmitos",20,"Enlatados","La Banda", ""));
		listadoProductos.add(new Producto(1033,"Sardinas",50,"Enlatados","La Campanola", ""));
		listadoProductos.add(new Producto(1034,"Atun",40,"Enlatados","La Campanola", ""));
		listadoProductos.add(new Producto(1035,"Arvejas",10,"Enlatados","La Campanola", ""));
		listadoProductos.add(new Producto(1036,"Galletitas Oreo",10,"Panaderia","Arcor", ""));
		listadoProductos.add(new Producto(1037,"Galletitas Pepitos",5,"Panaderia","Arcor", ""));
		listadoProductos.add(new Producto(1038,"Pan 1 Kg",10,"Panaderia","Sin Marca", ""));
		listadoProductos.add(new Producto(1039,"Aceite Girasol 1lt",25,"Almacen","Cocinero", ""));
		listadoProductos.add(new Producto(1040,"Aceite Oliva 1 lt",40,"Almacen","Cocinero", ""));
		listadoProductos.add(new Producto(1041,"Vino Tinto Don Alfredo",40,"Vineria","Don Alfredo", ""));
		listadoProductos.add(new Producto(1042,"Vino Tinto EL Chaquenio",12,"Vineria","El Chaquenio", ""));
		listadoProductos.add(new Producto(1043,"Vino Tinto Patero",10,"Vineria","Patero", ""));
		listadoProductos.add(new Producto(1044,"Vino Tinto UXMAL",40,"Vineria","Uxmal", ""));
		listadoProductos.add(new Producto(1045,"Vino Tinto Valmont",30,"Vineria","Chandon", ""));
		listadoProductos.add(new Producto(1046,"Vino Tinto Trapiche",55,"Vineria","Trapiche", ""));
		listadoProductos.add(new Producto(1047,"Vino Tinto Don Bosco",69,"Vineria","Don Bosco", ""));
		listadoProductos.add(new Producto(1048,"Vino Tinto Trumpeter",80,"Vineria","Las Rosas", ""));
		listadoProductos.add(new Producto(1049,"Vino Tinto Especial",100,"Vineria","Chandon", ""));
		listadoProductos.add(new Producto(1050,"Espumante Chandon",85,"Vineria","Chandon", ""));
		listadoProductos.add(new Producto(1051,"Espumante Generico",5,"Vineria","Generico", ""));
	}

	public void cargarMediosDePagoDisponibles(){
		this.mediosDePagoDisponibles.add(new MedioDePago("Efectivo", ""));
		this.mediosDePagoDisponibles.add(	new MedioDePago("Visa", "Galicia"));
		this.mediosDePagoDisponibles.add(	new MedioDePago("XYZ", ""));
		this.mediosDePagoDisponibles.add(	new MedioDePago("Visa", "Banco Provincia"));
		this.mediosDePagoDisponibles.add(	new MedioDePago("Visa", "Banco HSBC"));
		this.mediosDePagoDisponibles.add(	new MedioDePago("Visa", "Banco Itau"));
		this.mediosDePagoDisponibles.add(	new MedioDePago("Visa", "Banco Patagonia"));
		this.mediosDePagoDisponibles.add(	new MedioDePago("Visa", "Banco ICBS"));
		this.mediosDePagoDisponibles.add(	new MedioDePago("Visa", "Banco Piano"));
		this.mediosDePagoDisponibles.add(	new MedioDePago("Visa", "Banco Nacion"));
		this.mediosDePagoDisponibles.add(	new MedioDePago("Vale Super", ""));
		
	}
	
	public void cargarTipoDeClientes(){
		this.tipoDeCliente.add("Normal");
		this.tipoDeCliente.add("Estudiante");
		this.tipoDeCliente.add("Jubilados");
		this.tipoDeCliente.add("Cocinero");
		this.tipoDeCliente.add("Profesional");
	}
	
	
	public void cargarCupones(){
		Cupones cupon= Cupones.getInstance();
		ArrayList<Cupon> cupones = cupon.getListadoCupones();
		for (int i=0; i<cupones.size();i++){
			
		}
		
	}
	
	public ArrayList<Producto> listadoProductos() {
		return this.listadoProductos;
	}

	public void cargarPromocionesYBonificaciones() {	
		ArrayList<Promocion> misPromociones = ParserXml.getPromocionesFromXml(this.path);
		
		
		ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
		ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
		ArrayList<RestriccionTipoCliente> cli = new ArrayList<RestriccionTipoCliente>();
		cli.add(new RestriccionTipoCliente("Jubilados", 10.0f));
		
		ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();
		BonificacionDescuentoCupon bDescuento = new BonificacionDescuentoCupon(20,10,1,2,"Coca Cola");
		bonificaciones.add(bDescuento);
		
		Promocion miPromo = new Promocion(restricciones, excepciones, bonificaciones);
		misPromociones.add(miPromo);
		
		Promocion promoJubilados = new Promocion(restricciones, bonificaciones);
		promoJubilados.setTiposClientesAplicanPromo(cli);
		//misPromociones.add(promoJubilados);
		for (int i=0; i<misPromociones.size();i++){
//		
//
//		BonificacionDescuentoMedioDePago bonJub = new BonificacionDescuentoMedioDePago(10);
			misPromociones.get(i).setTiposClientesAplicanPromo(cli);
//		misPromociones.get(i).agregarMedioDePago(new MedioDePago("Descuento Jubilados 10%",""));
//			
//			
	}
		try {
			cajaprincipal.setPromociones(misPromociones);
			System.out.println("Promociones y Bonificaciones Actualizadas");
		} catch (ExceptionActualizarPromosConCajaCerrada e) {
			System.out.println("ERROR: No se puede actualizar: Caja CERRADA");
		}		
//		try {
//			/*
//			 * Restricciones DIA
//			 */
//			ArrayList<Integer> diasPromo = new ArrayList<Integer>();
//			diasPromo.add(Calendar.THURSDAY);
//			PeriodoValidezDiasSemana promodiaJueves = new PeriodoValidezDiasSemana(
//					diasPromo);
//
//			/*
//			 * Restricciones
//			 */
//			RestriccionNombreProducto res1 = new RestriccionNombreProducto(
//					"Coca cola 1lt", 2);
//			ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
//			restricciones.add(res1);
//			// restricciones.add(res2);
//			// restricciones.add(res3);
//
//			/*
//			 * Definicion de los medios de pagos para la promocion
//			 */
//			ArrayList<MedioDePago> mediosDePagosPromo = new ArrayList<MedioDePago>();
//			MedioDePago medioDePago1 = new MedioDePago("Visa", "Galicia");
//			mediosDePagosPromo.add(medioDePago1);
//
//			/*
//			 * Bonificaciones
//			 */
//			BonificacionDescuentoNombre bon1 = new BonificacionDescuentoNombre(
//					"Coca cola 1lt", 1, 100);
//			BonificacionDescuentoMedioDePago bon2 = new BonificacionDescuentoMedioDePago(
//					10);
//			ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();
//			bonificaciones.add(bon1);
//			bonificaciones.add(bon2);
//			// bonificaciones.add(bon3);
//			// bonificaciones.add(bon4);
//
//			/*
//			 * Excepciones
//			 */
//			ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();
//
//			/*
//			 * Nueva promo
//			 */
//			Promocion promo1 = new Promocion(restricciones, excepciones,
//					bonificaciones, mediosDePagosPromo);
//			promo1.setPeriodoValidezPromocion(promodiaJueves);
//
//			ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();
//
//			misPromociones.add(promo1);
//			cajaprincipal.setPromociones(misPromociones);
//
//			System.out.println("Promociones y Bonificaciones Actualizadas");
//		} catch (ExceptionActualizarPromosConCajaCerrada e) {
//			System.out.println("ERROR: No se puede actualizar: Caja CERRADA");
//		}
	}

	public void abrirCaja() {

		try {
			cajaprincipal.abrirCaja();

			System.out.println("Caja: " + cajaprincipal.getIdentificacionCaja()
					+ " ahora esta ABIERTA");
			System.out
					.println("Pulse Iniciar Compra para comenzar una nueva compra ");

		} catch (ExceptionAbrirCajaConCajaAbierta e) {
			System.out.println("ERROR: La caja ya esta abierta!");
		}

	}

	public void agregarProducto(Producto unProducto) {
		if (!cajaprincipal.isEstadoCaja()) {
			if (cajaprincipal.isCompraEnCurso()) {
				cajaprincipal.agregarProducto(unProducto);
			}
		}
	}

	public void setMedioPago(String banco, String medio) {
		this.med = new MedioDePago(medio, banco);

	}

	public boolean terminarCompra() {
		boolean ret = false;
		try {
			cajaprincipal.terminarCompraActual(med);
			ret = true;

		} catch (ExceptionTerminarCompraConCajaCerrada e) {
			System.out
					.println("ERROR:No se puede terminar compra: caja CERRADA");
		} catch (ExceptionTerminarCompraConCompraNoIniciada e) {
			System.out.println("ERROR:No hay una compra iniciada!");
		}
		return ret;

	}

	public int getNombreCaja() {

		return this.cajaprincipal.getIdentificacionCaja();
	}

	public void getTotalMedioPagoPorCaja() {
		try {
			this.cajaprincipal.imprimirTotalMedioDePago();
		} catch (ExceptionCajaCerrada e) {
			System.out.println("ERROR:No se puede mostrar: caja CERRADA");
		} catch (ExceptionCompraIniciada e) {
			System.out.println("ERROR:No se puede mostrar: compra en curso");
		}

	}

	public void getTotalSinDescuento() {
		try {
			this.cajaprincipal.imprimirTotalSinDescuento();
		} catch (ExceptionCajaCerrada e) {
			System.out.println("ERROR:No se puede mostrar: caja CERRADA");
		} catch (ExceptionCompraIniciada e) {
			System.out.println("ERROR:No se puede mostrar: compra en curso");
		}
	}

	public void getTotalDescuentos() {
		try {
			this.cajaprincipal.imprimirTotalDescuentos();
		} catch (ExceptionCajaCerrada e) {
			System.out.println("ERROR:No se puede mostrar: caja CERRADA");
		} catch (ExceptionCompraIniciada e) {
			System.out.println("ERROR:No se puede mostrar: compra en curso");
		}
	}

	public void mostrarRank() {
	this.cajaprincipal.mostrarrankingProductos();
	}

	public void iniciarCompra() {
		// TODO Auto-generated method stub
		try {
			cajaprincipal.iniciarCompra();
			System.out
					.println("Nueva Compra en curso, seleccione los productos de la lista");
			System.out.println("Doble click para agregar producto");

		} catch (ExceptionIniciarCompraConCajaCerrada e) {
			System.out
					.println("ERROR:No se puede iniciar compra: caja CERRADA");
		} catch (ExceptionIniciarCompraConCompraEnCurso e) {
			System.out.println("ERROR:Ya hay una compra en curso!");
		}

	}

	public boolean cerrarCaja() {
		boolean ret = false;
		try {
			cajaprincipal.cerrarCaja();
			ret = true;
		} catch (ExceptionCerrarCajaConCompraEnCurso e) {
			System.out.println("ERROR:No se puede cerrar: compra en curso");
		} catch (ExceptionCerrarCajaConCajaCerrada e) {
			System.out.println("ERROR:No se puede cerrar: caja CERRADA");
		}
		return ret;
	}

	public ArrayList<MedioDePago> getMediosDePagosDisponibles(){
		return this.mediosDePagoDisponibles;
	}
	
	public  ArrayList<String> getTiposDeClientes(){
		return this.tipoDeCliente;
	}
	
	public void verRanking(){
		cajaprincipal.mostrarrankingProductos();
	}
	
	public void setTipoCliente(String tipoCliente){
		if (!this.cajaprincipal.isEstadoCaja()){
			cajaprincipal.getCompraActual().setTipoCliente(tipoCliente);
		}
		
	}

	public ArrayList<Cupon> getCupones() {
		return cupones;
	}

	public void setCupones(ArrayList<Cupon> cupones) {
		this.cupones = cupones;
	}

	public void setCupon(String valor) {
//		//TODO:Implementar
//	cajaprincipal.getCompraActual().setCupon(valor);
	}
	
}
