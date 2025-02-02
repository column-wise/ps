import java.util.*;

class Solution {
            public static String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        Plan[] planList = new Plan[plans.length];
        Stack<Plan> stack = new Stack<>();

        for (int i = 0; i < plans.length; i++) {
            String[] plan = plans[i];
            String[] time = plan[1].split(":");

            planList[i] = new Plan(plan[0], Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]), Integer.parseInt(plan[2]));
        }

        Arrays.sort(planList);
        for(Plan p : planList) {
            System.out.println(p.name + " " + p.start + " " + p.left);
        }

        int curTime = 0;

        for(int i = 0; i < planList.length; i++) {
            Plan p = planList[i];

            if(stack.isEmpty()) {
                stack.push(p);
                curTime = p.start;
            } else {
                while(!stack.isEmpty() && curTime + stack.peek().left <= p.start) {
                    Plan doing = stack.pop();
                    curTime += doing.left;
                    answer.add(doing.name);
                }
                
                if(!stack.isEmpty()) {
                    Plan doing = stack.pop();
                    stack.push(new Plan(doing.name, p.start + p.left, curTime + doing.left - p.start));
                }

                stack.push(p);
                curTime = p.start;
            }
        }

        while(!stack.isEmpty()) {
            Plan doing = stack.pop();
            answer.add(doing.name);
        }

        return answer.toArray(new String[answer.size()]);
    }

    private static class Plan implements Comparable<Plan>{
        String name;
        int start;
        int left;

        public Plan(String name, int start, int left) {
            this.name = name;
            this.start = start;
            this.left = left;
        }

        public int compareTo(Plan o) {
            return start - o.start;
        }
    }
}