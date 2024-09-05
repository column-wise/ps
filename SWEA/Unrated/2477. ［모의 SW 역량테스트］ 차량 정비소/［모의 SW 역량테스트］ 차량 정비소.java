import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine());

            // 접수 창구 개수:N / 정비 창구 개수:M / 정비소 방문 고객 수:K / 지갑 두고 간 손님 이용 접수 창구: A / 지갑 손님 정비 창구: B
            // 창구 번호와 고객 번호는 1부터 시작한다
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            Staff[] receptionDesks = new Staff[N];
            Staff[] repairDesks = new Staff[M];
            Deque<Costumer> receptionWaiting = new ArrayDeque<>();
            Deque<Costumer> repairWaiting = new ArrayDeque<>();
            List<Costumer> completedCostumer = new ArrayList<>();

            // 접수 창구 소요시간 / 정비 창구 소요시간 / 고객 도착 시간
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                receptionDesks[i] = new Staff(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++){
                repairDesks[i] = new Staff(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < K; i++){
                receptionWaiting.add(new Costumer(i+1, Integer.parseInt(st.nextToken())));
            }

            int sum = 0;
            int time = 0;
            while(true){

                for(int i = 0; i < M; i++){
                    Staff s = repairDesks[i];
                    if(s.respondingCustomer != null && s.respondingCustomer.completeRepairTime == time){
                        completedCostumer.add(s.respondingCustomer);
                        s.respondingCustomer = null;
                    }
                }

                // 접수 창구에서 처리된 고객이 있는지 확인
                for(int i = 0; i < N; i++){
                    Staff s = receptionDesks[i];
                    // 처리됐다면 정비 창구 대기 큐에 넣음
                    if(s.respondingCustomer != null && s.respondingCustomer.completeReceptionTime == time){
                        repairWaiting.add(s.respondingCustomer);
                        s.respondingCustomer = null;
                    }
                }

                // 정비 창구에 빈 자리가 있는지 확인
                for(int i = 0; i < M; i++){
                    Staff s = repairDesks[i];

                    // 빈 자리가 있고 정비 대기 중인 고객이 있으면
                    if(s.respondingCustomer == null && !repairWaiting.isEmpty()){
                        s.respondingCustomer = repairWaiting.pollFirst();
                        s.respondingCustomer.UsedRepairDesk = i + 1;
                        s.respondingCustomer.completeRepairTime = time + s.processingTime;
                    }
                }

                // 정비소에 도착하여 대기하는 고객이 있음
                while(!receptionWaiting.isEmpty() && receptionWaiting.peekFirst().arrivalTime <= time){
                    boolean canAccept = true;

                    // 접수 창구에서 빈 곳이 있는지 탐색
                    for(int i = 0; i < N; i++){
                        Staff s = receptionDesks[i];
                        if(s.respondingCustomer == null){
                            s.respondingCustomer = receptionWaiting.pollFirst();
                            s.respondingCustomer.UsedReceptionDesk = i + 1;
                            s.respondingCustomer.completeReceptionTime = time + s.processingTime;
                            break;
                        }

                        if(i == N-1){
                            canAccept = false;
                        }
                    }

                    if(!canAccept){
                        break;
                    }
                }
                
                time ++;
                if(completedCostumer.size() == K){
                    break;
                }
            }

            for(Costumer c : completedCostumer){
                if(c.UsedReceptionDesk == A && c.UsedRepairDesk == B){
                    sum += c.num;
                }
            }

            System.out.println("#"+test_case+" "+(sum!=0?sum:-1));

        }
    }

    private static class Staff{
        int processingTime;
        Costumer respondingCustomer;

        public Staff(int processingTime) {
            this.processingTime = processingTime;
            respondingCustomer = null;
        }
    }

    private static class Costumer{
        int num;
        int arrivalTime;
        int UsedReceptionDesk;
        int UsedRepairDesk;
        int completeReceptionTime;
        int completeRepairTime;

        public Costumer(int num, int arrivalTime) {
            this.num = num;
            this.arrivalTime = arrivalTime;
        }
    }
}