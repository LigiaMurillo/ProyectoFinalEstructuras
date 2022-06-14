package co.edu.uniquindio;

<<<<<<< HEAD
import java.util.*;
=======
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
>>>>>>> f1c29503fedcc99133532a2c74a7e823f3f30489

public class Main {

    public static void main(String args[]) {

        //clcular caminos es de distrak
        boolean finalizarJuego = false ;
        double catidadPuntosGanarJuego = 0;

        Scanner lectura = new Scanner (System.in);

        System.out.println("Defina la cantidad de puntos para ganar el Juego: ");
        catidadPuntosGanarJuego = Double.parseDouble(lectura.next());


        Grafo<String> matriz = new Grafo<>();
        inicializarMatriz(matriz);
        
        Pila pila = new Pila();
        inicializarPila(matriz, pila);

        System.out.println("Pila inicial");
        imprimirPila(pila);

        ListaSimpleCircularEnlazada<Jugador> listaJugadores = new ListaSimpleCircularEnlazada<>();
        inicializarJugadores(listaJugadores, matriz, pila);

        while (finalizarJuego == false) {

            for (Jugador jugador : listaJugadores) {
                   calcularPosiblesMovimientos(matriz, jugador, catidadPuntosGanarJuego, finalizarJuego, listaJugadores, pila);

            }
        }

        for (Jugador jugador : listaJugadores) {

            if (jugador.getPuntosAcomulados() >= catidadPuntosGanarJuego){
                   System.out.println("Felicidades el Jugador " + jugador.getNombre() + " a ganado el juego");
            }
        }
    }



    private static void calcularPosiblesMovimientos(Grafo<String> matriz, Jugador jugador, double catidadPuntosGanarJuego, boolean finalizarJuego, ListaSimpleCircularEnlazada<Jugador> listaJugadores, Pila pila) {

        Scanner lectura = new Scanner (System.in);
        ArrayList<Vertice> listaPosiblesMovimientos = new ArrayList<>();
        boolean camino = false;
        String verticeDestino;
        double pesoArco = 0.0;
        boolean moverSemaforo = false;
        int dado1 = lanzarDado();
        int dado2 = lanzarDado();
        double sumaDados = dado1 + dado2;
        boolean nuevoMovimiento = true;


        System.out.println("Jugador: " + jugador.getNombre());
        System.out.println("Esta en la posicion: " + jugador.getUbicacion().getNombre());
        System.out.println("Resultado dados: " + dado1 + ", " + dado2);


        System.out.println( jugador.getNombre() + ", ¿Deseas Mover o asignar un semaforo? ");
        moverSemaforo = Boolean.parseBoolean(lectura.next());

            if (moverSemaforo){
                moverSemaforo(matriz , jugador);
                if(dado1 > dado2){
                    sumaDados = dado2;
                }else if (dado2 > dado1){
                    sumaDados = dado2;
                }else {
                    sumaDados = dado1;
                }
            }


        //System.out.println(matriz.obtenerMatrizPesos());

        while (nuevoMovimiento == true){

            System.out.println("Los poibles nodos a los que puede ir el juagdor " + jugador.getNombre() + " son: ");

            for (Vertice vertice: matriz.getVertices() ) {

                camino = matriz.adyacente(jugador.getUbicacion().getNombre(), vertice.getNombre());

                //Con este metodo validamos la posicion de los demas jugadores y no dejamos que el sistema
                for (Jugador jugadorAux:listaJugadores) {
                    if (jugadorAux.getUbicacion() == vertice){
                        camino = false;
                    }
                }

                if(camino){

                    pesoArco = matriz.pesoEntreArcos(jugador.getUbicacion().getNombre(), vertice.getNombre());
                   if (sumaDados >= pesoArco){
                        listaPosiblesMovimientos.add(vertice);
                        System.out.println(vertice.getNombre() + " -> " + pesoArco);

                   }
                }
            }

            if(listaPosiblesMovimientos.size() == 0){
                System.out.println("Lo sentimos no puedes hacer movimientos");
                nuevoMovimiento = false;
                break;
            }



            System.out.println("Ingrese el nombre del nodo al que desea ir: ");
            verticeDestino = lectura.next();
            Vertice vertice = matriz.buscarVertice(verticeDestino);

            while (vertice == null || listaPosiblesMovimientos.contains(vertice) == false){
                System.out.println("Valor ingresado no es valido, por favor verifique la informacion");
                System.out.println("Ingrese el nombre del nodo al que desea ir: ");
                verticeDestino = lectura.next();
                vertice = matriz.buscarVertice(verticeDestino);
            }

            if(listaPosiblesMovimientos.contains(vertice)){

                jugador.setUbicacion(vertice);
                sumaDados = sumaDados - pesoArco;
            }

            if (jugador.getUbicacion() == jugador.getCarta().getMision()){
                jugador.setPuntosAcomulados(jugador.getCarta().getPuntos());
                jugador.setMision(false);
                jugador.setCarta(null);
                asignarMision(matriz, pila , jugador);
                if (jugador.getPuntosAcomulados() >= catidadPuntosGanarJuego) {
                    finalizarJuego = true;
                }
            }

            listaPosiblesMovimientos.clear();
        }

        dado1 = 0;
        dado2 = 0;
    }

