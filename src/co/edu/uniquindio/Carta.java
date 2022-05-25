package co.edu.uniquindio;

public class Carta {

    Vertice mision;
    double puntos;
    Carta siguiente;

    public Carta(Vertice mision, double puntos) {
        this.mision = mision;
        this.puntos = puntos;
    }

    public Vertice getMision() {
        return mision;
    }

    public void setMision(Vertice mision) {
        this.mision = mision;
    }

    public double getPuntos() {
        return puntos;
    }

    public void setPuntos(double puntos) {
        this.puntos = puntos;
    }

    public Carta getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Carta siguiente) {
        this.siguiente = siguiente;
    }
}
