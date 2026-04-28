package ui;

import java.util.Scanner;
import model.Controller;

/**
 * Description: Executable class represents the user interface and main
 * controller of the
 * PokeCollector application. Manages the main menu and functionalities.
 */
public class Executable {

    // Attributes
    private Scanner reader;
    private Controller control;

    /**
     * Description: Main entry point of the PokeCollector application.
     * Initializes the Executable instance and displays the main menu.
     * 
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Executable exe = new Executable();
        exe.showMenu();
    }

    /**
     * Description: Initializes a new Executable instance with a Scanner for user
     * input
     * and a Controller for managing card operations.
     * Postcondition: reader and control attributes are initialized
     */
    public Executable() {
        reader = new Scanner(System.in);
        control = new Controller();
    }

    /**
     * Description: Displays the main menu and handles user option selection.
     * Allows users to access system functionalities or exit.
     * Precondition: reader Scanner must be initialized.
     */
    public void showMenu() {

        System.out.print("\033[H\033[2J");

        int option = 0;

        do {
            System.out.println("Bienvenido a PokeCollector!!!");
            System.out.println("\nMENU DE OPCIONES");
            System.out.println("\n1. Registrar una carta");
            System.out.println("2. Mostrar informacion de una carta");
            System.out.println("3. Modificar informacion de una carta");
            System.out.println("4. Borrar una carta");
            System.out.println("5. Visualizar la coleccion de cartas por tipo");
            System.out.println("6. Visualizar estadisticas de la coleccion de cartas por tipo y rareza");
            System.out.println("0. Salir del sistema");
            option = reader.nextInt();

            switch (option) {
                case 1:
                    registerCard();
                    break;

                case 2:
                    showCardInfo();
                    break;

                case 3:
                    modifyCardInfo();
                    break;
                case 4:
                    deleteCard();
                    break;
                case 5:
                    showCollectionByType();
                    break;
                case 6:
                    showCollectionByTypeAndRarity();
                    break;

                case 0:
                    System.out.println("Gracias por usar nuestros servicios!");
                    break;

                default:
                    System.out.println("Digite una opcion valida");
                    break;
            }

            if (option != 0) {
                clearConsole();
            }

        } while (option != 0);

    }

    /**
     * Description: Clears the console screen after confirming user wants to return
     * to the main
     * menu. Uses ANSI escape codes to clear the screen.
     * 
     * Precondition: reader Scanner must be initialized.
     */
    public void clearConsole() {

        int option = 0;

        while (option != 1) {
            System.out.println("\n¿Desea volver al menu principal?");
            System.out.println("1. Si");
            System.out.println("2. No");
            option = reader.nextInt();

        }

        System.out.print("\033[H\033[2J");

    }

    /**
     * Description: Captures card information from the user and registers a new Card
     * object.
     * Prompts for card name, type, rarity, expansion, ID, and price.
     * Validates that the card ID is unique and type and rarity are valid before
     * registering.
     * 
     * Precondition: reader Scanner must be initialized.
     * Precondition: control Controller must be initialized.
     */
    public void registerCard() {

        reader.nextLine();
        System.out.println("\nRegistro de Carta:");
        System.out.println("\nDigite el nombre");
        String name = reader.nextLine();

        boolean inputFlag;
        String type;
        do {
            System.out.println("Digite el tipo");
            type = reader.nextLine();
            inputFlag = control.checkType(type);

            if (!inputFlag) {
                System.out.println("Tipo invalido, digite nuevamente\n");
            }
        } while (!inputFlag);

        String rarity;

        do {
            System.out.println("Digite la rareza");
            rarity = reader.nextLine();
            inputFlag = control.checkRarity(rarity);

            if (!inputFlag) {
                System.out.println("Rareza invalida, digite nuevamente\n");
            }

        } while (!inputFlag);

        System.out.println("Digite la expansion");
        String expansion = reader.nextLine();

        System.out.println("Digite el precio");
        double price = reader.nextDouble();

        boolean result = control.registerCard(name, type, rarity, expansion, price);

        if (result) {
            System.out.println("Carta registrada exitosamente!");
        } else {
            System.out.println("Error al registrar carta, alguno de los valores proporcionados no es valido");
        }

    }

