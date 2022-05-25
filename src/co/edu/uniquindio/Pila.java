package co.edu.uniquindio;

public class Pila {

    private Carta cabeza;

    public Pila() {
    }

    public void insertar(Carta nuevo) {
        if (cabeza == null) {
            cabeza = nuevo;
        }
        else {
            Carta actual = cabeza;

            while(actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public Carta quitar() {
        Carta anterior = null;
        Carta actual = cabeza;

        if (cabeza.getSiguiente() == null) {
            cabeza = null;
            return actual;
        }

        while(actual.getSiguiente() != null) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        anterior.setSiguiente(null);
        return actual;
    }

    public boolean esVacia() {
        return cabeza == null;
    }

    public void limpiar() {
        cabeza = null;
    }

    public int size() {
        int valor = 0;
        if (cabeza != null) {
            Carta actual = cabeza;
            while(actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
                valor ++;
            }
        }
        return valor;
    }

}
