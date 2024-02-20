package GameQueue;

public class Node {

    Player player;
    Node next;

    Node() {
    }

    Node(Player x, Node p) {
        player = x;
        next = p;
    }
}
