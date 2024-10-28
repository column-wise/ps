import java.util.*;
class Solution {
    static int N;
    static int[] parents;
    static int[] dx = {0,1};
    static int[] dy = {1,0};
    
    public int solution(int[][] land) {
        int answer = 0;
        int R = land.length;
        int C = land[0].length;
        N = R*C;
        
        Set<Integer>[] groups = new HashSet[C];
        int[] oils = new int[C];
        
        for(int i = 0; i < C; i++){
            groups[i] = new HashSet<>();
        }
        
        init();
        
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                for(int k = 0; k < 2; k++){
                    int nr = i+dx[k];
                    int nc = j+dy[k];
                    
                    if(nr >= R || nc >= C) continue;
                    
                    if(land[i][j]==1 && land[nr][nc]==1){
                        union(i*C+j, nr*C+nc);
                    }
                }
            }
        }
        
        for(int j = 0; j < C; j++){
            for(int i = 0; i < R; i++){
                if(land[i][j] == 1){
                    groups[j].add(find(i*C+j));
                }
            }
        }
        
        for(int i = 0; i < C; i++){
            Iterator<Integer> iterator = groups[i].iterator();
            while(iterator.hasNext()){
                oils[i] -= parents[iterator.next()];
            }
            answer = Math.max(answer, oils[i]);
        }
        
        return answer;
    }
    
    private static void init(){
        parents = new int[N];
        for(int i = 0; i < N; i++){
            parents[i] = -1;
        }
    }
    
    private static int find(int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }
    
    private static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        
        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }
}
