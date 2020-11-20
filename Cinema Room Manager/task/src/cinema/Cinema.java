package cinema;

import java.util.Scanner;

public class Cinema {
    private static final Scanner scanner = new Scanner(System.in);
    private static int rowsNumber;
    private static int seatsPerRow;
    private static int totalSeats;
    private static String seatsArray[][];
    private static final int regularAndFrontHalfPrice = 10;
    private static final int backHalfPrice = 8;
    private static int chosenNumber;
    private static int purchasedTickets;
    private static int currentIncome;
    private static int totalIncome;
    private static int ticketPrice;

    public static void main(String[] args) {

        buildCinemaHall();

        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            chosenNumber = scanner.nextInt();

            switch (chosenNumber) {
                case 0:
                    break;
                case 1:
                    printCinemaHall();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    showStatistics();
                default:
                    System.out.println("Wrong choice. Please select once again!");
            }
        } while (chosenNumber != 0);
    }

    public static void buildCinemaHall() {
        System.out.println("Enter the number of rows:");
        rowsNumber = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = scanner.nextInt();

        totalSeats = rowsNumber * seatsPerRow;

        seatsArray = new String[rowsNumber][seatsPerRow];
        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seatsArray[i][j] = "S";
            }
        }

        if (totalSeats <= 60) {
            totalIncome = totalSeats * regularAndFrontHalfPrice;
        } else if (totalSeats > 60) {
            int halfRow = rowsNumber / 2;
            int frontHalfCost = halfRow * regularAndFrontHalfPrice * seatsPerRow;
            int backHalCost = (rowsNumber - halfRow) * backHalfPrice * seatsPerRow;
            totalIncome = frontHalfCost + backHalCost;

        }
    }

    public static void printCinemaHall() {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < rowsNumber; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print(seatsArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyTicket() {
        int chosenRowNumber;
        int chosenSeatNumber;

        while (true) {
            System.out.println();
            System.out.println("Enter a row number:");
            chosenRowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            chosenSeatNumber = scanner.nextInt();
            System.out.println();

            if (chosenRowNumber > seatsArray.length || chosenSeatNumber > seatsArray.length) {
                System.out.println("Wrong input!");
            } else if (seatsArray[chosenRowNumber - 1][chosenSeatNumber - 1] == "B") {
                System.out.println("That ticket has already been purchased");
            } else {
                break;
            }
        }

        if (totalSeats <= 60) {
            System.out.println("Ticket price: $" + regularAndFrontHalfPrice);
            ticketPrice = regularAndFrontHalfPrice;
        } else if (totalSeats > 60) {
            int halfRow = rowsNumber / 2;
            if (chosenRowNumber <= halfRow) {
                System.out.println("Ticket price: $" + regularAndFrontHalfPrice);
                ticketPrice = regularAndFrontHalfPrice;
            } else {
                System.out.println("Ticket price: $" + backHalfPrice);
                ticketPrice = backHalfPrice;
            }
        }
        seatsArray[chosenRowNumber - 1][chosenSeatNumber - 1] = "B";
        purchasedTickets++;
        currentIncome += ticketPrice;
    }


    public static void showStatistics(){
        float boughtTicketsPercentage = ((float) purchasedTickets/totalSeats)*100;
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%% %n", boughtTicketsPercentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }


}