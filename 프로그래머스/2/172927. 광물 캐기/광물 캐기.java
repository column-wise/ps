import java.lang.Math;
import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int totalPicks = picks[0] + picks[1] + picks[2];
        int mineralsCanPick = Math.min(totalPicks * 5, minerals.length);
        
        int d = mineralsCanPick / 5;
        int r = mineralsCanPick % 5;
        
        Block[] blocks = new Block[d+1];
        PriorityQueue<Block> pq = new PriorityQueue<>();
        
        for(int i = 0; i < d; i++) {
            Block block = new Block(5);
            for(int j = 0; j < 5; j++) {
                String mineral = minerals[i*5 + j];
                if(mineral.equals("diamond")) {
                    block.sum += 25;
                    block.minerals.add(25);
                } else if(mineral.equals("iron")) {
                    block.sum += 5;
                    block.minerals.add(5);
                } else {
                    block.sum += 1;
                    block.minerals.add(1);
                }
            }
            
            pq.add(block);
        }
        
        if(r != 0) {
            Block block = new Block(r);
            for(int i = d * 5; i < mineralsCanPick; i++) {
                String mineral = minerals[i];
                if(mineral.equals("diamond")) {
                    block.sum += 25;
                    block.minerals.add(25);
                } else if(mineral.equals("iron")) {
                    block.sum += 5;
                    block.minerals.add(5);
                } else {
                    block.sum += 1;
                    block.minerals.add(1);
                }
            }
            pq.add(block);
        }
        
        for(int i = 0; i < picks[0] && !pq.isEmpty(); i++) {
            Block block = pq.poll();
            answer += block.count;
        }
        
        for(int i = 0; i < picks[1] && !pq.isEmpty(); i++) {
            Block block = pq.poll();
            for(Integer mineral : block.minerals) {
                if(mineral == 25) {
                    answer += 5;
                } else {
                    answer += 1;
                }
            }
        }
        
        for(int i = 0; i < picks[2] && !pq.isEmpty(); i++) {
            Block block = pq.poll();
            for(Integer mineral : block.minerals) {
                answer += mineral;
            }
        }
        
        return answer;
    }
    
    private class Block implements Comparable<Block> {
        int sum;
        int count;
        List<Integer> minerals;
        
        public Block(int count) {
            sum = 0;
            this.count = count;
            minerals = new ArrayList<>();
        }
        
        public int compareTo(Block o) {
            return Integer.compare(o.sum, sum);
        }
    }
    
}