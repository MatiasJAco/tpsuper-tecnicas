package tp_supermarket.bonificacion;

import java.util.ArrayList;

import tp_supermarket.producto.Producto;
import tp_supermarket.restriccion.Restriccion;

public class BonificacionDescuentoCupon extends Bonificacion {

	private final float porcentaje;
	private boolean aplicada;
	private float valor;
	
	private String marca;
	private int numerador;
	private int denominador;
	
	 
	
	public BonificacionDescuentoCupon(float p) {
		this.porcentaje = p;
		this.aplicada = false;
		
	}
	
	public BonificacionDescuentoCupon(float p, float v, int nu,int deno, String m) {
		this.porcentaje = p;
		this.numerador=nu;
		this.denominador=deno;
		this.aplicada = false;
		this.valor = v;
		this.marca=m;
		
	}

	@Override
	public ArrayList<Producto> bonificar(ArrayList<Producto> misproducts,
			ArrayList<Restriccion> excepciones) {
		ArrayList<Producto> descuentos = new ArrayList<Producto>();
		Producto nuevoDescuento;
		float result = 0;
		float total = 0;
		float totalAcumulado=0;
		float acum=0;
		int cont = 0;
		if (!aplicada) {
			for (int i = 0; i < misproducts.size(); i++) {
				if (!isInExcepciones(excepciones, misproducts.get(i))) {
					
					if (misproducts.get(i).getMarca().equals(marca)){
						cont++;
						result = misproducts.get(i).getCosto();
						if ( cont <= this.denominador){						
						acum +=result;
						}else
							cont=0;
						totalAcumulado+=result;					
					
					}
				}
			}
			float descuento;
			float acumCompra = acum  /totalAcumulado;
			
			if( acumCompra > (this.porcentaje/100)){
				descuento= totalAcumulado*(this.porcentaje/100); 
			}else{	
				descuento=totalAcumulado - acum;						
			}
			if (descuento > valor){
				descuento= valor;
			};
			total += (descuento * -1);
			aplicada = true;
			nuevoDescuento = new Producto(0, "Descuento de "
					+ porcentaje + " % con su cupon", total);
			descuentos.add(nuevoDescuento);
		}
		return descuentos;
	}

}
