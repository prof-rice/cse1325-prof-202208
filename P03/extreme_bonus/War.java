import java.util.ArrayList;

public class War {
  public static void main(String[] args) {

    // Splash screen
    System.out.println("\n\n"
         + "        ███▄▄▄▄▄    \n"
         + "    ▂▄▅█████▅▄▃▂    \n"
         + "  ▒███ W A R █████▒ \n"
         + "   ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲⊙◤  \n\n\n");

    // Create the  decks
    Deck deck = new Deck();
    deck.shuffle();
    Deck player1 = new Deck(0);
    Deck player2 = new Deck(0);
    int round = 0;

    // Deal the cards
    System.out.println(deck.size());
    while (!deck.isEmpty()) {
      player1.add(deck.deal());
      player2.add(deck.deal());
    }

    // Main game loop
    while (!(player1.isEmpty() || player2.isEmpty())) {
        System.out.printf("Player 1 has %2d cards\nPlayer 2 has %2d cards\n",
                           player1.size(), player2.size());

        // Prepare for the next round of battle
        System.out.printf("\n=== ROUND %d ===\n", ++round);
        ArrayList<Card> inPlay = new ArrayList<>();
        Card c1 = player1.deal(); Card c2 = player2.deal();
        inPlay.add(c2);
        inPlay.add(c1);
        System.out.println("Cards: " + c1 + " " + c2);

        // Fight!
        int winner = 0;  // undecided
        System.out.printf("Player 1 deals %s\nPlayer 2 deals %s\n",
            inPlay.get(1).toString(), inPlay.get(0).toString());
        while (true) {

            // Try to resolve the battle and determine a winner
            int comp = inPlay.get(0).compareTo(inPlay.get(1));
            if(comp < 0) {
                winner = 1;
                break;
            } else if (comp > 0) {
                winner = 2;
                break;

            // No winner - put more cards at risk and try again
            } else {

                // Deal 4 cards, unless a player is running low
                System.out.println("Tie! Let's BATTLE!");
                int atRisk = Integer.min(4, Integer.min(player1.size(), player2.size()));

                // Verify both players have cards with which to fight
                if (atRisk == 0) { 
                    if (player1.size() == 0) winner = 2;
                    else winner = 1;
                    break;
                }

                // Put more cards at risk
                for (int i=0; i<atRisk; ++i) {
                    inPlay.add(0, player2.deal());
                    inPlay.add(0, player1.deal());
                    System.out.printf("Player 1 deals %s\nPlayer 2 deals %s\n",
                        inPlay.get(1).toString(), inPlay.get(0).toString());
                }
            }
        }
        // If stuck in a loop, occasionally flip a win to break the loop
        if ((round % 4051) == 0) {
            System.out.println("COLONEL RANDOMIZER: ");
            winner = 3-winner;
        }

        // Announce the winner
        System.out.printf("Player %d wins!\n\n", winner);
        if (winner == 1) for (Card c : inPlay) player1.add(c);
        else for (Card c : inPlay) player2.add(c);
    }
    
    // Announce who won the war and end
    System.out.printf("========================================\n");
    System.out.printf("THE WAR IS OVER! PLAYER %d IS VICTORIOUS!\n",
              ((player1.size() == 0) ? 2 : 1));
    System.out.printf("========================================\n");
  }
}

