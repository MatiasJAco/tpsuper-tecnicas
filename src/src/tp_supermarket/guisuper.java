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
import tp_supermarket.caja.ExceptionAbrirCajaConCajaAbierta;
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
		frmSuperTecnicasGui.setBounds(100, 100, 753, 590);
		frmSuperTecnicasGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSuperTecnicasGui.getContentPane().setLayout(null);
		
		final Controlador miControlador = new Controlador();
		
		
		
		
		//Medio de pago
		final MedioDePago med = new MedioDePago();
	
		
		
		//ABRO CAJA
		JButton btnNewButton = new JButton("Abrir Cajar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					miControlador.abrirCaja();
				} catch (ExceptionAbrirCajaConCajaAbierta e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println("Caja: "+miControlador.getNombreCaja()+" ahora esta ABIERTA");
				
				System.out.println("Pulse Iniciar Compra para comenzar una nueva compra ");
				
				
			}
		});
		btnNewButton.setBounds(10, 11, 169, 23);
		frmSuperTecnicasGui.getContentPane().add(btnNewButton);
		
		//INICIO COMPRA
		JButton btnNewButton_1 = new JButton("Iniciar Compra");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.iniciarCompra();
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
				miControlador.terminarCompra();
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
		btnFinalizarCompra.setBounds(595, 502, 123, 23);
		frmSuperTecnicasGui.getContentPane().add(btnFinalizarCompra);
		
		JLabel lblSeleccionarMedioDe = new JLabel("Seleccionar Medio de Pago");
		lblSeleccionarMedioDe.setBounds(343, 483, 159, 14);
		frmSuperTecnicasGui.getContentPane().add(lblSeleccionarMedioDe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(343, 125, 375, 355);
		frmSuperTecnicasGui.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		
		final DefaultListModel model = new DefaultListModel();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 138, 237, 207);
		frmSuperTecnicasGui.getContentPane().add(scrollPane_1);
		final JList list = new JList(model);
		scrollPane_1.setViewportView(list);
		
		
		for (int i=0; i<miControlador.listadoProductos().size();i++){
		
			model.addElement(miControlador.listadoProductos().get(i));
			
		}
		
		
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 2) {


		           Producto selectedItem = (Producto) list.getSelectedValue();
		           // add selectedItem to your second list.
		           miControlador.agregarProducto(selectedItem);
		           
		           
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
		comboBox.setBounds(344, 503, 140, 20);
		frmSuperTecnicasGui.getContentPane().add(comboBox);
		
		JButton btnCerrarCaja = new JButton("Cerrar Caja");
		btnCerrarCaja.setBounds(187, 11, 117, 23);
		frmSuperTecnicasGui.getContentPane().add(btnCerrarCaja);
		
		JButton btnTotalVentas = new JButton("Total Ventas");
		btnTotalVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miControlador.getTotalSinDescuento();
			}
		});
		btnTotalVentas.setBounds(11, 437, 133, 23);
		frmSuperTecnicasGui.getContentPane().add(btnTotalVentas);
		
		JButton btnTotalDescuentos = new JButton("Total Descuentos");
		btnTotalDescuentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.getTotalDescuentos();
			}
		});
		btnTotalDescuentos.setBounds(10, 468, 134, 23);
		frmSuperTecnicasGui.getContentPane().add(btnTotalDescuentos);
		
		JButton btnTotalMedioPago = new JButton("Total Medio Pago");
		btnTotalMedioPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.getTotalMedioPagoPorCaja();
				
			}
		});
		btnTotalMedioPago.setBounds(10, 502, 134, 23);
		frmSuperTecnicasGui.getContentPane().add(btnTotalMedioPago);
		
		JLabel lblImagen = new JLabel();
		lblImagen.setBounds(175, 412, 146, 128);
		lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("recursos/invoice-icon.png")));
		
				frmSuperTecnicasGui.getContentPane().add(lblImagen);
				
				JLabel lblEstadisticasTotales = new JLabel("Estadisticas / Totales");
				lblEstadisticasTotales.setBounds(10, 412, 134, 14);
				frmSuperTecnicasGui.getContentPane().add(lblEstadisticasTotales);
				
				JButton btnActualizarPromociones = new JButton("Actualizar Promociones");
				btnActualizarPromociones.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						miControlador.cargarPromocionesYBonificaciones();
						System.out.println("Bonificaciones Cargados");
					}
				});
				btnActualizarPromociones.setBounds(10, 78, 169, 23);
				frmSuperTecnicasGui.getContentPane().add(btnActualizarPromociones);
		
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
	                	
	                	miControlador.setMedioPago(item.getBanco(), item.getMedio());
	                	
	                	med.setBanco(item.getBanco());
	                	med.setMedio(item.getMedio());
	                }
	                else
	                {
	                	System.out.println("El Pago con "+item.toString() + " fue cancelado.");
	                }
	            }
	        });
		
		System.setOut(printStream);
	     System.setErr(printStream);
	     System.out.println("Bienvenido USUARIO (Pulse Abrir caja para iniciar)");
	

			
	     
	     
	}
}
