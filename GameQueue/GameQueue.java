package GameQueue;

import java.util.Arrays;
import java.util.Scanner;

public class GameQueue extends Menu {

    //execute code
    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                Player player = new Player();
                inputId(player);
                inputPlayer(player);
                enqueue(player);
                displayQueue();
                break;
            case 2:
                dequeue();
                displayQueue();
                break;
            case 3:
                System.err.println("Exited. Bye bye!");
                break;
            default:
                System.err.println("Invalid choice. Please try again.");
        }
    }
    Node front, rear;

    //constructor of GameQueue class
    public GameQueue() {
        super("Game Queue Management", Arrays.asList(new String[]{"Join queue", "Out queue", "Exit"}));
        this.front = this.rear = null;
    }

    //input code
    public void inputPlayer(Player player) {
        Scanner sc = new Scanner(System.in);
        player.character = player.inputStr("Enter character name");
        player.level = player.inputPositiveInt("Enter player's level");
    }

    void inputId(Player player) {
        Node p = front;
        Player a = new Player();
        boolean check;
        if (isEmpty()) {
            player.id = a.inputStr("Enter player's id: ");
        } else {
            do {
                player.id = a.inputStr("Enter player's id: ");
                check = false;
                do {
                    if (player.id.equals(p.player.id)) {
                        check = true;
                        break;
                    }
                    p = p.next;
                } while (p != front);
            } while (check);
        }
    }

    //action code
    boolean isEmpty() {
        return (front == null);
    }

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

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
        } else {
            front = front.next;
            rear.next = front;
            System.out.println("Player " + front.player.id + " lefted the game queue.");
        }
    }

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
