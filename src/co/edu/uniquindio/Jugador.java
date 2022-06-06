package co.edu.uniquindio;

import java.util.Comparator;

public class Jugador {

    private String nombre;
    private boolean jugadorHumano;
    private int semaforosDisponibles;
    private Vertice ubicacion;
    private Jugador siguiente;
    private boolean mision = false;
    private Carta carta;
    private double puntosAcomulados = 0;


    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public Jugador(String nombre, boolean esHumano, int semaforosDisponibles) {
        this.nombre = nombre;
        this.jugadorHumano = esHumano;
        this.semaforosDisponibles = semaforosDisponibles;
    }


    public Jugador(String nombre, boolean esHumano, int semaforosDisponibles, Vertice ubicacion) {
        this.nombre = nombre;
        this.jugadorHumano = esHumano;
        this.semaforosDisponibles = semaforosDisponibles;
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isJugadorHumano() {
        return jugadorHumano;
    }

    public void setJugadorHumano(boolean jugadorHumano) {
        this.jugadorHumano = jugadorHumano;
    }

    public int getSemaforosDisponibles() {
        return semaforosDisponibles;
    }

    public void setSemaforosDisponibles(int semaforosDisponibles) {
        this.semaforosDisponibles = semaforosDisponibles;
    }

    public Vertice getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Vertice ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Jugador getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Jugador siguiente) {
        this.siguiente = siguiente;
    }

    public boolean isMision() {
        return mision;
    }

    public void setMision(boolean mision) {
        this.mision = mision;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public double getPuntosAcomulados() {
        return puntosAcomulados;
    }

    public void setPuntosAcomulados(double puntosAcomulados) {
        this.puntosAcomulados = puntosAcomulados;
    }
}
