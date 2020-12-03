package com.domino.app;

import com.domino.app.Repository.PartidaRepository;
import com.domino.app.dao.PartidasDAO;
import com.domino.app.models.Partida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Scanner;

@Service
public class Game {

    private static int dominoPieces = 28;
    private static boolean canPlay = true;

    @Autowired
    private PartidasDAO partidasDAO;

    public void play() {

        //Jugador 1 pasa a ser (con su nombre) ese jugador

        Scanner input = new Scanner(System.in);
        Domino domino = new Domino();
        RandomNumber random = new RandomNumber(dominoPieces);
        Player player1 = new Player(domino, random);
        Player player2 = new Player(domino, random);
        Table table = new Table();
        //Luego de que ambos jugadores tengan sus respectivas manos quedaran 14 fichas
        dominoPieces = 14;
        int turn = 1;
        System.out.println();
        String inicioPartida = String.valueOf(LocalDateTime.now());

        //Jugador1 ingresa su nombre
        //Jugador2 ingresa su nombre

        System.out.println("Jugador1 ingrese su nombre:");
        String nombreJ1 = input.nextLine();
        System.out.println("Jugador2 ingrese su nombre:");
        String nombreJ2 = input.nextLine();

        // se comprueba quien tiene el par de dobles mas grande
        System.out.println(player1.printHand());
        System.out.println(player2.printHand());

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

        System.out.println(player1Name + ": Elija una pieza para comenzar el juego:");
        System.out.println(p1.printHand());
        int start = input.nextInt();
        input.nextLine();
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
            } else if(turn % 2 == 0){
                //Jugador2
                turn = playTurn(input, p2, table, turn, player2Name);
            }
        }
        String finPartida = String.valueOf(LocalDateTime.now());
        String winner = winner(p1, p2, player1Name, player2Name);
        Partida partida = new Partida(player1Name, player2Name, inicioPartida, finPartida, winner);
        partidasDAO.savePartida(partida);
    }

    private static int playTurn(Scanner input, Player player, Table table, int turn, String nombre) {
        System.out.println(Table.openEnds());
        System.out.println(player.printHand());
        System.out.println(nombre + ": \n 1-Seleccionar pieza \n 2-Pasar");
        choice(input, player, table);
        turn++;
        return turn;
    }

    private static void choice(Scanner input, Player player, Table table){
        int choice;
        choice = input.nextInt();
        if(choice == 2){
            pass(player);
        }
        else if(choice == 1){
            place(input, player, table);
        }
        else{
            System.out.println("Elección inválida, el turno es concedido al siguiente jugador.");
        }
    }

    private static void pass(Player player){
        dominoPieces--;
        if(dominoPieces > 0){
            player.giveDomino();
            System.out.println("Se ha agarrado una pieza aleatoria de las restantes.");
        }
        else{
            System.out.println("No quedan más piezas disponibles.");
            canPlay = false;
        }
    }

    private static void place(Scanner input, Player player, Table table){
        System.out.println("Seleccione una pieza: ");
        int domino = input.nextInt();
        input.nextLine();
        domino--;
        try {
            String piece = "" + player.getDomino(domino);
            System.out.println("En qué lado de la tabla le gustaría posicionarla? Escriba 'izquierda' o 'derecha':");
            String side = input.nextLine();
            table.placePiece(side, piece, player, domino);
        } catch(Exception e){
            System.out.println("Elección inválida, el turno es concedido al siguiente jugador.");
        }
    }

    private static String winner(Player player1, Player player2, String nombreJ1, String nombreJ2){
        String winner = "";
        if(player1.getDominoCount() < player2.getDominoCount()){
            winner = nombreJ1;
            System.out.println(winner + " es el ganador!");
            System.out.println("Con: " + player1.getDominoCount() + " piezas!");
        } else if(player1.getDominoCount() > player2.getDominoCount()){
            winner = nombreJ2;
            System.out.println(winner + " es el ganador!");
            System.out.println("Con: " + player2.getDominoCount() + " piezas!");
        } else{
            winner = "Empate";
            System.out.println(winner);
        }
        return winner;
    }

}
