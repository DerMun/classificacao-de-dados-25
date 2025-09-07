import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);
    static int quant;

    public static void main(String[] args) {
        int option = -1;
        while (option != 0) {
            displayMenu();
            try {
                option = Integer.parseInt(input.nextLine());

                switch (option) {
                    case 1:
                        System.out.println(createSerial());
                        break;
                    case 2:
                        System.out.println(createParallel());
                        break;
                    case 3:
                        System.out.println(createMixed());
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

    private static void displayMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Criar Circuito Serial");
        System.out.println("2. Criar Circuito Paralelo");
        System.out.println("3. Criar Circuito Misto");
        System.out.println("0. Exit");
        System.out.print("choose an option: ");
    }

    private static double createSerial() {
        System.out.print("\ninformar quantidade de resistores: ");
        quant = Integer.parseInt(input.nextLine());

        Serial serial = new Serial();
        for(int i=0; quant > i; i++){
            serial.circuits.add(selectResistor());
        }
        return serial.getResistance();
    }

    private static double createParallel(){
        System.out.print("\ninformar quantidade de resistores: ");
        quant = Integer.parseInt(input.nextLine());

        Parallel parallel = new Parallel();
        for(int i=1; quant >= i; i++){
            parallel.circuits.add(selectResistor());
        }
        return parallel.getResistance();
    }

    private static Resistor selectResistor(){
        Resistor resistor = new Resistor();
        System.out.print("informar resistência do resistor: ");
        resistor.setResistance( Double.parseDouble(input.nextLine()) );
        return resistor;
    }


    private static double createMixed() {
        //circuito principal em série
        Serial mixedCircuit = new Serial();

        int option = -1;
        while (option != 0) {
            displayMenuMixed();
            try {
                option = Integer.parseInt(input.nextLine());

                switch (option) {
                    case 1:
                        mixedCircuit.circuits.add( createSerialObject() );
                        break;
                    case 2:
                        mixedCircuit.circuits.add( createParallelObject() );
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

        System.out.print("\nreq do circuito misto: ");
        return mixedCircuit.getResistance();
    }

    private static void displayMenuMixed() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Adicionar Circuito Serial");
        System.out.println("2. Adicionar Circuito Paralelo");
        System.out.println("0. Exit");
        System.out.print("choose an option: ");
    }

    private static Circuit createParallelObject() {
        System.out.print("\ninforme a quantidade de resistores: ");
        int q = Integer.parseInt(input.nextLine());
        Parallel parallel = new Parallel();
        for(int i=1; q >= i; i++){
            parallel.circuits.add(selectResistor());
        }
        return parallel;
    }

    private static Circuit createSerialObject() {
        System.out.print("\ninforme a quantidade de resistores: ");
        int q = Integer.parseInt(input.nextLine());
        Serial serial = new Serial();
        for(int i=1; q >= i; i++){
            serial.circuits.add(selectResistor());
        }
        return serial;
    }

}