package tp_supermarket;

import java.awt.EventQueue;
import java.awt.Font;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import tp_supermarket.caja.MedioDePago;
import tp_supermarket.caja.MedioDePagoStats;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCajaCerrada;
import tp_supermarket.caja.exceptions.ExceptionIniciarCompraConCompraEnCurso;
import tp_supermarket.compra.Compra;
import tp_supermarket.producto.Producto;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.SystemColor;


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
	 * 
	 * @throws ExceptionIniciarCompraConCompraEnCurso
	 * @throws ExceptionIniciarCompraConCajaCerrada
	 */
	public guisuper() throws ExceptionIniciarCompraConCajaCerrada,
			ExceptionIniciarCompraConCompraEnCurso {
		initialize();
	}

	private Controlador miControlador;
	private MedioDePago med;
	
	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ExceptionIniciarCompraConCompraEnCurso
	 * @throws ExceptionIniciarCompraConCajaCerrada
	 */
	private void initialize() throws ExceptionIniciarCompraConCajaCerrada,
			ExceptionIniciarCompraConCompraEnCurso {
		frmSuperTecnicasGui = new JFrame();
		frmSuperTecnicasGui.setIconImage(new ImageIcon(
				"src/tp_supermarket/recursos/shop.png").getImage());
		frmSuperTecnicasGui.getContentPane().setBackground(
				SystemColor.activeCaption);
		frmSuperTecnicasGui.setTitle("Super Tecnicas");
		frmSuperTecnicasGui.setBounds(100, 100, 753, 719);
		frmSuperTecnicasGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSuperTecnicasGui.getContentPane().setLayout(null);

		miControlador = new Controlador();
		med = new MedioDePago();

		//BOTON ABRIR CAJA
		JButton btnNewButton = new JButton("Abrir Caja");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				miControlador.abrirCaja();

			}
		});
		btnNewButton.setBounds(343, 20, 169, 23);
		frmSuperTecnicasGui.getContentPane().add(btnNewButton);

		//BOTON INICIAR COMPRA
		JButton btnNewButton_1 = new JButton("Iniciar Compra");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.iniciarCompra();
			}
		});
		btnNewButton_1.setBounds(343, 56, 169, 23);
		frmSuperTecnicasGui.getContentPane().add(btnNewButton_1);

		//BOTON TERMINAR COMPRA
		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				miControlador.terminarCompra();

//				try {
//					Clip clip = AudioSystem.getClip();
//
//					clip.open(AudioSystem.getAudioInputStream(new File(
//							"src/tp_supermarket/recursos/chaching.wav")));
//					clip.start();
//				} catch (Exception exc) {
//					exc.printStackTrace(System.out);
//				}
//				try {
//					Clip clipw = AudioSystem.getClip();
//
//					clipw.open(AudioSystem.getAudioInputStream(new File(
//							"src/tp_supermarket/recursos/printer.wav")));
//					clipw.start();
//				} catch (Exception exc) {
//					exc.printStackTrace(System.out);
//				}
			}
		});
		btnFinalizarCompra.setBounds(565, 634, 140, 23);
		frmSuperTecnicasGui.getContentPane().add(btnFinalizarCompra);

		//BOTON SELECCIONAR MEDIO DE PAGO
		JLabel lblSeleccionarMedioDe = new JLabel("Seleccionar Medio de Pago");
		lblSeleccionarMedioDe.setBounds(343, 510, 159, 14);
		frmSuperTecnicasGui.getContentPane().add(lblSeleccionarMedioDe);

		
		//LISTADO DE PRODUCTOS
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(343, 138, 375, 355);
		frmSuperTecnicasGui.getContentPane().add(scrollPane);

		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 12));

		PrintStream printStream = new PrintStream(new CustomOutputStream(
				textArea));

		final DefaultListModel model = new DefaultListModel();

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 159, 237, 303);
		frmSuperTecnicasGui.getContentPane().add(scrollPane_1);
		final JList list = new JList(model);
		scrollPane_1.setViewportView(list);
		list.setFont(new Font("Courier New", Font.PLAIN, 12));
		for (int i = 0; i < miControlador.listadoProductos().size(); i++) {

			model.addElement(miControlador.listadoProductos().get(i));

		}

		//Imagen
		final JLabel lblImagen3 = new JLabel();
		lblImagen3.setBounds(257, 275, 98, 99);
		lblImagen3.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"recursos/shop-cart-add-icon.png")));
		frmSuperTecnicasGui.getContentPane().add(lblImagen3).hide();

		final ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				lblImagen3.setVisible(false);
			}
		};

		final Timer timer = new Timer(500, taskPerformer);
		//Listener de seleccion de productos
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					Producto selectedItem = (Producto) list.getSelectedValue();
					// add selectedItem to your second list.
					miControlador.agregarProducto(selectedItem);

					lblImagen3.setVisible(true);
					timer.start();
//					try {
//						Clip clip = AudioSystem.getClip();
//
//						clip.open(AudioSystem.getAudioInputStream(new File(
//								"src/tp_supermarket/recursos/beep.wav")));
//						clip.start();
//					} catch (Exception exc) {
//						exc.printStackTrace(System.out);
//					}

					System.out.printf("%1$-5d %2$-20s %3$-20s $%4$-10.2f\n",
							selectedItem.getId(), selectedItem.getNombre(),
							selectedItem.getMarca(), selectedItem.getCosto());

				}
			}
		};

		list.addMouseListener(mouseListener);

		//MEDIOS DE PAGO EN VISTA

		ArrayList<MedioDePago> medios = new ArrayList<MedioDePago>();
