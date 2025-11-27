package app;

import queue.Queue;
import queue.EmptyQueueException;
import java.util.Scanner;
import progression.*;

public class Main {
    public static Queue queue = new Queue("MyQueue1");
    public static Scanner input = new Scanner(System.in);


    public static void main(String[] args){




        //ArithProgression = new ArithProgression();


        int option = -1;

        while (option != 0) {
            displayMenu();
            try {
                option = Integer.parseInt(input.nextLine());

                switch (option) {
                    case 1:
                        addToQueue(input);
                        break;
                    case 2:
                        removeFromQueue();
                        break;
                    case 3:
                        queue.print();
                        break;
                    case 4:
                        runProgression("ArithProgression", new ArithProgression());
                        break;
                    case 5:
                        runProgression("FibonacciProgression", new FibonacciProgression());
                        break;
                    case 6:
                        runProgression("GeomProgression", new GeomProgression());
                        break;
                    case 0:
                        System.out.println("Exiting!");
                        break;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
        input.close();
    }

    private static void addToQueue(Scanner input) {
        System.out.print("enter value: ");
        queue.add( Integer.parseInt(input.nextLine()) );
    }

    private static void removeFromQueue() {
        try {
            Object obj = queue.remove();
            System.out.println("removing: " + obj);
        } catch (EmptyQueueException e){
            System.out.println("error:");
            e.printStackTrace();
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. add (queue)");
        System.out.println("2. remove (queue)");
        System.out.println("3. print (queue)");
        System.out.println("4. ArithProgression");
        System.out.println("5. FibonacciProgression");
        System.out.println("6. GeomProgression");
        System.out.println("0. Exit Application");
        System.out.print("choose an option: ");
    }

    private static void runProgression(String name, Progression p) {
        System.out.printf("how many terms of the %s do you want? ", name);
        try {
            int terms = Integer.parseInt(input.nextLine());
            System.out.println(p.printProgression(terms));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

}