import java.util.*;

class Solution {

    static Stack<Node> deleted;
    static Node head;
    static Node tail;
    static Node cursor;

    public static String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();
        deleted = new Stack<>();
        head = null;
        tail = null;
        cursor = null;

        for(int i = 0; i < n; i++) {
            Node node = new Node(i);
            if(head == null) {
                head = node;
                tail = node;
            } else {
                node.prev = tail;
                tail.next = node;
                tail = node;
            }
        }

        int count = 0;
        Node cur = head;
        while(cur != null) {
            if(count == k) {
                cursor = cur;
                break;
            }
            count++;
            cur = cur.next;
        }

        System.out.println(cursor.index);

        for(int i = 0; i < cmd.length; i++) {
            String[] inputs = cmd[i].split(" ");
            if(inputs[0].equals("U")) {
                up(Integer.parseInt(inputs[1]));
            } else if(inputs[0].equals("D")) {
                down(Integer.parseInt(inputs[1]));
            } else if(inputs[0].equals("C")) {
                delete();
            } else if(inputs[0].equals("Z")) {
                retrieve();
            }
        }

        cur = head;
        for(int i = 0; i < n; i++) {
            if(cur == null) {
                answer.append("X");
            } else if(cur.index != i){
                answer.append("X");
            } else {
                answer.append("O");
                cur = cur.next;
            }
        }

        return answer.toString();
    }
    private static class Node {
        int index;
        Node prev;
        Node next;

        public Node(int index) {
            this.index = index;
        }
    }

    private static void up (int x) {
        for(int i = 0; i < x; i++) {
            if(cursor.prev != null) cursor = cursor.prev;
            else return;
        }
    }

    private static void down (int x) {
        for(int i = 0; i < x; i++) {
            if(cursor.next != null) cursor = cursor.next;
            else return;
        }
    }

    private static void delete () {
        deleted.add(cursor);
        if(cursor.prev != null) cursor.prev.next = cursor.next;
        else head = cursor.next;
        if(cursor.next != null) {
            cursor.next.prev = cursor.prev;
            cursor = cursor.next;
        } else {
            cursor = cursor.prev;
        }
    }

    private static void retrieve () {
        if(deleted.isEmpty()) return;
        Node node = deleted.pop();

        if(node.prev != null) node.prev.next = node;
        else head = node;
        if(node.next != null) node.next.prev = node;
    }
}