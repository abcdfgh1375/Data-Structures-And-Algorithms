package MultiplayerGameQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//class GameQueue//
public class GameQueue extends Menu {
    
    //execute code//
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

    //constructor of GameQueue class//
    public GameQueue() {
        super("Game Queue Management", Arrays.asList(new String[]{"Join queue", "Update queue", "Exit"}));
        this.front = this.rear = null;
    }
    
        public int getChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter selection: ");
        return Integer.parseInt(sc.nextLine());
    }

    //input code//
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
            player.id = a.inputPattern("Enter player's id, e.g. s123, S234,..: ", "[sS][\\d]{3}");
        } else {
            do {
                player.id = a.inputPattern("Enter player's id, e.g. s123, S234,..: ", "[sS][\\d]{3}");
                check = false;
                do {
                    if (player.id.equals(p.player.id)) {
                        check = true;
                        break;
                    }
                    p = p.next;
                } while (p != front);
                p = front;
            } while (check);
        }
    }

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

//abstract class Menu//
abstract class Menu {

    protected String title;
    protected List<String> list = new ArrayList();

    public Menu(String title, List<String> list) {
        this.title = title;
        this.list = list;
    }

    public void display() {
        System.out.println(title);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    public int getChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter selection: ");
        return Integer.parseInt(sc.nextLine());
    }

    public abstract void execute(int choice);

    public void run() {
        int choice;
        do {
            display();
            choice = getChoice();
            execute(choice);
        } while (choice < list.size());
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
    public boolean isAlphabetic(String str) {
        return str.matches("[a-zA-Z]+");
    }
    
    public String inputStr(String msg) {
        Scanner sc = new Scanner(System.in);
        String s = null;
        while (s == null || s.isEmpty()||!isAlphabetic(s)) {
            System.out.print(String.format("%s: ", msg));
            s = sc.nextLine();
        }
        return s;
    }

    public String inputPattern(String msg, String pattern) {
        Scanner sc = new Scanner(System.in);
        String data;
        do {
            System.out.println(msg);
            data = sc.nextLine();
        } while (!data.matches(pattern));
        return data;
    }

    public int inputPositiveInt(String msg) {
        Scanner sc = new Scanner(System.in);
        int number;
        while (true) {
            System.out.print(String.format("%s: ", msg));
            try {
                number = sc.nextInt();
                if (number > 0 && number < 100) {
                    return number;
                } else {
                    System.err.println("Please enter the integer number in 1-100");
                }
            } catch (InputMismatchException e) {
                System.err.println("Just input the integer number ");
                sc.next();
            }
        }
    }
}

