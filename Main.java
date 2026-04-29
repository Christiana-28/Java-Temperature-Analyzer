import java.util.Scanner;
import java.util.Random;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        double[] temperatures = new double[30]; // 30 days
        int choice;

        do {
            choice = showMenu();
            switch(choice) {
                case 1:
                    enterTemperatures(temperatures);
                    break;
                case 2:
                    showStatistics(temperatures);
                    break;
                case 3:
                    displayHistogram(temperatures);
                    break;
                case 4:
                    listDaysAboveAverage(temperatures);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while(choice != 5);
    }

    public static double readDouble(String prompt) {
        double number;
        while (true) {
            System.out.print(prompt);
            if(sc.hasNextDouble()) {
                number = sc.nextDouble();
                sc.nextLine();
                return number;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }

    public static void enterTemperatures(double[] temperatures) {
        System.out.println("Do you want to fill temperatures manually or randomly?");
        System.out.println("1. Manually");
        System.out.println("2. Randomly");
        int choice = (int) readDouble("Enter choice (1 or 2): ");

        if(choice == 1) {
            for(int i = 0; i < temperatures.length; i++) {
                temperatures[i] = readDouble("Enter temperature for day " + (i+1) + ": ");
            }
        } else if(choice == 2) {
            Random rand = new Random();
            for(int i = 0; i < temperatures.length; i++) {
                temperatures[i] = -5 + rand.nextDouble() * 45; // -5 to 40°C
            }
            System.out.println("Temperatures filled randomly.");
        } else {
            System.out.println("Invalid choice. Returning to menu.");
        }
    }

    public static void showStatistics(double[] temperatures) {
        if(temperatures[0] == 0) {
            System.out.println("No temperatures entered yet.");
            return;
        }

        double sum = 0;
        double max = temperatures[0];
        double min = temperatures[0];
        int dayMax = 1;
        int dayMin = 1;

        for(int i = 0; i < temperatures.length; i++) {
            double temp = temperatures[i];
            sum += temp;
            if(temp > max) { max = temp; dayMax = i+1; }
            if(temp < min) { min = temp; dayMin = i+1; }
        }

        double average = sum / temperatures.length;
        System.out.printf("Average temperature: %.2f°C%n", average);
        System.out.printf("Maximum temperature: %.2f°C (Day %d)%n", max, dayMax);
        System.out.printf("Minimum temperature: %.2f°C (Day %d)%n", min, dayMin);
    }

    public static void displayHistogram(double[] temperatures) {
        if(temperatures[0] == 0) {
            System.out.println("No temperatures entered yet.");
            return;
        }

        System.out.println("Temperature Histogram:");
        for(int i = 0; i < temperatures.length; i++) {
            int stars = (int) temperatures[i];
            if(stars < 0) stars = 0;
            System.out.printf("Day %2d (%.1f°C): ", i+1, temperatures[i]);
            for(int j = 0; j < stars; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void listDaysAboveAverage(double[] temperatures) {
        if(temperatures[0] == 0) {
            System.out.println("No temperatures entered yet.");
            return;
        }

        double sum = 0;
        for(double temp : temperatures) sum += temp;
        double average = sum / temperatures.length;

        System.out.printf("Average temperature: %.2f°C%n", average);
        System.out.println("Days with temperature above average:");

        boolean found = false;
        for(int i = 0; i < temperatures.length; i++) {
            if(temperatures[i] > average) {
                System.out.printf("Day %d (%.1f°C)%n", i+1, temperatures[i]);
                found = true;
            }
        }

        if(!found) System.out.println("No days were above average.");
    }

    public static int showMenu() {
        System.out.println("\n=== Weather Data Analyzer ===");
        System.out.println("1. Enter temperatures");
        System.out.println("2. Show temperature statistics");
        System.out.println("3. Display histogram");
        System.out.println("4. List days above average");
        System.out.println("5. Exit");
        return (int) readDouble("Enter your choice: ");
    }
}

