package co.edu.uniquindio;

public class semaforos {


    public void cantidadSemaforos(int nroJugadores) {

        int semaforosDisponibles;

        if (nroJugadores + 1 == 2) {
            semaforosDisponibles = 4;
        } else if (nroJugadores + 1 == 3) {
            semaforosDisponibles = 3;
        } else if (nroJugadores + 1 == 4 || nroJugadores + 1 == 5) {
            semaforosDisponibles = 2;
        } else {
            semaforosDisponibles = 1;
        }
    }



    public  int ponerSemaforos(int semaforosActuales, int cantidadSemaforos){

        semaforosActuales = semaforosActuales - cantidadSemaforos;

        return semaforosActuales;
    }

}