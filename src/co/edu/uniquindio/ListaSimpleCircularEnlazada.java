package co.edu.uniquindio;

import java.util.Iterator;

public class ListaSimpleCircularEnlazada<T> implements Iterable<Jugador> {

    private Jugador cabeza;

    public ListaSimpleCircularEnlazada() {
    }

    public ListaSimpleCircularEnlazada(Jugador cabeza) {
        this.add(cabeza);
    }

    public void add(Jugador item) {
        if (this.cabeza == null) {
            this.cabeza = item;
            this.cabeza.setSiguiente(this.cabeza);
        }
        else {
            Jugador ultimo = obtenerUltimo();
            if (ultimo != null) {
                ultimo.setSiguiente(item);
                item.setSiguiente(this.cabeza);
            }
        }
    }

    public void add(Jugador item, int index) {
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
                Jugador current = this.cabeza;
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

    public void addAsHead(Jugador item) {
        if (this.cabeza == null) {
            this.add(item);
        }
        else {
            Jugador ultimo = this.obtenerUltimo();
            ultimo.setSiguiente(item);
            item.setSiguiente(this.cabeza);
            this.cabeza = item;
        }
    }

    public  Jugador obtenerCabeza(){
        Jugador next = this.cabeza;
        return next;
    }

    public Jugador obtenerUltimo() {
        Jugador next = this.cabeza;
        while (next != null) {
            if (next.getSiguiente() == (this.cabeza)) {
                return next;
            }
            next = next.getSiguiente();
        }
        return null;
    }

    public int lenght() {
        int resultado = 0;
        for (Iterator<Jugador> it = this.iterator(); it.hasNext(); ) {
            Jugador current = it.next();
            resultado ++;
        }
        return resultado;
    }

    public Jugador remove(Jugador jugador) {
        if (cabeza == null) {
            return null;
        }
        Jugador previous = null;
        Jugador current = this.cabeza;
        Jugador next = this.cabeza.getSiguiente();

        // If the list have just one item.
        if (current == next) {
            this.cabeza = null;
            return current;
        }

        while(next != this.cabeza) {
            if (current.getNombre().equals(jugador.getNombre())) {
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
        Jugador next = this.cabeza;
        String result = "";
        while (next != null) {
            result += ", " + next.toString();
            next = next.getSiguiente();
        }

        return result;
    }

    public Iterator<Jugador> iterator() {
        // return new IteradorNodoEnlaceSimple<>(cabeza);
        Iterator<Jugador> it = new Iterator<>() {

            private Jugador current = cabeza;
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
            public Jugador next() {
                Jugador current = this.current;
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
