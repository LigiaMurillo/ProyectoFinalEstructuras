package co.edu.uniquindio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {

        Grafo<String> matriz = new Grafo<>();
        inicializarMatriz(matriz);
        
        Pila pila = new Pila();
        inicializarPila(matriz, pila);

        ListaSimpleCircularEnlazada<NodoEnlaceSimple> listaJugadores = new ListaSimpleCircularEnlazada<>();
        inicializarJugadores(listaJugadores);


    }

    private static void inicializarJugadores(ListaSimpleCircularEnlazada<NodoEnlaceSimple> listaJugadores) {


        Scanner lectura = new Scanner (System.in);
        System.out.println("Ingrese el numero de Jugadores humanos: ");
        String nroJugadores  = lectura.next();

        while (Integer.parseInt(nroJugadores) <= 0 || Integer.parseInt(nroJugadores) >5){
            System.out.println("El número de jugadores debe de ser mayo a 0 y menor que 6");
            System.out.println("Ingrese el numero de Jugadores humanos: ");
            nroJugadores  = lectura.next();
        }

        String nombre = "Maquina";
        boolean jugadorHumano = false;
        int semaforosDisponibles;
        int aux=1;

        if(Integer.parseInt(nroJugadores)+1 == 2){
            semaforosDisponibles = 4;
        }else if (Integer.parseInt(nroJugadores)+1 == 3){
            semaforosDisponibles = 3;
        }else if (Integer.parseInt(nroJugadores)+1 == 4 || Integer.parseInt(nroJugadores)+1 == 5){
            semaforosDisponibles = 2;
        }else {
            semaforosDisponibles = 1;
        }

        do {

            listaJugadores.add(new NodoEnlaceSimple<Jugador>(new Jugador(nombre, jugadorHumano, semaforosDisponibles)));
            System.out.println("Ingrese nombre del Jugador: ");
            nombre = lectura.nextLine();
            jugadorHumano = true;
            aux ++;

        }while (aux <= Integer.parseInt(nroJugadores));





    }

    private static void inicializarPila(Grafo<String> matriz, Pila pila) {
        ArrayList<Carta> listaCartas = new ArrayList<>();

       listaCartas.add(new Carta(matriz.buscarVertice("Centro1"), 4.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Centro2"), 4.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Centro3"), 4.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Centro4"), 4.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Centro5"), 4.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Centro6"), 4.0));

       listaCartas.add(new Carta(matriz.buscarVertice("Azul1"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Azul2"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Azul3"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Azul4"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Azul5"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Azul6"), 3.0));

       listaCartas.add(new Carta(matriz.buscarVertice("Verde1"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Verde2"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Verde3"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Verde4"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Verde5"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Verde6"), 3.0));

       listaCartas.add(new Carta(matriz.buscarVertice("Amarillo1"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Amarillo2"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Amarillo3"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Amarillo4"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Amarillo5"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Amarillo6"), 3.0));

       listaCartas.add(new Carta(matriz.buscarVertice("Naranja1"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Naranja2"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Naranja3"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Naranja4"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Naranja5"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Naranja6"), 3.0));

       listaCartas.add(new Carta(matriz.buscarVertice("Rojo1"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Rojo2"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Rojo3"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Rojo4"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Rojo5"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Rojo6"), 3.0));

       listaCartas.add(new Carta(matriz.buscarVertice("Violeta1"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Violeta2"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Violeta3"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Violeta4"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Violeta5"), 3.0));
       listaCartas.add(new Carta(matriz.buscarVertice("Violeta6"), 3.0));


       //Metodo para mexclar las cartas
        Collections.shuffle(listaCartas);

        for (Carta carta: listaCartas) {
            pila.insertar(carta);
        }

    }

    private static void  inicializarMatriz( Grafo<String> matriz) {

        //Creando Vertices

        matriz.agregarVertice("Centro1", "Contenido generico del vertice");
        matriz.agregarVertice("Centro2", "Contenido generico del vertice");
        matriz.agregarVertice("Centro3", "Contenido generico del vertice");
        matriz.agregarVertice("Centro4", "Contenido generico del vertice");
        matriz.agregarVertice("Centro5", "Contenido generico del vertice");
        matriz.agregarVertice("Centro6", "Contenido generico del vertice");

        matriz.agregarVertice("Azul1", "Contenido generico del vertice");
        matriz.agregarVertice("Azul2", "Contenido generico del vertice");
        matriz.agregarVertice("Azul3", "Contenido generico del vertice");
        matriz.agregarVertice("Azul4", "Contenido generico del vertice");
        matriz.agregarVertice("Azul5", "Contenido generico del vertice");
        matriz.agregarVertice("Azul6", "Contenido generico del vertice");

        matriz.agregarVertice("Verde1", "Contenido generico del vertice");
        matriz.agregarVertice("Verde2", "Contenido generico del vertice");
        matriz.agregarVertice("Verde3", "Contenido generico del vertice");
        matriz.agregarVertice("Verde4", "Contenido generico del vertice");
        matriz.agregarVertice("Verde5", "Contenido generico del vertice");
        matriz.agregarVertice("Verde6", "Contenido generico del vertice");

        matriz.agregarVertice("Amarillo1", "Contenido generico del vertice");
        matriz.agregarVertice("Amarillo2", "Contenido generico del vertice");
        matriz.agregarVertice("Amarillo3", "Contenido generico del vertice");
        matriz.agregarVertice("Amarillo4", "Contenido generico del vertice");
        matriz.agregarVertice("Amarillo5", "Contenido generico del vertice");
        matriz.agregarVertice("Amarillo6", "Contenido generico del vertice");

        matriz.agregarVertice("Naranja1", "Contenido generico del vertice");
        matriz.agregarVertice("Naranja2", "Contenido generico del vertice");
        matriz.agregarVertice("Naranja3", "Contenido generico del vertice");
        matriz.agregarVertice("Naranja4", "Contenido generico del vertice");
        matriz.agregarVertice("Naranja5", "Contenido generico del vertice");
        matriz.agregarVertice("Naranja6", "Contenido generico del vertice");

        matriz.agregarVertice("Rojo1", "Contenido generico del vertice");
        matriz.agregarVertice("Rojo2", "Contenido generico del vertice");
        matriz.agregarVertice("Rojo3", "Contenido generico del vertice");
        matriz.agregarVertice("Rojo4", "Contenido generico del vertice");
        matriz.agregarVertice("Rojo5", "Contenido generico del vertice");
        matriz.agregarVertice("Rojo6", "Contenido generico del vertice");

        matriz.agregarVertice("Violeta1", "Contenido generico del vertice");
        matriz.agregarVertice("Violeta2", "Contenido generico del vertice");
        matriz.agregarVertice("Violeta3", "Contenido generico del vertice");
        matriz.agregarVertice("Violeta4", "Contenido generico del vertice");
        matriz.agregarVertice("Violeta5", "Contenido generico del vertice");
        matriz.agregarVertice("Violeta6", "Contenido generico del vertice");

        //Creando Arcos
        matriz.nuevoArco("Centro1", "Centro2", 3.0);
        matriz.nuevoArco("Centro2", "Centro3", 3.0);
        matriz.nuevoArco("Centro3", "Centro4", 3.0);
        matriz.nuevoArco("Centro4", "Centro5", 3.0);
        matriz.nuevoArco("Centro5", "Centro6", 3.0);
        matriz.nuevoArco("Centro6", "Centro1", 3.0);

        matriz.nuevoArco("Azul1", "Azul2", 2.0);
        matriz.nuevoArco("Azul2", "Azul3", 2.0);
        matriz.nuevoArco("Azul3", "Azul4", 2.0);
        matriz.nuevoArco("Azul4", "Azul5", 2.0);
        matriz.nuevoArco("Azul5", "Azul6", 2.0);
        matriz.nuevoArco("Azul6", "Azul1", 2.0);

        matriz.nuevoArco("Verde1", "Verde2", 2.0);
        matriz.nuevoArco("Verde2", "Verde3", 2.0);
        matriz.nuevoArco("Verde3", "Verde4", 2.0);
        matriz.nuevoArco("Verde4", "Verde5", 2.0);
        matriz.nuevoArco("Verde5", "Verde6", 2.0);
        matriz.nuevoArco("Verde6", "Verde1", 2.0);

        matriz.nuevoArco("Amarillo1", "Amarillo2", 2.0);
        matriz.nuevoArco("Amarillo2", "Amarillo3", 2.0);
        matriz.nuevoArco("Amarillo3", "Amarillo4", 2.0);
        matriz.nuevoArco("Amarillo4", "Amarillo5", 2.0);
        matriz.nuevoArco("Amarillo5", "Amarillo6", 2.0);
        matriz.nuevoArco("Amarillo6", "Amarillo1", 2.0);

        matriz.nuevoArco("Naranja1", "Naranja2", 2.0);
        matriz.nuevoArco("Naranja2", "Naranja3", 2.0);
        matriz.nuevoArco("Naranja3", "Naranja4", 2.0);
        matriz.nuevoArco("Naranja4", "Naranja5", 2.0);
        matriz.nuevoArco("Naranja5", "Naranja6", 2.0);
        matriz.nuevoArco("Naranja6", "Naranja1", 2.0);

        matriz.nuevoArco("Rojo1", "Rojo2", 2.0);
        matriz.nuevoArco("Rojo2", "Rojo3", 2.0);
        matriz.nuevoArco("Rojo3", "Rojo4", 2.0);
        matriz.nuevoArco("Rojo4", "Rojo5", 2.0);
        matriz.nuevoArco("Rojo5", "Rojo6", 2.0);
        matriz.nuevoArco("Rojo6", "Rojo1", 2.0);

        matriz.nuevoArco("Violeta1", "Violeta2", 2.0);
        matriz.nuevoArco("Violeta2", "Violeta3", 2.0);
        matriz.nuevoArco("Violeta3", "Violeta4", 2.0);
        matriz.nuevoArco("Violeta4", "Violeta5", 2.0);
        matriz.nuevoArco("Violeta5", "Violeta6", 2.0);
        matriz.nuevoArco("Violeta6", "Violeta1", 2.0);

        //Arcos que conectan los hexagonos

        matriz.nuevoArco("Verde5","Azul3",1.0);
        matriz.nuevoArco("Amarillo5","Verde3",1.0);
        matriz.nuevoArco("Naranja5","Amarillo3",1.0);
        matriz.nuevoArco("Rojo5","Naranja3",1.0);
        matriz.nuevoArco("Violeta5","Rojo3",1.0);
        matriz.nuevoArco("Azul5","Violeta3",1.0);

        //Arcos Bidireccionales
        matriz.nuevoArco("Centro1","Verde4",1.0);
        matriz.nuevoArco("Verde4","Centro1",1.0);
        matriz.nuevoArco("Centro6","Azul4",1.0);
        matriz.nuevoArco("Azul4","Centro6",1.0);
        matriz.nuevoArco("Amarillo4","Centro2",1.0);
        matriz.nuevoArco("Centro2","Amarillo4",1.0);
        matriz.nuevoArco("Naranja4","Centro3",1.0);
        matriz.nuevoArco("Centro3","Naranja4",1.0);
        matriz.nuevoArco("Centro4","Rojo4",1.0);
        matriz.nuevoArco("Rojo4","Centro4",1.0);
        matriz.nuevoArco("Centro5","Violeta4",1.0);
        matriz.nuevoArco("Violeta4","Centro5",1.0);
        matriz.nuevoArco("Centro5","Violeta4",1.0);

        System.out.println(matriz);

        //System.out.println(matriz.rutaMasCorta(matriz.buscarVertice("Centro1"), matriz.buscarVertice("Centro3")));

    }
}
