package model;

import java.util.ArrayList;

/**
 * Description: The Controller class manages a collection of Pokémon cards
 * (Pokedex). It provides functionalities to add, manage, and retrieve cards
 * in the collection.
 */
public class Controller {

    /**
     * ArrayList that stores all the Pokémon cards in the collection.
     */
    private ArrayList<Card> pokedex;

     /**
     * MatrixHandler instance used to generate and manipulate matrix representations and operations
     */
    private MatrixHandler matrixHandler;

    /**
     * Description: Initializes a new Controller with an empty Pokedex ArrayList.
     * Also loads test data into the Pokedex.
     * 
     * Postcondition: The pokedex ArrayList is initialized and populated with test
     * data.
     */
    public Controller() {
        pokedex = new ArrayList<Card>();
        matrixHandler = new MatrixHandler();
        loadTestData();

    }

    /**
     * Description: Loads test data into the Pokedex for demonstration purposes.
     * Registers three test cards with predefined information.
     * 
     * Precondition: The pokedex ArrayList must be initialized.
     * Postcondition: Three test cards are added (if possible) to the Pokedex.
     */
    public void loadTestData() {

        registerCard("Pikachu", "ELECTRIC", "NORMAL", "Rocky Ridges", 2.0);
        registerCard("Raichu", "ELECTRIC", "NORMAL", "Rocky Ridges", 4.0);
        registerCard("Blastoise", "WATER", "RARE", "Void Dimension", 14.0);
    }

    /**
     * Description: Registers a new Pokémon card in the Pokedex.
     * Creates a Card object with the provided details and adds it to the first
     * available
     * Precondition: The pokedex ArrayList must be initialized.
     * Postcondition: If available null position is found, a new Card object is
     * added
     * to to the pokedex.
     * 
     * @param name      the name of the Pokémon card
     * @param type      the type of the Pokémon (e.g., Fire, Water, Grass)
     * @param rarity    the rarity level of the card
     * @param expansion the expansion set the card belongs to
     * @param price     the price of the card
     * @return true if the card is successfully registered, false if the Pokedex is
     *         full
     */
    public boolean registerCard(String name, String type, String rarity, String expansion, double price) {

        if (checkType(type) && checkRarity(rarity)) {

            String id = matrixHandler.generateID(Rarity.valueOf(rarity));

            if (checkCardId(id)) {
                Type myType = Type.valueOf(type);

                Rarity myRarity = Rarity.valueOf(rarity);

                Card myCard = new Card(name, myType, myRarity, expansion, id, price);
                return pokedex.add(myCard);
            }

        }

        return false;

    }

    /**
     * Description: Checks that the string myType is a valid Type.
     * 
     * @param myType the type of the Pokémon (e.g., Fire, Water, Grass)
     * @return true if myType is a valid Type, false if not.
     */
    public boolean checkType(String myType) {

        Type[] types = Type.values();

        for (Type type : types) {

            if (myType.equals(type.toString())) {
                return true;
            }

        }

        return false;
    }

    /**
     * Description: Checks that the string myRarity is a valid Rarity.
     * 
     * @param myRarity the rarity of the Pokémon (e.g., Normal, Rare)
     * @return true if myRarity is a valid Rarity, false if not.
     */
    public boolean checkRarity(String myRarity) {

        Rarity[] rarities = Rarity.values();

        for (Rarity rarity : rarities) {

            if (myRarity.equals(rarity.toString())) {
                return true;
            }

        }

        return false;
    }

    /**
     * Description: Checks if a card with the given ID already exists in the
     * Pokedex.
     * Prevents duplicate card IDs from being registered.
     * 
     * Precondition: the pokedex ArrayList must be initialized.
     * 
     * @param id the ID to check for uniqueness
     * @return true if the ID is unique and not in use, false if it already exists
     */
    public boolean checkCardId(String id) {

        for (Card card : pokedex) {

            if (card != null && card.getId().equals(id)) {
                return false;
            }

        }

        return true;

    }

