package tp_supermarket.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ParserXml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			File fXmlFile = new File("src/tp_supermarket/xml/ofertas.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("oferta");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					
					System.out.println("Promo id : "
							+ eElement.getAttribute("id"));
					
					NodeList nLRestriccionNP = eElement.getElementsByTagName("RestriccionNombreProducto");
					Element restriccionNP= (Element)nLRestriccionNP.item(0);
					String nombreRNPString = restriccionNP.getElementsByTagName("Nombre").item(0).getTextContent();
					String cantidadRNPString = restriccionNP.getElementsByTagName("Cantidad").item(0).getTextContent();
					System.out.println("\nRestriccionNombreProducto:");
					System.out.println("Nombre: " + nombreRNPString);
					System.out.println("Cantidad: " + cantidadRNPString);
					
					NodeList nLRestriccionMarca = eElement.getElementsByTagName("RestriccionMarca");
					Element restriccionMarca = (Element)nLRestriccionMarca.item(0);
					String marcaRMString = restriccionMarca.getElementsByTagName("Marca").item(0).getTextContent();
					String cantidadRMString = restriccionMarca.getElementsByTagName("Cantidad").item(0).getTextContent();
					System.out.println("\nRestriccionMarca:");
					System.out.println("Marca: " + marcaRMString);
					System.out.println("Cantidad: " + cantidadRMString);
					
					NodeList nLBonificacionMarca = eElement.getElementsByTagName("BonificacionMarca");
					Element bonificacionMarca = (Element)nLBonificacionMarca.item(0);
					String marcaBMString = bonificacionMarca.getElementsByTagName("Marca").item(0).getTextContent();
					String cantidadBMString = bonificacionMarca.getElementsByTagName("Cantidad").item(0).getTextContent();
					String porcentajeBMString = bonificacionMarca.getElementsByTagName("Porcentaje").item(0).getTextContent();
					System.out.println("\nBonificacionMarca:");
					System.out.println("Marca: " + marcaBMString);
					System.out.println("Cantidad: " + cantidadBMString);
					System.out.println("Porcentaje: " + porcentajeBMString);

				}
			}
		} catch (Exception e) {
			System.out.println("Cagamoooo falloo el parser");
			e.printStackTrace();
		}
	}

}