//		medios.add(new MedioDePago("Efectivo", ""));
//		medios.add(	new MedioDePago("Visa", "Galicia"));
//		medios.add(	new MedioDePago("Vale Super", ""));
		for (int i = 0; i < miControlador.getMediosDePagosDisponibles().size(); i++) {

			medios.add(miControlador.getMediosDePagosDisponibles().get(i));

		}
		
		//Tipos de clientes en vista
		
		ArrayList<String> tclientes = new ArrayList<String>();

		for (int i = 0; i < miControlador.getTiposDeClientes().size(); i++) {

			tclientes.add(miControlador.getTiposDeClientes().get(i));

		}
		
		//Tipos de cupones en vista
		
		ArrayList<String> tcupon = new ArrayList<String>();

		for (int i = 0; i < miControlador.getTipoDesc().size(); i++) {

			tcupon.add(miControlador.getTipoDesc().get(i));

		}
		
		JComboBox comboBox = new JComboBox(medios.toArray());
		comboBox.setBounds(344, 530, 140, 20);
		frmSuperTecnicasGui.getContentPane().add(comboBox);

		final ActionListener taskCerrar = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				textArea.setText("");
				System.out
						.println("Bienvenido USUARIO (Pulse Abrir Caja para iniciar)");
			}
		};
		final Timer timerClose = new Timer(2500, taskCerrar);
		timerClose.setRepeats(false);
		JButton btnCerrarCaja = new JButton("Cerrar Caja");
		btnCerrarCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (miControlador.cerrarCaja()) {

					System.out.println("Cerrando Caja...");
					timerClose.start();					
				}

			}
		});

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
				miControlador.cargarPromocionesYBonificaciones();
			}
		});
		btnActualizarPromociones.setBounds(343, 91, 169, 23);
		frmSuperTecnicasGui.getContentPane().add(btnActualizarPromociones);

		JLabel lblImagen = new JLabel();
		lblImagen.setBounds(176, 473, 146, 128);
		lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"recursos/invoice-icon.png")));

		frmSuperTecnicasGui.getContentPane().add(lblImagen);

		JLabel lblListadoDeProductos = new JLabel("Listado de Productos");
		lblListadoDeProductos.setBounds(10, 138, 134, 14);
		frmSuperTecnicasGui.getContentPane().add(lblListadoDeProductos);

		JLabel lblImagen1 = new JLabel();
		lblImagen1.setBounds(8, 9, 137, 122);
		lblImagen1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"recursos/supertecnicas.png")));
		frmSuperTecnicasGui.getContentPane().add(lblImagen1);

		JButton btnRankingVentas = new JButton("Ranking Ventas");
		btnRankingVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				miControlador.verRanking();
			}
		});
		btnRankingVentas.setBounds(10, 597, 135, 23);
		frmSuperTecnicasGui.getContentPane().add(btnRankingVentas);
		
		JComboBox comboBox_1 = new JComboBox(tclientes.toArray());
		comboBox_1.setBounds(344, 581, 140, 20);
		frmSuperTecnicasGui.getContentPane().add(comboBox_1);
		
		JLabel lblSeleccionarTipoDe = new JLabel("Seleccionar Tipo De Cliente");
		lblSeleccionarTipoDe.setBounds(343, 561, 159, 14);
		frmSuperTecnicasGui.getContentPane().add(lblSeleccionarTipoDe);
		
		JLabel lblTieneCuponDe = new JLabel("Tiene Cupon de Descuento?");
		lblTieneCuponDe.setBounds(343, 615, 159, 14);
		frmSuperTecnicasGui.getContentPane().add(lblTieneCuponDe);
		
		JComboBox comboBox_2 = new JComboBox(tcupon.toArray());
		comboBox_2.setBounds(344, 635, 140, 20);
		frmSuperTecnicasGui.getContentPane().add(comboBox_2);
		
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// getItem returns an object so it gets cast
				// as a String to retrieve the item value
				MedioDePago item = (MedioDePago) e.getItem();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("El Pago con " + item.toString()
							+ " fue seleccionado.");

					miControlador.setMedioPago(item.getBanco(), item.getMedio());

					med.setBanco(item.getBanco());
					med.setMedio(item.getMedio());
				} else {
					System.out.println("El Pago con " + item.toString()
							+ " fue cancelado.");
				}
			}
		});
		
		comboBox_1.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					// getItem returns an object so it gets cast
					// as a String to retrieve the item value
					String item = (String) e.getItem();
					if (e.getStateChange() == ItemEvent.SELECTED) {
						System.out.println("Tipo de cliente " + item.toString()
								+ " seleccionado.");

						miControlador.setTipoCliente(item.toString());
						


					} else {
						System.out.println("Tipo de cliente " + item.toString()
								+ " cancelado.");
					}
				}
			});

		
		comboBox_2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// getItem returns an object so it gets cast
				// as a String to retrieve the item value
				String item = (String) e.getItem();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("Cupon " + item.toString()
							+ " seleccionado.");

					//TODO:Implementar
					miControlador.setTipoDesc(item.toString());
					


				} else {
					System.out.println("Cupon " + item.toString()
							+ " cancelado.");
				}
			}
		});
		
		System.setOut(printStream);
		System.setErr(printStream);
		System.out
				.println("Bienvenido USUARIO (Pulse Abrir Caja para iniciar)");

	}
}
