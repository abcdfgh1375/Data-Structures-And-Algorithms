package GameQueue;

import java.util.*;

public abstract class Menu {

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
