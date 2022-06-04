package co.edu.uniquindio;

import java.util.Iterator;

public class Pila implements Iterable<Carta>  {

    private Carta cabeza;

    public Pila() {
    }

    public Carta getCabeza() {
        return cabeza;
    }

    public void setCabeza(Carta cabeza) {
        this.cabeza = cabeza;
    }

    public Carta obtenerAnterior(Carta carta) {

        Carta actual = cabeza;
        if (cabeza == null) {
            return null;
        }

        if (carta == this.cabeza){
            return  null;
        }
        else {


            while(actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            //actual.setSiguiente(carta);

        }
        return actual;
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

        //codigo añadido
        actual.setSiguiente(this.cabeza);
        this.cabeza = actual;


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

    public Iterator<Carta> iterator() {
        // return new IteradorNodoEnlaceSimple<>(cabeza);
        Iterator<Carta> it = new Iterator<>() {

            private Carta current = cabeza;
            private boolean isHead = true;

            @Override
            public boolean hasNext() {
                if (this.current == cabeza && isHead) {
                    isHead = false;
                    return true;
                }
                else if (this.current != null && this.current.getSiguiente() != cabeza) {
                    return true;
                }
                return false;
            }

            @Override
            public Carta next() {
                Carta current = this.current;
                this.current = this.current.getSiguiente();
                return current;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }


}
