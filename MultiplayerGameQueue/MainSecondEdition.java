
package MultiplayerGameQueue;

public class MainSecondEdition {
    public static void main(String[] args) {
    GameQueueSecondEdition game = new GameQueueSecondEdition();
    Player p1 = new Player("s123","si",5);
    Player p2 = new Player("s163","so",6);
    Player p3 = new Player("s323","su",89);
    game.enqueue(p1);
    game.enqueue(p2);
    game.enqueue(p3);
    game.displayQueue();
    game.dequeue();
    game.displayQueue();
    }

}
