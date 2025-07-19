import java.util.*;
class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int[] parents;
    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        int R = maps.length;
        int C = maps[0].length();
        parents = new int[R*C];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                char x = maps[i].charAt(j);
                if(x != 'X') {
                    parents[i*C + j] = -(x - '0');
                }
            }
        }
        
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                for(int d = 0; d < 4; d++) {
                    int ni = i + dx[d];
                    int nj = j + dy[d];
                    
                    if(ni < 0 || nj < 0 || ni >= R || nj >= C) continue;
                    if(maps[i].charAt(j) != 'X' && maps[ni].charAt(nj) != 'X') {
                        union(i*C + j, ni*C + nj);
                    }
                }
            }
        }
        
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(parents[i*C + j] < 0) answer.add(-parents[i*C + j]);
            }
        }
        
        Collections.sort(answer);
        
        int[] ret;
        if(answer.size() == 0) {
            ret = new int[]{-1};
        } else {
            ret = new int[answer.size()];
            for(int i = 0; i < answer.size(); i++) {
                ret[i] = answer.get(i);
            }
        }
        
        return ret;
    }
    
    private int find(int a) {
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }
    
    private boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == bRoot) return false;
        
        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }
}