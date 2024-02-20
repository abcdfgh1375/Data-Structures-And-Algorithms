package GameQueue;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

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

    public String inputStr(String msg) {
        Scanner sc = new Scanner(System.in);
        String s = null;
        while (s == null || s.isEmpty()) {
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
