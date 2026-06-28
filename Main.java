import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        double[] temperaturas = new double[30];
        int opcion;

        do {
            opcion = mostrarMenu();

            switch (opcion) {
                case 1:
                    introducirTemperaturas(temperaturas);
                    break;

                case 2:
                    mostrarEstadisticas(temperaturas);
                    break;

                case 3:
                    mostrarHistograma(temperaturas);
                    break;

                case 4:
                    mostrarDiasSobreMedia(temperaturas);
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (opcion != 5);
    }

    public static double leerDouble(String mensaje) {

        while (true) {

            System.out.print(mensaje);

            if (sc.hasNextDouble()) {
                double numero = sc.nextDouble();
                sc.nextLine();
                return numero;
            } else {
                System.out.println("Error. Introduce un número válido.");
                sc.nextLine();
            }
        }
    }

    public static void introducirTemperaturas(double[] temperaturas) {

        System.out.println("\n¿Cómo deseas introducir las temperaturas?");
        System.out.println("1. Manualmente");
        System.out.println("2. Aleatoriamente");

        int opcion = (int) leerDouble("Selecciona una opción: ");

        if (opcion == 1) {

            for (int i = 0; i < temperaturas.length; i++) {
                temperaturas[i] =
                        leerDouble("Introduce la temperatura del día " + (i + 1) + ": ");
            }

        } else if (opcion == 2) {

            Random random = new Random();

            for (int i = 0; i < temperaturas.length; i++) {
                temperaturas[i] = -5 + random.nextDouble() * 45;
            }

            System.out.println("Temperaturas generadas aleatoriamente.");

        } else {

            System.out.println("Opción no válida. Volviendo al menú.");
        }
    }

    public static void mostrarEstadisticas(double[] temperaturas) {

        double suma = 0;
        double maxima = temperaturas[0];
        double minima = temperaturas[0];

        int diaMaxima = 1;
        int diaMinima = 1;

        for (int i = 0; i < temperaturas.length; i++) {

            suma += temperaturas[i];

            if (temperaturas[i] > maxima) {
                maxima = temperaturas[i];
                diaMaxima = i + 1;
            }

            if (temperaturas[i] < minima) {
                minima = temperaturas[i];
                diaMinima = i + 1;
            }
        }

        double media = suma / temperaturas.length;

        System.out.printf("Temperatura media: %.2f ºC%n", media);
        System.out.printf("Temperatura máxima: %.2f ºC (Día %d)%n", maxima, diaMaxima);
        System.out.printf("Temperatura mínima: %.2f ºC (Día %d)%n", minima, diaMinima);
    }

    public static void mostrarHistograma(double[] temperaturas) {

        System.out.println("\nHistograma de temperaturas:");

        for (int i = 0; i < temperaturas.length; i++) {

            int asteriscos = (int) temperaturas[i];

            if (asteriscos < 0) {
                asteriscos = 0;
            }

            System.out.printf("Día %2d (%.1f ºC): ", i + 1, temperaturas[i]);

            for (int j = 0; j < asteriscos; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    public static void mostrarDiasSobreMedia(double[] temperaturas) {

        double suma = 0;

        for (double temperatura : temperaturas) {
            suma += temperatura;
        }

        double media = suma / temperaturas.length;

        ArrayList<Double> temperaturasSobreMedia = new ArrayList<>();

        System.out.printf("Temperatura media: %.2f ºC%n", media);
        System.out.println("Días con temperatura superior a la media:");

        for (int i = 0; i < temperaturas.length; i++) {

            if (temperaturas[i] > media) {

                temperaturasSobreMedia.add(temperaturas[i]);

                System.out.printf("Día %d (%.1f ºC)%n",
                        i + 1,
                        temperaturas[i]);
            }
        }

        System.out.println("Total de temperaturas almacenadas en el ArrayList: "
                + temperaturasSobreMedia.size());
    }

    public static int mostrarMenu() {

        System.out.println("\n=== ANALIZADOR DE DATOS METEOROLÓGICOS ===");
        System.out.println("1. Introducir temperaturas");
        System.out.println("2. Mostrar estadísticas");
        System.out.println("3. Mostrar histograma");
        System.out.println("4. Mostrar días por encima de la media");
        System.out.println("5. Salir");

        return (int) leerDouble("Selecciona una opción: ");
    }
}