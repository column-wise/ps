class Solution {
    int[][] arr;
    public int[] solution(int[][] input) {
        arr = input;
        int R = input.length;
        int C = input[0].length;
        return devideConquer(0, R-1, 0, C-1);
    }
    
    private int[] devideConquer(int sx, int ex, int sy, int ey) {
        if(sx == ex && sy == ey) {
            if(arr[sx][sy] == 0) return new int[]{1,0};
            else return new int[]{0,1};
        }
        
        int zCount = 0;
        int oCount = 0;
        
        for(int i = sx; i <= ex; i++) {
            for(int j = sy; j <= ey; j++) {
                if(arr[i][j] == 0) zCount++;
                else oCount++;
            }
        }
        
        if(zCount == (ex-sx+1) * (ey-sy+1)) {
            return new int[]{1,0};
        }
        
        if(oCount == (ex-sx+1) * (ey-sy+1)) {
            return new int[]{0,1};
        }
        
        int[] ret = new int[2];
        int[] result;
        int mx = (sx + ex) / 2;
        int my = (sy + ey) / 2;
        result = devideConquer(sx, mx, sy, my);
        ret[0] += result[0];
        ret[1] += result[1];
        result = devideConquer(sx, mx, my+1, ey);
        ret[0] += result[0];
        ret[1] += result[1];
        result = devideConquer(mx+1, ex, sy, my);
        ret[0] += result[0];
        ret[1] += result[1];
        result = devideConquer(mx+1, ex, my+1, ey);
        ret[0] += result[0];
        ret[1] += result[1];
        return ret;
        
    }
}