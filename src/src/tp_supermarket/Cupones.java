package tp_supermarket;

import java.util.ArrayList;

import tp_supermarket.producto.Producto;

public class Cupones {
	 private static Cupones instancia ;
	 private ArrayList<Cupon> listadoCupones;

	 //CONSTRUCTOR PRIVADO
	 private Cupones() {
		 listadoCupones = new ArrayList<Cupon>();
	 }


	 //OBTENER LA INSTANCIA
	 public static Cupones getInstance() {
	  if(instancia==null) //SI ES NULL
	   instancia = new Cupones(); //LO INSTANCIAMOS
	  return instancia;
	 }
	 
	 public void setCupon(float monto){
		 this.listadoCupones.add(new Cupon(this.listadoCupones.size(),monto));
	 }


	public ArrayList<Cupon> getListadoCupones() {
		return listadoCupones;
	}


	public void setListadoCupones(ArrayList<Cupon> listadoCupones) {
		this.listadoCupones = listadoCupones;
	}

	public void mostrarListadoCupones(){
		for (int i=0; i< this.listadoCupones.size(); i++){
			
			System.out.print(" Cupon nro: ");
			System.out.print(this.listadoCupones.get(i).getNroCupon());
			System.out.print(" Monto: ");
			System.out.print(this.listadoCupones.get(i).getMonto());
			System.out.println();

			
		}
	}
	

	}
