import javax.swing.*;

public class Play
{
    public static void main(String[] args)
    {
        DeckOfCards myDeck = new DeckOfCards();
        myDeck.shuffle();
        myDeck.divideToTow();
        while (myDeck.getPlayer1().size() > 0 && myDeck.getPlayer2().size() > 0)//As long as both players have cards left
        {
            myDeck.pullCard();
            if (myDeck.getPlayer1().size() == 0)
                JOptionPane.showMessageDialog(null, "Player2 win");
            if (myDeck.getPlayer2().size() == 0)
                JOptionPane.showMessageDialog(null, "Player1 win");
        }
    }
}
