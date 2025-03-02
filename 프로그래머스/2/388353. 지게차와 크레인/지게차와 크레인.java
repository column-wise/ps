import java.util.*;

class Solution {

    static int count;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        System.out.println(solution(new String[] {"AZWQY","CAABX","BBDDA","ACACA"}, new String[] {"A","BB","A"}));
        System.out.println(solution(new String[] {"HAH","HBH","HHH","HAH","HBH"}, new String[] {"C","B","B","B","B","H"}));
    }
    public static int solution(String[] storage, String[] requests) {
        count = 0;
        String[][] storageWithPadding = new String[storage.length+2][storage[0].length()+2];
        for(int i = 0; i < storage.length; i++) {
            for(int j = 0; j < storage[i].length(); j++) {
                storageWithPadding[i+1][j+1] = storage[i].substring(j,j+1);
            }
        }

        for(String request : requests) {
            if(request.length() >= 2) {
                pickContainersByCrane(storageWithPadding, request.substring(0, 1));
            } else {
                pickContainersByCar(storageWithPadding, request);
                for(int i = 0; i < storageWithPadding.length; i++) {
                    for(int j = 0; j < storageWithPadding[i].length; j++) {
                        if(storageWithPadding[i][j] != null && storageWithPadding[i][j].equals("0")) {
                            storageWithPadding[i][j] = null;
                        }
                    }
                }
            }
        }

        return (storage.length * storage[0].length()) - count;
    }

    private static void pickContainersByCar(String[][] storage, String request) {
        Queue<Coord> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[storage.length][storage[0].length];

        queue.add(new Coord(0, 0));
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            Coord cur = queue.poll();
            int x = cur.x;
            int y = cur.y;

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= storage.length || ny < 0 || ny >= storage[0].length || visited[nx][ny]) continue;
                if(storage[nx][ny] == null) {
                    queue.add(new Coord(nx, ny));
                    storage[nx][ny] = null;
                    visited[nx][ny] = true;
                } else if(storage[nx][ny].equals(request)) {
                    storage[nx][ny] = "0";
                    count ++;
                }
            }
        }
    }

    private static void pickContainersByCrane(String[][] storage, String request) {
        for(int i = 1; i < storage.length - 1; i++) {
            for(int j = 1; j < storage[i].length - 1; j++) {
                if(storage[i][j] != null && storage[i][j].equals(request)) {
                    storage[i][j] = null;
                    count ++;
                }
            }
        }
    }

    private static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}