package co.edu.uniquindio;

import java.util.Comparator;

public class Jugador {

    private String nombre;
    private boolean jugadorHumano = true;
    private int semaforosDisponibles;
    private Vertice ubicacion;
    private Jugador siguiente;
    private int dado1 = lanzarDado();
    private  int dado2 = lanzarDado();
    private int sumaDados = dado1 + dado2;
    private boolean mision = false;
    private Carta carta;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public Jugador(String nombre, boolean maquina, int semaforosDisponibles) {
        this.nombre = nombre;
        this.jugadorHumano = maquina;
        this.semaforosDisponibles = semaforosDisponibles;
    }


    public Jugador(String nombre, boolean maquina, int semaforosDisponibles, Vertice ubicacion) {
        this.nombre = nombre;
        this.jugadorHumano = maquina;
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

    public int lanzarDado(){
            int random = (((int) (Math.random() * 100000.0)) % (6)) + 1;
            return random;

    }

    public int getDado1() {
        return dado1;
    }

    public void setDado1(int dado1) {
        this.dado1 = dado1;
    }

    public int getDado2() {
        return dado2;
    }

    public void setDado2(int dado2) {
        this.dado2 = dado2;
    }

    public int getSumaDados() {
        return sumaDados;
    }

    public void setSumaDados(int sumaDados) {
        this.sumaDados = sumaDados;
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


}
