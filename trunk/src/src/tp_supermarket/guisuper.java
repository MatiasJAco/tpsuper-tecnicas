package tp_supermarket;

import java.awt.EventQueue;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridLayout;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;

import tp_supermarket.bonificacion.Bonificacion;
import tp_supermarket.bonificacion.BonificacionDescuentoMarca;
import tp_supermarket.caja.Caja;
import tp_supermarket.caja.MedioDePago;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCompraEnCurso;
import tp_supermarket.producto.Producto;
import tp_supermarket.promocion.Promocion;
import tp_supermarket.restriccion.Restriccion;
import tp_supermarket.restriccion.RestriccionMarca;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollBar;
import java.awt.SystemColor;


import tp_supermarket.restriccion.*;
import tp_supermarket.producto.*;
import tp_supermarket.promocion.*;
import tp_supermarket.bonificacion.*;
import java.awt.Toolkit;


public class guisuper {

	private JFrame frmSuperTecnicasGui;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guisuper window = new guisuper();
					window.frmSuperTecnicasGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ExceptionIniciarCompraConCompraEnCurso 
	 * @throws ExceptionIniciarCompraConCajaCerrada 
	 */
	public guisuper() throws ExceptionIniciarCompraConCajaCerrada, ExceptionIniciarCompraConCompraEnCurso {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ExceptionIniciarCompraConCompraEnCurso 
	 * @throws ExceptionIniciarCompraConCajaCerrada 
	 */
	private void initialize() throws ExceptionIniciarCompraConCajaCerrada, ExceptionIniciarCompraConCompraEnCurso {
		frmSuperTecnicasGui = new JFrame();
		//frmSuperTecnicasGui.setIconImage(Toolkit.getDefaultToolkit().getImage(guisuper.class.getResource("src/tp_supermarket/shop.ico")));
		frmSuperTecnicasGui.setIconImage(new ImageIcon("src/tp_supermarket/recursos/shop.png").getImage()); 
		frmSuperTecnicasGui.getContentPane().setBackground(SystemColor.activeCaption);
		frmSuperTecnicasGui.setTitle("Super Tecnicas");
		frmSuperTecnicasGui.setBounds(100, 100, 753, 516);
		frmSuperTecnicasGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSuperTecnicasGui.getContentPane().setLayout(null);
		
		//Medio de pago
		final MedioDePago med = new MedioDePago();
		
		//PRODUCTOS
	     final Producto art1 = new Producto(1,"Galletas",100);
	     final Producto art2 = new Producto(2,"Manteca",100);
	     final Producto art3 = new Producto(3,"Carne Fresca",100);
	     final Producto art4 = new Producto(4,"Coca cola 1lt",100);
	     final Producto art5 = new Producto(5,"Mcallan 24 years",100);

	     RestriccionMarca res1 = new RestriccionMarca("Coca cola 1lt", 1);
	     RestriccionMarca res2 = new RestriccionMarca("Galletas", 2);
	     BonificacionDescuentoMarca bon1 = new BonificacionDescuentoMarca("Galletas", 2, 30);
				
						
	     ArrayList<Promocion> misPromociones = new ArrayList<Promocion>();	
	     Promocion promo1 = new Promocion();
	     promo1.addRestriccion(res1);		
	     promo1.addRestriccion(res2);
	     promo1.addBonificacion(bon1);
	     misPromociones.add(promo1);

			/*
			 * Excepciones
			 */
	     ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
			ArrayList<Restriccion> excepciones = new ArrayList<Restriccion>();
					ArrayList<Bonificacion> bonificaciones = new ArrayList<Bonificacion>();;

			Promocion promo2 = new Promocion(restricciones, excepciones,
					bonificaciones, "Visa", "Galicia");


			misPromociones.add(promo2);
	     
	    //CREO CAJA 
		final Caja cajaprincipal = new Caja(1234,misPromociones);
		
		
		//ABRO CAJA
		JButton btnNewButton = new JButton("Abrir Cajar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cajaprincipal.abrirCaja();
				
				System.out.println("Caja: "+cajaprincipal.getIdentificacionCaja()+" ahora esta ABIERTA");
				
				System.out.println("Pulse Iniciar Compra para comenzar una nueva compra ");
				
				
			}
		});
		btnNewButton.setBounds(10, 11, 169, 23);
		frmSuperTecnicasGui.getContentPane().add(btnNewButton);
		
