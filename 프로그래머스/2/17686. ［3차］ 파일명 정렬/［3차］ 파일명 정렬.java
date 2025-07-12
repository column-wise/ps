import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        int N = files.length;
        String[] answer = new String[N];
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for(int i = 0; i < N; i++) {
            String file = files[i];
            int idx = 0;
            int length = file.length();
            while(!Character.isDigit(file.charAt(idx))) {
                idx++;
            }
            String head = file.substring(0, idx);
            int numStart = idx;
            
            while(idx < length && Character.isDigit(file.charAt(idx))) {
                idx++;
            }
            String number = file.substring(numStart, idx);
            String tail = file.substring(idx, length);
            
            pq.add(new Node(head, number, tail, i));
        }
        
        for(int i = 0; i < N; i++) {
            Node n = pq.poll();
            String fileName = n.head + n.number + n.tail;
            answer[i] = fileName;
        }
        
        return answer;
    }
    
    private class Node implements Comparable<Node>{
        String head;
        String number;
        String tail;
        int order;
        
        Node(String head, String number, String tail, int order) {
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.order = order;
        }
        
        @Override
        public int compareTo(Node o) {
            int ret = head.compareToIgnoreCase(o.head);
            if(ret == 0) {
                ret = Integer.compare(Integer.parseInt(number), Integer.parseInt(o.number));
            }
            
            if(ret == 0) {
                ret = Integer.compare(order, o.order);
            }
            
            return ret;
        }
    }
}