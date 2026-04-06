package model;

/**
 * Description: Card class represents a Pokemon trading card with its properties.
 * Stores information about a card such as name, type, rarity, expansion set,
 * ID, and price.
 */
public class Card {

    // Attributes
    private String name;
    private Type type;
    private Rarity rarity;
    private String expansion;
    private int id;
    private double price;

    // Methods

    /**
     * Description: Constructs a Card object with the specified properties.
     *
     * @param name      The name of the card
     * @param type      The type of the card
     * @param rarity    The rarity level of the card
     * @param expansion The expansion set the card belongs to
     * @param id        The unique identification number of the card
     * @param price     The price of the card
     */
    public Card(String name, Type type, Rarity rarity, String expansion, int id, double price) {

        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.expansion = expansion;
        this.id = id;
        this.price = price;

    }

    /**
     * Description: Returns a string representation of the card with all its
     * information.
     *
     * @return A formatted string containing the card's name, type, rarity,
     *         expansion, ID, and price
     */
    public String toString() {

        return "Nombre: " + name +
                "\nTipo: " + type +
                "\nRareza: " + rarity +
                "\nExpansion: " + expansion +
                "\nID: " + id +
                "\nPrice: " + price;

    }

    /**
     * Description: Returns the name of the card.
     * 
     * @return a String containing the card's name
     */
    public String getName() {
        return name;
    }

    /**
     * Description: Sets the name of the card.
     * 
     * @param name the new name to assign to the card
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Description: Returns the type of the card.
     * 
     * @return a Type containing the card's type (e.g., Fire, Water, Grass)
     */
    public Type getType() {
        return type;
    }

    /**
     * Description: Sets the type of the card.
     * 
     * @param type the new type to assign to the card
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Description: Returns the rarity level of the card.
     * 
     * @return a Rarity containing the card's rarity level
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     * Description: Sets the rarity level of the card.
     * 
     * @param rarity the new rarity level to assign to the card
     */
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    /**
     * Description: Returns the expansion set of the card.
     * 
     * @return a String containing the card's expansion set
     */
    public String getExpansion() {
        return expansion;
    }

    /**
     * Description: Sets the expansion set of the card.
     * 
     * @param expansion the new expansion set to assign to the card
     */
    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }

    /**
     * Description: Returns the unique identification number of the card.
     * 
     * @return an integer containing the card's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Description: Returns the price of the card.
     * 
     * @return a double containing the card's price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Description: Sets the price of the card.
     * 
     * @param price the new price to assign to the card
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