    /**
     * Description: Displays the information of a registered card selected by the
     * user.
     * Prints all card details to the console after retrieving the card index.
     * 
     * Precondition: reader Scanner must be initialized.
     * Precondition: control Controller must be initialized.
     * 
     * @return the valid index of the selected card (0-based), or -1 if no cards
     *         exist
     */
    public int showCardInfo() {

        int index = askIndex();

        if (index >= 0) {

            String cardInfo = control.getCardInformation(index);

            if (cardInfo != null) {
                System.out.println("\nLa informacion de la carta es: \n\n" + cardInfo);

            } else {
                System.out.println("Se presento un error, intente nuevamente");
            }
        }

        return index;

    }

    /**
     * Description: Prompts the user to select a card from the registered cards in
     * the Pokedex.
     * Displays a list of all registered cards and asks the user to enter the card's
     * consecutive number.
     * Validates that the selected index is valid.
     * 
     * Precondition: reader Scanner must be initialized.
     * Precondition: control Controller must be initialized.
     * 
     * @return the valid index of the selected card (0-based), or -1 if no cards
     *         exist
     */
    public int askIndex() {

        int index = -1;
        String query = control.getPokedexInformation();

        if (query.equals("")) {
            System.out.println("\nNo hay cartas registradas en el sistema");
        } else {
            System.out.println("\nCartas registradas: \n");
            System.out.println(query);

            System.out.println("Digite el consecutivo de la carta");
            index = reader.nextInt();

            index--;

            if (control.checkIndex(index)) {
                return index;

            } else {
                System.out.println("\nSe presento un error, verifique el indice proporcionado!");
            }

        }

        return index;

    }

    /**
     * Description: Allows the user to modify the information of an existing card.
     * Validates that type and rarity are valid before modifying.
     * Precondition: reader Scanner must be initialized.
     * Precondition: control Controller must be initialized.
     */
    public void modifyCardInfo() {

        int index = showCardInfo();

        if (index >= 0) {

            reader.nextLine();
            System.out.println("\nModificando la Carta No.: " + (index + 1));
            System.out.println("\nDigite el nuevo nombre");
            String name = reader.nextLine();

            boolean inputFlag;
            String type;
            do {
                System.out.println("Digite el tipo");
                type = reader.nextLine();
                inputFlag = control.checkType(type);

                if (!inputFlag) {
                    System.out.println("Tipo invalido, digite nuevamente\n");
                }
            } while (!inputFlag);

            String rarity;

            do {
                System.out.println("Digite la rareza");
                rarity = reader.nextLine();
                inputFlag = control.checkRarity(rarity);

                if (!inputFlag) {
                    System.out.println("Rareza invalida, digite nuevamente\n");
                }

            } while (!inputFlag);

            System.out.println("Digite la nueva expansion");
            String expansion = reader.nextLine();

            System.out.println("Digite el precio");
            double price = reader.nextDouble();

            boolean result = control.modifyCard(index, name, type, rarity, expansion, price);

            if (result) {
                System.out.println("\nCarta modificada exitosamente!");
            } else {
                System.out.println("\nError al modificar la carta!");
            }

        }

    }

    /**
     * Description: Allows the user to delete a card from the Pokedex.
     * 
     * Precondition: reader Scanner must be initialized.
     * Precondition: control Controller must be initialized.
     */
    public void deleteCard() {

        System.out.println("\nBorrado de Carta:");

        boolean result = control.deleteCard(askIndex());

        if (result) {

            System.out.println("\nOperacion exitosa, la carta fue borrada del sistema!");
        }

    }

    public void showCollectionByType() {

        System.out.println("\nLa colección de cartas segun su tipo esta conformada asi:\n");
        System.out.println(control.getPokedexByType());
    }

    public void showCollectionByTypeAndRarity() {

        System.out.println("\nLas estadisticas de la colección de cartas segun su tipo y rareza son:\n");
        System.out.println(control.getPokedexByTypeAndRarity());
    }

}
