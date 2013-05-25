import java.util.ArrayList;


public class Compra {

	private ArrayList<Producto> vectorProductos;
	
	public Compra() {	
		vectorProductos = new ArrayList<Producto>();
	}
	
	public void agregarProducto(Producto unProducto) {
		this.vectorProductos.add(unProducto);
		
	}
	
	//TODO: CONFIRMAR COMPRA
	// CLASE FACTURA o COMPRA?

	public void generarFactura(){
		
		int total = 0;
		System.out.println("\\\\\\SUPERTECNICAS//////");
		System.out.println("Ticket No valido como factura");
		
		//TODO: REEMPLAZAR POR UN ITERADOR
		for (int i=0; i< this.vectorProductos.size(); i++){
			// id me da codigo de barra o algo asi y me da el descuento
			
			System.out.print(this.vectorProductos.get(i).getId());
			System.out.print("\t");
			System.out.print(this.vectorProductos.get(i).getNombre());
			System.out.print("\t\t");
			System.out.print(this.vectorProductos.get(i).getCosto());
			System.out.println();
			
			total+=this.vectorProductos.get(i).getCosto();
			
		}
		
		System.out.println("TOTAL SIN DESCUENTO: $ "+total);
		
		System.out.println("TOTAL CON DESCUENTO ESPECIAL: $ "+total*(1-(25*Math.random())/100));
		
		System.out.println("Gracias Por su compra");
		
	}
	
}
