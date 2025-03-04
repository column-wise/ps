import java.util.*;

class Solution {
    public int[] solution (String[][] places) {
        int[] answer = new int[places.length];
        for(int i = 0; i < places.length; i++) {
            answer[i] = 1;
        }

        List<Node>[] nodes = new ArrayList[5];

        for(int i = 0; i < 5; i++) {
            nodes[i] = new ArrayList<>();
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    if(places[i][j].charAt(k) == 'P') {
                        nodes[i].add(new Node(j, k));
                    }
                }
            }
        }

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < nodes[i].size(); j++) {
                for(int k = j+1; k < nodes[i].size(); k++) {
                    if(answer[i] == 1 && nodes[i].get(j).isClose(places[i], nodes[i].get(k))) {
                        continue;
                    } else {
                        answer[i] = 0;
                        break;
                    }
                }
            }
        }

        return answer;
    }

    private static class Node {
        int x;
        int y;

        public Node (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isClose(String[] places, Node node) {
            if(Math.abs(x - node.x) + Math.abs(y - node.y) > 2) {
                return true;
            }

            if(x == node.x || y == node.y) {
                return places[(x+node.x)/2].charAt((y+node.y)/2) == 'X';
            } else {
                return places[x].charAt(node.y) == 'X' && places[node.x].charAt(y) == 'X';
            }
        }
    }
}