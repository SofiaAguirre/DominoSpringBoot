package com.domino.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domino.app.dao.PartidasDAO;
import com.domino.app.models.Partida;

@Service
public class Game extends JFrame implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int dominoPieces = 28;
    private static boolean canPlay = true;

    @Autowired
    private PartidasDAO partidasDAO;
    static JLabel etiquetaFichas = new JLabel();
    static JLabel etiquetaNombre =new JLabel();
    static JLabel etiquetaLado = new JLabel();
    static JLabel etiquetaNombreGanador = new JLabel();
    static JLabel etiquetaP1 = new JLabel();
    static JLabel etiquetaP2 = new JLabel();
    static JLabel etiquetaWin = new JLabel();
    
    public void play() {
    	
    	UIManager.put("OptionPane.background", Color.cyan);
    	UIManager.getLookAndFeelDefaults().put("Panel.background", Color.cyan);
    	
        //Jugador 1 pasa a ser (con su nombre) ese jugador
    	
    	//JTextField txt = new JTextField();
        Scanner input = new Scanner(System.in);
        Domino domino = new Domino();
        RandomNumber random = new RandomNumber(dominoPieces);
        Player player1 = new Player(domino, random);
        Player player2 = new Player(domino, random);
        Table table = new Table();
       
        //Luego de que ambos jugadores tengan sus respectivas manos quedaran 14 fichas
        dominoPieces = 14;
        int turn = 1;
        String inicioPartida = String.valueOf(LocalDateTime.now());
		
        //Jugador1 ingresa su nombre
        //Jugador2 ingresa su nombre
	 
       // System.out.println("Jugador1 ingrese su nombre:");
       // String nombreJ1 = input.nextLine();
        String nombreJ1 = JOptionPane.showInputDialog(null,"Jugador1 ingrese su nombre","DOMINO", JOptionPane.INFORMATION_MESSAGE);
      //  System.out.println("Jugador2 ingrese su nombre:");
        //1String nombreJ2 = input.nextLine();
        String nombreJ2 = JOptionPane.showInputDialog(null,"Jugador2 ingrese su nombre","DOMINO", JOptionPane.INFORMATION_MESSAGE);
        // se comprueba quien tiene el par de dobles mas grande
        //System.out.println(player1.printHand());
        //System.out.println(player2.printHand());
		
        Game ventanaJuego = new Game();

        Font fuente=new Font("Arial black", Font.ITALIC, 20);
        Font fuenteDos = new Font("Arial black",Font.ITALIC, 14);
        
	//	JLabel etiqueta = new JLabel(nombreJ1 +" "+ player1.printHand());
	    etiquetaLado.setBounds(0,200,800,30);
	    etiquetaLado.setVisible(true);
	    etiquetaLado.setOpaque(true);
	    etiquetaLado.setBackground(Color.BLACK);
	    etiquetaLado.setForeground(Color.white);
	    etiquetaLado.setHorizontalAlignment(JLabel.CENTER);
	    etiquetaLado.setFont(fuenteDos);
		add(etiquetaLado);
		
		//JLabel etiquetaDos = new JLabel(nombreJ2 +" "+ player2.printHand());
		//etiquetaDos.setBounds(20,80,400,20);
	//	add(etiquetaDos);
		
		etiquetaFichas = new JLabel("Fichas "+ player1.printHand());
		etiquetaFichas.setBounds(0,170,800,30);
		etiquetaFichas.setOpaque(true);
		etiquetaFichas.setBackground(Color.black);
		etiquetaFichas.setForeground(Color.white);
		etiquetaFichas.setHorizontalAlignment(JLabel.CENTER);
		etiquetaFichas.setFont(fuenteDos);
        add(etiquetaFichas);
        
        etiquetaNombreGanador.setBounds(200,250,400,50);
        etiquetaNombreGanador.setOpaque(true);
		etiquetaNombreGanador.setBackground(Color.black);
		etiquetaNombreGanador.setHorizontalAlignment(JLabel.CENTER);
		etiquetaNombreGanador.setForeground(Color.yellow);
		etiquetaNombreGanador.setFont(fuente);
		etiquetaNombreGanador.setVisible(false);
		add(etiquetaNombreGanador);
		
		JButton boton = new JButton();
		boton.setText("S A L I R");	         
		boton.setForeground(Color.white);
		boton.setBackground(Color.black);
		boton.setBounds(300,500,200,30);
		boton.setFont(fuenteDos);
		add(boton);
		etiquetaP1.setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("/imagenes/mx1.jpg")).getImage())));
		etiquetaP1.setBounds(70,80,80,80);
		etiquetaP1.setVisible(false);
		add(etiquetaP1);

		etiquetaP2.setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("/imagenes/m2.jpg")).getImage())));
		etiquetaP2.setBounds(650,80,80,80);
		etiquetaP2.setVisible(false);
		add(etiquetaP2);

		JLabel etiquetaTitulo = new JLabel();
	    etiquetaTitulo.setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("/imagenes/update.gif")).getImage())));
	    etiquetaTitulo.setBounds(150,10,500,65);
	    etiquetaTitulo.setHorizontalAlignment(JLabel.CENTER);
	    add(etiquetaTitulo);

	    etiquetaWin.setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("/imagenes/MXWIN.gif")).getImage())));
        etiquetaWin.setBounds(300,300,200,200);
        etiquetaWin.setVisible(false);
        etiquetaWin.setHorizontalAlignment(JLabel.CENTER);
        add(etiquetaWin); 

        JLabel etiquetaDomAnim2 = new JLabel ();
        etiquetaDomAnim2.setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("/imagenes/dom2.gif")).getImage())));
        etiquetaDomAnim2.setBounds(0,250,400,300);
        add(etiquetaDomAnim2);

        JLabel etiquetaDomAnim = new JLabel ();
        etiquetaDomAnim.setIcon(new ImageIcon((new ImageIcon(this.getClass().getResource("/imagenes/dom1.gif")).getImage())));
        etiquetaDomAnim.setBounds(450,250,400,300);
        add(etiquetaDomAnim); 
        
       
        etiquetaNombre.setBounds(200,100,400,50);
        etiquetaNombre.setOpaque(true);
		etiquetaNombre.setBackground(Color.black);
		etiquetaNombre.setForeground(Color.green);
		etiquetaNombre.setFont(fuente);
		etiquetaNombre.setHorizontalAlignment(JLabel.CENTER);
        add(etiquetaNombre);
        
        		
	    JPanel fondo = new JPanel();
	    fondo.setBackground(Color.black);
	    fondo.setSize(800,600);
	    fondo.setLayout(new GridBagLayout());
	    add(fondo);
	    
		setSize(800,600);
		setTitle("DOMINO");
		setVisible(true);
		setLocation(275,10);
		ventanaJuego.pack();
		
		boton.addActionListener( e -> {
		System.exit(0);
		//SpringApplication.run(AppApplication.class);
		}); 
		
        int player1MayorDoble = player1.dobleMasGrande();
        int player2MayorDoble = player2.dobleMasGrande();

        String player1Name = "";
        String player2Name = "";

        Player p1 = null;
        Player p2 = null;

        if(player2MayorDoble > player1MayorDoble){
            player1Name = nombreJ2;
            p1 = player2;
            player2Name = nombreJ1;
            p2 = player1;
          
        } else if(player1MayorDoble > player2MayorDoble){
            player1Name = nombreJ1;
            p1 = player1;
            player2Name = nombreJ2;
            p2 = player2;
         
        } else {
            //if(player1MayorDoble == player2MayorDoble)
            int player1FichaMasAlta = player1.fichaMasAlta();
            int player2FichaMasAlta = player2.fichaMasAlta();
            if(player2FichaMasAlta > player1FichaMasAlta){
                player1Name = nombreJ2;
                p1 = player2;
                player2Name = nombreJ1;
                p2 = player1;
              
            } else if (player1FichaMasAlta > player2FichaMasAlta){
                player1Name = nombreJ1;
                p1 = player1;
                player2Name = nombreJ2;
                p2 = player2;
              
            }
        }
      
 
        System.out.println(player1Name + ": Elija una ficha para comenzar el juego:");
        etiquetaNombre.setText("JUGADOR: "+player1Name);
       //System.out.println(p1.printHand());
        etiquetaFichas.setText("FICHAS: "+p1.printHand());
       //int start = input.nextInt();
       
        
        etiquetaP1.setVisible(true);
        etiquetaP2.setVisible(false);
       
        String dato = JOptionPane.showInputDialog(null,"Seleccione una ficha","DOMINO", JOptionPane.INFORMATION_MESSAGE);
        etiquetaP1.setVisible(false);
        etiquetaP2.setVisible(true);
        etiquetaNombre.setForeground(Color.magenta);
        int start= Integer.valueOf(dato);
        
        start--;
        String firstPiece = p1.getDomino(start);
        p1.removeDomino(start);
        table.placeFirst(firstPiece);
        
        turn++;
       
        //booleanos, demuestra si los jugadores tienen fichas para jugar
        boolean playerOnePieces = true;
        boolean playerTwoPieces = true;

        while(canPlay && playerOnePieces && playerTwoPieces){
        
            if(p1.getDominoCount() <= 0){
                playerOnePieces = false;
            }
            else if(p2.getDominoCount() <= 0){
                playerTwoPieces = false;
            }
            if(turn % 2 == 1){
                //Jugador1
                turn = playTurn(input, p1, table, turn, player1Name);
                etiquetaP1.setVisible(false);
                etiquetaP2.setVisible(true);
                etiquetaNombre.setForeground(Color.magenta);
                
            } else if(turn % 2 == 0){
                //Jugador2
                turn = playTurn(input, p2, table, turn, player2Name);
                etiquetaP1.setVisible(true);
                etiquetaP2.setVisible(false);
                etiquetaNombre.setForeground(Color.green);
            }   
        }
        
        String finPartida = String.valueOf(LocalDateTime.now());
        String winner = winner(p1, p2, player1Name, player2Name);
        Partida partida = new Partida(player1Name, player2Name, inicioPartida, finPartida, winner);
        partidasDAO.savePartida(partida);
       
    }
    	private static int playTurn(Scanner input, Player player, Table table, int turn, String nombre) {
    		
	   	etiquetaFichas.setText("FICHAS:  "+player.printHand());
        
	   	System.out.println(Table.openEnds());
        
        etiquetaLado.setText(Table.openEnds());
        
        System.out.println (player.printHand());
        
        System.out.println(nombre + ": \n 1-Seleccionar ficha \n 2-Pasar");
        
        etiquetaNombre.setText("JUGADOR: " + nombre);
        
        
        choice(input, player, table);
        turn++;
        return turn;
    }

    private static void choice(Scanner input, Player player, Table table){
    	Integer[] botones = {1, 2};
        int choice;
        int datoDos = JOptionPane.showOptionDialog(null, "Seleccione opcion\n 1-Seleccionar ficha  2-Pasar","DOMINO", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones,1);
      
        choice = datoDos;
        if(choice == 1){
            pass(player);
        }
        else if(choice == 0){
            place(input, player, table);
        }
        else{
            System.out.println("Elección inválida, el turno es concedido al siguiente jugador");            
        }
    }

    private static void pass(Player player){
        dominoPieces--;
        if(dominoPieces > 0){
            player.giveDomino();
            System.out.println("Se ha agarrado una pieza aleatoria de las restantes.");
            JOptionPane.showInternalMessageDialog(null,"Se ha agarrado una pieza aleatoria de las restantes","DOMINO", JOptionPane.INFORMATION_MESSAGE);	
          
        }
        else{
            System.out.println("No quedan más piezas disponibles.");
         
            canPlay = false;
        }
    }
   

    private static void place(Scanner input, Player player, Table table){
        //System.out.println("Seleccione una pieza: ");
    	String side;
        String datoDos = JOptionPane.showInputDialog(null, "Seleccione una Ficha","DOMINO", JOptionPane.INFORMATION_MESSAGE);
        int domino = Integer.valueOf(datoDos);
      //  input.nextLine();
        domino--;
        try {
            String piece = "" + player.getDomino(domino);
            System.out.println("En qué lado de la tabla le gustaría posicionarla? Escriba 'izquierda' o 'derecha':");
            
            String[] botones = {"izquierda", "derecha"};
            int datoLado = JOptionPane.showOptionDialog(null, "Seleccione opcion","DOMINO", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botones,1);
          
            if (datoLado==0) {
             side = "izquierda";
            }
             else {
             side="derecha";
             }
            table.placePiece(side, piece, player, domino);   
        } catch(Exception e){
            System.out.println("Elección inválida, el turno es concedido al siguiente jugador");
            JOptionPane.showInputDialog(null, "Elección inválida, el turno es concedido al siguiente jugador","DOMINO", JOptionPane.INFORMATION_MESSAGE);          
        }
        
    }
    private static String winner(Player player1, Player player2, String nombreJ1, String nombreJ2){
        String winner = "";
        etiquetaNombreGanador.setVisible(true);
        etiquetaWin.setVisible(true);
        if(player1.getDominoCount() < player2.getDominoCount()){
            winner = nombreJ1;
            System.out.println(winner + " ES EL GANADOR!!!");
            System.out.println("Con: " + player1.getDominoCount() + " piezas!");
            etiquetaNombreGanador.setText(winner + " ES EL GANADOR!!!");
        } else if(player1.getDominoCount() > player2.getDominoCount()){
            winner = nombreJ2;
            System.out.println(winner + " ES EL GANADOR!!!");
            System.out.println("Con: " + player2.getDominoCount() + " piezas!");
            etiquetaNombreGanador.setText(winner + " ES EL GANADOR!!!");
        } else{
            winner = "EMPATE";
            System.out.println(winner);
            etiquetaNombreGanador.setText(winner);
        }
        return winner;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}
    
}