		//INICIO COMPRA
		JButton btnNewButton_1 = new JButton("Iniciar Compra");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cajaprincipal.iniciarCompra();
				} catch (ExceptionIniciarCompraConCajaCerrada e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExceptionIniciarCompraConCompraEnCurso e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Nueva Compra en curso, seleccion los productos de la lista");
				System.out.println("Doble click para agregar producto");
			}
		});
		btnNewButton_1.setBounds(10, 44, 169, 23);
		frmSuperTecnicasGui.getContentPane().add(btnNewButton_1);
		
		//TERMINO COMPRA
		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cajaprincipal.terminarCompraActual(med);
				  try
		           {
		               Clip clip = AudioSystem.getClip();
		            
		               clip.open(AudioSystem.getAudioInputStream(new File("src/tp_supermarket/recursos/chaching.wav")));
		               clip.start();
		           }
		           catch (Exception exc)
		           {
		               exc.printStackTrace(System.out);
		           }
				  try
		           {
		               Clip clipw = AudioSystem.getClip();
		            
		               clipw.open(AudioSystem.getAudioInputStream(new File("src/tp_supermarket/recursos/printer.wav")));
		               clipw.start();
		           }
		           catch (Exception exc)
		           {
		               exc.printStackTrace(System.out);
		           }
			}
		});
		btnFinalizarCompra.setBounds(598, 432, 123, 23);
		frmSuperTecnicasGui.getContentPane().add(btnFinalizarCompra);
		
		JLabel lblSeleccionarMedioDe = new JLabel("Seleccionar Medio de Pago");
		lblSeleccionarMedioDe.setBounds(346, 374, 159, 14);
		frmSuperTecnicasGui.getContentPane().add(lblSeleccionarMedioDe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(346, 8, 375, 355);
		frmSuperTecnicasGui.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		
		final DefaultListModel model = new DefaultListModel();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 84, 159, 207);
		frmSuperTecnicasGui.getContentPane().add(scrollPane_1);
		final JList list = new JList(model);
		scrollPane_1.setViewportView(list);
		
		
		
		model.addElement(art1);
		model.addElement(art2);
		model.addElement(art3);
		model.addElement(art4);
		
		
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 2) {


		           Producto selectedItem = (Producto) list.getSelectedValue();
		           // add selectedItem to your second list.
		           cajaprincipal.agregarProducto(selectedItem);
		           
		           
		           try
		           {
		               Clip clip = AudioSystem.getClip();
		            
		               clip.open(AudioSystem.getAudioInputStream(new File("src/tp_supermarket/recursos/beep.wav")));
		               clip.start();
		           }
		           catch (Exception exc)
		           {
		               exc.printStackTrace(System.out);
		           }
		           
		           System.out.println(selectedItem.getNombre());

		         }
		    }
		};
	
		list.addMouseListener(mouseListener);
		
		
		MedioDePago[] choices= {new MedioDePago("Efectivo",""),new MedioDePago("Visa","Galicia"),new MedioDePago("Vale Super","")};
		
		JComboBox comboBox = new JComboBox(choices);
		comboBox.setBounds(346, 391, 140, 20);
		frmSuperTecnicasGui.getContentPane().add(comboBox);
		
		JLabel lblImagen = new JLabel();
		lblImagen.setBounds(49, 338, 146, 128);
		lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("recursos/invoice-icon.png")));

		frmSuperTecnicasGui.getContentPane().add(lblImagen);
		
		comboBox.addItemListener(new ItemListener(){
	            @Override
	            public void itemStateChanged(ItemEvent e)
	            {
	                //getItem returns an object so it gets cast
	                //as a String to retrieve the item value
	            	MedioDePago item = (MedioDePago)e.getItem();
	                if (e.getStateChange() == ItemEvent.SELECTED)
	                {
	                	System.out.println("El Pago con "+item.toString() + " fue seleccionado.");
	                	
	                	med.setBanco(item.getBanco());
	                	med.setMedio(item.getMedio());
	                }
	                else
	                {
	                	System.out.println("El Pago con "+item.toString() + " fue cancelado.");
	                }
	            }
	        });
		
		 PrintStream standardOut = System.out;
		 System.setOut(printStream);
	     System.setErr(printStream);
	     System.out.println("Bienvenido USUARIO (Pulse Abrir caja para iniciar)");
	

			
	     
	     
	}
}
