
public class main {

	/**
	 * @param args
	 * @throws iniciarCompraConCompraEnCurso 
	 * @throws iniciarCompraConCajaCerrada 
	 * @throws exepcionCajaCerradaoCompraEnCurso 
	 */
	public static void main(String[] args) throws iniciarCompraConCajaCerrada, iniciarCompraConCompraEnCurso{

		Producto art1 = new Producto(1,"Galletas",100);
		Producto art2 = new Producto(2,"Manteca",100);
		Producto art3 = new Producto(3,"Carne Fresca",100);
		Producto art4 = new Producto(4,"Coca cola 1lt",100);
		Producto art5 = new Producto(5,"Mcallan 24 years",100);
		
		Caja cajaprincipal = new Caja(1234);
		cajaprincipal.abrirCaja();
		cajaprincipal.iniciarCompra();
		
		cajaprincipal.agregarProducto(art1);
		cajaprincipal.agregarProducto(art2);
		cajaprincipal.agregarProducto(art3);
		cajaprincipal.agregarProducto(art4);
		cajaprincipal.agregarProducto(art5);
		cajaprincipal.agregarProducto(art1);
		cajaprincipal.agregarProducto(art1);
		cajaprincipal.agregarProducto(art1);
		
		cajaprincipal.terminarCompraActual();
	
		
	}

}
