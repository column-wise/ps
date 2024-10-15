import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Belt conveyor[] = new Belt[2*N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < 2*N; i++){
            conveyor[i] = new Belt(Integer.parseInt(st.nextToken()));
        }

        conveyor[0].prev = conveyor[2*N-1];
        conveyor[0].next = conveyor[1];

        conveyor[2*N-1].next = conveyor[0];
        conveyor[2*N-1].prev = conveyor[2*N-2];

        for(int i = 1; i < 2*N-1; i++){
            conveyor[i].next = conveyor[i+1];
            conveyor[i].prev = conveyor[i-1];
        }

        Belt entryPosition = conveyor[0];
        Belt exitPosition = conveyor[N-1];
        Belt cursor;

        int process = 0;
        int count = 0;

        while(true){
            process++;
            entryPosition = entryPosition.prev;
            exitPosition = exitPosition.prev;
            if(exitPosition.isRobotOn) exitPosition.isRobotOn = false;

            cursor = exitPosition.prev;
            while(cursor != entryPosition.prev){
                if(cursor.isRobotOn && !cursor.next.isRobotOn && cursor.next.durability > 0){
                    cursor.isRobotOn = false;
                    cursor.next.isRobotOn = true;
                    cursor.next.durability--;
                    if(cursor.next.durability == 0){
                        count++;
                    }
                }
                cursor = cursor.prev;
            }
            if(exitPosition.isRobotOn) exitPosition.isRobotOn = false;

            if(!entryPosition.isRobotOn && entryPosition.durability > 0){
                entryPosition.isRobotOn = true;
                entryPosition.durability--;
                if(entryPosition.durability == 0){
                    count++;
                }
            }
//
//            System.out.println("process: "+process);
//            cursor = entryPosition;
//            for(int i = 0; i < 2*N; i++){
//                System.out.print(cursor.durability+" ");
//                cursor = cursor.next;
//            }
//            System.out.println();
//
//            cursor = entryPosition;
//            for(int i = 0; i < 2*N; i++){
//                System.out.print(cursor.isRobotOn==true?"0 ":"X ");
//                cursor = cursor.next;
//            }
//            System.out.println();

            if(count >= K) break;
        }

        System.out.println(process);
    }

    private static class Belt{
        int durability;
        boolean isRobotOn;
        Belt next;
        Belt prev;

        private Belt(int durability){
            this.durability = durability;
            isRobotOn = false;
        }
    }
}