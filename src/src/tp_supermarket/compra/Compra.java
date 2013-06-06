package tp_supermarket.compra;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tp_supermarket.caja.MedioDePago;
import tp_supermarket.producto.Producto;
import tp_supermarket.promocion.Promocion;
import tp_supermarket.restriccion.Restriccion;
import tp_supermarket.restriccion.RestriccionTipoCliente;

public class Compra {

	private MedioDePago medioDePago;
	private final ArrayList<Producto> productos;
	private final ArrayList<Producto> productosAplicanPromo;
	private float totalSD;
	private float totalCD;
	private float totalDesc;
	private int nroCompra;
	private int caja;
	private Date fechayhora;
	private String tipoCliente = "ALL";

	public Compra() {
		this.totalSD = 0;
		this.totalCD = 0;
		productos = new ArrayList<Producto>();
		productosAplicanPromo = new ArrayList<Producto>();
		this.medioDePago = new MedioDePago("Efectivo", "");
	}

	public Compra(String medP, String entidad) {
		this.totalSD = 0;
		this.totalCD = 0;
		productos = new ArrayList<Producto>();
		productosAplicanPromo = new ArrayList<Producto>();
		this.medioDePago = new MedioDePago(medP, entidad);
	}

	public void agregarProducto(Producto unProducto) {
		this.productos.add(unProducto);

	}

	// TODO: CONFIRMAR COMPRA
	// CLASE FACTURA o COMPRA?

	public void generarFactura() {

		float total = 0;
		float totalSinDescuento = 0;
		DecimalFormat df = new DecimalFormat("#.##");

		System.out.println("");
		System.out.println("#############SUPER TECNICAS#############");
		System.out.println("");
		System.out.println("Av. Paseo Colon 850 - 4Piso");
		System.out.println("C1063ACV - Buenos Aires - Argentina");
		System.out.println("Tel +54 (11) 4343-0893");
		System.out.println("");
		System.out.println("Ticket No valido como factura");
		System.out.println("");

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.fechayhora = date;
		System.out.println("Caja nro:" + this.caja);
		System.out.print("Fecha: " + dateFormat.format(this.fechayhora));
		System.out.println("");
		System.out.println("Nro compra:" + this.nroCompra);
		System.out.println("");
		System.out.println("########################################");
		System.out.println("Productos Comprados");
		System.out.println("########################################");
		
		//TODO: REEMPLAZAR POR UN ITERADOR
		for (int i=0; i< this.productos.size(); i++){
			// id me da codigo de barra o algo asi y me da el descuento
			System.out.printf("%1$-2d %2$-30s $%3$-10.2f\n",this.productos.get(i).getId(),this.productos.get(i).getNombre(),this.productos.get(i).getCosto());
			total+=this.productos.get(i).getCosto();
			
		}
			
		totalSinDescuento = total;

		System.out.println("");
		System.out.println("########################################");
		System.out.println("Descuentos aplicados");
		System.out.println("########################################");
		
		for (int i=0; i< this.productosAplicanPromo.size(); i++){
			
			System.out.print(this.productosAplicanPromo.get(i).getNombre());
			System.out.print("\t\t");
			System.out.print(this.productosAplicanPromo.get(i).getCosto());
			System.out.println();
			
			total+=this.productosAplicanPromo.get(i).getCosto();
			
		}
		
		this.totalSD=totalSinDescuento;
//		this.totalCD=total;
		this.totalDesc=(totalSinDescuento-total);
		System.out.println("");
		System.out.println("TOTAL SIN DESCUENTO: $ " + this.getTotalSD());
		System.out.println("TOTAL CON DESCUENTO: $ " + this.getTotalCD());
		System.out.println("USTED AHORRO UN "
				+ df.format(100 - (this.getTotalCD() / totalSinDescuento) * 100)
				+ "% EN SU COMPRA");

		// System.out.println("TOTAL CON DESCUENTO ESPECIAL: $ "+total*(1-(25*Math.random())/100));
		System.out.println("");
		System.out.println("########################################");
		System.out.println("Orientacion Cons. B.A. 0800-222-9042");
		System.out.println("CF");
		System.out.println("DGI");
		System.out.println("########################################");
		System.out.println("         Gracias Por su compra");

	}