    //Metodo para poner semaforos en un vertice

    private static void moverSemaforo(Grafo<String> matriz, Jugador jugador) {

        System.out.println("Poner o modificar semaforo: ");

        Arco arco = null , arco2 = null;

        if (jugador.getSemaforosDisponibles() == 0) {

            System.out.println("No tienes semaforos disponibles, ingresa los datos para buscar el semaforo que quieres cambiar: ");

            arco = capturarDatosArco(matriz);

            while (arco.getPesoModificado() == false){
                System.out.println("Por favor verifique la informacion!");
                arco2 = capturarDatosArco(matriz);
            }

            System.out.println("Ingrese la ubicacion donde quiere cambiar el semaforo:");
            arco2 = capturarDatosArco(matriz);

            while (arco2 == null){
                System.out.println("Por favor verifique la informacion!");
                arco2 = capturarDatosArco(matriz);
            }
            arco.setPeso(arco.getPeso()-2);
            arco.setPesoModificado(false);
            arco2.setPeso(arco2.getPeso()+2);
            arco2.setPesoModificado(true);


        } else if (jugador.getSemaforosDisponibles() > 0) {

            while (arco == null ){
                System.out.println("Por favor verifique la informacion!");
                arco = capturarDatosArco(matriz);
            }

            arco.setPeso(arco.getPeso()+2);
            arco.setPesoModificado(true);
            jugador.setSemaforosDisponibles(jugador.getSemaforosDisponibles()-1);

        }

        //System.out.println("moviendo semaforo...");
    }

    private static Arco capturarDatosArco(Grafo matriz) {

        Arco arcoAux;
        Scanner lectura = new Scanner(System.in);
        System.out.println("Ingrese el origen");
        String semaforoOrigen = lectura.next();
        System.out.println("Ingrese el destino");
        String semaforoDestino = lectura.next();

        return matriz.bucarArco(semaforoOrigen, semaforoDestino);
    }

    private static void imprimirPila(Pila pila) {

        System.out.println("Lista Cartas");
        for (Carta carta: pila) {
            System.out.println(carta.getMision());

        }
    }

    public static int lanzarDado(){
        int random = (((int) (Math.random() * 100000.0)) % (6)) + 1;
        return random;

    }


    private static void asignarMision(Grafo<String> matriz, Pila pila, Jugador jugador) {

        Scanner lectura = new Scanner (System.in);

        int misionesRechazadas = 0;
        Carta carta = pila.quitar();
        boolean aceptarMision = false;


        while (misionesRechazadas < 2 ){

            System.out.println("La mision que ha tomado " + jugador.getNombre() + " es: " + carta.getMision().getNombre());
            System.out.println("¿Acepta la mision?");
            aceptarMision = Boolean.parseBoolean(lectura.next());

            if(aceptarMision){
                jugador.setCarta(carta);
                jugador.setMision(true);
                break;
            }

            misionesRechazadas ++;
            carta = pila.quitar();


        }

        if( aceptarMision == false){

            jugador.setCarta(carta);
            jugador.setMision(true);
            System.out.println("Ya no puede rechazar las misiones, su mision asignada es : " + jugador.getCarta().getMision().getNombre());
            System.out.println();

        }
    }

