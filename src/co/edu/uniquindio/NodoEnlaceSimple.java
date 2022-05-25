package co.edu.uniquindio;

public class NodoEnlaceSimple<T> implements Comparable<NodoEnlaceSimple<T>> {

    protected NodoEnlaceSimple<T> siguiente;
    protected T t;

    public NodoEnlaceSimple(T t) {
        this.t = t;
    }

    public NodoEnlaceSimple getSiguiente() {
        return siguiente;
    }

    protected void setSiguiente(NodoEnlaceSimple<T> item) {
        this.siguiente = item;
    }

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }

    public String toString() {
        return t.toString();
    }

    @Override
    public int compareTo(NodoEnlaceSimple<T> nodo) {
        if (this.t instanceof Comparable<?>) {
            Comparable<T> comparable = (Comparable<T>) this.t;
            return comparable.compareTo(nodo.get());
        } else {
            if (this.t == nodo.get()) {
                return 0;
            }
            return 1;
        }
    }
}
