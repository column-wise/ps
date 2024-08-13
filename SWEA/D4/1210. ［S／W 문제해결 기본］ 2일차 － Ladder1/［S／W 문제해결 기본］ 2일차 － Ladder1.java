import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int[][] map;
	static int[] dx = {0,-1,0};
	static int[] dy = {-1,0,1};
	public static void main(String args[]) throws Exception{
		int T = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[100][102];
		for(int test_case = 1; test_case <= T; test_case++){
                int tc = Integer.parseInt(br.readLine());
                
                for(int i = 0; i < 100; i++) {
                    st = new StringTokenizer(br.readLine());
                    for(int j = 1; j < 101; j++) {
                        map[i][j] = Integer.parseInt(st.nextToken());
                    }
                }

                int answer = 0;

                // 맨 아래 줄, 2가 있는 위치를 찾은 후, 위로 올라가며 탐색
                for(int i = 1; i < 101; i++) {
                    if(map[99][i] == 2) {
                        // direction 0: 왼쪽, 1: 위, 2: 오른쪽
                        int direction = 1;
                        int x = 99;
                        int y = i;

                        while(true) {
                            if(x==0) {
                                answer = y-1;
                                break;
                            }else {
                                direction = directionChange(x, y, direction);
                                x += dx[direction];
                                y += dy[direction];
                            }
                        }
                        if(answer != 0) {
                            break;
                        }
                    }
                }

                System.out.println("#"+test_case+" "+answer);

            }
	}

    public static int directionChange(int x, int y, int direction) {
        if(direction == 1) {
            if(map[x+dx[0]][y+dy[0]] == 1) {
                direction = 0;
            }else if(map[x+dx[2]][y+dy[2]] == 1) {
                direction = 2;
            }
        }else {
            if(map[x+dx[1]][y+dy[1]] == 1) {
                direction = 1;
            }
        }
        return direction;
    }

}
