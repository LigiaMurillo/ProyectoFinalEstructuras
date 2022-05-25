package co.edu.uniquindio;

public class Arco {

    private Vertice destino;
    private double peso;

    public Arco(Vertice destino) {
        this.destino = destino;
        this.peso = 0.0;
    }

    public Arco(Vertice destino, double peso) {
        this(destino);
        this.peso = peso;
    }

    public Vertice getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public boolean equals(Object n) {
        Arco a = (Arco) n;
        return destino == a.destino;
    }
}