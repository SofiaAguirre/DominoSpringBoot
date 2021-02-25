package com.domino.app;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaPrincipal extends JFrame {
	/**
	 * 
	 */
	private static int dominoPieces = 28;
	private static final long serialVersionUID = 1L;
	Domino domino = new Domino();
    RandomNumber random = new RandomNumber(dominoPieces);
	Player player1 = new Player(domino, random);
	Player player2 = new Player(domino, random);
	public VentanaPrincipal() {
		this.setLayout(null);
		JLabel etiqueta = new JLabel(player1.printHand());
		etiqueta.setBounds(20, 20, 300, 20);
		JLabel etiquetaDos = new JLabel(player2.printHand());
		etiquetaDos.setBounds(20,40,300,30);
		setSize(500,600);
		setTitle("DOMINO");
		setVisible(true);
		add(etiqueta);
		add(etiquetaDos);
		
		
	}

}
