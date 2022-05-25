package co.edu.uniquindio;

public class Jugador {

    private String nombre;
    private boolean jugadorHumano = true;
    private int semaforosDisponibles;
    private Vertice ubicacion;
    private Jugador siguiente;

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
}
