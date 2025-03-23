class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int h = (n / w) + 1;
        if(n % w == 0) h--;
        int[][] boxes = new int[h][w];
        
        int x = -1;
        int y = -1;
        int box = 1;
        
        for(int i = 0; i < h; i++) {
            if(i%2 == 0) {
                for(int j = 0; j < w; j++) {
                    boxes[i][j] = box;
                    if(boxes[i][j] == num) {
                        x = i;
                        y = j;
                    }
                    
                    if(box == n) break;
                    box++;
                }
            } else {
                for(int j = w-1; j >= 0; j--) {
                    boxes[i][j] = box;
                    if(boxes[i][j] == num) {
                        x = i;
                        y = j;
                    }
                                        
                    if(box == n) break;
                    box++;
                }               
            }
        }
        
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                System.out.print(boxes[i][j] + " ");
            }
            System.out.println();
        }
        
        for(int i = x; i < h; i++) {
            if(boxes[i][y] != 0) answer++;
        }
        
        return answer;
    }
}