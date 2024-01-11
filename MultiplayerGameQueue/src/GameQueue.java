import java.util.*;

//abstract class Menu//
abstract class Menu {
    public abstract void execute(int choice);
}
//class GameQueue//
public class GameQueue extends Menu {
    //execute code//
    Player Player = new Player();
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
                int times = Player.inputPositiveInt("Choose number of user can join game");
                for(int i=0; i< times;i++){
                    dequeue();}
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
    }

    public int getChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter selection: ");
        return Integer.parseInt(sc.nextLine());
    }

    //input code//
    public void inputPlayer(Player player) {
        Scanner sc = new Scanner(System.in);
        player.setCharacter(player.inputStr("Enter character name"));
        player.setLevel(player.inputPositiveInt("Enter player's level"));
    }

    void inputId(Player player) {
        Node p = front;
        if (isEmpty()) {
            player.setId(player.inputPattern("Enter player's id, e.g., AD123, AD234", "AD\\d{3}"));
        } else {
                player.setId(player.inputPattern("Enter player's id, e.g., AD123, AD234", "AD\\d{3}"));
                do {
                    if (player.getId().equals(p.player.getId())) {
                        break;
                    }
                    p = p.next;
                } while (p != front);
                p = front;
        }
    }

    //action code//
    boolean isEmpty() {
        return (front == null);
    }
//add object into queue
    public void enqueue(Player player) throws NullPointerException {
        Node newNode = new Node(player, null);
        if (isEmpty()) {
            front = rear = newNode;
            rear.next = front;
        } else {
            rear.next = newNode;
            rear = newNode;
            rear.next = front;
        }
        System.out.println("Player " + player.getId() + " joined the game queue.");
}
//delete the first object of queue and update queue
    public void dequeue() throws NullPointerException{
        if (isEmpty()) {
            System.out.println("Queue is empty!");
        } else {
            if(front == front.next){
                System.out.println("Player " + front.player.getId() + " lefted the game queue.");
                front = null;
            }else{
            System.out.println("Player " + front.player.getId() + " lefted the game queue.");
            front = front.next;
            rear.next = front;}
        }
    }
//display the queue
    public void displayQueue() throws NullPointerException {
        if(isEmpty()){
            System.out.println("Queue is empty.");
        }else{
        Node p = front;
        System.out.println("\nGame Queue: ");
        do {
            if (p == null) {
            System.out.println("Node is null.");
            return;
            }
            System.out.println(p.player.toString());
            p = p.next;
        } while (p != front);
        System.out.println();
    }
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

    private String id;
    private String character;
    private int level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    

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
            System.out.print(String.format("%s: ", msg));
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
                if (number > 0 && number < 101) {
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




