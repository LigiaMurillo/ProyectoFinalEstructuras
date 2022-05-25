package co.edu.uniquindio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Grafo<T> {

    private ArrayList<Vertice> vertices;

    public Grafo() {
        vertices = new ArrayList<>();
    }

    public void agregarVertice(String nombre, T t) {
        if (buscarVertice(nombre) == null) {
            vertices.add(new Vertice(nombre, t));
        }
    }

    public Vertice buscarVertice(String nombre) {
        for (Vertice vertice: vertices) {
            if (vertice.getNombre().equals(nombre)) {
                return vertice;
            }
        }
        return null;
    }

    public void eliminarVertice(String nombre) {
        Vertice<T> verticeOrigen = buscarVertice(nombre);
        if (verticeOrigen == null) {
            return;
        }
        for (Vertice<T> vertice:vertices) {
            // Eliminamos todos los arcos donde el vertice a borrar sea un destino.
            vertice.eliminarArco(verticeOrigen.getNombre());
        }
        // Finalmente eliminamos el vertice.
        vertices.remove(verticeOrigen);
    }

    public void nuevoArco(String origen, String destino, double peso) {
        Vertice<T> verticeOrigen = buscarVertice(origen);
        Vertice<T> verticeDestino = buscarVertice(destino);

        if (verticeOrigen != null && verticeDestino != null) {
            verticeOrigen.agregarArco(verticeDestino, peso);
        }
    }

    public void eliminarArco(String origen, String destino) {
        Vertice<T> verticeOrigen = buscarVertice(origen);
        if (verticeOrigen != null) {
            verticeOrigen.eliminarArco(destino);
        }
    }

    public boolean adyacente(String origen, String destino) {
        Vertice<T> verticeOrigen = buscarVertice(origen);

        if (verticeOrigen != null) {
            return verticeOrigen.buscarDestino(destino) != null;
        }

        return false;
    }

    public double pesoEntreArcos(String origen, String destino) {
        Vertice<T> verticeOrigen = buscarVertice(origen);

        if (verticeOrigen != null) {
            Vertice<T> verticeDestino = verticeOrigen.buscarDestino(destino);
            for (Arco arco: verticeOrigen.getArcos()) {
                if (arco.getDestino().equals(verticeDestino)) {
                    return arco.getPeso();
                }
            }
        }

        return 0.0;
    }

    public int[][] obtenerMatrizAdyacencia() {
        int[][] matriz = new int[vertices.size()][vertices.size()];
        for (int h = 0; h < vertices.size(); h++) {
            Vertice<T> verticeY = vertices.get(h);
            for (int w = 0; w < vertices.size(); w++) {
                Vertice<T> verticeX = vertices.get(w);
                if (adyacente(verticeY.getNombre(), verticeX.getNombre())) {
                    matriz[h][w] = 1;
                }
                else {
                    matriz[h][w] = 0;
                }
            }
        }

        return matriz;
    }

    public double[][] obtenerMatrizPesos() {
        double[][] matriz = new double[vertices.size()][vertices.size()];
        for (int h = 0; h < vertices.size(); h++) {
            Vertice<T> verticeY = vertices.get(h);
            for (int w = 0; w < vertices.size(); w++) {
                Vertice<T> verticeX = vertices.get(w);
                if (adyacente(verticeY.getNombre(), verticeX.getNombre())) {
                    matriz[h][w] = pesoEntreArcos(verticeY.getNombre(), verticeX.getNombre());
                }
                else {
                    matriz[h][w] = 0.0;
                }
            }
        }

        return matriz;
    }

    public String toString() {
        String vertices = "V = {";
        for (Vertice<T> vertice: this.vertices) {
            vertices += " \"" + vertice.getNombre() + "\",";
        }
        vertices += "}\n";

        String adyacencia = "A = {\n";
        for (int[] row: obtenerMatrizAdyacencia()) {
            adyacencia += "\t[";
            for (int item: row) {
                adyacencia += item + ",";
            }
            adyacencia += "]\n";
        }
        adyacencia += "}\n";

        String pesos = "P = {\n";
        for (double[] row: obtenerMatrizPesos()) {
            pesos += "\t[";
            for (double item: row) {
                pesos += item + ", ";
            }
            pesos += "]\n";
        }
        pesos += "}\n";

        return vertices + adyacencia + pesos;
    }

    public int [][] matrizCaminos() {
        int n = vertices.size();
        int [][] caminos = obtenerMatrizAdyacencia();

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    caminos[i][j] = Math.min(caminos[i][j] + caminos[i][k] * caminos[k][j], 1);
                }
            }
        }
        return caminos;
    }

    public int getVerticeIndice(String origen) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getNombre().equals(origen)) {
                return i;
            }
        }
        return -1;
    }

    public List<Vertice<T>> rutaMasCorta(Vertice<T> origen, Vertice<T> destino) {
        resetMinDistanceEnVertices();
        calcularCaminos(origen);
        return obtenerCaminoMasCorto(destino);
    }

    private void resetMinDistanceEnVertices() {
        for (Vertice<T> vertice: vertices) {
            vertice.setMinDistance(Double.POSITIVE_INFINITY);
            vertice.setPrevio(null);
        }
    }

    private void calcularCaminos(Vertice<T> source) {
        source.setMinDistance(0.0);
        PriorityQueue<Vertice<T>> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertice<T> poll = vertexQueue.poll();

            // Visit each edge exiting u
            for (Object element : poll.getArcos()) {
                Arco arco = (Arco) element;
                Vertice<T> v = arco.getDestino();
                double weight = arco.getPeso();
                double distanceThroughU = poll.getMinDistance() + weight;
                if (distanceThroughU < v.getMinDistance()) {
                    vertexQueue.remove(v);

                    v.setMinDistance(distanceThroughU);
                    v.setPrevio(poll);
                    vertexQueue.add(v);
                }
            }
        }
    }

    private List<Vertice<T>> obtenerCaminoMasCorto(Vertice<T> target) {
        List<Vertice<T>> path = new ArrayList<>();
        for (Vertice<T> vertex = target; vertex != null; vertex = vertex.getPrevio()) {
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }

}
