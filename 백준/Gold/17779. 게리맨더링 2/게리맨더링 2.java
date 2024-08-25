import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[]args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int [][] city = new int[n][n];
        List<District> districts = new ArrayList<>();
        int sum = 0;
        int answer = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                city[i][j] = Integer.parseInt(st.nextToken());
                sum += city[i][j];
            }
        }

        for(int a = 0; a < n; a++){
            for(int b = 0; b < n; b++){
                for(int c = 1; c < n; c++){
                    for(int d = 1; d < n; d++){
                        if(a+c+d >= n || b-c < 0 || b+d >= n){
                            break;
                        }
                        districts.add(new District(a, b, c, d));
                    }
                }
            }
        }

        for(int i = 0; i < districts.size(); i++) {

            int p1 = 0;
            int p2 = 0;
            int p3 = 0;
            int p4 = 0;
            int p5 = 0;

            District d = districts.get(i);

            for (int r = 0; r < d.x1; r++) {
                for (int c = 0; c < n; c++) {
                    if (c <= d.y1) {
                        p1 += city[r][c];
                    } else {
                        p2 += city[r][c];
                    }
                }
            }

            for (int j = 0; j < d.d1; j++) {
                for (int c = 0; c < d.y1 - j; c++) {
                    p1 += city[d.x1 + j][c];
                }
            }

            for (int j = 0; j <= d.d2; j++) {
                for (int c = d.y1 + 1 + j; c < n; c++) {
                    p2 += city[d.x1 + j][c];
                }
            }

            for (int j = 0; j <= d.d2; j++) {
                for (int c = 0; c < d.y2 + j; c++) {
                    p3 += city[d.x2 + j][c];
                }
            }


            for (int j = 1; j <= d.d1; j++) {
                for (int c = d.y4-j+1; c < n; c++) {
                    p4 += city[d.x4 + j][c];
                }
            }

            for (int r = d.x3 + 1; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (c < d.y3) {
                        p3 += city[r][c];
                    } else {
                        p4 += city[r][c];
                    }
                }
            }

            p5 = sum - p1 - p2 - p3 - p4;

//            System.out.println("x1, y1, d1, d2 : " + d.x1 +" "+d.y1+" "+d.d1+" "+d.d2);
//            System.out.println(p1 + " " + p2 + " " + p3 + " " + p4 + " " + p5 + " ");

            int max = Math.max(p1, p2);
            max = Math.max(max,p3);
            max = Math.max(max, p4);
            max = Math.max(max, p5);

            int min = Math.min(p1, p2);
            min = Math.min(min,p3);
            min = Math.min(min,p4);
            min = Math.min(min,p5);

            answer = Math.min(answer, max - min);

        }

        System.out.println(answer);

    }

    static class District{
        int x1;
        int y1;
        int d1;
        int d2;

        int x2;
        int y2;

        int x3;
        int y3;

        int x4;
        int y4;


        District(int x, int y, int d1, int d2){
            this.x1 = x;
            this.y1 = y;
            this.d1 = d1;
            this.d2 = d2;

            x2 = x1+d1;
            y2 = y1-d1;

            x3 = x2+d2;
            y3 = y2+d2;

            x4 = x1+d2;
            y4 = y1+d2;
        }



    }

    static class Point {
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}