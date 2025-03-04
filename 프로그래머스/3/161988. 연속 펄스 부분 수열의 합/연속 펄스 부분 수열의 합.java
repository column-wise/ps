class Solution {
    public static long solution (int[] sequence) {
        long answer = Long.MIN_VALUE;
        long[] prefixSum = new long[sequence.length+1];
        long[] prefixSum2 = new long[sequence.length+1];

        int pulse = -1;
        for(int i = 0; i < sequence.length; i++) {
            prefixSum[i+1] = prefixSum[i] + (sequence[i] * pulse);
            prefixSum2[i+1] = prefixSum2[i] + (sequence[i] * -pulse);
            pulse = -pulse;
        }

        long min = 0;
        long max = Long.MIN_VALUE;

        for(int i = 1; i <= sequence.length; i++) {
            long sum = prefixSum[i] - min;
            if(prefixSum[i] < min) min = prefixSum[i];
            if(sum > max) max = sum;
            answer = Math.max(answer, max);
        }

        min = 0;
        max = Long.MIN_VALUE;
        for(int i = 1; i <= sequence.length; i++) {
            long sum = prefixSum2[i] - min;
            if(prefixSum2[i] < min) min = prefixSum2[i];
            if(sum > max) max = sum;
            answer = Math.max(answer, max);
        }

        return answer;
    }
}