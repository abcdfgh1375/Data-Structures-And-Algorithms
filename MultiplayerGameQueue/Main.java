package MultiplayerGameQueue;

public class Main {
    public static void main(String[] args) {
        GameQueue gameQueue = new GameQueue();
        int choice;
        do{
            System.out.println("1. Join queue\n2. Update queue\n3. Exit");
            choice = gameQueue.getChoice();
            gameQueue.execute(choice);
        }while(choice < 4);
}
}
