package co.edu.uniquindio;

public class Arco {

    private Vertice destino;
    private double peso;
    /*
    Esta variable se añade con el fin de que con esta se pongan y modifiquen
    los semaforos a fin de que este no sea modificado si aun continene su peso inicial
     */
    private boolean pesoModificado;

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

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean getPesoModificado() {
        return pesoModificado;
    }

    public void setPesoModificado(boolean pesoModificado) {
        this.pesoModificado = pesoModificado;
    }
}