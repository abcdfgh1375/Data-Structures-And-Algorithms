
package MultiplayerGameQueue;

public class GameQueueSecondEdition {   
    Node front,rear;
    //action code//
    boolean isEmpty() {
        return (front == null);
    }
//add object into queue
    public void enqueue(Player player) {
        Node newNode = new Node(player, null);
        if (isEmpty()) {
            front = rear = newNode;
            rear.next = front;
        } else {
            rear.next = newNode;
            rear = newNode;
            rear.next = front;
        }
        System.out.println("Player " + player.id + " joined the game queue.");
    }
//delete the first object of queue and update queue
    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
        } else {
            front = front.next;
            rear.next = front;
            System.out.println("Player " + front.player.id + " lefted the game queue.");
        }
    }
//display the queue
    public void displayQueue() {
        Node p = front;
        System.out.println("\nGame Queue: ");
        do {
            System.out.println(p.player.toString());
            p = p.next;
        } while (p != front);
        System.out.println();
    }
}

//class Node//
 class Node {

    Player player;
    Node next;

    Node() {
    }

    Node(Player x, Node p) {
        player = x;
        next = p;
    }
}
//class Player//
class Player {

    String id;
    String character;
    int level;

    public Player(String id, String character, int level) {
        this.id = id;
        this.character = character;
        this.level = level;
    }

    Player() {
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", character=" + character + ", level=" + level + '}';
    }
}
