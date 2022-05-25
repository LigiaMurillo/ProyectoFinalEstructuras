package co.edu.uniquindio;

import java.util.Iterator;

public class ListaSimpleCircularEnlazada<T> implements Iterable<NodoEnlaceSimple<T>> {

    private NodoEnlaceSimple<T> cabeza;

    public ListaSimpleCircularEnlazada() {
    }

    public ListaSimpleCircularEnlazada(NodoEnlaceSimple<T> cabeza) {
        this.add(cabeza);
    }

    public void add(NodoEnlaceSimple<T> item) {
        if (this.cabeza == null) {
            this.cabeza = item;
            this.cabeza.setSiguiente(this.cabeza);
        }
        else {
            NodoEnlaceSimple<T> ultimo = obtenerUltimo();
            if (ultimo != null) {
                ultimo.setSiguiente(item);
                item.setSiguiente(this.cabeza);
            }
        }
    }

    public void add(NodoEnlaceSimple<T> item, int index) {
        if (this.cabeza == null) {
            add(item);
        }
        else {
            if (index == 0) {
                addAsHead(item);
            }
            else {
                int lenght = lenght();
                if (index > lenght) {
                    return;
                }
                int i = 0;
                NodoEnlaceSimple<T> current = this.cabeza;
                while(i < lenght) {
                    if (i == index) {
                        item.setSiguiente(current.getSiguiente());
                        current.setSiguiente(item);
                    }
                    current = current.getSiguiente();
                    i ++;
                }

                /* index = lenght() % index;
                NodoEnlaceSimple<T> current = this.cabeza;
                for (int i = 0; i <= index; i++) {
                    if (i == index) {
                        item.setSiguiente(current.getSiguiente());
                        current.setSiguiente(item);
                        break;
                    }
                    current = current.getSiguiente();
                } */
            }
        }
    }

    public void addAsHead(NodoEnlaceSimple<T> item) {
        if (this.cabeza == null) {
            this.add(item);
        }
        else {
            NodoEnlaceSimple<T> ultimo = this.obtenerUltimo();
            ultimo.setSiguiente(item);
            item.setSiguiente(this.cabeza);
            this.cabeza = item;
        }
    }

    public NodoEnlaceSimple<T> obtenerUltimo() {
        NodoEnlaceSimple next = this.cabeza;
        while (next != null) {
            if (next.getSiguiente().equals(this.cabeza)) {
                return next;
            }
            next = next.getSiguiente();
        }
        return null;
    }

    public int lenght() {
        int resultado = 0;
        for (Iterator<NodoEnlaceSimple<T>> it = this.iterator(); it.hasNext(); ) {
            NodoEnlaceSimple<T> current = it.next();
            resultado ++;
        }
        return resultado;
    }

    public NodoEnlaceSimple<T> remove(T t) {
        if (cabeza == null) {
            return null;
        }
        NodoEnlaceSimple<T> previous = null;
        NodoEnlaceSimple<T> current = this.cabeza;
        NodoEnlaceSimple<T> next = this.cabeza.getSiguiente();

        // If the list have just one item.
        if (current == next) {
            this.cabeza = null;
            return current;
        }

        while(next != this.cabeza) {
            if (current.get().equals(t)) {
                previous.setSiguiente(next);
                current.setSiguiente(null);
                return current;
            }
            previous = current;
            current = next;
            next = next.getSiguiente();
        }

        return null;
    }

    public String toString() {
        NodoEnlaceSimple<T> next = this.cabeza;
        String result = "";
        while (next != null) {
            result += ", " + next.get().toString();
            next = next.getSiguiente();
        }

        return result;
    }

    public Iterator<NodoEnlaceSimple<T>> iterator() {
        // return new IteradorNodoEnlaceSimple<>(cabeza);
        Iterator<NodoEnlaceSimple<T>> it = new Iterator<>() {

            private NodoEnlaceSimple<T> current = cabeza;
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
            public NodoEnlaceSimple<T> next() {
                NodoEnlaceSimple<T> current = this.current;
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
