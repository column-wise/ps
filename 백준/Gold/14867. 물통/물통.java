import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());
        int targetA = Integer.parseInt(st.nextToken());
        int targetB = Integer.parseInt(st.nextToken());

        Map<Point, Integer> visited = new HashMap<>();

        Deque<Status> queue = new ArrayDeque<>();
        queue.add(new Status(0,0,0));
        visited.put(new Point(0, 0), 0);

        while(!queue.isEmpty()) {
            Status status = queue.poll();
            int x = status.x;
            int y = status.y;

            int nx = sizeA;
            int ny = y;
            if(!visited.containsKey(new Point(nx, ny))) {
                visited.put(new Point(nx, ny), status.turn + 1);
                queue.add(new Status(nx, ny, status.turn + 1));
            }

            nx = x;
            ny = sizeB;
            if(!visited.containsKey(new Point(nx, ny))) {
                visited.put(new Point(nx, ny), status.turn + 1);
                queue.add(new Status(nx, ny, status.turn + 1));
            }

            nx = x;
            ny = 0;
            if(!visited.containsKey(new Point(nx, ny))) {
                visited.put(new Point(nx, ny), status.turn + 1);
                queue.add(new Status(nx, ny, status.turn + 1));
            }

            nx = 0;
            ny = y;
            if(!visited.containsKey(new Point(nx, ny))) {
                visited.put(new Point(nx, ny), status.turn + 1);
                queue.add(new Status(nx, ny, status.turn + 1));
            }

            int temp = x+y;
            if(temp > sizeA) {
                nx = sizeA;
                ny = temp - sizeA;
                if(!visited.containsKey(new Point(nx, ny))) {
                    visited.put(new Point(nx, ny), status.turn + 1);
                    queue.add(new Status(nx, ny, status.turn + 1));
                }
            } else {
                nx = temp;
                ny = 0;
                if(!visited.containsKey(new Point(nx, ny))) {
                    visited.put(new Point(nx, ny), status.turn + 1);
                    queue.add(new Status(nx, ny, status.turn + 1));
                }
            }

            if(temp > sizeB) {
                nx = temp - sizeB;
                ny = sizeB;
                if(!visited.containsKey(new Point(nx, ny))) {
                    visited.put(new Point(nx, ny), status.turn + 1);
                    queue.add(new Status(nx, ny, status.turn + 1));
                }
            } else {
                nx = 0;
                ny = temp;
                if(!visited.containsKey(new Point(nx, ny))) {
                    visited.put(new Point(nx, ny), status.turn + 1);
                    queue.add(new Status(nx, ny, status.turn + 1));
                }
            }
        }

        Point target = new Point(targetA, targetB);
        System.out.println(visited.get(target) != null ? visited.get(target) : "-1");
    }

    static class Status {
        int x;
        int y;
        int turn;

        Status(int x, int y, int turn) {
            this.x = x;
            this.y = y;
            this.turn = turn;
        }
    }

    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}