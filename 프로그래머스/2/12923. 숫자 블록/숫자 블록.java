class Solution {
    public int[] solution(long begin, long end) {
        int s = (int) begin;
        int e = (int) end;
        int[] answer = new int[e-s+1];
        for(int i = s; i <= e; i++) {
            answer[i - s] = calculate(i);
        }
        return answer;
    }
    
    public static int calculate(int input){
        if(input == 1) return 0;
        int max = 1;
        for(int i = 2; i * i <= input; i++){
            if(input % i == 0){
                max = i;
                if(input / i <= 10000000)
                return input / i;
            }
        }
        return max;
    }
}