    private static void inicializarJugadores(ListaSimpleCircularEnlazada<Jugador> listaJugadores, Grafo matriz, Pila pila) {


        Scanner lectura = new Scanner (System.in);
        System.out.println("Ingrese el numero de Jugadores humanos: ");
        String nroJugadores  = lectura.next();

        while (Integer.parseInt(nroJugadores) <= 0 || Integer.parseInt(nroJugadores) >5){
            System.out.println("El número de jugadores debe de ser mayo a 0 y menor que 6");
            System.out.println("Ingrese el numero de Jugadores humanos: ");
            nroJugadores  = lectura.next();
        }

        ArrayList<Vertice> posicionesIniciales = new ArrayList<>();
        posicionesIniciales.add(matriz.buscarVertice("Verde1"));
        posicionesIniciales.add(matriz.buscarVertice("Amarillo1"));
        posicionesIniciales.add(matriz.buscarVertice("Naranja1"));
        posicionesIniciales.add(matriz.buscarVertice("Rojo1"));
        posicionesIniciales.add(matriz.buscarVertice("Violeta1"));
        posicionesIniciales.add(matriz.buscarVertice("Azul1"));

        Collections.shuffle(posicionesIniciales);

        int aux = 0;
        String nombre;
        boolean jugadorHumano = true;
        int semaforosDisponibles;
        Vertice ubicacion = posicionesIniciales.get(aux);
        int dado1 = 0;
        int dado2 = 0;
        int sumaDados;


        //La maquina siempre juega por defcto, por eso se suma 1 siempre
        if(Integer.parseInt(nroJugadores)+1 == 2){
            semaforosDisponibles = 4;
        }else if (Integer.parseInt(nroJugadores)+1 == 3){
            semaforosDisponibles = 3;
        }else if (Integer.parseInt(nroJugadores)+1 == 4 || Integer.parseInt(nroJugadores)+1 == 5){
            semaforosDisponibles = 2;
        }else {
            semaforosDisponibles = 1;
        }


        for(int i = 1 ; i <= Integer.parseInt(nroJugadores) ; i++){

<<<<<<< HEAD
            System.out.print("Ingrese nombre del Jugador " + i + " : ");
=======

            System.out.print("Ingrese nombre del Jugador " + i + ": ");
>>>>>>> f1c29503fedcc99133532a2c74a7e823f3f30489
            nombre = lectura.next();
            ubicacion = posicionesIniciales.get(aux);
            listaJugadores.add(new Jugador(nombre, jugadorHumano, semaforosDisponibles, ubicacion));
            System.out.println("El jugador " + nombre + ", esta en la posicion " + ubicacion + " y tiene disponible " + semaforosDisponibles + " semaforos");
            aux++;

        }

        aux++;
        listaJugadores.add(new Jugador("Maquina", false, semaforosDisponibles, ubicacion));

       /* for (Jugador jugador: listaJugadores){
                System.out.println("El jugador " + jugador.getNombre());

        }*/

        for (Jugador jugador: listaJugadores){

            if (jugador.isMision() == false){
                asignarMision(matriz , pila , jugador);
                System.out.println("El jugador " + jugador.getNombre() + ", esta en la posicion " + jugador.getUbicacion().getNombre());
                System.out.println("y su mision es ir a " + jugador.getCarta().getMision().getNombre());
                System.out.println();
            }

        }
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

        //System.out.println(matriz);

        //System.out.println(matriz.rutaMasCorta(matriz.buscarVertice("Centro1"), matriz.buscarVertice("Centro3")));

    }
}
