/*
 * Last Editor(s): Pavan Anoop
 * Last Edit @ 05-30-2024
 */

/*
 * Info:
 * Testing Area for ./player
 */

package Player;

public class PlayerTest {

    public static void main(String[] args) {

        // Test constructor
        Player player = new Player("null");
        System.out.println("Player name: " + player.getName()); // Expected output: Player name: Bard

        // Test getters and setters
        player.setName("TestPlayer");
        System.out.println("Player name after change: " + player.getName()); // Expected output: Player name after change: TestPlayer

        // Test initial values
        System.out.println("Initial arrows: " + player.getArrows()); // Expected output: Initial arrows: 3
        System.out.println("Initial gold coins: " + player.getGoldCoins()); // Expected output: Initial gold coins: 0
        System.out.println("Initial turns: " + player.getTurns()); // Expected output: Initial turns: 0

        // Test adding and removing arrows
        player.addArrows();
        System.out.println("Arrows after adding one: " + player.getArrows()); // Expected output: Arrows after adding one: 4
        player.decrementArrows();
        player.decrementArrows();
        System.out.println("Arrows after removing two: " + player.getArrows()); // Expected output: Arrows after removing two: 2

        // Test adding and removing gold coins
        player.addGoldCoins();
        player.addGoldCoins();
        System.out.println("Gold coins after adding two: " + player.getGoldCoins()); // Expected output: Gold coins after adding two: 2
        player.decrementGoldCoins();
        System.out.println("Gold coins after removing one: " + player.getGoldCoins()); // Expected output: Gold coins after removing one: 1

        // Test turns
        player.incrementTurns();
        player.incrementTurns();
        System.out.println("Turns after incrementing twice: " + player.getTurns()); // Expected output: Turns after incrementing twice: 2

        // Test haveArrows method (not calling in main for safety)
        // System.out.println(player.haveArrows());  // Uncomment to test, but avoid in main due to potential game over state
    }
}

