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
import javax.swing.Timer;

import tp_supermarket.bonificacion.Bonificacion;
import tp_supermarket.bonificacion.BonificacionDescuentoMarca;
import tp_supermarket.caja.Caja;
import tp_supermarket.caja.ExceptionAbrirCajaConCajaAbierta;
import tp_supermarket.caja.ExceptionActualizarPromosConCajaCerrada;
import tp_supermarket.caja.ExceptionTerminarCompraConCajaCerrada;
import tp_supermarket.caja.ExceptionTerminarCompraConCompraNoIniciada;
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
		frmSuperTecnicasGui.setBounds(100, 100, 753, 650);
		frmSuperTecnicasGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSuperTecnicasGui.getContentPane().setLayout(null);
		
		final Controlador miControlador = new Controlador();
		
		
		
		
		//Medio de pago
		final MedioDePago med = new MedioDePago();
		
		
		//ABRO CAJA
		JButton btnNewButton = new JButton("Abrir Caja");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					miControlador.abrirCaja();
				} catch (ExceptionAbrirCajaConCajaAbierta e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(343, 20, 169, 23);
		frmSuperTecnicasGui.getContentPane().add(btnNewButton);
		
		//INICIO COMPRA
		JButton btnNewButton_1 = new JButton("Iniciar Compra");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.iniciarCompra();
			}
		});
		btnNewButton_1.setBounds(343, 56, 169, 23);
		frmSuperTecnicasGui.getContentPane().add(btnNewButton_1);
		
		
		
		//TERMINO COMPRA
		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					miControlador.terminarCompra();
				} catch (ExceptionTerminarCompraConCajaCerrada e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExceptionTerminarCompraConCompraNoIniciada e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  
				
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
		btnFinalizarCompra.setBounds(595, 529, 123, 23);
		frmSuperTecnicasGui.getContentPane().add(btnFinalizarCompra);
		
		JLabel lblSeleccionarMedioDe = new JLabel("Seleccionar Medio de Pago");
		lblSeleccionarMedioDe.setBounds(343, 510, 159, 14);
		frmSuperTecnicasGui.getContentPane().add(lblSeleccionarMedioDe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(343, 138, 375, 355);
		frmSuperTecnicasGui.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		
		final DefaultListModel model = new DefaultListModel();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 159, 237, 303);
		frmSuperTecnicasGui.getContentPane().add(scrollPane_1);
		final JList list = new JList(model);
		scrollPane_1.setViewportView(list);
		
		
		
		for (int i=0; i<miControlador.listadoProductos().size();i++){
		
			model.addElement(miControlador.listadoProductos().get(i));
			
		}
		
		final JLabel lblImagen3 = new JLabel();
		lblImagen3.setBounds(257, 275, 98, 99);
		lblImagen3.setIcon(new javax.swing.ImageIcon(getClass().getResource("recursos/shop-cart-add-icon.png")));
		frmSuperTecnicasGui.getContentPane().add(lblImagen3).hide();
		
		   final ActionListener taskPerformer = new ActionListener() {
		       public void actionPerformed(ActionEvent evt) {
		    	   lblImagen3.setVisible(false);
		       }
		   };
		   
		   final Timer timer = new Timer(500, taskPerformer);
		
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 2) {

		           Producto selectedItem = (Producto) list.getSelectedValue();
		           // add selectedItem to your second list.
		           miControlador.agregarProducto(selectedItem);
		           
		           lblImagen3.setVisible(true);
		           timer.start();
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

		           System.out.printf("%1$-5d %2$-20s %3$-10s $%4$-10.2f\n", selectedItem.getId() ,selectedItem.getNombre(),selectedItem.getMarca(),selectedItem.getCosto());

		           
		         }
		    }
		};
		
		list.addMouseListener(mouseListener);
		
		MedioDePago[] choices= {new MedioDePago("Efectivo",""),new MedioDePago("Visa","Galicia"),new MedioDePago("Vale Super","")};
		
		JComboBox comboBox = new JComboBox(choices);
		comboBox.setBounds(344, 530, 140, 20);
		frmSuperTecnicasGui.getContentPane().add(comboBox);
		
		JButton btnCerrarCaja = new JButton("Cerrar Caja");
		btnCerrarCaja.setBounds(538, 20, 117, 23);
		frmSuperTecnicasGui.getContentPane().add(btnCerrarCaja);
		
		JButton btnTotalVentas = new JButton("Total Ventas");
		btnTotalVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miControlador.getTotalSinDescuento();
			}
		});
		btnTotalVentas.setBounds(12, 498, 133, 23);
		frmSuperTecnicasGui.getContentPane().add(btnTotalVentas);
		
		JButton btnTotalDescuentos = new JButton("Total Descuentos");
		btnTotalDescuentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.getTotalDescuentos();
			}
		});
		btnTotalDescuentos.setBounds(11, 529, 134, 23);
		frmSuperTecnicasGui.getContentPane().add(btnTotalDescuentos);
		
		JButton btnTotalMedioPago = new JButton("Total Medio Pago");
		btnTotalMedioPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.getTotalMedioPagoPorCaja();
				
			}
		});
		btnTotalMedioPago.setBounds(11, 563, 134, 23);
		frmSuperTecnicasGui.getContentPane().add(btnTotalMedioPago);
				
				JLabel lblEstadisticasTotales = new JLabel("Estadisticas / Totales");
				lblEstadisticasTotales.setBounds(11, 473, 134, 14);
				frmSuperTecnicasGui.getContentPane().add(lblEstadisticasTotales);
				
				JButton btnActualizarPromociones = new JButton("Actualizar Promociones");
				btnActualizarPromociones.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							miControlador.cargarPromocionesYBonificaciones();
						} catch (ExceptionActualizarPromosConCajaCerrada e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
				btnActualizarPromociones.setBounds(343, 91, 169, 23);
				frmSuperTecnicasGui.getContentPane().add(btnActualizarPromociones);
				
				JLabel lblImagen = new JLabel();
				lblImagen.setBounds(176, 473, 146, 128);
				lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("recursos/invoice-icon.png")));
				
						frmSuperTecnicasGui.getContentPane().add(lblImagen);
						

						
						JLabel lblListadoDeProductos = new JLabel("Listado de Productos");
						lblListadoDeProductos.setBounds(10, 138, 134, 14);
						frmSuperTecnicasGui.getContentPane().add(lblListadoDeProductos);
						
						
						JLabel lblImagen1 = new JLabel();
						lblImagen1.setBounds(8, 9, 137, 122);
						lblImagen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("recursos/supertecnicas.png")));
						frmSuperTecnicasGui.getContentPane().add(lblImagen1);
		
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
	     System.out.println("Bienvenido USUARIO (Pulse Abrir Caja para iniciar)");
	

			
	     
	     
	}
}
