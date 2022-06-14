package co.edu.uniquindio;
import java.util.Random;

public class Dados {

// Metodo para generar el numero del dado 1 de manera aleatoria

    public int generadorDados1(int min, int max) {
        Random random = new Random();
       int dado1 = random.nextInt(min, max);
       return dado1;
    }
    // Metodo para generar el numero del dado 2 de manera aleatoria

    public int generadorDados2(int min, int max) {
        Random random = new Random();
        int dado2 = random.nextInt(min, max);
        return dado2;
    }

    //Metodo que suma los resultados de los dos dados

    public int generadorTotal(int resultadoDado1, int resultadoDado2) {
        Random random = new Random();
        int total = resultadoDado1 + resultadoDado2;
        return total;
    }


    //Metodo que selecciona el color de acuerdo al resultado del primer dado

    public static String pinta(int color) {

        String colorCelda;

        switch (color) {
            case 1:
                colorCelda = "Rojo";
                break;
            case 2:
                colorCelda = "Amarillo";
                break;
            case 3:
                colorCelda = "Azul";
                break;
            case 4:
                colorCelda = "Verde";
                break;
            case 5:
                colorCelda = "Violeta";
                break;
            case 6:
                colorCelda = "Naranja";
                break;
            case 7:
                colorCelda = "Centro";
                break;
            default:
                colorCelda = "Color No valido";
                break;
        }


        return colorCelda;
    }




}
