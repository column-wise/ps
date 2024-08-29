import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static List<Person> persons;
    static List<Stair> stairs;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            persons = new ArrayList<>();
            stairs = new ArrayList<>();
            int [][] map = new int[N][N];
            int min = Integer.MAX_VALUE;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) {
                        persons.add(new Person(i, j));
                    } else if(map[i][j] != 0 && map[i][j] != 1) {
                        stairs.add(new Stair(i,j,map[i][j]));
                    }
                }
            }

            int num = persons.size();
            for(int i = 0; i < (1<<num); i++) {
                List<Person> comb = new ArrayList<>();
                stairs.get(0).queue = new ArrayList<>();
                stairs.get(1).queue = new ArrayList<>();
                for(int j = 0; j < num; j++) {
                    Person p = persons.get(j);
                    if((i&(1<<j)) != 0) {
                        p.choosed = stairs.get(1);
                    } else {
                        p.choosed = stairs.get(0);
                    }
                    p.getDistance();
//                    System.out.println("j = "+j);
//                    System.out.println(p.toString());

                    comb.add(p);
                }
                Collections.sort(comb);
                for(int j = 0; j < num; j++) {
                    Person p = comb.get(j);
                    Stair s = p.choosed;
                    List<Schedule> q = s.queue;
                    if(q.size() >= 3 && p.distance < q.get(q.size()-3).goDownTime){
                        q.add(new Schedule(p,s,p.distance,q.get(q.size()-3).goDownTime+s.timeRequired));
                    } else{
                        q.add(new Schedule(p,s,p.distance,p.distance+s.timeRequired+1));
                    }
                }

//                for(int j = 0; j < 2; j++){
//                    Stair s = stairs.get(j);
//                    for(Schedule schedule : s.queue){
//                        System.out.println(schedule);
//                    }
//                }

                List<Schedule> q1 = stairs.get(0).queue;
                List<Schedule> q2 = stairs.get(1).queue;
                int max = 0;
                if(!q1.isEmpty() && !q2.isEmpty()){
                   max = Math.max(q1.get(q1.size()-1).goDownTime, q2.get(q2.size()-1).goDownTime);
                } else if (!q1.isEmpty()) {
                    max = q1.get(q1.size()-1).goDownTime;
                } else {
                    max = q2.get(q2.size()-1).goDownTime;
                }

                min = Math.min(min, max);
            }

            System.out.println("#"+test_case+" "+min);

        }
    }

    static class Person implements Comparable<Person>{
        int r;
        int c;
        Stair choosed;
        int distance;

        public Person(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void getDistance() {
            distance = Math.abs(r - choosed.r) + Math.abs(c - choosed.c);
        }

        @Override
        public int compareTo(Person o) {
            // TODO Auto-generated method stub
            return distance - o.distance;
        }

        @Override
        public String toString() {
            return "Person [r=" + r + ", c=" + c + ", choosed=" + choosed + ", distance=" + distance + "]";
        }
    }

    static class Stair{
        int r;
        int c;
        int timeRequired;
        List<Schedule> queue;

        public Stair(int r, int c, int time) {
            this.r = r;
            this.c = c;
            timeRequired = time;
        }

        @Override
        public String toString() {
            return "Stair [r=" + r + ", c=" + c + ", timeRequired=" + timeRequired + "]";
        }
    }

    static class Schedule{
        Person p;
        Stair s;
        int arrivedTime;
        int goDownTime;

        public Schedule(Person p, Stair s, int arrivedTime, int goDownTime) {
            super();
            this.p = p;
            this.s = s;
            this.arrivedTime = arrivedTime;
            this.goDownTime = goDownTime;
        }

        @Override
        public String toString() {
            return "Schedule [p=" + p.r +" " +p.c + ", s=" + s.r+" "+s.c + ", arrivedTime=" + arrivedTime + ", goDownTime=" + goDownTime
                    + "]";
        }
    }
}
