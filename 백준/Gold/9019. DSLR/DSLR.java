import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            Deque<Node> queue = new ArrayDeque<>();
            queue.add(new Node(A, ""));

            boolean[] visited = new boolean[10000];
            visited[A] = true;

            while(!queue.isEmpty()) {
                Node node = queue.poll();

                if(node.num == B){
                    sb.append(node.commandList);
                    sb.append("\n");
                    break;
                } else{
                    int currentNum = node.num;
                    String currentCommandList = node.commandList;

                    int newNum = currentNum * 2 % 10000;
                    if(!visited[newNum]){
                        queue.add(new Node(newNum, currentCommandList+"D"));
                        visited[newNum] = true;
                    }

                    newNum = currentNum - 1;
                    if(newNum == -1){
                        newNum = 9999;
                    }
                    if(!visited[newNum]){
                        queue.add(new Node(newNum, currentCommandList+"S"));
                        visited[newNum] = true;
                    }

                    newNum = currentNum * 10;
                    int temp = newNum/10000;
                    newNum = newNum%10000;
                    newNum += temp;

                    if(!visited[newNum]) {
                        queue.add(new Node(newNum, currentCommandList+"L"));
                        visited[newNum] = true;
                    }

                    temp = currentNum%10;
                    newNum = currentNum/10;
                    newNum += temp*1000;
                    if(!visited[newNum]) {
                        queue.add(new Node(newNum, currentCommandList+"R"));
                        visited[newNum] = true;
                    }
                }
            }
        }
        System.out.println(sb);
    }

    private static class Node {
        int num;
        String commandList;

        private Node(int num, String commandList) {
            this.num = num;
            this.commandList = commandList;
        }
    }
}