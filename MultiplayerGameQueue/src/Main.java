
public class Main {

    public static void main(String[] args) {
        GameQueue gameQueue = new GameQueue();
        int choice;
        do {
            System.out.println("\n1. Join queue\n2. Out queue and join game\n3. Exit");
            choice = gameQueue.getChoice();
            gameQueue.execute(choice);
        } while (choice < 3);
    }
}
