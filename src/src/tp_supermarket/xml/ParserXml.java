package tp_supermarket.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import tp_supermarket.bonificacion.Bonificacion;
import tp_supermarket.bonificacion.BonificacionDescuentoMarca;
import tp_supermarket.promocion.Promocion;
import tp_supermarket.restriccion.Restriccion;
import tp_supermarket.restriccion.RestriccionCantidad;
import tp_supermarket.restriccion.RestriccionMarca;
import tp_supermarket.restriccion.RestriccionNombreProducto;

import java.io.File;
import java.util.ArrayList;

public class ParserXml {


	public static ArrayList<Promocion> getPromocionesFromXml(String path) {
		ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();
		try {
		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("promocion");
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);


			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				
				NodeList nLRestriccionNP = eElement.getElementsByTagName("RestriccionNombreProducto");
				Element restriccionNP= (Element)nLRestriccionNP.item(0);
				String nombreRNPString = restriccionNP.getElementsByTagName("Nombre").item(0).getTextContent();
				String cantidadRNPString = restriccionNP.getElementsByTagName("Cantidad").item(0).getTextContent();
				NodeList nLRestriccionMarca = eElement.getElementsByTagName("RestriccionMarca");
				Element restriccionMarca = (Element)nLRestriccionMarca.item(0);
				String marcaRMString = restriccionMarca.getElementsByTagName("Marca").item(0).getTextContent();
				String cantidadRMString = restriccionMarca.getElementsByTagName("Cantidad").item(0).getTextContent();
				
				NodeList nLBonificacionMarca = eElement.getElementsByTagName("BonificacionMarca");
				Element bonificacionMarca = (Element)nLBonificacionMarca.item(0);
				String marcaBMString = bonificacionMarca.getElementsByTagName("Marca").item(0).getTextContent();
				String cantidadBMString = bonificacionMarca.getElementsByTagName("Cantidad").item(0).getTextContent();
				String porcentajeBMString = bonificacionMarca.getElementsByTagName("Porcentaje").item(0).getTextContent();
				
				RestriccionNombreProducto restNP = new RestriccionNombreProducto(nombreRNPString,new RestriccionCantidad(Integer.parseInt(cantidadRNPString)));
				RestriccionMarca restMarca = new RestriccionMarca(marcaRMString,new RestriccionCantidad(Integer.parseInt(cantidadRMString)));
				BonificacionDescuentoMarca boniMarca = new BonificacionDescuentoMarca(marcaBMString,Integer.parseInt(cantidadBMString),Float.parseFloat(porcentajeBMString));
				ArrayList<Restriccion> r = new ArrayList<Restriccion>();
				ArrayList<Bonificacion> b = new ArrayList<Bonificacion>();
				r.add(restNP);
				r.add(restMarca);
				b.add(boniMarca);
				misPromociones.add(new Promocion(r,b));
			}
		}
	} catch (Exception e) {
		System.out.println("Cagamoooo falloo el parser");
		e.printStackTrace();
		return null;
	}
		return misPromociones;
	}
	 
	public static void main(String[] args) {
		ArrayList<Promocion> promociones = ParserXml.getPromocionesFromXml("src/tp_supermarket/xml/ofertas.xml");
		for (Promocion promocion : promociones) {
			System.out.println("\n\nPromocion");
			BonificacionDescuentoMarca bonificacion = (BonificacionDescuentoMarca) promocion.getBonificaciones().get(0);
			RestriccionNombreProducto restriccionNP = (RestriccionNombreProducto) promocion.getRestricciones().get(0);
			RestriccionMarca restriccionM = (RestriccionMarca) promocion.getRestricciones().get(1);
			System.out.println("RestriccionNombreProducto:");
			System.out.println("Nombre: " + restriccionNP.getNombre());
			System.out.println("Cantidad: " + restriccionNP.getrCant().getCantidad());

			System.out.println("\nRestriccionMarca:");
			System.out.println("Marca: " + restriccionM.getMarca());
			System.out.println("Cantidad: " + restriccionM.getrCant().getCantidad());
			
			System.out.println("\nBonificacionMarca:");
			System.out.println("Marca: " + bonificacion.getMarca());
			System.out.println("Cantidad: " + bonificacion.getCant());
			System.out.println("Porcentaje: " + bonificacion.getPorcentaje());
		}
	}
}
