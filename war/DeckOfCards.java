import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {
    //attributes
    private final ArrayList<Card> deck = new ArrayList<>(NUMBER_OF_CARD);
    private final ArrayList<Card> player1 = new ArrayList<>();
    private final ArrayList<Card> player2 = new ArrayList<>();
    private static final int NUMBER_OF_CARD = 52;
    //constructor
    public DeckOfCards() {
        String[] faces = {"Ace", "Deuce", "Tree", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        //populate deck with Card objects
        for (int count = 0; count < NUMBER_OF_CARD; count++) {
            deck.add(new Card(faces[count % 13], suits[count / 13]));
        }
    }
    //methods
    public ArrayList<Card> getPlayer1()
    {
        return player1;
    }

    public ArrayList<Card> getPlayer2()
    {
        return player2;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void divideToTow()
    {
        for (int i = 0; i < NUMBER_OF_CARD / 2; i++)
            player1.add(deck.get(i));
        for (int i = NUMBER_OF_CARD / 2; i < NUMBER_OF_CARD; i++)
            player2.add(deck.get(i));
    }

    //Each player pull a card, and the winner between them takes both cards
    public void pullCard()
    {
        if (player1.get(0).getValue() > player2.get(0).getValue())//if player1 wins, add the two cards to the end of the pack, and remove the cards in the first member of each player's set.
        {
            JOptionPane.showMessageDialog(null, "player1:  " + getPlayer1().get(0) + "\n player2: " + getPlayer2().get(0) + "\n Player1 takes");
            player1.add(player1.size(), player1.get(0));
            player1.remove(0);
            player1.add(player1.size(), player2.get(0));
            player2.remove(0);
        }
        else if (player1.get(0).getValue() < player2.get(0).getValue())//if player2 wins, add the two cards to the end of the pack, and remove the cards in the first member of each player's set.
        {
            JOptionPane.showMessageDialog(null, "player1:  " + getPlayer1().get(0) + "\n player2: " + getPlayer2().get(0) + "\n Player2 takes");
            player2.add(player2.size(), player2.get(0));
            player2.remove(0);
            player2.add(player2.size(), player1.get(0));
            player1.remove(0);
        }
        else if (player1.get(0).getValue() == player2.get(0).getValue())
        {
            JOptionPane.showMessageDialog(null, "player1:  " + getPlayer1().get(0) + "\n player2: " + getPlayer2().get(0) + "\n war");
            war(0);
        }
    }

    public void war(int j)
    {
        if (player1.size() > 4 + j && player2.size() > 4 + j)//Both players with enough cards for the war
        {
            if (player1.get(3 + j).getValue() > player2.get(3 + j).getValue())
            {
                JOptionPane.showMessageDialog(null, "player1:  " + getPlayer1().get(3 + j) + "\n player2: " + getPlayer2().get(3 + j) + "\n Player1 takes");
                for (int i = 0; i < 4 + j; i++)
                {
                    player1.add(player1.size(), player1.get(0));
                    player1.remove(0);
                    player1.add(player1.size(), player2.get(0));
                    player2.remove(0);
                }
            }
            else if (player1.get(3 + j).getValue() < player2.get(3 + j).getValue())
            {
                JOptionPane.showMessageDialog(null, "player1:  " + getPlayer1().get(3 + j) + "\n player2: " + getPlayer2().get(3 + j) + "\n Player2 takes");
                for (int i = 0; i < 4 + j; i++)
                {
                    player2.add(player2.size(), player2.get(0));
                    player2.remove(0);
                    player2.add(player2.size(), player1.get(0));
                    player1.remove(0);
                }
            }
            else if (player1.get(3 + j).getValue() == player2.get(3 + j).getValue())//war again
            {
                JOptionPane.showMessageDialog(null, "player1:  " + getPlayer1().get(0) + "\n player2: " + getPlayer2().get(0) + "\n war");
                war(j + 3);
            }
        }
        //According to the rule that in case one of the players does not have enough cards for the war, the other player wins.
        else if (player1.size() > player2.size())//Player2 does not have enough cards, he loses.
        {
            JOptionPane.showMessageDialog(null, "player1:  " + getPlayer1().get(0) + "\n player2: " + getPlayer2().get(0) + "\n Player1 takes");
            player1.addAll(player2);
            player2.removeAll(player2);
        }
        else if (player1.size() < player2.size())//Player 1 does not have enough cards, he loses.
        {
            JOptionPane.showMessageDialog(null, "player1:  " + getPlayer1().get(0) + "\n player2: " + getPlayer2().get(0) + "\n Player1 takes");
            player2.addAll(player1);
            player1.removeAll(player1);
        }
        else//draw
        {
            JOptionPane.showMessageDialog(null, "player1:  " + getPlayer1().get(0) + "\n player2: " + getPlayer2().get(0) + "\nThe game ended in a draw");
            //Empty the package so that the main knows it is a draw
            player1.removeAll(player1);
            player2.removeAll(player2);
        }

    }


}