    /**
     * Description: Retrieves formatted information about all cards in the Pokedex.
     * Lists each registered card with its position number and name, separated by
     * newlines.
     * Only non-null cards are included in the output.
     * Precondition: the pokedex ArrayList must be initialized.
     * 
     * @return a String containing the formatted list of all cards in the Pokedex,
     *         or an empty string if no cards are registered
     */
    public String getPokedexInformation() {

        String information = "";

        for (int i = 0; i < pokedex.size(); i++) {
            if (pokedex.get(i) != null) {
                information += (i + 1) + ". " + pokedex.get(i).getName() + "\n";
            }

        }

        return information;

    }

    /**
     * Description: Retrieves detailed information about a specific card in the
     * Pokedex.
     * Precondition: pokedex ArrayList must be initialized.
     * 
     * @param index the position of the card in the Pokedex ArrayList (0-based
     *              indexing)
     * @return a String containing the card's detailed information, or null if not
     *         implemented
     *         or if the index is out of bounds
     */
    public String getCardInformation(int index) {

        if (checkIndex(index) && pokedex.get(index) != null) {

            return pokedex.get(index).toString();

        }

        return null;
    }

    /**
     * Description: Validates if an index is within the valid range of the Pokedex
     * ArrayList.
     * 
     * Precondition: the pokedex ArrayList must be initialized.
     * 
     * @param index the index to validate
     * @return true if the index is within the valid range (0 to MAX_CARDS-1), false
     *         otherwise
     */
    public boolean checkIndex(int index) {

        if (index >= 0 && index < pokedex.size()) {
            return true;
        }

        return false;
    }

    /**
     * Description: Modifies the information of an existing card in the Pokedex.
     * Updates the card's name, type, rarity, expansion, and price.
     * 
     * Precondition: pokedex ArrayList must be initialized.
     * Precondition: index must be valid and within bounds.
     * Postcondition: If the card exists at the given index, its information is
     * updated.
     * 
     * @param index     the position of the card to modify (0-based indexing)
     * @param name      the new name for the card
     * @param type      the new type for the card
     * @param rarity    the new rarity level for the card
     * @param expansion the new expansion set for the card
     * @param price     the new price for the card
     * @return true if the card is successfully modified, false if the index is
     *         invalid or card doesn't exist
     */
    public boolean modifyCard(int index, String name, String type, String rarity, String expansion, double price) {

        if (checkIndex(index) && pokedex.get(index) != null && checkType(type) && checkRarity(rarity)) {

            pokedex.get(index).setName(name);
            pokedex.get(index).setType(Type.valueOf(type));
            pokedex.get(index).setRarity(Rarity.valueOf(rarity));
            pokedex.get(index).setExpansion(expansion);
            pokedex.get(index).setPrice(price);
            return true;

        }

        return false;
    }

    /**
     * Description: Deletes a card from the Pokedex by setting it to null.
     * The card at the specified index is removed from the collection.
     * 
     * Precondition: the pokedex ArrayList must be initialized.
     * Precondition: index must be valid and within bounds.
     * Postcondition: If a card exists at the given index, it is removed (set to
     * null).
     * 
     * @param index the position of the card to delete (0-based indexing)
     * @return true if the card is successfully deleted, false if the index is
     *         invalid or no card exists at that position
     */
    public boolean deleteCard(int index) {

        if (checkIndex(index) && pokedex.get(index) != null) {

            pokedex.remove(index);

            return true;

        }

        return false;

    }

     /**
     * Description: Gets a matrix representation of the Pokedex grouped by Pokémon type.
     * The matrix is created using the MatrixHandler instance and includes all cards in the Pokedex.
     * 
     * Precondition: The pokedex ArrayList must be initialized and may contain Card objects.
     * Precondition: The matrixHandler MatrixHandler must be initialized.
     * 
     * @return A String containing the matrix representation of the Pokedex grouped by type.
     */
    public String getPokedexByType() {

        return matrixHandler.generateMatrixByType(pokedex);

    }

    /**
     * Description: Gets a matrix representation of the Pokedex grouped by Pokémon type and rarity.
     * The matrix is created using the MatrixHandler instance and includes all cards in the Pokedex.
     * 
     * Precondition: The pokedex ArrayList must be initialized and may contain Card objects.
     * Precondition: The matrixHandler MatrixHandler must be initialized.
     * 
     * @return A String containing the matrix representation of the Pokedex grouped by type and rarity.
     */
    public String getPokedexByTypeAndRarity() {

        return matrixHandler.generateMatrixByTypeAndRarity(pokedex);

    }

}
