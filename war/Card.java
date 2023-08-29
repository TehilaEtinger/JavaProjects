public class Card {
    //attributes
    private final String face; //face of card
    private final String suit; //suit of card
    //constructor
    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
    }
    //methods
    public String toString() {
        return face + " of " + suit;
    }

    public int getValue() {
        String f = face;
        String[] faces = {"Ace", "Deuce", "Tree", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        for (int i = 0; i < faces.length; i++) {
            if (f.equals(faces[i]))
                return i + 1;//the numeric value of the card.
        }
        return 0;//if the face of the card was wrong.
    }
}
