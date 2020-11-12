package com.domino.app.models;

import javax.persistence.*;

@Entity
@Table(name = "partidas")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partida")
    private int partidaId;

    private String jugador1;

    private String jugador2;

    @Column(name = "inicio_partida")
    private String inicioPartida;

    @Column(name = "fin_partida")
    private String finPartida;

    private String ganador;

    public Partida(){

    }

    public Partida(String jugador1, String jugador2, String inicioPartida, String finPartida, String ganador) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.inicioPartida = inicioPartida;
        this.finPartida = finPartida;
        this.ganador = ganador;
    }

    public int getPartidaId() {
        return partidaId;
    }

    public String getJugador1() {
        return jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public String getInicioPartida() {
        return inicioPartida;
    }

    public String getFinPartida() {
        return finPartida;
    }

    public String getGanador() {
        return ganador;
    }

}