	public void aplicarPromociones(ArrayList<Promocion> promociones) {
		promociones = validarPromociones(promociones);
		for (int i = 0; i < promociones.size(); i++) {
			if (promociones.get(i).getVecesActivada() > 0) {
				ArrayList<Producto> descuentos = promociones.get(i)
						.aplicarBonificaciones(this.productos);
				for (int j = 0; j < descuentos.size(); j++) {
					this.productosAplicanPromo.add(descuentos.get(j));
				}
			}
		}
		//Se aplican las bonificaciones por tipo de cliente
		for (int i = 0; i < promociones.size(); i++) {
			if(promociones.get(i).isActiva()){
				this.totalCD = promociones.get(i).aplicarBonificacionPorTipoCliente(this.calcularTotalCD());
				return;
			}
		}
	}

	private ArrayList<Promocion> validarPromociones(
			ArrayList<Promocion> promociones) {
		for (int i = 0; i < promociones.size(); i++) {
			if (promociones.get(i).validarMedioPago(this.medioDePago) &&
					promociones.get(i).isClienteValido(tipoCliente)) {
				// promociones.get(i).checkProductos(this.vectorProductos);
				for (int j = 0; j < this.productos.size(); j++) {
					// this.productos.get(j).setMedioDePago(this.medioDePago);
					promociones.get(i).checkProducto(this.productos.get(j));
				}
			}
		}

		return promociones;
	}

	public MedioDePago getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}

	public float getTotalSD() {
		return totalSD;
	}

	public void setTotalSD(float totalSD) {
		this.totalSD = totalSD;
	}

	public float getTotalCD() {
		return totalCD;
	}

	public void setTotalCD(float totalCD) {
		this.totalCD = totalCD;
	}

	public float getTotalDesc() {
		return totalDesc;
	}

	public void setTotalDesc(float totalDesc) {
		this.totalDesc = totalDesc;
	}

	public int getNroCompra() {
		return nroCompra;
	}

	public void setNroCompra(int nroCompra) {
		this.nroCompra = nroCompra;
	}

	public int getCaja() {
		return caja;
	}

	public void setCaja(int caja) {
		this.caja = caja;
	}

	public ArrayList<Producto> verProductos() {
		return this.productos;
	}

	public void setTipoCliente(String tCli){
		this.tipoCliente = tCli;
	}
	
	public void descuentoMedioDePago() {
		if (this.medioDePago.getpValidez() != null && this.medioDePago.getpValidez().cumplePeriodoValidez()) {
			float precioTotal = 0;
			for (int i = 0; i < this.productos.size(); i++) {
				precioTotal += this.productos.get(i).getCosto();
			}
			for (int i = 0; i < this.productosAplicanPromo.size(); i++) {
				precioTotal += this.productosAplicanPromo.get(i).getCosto();
			}

			float precioConDescuento = (precioTotal
					* this.medioDePago.getDescuento() / 100)
					* -1;

			Producto descuentoMDP = new Producto(0, "Descuento de "
					+ this.medioDePago.getDescuento()
					+ " % con su medio de pago", precioConDescuento);
			this.productosAplicanPromo.add(descuentoMDP);
			this.totalCD = this.calcularTotalCD();
		}
	}
	
	private float calcularTotalCD(){
		float total = 0;
		for (int i = 0; i < this.productos.size(); i++) {
			total += this.productos.get(i).getCosto();
		}
		for (int i = 0; i < this.productosAplicanPromo.size(); i++) {
			total += this.productosAplicanPromo.get(i).getCosto();

		}
		return total;
	}
	
}
