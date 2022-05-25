package co.edu.uniquindio;

import java.util.ArrayList;

public class Vertice<T> implements Comparable<Vertice<T>> {

    private T t;
    private String nombre;
    private ArrayList<Arco> arcos;
    private double minDistance = Double.POSITIVE_INFINITY;
    private Vertice<T> previo;

    public Vertice(String nombre, T t) {
        this.t = t;
        this.nombre = nombre;
        arcos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarArco(Vertice<T> destino, double peso) {
        Vertice<T> verticeDestino = buscarDestino(destino.getNombre());
        if (verticeDestino == null) {
            arcos.add(new Arco(destino, peso));
        }
    }

    public void eliminarArco(String destino) {
        Arco arcoBuscado = null;
        for (Arco arco: arcos) {
            if (arco.getDestino().getNombre().equals(destino)) {
                arcoBuscado = arco;
            }
        }
        arcos.remove(arcoBuscado);
    }

    public Vertice<T> buscarDestino(String destino) {
        for (Arco arco: arcos) {
            if (arco.getDestino().getNombre().equals(destino)) {
                return arco.getDestino();
            }
        }
        return null;
    }

    public ArrayList<Arco> getArcos() {
        return arcos;
    }

    public boolean equals(Vertice vertice) {
        return nombre.equals(vertice.nombre);
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public Vertice<T> getPrevio() {
        return previo;
    }

    public void setPrevio(Vertice<T> previo) {
        this.previo = previo;
    }

    @Override
    public int compareTo(Vertice<T> vertice) {
        return Double.compare(this.getMinDistance(), vertice.getMinDistance());
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
